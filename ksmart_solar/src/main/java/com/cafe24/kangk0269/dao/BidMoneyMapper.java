package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidMoneyDTO;

@Mapper
public interface BidMoneyMapper {

	// 입출금 내역 입력
	public int addBidMoney(BidMoneyDTO money);
	
	// 관리자계좌 입출금내역 조회
	public List<BidMoneyDTO> getBidMoneyList();
}
