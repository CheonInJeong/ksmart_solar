package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PolicyController {
	

	@GetMapping("/policy/policyList")
	public String PolicyList() {
		
		return "/policy/policyList";
	}

}
