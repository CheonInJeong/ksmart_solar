package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.NoticeMapper;
import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.NoticeDTO;

@Service
@Transactional
public class NoticeService {
	
	private final NoticeMapper noticeMapper;
	private final FileUtils fileUtils;
	
	@Autowired
	public NoticeService(NoticeMapper noticeMapper,FileUtils fileUtils) {
		this.noticeMapper = noticeMapper;
		this.fileUtils = fileUtils;
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("NoticeService bean 등록");
		System.out.println("=========================================================");
	}
	
	//공지 조회수 증가
	public int addNoticeViews(int noticeIdx) throws Exception {
		return noticeMapper.addNoticeViews(noticeIdx);
	}
	
	//공지 삭제
	public int removeNotice(int noticeIdx) {
		return noticeMapper.removeNotice(noticeIdx);
	}
	
	//공지사항 상세조회
	public NoticeDTO getNotice(int noticeIdx) {
		return noticeMapper.getNotice(noticeIdx);
	}
	
	//공지사항  수정처리
	public int modifyNotice(NoticeDTO noticeDTO) {
		int result = noticeMapper.modifyNotice(noticeDTO);
		return result;
	}
	
	//공지사항 수정화면
	public NoticeDTO modifyNoticeByIdx(int noticeIdx) {
		return noticeMapper.modifyNoticeByIdx(noticeIdx);
		
	}
	
	//공지사항 파일 참조번호 수정
	public void modifyFileReference(int noticeIdx) {
		noticeMapper.modifyFileReference(noticeIdx);
	}
	
	//공지사항 파일조회
	public List<FileDTO> getNoticeFileList(int noticeIdx){
		
		return noticeMapper.getNoticeFileList(noticeIdx);
	}	
	
	//공지사항 파일등록
	public void addNoticeFile(MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request)throws Exception {
		List<FileDTO> filelist = fileUtils.parseFileInfo("공지서류",3,"공지서류", multipartHttpServletRequest,request);
		if(CollectionUtils.isEmpty(filelist)==false) {
			noticeMapper.addFile(filelist);
		}
	}
	
	//공지사항 등록
	public int addNotice(NoticeDTO noticeDTO){
		return noticeMapper.addNotice(noticeDTO);
		
	}
	
	//공지사항 조회
	public List<NoticeDTO> getNoticeList(){
		List<NoticeDTO> noticeDTOList = noticeMapper.getNoticeList();
		return noticeDTOList;
	}
	
	
}
