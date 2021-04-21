package com.cafe24.kangk0269.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cafe24.kangk0269.api.KakaoLoginApi;
import com.cafe24.kangk0269.common.SavePaging;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.dto.MemberKakao;
import com.cafe24.kangk0269.dto.MemberLogDTO;
import com.cafe24.kangk0269.dto.MemberRevokeDTO;
import com.cafe24.kangk0269.dto.PageDTO;
import com.cafe24.kangk0269.serivce.AccountService;
import com.cafe24.kangk0269.serivce.BidComponentService;
import com.cafe24.kangk0269.serivce.BidPlantService;
import com.cafe24.kangk0269.serivce.BoardQnaService;
import com.cafe24.kangk0269.serivce.BusinessService;
import com.cafe24.kangk0269.serivce.MemberService;
import com.cafe24.kangk0269.serivce.NoticeService;
import com.cafe24.kangk0269.serivce.PlantService;
import com.cafe24.kangk0269.serivce.SellService;

@Controller
public class MemberManageController {
	private final MemberService memberService;
	private final BusinessService businessService;
	private final PlantService plantService;
	private final AccountService accountService;
	private final BidPlantService bidPlantService;
	private final BidComponentService bidComponentService;
	private final SellService sellService;
	SavePaging savePaging = null;
	
	@Autowired
	public MemberManageController(MemberService memberService, BusinessService businessService
								 ,PlantService plantService, AccountService accountService
								 ,BidPlantService bidPlantService, BidComponentService bidComponentService
								 ,SellService sellService) {
		this.memberService = memberService;
		this.businessService = businessService;
		this.plantService = plantService;
		this.accountService = accountService;
		this.bidPlantService = bidPlantService;
		this.bidComponentService = bidComponentService;
		this.sellService = sellService;
	}
	
	//공고신청 반려사유
	@GetMapping("/member/noticeRejectReason")
	public String noticeCheckReason(Model model
									,@RequestParam(name="bPlCode", required=false) String bPlCode
									,@RequestParam(name="bCpCode", required=false) String bCpCode) {
		System.out.println(bPlCode);
		System.out.println(bCpCode);
		model.addAttribute("bPlCode", bPlCode);
		model.addAttribute("bCpCode", bCpCode);
		return "/member/noticeRejectReason";
	}
	
	//공고신청 반려처리
	@PostMapping("/ajax/noticeReject")
	public String noticeReturn(@RequestParam(name="bPlCode", required=false) String bPlCode
								,@RequestParam(name="bCpCode", required=false) String bCpCode
								,@RequestParam(name="bCpCode", required=false) String bPlRejectReason
								,@RequestParam(name="bCpCode", required=false) String bCpRejectReason) {
		if(bPlCode != null) {
			bidPlantService.bidPlantReturn(bPlCode,bPlRejectReason);
		}else if(bCpCode != null) {
			bidComponentService.bidComponentReturn(bCpCode,bCpRejectReason);
		}
		return "/member/getNotice";
	}
	
	//공고신청 승인처리
	@PostMapping("/ajax/noticeAdmit")
	public String noticeAdmit(@RequestParam(name="bPlCode", required=false) String bPlCode
							,@RequestParam(name="bCpCode", required=false) String bCpCode) {
		if(bPlCode != null) {
			bidPlantService.bidPlantAdmit(bPlCode);
		}else if(bCpCode != null) {
			bidComponentService.bidComponentAdmit(bCpCode);
		}
		return "/member/getNotice";
	}
	
