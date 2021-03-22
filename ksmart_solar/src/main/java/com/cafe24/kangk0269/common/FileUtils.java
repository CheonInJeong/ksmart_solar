package com.cafe24.kangk0269.common;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.FileDTO;

@Component
public class FileUtils {
	//오늘 날짜
	private final String today= LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	//업로드 경로
	private final String uploadPath = Paths.get("C:","upload",today).toString();
	
	//서버에 생성할 파일명을 처리할 랜덤 문자열 반환
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public List<FileDTO> uploadFile(MultipartFile[] files, String fileIdx){
		//파일이 비어있으면 비어있는 리스트 반환
		if(files[0].getSize()<1) {
			return Collections.emptyList();
		}
		//업로드 파일 정보를 담을 리스트
		List<FileDTO> fileList = new ArrayList<>();
		
		//uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉토리 생성
		File dir = new File(uploadPath);
		if(dir.exists()==false) {
			dir.mkdirs();
		}
		
		//파일 개수만큼 forEach 실행
		for(MultipartFile file: files) {
			try {
				//파일확장자
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				//서버에 저장할 파일명
				final String saveName = getRandomString() + "." + extension;
				//업로드 경로에 saveName과 동일한 이름을 가진 파일 생성
				File target = new File(uploadPath, saveName);
				//서버에 파일 저장
				file.transferTo(target);
				
				//파일 정보 저장
				FileDTO fileDto = new FileDTO();
				fileDto.setFileSaveName(file.getOriginalFilename());
				fileDto.setFileName(saveName);
				
				fileList.add(fileDto);
				
			}catch(Exception e) {
				
				e.printStackTrace();
			}
		}
		return fileList;
		
	}
}
