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
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.serivce.SellService;

@Controller
public class SellController {
	
	@Autowired
	private final SellService sellService;
	
	public SellController(SellService sellService) {
		this.sellService = sellService;
	}
	
	//서류 적합성 수정
	@RequestMapping(value= "/ajax/modifyDocumentCheck" , method= RequestMethod.POST)
	public @ResponseBody String modifyDocumentCheck(@RequestParam(value="bCode") String code,
													@RequestParam(value="documentYn") String check) {
		System.out.println(code+"-----"+check);
		sellService.modifyDocumentCheck(code, check);
		return check;
	}
	
	//입찰자 정보 보기 (회원정보+입찰내용)
	@GetMapping("/sell/plantBidderDetail")
	public String plantBidderDetail(@RequestParam(value="code") String code,
									Model model) {
		model.addAttribute("member", sellService.getBuyerInfoByCode(code));
		return "sell/plantBidderDetail";
	}
	
	
	
	//부품 공고 수정 화면
	@GetMapping("/sell/modifyComponentSell")
	public String modifyComponentSell(@RequestParam(value="bCpCode") String code, Model model) {
		return "";
	}
	
	//부품 공고 수정 처리
	@PostMapping("/sell/modifyComponentSell")
	public String modifyComponentSell(BidComponentDTO bidComponentDto) {
		return "redirect:/sell/myHisotry";
	}
	
	
	//부품 공고 삭제 처리
	@GetMapping("/sell/removeComponentSell")
	public String removeComponentSell(@RequestParam(value="bCpCode") String code) {
		return "redirect:/sell/myHistory";
	}
	//부품 공고 등록
	@PostMapping("sell/")
	public String regComponentSell(BidComponentDTO bidComponentDto) {
		return "";
	}
	
	
	//입찰 신청자 목록 보기
	@GetMapping("/sell/plantBidderList")
	public String getBidderList(@RequestParam(value="bPlCode") String code,Model model) {
		model.addAttribute("bidder", sellService.getPlantBidderList(code));
		model.addAttribute("plant", sellService.getBidPlantbyCode(code));
		return "sell/plantBidderList";
	}
	
	//부품 공고 내용 조회
	
	@GetMapping("/sell/getBidComponentDetail")
	public String getBidComponentDetail(@RequestParam(value="bCpCode") String code, Model model) {
		model.addAttribute("detail", sellService.getComponentDetail(code));
		return "sell/bidComponentDetail";
	}
	
	//발전소 공고 내용 조회
	@GetMapping("/sell/getBidPlantDetail")
	public String getBidPlantDetail(@RequestParam(value="bPlCode") String code,Model model) {
		List<BidPlantDTO> bidPlantDetail = sellService.getBidPlantDetail(code);
		model.addAttribute("bidPlantDetail", bidPlantDetail);
		return "sell/bidPlantDetail";
	}
	
	//발전소 공고 삭제
	@GetMapping("/sell/removePlantSell")
	public String removePlantSell(@RequestParam (value="bPlCode") String code) {
		System.out.println(code+"<---삭제할 공고의 코드");
		sellService.removePlantApply(code);
		return "redirect:/sell/myHistory";
	}
	//발전소 공고 수정 처리
	@PostMapping("/sell/modifyPlantSell")
	public String modifyPlantSell(BidPlantDTO bidPlantDto,MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request) throws Exception {
		sellService.modifyPlantApply(bidPlantDto, multipartHttpServletRequest, request);
		return "redirect:/sell/myHistory";
	}
	
	//발전소 공고 수정 화면 처리
	@GetMapping("/sell/modifyPlantSell")
	public ModelAndView modifyPlantSell(@RequestParam(value="bPlCode") String bPlCode) {
		System.out.println(bPlCode+"<---컨트롤러");
		ModelAndView mv = new ModelAndView("sell/modifyPlantSell");
		mv.addObject("bidPlant", sellService.getBidPlantbyCode(bPlCode));
		return mv;
	}
	
