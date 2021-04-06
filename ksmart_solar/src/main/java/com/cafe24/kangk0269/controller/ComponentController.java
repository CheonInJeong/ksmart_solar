package com.cafe24.kangk0269.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.kangk0269.serivce.ComponentService;

@Controller
public class ComponentController {
	
	@Autowired
	private ComponentService componentService;
	
	@GetMapping("/plant/componentList")
	public String ComponentList(Model model ,HttpSession session) {
		componentService.getComponentListById(model, session);
		return "/plant/componentList";
	}
	@GetMapping("/plant/componentList/addComponent")
	public String addComponent(Model model ,HttpSession session) {
		String SLEVEL = (String) session.getAttribute("SLEVEL");
		if("태양광사업자".equals(SLEVEL) || "관리자".equals(SLEVEL)) {
			componentService.addComponent(model, session);
			return "/plant/addComponent";
		}
		return "main";
	}
	
	  
}
