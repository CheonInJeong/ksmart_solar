package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.MemberAccountDTO;

@Mapper
public interface BankAccountMapper {
	
	
	//계좌 삭제처리
	public int removeAccount(int mAccountIdx);
	
	//계좌 사용여부 변경처리
	public MemberAccountDTO modifyAccountUse(String mAccountCheck, int mAccountIdx);
	
	//개인 계좌조회
	public List<MemberAccountDTO> getAccountListById(String login_id);

	//개인 계좌등록
	public int addAccout(MemberAccountDTO memberAccountDTO);
	
	//개인 계좌조회
	public List<MemberAccountDTO> getAccountListByManager(List<String> Id);
	//인덱스로 계좌조회
	public MemberAccountDTO getAccountListByIdx(String mAccountIdx);
}







