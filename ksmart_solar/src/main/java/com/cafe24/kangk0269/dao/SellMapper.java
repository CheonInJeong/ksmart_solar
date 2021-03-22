package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;


@Mapper
public interface SellMapper {
	//발전소 이름 가져오는 메서드 //parameter : String mId 추가하기
	public List<BusinessPlantDTO> getPlantName();
}
