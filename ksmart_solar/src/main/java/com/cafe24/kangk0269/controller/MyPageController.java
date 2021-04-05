package com.cafe24.kangk0269.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.dto.PickDTO;
import com.cafe24.kangk0269.serivce.AccountService;
import com.cafe24.kangk0269.serivce.MemberService;
import com.cafe24.kangk0269.serivce.PickService;

import ch.qos.logback.classic.Logger;


@Controller
public class MyPageController {
	
	
	private final AccountService accountService;
	private final MemberService memberService;
	private final PickService pickService;
	
	@Autowired
	public MyPageController(AccountService accountService, MemberService memberService, PickService pickService ) {
		this.accountService = accountService; 
		this.memberService = memberService; 
		this.pickService = pickService; 
	}
	
	
	//관심목록 등록
	@PostMapping("/mypage/addWishlist")
	public String addWishlist(HttpSession session,
			@RequestParam(name = "announcedCode", required = false ) String announcedCode) {
		System.out.println("addWishlist 실행");
		String login_id = (String)session.getAttribute("SID");
		pickService.addWishlist(announcedCode, login_id);
		return "/mypage/wishlist";
		
		
	}
	
	
	//관심목록 삭제
	@GetMapping("/mypage/removeWishlist")
	public String removeWishlist(@RequestParam(name = "pIdx", required = false ) int pIdx) {
		int result = pickService.removeWishlist(pIdx);
		return "redirect:/mypage/wishlist";
	}
	
	//관심목록 조회
	@GetMapping("/mypage/wishlist")
	public String Wishlist(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		String log_id = (String)session.getAttribute("SID");
		if(log_id == null) {
			ScriptUtils.alertAndMovePage(response, "로그인해주세요", "/login");
		}
		List<PickDTO> pickDTOList = pickService.getWishList(log_id);
		System.out.println("pickDTOList-->" + pickDTOList);
		model.addAttribute("pickDTOList", pickDTOList);
		return "/mypage/wishlist";
	}
	
	
	
	//탈퇴 신청처리
	
	
	
	
	
	
	
	//탈퇴 신청화면
	@GetMapping("/mypage/withdraw")
	public String withdraw() {
		return "/mypage/withdraw";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	 * //개인 비밀번호 수정처리
	 * 
	 * @PostMapping("/mypage/modifyPw") public String modifyPw(HttpSession session)
	 * { String login_id = (String)session.getAttribute("SID"); MemberDTO member =
	 * memberService.getMemberInfoById(login_id); String resultPw = member.getmPw();
	 * 
	 * return "/";
	 * 
	 * }
	 */
	
	//개인 비밀번호 수정화면
	@GetMapping("/mypage/modifyPw")
	public String modifyPw() {
		return "/mypage/modifyPw";
	}
	
	//개인 프로필 수정처리
	@PostMapping("/mypage/ModifyMyInfo")
	public String modifyMyInfo(MemberDTO memberDTO) {
		System.out.println("====================================");
		System.out.println("수정한 회원정보 내용->>" + memberDTO );
		System.out.println("====================================");
		int result = memberService.modifyMyInfo(memberDTO);
		return "redirect:/mypage/myInfo";
	}
	
	//개인 프로필 수정화면
	@GetMapping("/mypage/ModifyMyInfo")
	public String modifyMyInfo(Model model, HttpSession session) {
		/* getMyInfoById(null, model,session); */
		return "/mypage/ModifyMyInfo";
	}
	
	
	//개인 회원조회 화면
	@GetMapping("/mypage/myInfo")
	public String getMyInfoById(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		String login_id = (String)session.getAttribute("SID");
		if(login_id == null) {
			ScriptUtils.alertAndMovePage(response, "로그인해주세요", "/login");
		}
		System.out.println("=============================");
		System.out.println("로그인아이디-->" + login_id);
		System.out.println("=============================");
		MemberDTO memberDTO = memberService.getMyInfoById(login_id);
		
		System.out.println("개인회원 정보 조회-->" + memberDTO);
		model.addAttribute(memberDTO);
		return "/mypage/myInfo";
	}
	

	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//계좌 삭제처리
	@GetMapping("/mypage/removeAccount")
	public String removeAccount(@RequestParam(name = "mAccountIdx", required = false ) int mAccountIdx) {
		int result =  accountService.removeAccount(mAccountIdx);
		return "redirect:/mypage/myAccount";
		
	}
	
	
	//계좌 수정처리
	@PostMapping("/mypage/modifyAccount")
	public String modifyAccount(MemberAccountDTO memberAccountDTO) {
		System.out.println("====================================");
		System.out.println("수정한 계좌 내용->>" + memberAccountDTO );
		System.out.println("====================================");
		int result = accountService.modifyAccount(memberAccountDTO);
		return "redirect:/mypage/myAccount";
	}
	
	//계좌 수정화면
	@GetMapping("/mypage/modifyAccount")
	public String modifyAccount(Model model,
										@RequestParam(name = "mAccountIdx", required = false ) int mAccountIdx) {
		System.out.println("==================================");
		System.out.println("입력받은 계좌 인덱스->>" + mAccountIdx);
		System.out.println("==================================");
		MemberAccountDTO memberAccountDTO = accountService.modifyAccountByIdx(mAccountIdx);
		
		System.out.println("선택계좌정보조회->>" + memberAccountDTO);
		model.addAttribute("memberAccountDTO", memberAccountDTO);
		return "/mypage/modifyAccount";
	}
	
	//계좌등록 처리
	@PostMapping("/mypage/addAccount")
	public String addAccount(MemberAccountDTO memberAccountDTO) {
		System.out.println("memberAccountDTO-->" + memberAccountDTO );
		accountService.addAccout(memberAccountDTO);
		return "redirect:/mypage/myAccount";
		
	}
	
	//계좌등록 화면
	@GetMapping("/mypage/addAccount")
	public String addAccount() {
		return "/mypage/addAccount";
	}
	
	//개인 계좌조회
	@GetMapping("/mypage/myAccount")
	public String getAccountListById(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		String login_id = (String)session.getAttribute("SID");
		if(login_id == null) {
			ScriptUtils.alertAndMovePage(response, "로그인해주세요", "/login");
		}
		System.out.println("================================================");
		System.out.println("로그인 아이디->" + login_id);
		System.out.println("================================================");
		
		List<MemberAccountDTO> memberAccountDTOList = accountService.getAccountListById(login_id);
		
		System.out.println("개인계좌조회->" + memberAccountDTOList);
		model.addAttribute("memberAccountDTOList", memberAccountDTOList);
		return "/mypage/myAccount";
	}
}
