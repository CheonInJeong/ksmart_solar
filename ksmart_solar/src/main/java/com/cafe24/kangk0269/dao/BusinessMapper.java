package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;

@Mapper
public interface BusinessMapper {

	// 사업자인증신청 반려
	public int businessReturn(BusinessDTO business);
	
	// 사업자인증신청 승인
	public int businessAdmit(BusinessDTO business);
	
	// 사업자인증신청 상세정보(사업자코드)
	public BusinessDTO getBusinessInfoBybzCode(String bzCode);
	
	// 사업자인증신청 상세정보(아이디)
	public List<BusinessDTO> getBusinessInfoById(String mId);
	
	// 사업자인증신청목록
	public List<BusinessDTO> getAllBusinessAdmitList(int start,int end,String searchKey,String searchValue);
	// 사업자인증신청 리스트 수
	public int getAllBusinessAdmitListCnt(String searchKey,String searchValue);
	
	// 일반 사업자 신청
	public int addRecycleEntrepreneur(BusinessDTO bs);
	
	//태양광사업자 insert
	public int addSolarEntrepreneur(BusinessPlantDTO bp);

	//태양광사업자 감가계산 table insert
	public int addDepreciation(PlantDepreciationDTO pd);
	
	//bz_code
	public String getInsertBzCode();
	
	//bz_pl_code
	public String getInsertBzPlCode();
	
}