package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SellController {

	@GetMapping("/sell/apply")
	public String Apply() {
		
		return "/sell/apply";
	}
	@GetMapping("/sell/myHistory")
	public String MyHistory() {
		
		return "/sell/myHistory";
	}
	@GetMapping("/sell/mySell")
	public String MySell() {
		
		return "/sell/mySell";
	}
	@GetMapping("/sell/applyPayment")
	public String ApplyPayment() {
		
		return "/sell/applyPayment";
	}
	@GetMapping("/sell/paymentList")
	public String PaymentList() {
		
		return "/sell/paymentList";
	}
	@GetMapping("/sell/qna")
	public String Qna() {
		
		return "/sell/qna";
	}
}
