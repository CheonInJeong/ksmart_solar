package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidComponentDTO;

@Mapper
public interface BidComponentMapper {
	//공고 리스트 조회(진핸중과 마감 구분)
	public List<BidComponentDTO> getBidComponent(String status);
	//해당 공고 리스트 조회
	public BidComponentDTO getBidComponentByInfo(String announceTitle);
	public List<BidComponentDTO> getBidComponentMyBid(String sId);
}
