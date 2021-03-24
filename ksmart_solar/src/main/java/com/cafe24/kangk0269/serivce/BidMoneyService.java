package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BidMoneyMapper;
import com.cafe24.kangk0269.dto.BidMoneyDTO;

@Service
@Transactional
public class BidMoneyService {

	private final BidMoneyMapper bidMoneyMapper;
	
	@Autowired
	public BidMoneyService(BidMoneyMapper bidMoneyMapper) {
		this.bidMoneyMapper = bidMoneyMapper;
	}
	
	public List<BidMoneyDTO> getBidMoneyList(){
		return bidMoneyMapper.getBidMoneyList();
	}
}
