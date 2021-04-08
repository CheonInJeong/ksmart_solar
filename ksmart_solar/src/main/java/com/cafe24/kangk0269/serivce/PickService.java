package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.PickMapper;
import com.cafe24.kangk0269.dto.PickDTO;

@Service
@Transactional
public class PickService {
	private final PickMapper pickMapper;
	
	@Autowired
	public PickService(PickMapper pickMapper) {
		this.pickMapper = pickMapper;
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("PickService bean 등록");
		System.out.println("=========================================================");
	}
	
	//중복 등록 확인 
	public int pickCheck(String announcedCode, String log_id) {
		return pickMapper.pickCheck(announcedCode, log_id);
	}
	
	//관심목록 등록
	public int addWishlist(String announcedCode, String login_id) {
		return pickMapper.addWishlist(announcedCode, login_id);
	}
	
	//관심목록 삭제
	public int removeWishlist(int pIdx) {
		return pickMapper.removeWishlist(pIdx); 
	}
	
	//관심 발전소목록 조회
	public List<PickDTO> getPlWishList(String log_id){
		List<PickDTO> pickDTOList = pickMapper.getPlWishList(log_id);
		return pickDTOList;
	}
		
	//관심 부품목록 조회
	public List<PickDTO> getCpWishList(String log_id){
		List<PickDTO> pickDTOList = pickMapper.getCpWishList(log_id);
		return pickDTOList;
	}
}
