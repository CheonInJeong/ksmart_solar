package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.BankAccountMapper;
import com.cafe24.kangk0269.dto.MemberAccountDTO;


@Service
@Transactional
public class AccountService {
	
	private final BankAccountMapper bankAccountMapper;
	
	@Autowired
	public AccountService(BankAccountMapper bankAccountMapper) {
		this.bankAccountMapper = bankAccountMapper; 
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("AccountService bean 등록");
		System.out.println("=========================================================");
	}
	
	//계좌 삭제처리
	public int removeAccount(int mAccountIdx) {
		int result = bankAccountMapper.removeAccount(mAccountIdx);
		return result;
	}
	
	//계좌 수정처리
	public int modifyAccount(MemberAccountDTO memberAccountDTO) {
		int result = bankAccountMapper.modifyAccount(memberAccountDTO);
		return result;
	}
	
	
	//수정을 위한 선택 계좌조회
	public MemberAccountDTO getAccountByIdx(int mAccountIdx) {
		return bankAccountMapper.getAccountByIdx(mAccountIdx);
		
	}
	
	
	//개인 계좌등록
	public int addAccout(MemberAccountDTO memberAccountDTO) {
		return bankAccountMapper.addAccout(memberAccountDTO);
	}
	
	
	//개인 계좌조회
	public List<MemberAccountDTO> getAccountListById(String login_id){
		List<MemberAccountDTO> memberAccountDTOList = bankAccountMapper.getAccountListById(login_id);
		System.out.println(memberAccountDTOList);
		return memberAccountDTOList;
		
	}
	
}
