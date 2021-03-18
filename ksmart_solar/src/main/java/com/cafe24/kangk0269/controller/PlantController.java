package com.cafe24.kangk0269.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlantController {

	@GetMapping("/plant/plantList")
	public String PlantList() {
		
		return "/plant/plantList";
	}
	
	@GetMapping("/plant/componentList")
	public String ComponentList() {
		
		return "/plant/componentList";
	}
	
	@GetMapping("/plant/authorization")
	public String Authorization() {
		
		return "/plant/authorization";
	}
	
}
