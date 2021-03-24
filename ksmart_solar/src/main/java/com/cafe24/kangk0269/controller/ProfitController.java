package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.kangk0269.dto.BidMoneyDTO;
import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.serivce.BidMoneyService;
import com.cafe24.kangk0269.serivce.TradeService;

@Controller
public class ProfitController {

	@Autowired
	private TradeService tradeService;

	@Autowired
	private BidMoneyService bidMoneyService;
	
	@GetMapping("/profit/cancel")
	public String Cancel() {
		return "/profit/cancel";
	}
	
	@GetMapping("/profit/commission")
	public String Commission() {
		return "/profit/commission";
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
