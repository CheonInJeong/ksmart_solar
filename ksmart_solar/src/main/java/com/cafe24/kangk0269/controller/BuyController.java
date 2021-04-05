package com.cafe24.kangk0269.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.serivce.BidComponentService;
import com.cafe24.kangk0269.serivce.BidListService;
import com.cafe24.kangk0269.serivce.BidPlantService;

@Controller
public class BuyController {
	
	private final BidComponentService bidComponentService;
	private final BidPlantService bidPlantService;
	private final BidListService bidListService;
	
	@Autowired
	public BuyController(BidComponentService bidComponentService,
							BidPlantService bidPlantService
							,BidListService bidListService) {
		this.bidComponentService = bidComponentService; 
		this.bidPlantService = bidPlantService; 
		this.bidListService = bidListService; 
	}
	//내가 입찰한 공고
	@GetMapping("/buy/myHistory")
	public String MyHistory(HttpSession session, Model model) {
		System.out.println(session.getAttribute("SID"));
		String sId = (String) session.getAttribute("SID");
		if(sId != null) {
			List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponentMyBid(sId);
			List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlantMyBid(sId);
			if(bidComponentList!=null && bidComponentList.size()>1) {
				for(int i=0; i<bidComponentList.size();i++) {
					if(i!=0) {
						bidComponentList.get(i).setNum(bidComponentList.get(i-1).getBidListDTOList().size()+bidComponentList.get(i-1).getNum());
					}
				}
			}
			if(bidPlantList!=null && bidPlantList.size()>1) {
				for(int i=0; i<bidPlantList.size();i++) {
					if(i!=0) {
						bidPlantList.get(i).setNum(bidPlantList.get(i-1).getBidListDTOList().size()+bidPlantList.get(i-1).getNum());
					}
				}
			}
			if(bidPlantList!=null) {				
				model.addAttribute("bidPlantList", bidPlantList);
			}
			if(bidComponentList != null) {
				model.addAttribute("bidComponentList", bidComponentList);
			}
		}
		return "/buy/myHistory";
	}
	//예치금 환불 리스트
	@GetMapping("/buy/applyRefund")
	public String ApplyRefund(HttpSession session, Model model) {
		System.out.println(session.getAttribute("SID"));
		String id = (String) session.getAttribute("SID");
		if(id != null) {
			Map<String, List<BidListDTO>> refundList = bidListService.getApplyRefundList(id);
			model.addAttribute("refundList", refundList);
		}
		return "/buy/applyRefund";
	}
	
	@GetMapping("/buy/qna")
	public String Qna() {
		
		return "/buy/qna";
	}
	//예치금 출금 신청
	@PostMapping("/buy/refundRequest")
	public String refundRequest(String bCode,String bDeposit) {
		
		return "/buy/refundRequest";
	}
	
}
