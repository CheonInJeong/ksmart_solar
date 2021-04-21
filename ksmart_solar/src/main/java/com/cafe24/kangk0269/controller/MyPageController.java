package com.cafe24.kangk0269.controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.kangk0269.common.ScriptUtils;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.dto.MemberRevokeDTO;
import com.cafe24.kangk0269.dto.PickDTO;
import com.cafe24.kangk0269.serivce.AccountService;
import com.cafe24.kangk0269.serivce.MemberService;
import com.cafe24.kangk0269.serivce.PickService;

import ch.qos.logback.classic.Logger;

@Controller
public class MyPageController {

	private static final org.mybatis.logging.Logger logger = LoggerFactory.getLogger(MyPageController.class);
	private final AccountService accountService;
	private final MemberService memberService;
	private final PickService pickService;

	@Autowired
	public MyPageController(AccountService accountService, MemberService memberService, PickService pickService) {
		this.accountService = accountService;
		this.memberService = memberService;
		this.pickService = pickService;
	}

	// 관심목록 등록 + 중복 등록 방지 메서드
	@PostMapping("/mypage/addWishlist")
	public void addWishlist(HttpServletResponse response, HttpSession session,
			@RequestParam(name = "announcedCode", required = false) String announcedCode) throws IOException {
		String log_id = (String) session.getAttribute("SID");
		if(pickService.pickCheck(announcedCode, log_id) != 0) {
			ScriptUtils.alertAndBackPage(response, "이미 등록된 공고입니다");
		}else {
			
			ScriptUtils.alertAndBackPage(response, "등록되었습니다");
			pickService.addWishlist(announcedCode, log_id);
		}
	}

	// 관심목록 삭제
	@GetMapping("/mypage/removeWishlist")
	public String removeWishlist(@RequestParam(name = "pIdx", required = false) int pIdx) {
		int result = pickService.removeWishlist(pIdx);
		return "redirect:/mypage/wishlist";
	}

	// 관심목록 조회
	@GetMapping("/mypage/wishlist")
	public String Wishlist(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		String log_id = (String) session.getAttribute("SID");
		if (log_id == null) {
			ScriptUtils.alertAndMovePage(response, "로그인해주세요", "/login");
		}
		List<PickDTO> picPlkDTOList = pickService.getPlWishList(log_id);
		List<PickDTO> pickCpDTOList = pickService.getCpWishList(log_id);
		model.addAttribute("picPlkDTOList", picPlkDTOList);
		model.addAttribute("pickCpDTOList", pickCpDTOList);
		return "/mypage/wishlist";
	}

