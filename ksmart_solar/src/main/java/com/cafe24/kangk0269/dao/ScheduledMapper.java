package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;

@Mapper
public interface ScheduledMapper {

	public int addPayIn(@Param(value="tradePriorityDTO") TradePriorityDTO tradePriorityDTO,
						@Param(value="tomorrow") String tomorrow);
	
	public List<TradePriorityDTO> getPriority();
	//계약이 취소된 낙찰자의 공고코드와 순위가져오는 쿼리
	public List<TradePriorityDTO> getPriorityFail();
	//낙찰자 테이블에서 취소가 되면 다음순위 낙찰자 가져오는 쿼리
	public String getBidListNextRank(@Param(value="announcedCode") String announcedCode,
										 @Param(value="rank") int rank);
	
}
