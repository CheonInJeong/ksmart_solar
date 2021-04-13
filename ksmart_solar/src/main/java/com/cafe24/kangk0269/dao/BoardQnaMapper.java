package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BoardQnaDTO;

@Mapper
public interface BoardQnaMapper {

	//임시저장 불러오기
	public List<BoardQnaDTO> loadQna(String log_id);
	
	//문의 조회수 증가
	public int addQnaViews(int bQnaIdx);
	
	//답글 등록
	public int addReQna(BoardQnaDTO boardQnaDTO);
	
	//문의 삭제
	public int removeQna(int bQnaIdx);
	
	//문의 수정
	public int modifyQna(BoardQnaDTO boardQnaDTO);
	
	//문의 등록
	public int addQna(BoardQnaDTO boardQnaDTO);
		
	//문의 상세조회
	public BoardQnaDTO getQna(int bQnaIdx);
	
	//문의 조회
	public List<BoardQnaDTO> getQnaList(String searchKey, String searchValue);
}
