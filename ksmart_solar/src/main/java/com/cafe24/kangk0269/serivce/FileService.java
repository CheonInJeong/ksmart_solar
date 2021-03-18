package com.cafe24.kangk0269.serivce;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dao.FileMapper;
import com.cafe24.kangk0269.dto.FileDTO;

import jdk.internal.org.jline.utils.Log;

@Service
public class FileService implements FileMapper{

	@Override
	public void addFile(FileDTO fileDto, MultipartHttpServletRequest multipartHttpServletRequest) {
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)==false) {
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name = iterator.next();
				Log.debug("file tag name : "+ name);
				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile : list) {
					Log.debug("start file information");
					Log.debug("file name:"+multipartFile.getOriginalFilename());;
					Log.debug("file size:"+multipartFile.getSize());
					Log.debug("file content type: " + multipartFile.getContentType());
					Log.debug("end file information");
				}
			}
		}
		
	}
	
	
}
