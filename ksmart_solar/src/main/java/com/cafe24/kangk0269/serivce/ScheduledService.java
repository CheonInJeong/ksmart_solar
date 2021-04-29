package com.cafe24.kangk0269.serivce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dao.BidPlantMapper;
import com.cafe24.kangk0269.dao.PolicyMapper;
import com.cafe24.kangk0269.dao.ScheduledMapper;
import com.cafe24.kangk0269.dao.TradeMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;




@Service
public class ScheduledService {
	@Autowired
	private final ScheduledMapper scheduledMapper;
	@Autowired
	private BidComponentMapper bidComponentMapper;
	@Autowired
	private BidPlantMapper bidPlantMapper;
	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private PolicyMapper policyMapper;

	
	Date date = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String today = format.format(date);
	
	public ScheduledService(ScheduledMapper scheduledMapper) {
		this.scheduledMapper = scheduledMapper;
	}
	
	//공고상태코드 변경
	public void updateBidStatus() {
		List<BidListDTO> plant = scheduledMapper.getPlantBidderNumber();
		List<BidListDTO> component = scheduledMapper.getComponentBidderNumber();
		
		for(int i=0; i<plant.size(); i++) {
			int bidderNumber = plant.get(i).getBidPlantDTO().getbPlNumberOfBidder();
			System.out.println(bidderNumber+"어떤 공고 : "+plant.get(i).getBidPlantDTO().getbPlCode());
			//공고마감일에 입찰자 수 0인 경우 거래실패로 상태변경 - 발전소인경우
			if(plant.get(i).getBidPlantDTO().getbPlDateBidding2().equals(today)) {
				if(bidderNumber==0) {
					scheduledMapper.updateBidPlantAc(plant.get(i).getBidPlantDTO().getbPlCode());
				}
			}
			//공고시작일에 공고진행상태 공고승인 > 공고진행중으로 바꾸기
			if(plant.get(i).getBidPlantDTO().getbPlDateBidding1().equals(today)) {
				scheduledMapper.updatePlantConfirmToIng(today);
			}
			
		}

		for(int i=0; i<component.size(); i++) {
			int bidderNumber = component.get(i).getBidComponentDTO().getbCpBidderNumber();
			System.out.println(bidderNumber+"어떤 공고 : "+component.get(i).getBidComponentDTO().getbCpCode());
			//공고마감일에 입찰자 수 0인 경우 거래실패로 상태변경 - 부품인경우
			if(component.get(i).getBidComponentDTO().getbCpDateBidding2().equals(today)) {
				if(bidderNumber==0) {
					scheduledMapper.updateBiComponentAc(component.get(i).getBidComponentDTO().getbCpCode());
				}
			}
			if(component.get(i).getBidComponentDTO().getbCpDateBidding1().equals(today)) {
				scheduledMapper.updateComponentConfirmToIng(today);
			}
			
			
		}
		
	}
	
	//1순위의 거래상태가 대금 미납인 경우 상태변경
	public void updatePaymentStatus() {
		List<TradePriorityDTO> tradePriority = scheduledMapper.getNotPaied();
		for(int i= 0; i<tradePriority.size();i++) {
			String lastDate = tradePriority.get(i).getTradePaymentInDTO().getTrPayinDate2();
			//입금진행상태
			String payinStatus = tradePriority.get(i).getTradePaymentInDTO().getTrPayinStatus();
			//공고코드
			String trPrCode = tradePriority.get(i).getAnnouncedCode();
			//공고 분류
			String bTypeCode = tradePriority.get(i).getBidListDTO().getbTypeCode();
			//입찰코드
			String bCode = tradePriority.get(i).getbCode();
			//순위
			int rank = tradePriority.get(i).getTrPrRank();
			
			if(lastDate!=null) {
				lastDate = lastDate.substring(0,10);
				
				if(lastDate.equals(today)) {
					System.out.println(trPrCode+"--------------------------------trPrCode");
					//대금미납인경우
					if(payinStatus.equals("미납")) {
						//낙찰자테이블의 거래상태 바꾸기 :  11대금처리중 > 12 대금 미납
						scheduledMapper.updateAcInPriority(bCode);
						//입찰자 리스트의 거래상태 바꾸기 :  11대금처리중 > 12 대금 미납
						scheduledMapper.updateAcInbidList(bCode);
						//2순위 낙찰자 테이블에 추가
							//발전소인경우
							if(bTypeCode.equals("1")) {
								BidListDTO plant = scheduledMapper.getPlantRankInfo(trPrCode,rank+1);
								if(plant!=null) {
									System.out.println("다음순위 있음");
									String groupCode = plant.getBidPlantDTO().getbPlGroupcode();
									String mIdSeller = plant.getBidPlantDTO().getmId();
									scheduledMapper.addTradePriod(plant, today,groupCode,mIdSeller);
									scheduledMapper.updateBidSecondState(trPrCode, rank+1);
								}else if(plant==null) {
									System.out.println("다음순위 없음");
									bidPlantMapper.updatePlantFail(trPrCode);
								}
							}
							//부품인경우
							else if(bTypeCode.equals("2")) {
								BidListDTO component = scheduledMapper.getComponentRankInfo(trPrCode,rank+1);
								if(component!=null) {
									System.out.println("다음순위 있음");
									String groupCode = component.getBidComponentDTO().getbCpGroupcode();
									String mIdSeller = component.getBidComponentDTO().getmId();
									scheduledMapper.addTradePriod(component, today,groupCode,mIdSeller);
									scheduledMapper.updateBidSecondState(trPrCode, rank+1);
								}else if(component==null) {
									System.out.println("다음순위 없음");
									bidComponentMapper.updateComponentFail(trPrCode);
								}
							}
					}
					
				}
				
			}
			
		}
	}
	
