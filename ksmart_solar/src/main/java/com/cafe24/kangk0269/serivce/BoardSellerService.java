package com.cafe24.kangk0269.serivce;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BoardSellerMapper;
import com.cafe24.kangk0269.dto.BoardSellerDTO;
import com.cafe24.kangk0269.dto.CommentDTO;

@Service
@Transactional
public class BoardSellerService {
	private final BoardSellerMapper boardSellerMapper;
	
	@Autowired
	public BoardSellerService(BoardSellerMapper boardSellerMapper) {
		this.boardSellerMapper = boardSellerMapper;
	}
	
	//댓글 수정 by 천인정
	public void modifyCmt(int cmtIdx,String comment) {
		boardSellerMapper.modifyCmt(cmtIdx, comment);
	}
	
	//대댓글 등록 by 천인정
	public void addReCmt(int bIdx, String comment,String targetId, String mId,int cmtIdx) {
		int cmtClass = boardSellerMapper.getCmtClass(cmtIdx)+1;
		int cmtOrder = boardSellerMapper.getCmtOrder(cmtIdx)+1;
		int cmtGroupCode = boardSellerMapper.getCmtGroupCode(cmtIdx);
	
		System.out.println(cmtGroupCode+"<---cmtGroupCode");
		CommentDTO commentDto = new CommentDTO();
		commentDto.setbIdx(bIdx);
		commentDto.setCmtClass(cmtClass);
		commentDto.setcmtOrder(cmtOrder);
		commentDto.setCmtComment(comment);
		commentDto.setTargetId(targetId);
		commentDto.setmId(mId);
		commentDto.setCmtIdx(cmtIdx);
		commentDto.setCmtGroupCode(cmtGroupCode);
		boardSellerMapper.addReCmt(commentDto);
		
	}
	
	//댓글 등록 by 천인정
	public void addCmt( int bIdx, String comment, String targetId, String mId) {
		boardSellerMapper.addCmt(bIdx, comment, targetId, mId);
	}
	
	
	//댓글 삭제 by 천인정
	public void removeCmt(int idx) {
		boardSellerMapper.removeCmt(idx);
	}
	//해당 게시글의 댓글 가져오기 by 천인정
	public List<CommentDTO> getCommentList(int idx){
		List<CommentDTO> commentDtoList =  boardSellerMapper.getCommentList(idx);
		//부모댓글 담을 리스트
		List<CommentDTO> commentParentList = new ArrayList<CommentDTO>();
		//자식댓글 담을 리스트
		List<CommentDTO> commentChildList = new ArrayList<CommentDTO>();
		//통합
		List<CommentDTO> commentAllList = new ArrayList<CommentDTO>();
		
		//부모와 자식 분리
		for(CommentDTO commentDTO:commentDtoList) {
			if(commentDTO.getCmtClass()==0) {
				commentParentList.add(commentDTO);
			}else {
				commentChildList.add(commentDTO);
			}
		}

		for(CommentDTO CommentParentDTO : commentParentList) {
			//부모 댓글 넣기(댓글)
			commentAllList.add(CommentParentDTO);
			for(CommentDTO CommentChildDTO : commentChildList) {
				if(CommentParentDTO.getCmtIdx()==CommentChildDTO.getCmtGroupCode()) {
					//자식댓글 넣기(대댓글)
					commentAllList.add(CommentChildDTO);
				}
			}
		}
		
		return commentAllList;
	}
	
	//발전소 문의글 상세내용 가져오기 by 천인정
	public BoardSellerDTO getQnaDetailForSeller(int idx) {
		return boardSellerMapper.getQnaDetailForSeller(idx);
	}
	
	
	//발전소 코드로 문의글 리스트 가져오기 by 천인정
		public List<BoardSellerDTO> getQnaListForSeller(String announceCode){
			return boardSellerMapper.getQnaListForSeller(announceCode);
		}
	
	
}
