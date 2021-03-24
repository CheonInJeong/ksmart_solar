package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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
