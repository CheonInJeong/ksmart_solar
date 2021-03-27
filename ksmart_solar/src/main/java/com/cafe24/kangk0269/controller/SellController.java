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
import com.cafe24.kangk0269.serivce.SellService;

@Controller
public class SellController {
	
	@Autowired
	private final SellService sellService;
	
	public SellController(SellService sellService) {
		this.sellService = sellService;
	}
	//입찰 신청자 목록 보기
	@GetMapping("/sell/bidderList")
	public String getBidderList(@RequestParam(value="bPlCode") String code,Model model) {
		model.addAttribute("bidder", sellService.getBidderList(code));
		return "/sell/bidderList";
	}
	
	//발전소 공고 내용 조회
	@GetMapping("/sell/getBidPlantDetail")
	public String getBidPlantDetail(@RequestParam(value="bPlCode") String code,Model model) {
		List<BidPlantDTO> bidPlantDetail = sellService.getBidPlantDetail(code);
		model.addAttribute("bidPlantDetail", bidPlantDetail);
		return "/sell/bidPlantDetail";
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
		ModelAndView mv = new ModelAndView("/sell/modifyPlantSell");
		mv.addObject("bidPlant", sellService.getBidPlantbyCode(bPlCode));
		return mv;
	}
	
	//발전소 판매 공고 등록
	@PostMapping("/sell/plantSell")
	public String regPlantSell(BidPlantDTO bidPlantDto, MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request) throws Exception {
		if(bidPlantDto!=null&&!"".equals(bidPlantDto.getmId())) {
			sellService.addPlantApply(bidPlantDto, multipartHttpServletRequest,request);
		}

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
		return "/sell/componentSell";
	}
	//공고신청 메뉴 클릭시
	@GetMapping("/sell/apply")
	public String Apply() {
		
		return "/sell/apply";
	}
	//내공고목록클릭시
	@GetMapping("/sell/myHistory")
	public String MyHistory(Model model,HttpServletRequest request,HttpSession session) {
		String sessionId = (String)session.getAttribute("SID");
		List<BidPlantDTO> bidPlantList  = sellService.getBidPlantbyId(sessionId);
		List<BidComponentDTO> bidComponentList = sellService.getBidComponentById(sessionId);
		model.addAttribute("bidPlantList", bidPlantList);
		model.addAttribute("bidComponentList", bidComponentList);
		
		return "/sell/myHistory";
	}
	@GetMapping("/sell/mySell")
	public String MySell() {
		
		return "/sell/mySell";
	}

	@GetMapping("/sell/applyPayment")
	public String applyPayment1() {
		
		return "/sell/applyPayment";
	}
	@GetMapping("/sell/paymentList")
	public String PaymentList() {
		
		return "/sell/paymentList";
	}
	@GetMapping("/sell/qna")
	public String Qna() {
		
		return "/sell/qna";
	}
}
