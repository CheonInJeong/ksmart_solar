package com.cafe24.kangk0269.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.serivce.AccountService;
import com.cafe24.kangk0269.serivce.BidComponentService;
import com.cafe24.kangk0269.serivce.BidListService;
import com.cafe24.kangk0269.serivce.BidPlantService;
import com.cafe24.kangk0269.serivce.MemberService;

@Controller
public class NoticeController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private AccountService accountService;
	private final BidComponentService bidComponentService;
	private final BidPlantService bidPlantService;
	private final BidListService bidListService;
	
	@Autowired
	public NoticeController(BidComponentService bidComponentService,
							BidPlantService bidPlantService,
							BidListService bidListService) {
		this.bidComponentService = bidComponentService; 
		this.bidPlantService = bidPlantService; 
		this.bidListService = bidListService; 
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
	public String Announcement(String announceTitle, String announceType, Model model) {
		System.out.println(announceTitle);
		System.out.println(announceType);
		if(announceType!=null && announceType.equals("발전소")) {
			BidPlantDTO bidPlantdto = bidPlantService.getBidPlantByInfo(announceTitle);
			model.addAttribute("bidPlantdto", bidPlantdto);
		}
		if(announceType!=null && announceType.equals("부품")) {
			BidComponentDTO bidComponentdto = bidComponentService.getBidComponentByInfo(announceTitle);
			model.addAttribute("bidComponentdto", bidComponentdto);
		}
		return "/notice/announcement";
	}
	
	@PostMapping("/notice/bidRequest")
	public String bidRequest() {
		return "/notice/bidRequest";
	}
	
	@RequestMapping(value = "/sDepositRateCheck", method=RequestMethod.POST)
	public @ResponseBody double sDepositRateCheck () {
		return bidListService.getDepositRate(); 
	}
	@RequestMapping(value = "/bankCheck", method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> bankCheck () {
		List<String> managerList=memberService.getManager();
		return null; 
	}
	 
}
