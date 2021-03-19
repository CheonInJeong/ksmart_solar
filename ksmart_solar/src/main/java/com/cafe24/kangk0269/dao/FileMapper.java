package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.FileDTO;

@Mapper
public interface FileMapper {

	public void addFile(List<FileDTO> fileList);
	
	public FileDTO getFileByIdx(String fileIdx);
	
	public int removeFile(String fileIdx);
	
	public List<FileDTO> getAllFile(String fileIdx);
}
