package com.cafe24.kangk0269.serivce;

import java.util.List;
import java.util.Map;

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
	
	// 등록된 사업장
	public List<String> checkBusiness(){
		
		return memberMapper.checkBusiness();
	}
	
	// 전체 회원정보수정
	public int modifyMember(MemberDTO member) {
		int result = memberMapper.modifyMember(member);
		return result;
	}
	
	//개인 회원정보수정
	public int modifyMyInfo(MemberDTO memberDTO) {
		int result = memberMapper.modifyMyInfo(memberDTO);
		return result;
	}
	
	
	//개인 회원조회
	public MemberDTO getMyInfoById(String login_id) {
		MemberDTO memberDTO = memberMapper.getMyInfoById(login_id);
		return memberDTO;
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
				member.setmLevelName("일반사업자");
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


	public int addMember(MemberDTO memberDTO) {
		int result = memberMapper.addMember(memberDTO);
		return result;
	}
	public List<String> getManager() {
		List<String> managerList = memberMapper.getManager();
		return managerList;
	}
	
	public List<MemberDTO> getAllMember(){
		List<MemberDTO> memberList = memberMapper.getAllMember();
		for(int i=0; i < memberList.size(); i++) {
			int mLevel = memberList.get(i).getmLevel();
			if(mLevel == 1) {
				memberList.get(i).setmLevelName("관리자");
			}
			if(mLevel == 2) {
				memberList.get(i).setmLevelName("태양광사업자(판매자)	");
			}
			if(mLevel == 3) {
				memberList.get(i).setmLevelName("재활용 중고 사업자(구매자)");
			}
			if(mLevel == 4) {
				memberList.get(i).setmLevelName("일반회원");
			}
		}
		
		return memberList;

	}
}
