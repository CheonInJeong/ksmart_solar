package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;

@Mapper
public interface ScheduledMapper {
	
	//발전소 공고 공고승인 >공고진행중 by천인정
	public int updatePlantConfirmToIng(String today);
	//부품 공고 공고승인 >공고진행중 by천인정
	public int updateComponentConfirmToIng(String today);
	
	
	//발전소 공고 상태 업데이트 (공고마감일에 입찰자가 없는 경우) by천인정
	public int updateBidPlantAc(String announcedCode);
	//부품 공고 상태 업데이트 (공고마감일에 입찰자가 없는 경우) by천인정
	public int updateBiComponentAc(String announcedCode);
	
	//부품 공고 입찰자 수 가져오기 by천인정
	public List<BidListDTO> getComponentBidderNumber();
	//발전소 공고 입찰자 수 가져오기 by천인정
	public List<BidListDTO> getPlantBidderNumber();
	
	//낙찰자테이블에 추가 by천인정
	public int addTradePriod(@Param(value="bidListDTO") BidListDTO bidListDTO,
							 @Param(value="today") String today);
	
	//낙찰자테이블에 넣기 위한 정보 조회 - 발전소일경우 by천인정
	public BidListDTO getPlantRankInfo(String announcedCode);
	
	//낙찰자테이블에 넣기 위한 정보 조회 - 부품일경우 by천인정
	public BidListDTO getComponentRankInfo(String announcedCode);
	//낙찰자테이블의 거래상태 바꾸기 by천인정
	public int updateAcInPriority(String trPrCode);
	//입찰자 리스트의 거래상태 바꾸기 by천인정
	public int updateAcInbidList(String bCode);
	//2순위 낙찰자 테이블에 추가 by천인정
	
	//대금납부테이블에 추가 by천인정
	public int addPayIn(@Param(value="tradePriorityDTO") TradePriorityDTO tradePriorityDTO,
						@Param(value="tomorrow") String tomorrow);
	
	//우선순위테이블 조회 by 천인정
	public List<TradePriorityDTO> getPriority();
	//계약이 취소된 낙찰자의 공고코드와 순위가져오는 쿼리
	public List<TradePriorityDTO> getPriorityFail();
	//낙찰자 테이블에서 취소가 되면 다음순위 낙찰자 가져오는 쿼리
	public String getBidListNextRank(@Param(value="announcedCode") String announcedCode,
										 @Param(value="rank") int rank);
	
	//대금 미납인 입찰자의 정보 가져오기 by천인정
	public List<TradePriorityDTO> getNotPaied();
	
	
} 
