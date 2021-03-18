package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelpController {

	@GetMapping("/help/notice")
	public String Notice() {
		
		return "/help/notice";
	}
	
	@GetMapping("/help/qna")
	public String Qna() {
		
		return "/help/qna";
	}
}
