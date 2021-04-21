package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.common.Pagination;
import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;

@Service
@Transactional
public class BidComponentService {
	
	private final BidComponentMapper bidComponentMapper;
	
	@Autowired
	public BidComponentService(BidComponentMapper bidComponentMapper) {
		this.bidComponentMapper = bidComponentMapper; 
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("BidComponentService bean 등록");
		System.out.println("=========================================================");
	}
	
	//공고신청 부품 반려처리
	public void bidComponentReturn(String bCpCode, String bCpRejectReason) {
		bidComponentMapper.bidComponentReturn(bCpCode, bCpRejectReason);
	}
	
	//공고신청 부품 승인처리
	public void bidComponentAdmit(String bCpCode) {
		bidComponentMapper.bidComponentAdmit(bCpCode);
	}
	
	// 공고신청 부품 상세보기
	public BidComponentDTO getNotice(String bCpCode) {
		return  bidComponentMapper.getNotice(bCpCode);
	}
	
	// 전체 공고신청 부품 조회
	public List<BidComponentDTO> getComponentNoticeAdmitList(int start,int end,String searchKey,String searchValue){
		if(searchKey != null && searchKey != "") {
			if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}
		}
		return bidComponentMapper.getComponentNoticeAdmitList(start, end, searchKey, searchValue);
	}
	// 공고신청 부품 리스트 수
	public int getComponentNoticeAdmitListCnt(String searchKey,String searchValue) {
		if(searchKey != null && searchKey != "") {
			if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}
		}
		return bidComponentMapper.getComponentNoticeAdmitListCnt(searchKey, searchValue);
	}	
	
	
	public List<BidComponentDTO> getBidComponent(String status,String searchKeyCp,String searchValueCp,BidComponentDTO bidComponentDTO) {		
		if(searchKeyCp!=null && searchKeyCp.equals("null")) {
			System.out.println("문자열 unll");
			searchKeyCp = null;
		}
		if(searchValueCp!=null && searchValueCp.equals("null")) {
			System.out.println("문자열 unll");
			searchValueCp = null;
		}
		if(searchKeyCp!=null) {
			if("bCpTitle".equals(searchKeyCp)) {
				searchKeyCp="b_cp_title";
			}else if("mId".equals(searchKeyCp)) {
				searchKeyCp="m_id";
			}
			else {
				searchKeyCp=null;
			}
		}
		
		System.out.println(searchKeyCp+"---------------------------searchKeyCp");
		System.out.println(searchValueCp+"---------------------------searchValueCp");
		List<BidComponentDTO> bidComponentList = null;
		
		int bidComponentCount = bidComponentMapper.getBidComponentListCount(status,searchKeyCp,searchValueCp);
		System.out.println(bidComponentCount);
		Pagination pagination = new Pagination(bidComponentDTO);
		
		pagination.setTotalRecordCount(bidComponentCount);
		
		bidComponentDTO.setPagination(pagination);
		
		if(bidComponentCount>0) {
			bidComponentList = bidComponentMapper.getBidComponent(status,searchKeyCp,searchValueCp,bidComponentDTO);
		}
		int num = (bidComponentDTO.getCurrentPageNo()-1)*5;
		if(bidComponentList!=null) {
			for(int i=0; i<bidComponentList.size();i++) {
				bidComponentList.get(i).setNum(++num);
			}
		}
		System.out.println(bidComponentList);
		return bidComponentList;
	}
	public BidComponentDTO getBidComponentByInfo(String announceCode) {		
		BidComponentDTO bidComponentDTO = bidComponentMapper.getBidComponentByInfo(announceCode);
		System.out.println(bidComponentDTO);
		return bidComponentDTO;
	}
	public List<BidComponentDTO> getBidComponentMyBid(String mId,String searchKeyCp,String searchValueCp,BidComponentDTO bidComponentDTO) {
		if(searchKeyCp!=null && searchKeyCp.equals("null")) {
			System.out.println("문자열 unll");
			searchKeyCp = null;
		}
		if(searchValueCp!=null && searchValueCp.equals("null")) {
			System.out.println("문자열 unll");
			searchValueCp = null;
		}
		if(searchKeyCp!=null) {
			if("bTitle".equals(searchKeyCp)) {
				searchKeyCp="li.b_title";
			}else if("bCpStatus".equals(searchKeyCp)) {
				searchKeyCp="com.b_cp_status";
			}else if("trTypeName".equals(searchKeyCp)) {
				searchKeyCp="li.tr_type_name";
			}else if("mId".equals(searchKeyCp)) {
				searchKeyCp="com.m_id";
			}
			else {
				searchKeyCp=null;
			}
		}
		
		System.out.println(searchKeyCp+"---------------------------searchKeyCp");
		System.out.println(searchValueCp+"---------------------------searchValueCp");
		List<BidComponentDTO> bidComponentList = null;
		
		int bidComponentCount = bidComponentMapper.getBidComponentCount(mId,searchKeyCp,searchValueCp,bidComponentDTO);
		System.out.println(bidComponentCount);
		Pagination pagination = new Pagination(bidComponentDTO);
		
		pagination.setTotalRecordCount(bidComponentCount);
		
		bidComponentDTO.setPagination(pagination);
		
		
		if(bidComponentCount>0) {
			bidComponentList = bidComponentMapper.getBidComponentMyBid(mId,searchKeyCp,searchValueCp,bidComponentDTO);
		}
		int num = (bidComponentDTO.getCurrentPageNo()-1)*5;
		if(bidComponentList!=null && bidComponentList.size()>0) {
			for(int i=0; i<bidComponentList.size();i++) {
				for(int j=0; j<bidComponentList.get(i).getBidListDTOList().size(); j++) {
					System.out.println(num);
					bidComponentList.get(i).getBidListDTOList().get(j).setNum(++num);
				}
			}
		}
		return bidComponentList;
	}
	public ComponentDTO getComponent(String CpCode) {
		return bidComponentMapper.getComponent(CpCode);
	}
}
