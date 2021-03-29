package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BusinessMapper;
import com.cafe24.kangk0269.dto.BusinessDTO;

@Service
@Transactional
public class BusinessService {

	private final BusinessMapper businessMapper;

	@Autowired
	public BusinessService(BusinessMapper businessMapper) {
		this.businessMapper = businessMapper;
	}

	// 사업자인증신청 승인
	public int businessAdmit(BusinessDTO business) {
		return businessMapper.businessAdmit(business);
	}
	
	// 사업자신청 상세조회
	public BusinessDTO getBusinessInfoBybzCode(String bzCode) {
		return businessMapper.getBusinessInfoBybzCode(bzCode);
	}
	
	// 전체 사업자신청 조회
	public List<BusinessDTO> getAllBusinessAdmitList(){

		return businessMapper.getAllBusinessAdmitList();
	}
	
	// 일반 사업자 신청
	public int addRecycleEntrepreneur(BusinessDTO bs){
		
		System.out.println(bs);
		
		return businessMapper.addRecycleEntrepreneur(bs);
	}
	
	
	
}