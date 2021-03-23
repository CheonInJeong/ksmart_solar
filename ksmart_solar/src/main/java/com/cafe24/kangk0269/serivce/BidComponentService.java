package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;

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
		System.out.println(bidComponentList);
		return bidComponentList;
	}
	
}
