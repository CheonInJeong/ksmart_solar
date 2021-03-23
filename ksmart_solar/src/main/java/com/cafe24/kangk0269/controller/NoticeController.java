package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponent("진행");
		List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlant("진행");
		if(bidComponentList!=null) {
			System.out.println(bidComponentList);
			model.addAttribute("bidComponentList", bidComponentList);
		}
		if(bidPlantList!=null) {			
			System.out.println(bidPlantList);
			model.addAttribute("bidPlantList", bidPlantList);
		}
		return "/notice/noticeList";
	}
	
	@GetMapping("/notice/history")
	public String History(Model model) {
		List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponent("종료");
		List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlant("종료");
		if(bidComponentList!=null) {
			System.out.println(bidComponentList);
			model.addAttribute("bidComponentList", bidComponentList);
		}
		if(bidPlantList!=null) {			
			System.out.println(bidPlantList);
			model.addAttribute("bidPlantList", bidPlantList);
		}
		return "/notice/history";
	}
	@PostMapping("/notice/announcement")
	public String Announcement(String announceTitle, String announceType) {
		System.out.println(announceTitle);
		System.out.println(announceType);
		if(announceType!=null && announceType.equals("발전소")) {
			
		}
		if(announceType!=null && announceType.equals("부품")) {
			
		}
		return "/notice/announcement";
	}
}
