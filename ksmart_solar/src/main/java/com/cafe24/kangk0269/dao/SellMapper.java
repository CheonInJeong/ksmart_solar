package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;


@Mapper
public interface SellMapper {
	
	//입찰자 수 불러오기
	public int getNumberOfBidder();
	//해당 발전소 공고에서 가장 높게 입찰 받은 가격 불러오기
	public int getHighestPriceByCode();
	
	//판매자의 발전소 공고 목록을 가져오는 메서드 parameter : String mId 추가하기
	public List<BidPlantDTO> getBidPlantbyId();
	
	public int addPlantApply();
	//발전소 이름 가져오는 메서드 //parameter : String mId 추가하기
	public List<BusinessPlantDTO> getPlantName();
}