	// 탈퇴 신청처리
	@PostMapping("/mypage/withdraw")
	public void withdraw(HttpServletResponse response, MemberRevokeDTO memberRevokeDTO, HttpSession session
							,@RequestParam(name = "in_pw", required = false) String in_pw) throws IOException {
		
		String login_id = (String) session.getAttribute("SID");
		MemberDTO member = memberService.getMemberInfoById(login_id);
		String currentPw = member.getmPw();
		if(!currentPw.equals(in_pw)) {
			ScriptUtils.alertAndBackPage(response, "현재 비밀번호가 틀렸습니다");
		}else {
			
			memberService.withdraw(memberRevokeDTO);
			ScriptUtils.alertAndMovePage(response, "회원탈퇴 신청이되었습니다.", "/");
			memberService.modifyMemberState(login_id);
			session.invalidate();
		}
	}
	
	
	// 탈퇴 신청화면
	@GetMapping("/mypage/withdraw")
	public String withdraw() {
		return "/mypage/withdraw";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 개인 비밀번호 수정처리
	@PostMapping("/mypage/modifyPw")
	public String modifyPw( HttpServletResponse response
							,HttpSession session
							,@RequestParam(name = "mPw", required = false) String mPw
							,@RequestParam(name = "newPw", required = false) String newPw
							,@RequestParam(name = "newPwCheck", required = false) String newPwCheck) throws IOException {
		String login_id = (String) session.getAttribute("SID");
		MemberDTO member = memberService.getMemberInfoById(login_id);
		String currentPw = member.getmPw();
		
			if(currentPw.equals(mPw)) {
				if(newPw.equals(newPwCheck) ) {
					memberService.modifyPw(login_id, newPw);
					ScriptUtils.alertAndMovePage(response, "비밀번호가 정상적으로 수정되었습니다.", "/mypage/myInfo");
				}
				else {
					ScriptUtils.alertAndBackPage(response, "새 비밀번호를 확인해주세요.");
				}
			}	
			else{
				ScriptUtils.alertAndBackPage(response, "현재 비밀번호가 틀렸습니다");
			}
			return "redirect:/mypage/myInfo";
	}

	// 개인 비밀번호 수정화면
	@GetMapping("/mypage/modifyPw")
	public String modifyPw() {
		return "/mypage/modifyPw";
	}
	
	// 개인 프로필 수정처리
	@PostMapping("/mypage/ModifyMyInfo")
	public String modifyMyInfo(HttpServletResponse response, MemberDTO memberDTO) throws IOException {
		System.out.println("====================================");
		System.out.println("수정한 회원정보 내용->>" + memberDTO);
		System.out.println("====================================");
		
		memberService.modifyMyInfo(memberDTO);
		ScriptUtils.alertAndMovePage(response, "프로필이 수정되었습니다" , "/mypage/myInfo");
		return "redirect:/mypage/myInfo";
	}

	// 개인 프로필 수정화면
	@GetMapping("/mypage/ModifyMyInfo")
	public String modifyMyInfo(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		getMyInfoById(response, model, session);
		return "/mypage/ModifyMyInfo";
	}

	// 개인 회원조회 화면
	@GetMapping("/mypage/myInfo")
	public String getMyInfoById(HttpServletResponse response, Model model, HttpSession session) throws IOException {
		String login_id = (String) session.getAttribute("SID");
		if (login_id == null) {
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

	// 계좌 삭제처리
	@GetMapping("/mypage/removeAccount")
	public String removeAccount(@RequestParam(name = "mAccountIdx", required = false) int mAccountIdx) {
		int result = accountService.removeAccount(mAccountIdx);
		return "redirect:/mypage/myAccount";

	}

	
	//계좌 사용여부 변경처리
	@RequestMapping(value="/ajax/modifyAccountUse", method = RequestMethod.POST)
	public @ResponseBody String modifyAccountUse(Model model
												, @RequestParam(name = "mAccountCheck", required = false) String mAccountCheck
												, @RequestParam(name = "mAccountIdx", required = false) int mAccountIdx) {
		System.out.println(mAccountCheck);
		System.out.println(mAccountIdx);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mAccountCheck", mAccountCheck);
		map.put("mAccountIdx", mAccountIdx);
		
		System.out.println(map+"<---쿼리실행전");
		
		int result = accountService.modifyAccountUse(map);
		System.out.println(map+"<---쿼리실행후");
		if(result > 0) {
			mAccountCheck = (String) map.get("mAccountCheck");
			System.out.println(mAccountCheck);
			return mAccountCheck;
		}
		return mAccountCheck;
	}
	
	// 계좌등록 처리
	@PostMapping("/mypage/addAccount")
	public String addAccount(MemberAccountDTO memberAccountDTO) {
		System.out.println("memberAccountDTO-->" + memberAccountDTO);
		accountService.addAccout(memberAccountDTO);
		return "redirect:/mypage/myAccount";

	}

	// 계좌등록 화면
	@GetMapping("/mypage/addAccount")
	public String addAccount() {
		return "/mypage/addAccount";
	}

	// 개인 계좌조회
	@GetMapping("/mypage/myAccount")
	public String getAccountListById(HttpServletResponse response, Model model, HttpSession session)
			throws IOException {
		String login_id = (String) session.getAttribute("SID");
		if (login_id == null) {
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
