package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
	//공지사항 수정처리
	public int modifyNotice(NoticeDTO noticeDTO);
	
	//공지사항 수정화면
	public NoticeDTO modifyNoticeByIdx(int noticeIdx);
	
	//공지사항 등록
	public int addNotice(NoticeDTO noticeDTO);
	
	//공지사항 조회
	public List<NoticeDTO> getNoticeList();

}
