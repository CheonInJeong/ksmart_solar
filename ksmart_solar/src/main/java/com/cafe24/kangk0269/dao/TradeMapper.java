package com.cafe24.kangk0269.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradeFailDTO;
import com.cafe24.kangk0269.dto.TradePaymentInDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;

@Mapper
public interface TradeMapper {

	// 월말정산리스트
	public List<TradeFailDTO> getCalculateList();
	// 월별 수수료 상세정보
	public List<TradeFailDTO> getCalculateMonth(String lastDate);
	
	// 대금수수료목록
	public List<TradePaymentOutDTO> getSuccessCommission();
	
	// 취소수수료목록
	public List<TradeFailDTO> getFailCommission();

	// 거래대금 출금 계좌확인
	public int paymentoutAccountCheck(String Code);
	// 거래대금 출금(거래대금 출금신청 테이블 변경)
	public int paymentoutWithdraw(String Code);
	// 거래대금 출금
	public int paymentWithdraw(String Code);
	// 거래대금 출금신청 상세조회
	public TradePaymentOutDTO getPaymentOut(String Code);
	// 거래대금출금신청목록
	public List<TradePaymentOutDTO> getPaymentOutList();
	
	// 예치금 출금 계좌확인
	public int depositAccountCheck(String Code);
	// 예치금 출금(예치금 출금신청 테이블 변경)
	public int depositWithdraw1(String Code);
	// 예치금 출금(입찰자 테이블 변경)
	public int depositWithdraw2(String bCode);
	// 예치금 출금신청 상세조회
	public TradeDepositOutDTO getDepositOut(String Code);
	// 예치금출금신청목록
	public List<TradeDepositOutDTO> getDepositOutList();
	
	// 낙찰자 테이블 낙찰코드로 상세조회
	public TradePriorityDTO getPriByPrCode(String prCode);
	// 거래대금 납부 납부코드로 상세조회
	public TradePaymentInDTO getPayInByPayInCode(String payInCode);
	
	//거래대금납부목록(본인)
	public TradePaymentInDTO getTradePaymentIn(String bCode);
	//대금신청 정보 갱신
	public int modifyTradePaymentIn(TradePaymentInDTO paymentInDTO);
	//예치금 환불 등록
	public int addRefundRequest(TradeDepositOutDTO tradeDepositOutDTO);
	public TradeDepositOutDTO getRefundInfo(String bCode);
	//공고가 계약중으로 바뀔때 1순위 낙찰자를 입력
	public int addTradePriority(Map<String,Object> List);
}
