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
	
	@GetMapping("/getMemberInfoById")
	public String getMemberInfoById(Model model
									,@RequestParam(value="mId", required=false) String mId) throws Exception {
		MemberDTO member = memberService.getMemberInfoById(mId);
		List<MemberAccountDTO> accountList = accountService.getAccountListById(mId);
		List<BusinessDTO> businessList = businessService.getBusinessInfoById(mId);
		List<BidPlantDTO> plantList = bidPlantService.getBidPlantById(mId);
		List<BusinessPlantDTO> operPlantList = plantService.getOperPlantListById(mId);
		List<ComponentDTO> componentList = sellService.getComponent(mId);
		model.addAttribute("member", member);
		model.addAttribute("accountList", accountList);
		model.addAttribute("businessList", businessList);
		model.addAttribute("plantList", plantList);
		model.addAttribute("operPlantList", operPlantList);
		model.addAttribute("componentList", componentList);
		return "/member/getMemberInfoById";
		
	}
	
	@PostMapping("/modifyMember")
	public String modifyMember(MemberDTO member) {
		System.out.println("수정한 값 " + member);
		memberService.modifyMember(member);
		return "redirect:/getMemberInfoById?mId=" + member.getmId();
	}
	
	@GetMapping("/modifyMember")
	public String modifyMember(Model model
							   ,@RequestParam(name="mId", required=false) String mId) {
		System.out.println("입력받은 아이디 : " + mId);
		MemberDTO member = memberService.getMemberInfoById(mId);
		System.out.println("회원정보조회 : " + member);
		model.addAttribute("member", member);
		return "/member/modifyMember";
	}
	
	@GetMapping("/member/memberList")
	public String MemberList(Model model
							, @RequestParam(name="searchKeyAM", required=false) String searchKeyAM
							, @RequestParam(name="searchValueAM", required=false) String searchValueAM
							, @RequestParam(name="searchKeyRM", required=false) String searchKeyRM
							, @RequestParam(name="searchValueRM", required=false) String searchValueRM) {
		System.out.println("활동카테고리 : " + searchKeyAM);
		System.out.println("활동검색내용 : " + searchValueAM);
		System.out.println("휴면카테고리 : " + searchKeyRM);
		System.out.println("휴면검색내용 : " + searchValueRM);
		List<MemberDTO> activememberList = memberService.getActiveMember(searchKeyAM, searchValueAM);
		List<MemberDTO> restmemberList = memberService.getRestMember(searchKeyRM, searchValueRM);
		System.out.println("활동회원조회 : " + activememberList);
		System.out.println("휴면회원조회 : " + restmemberList);
		model.addAttribute("activememberList", activememberList);
		model.addAttribute("restmemberList", restmemberList);
		return "/member/memberList";
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
									, @RequestParam(name="searchValueWCMF", required=false) String searchValueWCMF) {
		
		List<MemberRevokeDTO> withdrawAdmitList = memberService.getWithdrawAdmitMember(searchKeyWAM, searchValueWAM, searchValueWAMS, searchValueWAMF);
		List<MemberRevokeDTO> withdrawCompleteList = memberService.getWithdrawCompleteMember(searchKeyWCM, searchValueWCM, searchValueWCMS, searchValueWCMF);
		model.addAttribute("withdrawAdmitList", withdrawAdmitList);
		model.addAttribute("withdrawCompleteList", withdrawCompleteList);
		return "/member/memberWithdrawList";
	}
	
	@GetMapping("/bzCheckReason")
	public String bzCheckReason(Model model
								,@RequestParam(name="bzCode", required=false) String bzCode) {
		System.out.println("반려할 사업자신청코드 : " + bzCode);
		BusinessDTO business = businessService.getBusinessInfoBybzCode(bzCode);
		model.addAttribute("business", business);
		return "/member/bzCheckReason";
	}
	
	@PostMapping("/businessReturnSend")
	public String businessReturnSend(BusinessDTO business) {
		System.out.println("반려사유 추가 : " + business );
		businessService.businessReturn(business);
		return "redirect:/bzCheckReason?bzCode=" + business.getBzCode();
	}
	
	@PostMapping("/businessAdmitSend")
	public String businessAdmitSend(@RequestParam(name="bzCode", required=false) String bzCode) {
		System.out.println("승인된 사업자신청코드 : " + bzCode);
		BusinessDTO business = businessService.getBusinessInfoBybzCode(bzCode);
		businessService.businessAdmit(business);
		return "redirect:/getBusinessInfoBybzCode?bzCode=" + business.getBzCode();
	}
	
	@GetMapping("/getBusinessInfoBybzCode")
	public String getBusinessInfoBybzCode(Model model
										  ,@RequestParam(value="bzCode", required=false) String bzCode) {
		System.out.println("사업자신청코드 : " + bzCode);
		BusinessDTO business = businessService.getBusinessInfoBybzCode(bzCode);
		System.out.println("코드조회결과 : " + business);
		model.addAttribute("business", business);
		return "/member/getBusinessInfoBybzCode";
	}
	
	@GetMapping("/member/businessList")
	public String BusinessList(Model model) {
		List<BusinessDTO> businessList = businessService.getAllBusinessAdmitList();
		System.out.println(businessList);
		model.addAttribute("businessList", businessList);
		return "/member/businessList";
	}
	
	@PostMapping("/plantReturnSend")
	public String plantReturnSend(@RequestParam(name="bzPlCode", required=false) String bzPlCode) {
		System.out.println("반려된 사업자신청코드 : " + bzPlCode);
		BusinessPlantDTO plant = plantService.getPlantInfoBybzPlCode(bzPlCode);
		plantService.plantReturn(plant);
		return "redirect:/getPlantInfoBybzPlCode?bzPlCode=" + plant.getBzPlCode();
	}
	
	@PostMapping("/plantAdmitSend")
	public String plantAdmitSend(@RequestParam(name="bzPlCode", required=false) String bzPlCode) {
		System.out.println("승인된 사업자신청코드 : " + bzPlCode);
		BusinessPlantDTO plant = plantService.getPlantInfoBybzPlCode(bzPlCode);
		plantService.plantAdmit(plant);
		return "redirect:/getPlantInfoBybzPlCode?bzPlCode=" + plant.getBzPlCode();
	}
	
	@GetMapping("/getPlantInfoBybzPlCode")
	public String getPlantInfoBybzPlCode(Model model
										  ,@RequestParam(value="bzPlCode", required=false) String bzPlCode) {
		System.out.println("발전소사업자코드 : " + bzPlCode);
		BusinessPlantDTO plant = plantService.getPlantInfoBybzPlCode(bzPlCode);
		System.out.println("코드조회결과 : " + plant);
		model.addAttribute("plant", plant);
		return "/member/getPlantInfoBybzPlCode";
	}
	
	@GetMapping("/member/plantList")
	public String PlantList(Model model) {
		List<BusinessPlantDTO> plantList = plantService.getAllPlantAdmitList();
		System.out.println(plantList);
		model.addAttribute("plantList", plantList);
		return "/member/plantList";
	}
	
	@GetMapping("/member/account")
	public String Account(Model model) {
		List<MemberAccountDTO> allBankAccountList = memberService.getAllBankAccount();
		model.addAttribute("allBankAccountList", allBankAccountList);
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
			} else {
				checkResult = "실패";
			}
		}
		return checkResult;
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	// 카카오로그아웃
	@GetMapping("/logoutKakao")
	public String logoutKakao(HttpSession session) {
		KakaoLoginApi kakaoApi = new KakaoLoginApi();
		kakaoApi.kakaoLogout((String)session.getAttribute("accessToken"));
		session.invalidate();
		return "redirect:/login";
	}
	
}
