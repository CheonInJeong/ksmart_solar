package com.cafe24.kangk0269.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.kangk0269.dto.BusinessDTO;
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
	
	/*
	 * //사업자 등록(태양광사업자)
	 * 
	 * @RequestMapping(value="/ajax/solarEntrepreneur", method = RequestMethod.POST)
	 * public @ResponseBody int addsolarEntrepreneur(
	 * 
	 * @RequestParam(value="bzCompanyName", required = false) String bzCompanyName
	 * ,@RequestParam(value="bzCeoName", required = false) String bzCeoName
	 * ,@RequestParam(value="bzZipcode", required = false) String bzZipcode
	 * ,@RequestParam(value="bzAddr", required = false) String bzAddr
	 * ,@RequestParam(value="bzDetailAddr", required = false) String bzDetailAddr
	 * ,@RequestParam(value="bzLicense", required = false) String bzLicense
	 * ,HttpSession session) { boolean checkResult = false;
	 * 
	 * System.out.println(bzCompanyName); System.out.println(bzCeoName);
	 * System.out.println(bzZipcode); System.out.println(bzAddr);
	 * System.out.println(bzDetailAddr); System.out.println(bzLicense + " <<< 파일");
	 * 
	 * 
	 * BusinessDTO bs = new BusinessDTO();
	 * 
	 * String mId = (String) session.getAttribute("SID");
	 * 
	 * System.out.println(mId + " <<< mId");
	 * 
	 * if(mId == null || bzCompanyName == null || bzCeoName == null) { return 0; }
	 * 
	 * // bs.setBzCode(""); bs.setmId(mId); bs.setBzCompanyName(bzCompanyName);
	 * bs.setBzCeoName(bzCeoName); bs.setBzZipcode(bzZipcode); bs.setBzAddr(bzAddr);
	 * bs.setBzDetailAddr(bzDetailAddr); bs.setBzPlace(bzAddr + " " + bzDetailAddr);
	 * bs.setBzLicense(bzLicense); bs.setBzType("재활용 중고 사업자(구매자)");
	 * 
	 * 
	 * 
	 * return businessService.addRecycleEntrepreneur(bs); }
	 */
	
}
