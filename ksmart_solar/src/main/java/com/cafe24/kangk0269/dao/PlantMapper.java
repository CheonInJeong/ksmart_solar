package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Mapper
public interface PlantMapper {

	// 발전소인증신청 반려
	public int plantReturn(BusinessPlantDTO plant);
	
	// 발전소인증신청 승인
	public int plantAdmit(BusinessPlantDTO plant);
	
	// 발전소인증신청 상세정보
	public BusinessPlantDTO getPlantInfoBybzPlCode(String bzPlCode);
	
	// 발전소인증신청목록
	public List<BusinessPlantDTO> getAllPlantAdmitList();
	
	//발전소리스트(아이디)
	public List<BusinessPlantDTO> getPlantListById(String SID);
	
}