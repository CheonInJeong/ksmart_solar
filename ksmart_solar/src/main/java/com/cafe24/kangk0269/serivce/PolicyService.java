package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.kangk0269.dao.PolicyMapper;
import com.cafe24.kangk0269.dto.StandardDTO;

@Service
public class PolicyService {

	@Autowired
	private final PolicyMapper policyMapper;
	
	
	public List<StandardDTO> getTradeHistory(String startDate, String endDate){
		return policyMapper.getTradeHistory(startDate,endDate);
	}

	
	public List<StandardDTO> getCommissionHistory(String startDate, String endDate){
		return policyMapper.getCommissionHistory(startDate,endDate);
	}

	
	public List<StandardDTO> getDepositHistory(String startDate, String endDate){
			return policyMapper.getDepositHistory(startDate,endDate);
	}
	
	public PolicyService(PolicyMapper policyMapper) {
		this.policyMapper = policyMapper;
	}
	
	public void removeCommission(int idx) {
		policyMapper.removeCommission(idx);
	}
	
	
	public void addNewCommission(StandardDTO standardDto) {
		policyMapper.addNewCommission(standardDto);
	}
	
	public void addNewTrade(StandardDTO standardDto) {
		policyMapper.addNewTrade(standardDto);
	}
	
	public void addNewDeposit(StandardDTO standardDto) {
		policyMapper.addNewDeposit(standardDto);
	}
	

	public void modifyTrade(String idx, StandardDTO standardDto) {
		policyMapper.modifyTradePolicy(idx);
		policyMapper.addTradePolicy(standardDto);
	}
	
	public void modifyCommission(String idx, StandardDTO standardDto) {
		policyMapper.modifyCommissionPolicy(idx);
		policyMapper.addCommissionPolicy(standardDto);
	}
	
	public void modifyDeposit(String idx, StandardDTO standardDto) {
		policyMapper.modifyDepositPolicy(idx);
		policyMapper.addDepositPolicy(standardDto);
	}
	
	
	public List<StandardDTO> getDepositPolicy(){
		return policyMapper.getDepositPolicy();
	}
	public List<StandardDTO> getTradePolicy(){
		return policyMapper.getTradePolicy();
		
	}
	public List<StandardDTO> getCommissionPolicy(){
		return policyMapper.getCommissionPolicy();
	}
	
	
}
