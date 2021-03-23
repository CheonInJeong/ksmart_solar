package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;


@Mapper
public interface BoardMapper {
	//@Param 어노테이션 : 해당 파라미터들이 Map에 저장
	public BoardFileDTO selectBoardFileInfo(@Param("idx") int idx,
											@Param("boardIdx") int boardIdx);
	
	public List<BoardFileDTO> selectBoardFileList(int boardIdx);
	public int insertBoardFileList(List<BoardFileDTO> list);
	
	public int updateBoard(int boardIdx);
	
	public int updateBoardDetail(BoardDto boardDto);
	
	public int deleteBoard(int boardIdx);
	
	public int updateHitCount(int boardIdx);
	
	public BoardDto selectBoardDetail(int boardIdx);
	
	public int insertBoard(BoardDto boardDto) throws Exception;

	public List<BoardDto> selectBoardList() throws Exception;
}
