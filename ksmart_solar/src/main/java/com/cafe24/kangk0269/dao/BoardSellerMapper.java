package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.FileDTO;


@Mapper
public interface BoardSellerMapper {
	
	//파일 등록 메서드
	public int addFile(List<FileDTO> list);
	//파일 삭제 메서드
	public int removeApplyFile(String code);
	
	// 파일 리스트 가져오기
	public List<FileDTO> getFileList(FileDTO fileDto);
	
}
