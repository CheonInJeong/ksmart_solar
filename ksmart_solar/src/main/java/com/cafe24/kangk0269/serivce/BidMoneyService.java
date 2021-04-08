package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BidMoneyMapper;
import com.cafe24.kangk0269.dto.BidMoneyDTO;
import com.cafe24.kangk0269.dto.MoneyCheckDTO;

@Service
@Transactional
public class BidMoneyService {

	private final BidMoneyMapper bidMoneyMapper;
	
	@Autowired
	public BidMoneyService(BidMoneyMapper bidMoneyMapper) {
		this.bidMoneyMapper = bidMoneyMapper;
	}
	
	// 확인후 입출금내역 입력
	public int addBidMoney(MoneyCheckDTO moneycheck) {
		return bidMoneyMapper.addBidMoney(moneycheck);
	}
	
	// 확인 입출금 개별(미완료)
	public MoneyCheckDTO getMoneyCheck(String Code) {
		return bidMoneyMapper.getMoneyCheck(Code);
	}
	
	// 확인 입출금 목록(미완료, 완료)
	public List<MoneyCheckDTO> getMoneyCheckList(){
		return bidMoneyMapper.getMoneyCheckList();
	}
	
	public List<BidMoneyDTO> getBidMoneyList(){
		return bidMoneyMapper.getBidMoneyList();
	}
}
