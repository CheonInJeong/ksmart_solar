package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.common.DepreciationCalculate;
import com.cafe24.kangk0269.dao.BusinessMapper;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;

@Service
@Transactional
public class BusinessService {

	private final BusinessMapper businessMapper;

	@Autowired
	public BusinessService(BusinessMapper businessMapper) {
		this.businessMapper = businessMapper;
	}

	// 사업자인증신청 반려
	public int businessReturn(BusinessDTO business) {
		return businessMapper.businessReturn(business);
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

	public int addSolarEntrepreneur(BusinessDTO bs, BusinessPlantDTO bp, PlantDepreciationDTO pd) {
		
		System.out.println(bs);
		System.out.println(bp);
		System.out.println(pd);
		
		String bzCode = businessMapper.getInsertBzCode();
		String bzPlCode = businessMapper.getInsertBzPlCode();
		
		bp.setBzCode(bzCode);
		pd.setBzPlCode(bzPlCode);
		
		//날짜계산필요 pd.getPlDepBuyDate() - pd.getPlDepStartDate();
		
		int PlDepPriceBased = DepreciationCalculate.calculPlDepPriceBased(pd.getPlDepPrice(), 30);
		
		System.out.println(PlDepPriceBased);
		
		return 1;
	}
	
	
	
}