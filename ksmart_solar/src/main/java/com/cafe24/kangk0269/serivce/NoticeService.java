package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.NoticeMapper;
import com.cafe24.kangk0269.dto.NoticeDTO;

@Service
@Transactional
public class NoticeService {
	
	private final NoticeMapper noticeMapper;
	
	@Autowired
	public NoticeService(NoticeMapper noticeMapper) {
		this.noticeMapper = noticeMapper;
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("NoticeService bean 등록");
		System.out.println("=========================================================");
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
