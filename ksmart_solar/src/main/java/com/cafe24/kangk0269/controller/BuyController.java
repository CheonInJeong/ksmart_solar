package com.cafe24.kangk0269.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
			System.out.println(bidPlantList+"--------------------------------------------입찰신청한 발전소 목록");
			System.out.println(bidPlantList.get(0).getBidListDTO().getTrTypeName()+"--------------------------------------------입찰신청한 발전소 목록");
			System.out.println(bidPlantList.get(1).getBidListDTO().getTrTypeName()+"--------------------------------------------입찰신청한 발전소 목록");
			System.out.println(bidPlantList.get(1).getBidListDTO().getbCode()+"--------------------------------------------입찰신청한 발전소 목록");
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
	@PostMapping("/buy/refundRequest")
	public String refundRequest() {
		
		return "/buy/refundRequest";
	}
	@GetMapping("/buy/refundRequest")
	public String getrefundRequest() {
		
		return "/buy/refundRequest";
	}
	@PostMapping("/buy/myAnnouncement")
	public String myAnnouncement(String status, String title, Model model) {
		model.addAttribute("status", status);
		model.addAttribute("title", title);
		return "/buy/myAnnouncement";
	}
}
