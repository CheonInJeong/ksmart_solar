package com.cafe24.kangk0269.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.StandardDTO;



@Mapper
public interface PolicyMapper {
	//다운로드 된 파일 삭제
	public int removeFile(int idx);
	
	//파일 다운로드
	public FileDTO getFileInfo(int idx);

	//업로드된 공고 파일 조회
	public List<FileDTO> getNoticeFileList();
	public List<FileDTO> getBidFileList();
	
	//공고파일업로드
	public int addFile(List<FileDTO> list);
	
	
	public List<StandardDTO> getDepositHistory(String startDate, String endDate);
	public List<StandardDTO> getCommissionHistory(String startDate, String endDate);
	public List<StandardDTO> getTradeHistory(String startDate, String endDate);
	
	
	//거래대금 수수료울 삭제
	public int removeCommission(int idx);
	public int removeDeposit(int idx);
	public int removeTrade(int idx);
	
	
	//예치금상태없데이트(스케쥴러)
	public int updateDepositStatusN(StandardDTO standardDto);
	public int updateDepositStatusY(String status);
	//예치금상태없데이트(스케쥴러)
	public int updateTradeStatusN(String sTradeType, String sTradeLast);
	public int updateTradeStatusY(String status);
	//거래대금 수수료 상태없데이트(스케쥴러)
	public int updateCommissionStatusN(@Param(value="type") int type,
										@Param(value="sCommissionLast") String yesterday);
	public int updateCommissionStatusY(String status);
	
	
	
	//적용일 가져오기
	public List<StandardDTO> getDepositReservationDate();
	public List<StandardDTO> getTradeReservationDate();
	public List<StandardDTO> getCommissionReservationDate();
	
	//새로운 정책 추가
	public int addNewDeposit(StandardDTO standardDto);
	public int addNewTrade(StandardDTO standardDto);
	public int addNewCommission(StandardDTO standardDto);
	
	//정책사용 마지막 날 업데이트
	public int modifyDepositLastDay(StandardDTO standardDto);
	public int modifyCommissionLastDay(StandardDTO standardDto);
	public int modifyTradePolicy(StandardDTO standardDto);
	
	
	//정책 수정..사실 추가
	public int addDepositPolicy(StandardDTO standardDto);
	public int addCommissionPolicy(StandardDTO standardDto);
	public int addTradePolicy(StandardDTO standardDto);
	
	//정책수정
	public int modifyDepositPolicy(String idx);
	public int modifyTradePolicy(String idx);
	public int modifyCommissionPolicy(String idx);
	
	
	//정책조회
	public List<StandardDTO> getDepositPolicy();
	public List<StandardDTO> getTradePolicy();
	public List<StandardDTO> getCommissionPolicy();
}
