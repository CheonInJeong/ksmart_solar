package com.cafe24.kangk0269.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	
	//임시저장 불러오기
	@GetMapping("/help/loadQna")
	public void loadQna(Model model, HttpSession session) {
		String log_id = (String)session.getAttribute("SID");
		List<BoardQnaDTO> boardQnaDTOList = boardQnaService.loadQna(log_id); 
		model.addAttribute("boardQnaDTOList", boardQnaDTOList);
	}
	 
	
	//문의답글 처리
	@PostMapping("/help/addReQna")
	public String addReQna(BoardQnaDTO boardQnaDTO
							,@RequestParam(name = "bQnaIdx", required = false ) int bQnaIdx) {
		boardQnaService.addReQna(boardQnaDTO);
		boardQnaService.addRefCode(bQnaIdx);
		return "redirect:/help/qna"; 
	}
	 
	//문의답글 화면
	@GetMapping("/help/addReQna")
	public String addReQna(Model model
							,@RequestParam(name = "bQnaIdx", required = false ) int bQnaIdx) {
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
	public String Qna(Model model
						,@RequestParam(value="searchKey", required = false) String searchKey 
						,@RequestParam(value="searchValue", required = false) String searchValue) {
		if(searchKey != null && searchKey.equals("null")) {
			searchKey = null;
		}
		if(searchValue != null && searchValue.equals("null")) {
			searchValue = null;
		}
		
		List<BoardQnaDTO> boardQnaDTOList = boardQnaService.getQnaList(searchKey, searchValue );
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
	
	//공지 상세조회 + 공지 조회수 증가 + 파일조회
	@GetMapping("/help/getNotice")
	public String getNotice(Model model,
								@RequestParam(name = "noticeIdx", required = false ) int noticeIdx) throws Exception{
		
		noticeService.addNoticeViews(noticeIdx);
		
		NoticeDTO noticeDTO = noticeService.getNotice(noticeIdx);
		model.addAttribute("noticeDTO", noticeDTO);
		model.addAttribute("fileList", noticeService.getNoticeFileList());
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
	
	
	//공지등록 + 파일등록 처리
	@PostMapping("/help/addNotice")
	public String addNotice(NoticeDTO noticeDTO, MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request) throws Exception {
		System.out.println("noticeDTO-->" + noticeDTO);
		noticeService.addNotice(noticeDTO);
		noticeService.addNoticeFile(multipartHttpServletRequest, request);
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
