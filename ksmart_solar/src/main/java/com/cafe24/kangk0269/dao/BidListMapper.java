package com.cafe24.kangk0269.dao;




import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BoardSellerDTO;
import com.cafe24.kangk0269.dto.FileDTO;

@Mapper
public interface BidListMapper {
	//현재 예치금 수수료율
	public double getDepositRate();
	//입찰 등록
	public int addbidList(BidListDTO bidListDTO);
	//입찰을 했는지 안했는지
	public int getBidListCount(String announceCode, String id);
	//해당 아이디가 입찰을 했는지 안했는지
	public BidListDTO getBidList(String announceCode, String id,String bCode);
	//입찰신청한 입찰코드
	public String getBidCode(String announceCode, String id);
	//입찰 취소
	public int bidCancel(String bCode,String status);
	//입찰 취소시 발전소 공고 리스트의 입찰자 목록 -1
	public int bidPlantMemberMinus(String plCode); 
	//입찰 취소시 부품 공고 리스트의 입찰자 목록 -1
	public int bidComponentMemberMinus(String cpCode);
	//재공고시 이전공고에서 계약을 취소했는지 대금을 미납했는지 여부 판단
	public int reBidCount(String groupCode, String id); 
	//입찰신청시 필요한 서류
	public List<FileDTO> getBidFileList();
	//입찰 수정
	public int modifyBidList(BidListDTO bidListDTO);
	//계약 취소
	public int tradeCancel(String bCode);
	//환불 가능한 목록 조회
	public List<BidListDTO> getApplyRefundList(@Param(value="id") String id,
												@Param(value="status")String status,
												@Param(value="searchKey")String searchKey,
												@Param(value="searchValue")String searchValue, 
												@Param(value="bidListDTO")BidListDTO bidListDTO);
	//환불 가능한 목록 조회 갯수
	public int getApplyRefundListCount(@Param(value="id") String id,
										@Param(value="status")String status,
										@Param(value="searchKey")String searchKey,
										@Param(value="searchValue")String searchValue, 
										@Param(value="bidListDTO")BidListDTO bidListDTO);
	//입찰상태 변경
	public int updateBidStatus(Map<String,Object> List);
	//입찰 성공 -> 입찰 종료
	public int updateBidEnd(Map<String,Object> List);
	// 입찰 성공 -> 계약중
	public int updateBidTrade(Map<String,Object> List);
	//입찰 성공 -> 계약대기
	public int updateBidTradeWait(Map<String,Object> List);
	//문의하기 등록
	public int addqnaRequest(BoardSellerDTO boardSellerDTO);
	//문의 수정
	public int modifyQna(@Param(value="mIdBuyer")String mIdBuyer,
						@Param(value="bSubject")String bSubject,
						@Param(value="bContents")String bContents,
						@Param(value="bIdx")int bIdx); 
	public int removeQna(int bIdx);
	public int removeQnaCo(int bIdx);

	

}
