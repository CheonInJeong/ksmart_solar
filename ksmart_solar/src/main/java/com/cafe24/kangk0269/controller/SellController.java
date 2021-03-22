package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.serivce.SellService;

@Controller
public class SellController {
	
	@Autowired
	private final SellService sellService;
	
	public SellController(SellService sellService) {
		this.sellService = sellService;
	}
	
	
	
	
	//발전소판매공고신청 버튼 클릭시
	@GetMapping("/sell/plantSell")
	public ModelAndView plantSell() {
		ModelAndView mv = new ModelAndView("/sell/plantSell");
		List<BusinessPlantDTO> plantList = sellService.getPlantName();
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
