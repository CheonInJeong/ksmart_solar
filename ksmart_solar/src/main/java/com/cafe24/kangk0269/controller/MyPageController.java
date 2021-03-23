package com.cafe24.kangk0269.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.serivce.AccountService;




@Controller
public class MyPageController {
	
	private final AccountService accountService;
	
	@Autowired
	public MyPageController(AccountService accountService) {
		this.accountService = accountService; 
	}
	
	//개인 계좌수정
	@GetMapping("/modifyAccount")
	public String modifyAccount() {
		return null;
		
	}
	
	//개인 계좌조회
	@GetMapping("/mypage/myAccount")
	public String getAccountListById(Model model, HttpSession session) {
		String login_id = (String)session.getAttribute("SID");
		System.out.println("================================================");
		System.out.println("로그인 아이디->" + login_id);
		System.out.println("================================================");
		
		List<MemberAccountDTO> memberAccountDTOList = accountService.getAccountListById(login_id);
		
		System.out.println("개인계좌조회->" + memberAccountDTOList);
		model.addAttribute("memberAccountDTOList", memberAccountDTOList);
		return "/mypage/myAccount";
	}
	
	@GetMapping("/mypage/withdraw")
	public String Withdraw() {
		return "/mypage/withdraw";
	}
	

	@GetMapping("/mypage/myInfo")
	public String MyInfo() {
		
		return "/mypage/myInfo";
	}
	
	@GetMapping("/mypage/business")
	public String Business() {
		
		return "/mypage/business";
	}
	
	@GetMapping("/mypage/wishlist")
	public String Wishlist() {
		
		return "/mypage/wishlist";
	}
	
	
	
}
