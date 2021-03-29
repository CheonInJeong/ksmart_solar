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

import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.serivce.BusinessService;
import com.cafe24.kangk0269.serivce.MemberService;
import com.cafe24.kangk0269.serivce.PlantService;

@Controller
public class MemberManageController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BusinessService businessService;

	@Autowired
	private PlantService plantService;
	
	@GetMapping("/getMemberInfoById")
	public String getMemberInfoById(Model model
									,@RequestParam(value="mId", required=false) String mId) {
		MemberDTO member = memberService.getMemberInfoById(mId);
		model.addAttribute("member", member);
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
	public String MemberList(Model model) {
		List<MemberDTO> memberList = memberService.getAllMember();
		System.out.println(memberList);
		model.addAttribute("memberList", memberList);
		return "/member/memberList";
	}
	
	@GetMapping("/member/memberLoginHistory")
	public String MemberLoginHistory() {
		
		return "/member/memberLoginHistory";
	}

	@GetMapping("/member/memberWithdrawList")
	public String MemberWithdrawList() {
		
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
	public String Account() {
		
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
				//이름
				session.setAttribute("SNAME", resultName);	
				
				
			}else {
				checkResult = "비번불일치";
			}
		}
		return checkResult;
	}
	
	//버튼 로그인 (관리자)
	@GetMapping("/login/manager")
	public String loginManager(HttpSession session) {
		//아이디 
		session.setAttribute("SID", "manager01"); 
		//권한
		session.setAttribute("SLEVEL", "관리자"); 
		//이름 
		session.setAttribute("SNAME","홍길동");
		return "main";
	}
	//버튼 로그인 (태양광사업자)
	@GetMapping("/login/solar")
	public String loginSolar(HttpSession session) {
		//아이디 
		session.setAttribute("SID", "solar01"); 
		//권한
		session.setAttribute("SLEVEL", "태양광사업자"); 
		//이름 
		session.setAttribute("SNAME","김태풍");
		return "main";
	}
	//버튼 로그인 (관리자)
	@GetMapping("/login/recycle")
	public String loginRecycle(HttpSession session) {
		//아이디 
		session.setAttribute("SID", "recycle01"); 
		//권한
		session.setAttribute("SLEVEL", "재활용사업자"); 
		//이름 
		session.setAttribute("SNAME","박선비");
		return "main";
	}
	
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
}
