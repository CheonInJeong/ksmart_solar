package com.cafe24.kangk0269.serivce;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.common.Pagination;
import com.cafe24.kangk0269.dao.PolicyMapper;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.StandardDTO;


@Transactional
@Service
public class PolicyService {

	@Autowired
	private final PolicyMapper policyMapper;
	@Autowired
	private final FileUtils fileUtils;
	
	public PolicyService(PolicyMapper policyMapper,FileUtils fileUtils) {
		this.policyMapper = policyMapper;
		this.fileUtils = fileUtils;
	}
	
	public void updateCommission() {
		System.out.println("updateCommission 스케쥴러 실행");
		List<StandardDTO> reservation = policyMapper.getCommissionReservationDate();
		for(int i=0; i<reservation.size(); i++) {
			String resrvationDate = reservation.get(i).getsReservation();
			int type = reservation.get(i).getsCommissionType();
			System.out.println("적용일 : "  +resrvationDate + "종류:" + type );
			
			if(resrvationDate!=null) {
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String today = format.format(date);
				
				if(resrvationDate.equals(today)) {
					
				
					date = new Date(date.getTime()+(1000*60*60*24*-1));
					String yesterday = format.format(date);
					
					policyMapper.updateCommissionStatusN(type,yesterday);
					policyMapper.updateCommissionStatusY(resrvationDate);
				}
			}
		}
	}
	
	//거래기간 정책 상태 바꾸기(스케쥴러)
	public void updateTrade() {
		System.out.println("updateTrade 스케쥴러 실행");
		List<StandardDTO> reservation = policyMapper.getTradeReservationDate();
		for(int i=0; i<reservation.size(); i++) {
			String resrvationDate = reservation.get(i).getsReservation();
			String type = reservation.get(i).getsTradeType();
			System.out.println("적용일 : "  +resrvationDate + "종류:" + type );
			
			if(resrvationDate!=null) {
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String today = format.format(date);
				
				if(resrvationDate.equals(today)) {
					
					date = new Date(date.getTime()+(1000*60*60*24*-1));
					String yesterday = format.format(date);
					
					StandardDTO standard = new StandardDTO();
					standard.setsTradeLast(yesterday);
					standard.setsTradeType(type);
					
					policyMapper.updateTradeStatusN(type,yesterday);
					policyMapper.updateTradeStatusY(resrvationDate);
				}
			}
		}
	}
	
	//예치금 정책 상태 바꾸기(스케쥴러)
	public void updateDeposit() {
		System.out.println("updateDeposit 스케쥴러 실행");
		List<StandardDTO> reservation = policyMapper.getDepositReservationDate();
		for(int i=0 ; i<reservation.size();i++) {
			String resrvationDate = reservation.get(i).getsReservation();
			String type = reservation.get(i).getsDepositType();
			System.out.println("적용일 : "  +resrvationDate + "종류:" + type );
			if(resrvationDate!=null) {
	
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String today = format.format(date);
				if(resrvationDate.equals(today)) {
					date = new Date(date.getTime()+(1000*60*60*24*-1));
					String yesterday = format.format(date);
					
					StandardDTO standard = new StandardDTO();
					standard.setsDepositLast(yesterday);
					standard.setsDepositType(type);
					
					policyMapper.updateDepositStatusN(standard);
					policyMapper.updateDepositStatusY(resrvationDate);
				} 
			}
		}
	}
	
	
	
	//파일삭제
	public void  removeFile(int idx) {
		 policyMapper.removeFile(idx);
	}
	
	//파일다운로드
	public FileDTO getFileInfo(int idx) {
		return policyMapper.getFileInfo(idx);
	}
	
	
	//업로드 된 입찰 파일 조회
	public List<FileDTO> getBidFileList(){
		
		return policyMapper.getBidFileList();
	}
	
	//업로드 된 공고 파일 조회
	public List<FileDTO> getNoticeFileList(){
		
		return policyMapper.getNoticeFileList();
	}
	
	//입찰 관련 파일 등록
		public void addBidFile(MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request)throws Exception {
			List<FileDTO> filelist = fileUtils.parseFileInfo("입찰서류",2,"입찰서류", multipartHttpServletRequest,request);
			if(CollectionUtils.isEmpty(filelist)==false) {
				policyMapper.addFile(filelist);
			}
		}
	
