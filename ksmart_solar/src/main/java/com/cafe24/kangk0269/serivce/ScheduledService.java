package com.cafe24.kangk0269.serivce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.FileMapper;
import com.cafe24.kangk0269.dao.ScheduledMapper;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;


@Service
public class ScheduledService {
	@Autowired
	private final ScheduledMapper scheduledMapper;
	
	Date date = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String today = format.format(date);
	
	public ScheduledService(ScheduledMapper scheduledMapper) {
		this.scheduledMapper = scheduledMapper;
	}
	
	//공고마감일에 입찰자 수 0인 경우 거래실패로 상태변경
	public void updateBidStatus() {
		List<BidListDTO> plant = scheduledMapper.getPlantBidderNumber();
		List<BidListDTO> component = scheduledMapper.getComponentBidderNumber();
		
		//발전소인경우
		for(int i=0; i<plant.size(); i++) {
			int bidderNumber = plant.get(i).getBidPlantDTO().getbPlNumberOfBidder();
			
			if(plant.get(i).getBidPlantDTO().getbPlDateBidding2().equals(today)) {
				if(bidderNumber==0) {
					scheduledMapper.updateBidPlantAc(plant.get(i).getBidPlantDTO().getbPlCode());
				}
			}
		}
		//부품인경우
		for(int i=0; i<component.size(); i++) {
			int bidderNumber = component.get(i).getBidComponentDTO().getbCpBidderNumber();
			if(component.get(i).getBidComponentDTO().getbCpDateBidding2().equals(today)) {
				if(bidderNumber==0) {
					scheduledMapper.updateBiComponentAc(component.get(i).getBidComponentDTO().getbCpCode());
				}
			}
			
		}
		
	}
	
	//1순위의 거래상태가 대금 미납인 경우 상태변경
	public void updatePaymentStatus() {
		List<TradePriorityDTO> tradePriority = scheduledMapper.getNotPaied();
		for(int i= 0; i<tradePriority.size();i++) {
			String lastDate = tradePriority.get(i).getTrPrConclusionDate2();
			//공고코드
			String trPrCode = tradePriority.get(i).getTrPrCode();
			//진행상태코드
			int typeCode = tradePriority.get(i).getTrTypeCode();
			//공고 분류
			String bTypeCode = tradePriority.get(i).getBidListDTO().getbTypeCode();
			//입찰코드
			String bCode = tradePriority.get(i).getbCode();
			
			if(lastDate!=null) {
				lastDate = lastDate.substring(0,10);
				
				if(lastDate.equals(today)) {
					//대금미납인경우
					if(typeCode==12) {
						//낙찰자테이블의 거래상태 바꾸기 :  11대금처리중 > 12 대금 미납
						scheduledMapper.updateAcInPriority(trPrCode);
						//입찰자 리스트의 거래상태 바꾸기 :  11대금처리중 > 12 대금 미납
						scheduledMapper.updateAcInbidList(bCode);
						//2순위 낙찰자 테이블에 추가
							//발전소인경우
							if(bTypeCode.equals("1")) {
								BidListDTO plant = scheduledMapper.getPlantRankInfo(trPrCode);
								scheduledMapper.addTradePriod(plant, today);
								
							}
							//부품인경우
							else if(bTypeCode.equals("2")) {
								BidListDTO component = scheduledMapper.getComponentRankInfo(trPrCode);
								scheduledMapper.addTradePriod(component, today);
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
			int status = tradePriority.get(i).getTrTypeCode();
			
			if(lastDate!=null) {
				lastDate = lastDate.substring(0,10);
				/*
				 * Date date = new Date(); SimpleDateFormat format = new
				 * SimpleDateFormat("yyyy-MM-dd"); String today = format.format(date);
				 */
				if(lastDate.equals(today)&&status==6) {
					System.out.println("실행할 코드 작성");
					//내일날짜구하기
					/*
					 * Calendar calendar = Calendar.getInstance();
					 * calendar.setTime(format.parse(today)); calendar.add(Calendar.DATE, 1); String
					 * tomorrow = format.format(calendar.getTime());
					 */
					scheduledMapper.addPayIn(tradePriority.get(i),today);
				
				}
			}
				
				
				

				
		}
		
	}
	
}
