package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.common.Pagination;
import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
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
	
	public List<BidComponentDTO> getBidComponent(String status) {		
		List<BidComponentDTO> bidComponentList = bidComponentMapper.getBidComponent(status);
		if(bidComponentList!=null) {
			for(int i=0; i<bidComponentList.size();i++) {
				bidComponentList.get(i).setNum(i+1);
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
		return bidComponentList;
	}
	public ComponentDTO getComponent(String CpCode) {
		return bidComponentMapper.getComponent(CpCode);
	}
}
