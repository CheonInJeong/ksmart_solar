package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.common.Pagination;
import com.cafe24.kangk0269.dao.BidPlantMapper;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Service
@Transactional
public class BidPlantService {
	private final BidPlantMapper bidPlantMapper;
	
	@Autowired
	public BidPlantService(BidPlantMapper bidPlantMapper) {
		this.bidPlantMapper = bidPlantMapper; 
	}
	
	//공고신청 발전소 반려처리
	public void bidPlantReturn(String bPlCode, String bPlRejectReason) {
		bidPlantMapper.bidPlantReturn(bPlCode, bPlRejectReason);
	}
	
	//공고신청 발전소 승인처리
	public void bidPlantAdmit(String bPlCode) {
		bidPlantMapper.bidPlantAdmit(bPlCode);
	}
	
	// 공고신청 발전소 상세보기
	public BidPlantDTO getNotice(String bPlCode) {
		return  bidPlantMapper.getNotice(bPlCode);
	}
	
	
	// 전체 공고신청 발전소 조회
	public List<BidPlantDTO> getPlantNoticeAdmitList(int start,int end,String searchKey,String searchValue){
		if(searchKey != null && searchKey != "") {
			if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}
		}
		return bidPlantMapper.getPlantNoticeAdmitList(start, end, searchKey, searchValue);
	}
	// 공고신청 발전소 리스트 수
	public int getPlantNoticeAdmitListCnt(String searchKey,String searchValue) {
		if(searchKey != null && searchKey != "") {
			if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}
		}
		return bidPlantMapper.getPlantNoticeAdmitListCnt(searchKey, searchValue);
	}	
	
	
	public List<BidPlantDTO> getBidPlantById(String mId){
		return bidPlantMapper.getBidPlantById(mId);
	}
	
	public List<BidPlantDTO> getBidPlant(String status,String searchKeyPl,String searchValuePl,BidPlantDTO bidPlantDTO) {
		if(searchKeyPl!=null && searchKeyPl.equals("null")) {
			System.out.println("문자열 unll");
			searchKeyPl = null;
		}
		if(searchValuePl!=null && searchValuePl.equals("null")) {
			System.out.println("문자열 unll");
			searchValuePl = null;
		}
		if(searchKeyPl!=null) {
			if("bPlTitle".equals(searchKeyPl)) {
				searchKeyPl="b_pl_title";
			}else if("mId".equals(searchKeyPl)) {
				searchKeyPl="m_id";
			}
			else {
				searchKeyPl=null;
			}
		}
		
		System.out.println(searchKeyPl+"---------------------------searchKeyPl");
		System.out.println(searchValuePl+"---------------------------searchValuePl");
		System.out.println(status+"--------------------------------------status");
		List<BidPlantDTO> bidPlantList = null;
		
		int bidPlantCount = bidPlantMapper.getBidPlantListCount(status, searchKeyPl, searchValuePl);
		System.out.println(bidPlantCount);
		Pagination pagination = new Pagination(bidPlantDTO);
		
		pagination.setTotalRecordCount(bidPlantCount);
		
		bidPlantDTO.setPagination(pagination);
		
		if(bidPlantCount>0) {
			bidPlantList = bidPlantMapper.getBidPlant(status,searchKeyPl,searchValuePl,bidPlantDTO);
		}
		int num = (bidPlantDTO.getCurrentPageNo()-1)*5;
		if(bidPlantList!=null) {
			for(int i=0; i<bidPlantList.size();i++) {
				bidPlantList.get(i).setNum(++num);
			}
		}
		
		
		return bidPlantList;
	}
	public BidPlantDTO getBidPlantByInfo(String announceTitle) {
		BidPlantDTO bidPlantDTO = bidPlantMapper.getBidPlantByInfo(announceTitle);
		return bidPlantDTO;
	}
	public List<BidPlantDTO> getBidPlantMyBid(String sId,String searchKeyPl,String searchValuePl,BidPlantDTO bidPlantDTO) {
		if(searchKeyPl!=null && searchKeyPl.equals("null")) {
			System.out.println("문자열 unll");
			searchKeyPl = null;
		}
		if(searchValuePl!=null && searchValuePl.equals("null")) {
			System.out.println("문자열 unll");
			searchValuePl = null;
		}
		if(searchKeyPl!=null) {
			if("bTitle".equals(searchKeyPl)) {
				searchKeyPl="li.b_title";
			}else if("bCpStatus".equals(searchKeyPl)) {
				searchKeyPl="pl.b_pl_status";
			}else if("trTypeName".equals(searchKeyPl)) {
				searchKeyPl="li.tr_type_name";
			}else if("mId".equals(searchKeyPl)) {
				searchKeyPl="pl.m_id";
			}else {
				searchKeyPl=null;
			}
		}
		System.out.println(searchKeyPl+"---------------------------searchKeyCp");
		System.out.println(searchValuePl+"---------------------------searchValueCp");
		List<BidPlantDTO> bidPlantListdto = null;
		
		int bidPlantCount = bidPlantMapper.getBidPlantCount(sId,searchKeyPl,searchValuePl,bidPlantDTO);
		System.out.println(bidPlantCount);
		Pagination pagination = new Pagination(bidPlantDTO);
		
		pagination.setTotalRecordCount(bidPlantCount);
		
		bidPlantDTO.setPagination(pagination);
		
		if(bidPlantCount>0) {
			bidPlantListdto = bidPlantMapper.getBidPlantMyBid(sId,searchKeyPl,searchValuePl,bidPlantDTO);
		}
		int num = (bidPlantDTO.getCurrentPageNo()-1)*5;
		if(bidPlantListdto!=null && bidPlantListdto.size()>0) {
			for(int i=0; i<bidPlantListdto.size();i++) {
				for(int j=0; j<bidPlantListdto.get(i).getBidListDTOList().size(); j++) {
					System.out.println(num);
					bidPlantListdto.get(i).getBidListDTOList().get(j).setNum(++num);
				}
			}
		}
		return bidPlantListdto;
	}
	public BusinessPlantDTO getPlant(String announceCode) {
		return bidPlantMapper.getPlant(announceCode);
	}
}
