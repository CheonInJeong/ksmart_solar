package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BusinessDTO;

@Mapper
public interface BusinessMapper {

	// 사업자인증신청 승인
	public int businessAdmit(BusinessDTO business);
	
	// 사업자인증신청 상세정보
	public BusinessDTO getBusinessInfoBybzCode(String bzCode);
	
	// 사업자인증신청목록
	public List<BusinessDTO> getAllBusinessAdmitList();
}