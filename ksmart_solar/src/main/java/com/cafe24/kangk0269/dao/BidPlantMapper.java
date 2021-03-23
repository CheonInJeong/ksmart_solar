package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidPlantDTO;

@Mapper
public interface BidPlantMapper {
	//공고 리스트 조회(진핸중과 마감 구분)
	public List<BidPlantDTO> getBidPlant(String status);
	//해당 공고 리스트 조회
	public BidPlantDTO getBidPlantByInfo(String announceTitle);
}
