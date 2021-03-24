package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;

@Mapper
public interface TradeMapper {

	// 거래대금출금신청목록
	public List<TradePaymentOutDTO> getPaymentOutList();
	
	// 예치금출금신청목록
	public List<TradeDepositOutDTO> getDepositOutList();
}
