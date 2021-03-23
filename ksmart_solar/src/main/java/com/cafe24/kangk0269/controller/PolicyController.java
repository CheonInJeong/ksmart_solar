package com.cafe24.kangk0269.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.kangk0269.dto.FileDTO;

@Controller
public class PolicyController {
	
	
	
	@GetMapping("/policy/policyList")
	public String PolicyList() {
		
		return "/policy/policyList";
	}
	
	@GetMapping("/policy/document")
	public String Document() {
		
		return "/policy/document";
	}

}
