package com.cafe24.kangk0269.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.serivce.BidComponentService;
import com.cafe24.kangk0269.serivce.BidPlantService;

@Controller
public class BuyController {
	
	private final BidComponentService bidComponentService;
	private final BidPlantService bidPlantService;
	
	@Autowired
	public BuyController(BidComponentService bidComponentService,
							BidPlantService bidPlantService) {
		this.bidComponentService = bidComponentService; 
		this.bidPlantService = bidPlantService; 
	}
	
	@GetMapping("/buy/myHistory")
	public String MyHistory(HttpSession session, Model model) {
		System.out.println(session.getAttribute("SID"));
		String sId = (String) session.getAttribute("SID");
		if(sId != null) {
			List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponentMyBid(sId);
			List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlantMyBid(sId);
			if(bidPlantList!=null) {				
				model.addAttribute("bidPlantList", bidPlantList);
			}
			if(bidComponentList != null) {
				model.addAttribute("bidComponentList", bidComponentList);
			}
		}
		return "/buy/myHistory";
	}
	
	@GetMapping("/buy/applyRefund")
	public String ApplyRefund(HttpSession session, Model model) {
		System.out.println(session.getAttribute("SID"));
		String sId = (String) session.getAttribute("SID");
		if(sId != null) {
			List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponentMyBid(sId);
			List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlantMyBid(sId);
			if(bidPlantList!=null) {				
				model.addAttribute("bidPlantList", bidPlantList);
			}
			if(bidComponentList != null) {
				model.addAttribute("bidComponentList", bidComponentList);
			}
		}
		return "/buy/applyRefund";
	}
	
	@GetMapping("/buy/depositList")
	public String DepositList() {
		
		return "/buy/depositList";
	}
	
	@GetMapping("/buy/qna")
	public String Qna() {
		
		return "/buy/qna";
	}
}
