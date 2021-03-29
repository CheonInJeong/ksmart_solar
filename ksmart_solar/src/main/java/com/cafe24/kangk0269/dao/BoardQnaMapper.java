package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BoardQnaDTO;

@Mapper
public interface BoardQnaMapper {

	//문의 상세조회
	public BoardQnaDTO getQna(int bQnaIdx);
	
	//문의 조회
	public List<BoardQnaDTO> getQnaList();
}
