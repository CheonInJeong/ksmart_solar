package com.cafe24.kangk0269.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.common.ScriptUtils;
import com.cafe24.kangk0269.dto.BoardQnaDTO;
import com.cafe24.kangk0269.dto.NoticeDTO;
import com.cafe24.kangk0269.serivce.BoardQnaService;
import com.cafe24.kangk0269.serivce.NoticeService;

@Controller
public class HelpController {
	
	private final NoticeService noticeService;
	private final BoardQnaService boardQnaService;
	
	@Autowired
	public HelpController(NoticeService noticeService, BoardQnaService boardQnaService) {
		this.noticeService = noticeService;
		this.boardQnaService = boardQnaService;
	}
	
	
	//문의 검색
	@GetMapping("/help/QnaList")
	public String getQnaList(@RequestParam(name = "searchKey", required = false) String searchKey,
							 @RequestParam(name = "searchValue", required = false) String searchValue
							 ,Model model) {
		List<BoardQnaDTO> boardQnaDTOList = boardQnaService.getQnaList(searchKey, searchValue);
		model.addAttribute("boardQnaDTOList", boardQnaDTOList);
		return "/help/qna";
	}
	 
	/*
	 * //답글 처리
	 * 
	 * @PostMapping("/help/addReQna") public String addReQna(BoardQnaDTO
	 * boardQnaDTO) { boardQnaService.addReQna(boardQnaDTO); return
	 * "redirect:/help/qna"; }
	 */
	//답글 화면
	@GetMapping("/help/addReQna")
	public String addReQna(Model model,
							@RequestParam(name = "bQnaIdx", required = false ) int bQnaIdx) {
		getQna(model, bQnaIdx);
		return "/help/addReQna";
		
	}
	
	//문의 삭제
	@GetMapping("/help/removeQna")
	public String removeQna(@RequestParam(name = "bQnaIdx", required = false ) int bQnaIdx) {
		int result = boardQnaService.removeQna(bQnaIdx);
		return "redirect:/help/qna";
	}
	
	//문의 수정처리
	@PostMapping("/help/modifyQna")
	public String modifyQna(BoardQnaDTO boardQnaDTO) {
		int result = boardQnaService.modifyQna(boardQnaDTO);
		return "redirect:/help/qna";
		
	}
	
	//문의 수정화면
	@GetMapping("/help/modifyQna")
	public String modifyQna(Model model,
							@RequestParam(name = "bQnaIdx", required = false ) int bQnaIdx) {
		getQna(model, bQnaIdx);
		return "/help/modifyQna";
	}
	
	//문의 등록처리
	@PostMapping("/help/addQna")
	public String addQna(BoardQnaDTO boardQnaDTO) {
		boardQnaService.addQna(boardQnaDTO);
		return "redirect:/help/qna";
		
	}
	
	// 문의 등록화면
	@GetMapping("/help/addQna")
	public String addQna(HttpServletResponse response, HttpSession session) throws IOException {
		String log_id = (String)session.getAttribute("SID");
		if(log_id == null) {
			ScriptUtils.alertAndMovePage(response, "로그인해주세요", "/login");
		}
		return "/help/addQna";
	}
	
	// 문의 상세조회 + 문의 조회수 증가
	@GetMapping("/help/getQna")
	public String getQna(Model model,
							@RequestParam(name = "bQnaIdx", required = false) int bQnaIdx) {
		
		boardQnaService.addQnaViews(bQnaIdx);
		
		BoardQnaDTO boardQnaDTO = boardQnaService.getQna(bQnaIdx);
		model.addAttribute("boardQnaDTO", boardQnaDTO);
		return "/help/getQna";
		
	}
	
	
	// 문의 조회
	@GetMapping("/help/qna")
	public String Qna(Model model) {
		List<BoardQnaDTO> boardQnaDTOList = boardQnaService.getQnaList();
		model.addAttribute("boardQnaDTOList", boardQnaDTOList);
		return "/help/qna";
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//공지 삭제
	@GetMapping("/help/removeNotice")
	public String removeNotice(@RequestParam(name = "noticeIdx", required = false ) int noticeIdx) {
		int result = noticeService.removeNotice(noticeIdx);
		return "redirect:/help/notice";
	}
	
	//공지 상세조회 + 공지 조회수 증가
	@GetMapping("/help/getNotice")
	public String getNotice(Model model,
								@RequestParam(name = "noticeIdx", required = false ) int noticeIdx) throws Exception{
		
		noticeService.addNoticeViews(noticeIdx);
		
		NoticeDTO noticeDTO = noticeService.getNotice(noticeIdx);
		model.addAttribute("noticeDTO", noticeDTO);
		return "/help/getNotice";
		
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
		System.out.println("noticeDTO-->" + noticeDTO);
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
}
