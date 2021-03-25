package com.cafe24.kangk0269.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.kangk0269.dto.BusinessDTO;


@Controller
public class BusinessController {

	//사업자 등록(일반사업자)
	@RequestMapping(value="/ajax/recycleEntrepreneur", method = RequestMethod.POST)
	public @ResponseBody boolean addRecycleEntrepreneur(
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
		System.out.println(bzLicense);
		
		
		BusinessDTO bs = new BusinessDTO();
		
		String MId = (String) session.getAttribute("SID");
		
		if(MId == null || bzCompanyName == null || bzCeoName == null) {
			return checkResult;
		}
		
		//
		bs.setBzCode("");
		bs.setMId(MId);
		bs.setBzCompanyName(bzCompanyName);
		bs.setBzCeoName(bzCeoName);
		bs.setBzZipcode(bzZipcode);
		bs.setBzAddr(bzAddr);
		bs.setBzDetailAddr(bzDetailAddr);
		bs.setBzPlace(bzAddr + " " + bzDetailAddr);
		bs.setBzLicense(bzLicense);
		bs.setBzType("일반사업자");
		/*
		private String bzCode;
		private String mId;
		private String bzCompanyName;
		private String bzCeoName;
		private String bzZipcode;
		private String bzAddr;
		private String bzDetailAddr;
		private String bzPlace;
		private String bzLicense;
		private String bzType;
		private String bzRequestDate;
		 * */
		
		return checkResult;
	}
	
	//사업자 등록(태양광사업자)
	
	
}
