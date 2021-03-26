package com.cafe24.kangk0269.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.StandardDTO;



@Mapper
public interface PolicyMapper {
	
	//새로운 정책 추가
	public int addNewDeposit(StandardDTO standardDto);
	
	
	//정책 수정..사실 추가
	public int addDepositPolicy(StandardDTO standardDto);
	public int addCommissionPolicy(StandardDTO standardDto);
	public int addTradePolicy(StandardDTO standardDto);
	
	//정책수정
	public int modifyDepositPolicy(String idx);
	public int modifyTradePolicy(String idx);
	public int modifyCommissionPolicy(String idx);
	
	
	//정책조회
	public List<StandardDTO> getDepositPolicy();
	public List<StandardDTO> getTradePolicy();
	public List<StandardDTO> getCommissionPolicy();
}
