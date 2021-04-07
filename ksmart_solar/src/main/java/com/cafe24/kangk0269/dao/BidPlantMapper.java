package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Mapper
public interface BidPlantMapper {
	//등록 발전소공고 조회(판매자아이디)
	public List<BidPlantDTO> getBidPlantById(String mId); 
	
	//공고 리스트 조회(진핸중과 마감 구분)
	public List<BidPlantDTO> getBidPlant(String status);
	//해당 공고 리스트 조회
	public BidPlantDTO getBidPlantByInfo(String announceCode);
	public List<BidPlantDTO> getBidPlantMyBid(String sId);
	//해당공고의 발전소 정보 가져오기
	public BusinessPlantDTO getPlant(String announceCode);
	//해당공고의 상태에 따른 목록조회
	public List<String> getPlantSatusList(@Param(value="status")int status,@Param(value="bStatus")String bStatus);
	//거래진행중인 공고들의 계약중인 사람중 1순위
	public List<BidPlantDTO> getBidPlantTradeList(List<String> plantList);

}
