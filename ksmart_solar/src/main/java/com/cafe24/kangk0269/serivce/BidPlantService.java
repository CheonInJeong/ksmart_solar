package com.cafe24.kangk0269.serivce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.common.Pagination;
import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dao.BidPlantMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Service
@Transactional
public class BidPlantService {
	private final BidPlantMapper bidPlantMapper;
	
	@Autowired
	public BidPlantService(BidPlantMapper bidPlantMapper) {
		this.bidPlantMapper = bidPlantMapper; 
	}
	
	public List<BidPlantDTO> getBidPlantById(String mId){
		return bidPlantMapper.getBidPlantById(mId);
	}
	
	public List<BidPlantDTO> getBidPlant(String status) {
		List<BidPlantDTO> bidPlantList = bidPlantMapper.getBidPlant(status);
		if(bidPlantList!=null) {
			for(int i=0; i<bidPlantList.size();i++) {
				bidPlantList.get(i).setNum(i+1);
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
