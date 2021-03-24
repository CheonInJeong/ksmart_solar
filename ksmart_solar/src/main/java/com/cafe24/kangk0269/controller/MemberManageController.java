package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberManageController {

	@GetMapping("/member/memberList")
	public String MemberList() {
		
		return "/member/memberList";
	}
	
	@GetMapping("/member/memberLogin")
	public String MemberLogin() {
		return "/member/memberLogin";
	}
	
	@GetMapping("/member/memberWithdrawList")
	public String MemberWithdrawList() {
		
		return "/member/memberWithdrawList";
	}
	
	@GetMapping("/member/businessList")
	public String BusinessList() {
		
		return "/member/businessList";
	}
	
	@GetMapping("/member/plantList")
	public String PlantList() {
		
		return "/member/plantList";
	}
	
	@GetMapping("/member/account")
	public String Account() {
		
		return "/member/account";
	}
		
}
