package com.cafe24.kangk0269.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyController {

	@GetMapping("/buy/myHistory")
	public String MyHistory(HttpSession session) {
		System.out.println(session.getAttribute("SID"));
		String sId = (String) session.getAttribute("SID");
		
		return "/buy/myHistory";
	}
	
	@GetMapping("/buy/applyRefund")
	public String ApplyRefund() {
		
		return "/buy/applyRefund";
	}
	
	@GetMapping("/buy/depositList")
	public String DepositList() {
		
		return "/buy/depositList";
	}
	
	@GetMapping("/buy/qna")
	public String Qna() {
		
		return "/buy/qna";
	}
}
