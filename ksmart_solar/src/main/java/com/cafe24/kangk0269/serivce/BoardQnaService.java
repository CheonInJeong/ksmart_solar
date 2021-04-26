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
	
	
	
	//조회 문의리스트 수
	public int getQnaCnt(String searchKey,String searchValue) {
		if(searchKey != null) {
			if("bQnaSubject".equals(searchKey)) {
				searchKey = "b_qna_subject";
			}else if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}
		}		
		return boardQnaMapper.getQnaCnt(searchKey, searchValue);
	}	
	
	//임서저장 글 불러오기
	public BoardQnaDTO getLoadQna(int bQnaIdx) {
		return boardQnaMapper.getLoadQna(bQnaIdx);
	}
	
	
	//문의사항 임시저장(update) 
	public int saveQnaUp(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.saveQnaUp(boardQnaDTO);
	}
	
	//문의사항 임시저장(insert)
	public int saveQnaIn(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.saveQnaIn(boardQnaDTO);
	}
	
	//부모글 참조번호 추가
	public void addRefCode(int bQnaIdx) {
		boardQnaMapper.addRefCode(bQnaIdx);
	}
	
	//임시저장 불러오기
	public List<BoardQnaDTO> loadQna(String log_id) {
		return boardQnaMapper.loadQna(log_id);
	}
	
	//문의 조회수 증가
	public int addQnaViews(int bQnaIdx) {
		return boardQnaMapper.addQnaViews(bQnaIdx);
	}
	
	//문의 답글처리
	public int addReQna(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.addReQna(boardQnaDTO);
	}
	
	//문의 삭제
	public int removeQna(int bQnaIdx) {
		return boardQnaMapper.removeQna(bQnaIdx);
	}
	
	//문의 수정
	public int modifyQna(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.modifyQna(boardQnaDTO);
	}
	
	//문의 등록(update)
	public int addQnaUp(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.addQnaUp(boardQnaDTO);
	}
	
	//문의 등록(insert)
	public int addQnaIn(BoardQnaDTO boardQnaDTO) {
		return boardQnaMapper.addQnaIn(boardQnaDTO);
	}
	
	//문의 상세조회
	public BoardQnaDTO getQna(int bQnaIdx) {
		return boardQnaMapper.getQna(bQnaIdx);
	}
	
	//문의 조회
	public List<BoardQnaDTO> getQnaList(int start,int end, String searchKey, String searchValue){
		
		if(searchKey != null) {
			if("bQnaSubject".equals(searchKey)) {
				searchKey = "b_qna_subject";
			}else if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}
		}
		List<BoardQnaDTO> boardQnaDTOList = boardQnaMapper.getQnaList(start, end, searchKey, searchValue);
		return boardQnaDTOList;
		
	}
}
