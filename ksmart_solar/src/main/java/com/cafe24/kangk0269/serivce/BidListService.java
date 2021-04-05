package com.cafe24.kangk0269.serivce;

import java.util.ArrayList;
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
import com.cafe24.kangk0269.dao.BidListMapper;
import com.cafe24.kangk0269.dao.FileMapper;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.FileDTO;

@Service
@Transactional
public class BidListService {
	private final BidListMapper bidListMapper;
	private final FileUtils fileUtils;
	private final FileMapper fileMapper;
	
	@Autowired
	public BidListService(BidListMapper bidListMapper,FileUtils fileUtils,FileMapper fileMapper) {
		this.bidListMapper = bidListMapper; 
		this.fileUtils = fileUtils; 
		this.fileMapper = fileMapper; 
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
	public Map<String, List<BidListDTO>> getApplyRefundList(String id) {
		List<BidListDTO> RefundPossibleList = bidListMapper.getApplyRefundList(id,"가능");
		List<BidListDTO> RefundCompleteList = bidListMapper.getApplyRefundList(id,"완료");
		List<BidListDTO> RefundRequestList = bidListMapper.getApplyRefundList(id,"신청");
		System.out.println(RefundPossibleList+"--------------------------------------RefundPossibleList");
		System.out.println(RefundCompleteList+"--------------------------------------RefundCompleteList");
		System.out.println(RefundRequestList+"--------------------------------------RefundRequestList");
		Map<String, List<BidListDTO>> refundList = new HashMap<String, List<BidListDTO>>();
		refundList.put("RefundPossibleList", RefundPossibleList);
		refundList.put("RefundCompleteList", RefundCompleteList);
		refundList.put("RefundRequestList", RefundRequestList);
		
		return refundList;
	}

}
