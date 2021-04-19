package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.TradePriorityDTO;

@Mapper
public interface ScheduledMapper {

	public int addPayIn(@Param(value="tradePriorityDTO") TradePriorityDTO tradePriorityDTO,
						@Param(value="tomorrow") String tomorrow);
	
	public List<TradePriorityDTO> getPriority();
	
}
