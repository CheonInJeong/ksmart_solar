package com.cafe24.kangk0269.controller;

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

import com.cafe24.kangk0269.dto.StandardDTO;
import com.cafe24.kangk0269.serivce.PolicyService;

@Controller
public class PolicyController {
	
	@Autowired
	private final PolicyService policyService;
	
	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
	}
	
	@PostMapping("/policy/addNewCommission")
	public String addNewCommssion(StandardDTO standardDto, HttpSession session) {
		standardDto.setmId((String)session.getAttribute("SID"));
		policyService.addNewCommission(standardDto);
		return "redirect:/policy/policyList";
	}
	
	@PostMapping("/policy/addNewTrade")
	public String addNewTrade(StandardDTO standardDto, HttpSession session) {
		standardDto.setmId((String)session.getAttribute("SID"));
		policyService.addNewTrade(standardDto);
		return "redirect:/policy/policyList";
	}

	@RequestMapping(value="/ajax/depositCheck", method = RequestMethod.POST)
	public @ResponseBody boolean depositCheck(@RequestParam(value="depositName", required = false) String depositName) {
		boolean checkResult = true;
		if(depositName!=null && !"".equals(depositName)) {
			List<StandardDTO> depositList = policyService.getDepositPolicy();
			for(int i = 0; i<depositList.size(); i++) {
				System.out.println(depositList.get(i).getsDepositType()+"<-------이미 존재하는 값");
				if(depositList.get(i).getsDepositType().equals(depositName)) {
					checkResult = false;
				}
			}
		}
		return checkResult;
	}
	
	
	@PostMapping("/policy/addNewDeposit")
	public String addNewDeposit(StandardDTO standardDto, HttpSession session) {
		
		standardDto.setmId((String)session.getAttribute("SID"));
		policyService.addNewDeposit(standardDto);
		
		return "redirect:/policy/policyList";
	}
	
	
	
	@RequestMapping(value="/ajax/modifyTrade",method = RequestMethod.POST)
	public @ResponseBody void modifyTrade(@RequestParam(value="idx") String idx,
										  @RequestParam(value="period") int period,
										  @RequestParam(value="type") String type,
										  HttpSession session) {
		StandardDTO standardDto = new StandardDTO();
		standardDto.setmId((String)session.getAttribute("SID"));
		standardDto.setsTradePeriod(period);
		standardDto.setsTradeType(type);
		System.out.println(idx+"<=---거래기간 인덱스");
		System.out.println(period+"<=---거래기간 ");
		System.out.println(type+"<=---거래기간 타입");
		policyService.modifyTrade(idx, standardDto);
		
	}
	
	@RequestMapping(value="/ajax/modifyCommission",method = RequestMethod.POST)
	public @ResponseBody void modifyCommission(@RequestParam(value="idx") String idx,
												 @RequestParam(value="rate") double rate,
												 @RequestParam(value="type") String type,
												 HttpSession session) {
		StandardDTO standardDto = new StandardDTO();
		standardDto.setmId((String)session.getAttribute("SID"));
		standardDto.setsCommissionRate(rate);
		standardDto.setsCommissionType(type);
		
		
		policyService.modifyCommission(idx, standardDto);
	
	}
	
	@RequestMapping(value="/ajax/modifyDeposit",method = RequestMethod.POST)
	public @ResponseBody String modifyDeposit(@RequestParam(value="idx") String idx , 
											  @RequestParam(value="rate") double rate,
											  @RequestParam(value="type") String type,
											  HttpSession session
											  ) {
		
		StandardDTO standardDto = new StandardDTO();
		standardDto.setsDepositRate(rate);
		standardDto.setsDepositType(type);
		standardDto.setmId((String)session.getAttribute("SID"));
		
		policyService.modifyDeposit(idx, standardDto);
		
		return "redirect:/policy/policyList";
	}
	@GetMapping("/policy/policyList")
	public String PolicyList(Model model) {
		model.addAttribute("commission", policyService.getCommissionPolicy());
		model.addAttribute("deposit", policyService.getDepositPolicy());
		model.addAttribute("trade", policyService.getTradePolicy());
	
		return "/policy/policyList";
	}
	
	@GetMapping("/policy/document")
	public String Document() {
		
		return "/policy/document";
	}

}
