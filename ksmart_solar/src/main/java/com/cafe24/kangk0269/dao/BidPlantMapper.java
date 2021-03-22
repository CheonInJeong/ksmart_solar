package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidPlantDTO;

@Mapper
public interface BidPlantMapper {

	public List<BidPlantDTO> getBidPlant();
}
