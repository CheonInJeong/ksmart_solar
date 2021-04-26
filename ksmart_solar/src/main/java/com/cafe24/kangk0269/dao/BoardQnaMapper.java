package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BoardQnaDTO;

@Mapper
public interface BoardQnaMapper {

	// 조회 문의리스트 수
	public int getQnaCnt(String searchKey,String searchValue);
	
	//임서저장 글 불러오기
	public BoardQnaDTO getLoadQna(int bQnaIdx);
	
	//문의사항 임시저장(update)
	public int saveQnaUp(BoardQnaDTO boardQnaDTO);
	
	//문의사항 임시저장(insert)
	public int saveQnaIn(BoardQnaDTO boardQnaDTO);
	
	//부모글 참조번호 추가
	public int addRefCode(int bQnaIdx);
	
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
	
	//문의 등록(update)
	public int addQnaUp(BoardQnaDTO boardQnaDTO);
	//문의 등록(insert)
	public int addQnaIn(BoardQnaDTO boardQnaDTO);
		
	//문의 상세조회
	public BoardQnaDTO getQna(int bQnaIdx);
	
	//문의 조회
	public List<BoardQnaDTO> getQnaList(int start,int end, String searchKey, String searchValue);
}
