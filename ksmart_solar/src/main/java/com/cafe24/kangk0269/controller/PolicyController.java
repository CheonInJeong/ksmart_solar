package com.cafe24.kangk0269.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.serivce.FileService;

@Controller
public class PolicyController {
	
	private final FileService fileService;
	
	@Autowired
	public PolicyController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@PostMapping("/policy/addFile")
	//@RequestParam을 통해서 document.html에서 넘겨주는 데이터를 받는다.
	public String addFile(FileDTO fileDto, MultipartHttpServletRequest multipartHttpServletRequest) {
		try {
			fileService.addFile(fileDto, multipartHttpServletRequest);
			return "redirect:/";

		}catch(Exception e) {
			e.printStackTrace();
		}
		return "/policy/document";
	}
	@GetMapping("/policy/policyList")
	public String PolicyList() {
		
		return "/policy/policyList";
	}
	
	@GetMapping("/policy/document")
	public String Document() {
		
		return "/policy/document";
	}

}
