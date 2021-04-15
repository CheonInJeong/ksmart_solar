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
