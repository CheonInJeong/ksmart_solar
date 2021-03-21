package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;


@Mapper
public interface BoardMapper {
	
	public int insertBoardFileList(List<BoardFileDTO> list);
	
	public int updateBoard(int boardIdx);
	
	public int updateBoardDetail(BoardDto boardDto);
	
	public int deleteBoard(int boardIdx);
	
	public int updateHitCount(int boardIdx);
	
	public BoardDto selectBoardDetail(int boardIdx);
	
	public int insertBoard(BoardDto boardDto) throws Exception;

	public List<BoardDto> selectBoardList() throws Exception;
}
