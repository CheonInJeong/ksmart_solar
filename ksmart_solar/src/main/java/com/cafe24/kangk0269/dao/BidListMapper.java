package com.cafe24.kangk0269.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BidListDTO;

@Mapper
public interface BidListMapper {
	//현재 예치금 수수료율
	public double getDepositRate();
	//입찰 등록
	public int addbidList(BidListDTO bidListDTO);
	//입찰을 했는지 안했는지
	public int getBidListCount(String announceCode, String id);
	//해당 아이디가 입찰을 했는지 안했는지
	public BidListDTO getBidList(String announceCode, String id,String bCode);
	//입찰신청한 입찰코드
	public String getBidCode(String announceCode, String id);
	//입찰 취소
	public int bidCancel(String bCode);
	//입찰 취소시 발전소 공고 리스트의 입찰자 목록 -1
	public int bidPlantMemberMinus(String plCode); 
	//입찰 취소시 부품 공고 리스트의 입찰자 목록 -1
	public int bidComponentMemberMinus(String cpCode); 

}
