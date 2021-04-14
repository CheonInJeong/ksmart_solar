package com.cafe24.kangk0269.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.dto.MemberKakao;
import com.cafe24.kangk0269.dto.MemberRevokeDTO;

@Mapper
public interface MemberMapper {
	
	//전체회원계좌 조회
	public List<MemberAccountDTO> getAllBankAccount();
	
	// 탈퇴신청회원 조회
	public List<MemberRevokeDTO> getWithdrawAdmitMember(String searchKeyWAM, String searchValueWAM, String searchValueWAMS, String searchValueWAMF);

	// 탈퇴완료회원 조회
	public List<MemberRevokeDTO> getWithdrawCompleteMember(String searchKeyWCM, String searchValueWCM, String searchValueWCMS, String searchValueWCMF);
	
	// 로그인 기록 조회
	public List<Map<String,Object>> getLoginHistory(String searchKeyL, String searchValueL, String searchValueLS, String searchValueLF);
	
	//개인 비밀번호수정
	public int modifyPw(String login_id, String newPw);
	
	// 개인 프로필 수정처리
	public int modifyMyInfo(MemberDTO member);
	
	//개인 회원조회
	public MemberDTO getMyInfoById(String login_id);
	
	
	
	// 전체회원정보 수정
	public int modifyMember(MemberDTO member);
	
	// 활동회원조회
	public List<MemberDTO> getActiveMember(String searchKeyAM, String searchValueAM);
	
	// 휴면회원조회
	public List<MemberDTO> getRestMember(String searchKeyRM, String searchValueRM);
	
	// 회원가입
	public int addMember(MemberDTO member);
	
	// 회원정보 조회
	public MemberDTO getMemberInfoById(String mId);

	
	// 관리자 아이디 조회
	public List<String> getManager();
	
	//카카오 회원가입
	public int addMemberKakao(MemberKakao mk);

}
