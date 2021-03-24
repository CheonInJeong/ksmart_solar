package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.FileDTO;


@Mapper
public interface SellMapper {
	public int addFile(List<FileDTO> list);
	
	//공고신청 중 발전소 선택시 선택한 발전소의 정보(잔존가치, 매입금액, 발전소시작일)을 가져오는 메서드
	public BusinessPlantDTO getPlantInformation(String code);
	
	//입찰자 수 불러오기
	public int getNumberOfBidder();
	//해당 발전소 공고에서 가장 높게 입찰 받은 가격 불러오기
	public int getHighestPriceByCode();
	
	//판매자의 발전소 공고 목록을 가져오는 메서드 parameter : String mId 추가하기
	public List<BidPlantDTO> getBidPlantbyId();
	
	//공고신청 메서드
	public int addPlantApply(BidPlantDTO bidPlantDto);
	//발전소 이름 가져오는 메서드 //parameter : String mId 추가하기
	public List<BusinessPlantDTO> getPlantName(String mId);
}
