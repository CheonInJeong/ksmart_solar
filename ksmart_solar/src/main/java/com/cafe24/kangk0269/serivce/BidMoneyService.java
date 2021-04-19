package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BidMoneyMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidMoneyDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.MoneyCheckDTO;
import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradePaymentInDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;

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
	
	// 입찰자 테이블 업데이트(입출금코드,예치금납부여부,예치금확인일,진행상태코드,진행상태,상태변경일) - 예치금 입금
	public int modifyBidDepositIn(BidListDTO bidlist) {
		return bidMoneyMapper.modifyBidDepositIn(bidlist);
	}
	// 발전소매각공고신청 테이블 업데이트(입찰자 수) - 예치금 입금
	public int modifyPlantDepositIn(BidPlantDTO bidplant) {
		return bidMoneyMapper.modifyPlantDepositIn(bidplant);
	}
	// 부품매각공고신청 테이블 업데이트(입찰자 수) - 예치금 입금
	public int modifyComDepositIn(BidComponentDTO bidcomponent) {
		return bidMoneyMapper.modifyComDepositIn(bidcomponent);
	}
	
	// 거래대금납부 테이블 업데이트(입출금코드,입금여부,입금시간,진행상황) - 대금 입금
	public int modifyPayIn(TradePaymentInDTO paymentin) {
		return bidMoneyMapper.modifyPayIn(paymentin);
	}
	// 낙찰자우선순위 테이블 업데이트(진행상태코드,진행상태,상태변경시간) - 대금 입금
	public int modifyPriPayIn(TradePaymentInDTO paymentin) {
		return bidMoneyMapper.modifyPriPayIn(paymentin);
	}
	// 입찰자 테이블 업데이트(진행상태코드,진행상태,상태변경일) - 대금 입금
	public int modifyBidPayIn(TradePaymentInDTO paymentin) {
		return bidMoneyMapper.modifyBidPayIn(paymentin);
	}
	// 발전소매각공고신청 테이블 업데이트(진행상태, 공고진행상태코드) - 대금 입금
	public int modifyPlantPayIn(BidPlantDTO bidplant) {
		return bidMoneyMapper.modifyPlantPayIn(bidplant);
	}
	// 부품매각공고신청 테이블 업데이트(진행상태, 공고진행상태코드) - 대금 입금
	public int modifyComPayIn(BidComponentDTO bidcomponent) {
		return bidMoneyMapper.modifyComPayIn(bidcomponent);
	}
	// 입금자가 1순위일 경우 2순위는 ?: 입찰자 테이블 업데이트(진행상태코드,진행상태,상태변경일,예치금환불가능여부) - 대금입금
	public int modifyBidPayInPri12(String announcedCode) {
		return bidMoneyMapper.modifyBidPayInPri12(announcedCode);
	}
	// 입금자가 1순위일 경우 그외 순위? : 입찰자 테이블 업데이트(예치금환불가능여부) - 대금입금
	public int modifyBidPayInPri1ex(String announcedCode) {
		return bidMoneyMapper.modifyBidPayInPri1ex(announcedCode);
	}
	// 입금자가 2순위일 경우 1순위는? : 입찰자 테이블 업데이트(예치금환불가능여부) - 대금입금
	public int modifyBidPayInPri21(String announcedCode) {
		return bidMoneyMapper.modifyBidPayInPri21(announcedCode);
	}
	// 입금자가 2순위일 경우 그외 순위? : 입찰자 테이블 업데이트(예치금환불가능여부) - 대금입금
	public int modifyBidPayInPri2ex(String announcedCode) {
		return bidMoneyMapper.modifyBidPayInPri2ex(announcedCode);
	}
	
	// 예치금출금신청 테이블 업데이트(입출금코드) - 예치금 환불
	public int modifyDepositOut(TradeDepositOutDTO depositout) {
		return bidMoneyMapper.modifyDepositOut(depositout);
	}
	// 입찰자 테이블 업데이트(예치금환불가능여부,환불완료여부) - 예치금 환불
	public int modifyBidDepositOut(TradeDepositOutDTO depositout) {
		return bidMoneyMapper.modifyBidDepositOut(depositout);
	}
	
	// 거래대금출금신청 테이블 업데이트(입출금코드) - 대금 출금
	public int modifyPayOut(TradePaymentOutDTO paymentout) {
		return bidMoneyMapper.modifyPayOut(paymentout);
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
