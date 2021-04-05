package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.TradeMapper;
import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradeFailDTO;
import com.cafe24.kangk0269.dto.TradePaymentInDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;

@Service
@Transactional
public class TradeService {

	private final TradeMapper tradeMapper;

	@Autowired
	public TradeService(TradeMapper tradeMapper) {
		this.tradeMapper = tradeMapper;
	}
	
	public List<TradeFailDTO> getCalculateList(){
		return tradeMapper.getCalculateList();
	}
	
	public List<TradePaymentOutDTO> getSuccessCommission(){
		return tradeMapper.getSuccessCommission();
	}
	
	public List<TradeFailDTO> getFailCommission(){
		return tradeMapper.getFailCommission();
	}

	public List<TradePaymentOutDTO> getPaymentOutList(){
		return tradeMapper.getPaymentOutList();
	}
	
	public List<TradeDepositOutDTO> getDepositOutList() {

		return tradeMapper.getDepositOutList();
	}
	//대금납부 정보 
	public TradePaymentInDTO getTradePaymentIn(String bCode) {
		return tradeMapper.getTradePaymentIn(bCode);
	}
	public int modifyTradePaymentIn(TradePaymentInDTO paymentInDTO) {
		return tradeMapper.modifyTradePaymentIn(paymentInDTO);
	}
	
}
