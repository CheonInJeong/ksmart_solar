package com.cafe24.kangk0269.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.serivce.SellService;

@Controller
public class SellController {
	
	@Autowired
	private final SellService sellService;
	
	public SellController(SellService sellService) {
		this.sellService = sellService;
	}
	
	
	//발전소 판매 공고 등록
	@PostMapping("/sell/regPlantSell")
	public String regPlantSell(BidPlantDTO bidPlantDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		if(bidPlantDto!=null&&!"".equals(bidPlantDto.getmId())) {
			sellService.addPlantApply(bidPlantDto,multipartHttpServletRequest);
		}
		return "/sell/myHistory";
	}
	
	//발전소 공고 등록시 선택한 발전소의 정보를 가져옴
	@RequestMapping(value="/ajax/plantInformation",method = RequestMethod.POST)
	public @ResponseBody BusinessPlantDTO plantUnformation(@RequestParam(value="plantCode") String plantCode) {
		BusinessPlantDTO bzPlantDto =sellService.getPlantInformation(plantCode);
		


		/*
		 * ArrayList<Object> plantInfo = new ArrayList<Object>();
		 * plantInfo.add(bzPlantDto.getPlantDepreciationDTO().getPlDepStartDate());
		 * plantInfo.add(bzPlantDto.getPlantDepreciationDTO().getPlDepPrice());
		 * plantInfo.add(bzPlantDto.getPlantDepDataDTO().getPlDepDataResidual());
		 */
		
		return bzPlantDto;
		
	}
	
	/*
	 * //발전소판매공고신청 버튼 클릭시
	 * 
	 * @GetMapping("/sell/plantSell") public ModelAndView
	 * plantSell(@RequestParam(name="mId") String mId) { ModelAndView mv = new
	 * ModelAndView("/sell/plantSell"); List<BusinessPlantDTO> plantList =
	 * sellService.getPlantName("id013"); mv.addObject("plantList", plantList);
	 * return mv; }
	 */
	
	//발전소판매공고신청 버튼 클릭시
	@GetMapping("/sell/plantSell")
	public ModelAndView plantSell() {
		ModelAndView mv = new ModelAndView("/sell/plantSell");
		List<BusinessPlantDTO> plantList = sellService.getPlantName("id013");
		mv.addObject("plantList", plantList);
		return mv;
	}
	//부품판매공고신청 버튼 클릭시
	@GetMapping("/sell/componentSell")
	public String componentSell() {
		
		return "/sell/componentSell";
	}
	
	@GetMapping("/sell/apply")
	public String Apply() {
		
		return "/sell/apply";
	}
	@GetMapping("/sell/myHistory")
	public String MyHistory() {
		
		return "/sell/myHistory";
	}
	@GetMapping("/sell/mySell")
	public String MySell() {
		
		return "/sell/mySell";
	}
	@GetMapping("/sell/applyPayment")
	public String ApplyPayment() {
		
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
