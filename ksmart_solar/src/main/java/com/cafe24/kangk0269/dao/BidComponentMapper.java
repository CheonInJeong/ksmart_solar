package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidComponentDTO;

@Mapper
public interface BidComponentMapper {

	public List<BidComponentDTO> getBidComponent();
}
