package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BoardSellerDTO;
import com.cafe24.kangk0269.dto.FileDTO;


@Mapper
public interface BoardSellerMapper {
	
	//발전소 문의글 상세내용 가져오기 by 천인정
	public BoardSellerDTO getQnaDetailForSeller(int idx);
	
	//발전소 코드로 문의글 리스트 가져오기 by 천인정
	public List<BoardSellerDTO> getQnaListForSeller(String announceCode);
	
	//파일 등록 메서드
	public int addFile(List<FileDTO> list);
	//파일 삭제 메서드
	public int removeApplyFile(String code);
	
	// 파일 리스트 가져오기
	public List<FileDTO> getFileList(FileDTO fileDto);
	
}
