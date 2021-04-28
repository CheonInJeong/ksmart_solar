package com.cafe24.kangk0269.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.MemberDTO;
import com.cafe24.kangk0269.dto.MemberKakao;
import com.cafe24.kangk0269.dto.MemberLogDTO;
import com.cafe24.kangk0269.dto.MemberRevokeDTO;

@Mapper
public interface MemberMapper {
	
	//프로필만 수정처리
	public int modifyMyProfile(String login_id, String mPhoto);
	
	//인터셉터 처리를 위한 uri 불러오기
	public List<String> getUri(int level);
	
	//전체회원계좌 조회
	public List<MemberAccountDTO> getAllBankAccount(int start, int end, String searchKey, String searchValue);
	//전체회원계좌 리스트 수 조회
	public int getAllBankAccountCnt(String searchKey, String searchValue);
	
	// 탈퇴신청회원 조회
	public List<MemberRevokeDTO> getWithdrawAdmitMember(int start, int end, String searchKeyWAM, String searchValueWAM, String searchValueWAMS, String searchValueWAMF);
	// 탈퇴신청회원 리스트 수 조회
	public int getWithdrawAdmitMemberCnt(String searchKeyWAM, String searchValueWAM, String searchValueWAMS, String searchValueWAMF);

	// 탈퇴완료회원 조회
	public List<MemberRevokeDTO> getWithdrawCompleteMember(int start, int end, String searchKeyWCM, String searchValueWCM, String searchValueWCMS, String searchValueWCMF);
	// 탈퇴완료회원 리스트 수 조회
	public int getWithdrawCompleteMemberCnt(String searchKeyWCM, String searchValueWCM, String searchValueWCMS, String searchValueWCMF);
	
	// 로그인 기록 입력
	public int addLoginHistory(String mId);
	// 로그아웃시 로그인 기록 업데이트
	public int modifyLoginHistory(String mId);
	// 로그인 기록 조회
	public List<MemberLogDTO> getLoginHistory(int start, int end, String searchKeyL, String searchValueL, String searchValueLS, String searchValueLF);
	// 로그인 기록 리스트 수 조회
	public int getLoginHistoryCnt(String searchKeyL, String searchValueL, String searchValueLS, String searchValueLF);
	
	//개인 비밀번호수정
	public int modifyPw(String login_id, String newPw);
	
	// 개인 프로필 수정처리
	public int modifyMyInfo(MemberDTO member);
	
	//개인 회원조회
	public MemberDTO getMyInfoById(String login_id);
	
	
	//개인 회원탈퇴신청
	public void withdraw(MemberRevokeDTO memberRevokeDTO);
	
	//탈퇴 회원상태 변경
	public void modifyMemberState(String login_id);
	
	// 전체회원권한수정
	public int modifyMember(MemberDTO member);
	
	// 활동회원조회
	public List<MemberDTO> getActiveMember(int start, int end, String searchKeyAM, String searchValueAM,String searchValueAMS, String searchValueAMF);
	// 활동회원 리스트 수 조회
	public int getActiveMemberCnt(String searchKeyAM, String searchValueAM,String searchValueAMS, String searchValueAMF);
	
	// 휴면회원조회
	public List<MemberDTO> getRestMember(int start, int end, String searchKeyRM, String searchValueRM,String searchValueRMS, String searchValueRMF);
	// 휴면회원 리스트 수 조회
	public int getRestMemberCnt(String searchKeyRM, String searchValueRM,String searchValueRMS, String searchValueRMF);
	
	// 회원가입
	public int addMember(MemberDTO member);
	
	// 회원정보 조회
	public MemberDTO getMemberInfoById(String mId);

	
	// 관리자 아이디 조회
	public List<String> getManager();
	
	//카카오 회원가입
	public int addMemberKakao(MemberKakao mk);

}
