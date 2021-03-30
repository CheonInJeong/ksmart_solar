package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;


@Mapper
public interface SellMapper {
	
	//입찰자 정보 얻기
	public BusinessDTO getBuyerInfoById(String id);
	
	//출금신청여부 업데이트
	public int modifyApplyYn(String code);
	
	
	//파일 수정 메서드
	public int modifyFile(String code);
	
	public BidComponentDTO getComponentDetail(String code);
	
	public MemberAccountDTO getAccountInfoByNumber(String number);
	//출금신청
	public int addApplyPayment(TradePaymentOutDTO tradePaymentOutDTO);
	
	public List<MemberAccountDTO> getMemberAccountById(String mId);
	
	//출금신청 화면
	public TradePriorityDTO getPaymentOutByCode(String code);
	
	
	//출금신청 가능한 리스트 가져오기
	public List<TradePriorityDTO> getPaymentOutList(String mId);
		
	//해당 아이디의 부품공고 리스트를 가져옴
	public List<BidComponentDTO> getBidComponentById(String mId,String searchKeyCp, String searchValueCp);
	
	//파일 등록 메서드
	public int addFile(List<FileDTO> list);
	
	//해당 공고 입찰자 목록
	public List<BidListDTO> getPlantBidderList(String code);
	
	//해당 아이디가 가지고 있는 부품 정보 
	public List<ComponentDTO> getComponent(String mId);
	
	//부품 코드로 부품 상세 정보 가져오기
	public ComponentDTO getComponentInformation(String code);
	
	//공고신청 중 발전소 선택시 선택한 발전소의 정보(잔존가치, 매입금액, 발전소시작일)을 가져오는 메서드
	public BusinessPlantDTO getPlantInformation(String code);
	

	//판매자의 발전소 공고 목록을 가져오는 메서드 
	public List<BidPlantDTO> getBidPlantbyId(String mId,String searchKey, String searchValue);
	//공고 코드로 공고 신청 내역 불러오는 메서드
	public List<BidPlantDTO> getBidPlantbyCode(String code);
	
	///발전소 공고 내용 조회
	public List<BidPlantDTO> getBidPlantDetail(String code);
	
	//공고 신청 삭제 메서드
	public int removePlantApply(String code);
	
	//공고신청 수정 처리 메서드
	public int modifyPlantApply(BidPlantDTO bidPlantDto);
	
	//공고신청 메서드
	public int addPlantApply(BidPlantDTO bidPlantDto);
	
	//발전소 이름 가져오는 메서드
	public List<BusinessPlantDTO> getPlantName(String mId);
	
}
