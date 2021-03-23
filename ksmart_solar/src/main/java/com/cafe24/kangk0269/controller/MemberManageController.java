package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.serivce.MemberService;

@Controller
public class MemberManageController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/memberList")
	public String MemberList(Model model) {
		List<MemberDTO> memberList = memberService.getAllMember();
		System.out.println(memberList);
		model.addAttribute("memberList", memberList);
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
