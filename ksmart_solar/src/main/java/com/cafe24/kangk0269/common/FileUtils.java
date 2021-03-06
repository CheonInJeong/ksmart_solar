package com.cafe24.kangk0269.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.BoardFileDTO;

//첨부파일 정보 가공 및 지정된 위치에 파일 저장
@Component
public class FileUtils {

	public List<BoardFileDTO> parseFileInfo(int boardIdx, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		//파일이 업로드 될 폴더 생성
		List<BoardFileDTO> fileList = new ArrayList<>();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		//오늘의 날짜 확인
		ZonedDateTime current = ZonedDateTime.now();
		//파일 경로 지정
		String path ="C:/upload/"+current.format(format);
		
		File file = new File(path);
		//경로 및 파일이 존재하지 않으면
		if(file.exists()==false) {
			//모든 경로 생성
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		String newFileName, originalFileExtension,contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				if(multipartFile.isEmpty()==false) {
					contentType=multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						//if 조건 충족 시 for 반복문을 빠져나감
						break;
					}else {
						if(contentType.contains("image/jepg")) {
							originalFileExtension = ".jpg";
						}else if(contentType.contains("image/png")) {
							originalFileExtension = ".png";
						}else if(contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						}else if(contentType.contains("text")) {
							originalFileExtension =".text";
						}else if(contentType.contains("pdf")) {
							originalFileExtension = ".pdf";
						}else if(contentType.contains("hwp")){
							originalFileExtension = ".hwp";
						}else {
							break;
						}
					}
					
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					
					BoardFileDTO boardFileDto =  new BoardFileDTO();
					boardFileDto.setBoardIdx(boardIdx);
					boardFileDto.setFileSize(multipartFile.getSize());
					boardFileDto.setOriginalFileName(multipartFile.getOriginalFilename());
					boardFileDto.setStoredFilePath(path+"/"+newFileName);
					fileList.add(boardFileDto);
					
					//업로드 된 파일을 새로운 이름으로 바꾸어 지정된 경로에 저장
					file = new File(path+"/"+newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
	
	
}
