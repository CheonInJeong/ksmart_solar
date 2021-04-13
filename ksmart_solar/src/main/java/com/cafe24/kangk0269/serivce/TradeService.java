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
	// 거래대금 출금 계좌확인
	public int paymentoutAccountCheck(String Code) {
		return tradeMapper.paymentoutAccountCheck(Code);
	}
	// 거래대금 출금(거래대금 출금신청 테이블 변경)
	public int paymentoutWithdraw(String Code) {
		return tradeMapper.paymentoutWithdraw(Code);
	}
	// 거래대금 출금신청 상세조회
	public TradePaymentOutDTO getPaymentOut(String Code) {
		return tradeMapper.getPaymentOut(Code);
	}
	
	// 거래대금출금신청목록
	public List<TradePaymentOutDTO> getPaymentOutList(){
		return tradeMapper.getPaymentOutList();
	}
	
	// 예치금 출금 계좌확인
	public int depositAccountCheck(String Code) {
		return tradeMapper.depositAccountCheck(Code);
	}
	// 예치금 출금(예치금 출금신청 테이블 변경)
	public int depositWithdraw1(String Code) {
		return tradeMapper.depositWithdraw1(Code);
	}
	// 예치금 출금(입찰자 테이블 변경)
	public int depositWithdraw2(String bCode) {
		return tradeMapper.depositWithdraw2(bCode);
	}
	// 예치금 출금신청 상세조회
	public TradeDepositOutDTO getDepositOut(String Code) {
		return tradeMapper.getDepositOut(Code);
	}
	
	// 예치금출금신청목록
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
	//예치금 환불등록
	public int addRefundRequest(TradeDepositOutDTO tradeDepositOutDTO) {
		return tradeMapper.addRefundRequest(tradeDepositOutDTO);
	}
	public TradeDepositOutDTO getRefundInfo(String bCode) {
		return tradeMapper.getRefundInfo(bCode);
	}
	
}
