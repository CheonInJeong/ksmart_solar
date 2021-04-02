package com.cafe24.kangk0269.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.api.CrawlingApi;
import com.cafe24.kangk0269.common.DepreciationCalculate;
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
	public String benefitAnalysis(HttpSession session, Model model) throws ParseException {
		String bzCode = (String)session.getAttribute("SBZCODE");
		plantService.noResidualValue(bzCode);
		if(bzCode != null) {
			plantService.getBenefitAnalysis(model, bzCode);
			return "/plant/benefitAnalysis";
		}
		return "main";
	}
	
	
	//발전량 분석
	@GetMapping("/plant/plantDetail/generationAnalysis")
	public String generationAnalysis(HttpSession session, Model model) {
		String bzCode = (String)session.getAttribute("SBZCODE");
		if(bzCode != null) {
			plantService.getPlantDetail(model, bzCode);
			return "/plant/generationAnalysis";
		}
		return "main";
	}
	
	
	
	@GetMapping("/plant/plantList")
	public String getPlantList(Model model,HttpSession session) {
		String SLEVEL = (String) session.getAttribute("SLEVEL");
		String SID = (String) session.getAttribute("SID");
		if("태양광사업자".equals(SLEVEL) || "관리자".equals(SLEVEL)) {
			List<BusinessPlantDTO> plantListById = plantService.getPlantListById(SID);
			model.addAttribute("plantListById", plantListById);
			return "/plant/plantList";
		}
		return "main";
	}
	
	
}
