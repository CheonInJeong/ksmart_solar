package com.cafe24.kangk0269.serivce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.MemberMapper;
import com.cafe24.kangk0269.dto.MemberDTO;

@Service
@Transactional
public class MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	
	private final MemberMapper memberMapper;
	
	@Autowired
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	//아이디를 입력해서 회원정보를 검색하는 메서드
	public MemberDTO getMemberInfoById(String mId) {
		MemberDTO member = memberMapper.getMemberInfoById(mId);
		int memberLevel = 0;
		
		if(member != null && member.getmLevel() != 0) {
			memberLevel += member.getmLevel();
			
			switch (memberLevel) {
			case 1:
				member.setmLevelName("관리자");
				break;
			case 2:
				member.setmLevelName("태양광사업자");
				break;
			case 3:
				member.setmLevelName("재활용사업자");
				break;
			case 4:
				member.setmLevelName("일반회원");
				break;
			default:
				member.setmLevelName("비회원");
				break;
			}
		}
		return member;
	}

	public void addMember(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
	}
}
