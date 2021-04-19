package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidMoneyDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.MoneyCheckDTO;
import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradePaymentInDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;

@Mapper
public interface BidMoneyMapper {

	// 확인후 입출금내역 입력
	public int addBidMoney(MoneyCheckDTO moneycheck);
	
	// 입찰자 테이블 업데이트(입출금코드,예치금납부여부,예치금확인일,진행상태코드,진행상태,상태변경일) - 예치금 입금
	public int modifyBidDepositIn(BidListDTO bidlist);
	// 발전소매각공고신청 테이블 업데이트(입찰자 수) - 예치금 입금
	public int modifyPlantDepositIn(BidPlantDTO bidplant);
	// 부품매각공고신청 테이블 업데이트(입찰자 수) - 예치금 입금
	public int modifyComDepositIn(BidComponentDTO bidcomponent);
	
	// 거래대금납부 테이블 업데이트(입출금코드,입금여부,입금시간,진행상황) - 대금 입금
	public int modifyPayIn(TradePaymentInDTO paymentin);
	// 낙찰자우선순위 테이블 업데이트(진행상태코드,진행상태,상태변경시간) - 대금 입금
	public int modifyPriPayIn(TradePaymentInDTO paymentin);
	// 입찰자 테이블 업데이트(진행상태코드,진행상태,상태변경일) - 대금 입금
	public int modifyBidPayIn(TradePaymentInDTO paymentin);
	// 발전소매각공고신청 테이블 업데이트(진행상태, 공고진행상태코드) - 대금 입금
	public int modifyPlantPayIn(BidPlantDTO bidplant);
	// 부품매각공고신청 테이블 업데이트(진행상태, 공고진행상태코드) - 대금 입금
	public int modifyComPayIn(BidComponentDTO bidcomponent);
	
	// 예치금출금신청 테이블 업데이트(입출금코드) - 예치금 환불
	public int modifyDepositOut(TradeDepositOutDTO depositout);
	// 입찰자 테이블 업데이트(예치금환불가능여부,환불완료여부) - 예치금 환불
	public int modifyBidDepositOut(TradeDepositOutDTO depositout);
	
	// 거래대금출금신청 테이블 업데이트(입출금코드) - 대금 출금
	public int modifyPayOut(TradePaymentOutDTO paymentout);
	
	// 확인 입출금 개별(미완료)
	public MoneyCheckDTO getMoneyCheck(String Code);
	
	// 확인 입출금 목록(미완료, 완료)
	public List<MoneyCheckDTO> getMoneyCheckList();
	
	// 관리자계좌 입출금내역 조회
	public List<BidMoneyDTO> getBidMoneyList();
}
