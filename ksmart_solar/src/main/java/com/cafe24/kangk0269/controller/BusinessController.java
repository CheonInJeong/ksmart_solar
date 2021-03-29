package com.cafe24.kangk0269.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;
import com.cafe24.kangk0269.serivce.BusinessService;


@Controller
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	//사업자 등록(일반사업자)
	@RequestMapping(value="/ajax/recycleEntrepreneur", method = RequestMethod.POST)
	public @ResponseBody int addRecycleEntrepreneur(
							 @RequestParam(value="bzCompanyName", required = false)	 	String bzCompanyName
							,@RequestParam(value="bzCeoName", required = false) 		String bzCeoName
							,@RequestParam(value="bzZipcode", required = false) 		String bzZipcode
							,@RequestParam(value="bzAddr", required = false) 			String bzAddr
							,@RequestParam(value="bzDetailAddr", required = false) 		String bzDetailAddr
							,@RequestParam(value="bzLicense", required = false) 		String bzLicense
							,HttpSession session) {
		boolean checkResult = false;
		
		System.out.println(bzCompanyName);
		System.out.println(bzCeoName);
		System.out.println(bzZipcode);
		System.out.println(bzAddr);
		System.out.println(bzDetailAddr);
		System.out.println(bzLicense + " <<< 파일");
		
		
		BusinessDTO bs = new BusinessDTO();
		
		String mId = (String) session.getAttribute("SID");
		
		System.out.println(mId + " <<< mId");
		
		if(mId == null || bzCompanyName == null || bzCeoName == null) {
			return 0;
		}
		
		//
		bs.setBzCode("");
		bs.setmId(mId);
		bs.setBzCompanyName(bzCompanyName);
		bs.setBzCeoName(bzCeoName);
		bs.setBzZipcode(bzZipcode);
		bs.setBzAddr(bzAddr);
		bs.setBzDetailAddr(bzDetailAddr);
		bs.setBzPlace(bzAddr + " " + bzDetailAddr);
		bs.setBzLicense(bzLicense);
		bs.setBzType("재활용 중고 사업자(구매자)");
		
		
		
		return businessService.addRecycleEntrepreneur(bs);
	}
	
	
	  //사업자 등록(태양광사업자)
	  @RequestMapping(value="/ajax/solarEntrepreneur", method = RequestMethod.POST)
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
									  ,HttpSession session) { 
	  
	  System.out.println(bzCompanyName); System.out.println(bzCeoName);
	  System.out.println(bzZipcode); System.out.println(bzAddr);
	  System.out.println(bzDetailAddr); System.out.println(bzLicense + " <<< 파일");
	  
	  
	  BusinessDTO bs = new BusinessDTO();
	  BusinessPlantDTO bp = new BusinessPlantDTO();
	  PlantDepreciationDTO pd = new PlantDepreciationDTO();
	  
	  String mId = (String) session.getAttribute("SID");
	  
	  
	  if(mId == null || bzCompanyName == null || bzCeoName == null) { return 0; }
	  
	  // 
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
	  bp.setMId(mId);
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
	  //구해야함
	  pd.setPlDepPriceBased(0);
	  pd.setPlDepPrice(plDepPrice);
	  pd.setPlDepStartDate(plDepStartDate);
	  pd.setPlDepBuyDate(plDepBuyDate);
	  pd.setPlDepMaintenance(plDepMaintenance);
	  pd.setPlDepUsed("");
	  pd.setPlDepServicelife(0);
	  
	  
	  
	  return businessService.addSolarEntrepreneur(bs, bp, pd); }
	 
	
}
