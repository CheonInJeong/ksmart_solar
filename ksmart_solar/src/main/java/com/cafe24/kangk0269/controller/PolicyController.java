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

import com.cafe24.kangk0269.common.SavePaging;
import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.StandardDTO;
import com.cafe24.kangk0269.serivce.PolicyService;

@Controller
public class PolicyController {
	
	@Autowired
	private final PolicyService policyService;
	
	SavePaging savePaging = null;
	
	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
	}
	
	//파일 다운로드
	@GetMapping("/policy/downloadFile")
	public void downloadBoardFile(@RequestParam(value="idx") int idx,
								  HttpServletResponse response,
								  HttpServletRequest request) throws Exception{
		FileDTO fileDto = policyService.getFileInfo(idx);
		
		if(ObjectUtils.isEmpty(fileDto)==false) {
			String fileName = fileDto.getOriginalFileName();
			System.out.println(fileName);
			String filePath = fileDto.getStoredFilePath();
			System.out.println(filePath);
			String fileBasePath=request.getSession().getServletContext().getRealPath("/");
			
			//실제 저장되어 있는 파일을 읽어 온 후 byte 형식으로 변환
			byte[] files = FileUtils.readFileToByteArray(new File(fileBasePath + fileDto.getStoredFilePath()));
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
								  HttpSession session,
								  @RequestParam(value="searchKey", required = false) String searchKey, 
								  @RequestParam(value="searchValue", required = false) String searchValue,
								  @RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
								  @RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
								  @RequestParam(value = "pageSize", required = false) 			String pageSize,
								  @RequestParam(value = "state", required = false) 				String state) {
		
		
		StandardDTO standardDTO = new StandardDTO();
		standardDTO.setState(1);
		if(savePaging==null || state ==null) {
			savePaging = new SavePaging(1,session);
			savePaging.setPaging(1,1,5,5);
		}
		
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage), Integer.parseInt(pageSize));
		}
		
		savePaging.getPaging(standardDTO);
		
		if(searchKey != null && searchKey.equals("null")) {
			searchKey = null;
		}
		if(searchValue != null && searchValue.equals("null")) {
			searchValue = null;
		}
		model.addAttribute("tradeHistory", policyService.getTradeHistory());
		model.addAttribute("reservation", policyService.getTradeReservation());
		model.addAttribute("notUsed", policyService.getTradeNotUsed(searchKey,searchValue,standardDTO));
		model.addAttribute("standardDTO", standardDTO);
		return "/policy/tradeHistory";
	}
	
	
	@GetMapping("/policy/commissionHistory")
	public String getCommissionHistory(Model model,
									   HttpSession session,
									   @RequestParam(value="searchKey", required = false) String searchKey, 
									   @RequestParam(value="searchValue", required = false) String searchValue,
									   @RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
									   @RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
									   @RequestParam(value = "pageSize", required = false) 			String pageSize,
									   @RequestParam(value = "state", required = false) 				String state) {
		
		StandardDTO standardDTO = new StandardDTO();
		standardDTO.setState(1);
		if(savePaging==null || state ==null) {
			savePaging = new SavePaging(1,session);
			savePaging.setPaging(1,1,5,5);
		}
		
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage), Integer.parseInt(pageSize));
		}
		
		savePaging.getPaging(standardDTO);
		
		if(searchKey != null && searchKey.equals("null")) {
			searchKey = null;
		}
		if(searchValue != null && searchValue.equals("null")) {
			searchValue = null;
		}
		model.addAttribute("commissionHistory", policyService.getCommissionHistory());
		model.addAttribute("reservation", policyService.getCommissionReservation());
		model.addAttribute("notUsed", policyService.getCommissionNotUsed(searchKey,searchValue,standardDTO));
		model.addAttribute("standardDTO", standardDTO);
		return "/policy/commissionHistory";
	}
	
	
	@GetMapping("/policy/depositHistory")
	public String getDepositHistory(Model model,
									HttpSession session,
									@RequestParam(value="searchKey", required = false) String searchKey, 
									@RequestParam(value="searchValue", required = false) String searchValue,
									@RequestParam(value = "currentPageNo", required = false) 		String currentPageNo,
									@RequestParam(value = "recordsPerPage", required = false) 		String recordsPerPage,
									@RequestParam(value = "pageSize", required = false) 			String pageSize,
									@RequestParam(value = "state", required = false) 				String state) {
		
		StandardDTO standardDTO = new StandardDTO();
		standardDTO.setState(1);
		if(savePaging==null || state ==null) {
			savePaging = new SavePaging(1,session);
			savePaging.setPaging(1,1,5,5);
		}
		
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage), Integer.parseInt(pageSize));
		}
		
		savePaging.getPaging(standardDTO);
		
		if(searchKey != null && searchKey.equals("null")) {
			searchKey = null;
		}
		if(searchValue != null && searchValue.equals("null")) {
			searchValue = null;
		}
		model.addAttribute("depositHistory", policyService.getDepositHistory());
		model.addAttribute("reservation", policyService.getDepositReservation());
		model.addAttribute("notUsed", policyService.getDepositNotUsed(searchKey,searchValue,standardDTO));
		model.addAttribute("standardDTO", standardDTO);
		return "/policy/depositHistory";
	}
	
	
	
	@GetMapping("/policy/removeTrade")
	public String removeTrade(@RequestParam(value="idx") int idx) {
		policyService.removeTrade(idx);
		return "redirect:/policy/tradeHistory";
	}
	
	@GetMapping("/policy/removeDeposit")
	public String removeDeposit(@RequestParam(value="idx") int idx) {
		policyService.removeDeposit(idx);
		return "redirect:/policy/depositHistory";
	}
	
	
	@GetMapping("/policy/removeCommission")
	public String removeCommission(@RequestParam(value="idx") int idx) {
		policyService.removeCommission(idx);
		return "redirect:/policy/commissionHistory";
	}
	
	@PostMapping("/policy/addNewCommission")
	public String addNewCommssion(StandardDTO standardDto, HttpSession session) {
		standardDto.setmId((String)session.getAttribute("SID"));
		policyService.addNewCommission(standardDto);
		return "redirect:/policy/commissionHistory";
	}
	
	@PostMapping("/policy/addNewTrade")
	public String addNewTrade(StandardDTO standardDto, HttpSession session) {
		standardDto.setmId((String)session.getAttribute("SID"));
		policyService.addNewTrade(standardDto);
		return "redirect:/policy/tradeHistory";
	}

	@RequestMapping(value="/ajax/commissionCheck", method=RequestMethod.POST)
	public @ResponseBody boolean commissionCheck(@RequestParam(value="commissionPrice", required = false) int commissionPrice) {
		boolean checkResult = false;
		System.out.println(commissionPrice);
		
			List<StandardDTO> commissionList = policyService.getCommissionPolicy();
			for(int i=0; i<commissionList.size(); i++) {
				if(commissionPrice==commissionList.get(i).getsCommissionType()) {
					System.out.println(commissionList.get(i).getsCommissionType() + "::동일한 기준 존재");
					checkResult = true;
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
		
		return "redirect:/policy/depositHistory";
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
												 @RequestParam(value="type") int type,
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
