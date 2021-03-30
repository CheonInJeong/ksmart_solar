package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BoardQnaMapper;
import com.cafe24.kangk0269.dto.BoardQnaDTO;

@Service
@Transactional
public class BoardQnaService {
	private final BoardQnaMapper boardQnaMapper;
	
	@Autowired
	public BoardQnaService(BoardQnaMapper boardQnaMapper) {
		this.boardQnaMapper = boardQnaMapper;
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("BoardQnaService bean 등록");
		System.out.println("=========================================================");
	}
	
	
	//문의 삭제
	public int removeQna(int bQnaIdx) {
		return boardQnaMapper.removeQna(bQnaIdx);
	}
	
	//문의 수정
	public int modifyQna(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.modifyQna(boardQnaDTO);
	}
	
	//문의 등록
	public int addQna(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.addQna(boardQnaDTO);
	}
	
	//문의 상세조회
	public BoardQnaDTO getQna(int bQnaIdx) {
		return boardQnaMapper.getQna(bQnaIdx);
	}
	
	//문의 조회
	public List<BoardQnaDTO> getQnaList(){
		List<BoardQnaDTO> boardQnaDTOList = boardQnaMapper.getQnaList();
		return boardQnaDTOList;
		
	}
}
