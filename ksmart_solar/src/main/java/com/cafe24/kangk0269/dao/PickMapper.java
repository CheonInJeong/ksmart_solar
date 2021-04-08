package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.PickDTO;

@Mapper
public interface PickMapper {
	
	
	//관심목록 중복확인
	public int pickCheck(String announcedCode, String log_id);
	
	//관심목록 등록
	public int addWishlist(String announcedCode, String login_id);
	
	//관심목록 삭제
	public int removeWishlist(int pIdx);
	
	//관심 발전소 조회
	public List<PickDTO> getPlWishList(String log_id);
	
	//관심 발전소 조회
	public List<PickDTO> getCpWishList(String log_id);
}
