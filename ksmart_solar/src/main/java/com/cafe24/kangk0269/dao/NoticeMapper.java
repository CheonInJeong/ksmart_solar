package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
	
	// 공지 리스트 수
	public int getNoticeCnt(String searchKey,String searchValue);	
	
	//공지 조회수 증가
	public int addNoticeViews(int noticeIdx);
	
	//공지 삭제
	public int removeNotice(int noticeIdx);
	
	//공지사항 파일조회
	public List<FileDTO> getNoticeFileList(int noticeIdx);
	
	//공지사항 상세조회
	public NoticeDTO getNotice(int noticeIdx);
	
	//공지사항 수정처리
	public int modifyNotice(NoticeDTO noticeDTO);
	
	//공지사항 수정화면
	public NoticeDTO modifyNoticeByIdx(int noticeIdx);
	
	//공지사항 파일참조번호 수정
	public void modifyFileReference(int noticeIdx); 
	
	//공지사항 파일등록
	public int addFile(List<FileDTO> list);
	
	//공지사항 등록
	public int addNotice(NoticeDTO noticeDTO);
	
	//공지사항 조회
	public List<NoticeDTO> getNoticeList(int start,int end, String searchKey, String searchValue);

}
