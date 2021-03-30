package com.cafe24.kangk0269.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.StandardDTO;
import com.cafe24.kangk0269.serivce.PolicyService;

@Controller
public class PolicyController {
	
	@Autowired
	private final PolicyService policyService;
	
	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
	}
	
	//파일 다운로드
	@GetMapping("/policy/downloadFile")
	public void downloadBoardFile(@RequestParam(value="idx") int idx,
								  HttpServletResponse response) throws Exception{
		System.out.println("boadService메서드 실행 ");
		FileDTO fileDto = policyService.getFileInfo(idx);
		System.out.println("boadService메서드 실행 완료");
		
		if(ObjectUtils.isEmpty(fileDto)==false) {
			String fileName = fileDto.getOriginalFileName();
			System.out.println(fileName);
			String filePath = fileDto.getStoredFilePath();
			System.out.println(filePath);
			
			//실제 저장되어 있는 파일을 읽어 온 후 byte 형식으로 변환
			byte[] files = FileUtils.readFileToByteArray(new File(fileDto.getStoredFilePath()));
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\""+URLEncoder.encode(fileName,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
		
	}
	
	
	
	//서류등록 버튼 눌렀을 때
	@GetMapping("/policy/document")
	public String Document(Model model) {
		//업로드 된 공고 서류 목록을 보여줌
		model.addAttribute("fileList", policyService.getNoticeFileList());
		model.addAttribute("bidFileList", policyService.getBidFileList());
		return "/policy/document";
	}
	
	//업로드 된 파일삭제
	@GetMapping("/policy/removeFile")
	public String removeFile(@RequestParam(value="idx") int idx) {
		policyService.removeFile(idx);
		return "redirect:/policy/document";
	}
	//입찰 서류 업로드
	@PostMapping("/policy/addBidFile")
	public String addBidFile(MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request)throws Exception{
		policyService.addBidFile(multipartHttpServletRequest, request);
		
		return "redirect:/policy/document";
	}
	
	
	//공고서류 업로드 
	@PostMapping("/policy/addNoticeFile")
	public String addNoticeFile(MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request)throws Exception{
		policyService.addNoticeFile(multipartHttpServletRequest, request);
		
		return "redirect:/policy/document";
	}
	
	
	@GetMapping("/policy/tradeHistory")
	public String getTradeHistory(Model model,
									@RequestParam(value="startDate",required = false) String startDate,
									@RequestParam(value="endDate", required = false) String endDate) {
		
		model.addAttribute("tradeHistory", policyService.getTradeHistory(startDate, endDate));
		
		return "/policy/tradeHistory";
	}
	
	
	@GetMapping("/policy/commissionHistory")
	public String getCommissionHistory(Model model,
									@RequestParam(value="startDate",required = false) String startDate,
									@RequestParam(value="endDate", required = false) String endDate) {
		
		model.addAttribute("commissionHistory", policyService.getCommissionHistory(startDate, endDate));
		
		return "/policy/commissionHistory";
	}
	
	
	@GetMapping("/policy/depositHistory")
	public String getDepositHistory(Model model,
									@RequestParam(value="startDate",required = false) String startDate,
									@RequestParam(value="endDate", required = false) String endDate) {
		
		model.addAttribute("depositHistory", policyService.getDepositHistory(startDate, endDate));
		
		return "/policy/depositHistory";
	}
	
	@GetMapping("/policy/removeCommission")
	public String removeCommission(@RequestParam(value="sCommissionIdx") int idx) {
		policyService.removeCommission(idx);
		return "redirect:/policy/policyList";
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

	@RequestMapping(value="/ajax/commissionCheck", method=RequestMethod.POST)
	public @ResponseBody boolean commissionCheck(@RequestParam(value="commssionPrice", required = false) String commssionPrice) {
		boolean checkResult = true;
		if(commssionPrice!=null && !"".equals(commssionPrice)) {
			List<StandardDTO> commissionList = policyService.getCommissionPolicy();
			for(int i=0; i<commissionList.size(); i++) {
				if(commssionPrice.equals(commissionList.get(i).getsCommissionType())) {
					checkResult = false;
				}
			}
		}
		return checkResult;
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
	


}
