package com.cafe24.kangk0269.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
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
	public String Announcement(String uri,String announceCode, String announceType, Model model,
							   HttpSession session) {
		//공고 코드
		System.out.println(announceCode);
		System.out.println(announceType);
		System.out.println(uri);
		//입찰자 목록 조회해야함
		String id = (String) session.getAttribute("SID");
		int getBidListCount = bidListService.getBidListCount(announceCode,id);
		//입찰한지 안한지를 보내준다.
		model.addAttribute("getBidListCount",getBidListCount);
		//발전소 공고인지 부품공고인지를 구분하여 화면에 알맞는 정보를 보내준다.
		if(getBidListCount != 0) {
			//이미 입찰을 했다면 입찰한 정보를 보여준다.
			BidListDTO bidListDTO = bidListService.getBidList(announceCode,id);
			if(bidListDTO!=null) {
				model.addAttribute("bidListDTO",bidListDTO);
			}
		}
		if(announceType!=null && announceType.equals("발전소")) {
			BidPlantDTO bidPlantdto = bidPlantService.getBidPlantByInfo(announceCode);
			BusinessPlantDTO businessPlantDTO = bidPlantService.getPlant(announceCode);
			System.out.println(businessPlantDTO+"----------------------------------발전소 정보");
			model.addAttribute("bidPlantdto", bidPlantdto);
			model.addAttribute("businessPlantDTO", businessPlantDTO);
		}
		if(announceType!=null && announceType.equals("부품")) {
			BidComponentDTO bidComponentdto = bidComponentService.getBidComponentByInfo(announceCode);
			model.addAttribute("bidComponentdto", bidComponentdto);
		}
		return "/notice/announcement";
	}
	
	
	@PostMapping("/notice/bidRequest")
	public String bidRequest(String announcedCode,String announcedTitle,String announcedPrice,String announcedType,Model model) {
		System.out.println(announcedCode+"<-----공고코드");
		System.out.println(announcedTitle+"<---------공고제목");
		System.out.println(announcedPrice+"<---------공고 입찰시작가");
		System.out.println(announcedType+"<----------2=부품인지 1=발전소인지");
		model.addAttribute("announcedCode", announcedCode);
		model.addAttribute("announcedTitle", announcedTitle);
		model.addAttribute("announcedPrice", announcedPrice);
		model.addAttribute("announcedType", announcedType);
		return "/notice/bidRequest";
	}
	//수수료율 가져오는 ajax통신
	@RequestMapping(value = "/sDepositRateCheck", method=RequestMethod.POST)
	public @ResponseBody double sDepositRateCheck () {
		return bidListService.getDepositRate(); 
	}
	//관리자 계좌 가져오는 ajax 통신
	@RequestMapping(value = "/bankCheck", method=RequestMethod.POST)
	public @ResponseBody List<MemberAccountDTO> bankCheck () {
		List<String> managerList=memberService.getManager();
		List<MemberAccountDTO> accountList=accountService.getAccountListByManager(managerList);
		System.out.println(accountList.get(0).getmAccountBank());
		return accountList; 
	}
	@GetMapping("/notice/bidRequestResult")
	public String bidRequestResult(BidListDTO bidListDTO) {
		return "/notice/bidRequestResult";
	}
	
	@PostMapping("/notice/addbidRequest")
	public String addbidRequest(BidListDTO bidListDTO) {
		System.out.println(bidListDTO);
		bidListService.addbidList(bidListDTO);
		return "redirect:/notice/bidRequestResult";
	}
}
