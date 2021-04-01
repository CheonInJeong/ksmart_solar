package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.dto.BidMoneyDTO;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradeFailDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.serivce.BidMoneyService;
import com.cafe24.kangk0269.serivce.MemberService;
import com.cafe24.kangk0269.serivce.TradeService;

@Controller
public class ProfitController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TradeService tradeService;

	@Autowired
	private BidMoneyService bidMoneyService;
	
	@GetMapping("/profit/cancel")
	public String Cancel(Model model) {
		List<TradeFailDTO> cancelCmList = tradeService.getFailCommission();
		System.out.println("취소수수료조회 : " + cancelCmList);
		model.addAttribute("cancelCmList", cancelCmList);
		return "/profit/cancel";
	}
	
	@GetMapping("/profit/commission")
	public String Commission() {
		return "/profit/commission";
	}
	
	@PostMapping("/bidMoneyInsertSend")
	public String bidMoneyInsertSend(@RequestParam(name="mId", required=false) String mId
									,@RequestParam(name="bMoDetail", required=false) String bMoDetail
									,@RequestParam(name="bMoAmount", required=false) String bMoAmount
									,@RequestParam(name="bMoType", required=false) String bMoType) {
		System.out.println("거래회원 : " + mId);
		System.out.println("거래유형 : " + bMoType);
		System.out.println("상세내역 : " + bMoDetail);
		System.out.println("금액 : " + bMoAmount);
		
		return "/profit/bidMoneyInsert";
	}
	
	@GetMapping("/bidMoneyInsert")
	public String bidMoneyInsert(Model model) {
		List<MemberDTO> memberList = memberService.getAllMember();
		model.addAttribute("memberList", memberList);
		return "/profit/bidMoneyInsert";
	}
	
	@GetMapping("/profit/balance")
	public String Balance(Model model) {
		List<BidMoneyDTO> bidMoneyList = bidMoneyService.getBidMoneyList();
		System.out.println(bidMoneyList);
		model.addAttribute("bidMoneyList", bidMoneyList);
		return "/profit/balance";
	}
	
	@GetMapping("/profit/calculate")
	public String Calculate() {
		return "/profit/calculate";
	}
	
	@GetMapping("/profit/depositList")
	public String DepositList(Model model) {
		List<TradeDepositOutDTO> depositOutList = tradeService.getDepositOutList();
		System.out.println(depositOutList);
		model.addAttribute("depositOutList", depositOutList);
		return "/profit/depositList";
	}
	
	@GetMapping("/profit/commissionList")
	public String CommissionList(Model model) {
		List<TradePaymentOutDTO> paymentOutList = tradeService.getPaymentOutList();
		System.out.println(paymentOutList);
		model.addAttribute("paymentOutList", paymentOutList);
		return "/profit/commissionList";
	}
}
