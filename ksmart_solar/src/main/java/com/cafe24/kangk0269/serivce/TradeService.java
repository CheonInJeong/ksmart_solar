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
import com.cafe24.kangk0269.dto.TradePriorityDTO;

@Service
@Transactional
public class TradeService {

	private final TradeMapper tradeMapper;

	@Autowired
	public TradeService(TradeMapper tradeMapper) {
		this.tradeMapper = tradeMapper;
	}
	
	// 월말정산 리스트 조회
	public List<TradeFailDTO> getCalculateList(int start,int end){
		return tradeMapper.getCalculateList(start, end);
	}
	// 월말정산리스트 수
	public int getCalculateListCnt() {
		return tradeMapper.getCalculateListCnt();
	}
	// 월별 수수료 상세정보
	public List<TradeFailDTO> getCalculateMonth(String lastDate) {
		return tradeMapper.getCalculateMonth(lastDate);
	}
	
	// 전체 대금 수수료 리스트 조회
	public List<TradePaymentOutDTO> getSuccessCommission(int start,int end,String searchKey,String searchValue){
		return tradeMapper.getSuccessCommission(start, end, searchKey, searchValue);
	}
	// 대금수수료 리스트 수
	public int getSuccessCommissionCnt(String searchKey,String searchValue) {
		return tradeMapper.getSuccessCommissionCnt(searchKey, searchValue);
	}
	
	// 전체 취소 수수료 리스트 조회
	public List<TradeFailDTO> getFailCommission(int start,int end,String searchKey,String searchValue){
		return tradeMapper.getFailCommission(start, end, searchKey, searchValue);
	}
	// 취소수수료 리스트 수
	public int getFailCommissionCnt(String searchKey,String searchValue) {
		return tradeMapper.getFailCommissionCnt(searchKey, searchValue);
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
	public List<TradePaymentOutDTO> getPaymentOutList(int start,int end,String searchKey,String searchValue){
		return tradeMapper.getPaymentOutList(start, end, searchKey, searchValue);
	}
	// 거래대금출금신청 리스트 수 조회
	public int getPaymentOutListCnt(String searchKey,String searchValue) {
		return tradeMapper.getPaymentOutListCnt(searchKey, searchValue);
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
	public List<TradeDepositOutDTO> getDepositOutList(int start,int end,String searchKey,String searchValue) {
		return tradeMapper.getDepositOutList(start, end, searchKey, searchValue);
	}
	// 예치금출금신청 리스트 수
	public int getDepositOutListCnt(String searchKey,String searchValue) {
		return tradeMapper.getDepositOutListCnt(searchKey, searchValue);
	}
	
	// 낙찰자 테이블 낙찰코드로 상세조회
	public TradePriorityDTO getPriByPrCode(String prCode) {
		return tradeMapper.getPriByPrCode(prCode);
	}
	// 거래대금 납부 납부코드로 상세조회
	public TradePaymentInDTO getPayInByPayInCode(String payInCode) {
		return tradeMapper.getPayInByPayInCode(payInCode);
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
	//예치금환불 수정
	public String modifyRefund(TradeDepositOutDTO tradeDepositOutDTO) {
		tradeMapper.modifyRefund(tradeDepositOutDTO);
		return tradeMapper.getRefundDepDate(tradeDepositOutDTO.getbCode());
	}
	public TradeDepositOutDTO getRefundInfo(String bCode) {
		return tradeMapper.getRefundInfo(bCode);
	}
	public TradePriorityDTO getPriorityDate(String bCode) {
		return tradeMapper.getPriorityDate(bCode);
	}

	
}
