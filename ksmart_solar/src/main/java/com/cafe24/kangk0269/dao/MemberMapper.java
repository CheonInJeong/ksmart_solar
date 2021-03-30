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
	
	// 전체회원계좌 조회
	public List<MemberAccountDTO> getAllBankAccount();
	
	// 탈퇴신청회원 조회
	public List<MemberRevokeDTO> getWithdrawAdmitMember();
	
	// 로그인 기록 조회
	public List<Map<String,Object>> getLoginHistory();
	
	// 개인 회원정보수정
	public int modifyMyInfo(MemberDTO member);
	
	//개인 회원조회
	public MemberDTO getMyInfoById(String login_id);
	
	
	// 전체회원정보 수정
	public int modifyMember(MemberDTO member);
	
	// 전체회원조회
	public List<MemberDTO> getAllMember();
	
	// 회원가입
	public int addMember(MemberDTO member);
	
	// 회원정보 조회
	public MemberDTO getMemberInfoById(String mId);

	
	// 관리자 아이디 조회
	public List<String> getManager();
	
	//카카오 회원가입
	public int addMemberKakao(MemberKakao mk);

}
