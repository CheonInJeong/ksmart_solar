package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;

@Mapper
public interface BidComponentMapper {
	//공고 리스트 조회(진핸중과 마감 구분)
	public List<BidComponentDTO> getBidComponent(@Param(value="status") String status
												 ,@Param(value="searchKeyCp") String searchKeyCp
												 ,@Param(value="searchValueCp") String searchValueCp
												 ,@Param(value="bidComponentDTO") BidComponentDTO bidComponentDTO);
	public int getBidComponentListCount(@Param(value="status") String status
										,@Param(value="searchKeyCp") String searchKeyCp
										,@Param(value="searchValueCp") String searchValueCp);
	//해당 공고 리스트 조회
	public BidComponentDTO getBidComponentByInfo(String announceCode);
	public List<BidComponentDTO> getBidComponentMyBid(@Param(value="mId") String mId
													 ,@Param(value="searchKeyCp") String searchKeyCp
													 ,@Param(value="searchValueCp") String searchValueCp
													 ,@Param(value="bidComponentDTO") BidComponentDTO bidComponentDTO);
	public ComponentDTO getComponent(String CpCode);
	//해당공고의 상태에 따른 목록조회
	public List<String> getComponentSatusList(@Param(value="status")int status,@Param(value="bStatus")String bStatus);
	//
	public List<BidComponentDTO> getBidComTradeList(List<String> componentList);
	public int getComponentBidList(String announcedCode);
	//본인이 입찰한 부품의 공고 횟수
	public int getBidComponentCount(@Param(value="mId") String mId
									 ,@Param(value="searchKeyCp") String searchKeyCp
									 ,@Param(value="searchValueCp") String searchValueCp
									 ,@Param(value="bidComponentDTO") BidComponentDTO bidComponentDTO);
	}
