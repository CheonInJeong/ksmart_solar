package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradeFailDTO;
import com.cafe24.kangk0269.dto.TradePaymentInDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;

@Mapper
public interface TradeMapper {

	// 취소수수료목록
	public List<TradeFailDTO> getFailCommission();
	
	// 거래대금출금신청목록
	public List<TradePaymentOutDTO> getPaymentOutList();
	
	// 예치금출금신청목록
	public List<TradeDepositOutDTO> getDepositOutList();
	
	//거래대금납부목록(본인)
	public TradePaymentInDTO getTradePaymentIn(String bCode);
	//대금신청 정보 갱신
	public int modifyTradePaymentIn(TradePaymentInDTO paymentInDTO);
}
