package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dao.BidPlantMapper;
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
	public List<BidPlantDTO> getBidPlantMyBid(String sId) {
		List<BidPlantDTO> bidPlantList = bidPlantMapper.getBidPlantMyBid(sId);
		if(bidPlantList!=null) {
			for(int i=0; i<bidPlantList.size();i++) {
				bidPlantList.get(i).setNum(i+1);
			}
		}
		return bidPlantList;
	}
	public BusinessPlantDTO getPlant(String announceCode) {
		return bidPlantMapper.getPlant(announceCode);
	}
}
