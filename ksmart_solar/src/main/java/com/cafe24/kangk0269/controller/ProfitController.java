package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfitController {

	@GetMapping("/profit/cancel")
	public String Cancel() {
		return "/profit/cancel";
	}
	
	@GetMapping("/profit/commission")
	public String Commission() {
		return "/profit/commission";
	}
	
	@GetMapping("/profit/balance")
	public String Balance() {
		return "/profit/balance";
	}
	
	@GetMapping("/profit/calculate")
	public String Calculate() {
		return "/profit/calculate";
	}
	
	@GetMapping("/profit/depositList")
	public String DepositList() {
		return "/profit/depositList";
	}
	
	@GetMapping("/profit/commissionList")
	public String CommissionList() {
		return "/profit/commissionList";
	}
}
