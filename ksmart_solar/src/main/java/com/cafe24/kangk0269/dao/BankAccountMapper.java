package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.MemberAccountDTO;

@Mapper
public interface BankAccountMapper {
	
	//전체회원계좌 조회
	public List<MemberAccountDTO> getAllBankAccount();
	
	//계좌 삭제처리
	public int removeAccount(int mAccountIdx);
	
	//계좌 수정처리
	public int modifyAccount(MemberAccountDTO memberAccountDTO);
	
	//개인 계좌 수정화면
	public MemberAccountDTO modifyAccountByIdx(int mAccountIdx);
	
	//개인 계좌조회
	public List<MemberAccountDTO> getAccountListById(String login_id);

	//개인 계좌등록
	public int addAccout(MemberAccountDTO memberAccountDTO);
	
	//개인 계좌조회
	public List<MemberAccountDTO> getAccountListByManager(List<String> Id);
}







