package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	@GetMapping("/")
	public String main() {
		return "main";
	}
	@GetMapping("/components/todoList")
	public String test() {
		return "components/todoList";
	}
	@GetMapping("/login")
	public String login() {
		return "member/memberLogin";
	}
	@GetMapping("/signup")
	public String signup() {
		return "member/memberSignUp";
	}
	
	
	
}
