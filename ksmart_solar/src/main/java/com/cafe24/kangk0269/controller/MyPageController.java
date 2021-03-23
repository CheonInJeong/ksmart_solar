package com.cafe24.kangk0269.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.kangk0269.dto.MemberAccountDTO;


@Controller
public class MyPageController {

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
	
	
	@GetMapping("/mypage/withdraw")
	public String Withdraw() {
		return "/mypage/withdraw";
	}
}
