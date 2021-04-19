package com.cafe24.kangk0269.serivce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.common.Pagination;
import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dao.BidListMapper;
import com.cafe24.kangk0269.dao.BidPlantMapper;
import com.cafe24.kangk0269.dao.FileMapper;
import com.cafe24.kangk0269.dao.PolicyMapper;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dao.TradeMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BoardSellerDTO;
import com.cafe24.kangk0269.dto.FileDTO;

@Service
@Transactional
public class BidListService {
	private final BidListMapper bidListMapper;
	private final FileUtils fileUtils;
	private final FileMapper fileMapper;
	private final BidComponentMapper bidComponentMapper;
	private final BidPlantMapper bidPlantMapper;
	private final PolicyMapper policyMapper;
	private final TradeMapper tradeMapper;
	private final SellMapper sellMapper;
	
	@Autowired
	public BidListService(BidListMapper bidListMapper
						,FileUtils fileUtils
						,FileMapper fileMapper
						,BidComponentMapper bidComponentMapper
						,BidPlantMapper bidPlantMapper
						,PolicyMapper policyMapper
						,TradeMapper tradeMapper
						,SellMapper sellMapper) {
		this.bidListMapper = bidListMapper; 
		this.fileUtils = fileUtils; 
		this.fileMapper = fileMapper; 
		this.bidComponentMapper = bidComponentMapper; 
		this.bidPlantMapper = bidPlantMapper; 
		this.policyMapper = policyMapper; 
		this.tradeMapper = tradeMapper; 
		this.sellMapper = sellMapper; 
	}
	