	//발전소 판매 공고 등록
	@PostMapping("/sell/plantSell")
	public String regPlantSell(BidPlantDTO bidPlantDto, MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request) throws Exception {
	
			sellService.addPlantApply(bidPlantDto, multipartHttpServletRequest,request);

		return "redirect:/sell/myHistory";
	}
	
	//부품 선택 시 해당 부품의 정보를 가져옴
	@RequestMapping(value="/ajax/componentInformation",method = RequestMethod.POST)
	public @ResponseBody ComponentDTO componentInformation(@RequestParam(value="cpCode") String cpCode) {
		return sellService.getComponentInformation(cpCode);
	}
	
	//발전소 공고 등록시 선택한 발전소의 정보를 가져옴
	@RequestMapping(value="/ajax/plantInformation",method = RequestMethod.POST)
	public @ResponseBody BusinessPlantDTO plantUnformation(@RequestParam(value="plantCode") String plantCode) {
		BusinessPlantDTO bzPlantDto =sellService.getPlantInformation(plantCode);
		return bzPlantDto;
	}
	
	 //발전소판매공고신청 버튼 클릭시
	@GetMapping("/sell/plantSell") 
	public ModelAndView plantSell(@RequestParam(name="mId") String mId) { 
		ModelAndView mv = new ModelAndView("/sell/plantSell");
		List<BusinessPlantDTO> plantList =	sellService.getPlantName(mId); 
		mv.addObject("plantList", plantList);
	  return mv; 
	  
	}

	//부품판매공고신청 버튼 클릭시
	@GetMapping("/sell/componentSell")
	public String componentSell(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("SID");
		model.addAttribute("component", sellService.getComponent(sessionId));
		return "sell/componentSell";
	}

	@GetMapping("/sell/apply")
	public String Apply() {
		
		return "sell/apply";
	}
	//내공고목록클릭시
	@GetMapping("/sell/myHistory")
	public String MyHistory(Model model,HttpSession session,String searchKey, String searchValue,String searchKeyCp,String searchValueCp) {
		String sessionId = (String)session.getAttribute("SID");
		List<BidPlantDTO> bidPlantList  = sellService.getBidPlantbyId(sessionId,searchKey,searchValue);
		List<BidComponentDTO> bidComponentList = sellService.getBidComponentById(sessionId,searchKeyCp,searchValueCp);
		model.addAttribute("bidPlantList", bidPlantList);
		model.addAttribute("bidComponentList", bidComponentList);
		
		return "sell/myHistory";
	}
	@GetMapping("/sell/mySell")
	public String MySell() {
		
		return "sell/mySell";
	}

	
	@RequestMapping(value="/ajax/getAccountInfoByNumber", method=RequestMethod.POST)
	public @ResponseBody MemberAccountDTO getAccountInfoByAccount(@RequestParam(value="bankAccount") String number) {
		return sellService.getAccountInfoByAccount(number);
	}
	
	@GetMapping("/sell/applyPayment")
	public String applyPayment(@RequestParam(value="trPrCode") String code, Model model,HttpSession session) {
		model.addAttribute("account", sellService.getMemberAccountById((String)session.getAttribute("SID")));
		model.addAttribute("applyPayment", sellService.getPaymentOutByCode(code));
		return "sell/applyPayment";
	}
	@PostMapping("/sell/applyPayment")
	public String applyPayment(TradePaymentOutDTO trPayOutDto) {
		sellService.addApplyPayment(trPayOutDto);
		return "redirect:/sell/paymentList";
	}
	
	
	//출금 가능한 거래 내역 보기
	@GetMapping("/sell/paymentList")
	public String PaymentList(Model model, HttpSession session) {
		String sessionId = (String)session.getAttribute("SID");
		model.addAttribute("available", sellService.getPaymentOutList(sessionId));
		return "sell/paymentList";
	}
	@GetMapping("/sell/qna")
	public String Qna() {
		
		return "sell/qna";
	}
}
