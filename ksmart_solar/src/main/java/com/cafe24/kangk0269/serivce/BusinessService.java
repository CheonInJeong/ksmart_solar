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
	
	public List<BusinessDTO> getAllBusinessAdmitList(){
		
		return businessMapper.getAllBusinessAdmitList();
	}
}
