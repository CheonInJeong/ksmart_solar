package com.cafe24.kangk0269.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradeDepositOutDTO;
import com.cafe24.kangk0269.serivce.AccountService;
import com.cafe24.kangk0269.serivce.BidComponentService;
import com.cafe24.kangk0269.serivce.BidListService;
import com.cafe24.kangk0269.serivce.BidPlantService;
import com.cafe24.kangk0269.serivce.TradeService;

@Controller
public class BuyController {
	
	private final BidComponentService bidComponentService;
	private final BidPlantService bidPlantService;
	private final BidListService bidListService;
	private final AccountService accountService;
	private final TradeService tradeService;
	
	@Autowired
	public BuyController(BidComponentService bidComponentService,
							BidPlantService bidPlantService
							,BidListService bidListService,
							AccountService accountService,
							TradeService tradeService) {
		this.bidComponentService = bidComponentService; 
		this.bidPlantService = bidPlantService; 
		this.bidListService = bidListService; 
		this.accountService = accountService; 
		this.tradeService = tradeService; 
	}
	//내가 입찰한 공고
	@GetMapping("/buy/myHistory")
	public String MyHistory(HttpSession session,
							Model model,
							@RequestParam(value = "searchKeyPl", required = false) String searchKeyPl, 
							@RequestParam(value = "searchValuePl", required = false) String searchValuePl,
							@RequestParam(value = "searchKeyCp", required = false) String searchKeyCp,
							@RequestParam(value = "searchValueCp", required = false) String searchValueCp,
							
							@RequestParam(value = "currentPageNoCp", required = false) String currentPageNoCp,
							@RequestParam(value = "recordsPerPageCp", required = false) String recordsPerPageCp,
							@RequestParam(value = "pageSizeCp", required = false) String pageSizeCp,
							@RequestParam(value = "currentPageNoPl", required = false) String currentPageNoPl,
							@RequestParam(value = "recordsPerPagePl", required = false) String recordsPerPagePl,
							@RequestParam(value = "pageSizePl", required = false) String pageSizePl,
							@RequestParam(value = "state", required = false) String state,
							
							@ModelAttribute("bidPlantDTO") BidPlantDTO bidPlantDTO,
							@ModelAttribute("bidComponentDTO") BidComponentDTO bidComponentDTO) {
		System.out.println(bidComponentDTO.getCurrentPageNo()+"-------------------------------부품 현재페이지");
		System.out.println(bidPlantDTO.getCurrentPageNo()+"-------------------------------발전소 현재페이지");
		if(state!=null && bidComponentDTO.getState()==Integer.parseInt(state)) {
			System.out.println("부품");
			if(currentPageNoCp!=null) bidComponentDTO.setCurrentPageNo(Integer.parseInt(currentPageNoCp));
			if(recordsPerPageCp!=null) bidComponentDTO.setRecordsPerPage(Integer.parseInt(recordsPerPageCp));
			if(pageSizeCp!=null) bidComponentDTO.setPageSize(Integer.parseInt(pageSizeCp));
		}
		if(state!=null && bidPlantDTO.getState()==Integer.parseInt(state)) {
			System.out.println("발전소");
			if(currentPageNoPl!=null) bidPlantDTO.setCurrentPageNo(Integer.parseInt(currentPageNoPl));
			if(recordsPerPagePl!=null) bidPlantDTO.setRecordsPerPage(Integer.parseInt(recordsPerPagePl));
			if(pageSizePl!=null) bidPlantDTO.setPageSize(Integer.parseInt(pageSizePl));
		}
		
		
		System.out.println(session.getAttribute("SID"));
		String sId = (String) session.getAttribute("SID");
		List<BidComponentDTO> bidComponentList = null;
		List<BidPlantDTO> bidPlantList = null;
		System.out.println(bidComponentDTO.getPagination());
		if(searchKeyCp!=null && searchKeyCp.equals("null")) {
			System.out.println("문자열 unll");
			searchKeyCp = null;
		}
		if(searchValueCp!=null && searchValueCp.equals("null")) {
			System.out.println("문자열 unll");
			searchValueCp = null;
		}
		if(searchKeyPl!=null && searchKeyPl.equals("null")) {
			System.out.println("문자열 unll");
			searchKeyPl = null;
		}
		if(searchValuePl!=null && searchValuePl.equals("null")) {
			System.out.println("문자열 unll");
			searchValuePl = null;
		}
		if(sId != null) {
			bidComponentList = bidComponentService.getBidComponentMyBid(sId,searchKeyCp,searchValueCp,bidComponentDTO);
			bidPlantList = bidPlantService.getBidPlantMyBid(sId,searchKeyPl,searchValuePl,bidPlantDTO);
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
		}
		model.addAttribute("searchKeyCp", searchKeyCp);
		model.addAttribute("searchValueCp", searchValueCp);
		model.addAttribute("searchKeyPl", searchKeyPl);
		model.addAttribute("searchValuePl", searchValuePl);
		model.addAttribute("bidPlantList", bidPlantList);
		model.addAttribute("bidComponentList", bidComponentList);
		return "buy/myHistory";
	}
	//예치금 환불 리스트
	@GetMapping("/buy/applyRefund")
	public String ApplyRefund(HttpSession session, Model model) {
		System.out.println(session.getAttribute("SID"));
		String id = (String) session.getAttribute("SID");
		Map<String, List<BidListDTO>> refundList =null;
		if(id != null) {
			refundList = bidListService.getApplyRefundList(id);
		}
		model.addAttribute("refundList", refundList);
		return "buy/applyRefund";
	}
	
