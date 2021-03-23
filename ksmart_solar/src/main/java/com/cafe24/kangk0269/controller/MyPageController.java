package com.cafe24.kangk0269.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	
	//개인 계좌조회
	@GetMapping("/mypage/withdraw")
	public String Withdraw(Model model, @RequestParam(name = "memberId", required = false) String memberId) {
		System.out.println("================================================");
		System.out.println("화면에서 입력받은 값->" + memberId);
		System.out.println("================================================");
		
		List<MemberAccountDTO> memberAccountDTOList = accountService.getByIdMemberAccount(memberId);
		
		System.out.println("개인계좌조회->" + memberAccountDTOList );
		model.addAttribute("memberAccountDTOList", memberAccountDTOList);
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
