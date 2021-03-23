package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.MemberMapper;
import com.cafe24.kangk0269.dto.MemberDTO;

@Service
@Transactional
public class MemberService {

	private final MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	public List<MemberDTO> getAllMember(){
		
		return memberMapper.getAllMember();
	}
}
