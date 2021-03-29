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
	
	//문의 조회
	public List<BoardQnaDTO> getQnaList(){
		List<BoardQnaDTO> boardQnaDTOList = boardQnaMapper.getQnaList();
		return boardQnaDTOList;
		
	}
}