	//공고신청 상세보기
	@GetMapping("/member/getNotice")
	public String getNotice(Model model
							,@RequestParam(name="bPlCode", required=false) String bPlCode
							,@RequestParam(name="bCpCode", required=false) String bCpCode) {
		if(bPlCode != null) {
			BidPlantDTO bidPlantdto = bidPlantService.getNotice(bPlCode);
			BusinessPlantDTO businessPlantDTO = bidPlantService.getPlant(bPlCode);
			model.addAttribute("bidPlantdto", bidPlantdto);
			model.addAttribute("businessPlantDTO", businessPlantDTO);
			System.out.println(bPlCode);
			
		}else if(bCpCode != null) {
			System.out.println(bCpCode);
			BidComponentDTO bidComponentdto = bidComponentService.getNotice(bCpCode);
			ComponentDTO componentDTO = bidComponentService.getComponent(bidComponentdto.getCpCode());
			model.addAttribute("bidComponentdto", bidComponentdto);
			model.addAttribute("componentDTO", componentDTO);
		}
		return "/member/getNotice";
	}
	
	
	//공고승인신청 화면
	@GetMapping("/member/noticeList")
	public String noticeList(Model model
							, @RequestParam(name="searchKey", required=false) String searchKey
							, @RequestParam(name="searchValue", required=false) String searchValue
							, @RequestParam(name="curPage", required=false, defaultValue="1") int curPage) {
			    
		int countPlant = bidPlantService.getPlantNoticeAdmitListCnt(searchKey, searchValue);
		int countComponent = bidComponentService.getComponentNoticeAdmitListCnt(searchKey, searchValue);
		int countSum = (countPlant + countComponent);
		
		PageDTO page = new PageDTO(countSum ,curPage);
		int start = page.getPageBegin();
		int end = page.getPageEnd();
		
		List<BidPlantDTO> bidPlantList = bidPlantService.getPlantNoticeAdmitList(start, end, searchKey, searchValue);
		List<BidComponentDTO> bidComponentList = bidComponentService.getComponentNoticeAdmitList(start, end, searchKey, searchValue);
		
		model.addAttribute("bidPlantList", bidPlantList);
		model.addAttribute("bidComponentList", bidComponentList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("page", page);
		return "/member/noticeList";
	}
	
	
	
	@GetMapping("/getMemberInfoById")
	public String getMemberInfoById(Model model,HttpSession session
									, @RequestParam(value="mId", required=false) String mId
									, @RequestParam(name="searchKeyAM", required=false) String searchKeyAM
									, @RequestParam(name="searchValueAM", required=false) String searchValueAM
									, @RequestParam(name="searchValueAMS", required=false) String searchValueAMS
									, @RequestParam(name="searchValueAMF", required=false) String searchValueAMF
									, @RequestParam(name="searchKeyRM", required=false) String searchKeyRM
									, @RequestParam(name="searchValueRM", required=false) String searchValueRM
									, @RequestParam(name="searchValueRMS", required=false) String searchValueRMS
									, @RequestParam(name="searchValueRMF", required=false) String searchValueRMF
									, @RequestParam(name="searchKeyWAM", required=false) String searchKeyWAM
									, @RequestParam(name="searchValueWAM", required=false) String searchValueWAM
									, @RequestParam(name="searchValueWAMS", required=false) String searchValueWAMS
									, @RequestParam(name="searchValueWAMF", required=false) String searchValueWAMF
									, @RequestParam(name="searchKeyWCM", required=false) String searchKeyWCM
									, @RequestParam(name="searchValueWCM", required=false) String searchValueWCM
									, @RequestParam(name="searchValueWCMS", required=false) String searchValueWCMS
									, @RequestParam(name="searchValueWCMF", required=false) String searchValueWCMF
									, @RequestParam(name="uri", required=false) String uri
									, @RequestParam(name="curPage1", required=false, defaultValue="1") int curPage1
									, @RequestParam(name="curPage2", required=false, defaultValue="1") int curPage2
									, @RequestParam(value = "currentPageNo", required = false) String currentPageNo
									, @RequestParam(value = "recordsPerPage", required = false) String recordsPerPage
									, @RequestParam(value = "pageSize", required = false) String pageSize
									, @RequestParam(value = "state", required = false) String state) throws Exception {
		String SLEVEL = (String) session.getAttribute("SLEVEL");
		BidPlantDTO bidPlantDTO = new BidPlantDTO();
		BidComponentDTO bidComponentDTO = new BidComponentDTO();
		bidPlantDTO.setState(1);
		bidComponentDTO.setState(2);
		if(savePaging==null || state==null) {
			//화면의 제일 처름 페이지 설정
			System.out.println("세이브페이징 만들어짐");
			savePaging = new SavePaging(2,session);
			savePaging.setPaging(1, 1, 5, 5);
			savePaging.setPaging(2, 1, 5, 5);
		}
		if(state!=null && currentPageNo!=null && recordsPerPage!=null && pageSize!=null) {
			//페이지가 넘어갈때 넘어간 페이지 저장
			savePaging.setPaging(Integer.parseInt(state), Integer.parseInt(currentPageNo), Integer.parseInt(recordsPerPage),Integer.parseInt(pageSize));
		}
		//페이지 저장 가져오기
		savePaging.getPaging(bidComponentDTO);
		savePaging.getPaging(bidPlantDTO);
		
		if("관리자".equals(SLEVEL)) {
			List<BusinessPlantDTO> plantListById = plantService.getPlantListById(mId);
			List<ComponentDTO> cd = memberService.getComponentListById(mId);
			List<BidComponentDTO> bidComponentList = bidComponentService.getBidComponentMyBid(mId,null,null,bidComponentDTO);
			List<BidPlantDTO> bidPlantList = bidPlantService.getBidPlantMyBid(mId,null,null,bidPlantDTO);
			List<BidPlantDTO> bidPlantList2  = sellService.getBidPlantbyId(mId,null,null,bidPlantDTO);
			List<BidComponentDTO> bidComponentList2 = sellService.getBidComponentById(mId,null,null,bidComponentDTO);
			model.addAttribute("plantListById", plantListById);
			model.addAttribute("componentListById", cd);
			model.addAttribute("bidPlantList", bidPlantList);
			model.addAttribute("bidPlantList2", bidPlantList2);
			model.addAttribute("bidPlantDTO", bidPlantDTO);
			model.addAttribute("bidComponentDTO", bidComponentDTO);
			model.addAttribute("bidComponentList", bidComponentList);
			model.addAttribute("bidComponentList2", bidComponentList2);
		}
		
		MemberDTO member = memberService.getMemberInfoById(mId);
		List<MemberAccountDTO> accountList = accountService.getAccountListById(mId);
		model.addAttribute("member", member);
		model.addAttribute("accountList", accountList);
		model.addAttribute("searchKeyAM", searchKeyAM);
		model.addAttribute("searchValueAM", searchValueAM);
		model.addAttribute("searchValueAMS", searchValueAMS);
		model.addAttribute("searchValueAMF", searchValueAMF);
		model.addAttribute("searchKeyRM", searchKeyRM);
		model.addAttribute("searchValueRM", searchValueRM);
		model.addAttribute("searchValueRMS", searchValueRMS);
		model.addAttribute("searchValueRMF", searchValueRMF);
		model.addAttribute("searchKeyWAM", searchKeyWAM);
		model.addAttribute("searchValueWAM", searchValueWAM);
		model.addAttribute("searchValueWAMS", searchValueWAMS);
		model.addAttribute("searchValueWAMF", searchValueWAMF);
		model.addAttribute("searchKeyWCM", searchKeyWCM);
		model.addAttribute("searchValueWCM", searchValueWCM);
		model.addAttribute("searchValueWCMS", searchValueWCMS);
		model.addAttribute("searchValueWCMF", searchValueWCMF);
		model.addAttribute("curPage1", curPage1);
		model.addAttribute("curPage2", curPage2);
		model.addAttribute("uri", uri);
		return "/member/getMemberInfoById";
		
	}
	
	@PostMapping("/modifyMember")
	public String modifyMember(MemberDTO member
							   , @RequestParam(name="searchKeyAM", required=false) String searchKeyAM
							   , @RequestParam(name="searchValueAM", required=false) String searchValueAM
							   , @RequestParam(name="searchValueAMS", required=false) String searchValueAMS
							   , @RequestParam(name="searchValueAMF", required=false) String searchValueAMF
							   , @RequestParam(name="searchKeyRM", required=false) String searchKeyRM
							   , @RequestParam(name="searchValueRM", required=false) String searchValueRM
							   , @RequestParam(name="searchValueRMS", required=false) String searchValueRMS
							   , @RequestParam(name="searchValueRMF", required=false) String searchValueRMF
							   , @RequestParam(name="curPage1", required=false, defaultValue="1") int curPage1
							   , @RequestParam(name="curPage2", required=false, defaultValue="1") int curPage2) {
		System.out.println("수정한 값 " + member);
		memberService.modifyMember(member);
		if(searchKeyAM != null) {
			return "redirect:/getMemberInfoById?mId=" + member.getmId() + "&curPage1=" + curPage1 + "&searchKeyAM=" + searchKeyAM + "&searchValueAM=" + searchValueAM +"&searchValueAMS=" + searchValueAMS +"&searchValueAMF=" + searchValueAMF + "&uri=/member/memberList";
			
		}else {
			return "redirect:/getMemberInfoById?mId=" + member.getmId() + "&curPage2=" + curPage2 + "&searchKeyRM=" + searchKeyRM + "&searchValueRM=" + searchValueRM + "&searchValueRMS=" + searchValueRMS +"&searchValueRMF=" + searchValueRMF + "&uri=/member/memberList";
		}
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(Model model
							   , @RequestParam(name="mId", required=false) String mId
							   , @RequestParam(name="searchKeyAM", required=false) String searchKeyAM
							   , @RequestParam(name="searchValueAM", required=false) String searchValueAM
							   , @RequestParam(name="searchValueAMS", required=false) String searchValueAMS
							   , @RequestParam(name="searchValueAMF", required=false) String searchValueAMF
							   , @RequestParam(name="searchKeyRM", required=false) String searchKeyRM
							   , @RequestParam(name="searchValueRM", required=false) String searchValueRM
							   , @RequestParam(name="searchValueRMS", required=false) String searchValueRMS
							   , @RequestParam(name="searchValueRMF", required=false) String searchValueRMF
							   , @RequestParam(name="curPage1", required=false, defaultValue="1") int curPage1
							   , @RequestParam(name="curPage2", required=false, defaultValue="1") int curPage2) {
		System.out.println("입력받은 아이디 : " + mId);
		MemberDTO member = memberService.getMemberInfoById(mId);
		System.out.println("회원정보조회 : " + member);
		model.addAttribute("member", member);
		model.addAttribute("searchKeyAM", searchKeyAM);
		model.addAttribute("searchValueAM", searchValueAM);
		model.addAttribute("searchValueAMS", searchValueAMS);
		model.addAttribute("searchValueAMF", searchValueAMF);
		model.addAttribute("searchKeyRM", searchKeyRM);
		model.addAttribute("searchValueRM", searchValueRM);
		model.addAttribute("searchValueRMS", searchValueRMS);
		model.addAttribute("searchValueRMF", searchValueRMF);
		model.addAttribute("curPage1", curPage1);
		model.addAttribute("curPage2", curPage2);
		model.addAttribute("uri", "/member/memberList");
		return "/member/modifyMember";
	}
	
	@GetMapping("/member/memberList")
	public String MemberList(Model model
							, @RequestParam(name="searchKeyAM", required=false) String searchKeyAM
							, @RequestParam(name="searchValueAM", required=false) String searchValueAM
							, @RequestParam(name="searchValueAMS", required=false) String searchValueAMS
							, @RequestParam(name="searchValueAMF", required=false) String searchValueAMF
							, @RequestParam(name="searchKeyRM", required=false) String searchKeyRM
							, @RequestParam(name="searchValueRM", required=false) String searchValueRM
							, @RequestParam(name="searchValueRMS", required=false) String searchValueRMS
							, @RequestParam(name="searchValueRMF", required=false) String searchValueRMF
							, @RequestParam(name="curPage1", required=false, defaultValue="1") int curPage1
							, @RequestParam(name="curPage2", required=false, defaultValue="1") int curPage2) {
		int count1 = memberService.getActiveMemberCnt(searchKeyAM, searchValueAM, searchValueAMS, searchValueAMF);
		PageDTO page1 = new PageDTO(count1,curPage1);
		int start1 = page1.getPageBegin();
		int end1 = page1.getPageEnd();
		List<MemberDTO> activememberList = memberService.getActiveMember(start1, end1, searchKeyAM, searchValueAM, searchValueAMS, searchValueAMF);
		int count2 = memberService.getRestMemberCnt(searchKeyRM, searchValueRM, searchValueRMS, searchValueRMF);
		PageDTO page2 = new PageDTO(count2,curPage2);
		int start2 = page2.getPageBegin();
		int end2 = page2.getPageEnd();
		List<MemberDTO> restmemberList = memberService.getRestMember(start2, end2, searchKeyRM, searchValueRM, searchValueRMS, searchValueRMF);
		String uri = "/member/memberList";
		model.addAttribute("activememberList", activememberList);
		model.addAttribute("restmemberList", restmemberList);
		model.addAttribute("searchKeyAM", searchKeyAM);
		model.addAttribute("searchValueAM", searchValueAM);
		model.addAttribute("searchValueAMS", searchValueAMS);
		model.addAttribute("searchValueAMF", searchValueAMF);
		model.addAttribute("searchKeyRM", searchKeyRM);
		model.addAttribute("searchValueRM", searchValueRM);
		model.addAttribute("searchValueRMS", searchValueRMS);
		model.addAttribute("searchValueRMF", searchValueRMF);
		model.addAttribute("page1", page1);
		model.addAttribute("page2", page2);
		model.addAttribute("uri", uri);
		return uri;
	}
	
	@GetMapping("/member/memberLoginHistory")
	public String MemberLoginHistory(Model model
									, @RequestParam(name="searchKeyL", required=false) String searchKeyL
									, @RequestParam(name="searchValueL", required=false) String searchValueL
									, @RequestParam(name="searchValueLS", required=false) String searchValueLS
									, @RequestParam(name="searchValueLF", required=false) String searchValueLF
									, @RequestParam(name="curPage", required=false, defaultValue="1") int curPage) {
		System.out.println("로그인 기록 카테고리 : " + searchKeyL);
		//리스트 개수
		int count = memberService.getLoginHistoryCnt(searchKeyL, searchValueL, searchValueLS, searchValueLF);
		System.out.println(count);
		//페이지 나누기
		PageDTO page = new PageDTO(count,curPage);
		int start = page.getPageBegin();
		int end = page.getPageEnd();
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		List<MemberLogDTO> loginHistoryList = memberService.getLoginHistory(start, end, searchKeyL, searchValueL, searchValueLS, searchValueLF);
		model.addAttribute("loginHistoryList", loginHistoryList);
		model.addAttribute("searchKeyL", searchKeyL);
		model.addAttribute("searchValueL", searchValueL);
		model.addAttribute("searchValueLS", searchValueLS);
		model.addAttribute("searchValueLF", searchValueLF);
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		return "/member/memberLoginHistory";
	}

	@GetMapping("/member/memberWithdrawList")
	public String MemberWithdrawList(Model model
									, @RequestParam(name="searchKeyWAM", required=false) String searchKeyWAM
									, @RequestParam(name="searchValueWAM", required=false) String searchValueWAM
									, @RequestParam(name="searchValueWAMS", required=false) String searchValueWAMS
									, @RequestParam(name="searchValueWAMF", required=false) String searchValueWAMF
									, @RequestParam(name="searchKeyWCM", required=false) String searchKeyWCM
									, @RequestParam(name="searchValueWCM", required=false) String searchValueWCM
									, @RequestParam(name="searchValueWCMS", required=false) String searchValueWCMS
									, @RequestParam(name="searchValueWCMF", required=false) String searchValueWCMF
									, @RequestParam(name="curPage1", required=false, defaultValue="1") int curPage1
									, @RequestParam(name="curPage2", required=false, defaultValue="1") int curPage2) {
		int count1 = memberService.getWithdrawAdmitMemberCnt(searchKeyWAM, searchValueWAM, searchValueWAMS, searchValueWAMF);
		PageDTO page1 = new PageDTO(count1,curPage1);
		int start1 = page1.getPageBegin();
		int end1 = page1.getPageEnd();
		List<MemberRevokeDTO> withdrawAdmitList = memberService.getWithdrawAdmitMember(start1, end1, searchKeyWAM, searchValueWAM, searchValueWAMS, searchValueWAMF);
		int count2 = memberService.getWithdrawCompleteMemberCnt(searchKeyWCM, searchValueWCM, searchValueWCMS, searchValueWCMF);
		PageDTO page2 = new PageDTO(count2,curPage2);
		int start2 = page2.getPageBegin();
		int end2 = page2.getPageEnd();
		List<MemberRevokeDTO> withdrawCompleteList = memberService.getWithdrawCompleteMember(start2, end2, searchKeyWCM, searchValueWCM, searchValueWCMS, searchValueWCMF);
		String uri = "/member/memberWithdrawList";
		model.addAttribute("withdrawAdmitList", withdrawAdmitList);
		model.addAttribute("withdrawCompleteList", withdrawCompleteList);
		model.addAttribute("searchKeyWAM", searchKeyWAM);
		model.addAttribute("searchValueWAM", searchValueWAM);
		model.addAttribute("searchValueWAMS", searchValueWAMS);
		model.addAttribute("searchValueWAMF", searchValueWAMF);
		model.addAttribute("searchKeyWCM", searchKeyWCM);
		model.addAttribute("searchValueWCM", searchValueWCM);
		model.addAttribute("searchValueWCMS", searchValueWCMS);
		model.addAttribute("searchValueWCMF", searchValueWCMF);
		model.addAttribute("page1", page1);
		model.addAttribute("page2", page2);
		model.addAttribute("uri", uri);
		return uri;
	}
	
	@GetMapping("/bzCheckReason")
	public String bzCheckReason(Model model
								,@RequestParam(name="bzCode", required=false) String bzCode) {
		System.out.println("반려할 사업자신청코드 : " + bzCode);
		BusinessDTO business = businessService.getBusinessInfoBybzCode(bzCode);
		model.addAttribute("business", business);
		return "/member/bzCheckReason";
	}
	
	@PostMapping("/ajax/businessReturnSend")
	public String businessReturnSend(BusinessDTO business) {
		System.out.println("반려사유 추가 : " + business );
		businessService.businessReturn(business);
		return "redirect:/bzCheckReason?bzCode=" + business.getBzCode();
	}
	
	@PostMapping("/ajax/businessAdmitSend")
	public String businessAdmitSend(@RequestParam(name="bzCode", required=false) String bzCode) {
		System.out.println("승인된 사업자신청코드 : " + bzCode);
		BusinessDTO business = businessService.getBusinessInfoBybzCode(bzCode);
		businessService.businessAdmit(business);
		MemberDTO member = new MemberDTO();
		member.setmId(business.getmId());
		if(business.getBzType() == "태양광사업자(판매자)") {
			member.setmLevel(2);
		}else {
			member.setmLevel(3);
		}
		memberService.modifyMember(member);
		return "redirect:/getBusinessInfoBybzCode?bzCode=" + business.getBzCode();
	}
	
	@GetMapping("/getBusinessInfoBybzCode")
	public String getBusinessInfoBybzCode(Model model
										  , @RequestParam(value="bzCode", required=false) String bzCode
										  , @RequestParam(name="searchKey", required=false) String searchKey
										  , @RequestParam(name="searchValue", required=false) String searchValue
										  , @RequestParam(name="curPage", required=false, defaultValue="1") int curPage) {
		System.out.println("사업자신청코드 : " + bzCode);
		BusinessDTO business = businessService.getBusinessInfoBybzCode(bzCode);
		System.out.println("코드조회결과 : " + business);
		model.addAttribute("business", business);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("curPage", curPage);
		return "/member/getBusinessInfoBybzCode";
	}
	
	@GetMapping("/member/businessList")
	public String BusinessList(Model model
							   , @RequestParam(name="searchKey", required=false) String searchKey
							   , @RequestParam(name="searchValue", required=false) String searchValue
							   , @RequestParam(name="curPage", required=false, defaultValue="1") int curPage) {
		int count = businessService.getAllBusinessAdmitListCnt(searchKey, searchValue);
		PageDTO page = new PageDTO(count,curPage);
		int start = page.getPageBegin();
		int end = page.getPageEnd();
		List<BusinessDTO> businessList = businessService.getAllBusinessAdmitList(start, end, searchKey, searchValue);
		System.out.println(businessList);
		model.addAttribute("businessList", businessList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("page", page);
		return "/member/businessList";
	}
	
	@PostMapping("/ajax/plantReturnSend")
	public String plantReturnSend(@RequestParam(name="bzPlCode", required=false) String bzPlCode) {
		System.out.println("반려된 사업자신청코드 : " + bzPlCode);
		BusinessPlantDTO plant = plantService.getPlantInfoBybzPlCode(bzPlCode);
		plantService.plantReturn(plant);
		return "redirect:/getPlantInfoBybzPlCode?bzPlCode=" + plant.getBzPlCode();
	}
	
	@PostMapping("/ajax/plantAdmitSend")
	public String plantAdmitSend(@RequestParam(name="bzPlCode", required=false) String bzPlCode) {
		System.out.println("승인된 사업자신청코드 : " + bzPlCode);
		BusinessPlantDTO plant = plantService.getPlantInfoBybzPlCode(bzPlCode);
		plantService.plantAdmit(plant);
		return "redirect:/getPlantInfoBybzPlCode?bzPlCode=" + plant.getBzPlCode();
	}
	
	@GetMapping("/getPlantInfoBybzPlCode")
	public String getPlantInfoBybzPlCode(Model model
										  , @RequestParam(value="bzPlCode", required=false) String bzPlCode
										  , @RequestParam(name="searchKey", required=false) String searchKey
										  , @RequestParam(name="searchValue", required=false) String searchValue
										  , @RequestParam(name="curPage", required=false, defaultValue="1") int curPage) {
		System.out.println("발전소사업자코드 : " + bzPlCode);
		BusinessPlantDTO plant = plantService.getPlantInfoBybzPlCode(bzPlCode);
		System.out.println("코드조회결과 : " + plant);
		model.addAttribute("plant", plant);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("curPage", curPage);
		return "/member/getPlantInfoBybzPlCode";
	}
	
	@GetMapping("/member/plantList")
	public String PlantList(Model model
							, @RequestParam(name="searchKey", required=false) String searchKey
						    , @RequestParam(name="searchValue", required=false) String searchValue
						    , @RequestParam(name="curPage", required=false, defaultValue="1") int curPage) {
		int count = plantService.getAllPlantAdmitListCnt(searchKey, searchValue);
		PageDTO page = new PageDTO(count,curPage);
		int start = page.getPageBegin();
		int end = page.getPageEnd();
		List<BusinessPlantDTO> plantList = plantService.getAllPlantAdmitList(start, end, searchKey, searchValue);
		model.addAttribute("plantList", plantList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("page", page);
		return "/member/plantList";
	}
	
	@GetMapping("/member/account")
	public String Account(Model model
						  , @RequestParam(name="searchKey", required=false) String searchKey
						  , @RequestParam(name="searchValue", required=false) String searchValue
						  , @RequestParam(name="curPage", required=false, defaultValue="1") int curPage) {
		int count = memberService.getAllBankAccountCnt(searchKey, searchValue);
		PageDTO page = new PageDTO(count,curPage);
		int start = page.getPageBegin();
		int end = page.getPageEnd();
		List<MemberAccountDTO> allBankAccountList = memberService.getAllBankAccount(start, end, searchKey, searchValue);
		model.addAttribute("allBankAccountList", allBankAccountList);
		model.addAttribute("searchKey", searchKey);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("page", page);
		return "/member/account";
	}
	
	
	//회원아이디 중복검사
	@RequestMapping(value="/ajax/idCheck", method = RequestMethod.POST)
	public @ResponseBody boolean idCheck(@RequestParam(value="mId", required = false) String mId) {
		boolean checkResult = false;
		
		if(mId != null && !"".equals(mId)) {			
			MemberDTO member = memberService.getMemberInfoById(mId);
			if(member != null) {
				checkResult = true;
			}
		}
		return checkResult;
	}
		
	//회원가입
	@RequestMapping(value="/ajax/signup", method = RequestMethod.POST)
	public @ResponseBody boolean addMember(
							 @RequestParam(value="memberId", required = false)		 	String mId
							,@RequestParam(value="memberPassword", required = false) 	String mPw
							,@RequestParam(value="postcode", required = false) 			String mZipcode
							,@RequestParam(value="roadAddress", required = false) 		String mAddr
							,@RequestParam(value="detailAddress", required = false) 	String mDetailAddr
							,@RequestParam(value="memberEmail", required = false) 		String mEmail
							,@RequestParam(value="memberName", required = false) 		String mName
							,@RequestParam(value="memberPhoneSum", required = false) 	String mPhone
							,@RequestParam(value="inputFile", required = false) 		String mPhoto) {
		boolean checkResult = false;
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setmId(mId);
		memberDTO.setmPw(mPw);
		memberDTO.setmZipcode(mZipcode);
		memberDTO.setmAddr(mAddr);
		memberDTO.setmDetailAddr(mDetailAddr);
		memberDTO.setmEmail(mEmail);
		memberDTO.setmName(mName);
		memberDTO.setmPhone(mPhone);
		memberDTO.setmPhoto(mPhoto);
		System.out.println(memberDTO.toString());
		
		if(mId != null && !"".equals(mId)) {			
			memberService.addMember(memberDTO);
			checkResult = true;
		}
		return checkResult;
	}
	
	//로그인
	@RequestMapping(value="/ajax/login", method = RequestMethod.POST)
	public @ResponseBody String memberLogin(@RequestParam(value="mId", required = false) String mId
											,@RequestParam(value="mPw", required = false) String mPw
											,HttpSession session) {
		String checkResult = "";
		if(mId != null && !"".equals(mId)) {	
			MemberDTO member = memberService.getMemberInfoById(mId);
			String resultId = member.getmId();
			String resultPw = member.getmPw();
			String resultName = member.getmName();
			String resultLevel = member.getmLevelName();
			if(resultId == null) {
				checkResult = "아이디오류";
				return checkResult;
			}
			if(mPw.equals(resultPw)){
				checkResult = "로그인완료";
				
				//아이디
				session.setAttribute("SID", resultId);
				//권한
				session.setAttribute("SLEVEL", resultLevel);
				session.setAttribute("SLEVELNUM", member.getmLevel());
				//이름
				session.setAttribute("SNAME", resultName);	
				session.setAttribute("SPHOTO", member.getmPhoto());	
				//메뉴
				session.setAttribute("SURI", memberService.getUri(member.getmLevel()));
				//로그인 기록 등록
				memberService.addLoginHistory(resultId);
				
			}else {
				checkResult = "비번불일치";
			}
		}
		return checkResult;
	}
	
	//카카오 로그인
	@RequestMapping(value="/ajax/loginKakao", method = RequestMethod.POST)
	public @ResponseBody String memberLoginKakao(@RequestParam(value="accessToken", required = false)  String accessToken
												,@RequestParam(value="refrechToken", required = false) String refrechToken
												,HttpSession session) {
		System.out.println(accessToken + " <<< accessToken");
		System.out.println(refrechToken + " <<< refrechToken");
		
		String checkResult = "";
		if(accessToken != null && !"".equals(accessToken)) {	
			MemberDTO md = memberService.memberLoginKakao(accessToken);
			if(md != null) {
				String resultId = md.getmId();
				String resultName = md.getmName();
				int resultLevel = md.getmLevel();
				String mLevel = "";
				System.out.println(resultLevel + " <<<<<<<<<<< resultLevel");
				if(resultLevel == 1) {
					mLevel = "관리자";
				}
				if(resultLevel == 2) {
					mLevel = "태양광사업자";
				}
				if(resultLevel == 3) {
					mLevel = "재활용사업자";
				}
				if(resultLevel == 4) {
					mLevel = "일반회원";
				}
				session.setAttribute("SID", resultId);
				session.setAttribute("SPHOTO", md.getmPhoto());
				session.setAttribute("SLEVEL", mLevel);
				session.setAttribute("SNAME", resultName);
				session.setAttribute("accessToken", accessToken);
				checkResult = "성공";
				//로그인 기록 등록
				memberService.addLoginHistory("kakao"+resultId);
			} else {
				checkResult = "실패";
			}
		}
		return checkResult;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//로그아웃시 로그인 기록 업데이트
		memberService.modifyLoginHistory((String)session.getAttribute("SID"));
		session.invalidate();
		return "redirect:/login";
	}
	
	// 카카오로그아웃
	@GetMapping("/logoutKakao")
	public String logoutKakao(HttpSession session) {
		KakaoLoginApi kakaoApi = new KakaoLoginApi();
		kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
		//로그아웃시 로그인 기록 업데이트
		memberService.modifyLoginHistory("kakao"+(String)session.getAttribute("SID"));
		session.invalidate();
		return "redirect:/login";
	}
	
}
