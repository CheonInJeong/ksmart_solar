package com.cafe24.kangk0269.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.serivce.BoardService;


@Controller
public class boardController {
	
	@Autowired
	private final BoardService boardService;
	public boardController(BoardService boardService) {
		this.boardService = boardService;
	}
	@PostMapping("/board/updateBoard")
	public String updateBoard(BoardDto boardDto) throws Exception {
		System.out.println("들어와라");
		boardService.updateBoardDetail(boardDto);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/updateBoard")
	public ModelAndView updateBoard(@RequestParam(name="boardIdx",required=false) int boardIdx) {
		
		ModelAndView mv = new ModelAndView("/board/boardUpdate");
		BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
		mv.addObject("update", boardDto);
		return mv;
	}
	
	@GetMapping("/board/deleteBoard")
	public String deleteBoard(@RequestParam(name="boardIdx",required=false) int boardIdx) {
		boardService.deleteBoard(boardIdx);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/openBoardDetail")
	public ModelAndView openBoardDetail(@RequestParam(name="boardIdx",required = false) int boardIdx ) {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		BoardDto boardDto = boardService.selectBoardDetail(boardIdx);
		mv.addObject("detail", boardDto);
		return mv;
	}
	
	@PostMapping("/board/boardInsert")
	public String boardInsert(BoardDto boadDto, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardService.boardInsert(boadDto,multipartHttpServletRequest);
		return "redirect:/board/boardList";
	}
	
	@GetMapping("/board/boardWrite")
	public ModelAndView openBoardWrite() throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardWrite");
		return mv;
	}
	
	@GetMapping("/board/boardList")
	public String openBoardList(Model model) throws Exception {
		System.out.println("컨트롤러실행");
		//호출 된 요청의 결과를 보여 줄 뷰를 지정.
		
		List<BoardDto> list = boardService.selectBoardList();
		model.addAttribute("list",list);
		return "/board/boardList";
	}
}
