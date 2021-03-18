package com.cafe24.kangk0269.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.FileDTO;

@Mapper
public interface FileMapper {

	public void addFile(FileDTO fileDto, MultipartHttpServletRequest multipartHttpServletRequest );
	
}
