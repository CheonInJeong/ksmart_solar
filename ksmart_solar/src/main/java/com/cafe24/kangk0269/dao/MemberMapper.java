package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
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

}
