package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BusinessDTO;

@Mapper
public interface BusinessMapper {

	// 사업자인증신청목록
	public List<BusinessDTO> getAllBusinessAdmitList();
}