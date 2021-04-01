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
	//내가 입찰한 공고
	@GetMapping("/buy/myHistory")
	public String MyHistory(HttpSession session, Model model) {
		System.out.println(session.getAttribute("SID"));
		String sId = (String) session.getAttribute("SID");
		if(sId != null) {
			List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponentMyBid(sId);
			List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlantMyBid(sId);
			bidPlantList.get(1).setNum(bidPlantList.get(0).getBidListDTOList().size());
			if(bidComponentList!=null && bidComponentList.size()>1) {
				for(int i=0; i<bidComponentList.size();i++) {
					if(i!=0) {
						bidComponentList.get(i).setNum(bidComponentList.get(i-1).getBidListDTOList().size()+bidComponentList.get(i-1).getNum());
					}
				}
			}
			if(bidPlantList!=null && bidPlantList.size()>1) {
				for(int i=0; i<bidPlantList.size();i++) {
					if(i!=0) {
						bidPlantList.get(i).setNum(bidPlantList.get(i-1).getBidListDTOList().size()+bidPlantList.get(i-1).getNum());
					}
				}
			}
			if(bidPlantList!=null) {				
				model.addAttribute("bidPlantList", bidPlantList);
			}
			if(bidComponentList != null) {
				model.addAttribute("bidComponentList", bidComponentList);
			}
		}
		return "/buy/myHistory";
	}
	//예치금 환불 신청
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
