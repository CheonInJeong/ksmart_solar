package com.cafe24.kangk0269.controller;

import java.text.ParseException;

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

import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;
import com.cafe24.kangk0269.serivce.ComponentService;

@Controller
public class ComponentController {
	
	@Autowired
	private ComponentService componentService;
	
	@GetMapping("/plant/componentList")
	public String ComponentList(Model model ,HttpSession session) {
		componentService.getComponentListById(model, session);
		return "/plant/componentList";
	}
	@GetMapping("/plant/addComponent")
	public String addComponent(Model model ,HttpSession session) {
		return "/plant/addComponent";
	}
	
	  @RequestMapping(value="/ajax/addComponent", method = RequestMethod.POST)
	  public @ResponseBody int addComponent(
									  @RequestParam(value="cpName", required = false) 		String cpName
									  ,@RequestParam(value="cpPhoto", required = false) 	String cpPhoto
									  ,@RequestParam(value="cpInfo", required = false) 		String cpInfo
									  ,@RequestParam(value="cpMaker", required = false) 	String cpMaker
									  ,@RequestParam(value="cpMakedate", required = false) 	String cpMakedate
									  ,@RequestParam(value="cpUsedate", required = false) 	String cpUsedate
									  ,HttpSession session) throws ParseException { 
		  ComponentDTO cp = new ComponentDTO();
		  String mId = (String) session.getAttribute("SID");
		  cp.setmId(mId);
		  cp.setCpName(cpName);
		  cp.setCpPhoto(cpPhoto);
		  cp.setCpInfo(cpInfo);
		  cp.setCpMaker(cpMaker);
		  cp.setCpMakedate(cpMakedate);
		  cp.setCpUsedate(cpUsedate);
		  cp.setCpInfo(cpInfo);
		  return componentService.addComponent(cp);
	  }
	  
	  @RequestMapping(value="/ajax/componentModify", method = RequestMethod.POST)
	  public @ResponseBody int componentModify(
			  @RequestParam(value="cpCode", required = false) 		String cpCode
			  ,@RequestParam(value="cpName", required = false) 		String cpName
			  ,@RequestParam(value="cpPhoto", required = false) 	String cpPhoto
			  ,@RequestParam(value="cpInfo", required = false) 		String cpInfo
			  ,@RequestParam(value="cpMaker", required = false) 	String cpMaker
			  ,@RequestParam(value="cpMakedate", required = false) 	String cpMakedate
			  ,@RequestParam(value="cpUsedate", required = false) 	String cpUsedate
			  ,HttpSession session) throws ParseException { 
		  ComponentDTO cp = new ComponentDTO();
		  String mId = (String) session.getAttribute("SID");
		  cp.setmId(mId);
		  cp.setCpCode(cpCode);
		  cp.setCpName(cpName);
		  cp.setCpPhoto(cpPhoto);
		  cp.setCpInfo(cpInfo);
		  cp.setCpMaker(cpMaker);
		  cp.setCpMakedate(cpMakedate);
		  cp.setCpUsedate(cpUsedate);
		  cp.setCpInfo(cpInfo);
		  return componentService.componentModify(cp);
	  }
	  
	  @RequestMapping(value="/ajax/deleteComponent", method = RequestMethod.POST)
	  public @ResponseBody int deleteComponent(
			  @RequestParam(value="cpCode", required = false)String cpCode) throws ParseException { 
		  return componentService.deleteComponent(cpCode);
	  }

	@PostMapping("/plant/modifyComponent")
	public String plantDetail(Model model 
							  ,@RequestParam(name="cpCode", required=false) String cpCode) throws ParseException {
		componentService.getComponentListByCode(model, cpCode);
		return "/plant/componentModify";
	}
	
	
	
}
