package com.cafe24.kangk0269.serivce;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.BoardMapper;
import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;


@Service
public class BoardService {

	@Autowired
	private final BoardMapper boardMapper;
	@Autowired
	private final FileUtils fileUtils;

	public BoardService(BoardMapper boardMapper, FileUtils fileUtils) {
		this.boardMapper = boardMapper;
		this.fileUtils = fileUtils;
	}
	
	public BoardFileDTO selectBoardFileInfo(int idx, int boardIdx) throws Exception{
		return boardMapper.selectBoardFileInfo(idx,boardIdx);
	}

	public int updateBoardDetail(BoardDto boardDto) {
		int result = boardMapper.updateBoardDetail(boardDto);
		return result;
	}

	public int updateBoard(int boardIdx) {
		int result = boardMapper.updateBoard(boardIdx);
		return result;
	}

	public int deleteBoard(int boardIdx) {
		int result = boardMapper.deleteBoard(boardIdx);
		return result;
	}

	public BoardDto selectBoardDetail(int boardIdx) {
		BoardDto boardDto = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFileDTO> fileList = boardMapper.selectBoardFileList(boardIdx); 
		boardDto.setFileList(fileList);
		boardMapper.updateHitCount(boardIdx);
		return boardDto;

	}

	public List<BoardDto> selectBoardList() throws Exception {

		return boardMapper.selectBoardList();
	}

	public void boardInsert(BoardDto boardDto, MultipartHttpServletRequest multipartHttpServletRequest)
			throws Exception {
		boardMapper.insertBoard(boardDto);
		/*
		List<BoardFileDTO> filelist = fileUtils.parseFileInfo(boardDto.getBoardIdx(), multipartHttpServletRequest);
		System.out.println("실행확인1");
		if (CollectionUtils.isEmpty(filelist) == false) {
			System.out.println("실행확인2");
			boardMapper.insertBoardFileList(filelist);
		}
		 */
		/*
		 * if(ObjectUtils.isEmpty(multipartHttpServletRequest)==false) {
		 * 
		 * Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		 * String name; while(iterator.hasNext()) { name = iterator.next();
		 * //Log.debug("file tag name : " + name); List<MultipartFile> list =
		 * multipartHttpServletRequest.getFiles(name);
		 * 
		 * for(MultipartFile multipartFile : list) { Log.debug("start file info");
		 * log.debug("file name : "+ multipartFile.getOriginalFilename());
		 * log.debug("file size : "+ multipartFile.getSize());
		 * log.debug("file content type : " + multipartFile.getContentType());
		 * Log.debug("end file info"); }
		 * 
		 * } }
		 */

	}

}
