package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.serivce.BidComponentService;
import com.cafe24.kangk0269.serivce.BidPlantService;

@Controller
public class NoticeController {

	private final BidComponentService bidComponentService;
	private final BidPlantService bidPlantService;
	
	@Autowired
	public NoticeController(BidComponentService bidComponentService,
							BidPlantService bidPlantService) {
		this.bidComponentService = bidComponentService; 
		this.bidPlantService = bidPlantService; 
	}
	
	@GetMapping("/notice/noticeList")
	public String NoticeList(Model model) {
		List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponent();
		List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlant();
		model.addAttribute("bidComponentList", bidComponentList);
		model.addAttribute("bidPlantList", bidPlantList);
		return "/notice/noticeList";
	}
	
	@GetMapping("/notice/history")
	public String History() {
		
		return "/notice/history";
	}
}
