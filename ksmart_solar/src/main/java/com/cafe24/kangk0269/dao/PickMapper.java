package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.PickDTO;

@Mapper
public interface PickMapper {
	
	//관심목록 삭제
	public int removeWishlist(int pIdx);
	
	//관심목록 조회
	public List<PickDTO> getWishList(String log_id);
}
