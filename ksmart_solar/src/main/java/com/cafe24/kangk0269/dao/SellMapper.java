package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;


@Mapper
public interface SellMapper {
	
	public int updateAcStatus();
	
	//부품 공고 등록
	
	public int addComponentApply(BidComponentDTO bidComponentDto);	
	//부품등록
	public int addComponent(ComponentDTO componentDto);
	
	//공고상태 및  낙찰자 결정 시간 가져오기
	public List<BidPlantDTO> getAcStatus();
	//부품 공고 코드 가져오기
	public BidComponentDTO getBidComponentCode(String code) ;
	//발전소 공고 코드 가져오기
	public BidPlantDTO getBidPlantCode(String code) ;

	//서류 적합성 판단 수정
	public int modifyDocumentCheck(@Param(value="bCode") String code
								  ,@Param(value="bCheck") String check);
	
	//입찰자 정보 얻기
	public BidListDTO getBuyerInfoByCode(String code);
	
	//출금신청여부 업데이트
	public int modifyApplyYn(String code);
	
	
	public BidComponentDTO getComponentDetail(String code);
	
	public MemberAccountDTO getAccountInfoByNumber(String number);
	//출금신청
	public int addApplyPayment(TradePaymentOutDTO tradePaymentOutDTO);
	
	public List<MemberAccountDTO> getMemberAccountById(String mId);
	
	//출금신청 화면
	public TradePriorityDTO getPaymentOutByCode(String code);
	
	
	//출금신청 가능한 리스트 가져오기
	public List<TradePriorityDTO> getPaymentAvailable(String mId);
	
	//출금신청한 파일
	public List<TradePriorityDTO> getPaymentApplyList(String mId);
	
	//출금완료 리스트
	public  List<TradePriorityDTO> getPaymentOutList(String mId);
		
	//해당 아이디의 부품공고 리스트를 가져옴
	public List<BidComponentDTO> getBidComponentById(String mId,String searchKeyCp, String searchValueCp);
	
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
