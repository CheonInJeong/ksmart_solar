package com.cafe24.kangk0269.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.FileDTO;

@Component
public class FileUtils {

	public List<FileDTO> parseFileInfo(MultipartHttpServletRequest multipartHttpServletRequest){
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		List<FileDTO> fileList = new ArrayList();
		
		return null;
	}
}
