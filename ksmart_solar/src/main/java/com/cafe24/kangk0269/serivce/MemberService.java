package com.cafe24.kangk0269.serivce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartResolver;

import com.cafe24.kangk0269.api.KakaoLoginApi;
import com.cafe24.kangk0269.dao.MemberMapper;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.dto.MemberKakao;
import com.cafe24.kangk0269.dto.MemberRevokeDTO;

@Service
@Transactional
public class MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	
	private final MemberMapper memberMapper;
	
	@Autowired
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("MemberService bean 등록");
		System.out.println("=========================================================");
	}
	
	//권한에 따른 uri 가져오기
	public List<String> getUri(int level){
		return memberMapper.getUri(level);
	}

	// 프로필 사진 수정
	public MultipartResolver multipartResolver() {
	  org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
	  multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
	  return multipartResolver;
	}
	
	// 전체회원계좌 조회
	public List<MemberAccountDTO> getAllBankAccount(){
		return memberMapper.getAllBankAccount();
	}
	
	// 탈퇴신청회원 조회
	public List<MemberRevokeDTO> getWithdrawAdmitMember(String searchKeyWAM, String searchValueWAM, String searchValueWAMS, String searchValueWAMF){
		if(searchKeyWAM != null && searchKeyWAM != "") {
			if("mId".equals(searchKeyWAM)) {
				searchKeyWAM = "m_id";
			}else if("mRevokeDate".equals(searchKeyWAM)) {
				searchKeyWAM = "m_revoke_date";
			}else {
				searchKeyWAM = "m_revoke_cancel_date";
			}
		}
		
		return memberMapper.getWithdrawAdmitMember(searchKeyWAM, searchValueWAM, searchValueWAMS, searchValueWAMF);
	}

	// 탈퇴완료회원 조회
	public List<MemberRevokeDTO> getWithdrawCompleteMember(String searchKeyWCM, String searchValueWCM, String searchValueWCMS, String searchValueWCMF){
		if(searchKeyWCM != null && searchKeyWCM != "") {
			if("mId".equals(searchKeyWCM)) {
				searchKeyWCM = "m_id";
			}else if("mRevokeDate".equals(searchKeyWCM)) {
				searchKeyWCM = "m_revoke_date";
			}else {
				searchKeyWCM = "m_revoke_final_date";
			}
		}
		
		return memberMapper.getWithdrawCompleteMember(searchKeyWCM, searchValueWCM, searchValueWCMS, searchValueWCMF);
	}
	
	// 로그인 기록 조회
	public Map<String, Object> getLoginHistory(String searchKeyL, String searchValueL, String searchValueLS, String searchValueLF){
		if(searchKeyL != null && searchKeyL != "") {
			if("mId".equals(searchKeyL)) {
				searchKeyL = "m_id";
			}else {
				searchKeyL = "m_log_in";
			}
		}
		List<Map<String, Object>> loginHistory = memberMapper.getLoginHistory(searchKeyL, searchValueL, searchValueLS, searchValueLF);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("loginHistory", loginHistory);
		
		return resultMap;
	}
	
	// 전체 회원정보수정
	public int modifyMember(MemberDTO member) {
		int result = memberMapper.modifyMember(member);
		return result;
	}
	
	//개인 비멀번호수정
	public int modifyPw(String login_id, String newPw) {
		return memberMapper.modifyPw(login_id, newPw);
		
	}
	
	//개인 프로필 수정처리
	public int modifyMyInfo(MemberDTO memberDTO) {
		int result = memberMapper.modifyMyInfo(memberDTO);
		return result;
	}
	
	
	//개인 회원조회
	public MemberDTO getMyInfoById(String login_id) {
		MemberDTO memberDTO = memberMapper.getMyInfoById(login_id);
		return memberDTO;
	}
	
	
	//아이디를 입력해서 회원정보를 검색하는 메서드
	public MemberDTO getMemberInfoById(String mId) {
		MemberDTO member = memberMapper.getMemberInfoById(mId);
		int memberLevel = 0;
		
		if(member != null && member.getmLevel() != 0) {
			memberLevel += member.getmLevel();
			
			switch (memberLevel) {
			case 1:
				member.setmLevelName("관리자");
				break;
			case 2:
				member.setmLevelName("태양광사업자");
				break;
			case 3:
				member.setmLevelName("재활용사업자");
				break;
			case 4:
				member.setmLevelName("일반회원");
				break;
			default:
				member.setmLevelName("비회원");
				break;
			}
		}
		return member;
	}


	public int addMember(MemberDTO memberDTO) {
		int result = memberMapper.addMember(memberDTO);
		return result;
	}
	
	public MemberDTO memberLoginKakao(String accessToken) {
		MemberKakao mk = new MemberKakao();
		KakaoLoginApi kakaoApi = new KakaoLoginApi();
		HashMap<String, Object> memberInfo = kakaoApi.getUserInfo(accessToken);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(memberInfo);
		String kakaoId = (String) memberInfo.get("kakaoId");
		String nickName = (String) memberInfo.get("nickName");
		String thumbnailImage = (String) memberInfo.get("thumbnailImage");
		String kakaoEmail = (String) memberInfo.get("kakaoEmail");
		
		MemberDTO memberDTO = memberMapper.getMyInfoById("kakao" + kakaoId);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(memberDTO);
		if(memberDTO == null) {
			//카카오로 가입
			mk.setKakaoId(kakaoId);
			mk.setNickName(nickName);
			mk.setKakaoEmail(kakaoEmail);
			mk.setThumbnailImage(thumbnailImage);
			mk.setmId("kakao" + kakaoId);
			mk.setmPw("kakaoPW" + kakaoId);
			int kakaoInsertResult = memberMapper.addMemberKakao(mk);
			if(kakaoInsertResult == 0) {
				return null;
			}
			memberDTO = memberMapper.getMyInfoById("kakao" + kakaoId);
		}
		System.out.println(memberDTO);
		return memberDTO;
	}
	
	public List<String> getManager() {
		List<String> managerList = memberMapper.getManager();
		return managerList;
	}
	
	// 활동회원조회
	public List<MemberDTO> getActiveMember(String searchKeyAM, String searchValueAM){
		if(searchKeyAM != null) {
			if("mId".equals(searchKeyAM)) {
				searchKeyAM = "m_id";
			}else if("mName".equals(searchKeyAM)) {
				searchKeyAM = "m_name";
			}else if("mLevel".equals(searchKeyAM)) {
				searchKeyAM = "m_level";
			}else if("mAddr".equals(searchKeyAM)) {
				searchKeyAM = "m_addr";
			}else {
				searchKeyAM = "m_sub_date";
			}
		}
		List<MemberDTO> memberList = memberMapper.getActiveMember(searchKeyAM, searchValueAM);
		
		for(int i=0; i < memberList.size(); i++) {
			int mLevel = memberList.get(i).getmLevel();
			if(mLevel == 1) {
				memberList.get(i).setmLevelName("관리자");
			}
			if(mLevel == 2) {
				memberList.get(i).setmLevelName("태양광");
			}
			if(mLevel == 3) {
				memberList.get(i).setmLevelName("재활용");
			}
			if(mLevel == 4) {
				memberList.get(i).setmLevelName("일반");
			}
		}
		
		return memberList;

	}
	
	// 휴면회원조회
	public List<MemberDTO> getRestMember(String searchKeyRM, String searchValueRM){
		if(searchKeyRM != null) {
			if("mId".equals(searchKeyRM)) {
				searchKeyRM = "m_id";
			}else if("mName".equals(searchKeyRM)) {
				searchKeyRM = "m_name";
			}else if("mLevel".equals(searchKeyRM)) {
				searchKeyRM = "m_level";
			}else if("mAddr".equals(searchKeyRM)) {
				searchKeyRM = "m_addr";
			}else {
				searchKeyRM = "m_sub_date";
			}
		}
		List<MemberDTO> memberList = memberMapper.getRestMember(searchKeyRM, searchValueRM);
		
		for(int i=0; i < memberList.size(); i++) {
			int mLevel = memberList.get(i).getmLevel();
			if(mLevel == 1) {
				memberList.get(i).setmLevelName("관리자");
			}
			if(mLevel == 2) {
				memberList.get(i).setmLevelName("태양광");
			}
			if(mLevel == 3) {
				memberList.get(i).setmLevelName("재활용");
			}
			if(mLevel == 4) {
				memberList.get(i).setmLevelName("일반");
			}
		}
		
		return memberList;
		
	}
}
