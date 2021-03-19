package com.cafe24.kangk0269.serivce;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dao.FileMapper;
import com.cafe24.kangk0269.dto.FileDTO;



@Service
public class FileService implements FileMapper{

	private FileMapper fileMapper;
	
	public FileService(FileMapper fileMapper) {
		 this.fileMapper = fileMapper;
	}
	
	
	@Override
	public void addFile(List<FileDTO> fileList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FileDTO getFileByIdx(String fileIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeFile(String fileIdx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FileDTO> getAllFile(String fileIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
