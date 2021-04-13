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
import com.cafe24.kangk0269.dto.MoneyCheckDTO;
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
	public String Commission(Model model) {
		List<TradePaymentOutDTO> successCmList = tradeService.getSuccessCommission();
		System.out.println("성공수수료조회 : " + successCmList);
		model.addAttribute("successCmList", successCmList);
		return "/profit/commission";
	}
	
	@PostMapping("/bidMoneyInsertSend")
	public String bidMoneyInsertSend(MoneyCheckDTO moneycheck) {
		System.out.println("입출금 내역 입력 필요요소 :" + moneycheck);
		System.out.println(moneycheck.getCode().substring(0,1));
		if(moneycheck.getCode().substring(0,1).equals('b')) {
			
		}else {
			//bidMoneyService.addBidMoney(moneycheck);
		}
		
		return "/profit/balance";
	}
	
	@GetMapping("/bidMoneyInsert")
	public String bidMoneyInsert(Model model, @RequestParam(name="Code", required=false) String Code) {
		System.out.println("입력할 코드 : " + Code);
		MoneyCheckDTO check = bidMoneyService.getMoneyCheck(Code);
		List<MoneyCheckDTO> moneyCheckList = bidMoneyService.getMoneyCheckList();
		List<BidMoneyDTO> bidMoneyList = bidMoneyService.getBidMoneyList();
		model.addAttribute("check", check);
		model.addAttribute("bidMoneyList", bidMoneyList);
		model.addAttribute("moneyCheckList", moneyCheckList);
		return "/profit/bidMoneyInsert";
	}
	
	@GetMapping("/bidMoneyCheck")
	public String bidMoneyCheck(Model model) {
		List<MoneyCheckDTO> moneyCheckList = bidMoneyService.getMoneyCheckList();
		System.out.println(moneyCheckList);
		List<BidMoneyDTO> bidMoneyList = bidMoneyService.getBidMoneyList();
		model.addAttribute("bidMoneyList", bidMoneyList);
		model.addAttribute("moneyCheckList", moneyCheckList);
		return "/profit/bidMoneyCheck";
	}
	
	@GetMapping("/profit/balance")
	public String Balance(Model model) {
		List<BidMoneyDTO> bidMoneyList = bidMoneyService.getBidMoneyList();
		System.out.println(bidMoneyList);
		model.addAttribute("bidMoneyList", bidMoneyList);
		return "/profit/balance";
	}
	
	@GetMapping("/profit/calculate")
	public String Calculate(Model model) {
		List<TradeFailDTO> calculateList = tradeService.getCalculateList();
		System.out.println("수수료 정산 : " + calculateList);
		model.addAttribute("calculateList", calculateList);
		return "/profit/calculate";
	}
	
	@PostMapping("/withDraw")
	public String WithDraw(@RequestParam(name="Code", required=false) String Code) {
		System.out.println("출금완료 출금신청코드 : " + Code);
		String codeName = null;
		String url = null;
		codeName = Code.substring(0, Code.indexOf('_'));
		if(codeName != null) {
			if(codeName.equals("deout")) {
				String bCode = null;
				TradeDepositOutDTO depositout = tradeService.getDepositOut(Code);
				bCode = depositout.getbCode();
				if(bCode != null) {
					// 예치금 출금 신청 테이블 : 출금여부 = Y, 출금시간 : NOW()
					tradeService.depositWithdraw1(Code);
					// 입찰자 테이블 : 예치금 환불 가능여부 = N, 환불완료여부 = Y
					tradeService.depositWithdraw2(bCode);
				}
				url = "depositList";
			}else if(codeName.equals("payout")) {
				// 거래대금 출금 신청 테이블 : 출금여부 = Y, 출금시간 : NOW()
				tradeService.paymentoutWithdraw(Code);
				url = "commissionList";
			}
		}
		return "redirect:/profit/" + url;
	}
	
	@PostMapping("/accountCheck")
	public String accountCheck(@RequestParam(name="Code", required=false) String Code) {
		System.out.println("계좌확인 출금신청코드 : " + Code);
		String codeName = null;
		codeName = Code.substring(0, Code.indexOf('_'));
		if(codeName != null) {
			if(codeName.equals("deout")) {
				tradeService.depositAccountCheck(Code);
			}else if(codeName.equals("payout")) {
				tradeService.paymentoutAccountCheck(Code);
			}
		}
		
		return "redirect:/profit/withDraw?Code=" + Code;
	}
	
	@GetMapping("/profit/withDraw")
	public String withDraw(Model model, @RequestParam(name="Code", required=false) String Code) {
		System.out.println("출금신청코드 : " + Code);
		if(Code != null) {
			TradeDepositOutDTO depositout = tradeService.getDepositOut(Code);
			TradePaymentOutDTO paymentout = tradeService.getPaymentOut(Code);
			System.out.println(depositout);
			System.out.println(paymentout);
			model.addAttribute("depositout", depositout);
			model.addAttribute("paymentout", paymentout);
		}
		return "/profit/withDraw";
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
