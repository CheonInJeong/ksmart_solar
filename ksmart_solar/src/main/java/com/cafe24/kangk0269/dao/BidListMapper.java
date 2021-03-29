package com.cafe24.kangk0269.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidListDTO;

@Mapper
public interface BidListMapper {
	
	public double getDepositRate();
	public int addbidList(BidListDTO bidListDTO);
	public int getBidListCount(String announceTitle, String id);
	public BidListDTO getBidList(String announceTitle, String id);
}