	public double getDepositRate() {
		return bidListMapper.getDepositRate();
	}
	//공고신청
	public void addbidList(BidListDTO bidListDTO,MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request) throws Exception {
		System.out.println("확인");
		bidListMapper.addbidList(bidListDTO);
		String bCode = bidListMapper.getBidCode(bidListDTO.getAnnouncedCode(), bidListDTO.getmId());
		System.out.println(bCode+"--------------------------등록한 입찰 코드");
		
		List<FileDTO> filelist = fileUtils.parseFileInfo(bCode,2,"입찰서류", multipartHttpServletRequest,request);
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.addFile(filelist);
		}
		 
	}
	//입찰 취소
	public int bidCancel(String bCode) {
		int result = bidListMapper.bidCancel(bCode,"입찰");
		System.out.println(result+"=====================================취소 성공");
		if(result==1) {
			BidListDTO bidListDTO = getBidList(bCode);
			System.out.println(bidListDTO.getbTypeCode()+"========================================공고 종류");
			System.out.println(bidListDTO.getAnnouncedCode()+"=============================================공고코드");
			if(bidListDTO!=null && bidListDTO.getbTypeCode().equals("1")) {
				System.out.println("발전소");
				bidListMapper.bidPlantMemberMinus(bidListDTO.getAnnouncedCode());
			}else if(bidListDTO!=null && bidListDTO.getbTypeCode().equals("2")) {
				System.out.println("부품");
				bidListMapper.bidComponentMemberMinus(bidListDTO.getAnnouncedCode());
			}
		}
		return result;
	}
	//내가 입찰을 했는지 안했는지
	public int getBidListCount(String announceCode, String id) {
		return bidListMapper.getBidListCount(announceCode, id);
	}
	//공고 조회
	public BidListDTO getBidList(String announceCode, String id) {
		return getBidList(announceCode,id,null);
	}
	public BidListDTO getBidList(String bCode) {
		System.out.println("확인");
		return getBidList(null,null,bCode);
	}
	public BidListDTO getBidList(String announceCode, String id,String bCode) {
		return bidListMapper.getBidList(announceCode, id,bCode);
	}
	//이전공고에서 거래중에 취소를 했는지 안했는지
	public int reBidCount(String groupCode, String id) {
		return bidListMapper.reBidCount(groupCode, id);
	}
	public List<FileDTO> getBidFileList() {
		return bidListMapper.getBidFileList();
	}
	//입찰 신청 수정
	public int modifyBidList(BidListDTO bidListDTO,MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request)  {
		System.out.println("확인");
		int result=0;
		List<FileDTO> filelist;
		try {
			bidListMapper.modifyBidList(bidListDTO);
			filelist = fileUtils.parseFileInfo(bidListDTO.getbCode(),2,"입찰서류", multipartHttpServletRequest,request);
			if (CollectionUtils.isEmpty(filelist) == false) {
				fileMapper.removeApplyFile(bidListDTO.getbCode());
				fileMapper.addFile(filelist);
			}
			result=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//입찰 신청시에 등록한 파일 목록
	public List<FileDTO> getBidFileList(String bCode) throws Exception{
		FileDTO fileDto = new FileDTO();
		fileDto.setFileSortIdx(2);
		fileDto.setRelatedTableCode(bCode);
		
		return fileMapper.getFileList(fileDto);
	}
	//계약 취소
	public int tradeCancel(String bCode) {
		bidListMapper.bidCancel(bCode, "계약");
		return bidListMapper.tradeCancel(bCode);
	}
	//환불 가능한 목록
	public List<BidListDTO> getApplyRefundList(String id, String status, String searchKey, String searchValue, BidListDTO bidListDTO ) {
		System.out.println("환불리스트 서비스");
		if(searchKey!=null && searchKey.equals("null")) {
			System.out.println("문자열 unll");
			searchKey = null;
		}
		if(searchValue!=null && searchValue.equals("null")) {
			System.out.println("문자열 unll");
			searchValue = null;
		}
		if(searchKey!=null) {
			if("bTitle".equals(searchKey)) {
				searchKey="bl.b_title";
			}else if("bTypeCode".equals(searchKey)) {
				searchKey="bl.b_type_code";
			}else {
				searchKey=null;
			}
		}
		List<BidListDTO> RefundList = null;
		
		int RefundListCount = bidListMapper.getApplyRefundListCount(id, status, searchKey, searchValue, bidListDTO);
		System.out.println(RefundListCount);
		Pagination pagination = new Pagination(bidListDTO);
		
		pagination.setTotalRecordCount(RefundListCount);
		
		bidListDTO.setPagination(pagination);
		
		
		if(RefundListCount>0) {
			RefundList = bidListMapper.getApplyRefundList(id,status,searchKey,searchValue,bidListDTO);
		}
		int page = (bidListDTO.getCurrentPageNo()-1)*5;
		if(RefundList!=null) {
			for(int i =0 ; i<RefundList.size(); i++) {
				RefundList.get(i).setNum(++page);
			}
		}
		return RefundList;
	}
	//입찰 대기를 입찰실패로
	public void updateBidListsatus() {
		System.out.println("실행");
		List<String> componentList = bidComponentMapper.getComponentSatusList(5,"공고마감");
		List<String> plantList = bidPlantMapper.getPlantSatusList(5,"공고마감");
		Map<String,Object> List = null;
		if(componentList!=null && componentList.size()>0) {
			System.out.println("componentList null 아님");
			List = new HashMap<String,Object>();
			List.put("BList", componentList);
			List.put("status", 1);
			List.put("updateStatus", 2);
			List.put("updateStatusName", "입찰실패");
			int result = bidListMapper.updateBidStatus(List);
			System.out.println(result);
		}
		if(plantList!=null && plantList.size()>0) {
			System.out.println("plantList null 아님");
			List = new HashMap<String,Object>();
			List.put("BList", plantList);
			List.put("status", 1);
			List.put("updateStatus", 2);
			List.put("updateStatusName", "입찰실패");
			int result = bidListMapper.updateBidStatus(List);
			System.out.println(result);
		}
		System.out.println(componentList);
		System.out.println(plantList);
	}
	//입찰성공을 입찰종료,계약중,계약대기
	public void updateBidListsatus3() {
		List<String> componentList = bidComponentMapper.getComponentSatusList(6,"거래진행중");
		List<String> plantList = bidPlantMapper.getPlantSatusList(6,"거래진행중");
		Map<String,Object> List = null;
		if(componentList!=null && componentList.size()>0) {
			System.out.println("componentList null 아님");
			List = new HashMap<String,Object>();
			List.put("BList", componentList);
			List.put("status", 3);
			bidListMapper.updateBidEnd(List);
			bidListMapper.updateBidTrade(List);
			bidListMapper.updateBidTradeWait(List);
		}
		if(plantList!=null && plantList.size()>0) {
			System.out.println("plantList null 아님");
			List = new HashMap<String,Object>();
			List.put("BList", plantList);
			List.put("status", 3);
			bidListMapper.updateBidEnd(List);
			bidListMapper.updateBidTrade(List);
			bidListMapper.updateBidTradeWait(List);
		}
		System.out.println(componentList);
		System.out.println(plantList);
	}
	//계약중인 사람 등록
	public void addTradePriority() {
		//당일 거래진행중으로 바뀐 공고
		List<String> componentList = bidComponentMapper.getComponentSatusList(6,"거래진행중");
		List<String> plantList = bidPlantMapper.getPlantSatusList(6,"거래진행중");
		int tradePerioddate = policyMapper.getTradePeriod();
		if(componentList!=null && componentList.size()>0) {
			List<BidComponentDTO> BidComTradeList = bidComponentMapper.getBidComTradeList(componentList);
			for(int i=0; i<BidComTradeList.size(); i++) {
				Map<String,Object> List = new HashMap<String,Object>();
				List.put("tradePerioddate", tradePerioddate);
				List.put("BidComTradeList", BidComTradeList.get(i));				
				tradeMapper.addTradePriority(List);
			}
			System.out.println(BidComTradeList);
		}
		if(plantList!=null && plantList.size()>0) {
			List<BidPlantDTO> BidPlantTradeList = bidPlantMapper.getBidPlantTradeList(plantList);
			for(int i=0; i<BidPlantTradeList.size(); i++) {
				Map<String,Object> List = new HashMap<String,Object>();
				List.put("tradePerioddate", tradePerioddate);
				List.put("BidPlantTradeList", BidPlantTradeList.get(i));				
				tradeMapper.addTradePriority(List);
			}
			System.out.println(BidPlantTradeList);
		}
		
	}
	//낙찰자 선정기간내에 낙찰자를 선정하지 못한 경우 공고를 취소로
	//낙찰자를 선정한공고는 거래진행중으로 상태 변경
	public void updateBidMemberStatus() {
		List<String> componentList = bidComponentMapper.getComponentSatusList(5,"거래진행중");
		List<String> plantList = bidPlantMapper.getPlantSatusList(5,"거래진행중");
		//sellMapper.updateAcStatus1();
		System.out.println(componentList+"<-부품공고");
		System.out.println(plantList+"<-발전소공고");
		for(int i=0; i<componentList.size();i++) {
			int result = bidComponentMapper.getComponentBidList(componentList.get(i));
			System.out.println(result+"<-낙찰자마감날에 순위를 정했는지");
			if(result!=0) {
				try {
					sellMapper.updateComponentAcStatus1("진행",componentList.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					sellMapper.updateComponentAcStatus1("취소",componentList.get(i));
					bidListMapper.bidCancel(componentList.get(i), "공고취소");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for(int i=0; i<plantList.size();i++) {
			int result = bidPlantMapper.getPlantBidList(plantList.get(i));
			System.out.println(result+"<-낙찰자마감날에 순위를 정했는지");
			if(result!=0) {
				try {
					sellMapper.updateAcStatus1("진행",plantList.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					sellMapper.updateAcStatus1("취소",plantList.get(i));
					bidListMapper.bidCancel(plantList.get(i), "공고취소");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//문의등록
	public int addqnaRequest(BoardSellerDTO boardSellerDTO) {
		return bidListMapper.addqnaRequest(boardSellerDTO);
	}
	//문의수정
	public int modifyQna(String mIdBuyer,String bSubject,String bContents,int bIdx) {
		return bidListMapper.modifyQna(mIdBuyer,bSubject,bContents,bIdx);
	}
	public int removeQna(int bIdx) {
		bidListMapper.removeQnaCo(bIdx);
		bidListMapper.removeQna(bIdx);
		return 0;
	}

}
