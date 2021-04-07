package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidMoneyDTO;
import com.cafe24.kangk0269.dto.MoneyCheckDTO;

@Mapper
public interface BidMoneyMapper {

	// 확인후 입출금내역 입력
	public int addBidMoney(String Code,String Id,long money,String iOut,String inoutDate);
	
	// 확인 입출금 개별(미완료)
	public MoneyCheckDTO getMoneyCheck(String Code);
	
	// 확인 입출금 목록(미완료, 완료)
	public List<MoneyCheckDTO> getMoneyCheckList();
	
	// 관리자계좌 입출금내역 조회
	public List<BidMoneyDTO> getBidMoneyList();
}