	@GetMapping("/buy/qna")
	public String Qna() {
		
		return "buy/qna";
	}
	//예치금 출금 신청
	@PostMapping("/buy/refundRequest")
	public String refundRequest(String bCode,String bDeposit,HttpSession session, String bTitle,Model model, String bType) {
		String id = (String) session.getAttribute("SID");
		List<MemberAccountDTO> accountList = accountService.getAccountListById(id);
		model.addAttribute("accountList", accountList);
		model.addAttribute("bTitle", bTitle);
		model.addAttribute("bCode", bCode);
		model.addAttribute("bDeposit", bDeposit);
		model.addAttribute("bType", bType);
		System.out.println(bTitle);
		System.out.println(bCode);
		System.out.println(bDeposit);
		return "/buy/refundRequest";
	}
	//본인계좌 조회 ajax
	@RequestMapping(value = "/myBank", method=RequestMethod.POST)
	public @ResponseBody MemberAccountDTO bankCheck (String mAccountIdx) {
		MemberAccountDTO memberAccountDTO = accountService.getAccountListByIdx(mAccountIdx);
		return memberAccountDTO; 
	}
	//예치금신청등록
	@PostMapping("/buy/addrefundRequest")
	public String addRefundRequest(TradeDepositOutDTO tradeDepositOutDTO) {
		System.out.println(tradeDepositOutDTO.getbCode());
		System.out.println(tradeDepositOutDTO.getbDeposit());
		System.out.println(tradeDepositOutDTO.getmAccountBankName());
		System.out.println(tradeDepositOutDTO.getmAccountName());
		System.out.println(tradeDepositOutDTO.getmAccountNumber());
		System.out.println(tradeDepositOutDTO.getmId());
		tradeService.addRefundRequest(tradeDepositOutDTO);
		return "redirect:/buy/applyRefund";
	}
	@PostMapping("/buy/refundInfo")
	public String refundInfo(String bCode, String bTitle, Model model, String bType) {
		TradeDepositOutDTO tradeDepositOutDTO = tradeService.getRefundInfo(bCode);
		model.addAttribute("bTitle", bTitle);
		model.addAttribute("bType", bType);
		model.addAttribute("tradeDepositOutDTO", tradeDepositOutDTO);
		return "/buy/refundInfo";
	}
	@PostMapping("/buy/refundResult")
	public String refundResult(String bCode, String bTitle, Model model, String bType) {
		TradeDepositOutDTO tradeDepositOutDTO = tradeService.getRefundInfo(bCode);
		model.addAttribute("bTitle", bTitle);
		model.addAttribute("bType", bType);
		model.addAttribute("tradeDepositOutDTO", tradeDepositOutDTO);
		return "/buy/refundResult";
	}
}
