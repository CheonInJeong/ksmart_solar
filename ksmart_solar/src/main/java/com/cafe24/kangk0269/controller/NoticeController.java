package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

	@GetMapping("/notice/noticeList")
	public String NoticeList() {
		
		return "/notice/noticeList";
	}
	
	@GetMapping("/notice/history")
	public String History() {
		
		return "/notice/history";
	}
}
