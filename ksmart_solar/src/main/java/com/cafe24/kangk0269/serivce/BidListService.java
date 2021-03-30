package com.cafe24.kangk0269.serivce;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dao.BidListMapper;
import com.cafe24.kangk0269.dao.BidPlantMapper;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;

@Service
@Transactional
public class BidListService {
	private final BidListMapper bidListMapper;
	
	@Autowired
	public BidListService(BidListMapper bidListMapper) {
		this.bidListMapper = bidListMapper; 
	}
	
	public double getDepositRate() {
		return bidListMapper.getDepositRate();
	}
	public void addbidList(BidListDTO bidListDTO) {
		System.out.println("확인");
		bidListMapper.addbidList(bidListDTO);
	}
	public int getBidListCount(String announceCode, String id) {
		return bidListMapper.getBidListCount(announceCode, id);
	}
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

}