	//계약마지막날 상태 계약중인경우 payin 테이블 삽입
	public void updatePayIn() throws ParseException {
		
		List<TradePriorityDTO> tradePriority = scheduledMapper.getPriority();
		for(int i= 0; i<tradePriority.size();i++) {
			String lastDate = tradePriority.get(i).getTrPrConclusionDate2();
			String bCode= tradePriority.get(i).getbCode();
			int status = tradePriority.get(i).getTrTypeCode();
			
			if(lastDate!=null) {
				lastDate = lastDate.substring(0,10);
			
				if(lastDate.equals(today)&&status==6) {
					System.out.println("실행할 코드 작성");
					
					scheduledMapper.addPayIn(tradePriority.get(i),today);
					scheduledMapper.updatePriorityStatus(bCode);
					//대금납부테이블에 넣은 입찰자의 tb_bid_list 상태 바꿔주기 by천인정
					scheduledMapper.updateBidStatus(bCode);
	
					
					
				}
			}
	
		}
		
	}
	//계약중 계약취소되어 계약 마감날 다음순위를 낙찰자 테이블에 삽입
	public void updatePriority() {
		List<TradePriorityDTO> priorityFail = scheduledMapper.getPriorityFail();
		int tradePerioddate = policyMapper.getTradePeriod();
		for(int i=0; i<priorityFail.size(); i++) {
			String announcedCode = priorityFail.get(i).getAnnouncedCode();
			int rank = priorityFail.get(i).getTrPrRank();
			rank++;
			//다음순위 대기 입찰자가 있는지
			String bidListDTO = scheduledMapper.getBidListNextRank(announcedCode, rank);
			System.out.println(bidListDTO);
			//다음순위가 있다면 다음순위 낙찰자 테이블에 삽입
			if(bidListDTO!=null) {
				if(bidListDTO.equals("1")) {
					System.out.println("발전소");
					BidPlantDTO BidPlantTradeList = bidPlantMapper.getBidPlantTradeNext(announcedCode, rank);
					Map<String,Object> List = new HashMap<String, Object>();
					List.put("tradePerioddate", tradePerioddate);
					List.put("BidPlantTradeList", BidPlantTradeList);
					tradeMapper.addTradePriority(List);
					scheduledMapper.updateBidSecondState(announcedCode, rank);
				}
				if(bidListDTO.equals("2")) {
					System.out.println("부품");
					BidComponentDTO BidComTradeList = bidComponentMapper.getBidComTradeNext(announcedCode, rank);
					Map<String,Object> List = new HashMap<String, Object>();
					List.put("tradePerioddate", tradePerioddate);
					List.put("BidComTradeList", BidComTradeList);
					tradeMapper.addTradePriority(List);
					scheduledMapper.updateBidSecondState(announcedCode, rank);
				}
			//다음 순위가 없다면 거래 실패
			}else if(bidListDTO==null) {
				String bidType = priorityFail.get(i).getbTypeCode();
				System.out.println(bidType+"-------------------------------------bidType");
				if(bidType.equals("1")) {
					bidPlantMapper.updatePlantFail(announcedCode);
				}
				if(bidType.equals("2")) {
					bidComponentMapper.updateComponentFail(announcedCode);
				}
			}
		}
	}
	//공고 승인에서 공고 진행중으로 바꾸는 메서드
	public void startBid() {
		scheduledMapper.startBidPlant();
		scheduledMapper.startBidComponent();
	}
}
