package com.cafe24.kangk0269.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.api.CrawlingApi;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.serivce.PlantService;

@Controller
public class PlantController {
	
	@Autowired
	private PlantService plantService;
	
	
	@GetMapping("/plant/componentList")
	public String ComponentList() {
		
		return "/plant/componentList";
	}
	
	@GetMapping("/plant/authorization")
	public String Authorization() {
		
		return "/plant/authorization";
	}
	
	@GetMapping("/plant/plantDetail")
	public String plantDetail(Model model ,HttpSession session) {
		String bzCode = (String) session.getAttribute("SBZCODE");
		plantService.getPlantDetail(model, bzCode);
		return "/plant/plantDetail";
	}
	
	@PostMapping("/plant/plantDetail")
	public String plantDetail(Model model 
							  ,@RequestParam(name="bzCode", required=false) String bzCode
							  ,HttpSession session) {
		if(bzCode == null) {
			bzCode = (String) session.getAttribute("SBZCODE");
		}
		session.setAttribute("SBZCODE", bzCode);
		plantService.getPlantDetail(model, bzCode);
		return "/plant/plantDetail";
	}
	  
	//수익 분석
	@GetMapping("/plant/plantDetail/benefitAnalysis")
	public String benefitAnalysis() {
		
		return "/plant/benefitAnalysis";
	}
	
	
	//발전량 분석
	@GetMapping("/plant/plantDetail/generationAnalysis")
	public String generationAnalysis() {
		
		return "/plant/generationAnalysis";
	}
	
	
	//발전량 예측
	@GetMapping("/plant/plantDetail/generationPredict")
	public String generationPredict() {
		
		return "/plant/generationPredict";
	}
	
	
	@GetMapping("/plant/plantList")
	public String getPlantList(Model model,HttpSession session) {
		String SLEVEL = (String) session.getAttribute("SLEVEL");
		String SID = (String) session.getAttribute("SID");
		System.out.println("???");
		if("태양광사업자".equals(SLEVEL) || "관리자".equals(SLEVEL)) {
			System.out.println("???");
			List<BusinessPlantDTO> plantListById = plantService.getPlantListById(SID);
			model.addAttribute("plantListById", plantListById);
			return "/plant/plantList";
		}
		System.out.println("???");
		return "main";
	}
	
	
}
