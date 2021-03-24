package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.MemberAccountDTO;

@Mapper
public interface BankAccountMapper {

	List<MemberAccountDTO> getAccountListById(String login_id);

}
