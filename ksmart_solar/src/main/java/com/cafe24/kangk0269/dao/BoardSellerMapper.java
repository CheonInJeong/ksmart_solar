package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.kangk0269.dto.BoardSellerDTO;
import com.cafe24.kangk0269.dto.CommentDTO;
import com.cafe24.kangk0269.dto.FileDTO;


@Mapper
public interface BoardSellerMapper {
	
	//댓글 수정 by 천인정
	public int modifyCmt(@Param(value="cmtIdx") int cmtIdx,
						 @Param(value="cmtComment") String comment);
	
	//댓글의 크룹 코드 가져오기 by천인정
	public int getCmtGroupCode(int cmtIdx);
	
	//댓글 계층 가져오기 by 천인정
	public int	getCmtClass(int cmtIdx);
	
	//댓글순서의 최댓값 가져오기 by 천인정 
	public int	getCmtOrder(int cmtIdx);
	
	//대댓글 등록 by 천인정
	public int addReCmt(CommentDTO commentDTO);
	
	//댓글 등록 by 천인정
	public int addCmt(@Param(value="bIdx") int bIdx,
					  @Param(value="cmtComment") String comment,
					  @Param(value="targetId") String targetId,
					  @Param(value="mId") String mId);
	//댓글 삭제 by 천인정
	public int removeCmt(int idx);
	
	//해당 게시글의 댓글 가져오기 by 천인정
	public List<CommentDTO> getCommentList(int idx);
	
	//발전소 문의글 상세내용 가져오기 by 천인정
	public BoardSellerDTO getQnaDetailForSeller(int idx);
	
	//발전소 코드로 문의글 리스트 가져오기 by 천인정
	public List<BoardSellerDTO> getQnaListForSeller(String announceCode);
	
	//파일 등록 메서드
	public int addFile(List<FileDTO> list);
	//파일 삭제 메서드
	public int removeApplyFile(String code);
	
	// 파일 리스트 가져오기
	public List<FileDTO> getFileList(FileDTO fileDto);
	
}
