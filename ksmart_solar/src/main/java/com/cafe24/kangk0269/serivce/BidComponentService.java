package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<BidComponentDTO> getBidComponentMyBid(String sId) {
		List<BidComponentDTO> bidComponentList = bidComponentMapper.getBidComponentMyBid(sId);
		if(bidComponentList!=null) {
			System.out.println("확인--------------------------");
			for(int i=0; i<bidComponentList.size();i++) {
				bidComponentList.get(i).setNum(i+1);
			}
		}
		return bidComponentList;
	}
	public ComponentDTO getComponent(String CpCode) {
		return bidComponentMapper.getComponent(CpCode);
	}
}
