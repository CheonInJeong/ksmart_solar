package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	
	// 회원정보수정
	public int modifyMember(MemberDTO member);
	
	// 개인회원정보조회
	public MemberDTO getMember(String mId);
	
	// 전체회원조회
	public List<MemberDTO> getAllMember();
	
	// 회원가입
	public int addMember(MemberDTO member);
	
	// 회원정보 조회
	public MemberDTO getMemberInfoById(String mId);
	// 회원정보 조회
	public List<String> getManager(String level);

}
