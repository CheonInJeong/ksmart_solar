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
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradePaymentInDTO;
import com.cafe24.kangk0269.serivce.AccountService;
import com.cafe24.kangk0269.serivce.BidComponentService;
import com.cafe24.kangk0269.serivce.BidListService;
import com.cafe24.kangk0269.serivce.BidPlantService;
import com.cafe24.kangk0269.serivce.MemberService;
import com.cafe24.kangk0269.serivce.TradeService;

@Controller
public class NoticeController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private AccountService accountService;
	private final BidComponentService bidComponentService;
	private final BidPlantService bidPlantService;
	private final BidListService bidListService;
	private final TradeService tradeService;
	
	@Autowired
	public NoticeController(BidComponentService bidComponentService,
							BidPlantService bidPlantService,
							BidListService bidListService,
							TradeService tradeService) {
		this.bidComponentService = bidComponentService; 
		this.bidPlantService = bidPlantService; 
		this.bidListService = bidListService;
		this.tradeService = tradeService;
	}
	//진행중인 공고 목록
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
	//마감한 공고 목록
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
	//공고 상세 정보 페이지
	@PostMapping("/notice/announcement")
	public String Announcement(String announceCode, String announceType, Model model,
							   HttpSession session) {
		BidListDTO bidListDTO 				= null;
		BidPlantDTO bidPlantdto 			= null;
		BusinessPlantDTO businessPlantDTO 	= null;
		BidComponentDTO bidComponentdto		= null;
		ComponentDTO componentDTO			= null;
		TradePaymentInDTO tradePaymentInDTO = null;
		//공고 코드
		System.out.println(announceCode);
		System.out.println(announceType);
		//입찰자 목록 조회해야함
		String id = (String) session.getAttribute("SID");
		System.out.println(id+"------------------------------------------id");
		int getBidListCount =0 ;
		if(id!=null) {
			getBidListCount = bidListService.getBidListCount(announceCode,id);
			//입찰한지 안한지를 보내준다.
		}
		model.addAttribute("getBidListCount",getBidListCount);
		//발전소 공고인지 부품공고인지를 구분하여 화면에 알맞는 정보를 보내준다.
		if(getBidListCount != 0) {
			//이미 입찰을 했다면 입찰한 정보를 보여준다.
			bidListDTO = bidListService.getBidList(announceCode,id);
			if(bidListDTO!=null) {
				System.out.println(bidListDTO.getTrTypeCode()+"----------------------------------------------------------------");
				model.addAttribute("bidListDTO",bidListDTO);
			}
		}
		if(announceType!=null && announceType.equals("발전소")) {
			bidPlantdto = bidPlantService.getBidPlantByInfo(announceCode);
			businessPlantDTO = bidPlantService.getPlant(announceCode);
			System.out.println(businessPlantDTO+"----------------------------------발전소 정보");
			model.addAttribute("bidPlantdto", bidPlantdto);
			model.addAttribute("businessPlantDTO", businessPlantDTO);
		}
		if(announceType!=null && announceType.equals("부품")) {
			bidComponentdto = bidComponentService.getBidComponentByInfo(announceCode);
			componentDTO = bidComponentService.getComponent(bidComponentdto.getCpCode());
			model.addAttribute("bidComponentdto", bidComponentdto);
			model.addAttribute("componentDTO", componentDTO);
		}
		if(bidListDTO!=null && bidListDTO.getTrTypeCode()==11) {
			tradePaymentInDTO = tradeService.getTradePaymentIn(bidListDTO.getbCode());
			model.addAttribute("tradePaymentInDTO", tradePaymentInDTO);
			System.out.println(tradePaymentInDTO.getmAccountBankName()+"========================================대금납부할 은행");
		}
		return "/notice/announcement";
	}
	//대금납부 신청 페이지
	@PostMapping("/notice/paymentInRequest")
	public String paymentIn(String bCode, Model model) {
		System.out.println(bCode+"-------------------------------------------");
		if(bCode!=null) {
			BidListDTO bidListDTO = bidListService.getBidList(bCode);
			TradePaymentInDTO tradePaymentInDTO = tradeService.getTradePaymentIn(bCode);
			System.out.println(bidListDTO+"-------------------------------------------------");
			model.addAttribute("tradePaymentInDTO", tradePaymentInDTO);
			model.addAttribute("bidListDTO", bidListDTO);
		}
		return "/notice/paymentInRequest";
	}
	@PostMapping("/notice/modifyPaymentIn")
	public String modifyPaymentIn(String bCode, Model model) {
		System.out.println(bCode+"-------------------------------------------");
		if(bCode!=null) {
			BidListDTO bidListDTO = bidListService.getBidList(bCode);
			TradePaymentInDTO tradePaymentInDTO = tradeService.getTradePaymentIn(bCode);
			System.out.println(bidListDTO+"-------------------------------------------------");
			model.addAttribute("tradePaymentInDTO", tradePaymentInDTO);
			model.addAttribute("bidListDTO", bidListDTO);
		}
		return "/notice/modifyPaymentIn";
	}
	@PostMapping("/notice/addpaymentInRequest")
	public String addpaymentInRequest(TradePaymentInDTO paymentInDTO) {
		System.out.println(paymentInDTO);
		tradeService.modifyTradePaymentIn(paymentInDTO);
		return "redirect:/buy/myHistory";
	}
	//입찰신청 페이지
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
	//입찰 확인 페이지
	@GetMapping("/notice/bidRequestResult")
	public String bidRequestResult(BidListDTO bidListDTO) {
		return "/notice/bidRequestResult";
	}
	//입찰신청 등록
	@PostMapping("/notice/addbidRequest")
	public String addbidRequest(BidListDTO bidListDTO) {
		System.out.println(bidListDTO);
		bidListService.addbidList(bidListDTO);
		return "redirect:/notice/bidRequestResult";
	}
}
