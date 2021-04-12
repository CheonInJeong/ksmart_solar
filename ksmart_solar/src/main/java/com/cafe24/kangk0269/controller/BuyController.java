package com.cafe24.kangk0269.controller;

import java.util.HashMap;
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

import com.cafe24.kangk0269.common.SavePaging;
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
	SavePaging savePaging = null;
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
							@RequestParam(value = "searchKeyPl", required = false) 			String searchKeyPl, 
							@RequestParam(value = "searchValuePl", required = false) 		String searchValuePl,
							@RequestParam(value = "searchKeyCp", required = false) 			String searchKeyCp,
							@RequestParam(value = "searchValueCp", required = false) 		String searchValueCp,
							
							@RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
							@RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
							@RequestParam(value = "pageSize", required = false) 			String pageSize,
							@RequestParam(value = "state", required = false) 				String state
							) {
		System.out.println("입찰한 공고 시작");
		System.out.println(savePaging==null);
		System.out.println(state+"----------------------------------------state");
		BidPlantDTO bidPlantDTO = new BidPlantDTO();
		BidComponentDTO bidComponentDTO = new BidComponentDTO();
		bidPlantDTO.setState(1);
		bidComponentDTO.setState(2);
		if(savePaging==null || state==null) {
			//화면의 제일 처름 페이지 설정
			System.out.println("세이브페이징 만들어짐");
			savePaging = new SavePaging(2,session);
			savePaging.setPaging(1, 1, 5, 5);
			savePaging.setPaging(2, 1, 5, 5);
		}
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			//페이지가 넘어갈때 넘어간 페이지 저장
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage),Integer.parseInt(pageSize));
		}
		//페이지 저장 가져오기
		savePaging.getPaging(bidComponentDTO);
		savePaging.getPaging(bidPlantDTO);
		
		System.out.println(bidComponentDTO.getCurrentPageNo()+"-------------------------------부품 현재페이지");
		System.out.println(bidPlantDTO.getCurrentPageNo()+"-------------------------------발전소 현재페이지");
		
		System.out.println(session.getAttribute("SID"));
		String sId = (String) session.getAttribute("SID");
		List<BidComponentDTO> bidComponentList = null;
		List<BidPlantDTO> bidPlantList = null;
		if(sId != null) {
			bidComponentList = bidComponentService.getBidComponentMyBid(sId,searchKeyCp,searchValueCp,bidComponentDTO);
			bidPlantList = bidPlantService.getBidPlantMyBid(sId,searchKeyPl,searchValuePl,bidPlantDTO);
		}
		if(searchKeyCp!=null && !searchKeyCp.equals("null")) {
			model.addAttribute("searchKeyCp", searchKeyCp);
		}
		if(searchValueCp!=null && !searchValueCp.equals("null")) {
			model.addAttribute("searchValueCp", searchValueCp);
		}
		if(searchKeyPl!=null && !searchKeyPl.equals("null")) {
			model.addAttribute("searchKeyPl", searchKeyPl);
		}
		if(searchValuePl!=null && !searchValuePl.equals("null")) {
			model.addAttribute("searchValuePl", searchValuePl);
		}
		model.addAttribute("bidPlantList", bidPlantList);
		model.addAttribute("bidPlantDTO", bidPlantDTO);
		model.addAttribute("bidComponentDTO", bidComponentDTO);
		model.addAttribute("bidComponentList", bidComponentList);
		return "buy/myHistory";
	}
	//예치금 환불 리스트
	@GetMapping("/buy/applyRefund")
	public String ApplyRefund(HttpSession session,
							  Model model,
							  @RequestParam(value = "searchKeyPb", required = false) 		String searchKeyPb, 
							  @RequestParam(value = "searchValuePb", required = false) 		String searchValuePb,
							  @RequestParam(value = "searchKeyCp", required = false) 		String searchKeyCp,
							  @RequestParam(value = "searchValueCp", required = false) 		String searchValueCp,
							  @RequestParam(value = "searchKeyRq", required = false) 		String searchKeyRq,
							  @RequestParam(value = "searchValueRq", required = false) 		String searchValueRq,
								
							  @RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
							  @RequestParam(value = "recordsPerPage", required = false) 	String recordsPerPage,
							  @RequestParam(value = "pageSize", required = false) 			String pageSize,
							  @RequestParam(value = "state", required = false) 				String state
							  ) {
		System.out.println(searchKeyPb+"---------------------------searchKeyPb");
		System.out.println(searchValuePb+"---------------------------searchValuePb");
		System.out.println(searchKeyCp+"---------------------------searchKeyCp");
		System.out.println(searchValueCp+"---------------------------searchValueCp");
		System.out.println(searchKeyRq+"---------------------------searchKeyRq");
		System.out.println(searchValueRq+"---------------------------searchValueRq");
		BidListDTO RefundPossible = new BidListDTO();
		BidListDTO RefundComplete = new BidListDTO();
		BidListDTO RefundRequest = new BidListDTO();
		RefundPossible.setState(1);
		RefundComplete.setState(2);
		RefundRequest.setState(3);
		if(savePaging==null || state==null) {
			//화면의 제일 처름 페이지 설정
			System.out.println("세이브페이징 만들어짐");
			savePaging = new SavePaging(3,session);
			savePaging.setPaging(1, 1, 5, 5);
			savePaging.setPaging(2, 1, 5, 5);
			savePaging.setPaging(3, 1, 5, 5);
		}
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			//페이지가 넘어갈때 넘어간 페이지 저장
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage),Integer.parseInt(pageSize));
		}
		//페이지 저장 가져오기
		savePaging.getPaging(RefundPossible);
		savePaging.getPaging(RefundComplete);
		savePaging.getPaging(RefundRequest);
		
		System.out.println(session.getAttribute("SID"));
		String id = (String) session.getAttribute("SID");
		Map<String, List<BidListDTO>> refundList =null;
		if(id != null) {
			System.out.println("환불리스트조회하기");
			List<BidListDTO> RefundRequestList = bidListService.getApplyRefundList(id,"신청",searchKeyRq,searchValueRq,RefundRequest);
			List<BidListDTO> RefundCompleteList = bidListService.getApplyRefundList(id,"완료",searchKeyCp,searchValueCp,RefundComplete);
			List<BidListDTO> RefundPossibleList = bidListService.getApplyRefundList(id,"가능",searchKeyPb,searchValuePb,RefundPossible);
			refundList = new HashMap<String, List<BidListDTO>>();
			refundList.put("RefundRequestList", RefundRequestList);
			refundList.put("RefundCompleteList", RefundCompleteList);
			refundList.put("RefundPossibleList", RefundPossibleList);
		}
		model.addAttribute("refundList", refundList);
		model.addAttribute("RefundPossible", RefundPossible);
		model.addAttribute("RefundComplete", RefundComplete);
		model.addAttribute("RefundRequest", RefundRequest);
		if(searchKeyPb!=null && !searchKeyPb.equals("null")) {
			model.addAttribute("searchKeyPb", searchKeyPb);
		}
		if(searchValuePb!=null && !searchValuePb.equals("null")) {
			model.addAttribute("searchValuePb", searchValuePb);
		}
		if(searchKeyCp!=null && !searchKeyCp.equals("null")) {
			model.addAttribute("searchKeyCp", searchKeyCp);
		}
		if(searchValueCp!=null && !searchValueCp.equals("null")) {
			model.addAttribute("searchValueCp", searchValueCp);
		}
		if(searchKeyRq!=null && !searchKeyRq.equals("null")) {
			model.addAttribute("searchKeyRq", searchKeyRq);
		}
		if(searchValueRq!=null && !searchValueRq.equals("null")) {
			model.addAttribute("searchValueRq", searchValueRq);
		}
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
