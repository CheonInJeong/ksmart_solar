package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Mapper
public interface PlantMapper {

	// 발전소인증신청목록
	public List<BusinessPlantDTO> getAllPlantAdmitList();
}
