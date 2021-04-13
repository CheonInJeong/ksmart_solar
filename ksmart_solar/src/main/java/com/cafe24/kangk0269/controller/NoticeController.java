package com.cafe24.kangk0269.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.SavePaging;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.FileDTO;
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
	SavePaging savePaging = null;

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
	public String NoticeList(Model model,
							 HttpSession session,
							 @RequestParam(value = "searchKeyPl", required = false) 		String searchKeyPl, 
							 @RequestParam(value = "searchValuePl", required = false) 		String searchValuePl,
							 @RequestParam(value = "searchKeyCp", required = false) 		String searchKeyCp,
							 @RequestParam(value = "searchValueCp", required = false) 		String searchValueCp,
								
							 @RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
							 @RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
							 @RequestParam(value = "pageSize", required = false) 			String pageSize,
							 @RequestParam(value = "state", required = false) 				String state
							  ) {
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
																
		List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponent("진행",searchKeyCp,searchValueCp,bidComponentDTO);
		List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlant("진행",searchKeyPl,searchValuePl,bidPlantDTO);
		
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
		return "notice/noticeList";
	}
	//마감한 공고 목록
	@GetMapping("/notice/history")
	public String History(Model model,
						 HttpSession session,
						 @RequestParam(value = "searchKeyPl", required = false) 		String searchKeyPl, 
						 @RequestParam(value = "searchValuePl", required = false) 		String searchValuePl,
						 @RequestParam(value = "searchKeyCp", required = false) 		String searchKeyCp,
						 @RequestParam(value = "searchValueCp", required = false) 		String searchValueCp,
							
						 @RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
						 @RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
						 @RequestParam(value = "pageSize", required = false) 			String pageSize,
						 @RequestParam(value = "state", required = false) 				String state
						 ) {
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
																
		List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponent("종료",searchKeyCp,searchValueCp,bidComponentDTO);
		List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlant("종료",searchKeyPl,searchValuePl,bidPlantDTO);
		
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
		return "notice/history";
	}
	//공고 상세 정보 페이지
	@PostMapping("/notice/announcement")
	public String Announcement(String announceCode, String announceType, Model model,
							   HttpSession session) throws Exception {
		BidListDTO bidListDTO 				= null;
		BidPlantDTO bidPlantdto 			= null;
		BusinessPlantDTO businessPlantDTO 	= null;
		BidComponentDTO bidComponentdto		= null;
		ComponentDTO componentDTO			= null;
		TradePaymentInDTO tradePaymentInDTO = null;
		//공고 코드
		System.out.println(announceCode+"---------------------------------------------------공고코드");
		System.out.println(announceType+"---------------------------------------------------타입");
		//입찰자 목록 조회해야함
		String id = (String) session.getAttribute("SID");
		String level = (String) session.getAttribute("SLEVEL");
		model.addAttribute("level", level);
		model.addAttribute("id", id);
		System.out.println(id+"------------------------------------------id");
		int getBidListCount =0 ;
		if(id!=null) {
			getBidListCount = bidListService.getBidListCount(announceCode,id);
			//입찰한지 안한지를 보내준다.
		}
		System.out.println(getBidListCount+"-------------------------------------getBidListCount");
		model.addAttribute("getBidListCount",getBidListCount);
		//발전소 공고인지 부품공고인지를 구분하여 화면에 알맞는 정보를 보내준다.
		if(getBidListCount != 0) {
			//이미 입찰을 했다면 입찰한 정보를 보여준다.
			bidListDTO = bidListService.getBidList(announceCode,id);
			if(bidListDTO!=null) {
				List<FileDTO> fileList = bidListService.getBidFileList(bidListDTO.getbCode());
				System.out.println(bidListDTO.getTrTypeCode()+"----------------------------------------------------------------TrTypeCode");
				System.out.println(bidListDTO+"----------------------------------------------------------------bidListDTO");
				model.addAttribute("bidListDTO",bidListDTO);
				model.addAttribute("fileList",fileList);
			}
		}
		//발전소 공고라면 발전소 정보와 발전소 공고의 정보를 가져온다.
		if(announceType!=null && announceType.equals("발전소")) {
			bidPlantdto = bidPlantService.getBidPlantByInfo(announceCode);
			businessPlantDTO = bidPlantService.getPlant(announceCode);
			System.out.println(bidPlantdto+"----------------------------------발전소 공고 정보");
			System.out.println(businessPlantDTO+"----------------------------------발전소 정보");
			model.addAttribute("bidPlantdto", bidPlantdto);
			model.addAttribute("businessPlantDTO", businessPlantDTO);
		}
		//부품 공고라면 부품 정보와 부품 공고의 정보를 가져온다.
		if(announceType!=null && announceType.equals("부품")) {
			bidComponentdto = bidComponentService.getBidComponentByInfo(announceCode);
			componentDTO = bidComponentService.getComponent(bidComponentdto.getCpCode());
			model.addAttribute("bidComponentdto", bidComponentdto);
			model.addAttribute("componentDTO", componentDTO);
		}
		//계약중이라면 계약 정보를 가져온다.
		if(bidListDTO!=null && bidListDTO.getTrTypeCode()>=11 && bidListDTO.getTrTypeCode()<=14) {
			tradePaymentInDTO = tradeService.getTradePaymentIn(bidListDTO.getbCode());
			model.addAttribute("tradePaymentInDTO", tradePaymentInDTO);
			System.out.println(tradePaymentInDTO+"===============================================tradePaymentInDTO");
		}
		return "notice/announcement";
	}
	//대금납부 신청 페이지
	@PostMapping("/notice/paymentInRequest")
	public String paymentIn(String bCode, Model model,String url) {
		System.out.println(bCode+"-------------------------------------------");
		if(bCode!=null) {
			BidListDTO bidListDTO = bidListService.getBidList(bCode);
			TradePaymentInDTO tradePaymentInDTO = tradeService.getTradePaymentIn(bCode);
			System.out.println(bidListDTO+"-------------------------------------------------");
			model.addAttribute("tradePaymentInDTO", tradePaymentInDTO);
			model.addAttribute("bidListDTO", bidListDTO);
			model.addAttribute("url", url);
		}
		return "/notice/paymentInRequest";
	}
	//입찰 취소
	@PostMapping("/notice/bidCancel")
	public String bidCancel(String bCode) {
		System.out.println(bCode);
		bidListService.bidCancel(bCode);
		return "redirect:/buy/myHistory";
	}
	//대금납부 수정
	@PostMapping("/notice/modifyPaymentIn")
	public String modifyPaymentIn(String bCode, Model model,String url) {
		System.out.println(bCode+"-------------------------------------------");
		if(bCode!=null) {
			BidListDTO bidListDTO = bidListService.getBidList(bCode);
			TradePaymentInDTO tradePaymentInDTO = tradeService.getTradePaymentIn(bCode);
			System.out.println(bidListDTO+"-------------------------------------------------");
			model.addAttribute("tradePaymentInDTO", tradePaymentInDTO);
			model.addAttribute("bidListDTO", bidListDTO);
			model.addAttribute("url", url);
		}
		return "/notice/modifyPaymentIn";
	}
	//입찰 수정
	@PostMapping("/notice/modifyBidRequest")
	public String modifyBidRequest(String bCode, Model model,String url,String announcedPrice) {
		System.out.println(bCode+"-------------------------------------------");
		if(bCode!=null) {
			BidListDTO bidListDTO = bidListService.getBidList(bCode);
			System.out.println(bidListDTO+"-------------------------------------------------");
			model.addAttribute("announcedPrice", announcedPrice);
			model.addAttribute("bidListDTO", bidListDTO);
			model.addAttribute("url", url);
		}
		return "/notice/modifyBidRequest";
	}
	//입찰수정 액션
	@PostMapping("/notice/modifyBidRequestAction")
	public String modifyBidRequestAction(Model model, BidListDTO bidListDTO,MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request) {
		System.out.println(bidListDTO.getbCode()+"=======================bidListDTO.bCode");
		System.out.println(bidListDTO.getbDeposit()+"==========================bidListDTO.Deposit");
		System.out.println(bidListDTO.getbPrice()+"==========================bidListDTO.bPrice");
		System.out.println(bidListDTO.getsDepositRate()+"==========================bidListDTO.getsDepositRate");
		System.out.println(bidListDTO.getmAccountBankName()+"==========================bidListDTO.mAccountBankName");
		System.out.println(bidListDTO.getmAccountNumber()+"==========================bidListDTO.mAccountNumber");
		System.out.println(bidListDTO.getmPaymentName()+"==========================bidListDTO.mPaymentName");
		try {
			bidListService.modifyBidList(bidListDTO,multipartHttpServletRequest,request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/buy/myHistory";
	}
	@PostMapping("/notice/addpaymentInRequest")
	public String addpaymentInRequest(TradePaymentInDTO paymentInDTO) {
		System.out.println(paymentInDTO);
		tradeService.modifyTradePaymentIn(paymentInDTO);
		return "redirect:/buy/myHistory";
	}
	//입찰신청 페이지
	@PostMapping("/notice/bidRequest")
	public String bidRequest(String announcedCode,String announcedTitle,String announcedPrice,String announcedType,Model model,String url) {
		System.out.println(announcedCode+"<-----공고코드");
		System.out.println(announcedTitle+"<---------공고제목");
		System.out.println(announcedPrice+"<---------공고 입찰시작가");
		System.out.println(announcedType+"<----------2=부품인지 1=발전소인지");
		System.out.println(url);
		model.addAttribute("url", url);
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
		System.out.println(bidListDTO);
		return "/notice/bidRequestResult";
	}
	//입찰신청 등록
	@PostMapping("/notice/addbidRequest")
	public String addbidRequest(BidListDTO bidListDTO,MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request) {
		System.out.println(bidListDTO);
		try {
			bidListService.addbidList(bidListDTO,multipartHttpServletRequest,request);
		} catch (Exception e) {
			System.out.println("파일등록 실패");
			e.printStackTrace();
		}
		return "redirect:/buy/myHistory";
	}
	//재공고 입찰시 이전 공고에서 거래를 취소한 적이 있는지 파악
	@RequestMapping(value = "/reBidCount",method = RequestMethod.GET )
	public @ResponseBody int reBidCount(String bGroupcode,HttpSession session) {
		System.out.println("입찰 카운드 들어옴");
		System.out.println(bGroupcode);
		System.out.println(session.getAttribute("SID"));
		int count = bidListService.reBidCount(bGroupcode, (String)session.getAttribute("SID"));
		System.out.println(count);
		return count;
	}
	//계약 취소
	@PostMapping("/notice/tradeCancel")
	public String tradeCancel(String bCode) {
		System.out.println(bCode);
		bidListService.tradeCancel(bCode);
		return "redirect:/notice/noticeList";
	}
}
