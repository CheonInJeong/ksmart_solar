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
	
	//관심목록 삭제
	public int removeWishlist(int pIdx) {
		return pickMapper.removeWishlist(pIdx); 
	}
	
	//관심목록 조회
	public List<PickDTO> getWishList(String log_id){
		List<PickDTO> pickDTOList = pickMapper.getWishList(log_id);
		return pickDTOList;
	}
}
