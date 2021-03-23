package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.serivce.MemberService;

@Controller
public class MemberManageController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/memberList")
	public String MemberList(Model model) {
		List<MemberDTO> memberList = memberService.getAllMember();
		System.out.println(memberList);
		model.addAttribute("memberList", memberList);
		return "/member/memberList";
	}
	
	@GetMapping("/member/memberLogin")
	public String MemberLogin() {
		
		return "/member/memberLogin";
	}
	
	@GetMapping("/member/memberWithdrawList")
	public String MemberWithdrawList() {
		
		return "/member/memberWithdrawList";
	}
	
	@GetMapping("/member/businessList")
	public String BusinessList() {
		
		return "/member/businessList";
	}
	
	@GetMapping("/member/plantList")
	public String PlantList() {
		
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
	
	
	//버튼 로그인 (관리자)
	
	
}
