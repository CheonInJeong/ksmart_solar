package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.dto.NoticeDTO;
import com.cafe24.kangk0269.serivce.NoticeService;

@Controller
public class HelpController {
	
	private final NoticeService noticeService;
	
	@Autowired
	public HelpController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	//공지 수정처리
	@PostMapping("/help/modifyNotice")
	public String modifyNoticeByIdx(NoticeDTO noticeDTO) {
		int result = noticeService.modifyNotice(noticeDTO);
		return "redirect:/help/notice";
	}
	
	
	//공지 수정화면
	@GetMapping("/help/modifyNotice")
	public String modifyNoticeByIdx(Model model,
									@RequestParam(name = "noticeIdx", required = false ) int noticeIdx) {
		NoticeDTO noticeDTO = noticeService.modifyNoticeByIdx(noticeIdx);
		model.addAttribute("noticeDTO", noticeDTO);
		return "/help//modifyNotice";
	}
	
	//공지등록 처리
	@PostMapping("/help/addNotice")
	public String addNotice(NoticeDTO noticeDTO) {
		System.out.println("noticeDTO-->" + noticeDTO);
		noticeService.addNotice(noticeDTO);
		return "redirect:/help/notice";
		
	}
	
	//공지등록 화면
	@GetMapping("/help/addNotice")
	public String addNotice() {
		return "/help/addNotice";
		
	}
	
	//공지사항 조회
	@GetMapping("/help/notice")
	public String Notice(Model model ) {
		List<NoticeDTO> noticeDTOList = noticeService.getNoticeList();
		model.addAttribute("noticeDTOList", noticeDTOList);
		return "/help/notice";
	}
	
	@GetMapping("/help/qna")
	public String Qna() {
		
		return "/help/qna";
	}
}
