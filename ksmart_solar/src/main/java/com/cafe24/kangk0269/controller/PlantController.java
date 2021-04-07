package com.cafe24.kangk0269.controller;

import java.text.ParseException;
import java.util.List;

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

import com.cafe24.kangk0269.api.CrawlingApi;
import com.cafe24.kangk0269.common.DepreciationCalculate;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;
import com.cafe24.kangk0269.serivce.BusinessService;
import com.cafe24.kangk0269.serivce.PlantService;

@Controller
public class PlantController {
	
	@Autowired
	private PlantService plantService;
	
	@Autowired
	private BusinessService businessService;
	
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
		plantService.residualValue(bzCode, model);
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
		System.out.println(bzCode);
		
		if(bzCode != null) {
			plantService.getGenerationAnalysisData(model, bzCode);
			
			
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
	
	@GetMapping("/plant/addPlant")
	public String addPlant(Model model,HttpSession session) {
		String SLEVEL = (String) session.getAttribute("SLEVEL");
		String SID = (String) session.getAttribute("SID");
		if("태양광사업자".equals(SLEVEL) || "관리자".equals(SLEVEL)) {
			List<BusinessPlantDTO> plantListById = plantService.getPlantListById(SID);
			model.addAttribute("plantListById", plantListById);
			return "/plant/addPlant";
		}
		return "/plant/addPlant";
	}
	
	@GetMapping("/plant/addComponent")
	public String addComponent(Model model,HttpSession session) {
		String SLEVEL = (String) session.getAttribute("SLEVEL");
		String SID = (String) session.getAttribute("SID");
		if("태양광사업자".equals(SLEVEL) || "관리자".equals(SLEVEL)) {
			List<BusinessPlantDTO> plantListById = plantService.getPlantListById(SID);
			model.addAttribute("plantListById", plantListById);
			return "/plant/plantList";
		}
		return "/plant/addComponent";
	}
	
	
	  //사업자 등록(태양광사업자, 석인)
	  @RequestMapping(value="/ajax/addPlant", method = RequestMethod.POST)
	  public @ResponseBody int addsolarEntrepreneur(
									  @RequestParam(value="bzCompanyName", required = false) 	String bzCompanyName
									  ,@RequestParam(value="bzCeoName", required = false) 		String bzCeoName
									  ,@RequestParam(value="bzZipcode", required = false) 		String bzZipcode
									  ,@RequestParam(value="bzAddr", required = false) 			String bzAddr
									  ,@RequestParam(value="bzDetailAddr", required = false) 	String bzDetailAddr
									  ,@RequestParam(value="bzLicense", required = false) 		String bzLicense
									  
									  ,@RequestParam(value="bzPlName", required = false) 		String bzPlName
									  ,@RequestParam(value="bzPlPhoto", required = false) 		String bzPlPhoto
									  ,@RequestParam(value="bzPlZipcode", required = false) 	String bzPlZipcode
									  ,@RequestParam(value="bzPlAddr", required = false) 		String bzPlAddr
									  ,@RequestParam(value="bzPlDetailAddr", required = false) 	String bzPlDetailAddr
									  ,@RequestParam(value="bzPlPower", required = false) 		int bzPlPower
									  ,@RequestParam(value="bzPlArea", required = false) 		int bzPlArea
									  ,@RequestParam(value="bzPlInvPower", required = false) 	int bzPlInvPower
									  ,@RequestParam(value="bzPlInvCount", required = false) 	int bzPlInvCount
									  ,@RequestParam(value="bzPlInvMaker", required = false) 	String bzPlInvMaker
									  ,@RequestParam(value="bzPlRec", required = false) 		int bzPlRec
									  ,@RequestParam(value="bzPlHardware", required = false) 	String bzPlHardware
									  
									  ,@RequestParam(value="plDepPrice", required = false) 		int plDepPrice
									  ,@RequestParam(value="plDepMaintenance", required = false)int plDepMaintenance
									  ,@RequestParam(value="plDepBuyDate", required = false) 	String plDepBuyDate
									  ,@RequestParam(value="plDepStartDate", required = false) 	String plDepStartDate
									  ,HttpSession session) throws ParseException { 
	  BusinessDTO bs = new BusinessDTO();
	  BusinessPlantDTO bp = new BusinessPlantDTO();
	  PlantDepreciationDTO pd = new PlantDepreciationDTO();
	  
	  String mId = (String) session.getAttribute("SID");
	  
	  if(mId == null || bzCompanyName == null || bzCeoName == null) { return 0; }
	  
	  
	  
	  
	  bs.setBzCode(""); 
	  bs.setmId(mId); 
	  bs.setBzCompanyName(bzCompanyName);
	  bs.setBzCeoName(bzCeoName);
	  bs.setBzZipcode(bzZipcode);
	  bs.setBzAddr(bzAddr);
	  bs.setBzDetailAddr(bzDetailAddr); 
	  bs.setBzPlace(bzAddr + " " + bzDetailAddr);
	  bs.setBzLicense(bzLicense); 
	  bs.setBzType("태양광사업자(판매자)");
	  
	  bp.setBzPlCode("");
	  bp.setmId(mId);
	  bp.setBzPlCheck("");
	  bp.setBzPlName(bzPlName);
	  bp.setBzPlPhoto(bzPlPhoto);
	  bp.setBzPlZipcode(bzPlZipcode);
	  bp.setBzPlAddr(bzPlAddr);
	  bp.setBzPlDetailAddr(bzPlDetailAddr);
	  bp.setBzPlPower(bzPlPower);
	  bp.setBzPlArea(bzPlArea);
	  bp.setBzPlInvPower(bzPlInvPower);
	  bp.setBzPlInvCount(bzPlInvCount);
	  bp.setBzPlInvMaker(bzPlInvMaker);
	  bp.setBzPlRec(bzPlRec);
	  bp.setBzPlHardware(bzPlHardware);
	  
	  pd.setBzPlCode("");
	  pd.setPlDepPriceBased(0);
	  pd.setPlDepPrice(plDepPrice);
	  pd.setPlDepStartDate(plDepStartDate);
	  pd.setPlDepBuyDate(plDepBuyDate);
	  pd.setPlDepMaintenance(plDepMaintenance);
	  pd.setPlDepUsed("");
	  pd.setPlDepServicelife(0);
	  
	  return businessService.addSolarEntrepreneur(bs, bp, pd); 
	  }
	  
}
