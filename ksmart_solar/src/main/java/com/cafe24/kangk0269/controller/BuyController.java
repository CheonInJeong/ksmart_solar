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
		model.addAttribute("bidPlantDTO", bidPlantDTO);
		model.addAttribute("bidComponentDTO", bidComponentDTO);
		model.addAttribute("bidComponentList", bidComponentList);
		return "buy/myHistory";
	}
	//예치금 환불 리스트
	@GetMapping("/buy/applyRefund")
	public String ApplyRefund(HttpSession session, Model model) {
		System.out.println(savePaging==null);
		if(savePaging==null) {
			//화면의 제일 처름 페이지 설정
			System.out.println("세이브페이징 만들어짐");
			savePaging = new SavePaging(3,session);
			savePaging.setPaging(1, 1, 5, 5);
			savePaging.setPaging(2, 1, 5, 5);
			savePaging.setPaging(3, 1, 5, 5);
		}
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