	//공고 관련 파일 등록
	public void addNoticeFile(MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request)throws Exception {
		List<FileDTO> filelist = fileUtils.parseFileInfo("공고서류",1,"공고서류", multipartHttpServletRequest,request);
		if(CollectionUtils.isEmpty(filelist)==false) {
			policyMapper.addFile(filelist);
		}
	}
	
	
	//사용 예약 중인 예치금
	public List<StandardDTO> getDepositReservation(){
		return policyMapper.getDepositReservation();
			
	}
	public List<StandardDTO> getCommissionReservation(){
		return policyMapper.getCommissionReservation();
	}
	public List<StandardDTO> getTradeReservation(){
		return policyMapper.getTradeReservation();
	}
		
		
	//사용 X인 예치금
	public List<StandardDTO> getDepositNotUsed(String searchKey,String searchValue,StandardDTO standardDTO){
		if(searchKey!=null) {
			if(searchKey.equals("sdepositType")) {
				searchKey ="s_deposit_type";
			}else if(searchKey.equals("sdepositRate")) {
				searchKey ="s_deposit_rate";
			}else if(searchKey.equals("sReservation")) {
				searchKey ="s_reservation";
			}else if(searchKey.equals("sDepositLast")) {
				searchKey ="s_deposit_last";
			}else if(searchKey.equals("mId")) {
				searchKey ="m_id";
			}
		}
		
		List<StandardDTO> depositList = null;
		int depositCount = policyMapper.getDepositNotUsedCount(searchKey,searchValue,standardDTO);
		
		Pagination pagination = new Pagination(standardDTO);
		pagination.setTotalRecordCount(depositCount);
		
		
		standardDTO.setPagination(pagination);
		
		if(depositCount>0) {
			depositList = policyMapper.getDepositNotUsed(searchKey,searchValue,standardDTO);
		}
		return depositList;
	}
	public List<StandardDTO> getCommissionNotUsed(String searchKey,String searchValue,StandardDTO standardDTO){
		if(searchKey!=null) {
			if(searchKey.equals("sCommissionType")) {
				searchKey ="s_commission_type";
			}else if(searchKey.equals("sCommissionRate")) {
				searchKey ="s_commission_rate";
			}else if(searchKey.equals("sReservation")) {
				searchKey ="s_reservation";
			}else if(searchKey.equals("sCommissionLast")) {
				searchKey ="s_commission_last";
			}else if(searchKey.equals("mId")) {
				searchKey ="m_id";
			}
		}
		
		List<StandardDTO> commissionList = null;
		int commissionCount = policyMapper.getCommissionNotUsedCount(searchKey,searchValue,standardDTO);
		
		Pagination pagination = new Pagination(standardDTO);
		pagination.setTotalRecordCount(commissionCount);
		
		
		standardDTO.setPagination(pagination);
		
		if(commissionCount>0) {
			commissionList = policyMapper.getCommissionNotUsed(searchKey,searchValue,standardDTO);
		}
		return commissionList;
	}
	public List<StandardDTO> getTradeNotUsed(String searchKey,String searchValue,StandardDTO standardDTO){
		if(searchKey!=null) {
			if(searchKey.equals("sTradeType")) {
				searchKey ="s_trade_type";
			}else if(searchKey.equals("sTradePeriod")) {
				searchKey ="s_trade_period";
			}else if(searchKey.equals("sReservation")) {
				searchKey ="s_reservation";
			}else if(searchKey.equals("sTradeLast")) {
				searchKey ="s_trade_last";
			}else if(searchKey.equals("mId")) {
				searchKey ="m_id";
			}
		}
		List<StandardDTO> tradeList = null;
		int tradeCount = policyMapper.getTradeNotUsedCount(searchKey,searchValue,standardDTO);
		
		Pagination pagination = new Pagination(standardDTO);
		pagination.setTotalRecordCount(tradeCount);
		
		
		standardDTO.setPagination(pagination);
		
		
		if(tradeCount>0) {
			tradeList = policyMapper.getTradeNotUsed(searchKey,searchValue,standardDTO);
		}
		return tradeList;
	}
	
	
	public List<StandardDTO> getTradeHistory(){
		return policyMapper.getTradeHistory();
	}

	
	public List<StandardDTO> getCommissionHistory(){
		return policyMapper.getCommissionHistory();
	}

	
	public List<StandardDTO> getDepositHistory(){
			return policyMapper.getDepositHistory();
	}
	
	
	
	public void removeCommission(int idx) {
		policyMapper.removeCommission(idx);
	}
	
	public void removeDeposit(int idx) {
		policyMapper.removeDeposit(idx);
	}
	public void removeTrade(int idx) {
		policyMapper.removeTrade(idx);
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
