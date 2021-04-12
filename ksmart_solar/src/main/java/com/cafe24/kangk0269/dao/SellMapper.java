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
	
	
	
	
	//공고그룹 코드로 최신 공고 코드 얻기
	public String getRecentlybPlCodeByGroupcode(String groupCode);
	
	//발전소 공고 코드로 공고 상태 얻기
	public BidPlantDTO getPlantAcStatusByCode(String bPlCode);
	//b_recently 'Y' -> 'N'으로
	public int updateBPlantRecentlyYn(String bPlCode);
	public int updateBComponentRecentlyYn(String cpCode);
	
	//b_recently 'N' -> 'Y'으로
	public int updateBPlantRecentlyNy(String groupcode);
	public int updateBComponentRecentlNy(String groupcode);
	
	
	//발전소 재공고신청
	public int addPlantRebidApply(BidPlantDTO bidPlantDTO);
	//부품 재공고 신청
	public int addComponentRebidApply(BidComponentDTO bidComponentDTO);
	
	
	//발전소 공고 상태 얻기
	public List<BidPlantDTO> getBidPlantAcById (String id);
	
	
	public List<BidComponentDTO> getBidComponentAcById (String id);
	//발전소 재공고 신청화면
	public BidPlantDTO getBidPlantAcByIdCode(@Param(value="mId") String id
											  ,@Param(value="bzPlCode") String bzPlCode);
	//부품 재공고 신청화면
	public BidComponentDTO getBidComponentAcByIdCode(String mId, String cpCode);
	
	//update rank
	public int modifyRank(@Param(value="bRank") int rank, @Param(value="bCode") String code) throws Exception;
	
	//rank check
	public List<BidListDTO> rankCheck(String bCode) throws Exception;
	
	//공고 상태 변경
	public int updateAcStatus() throws Exception;
	public int updateComponentAcStatus() throws Exception;
	
	//공고상태 및  낙찰자 결정 시간 가져오기
	public List<BidPlantDTO> getAcStatus() throws Exception;
	public List<BidComponentDTO> getComponentAcStatus() throws Exception;
	
	
	//서류 적합성 판단 수정
	public int modifyDocumentCheck(@Param(value="bCode") String code
								 ,@Param(value="bCheck") String check)  throws Exception;
	
	//부품 공고 코드 가져오기(파일등록을 위한)
	public String getBidComponentCode(String code)  throws Exception;
	//부품 공고 
	public BidComponentDTO getBidComponentByCode(String code) throws Exception;
	//부품 공고 수정처리
	public int modifyComponentSell(BidComponentDTO bidComponentDto) throws Exception;
	//부품 공고 삭제
	public int removeComponentSell(String code) throws Exception;
	//부품 공고 등록
	public int addComponentApply(BidComponentDTO bidComponentDto) throws Exception;	
	//부품등록
	public int addComponent(ComponentDTO componentDto) throws Exception;


	//발전소 공고 코드 가져오기(파일등록을 위한)
	public BidPlantDTO getBidPlantCode(String code) throws Exception ;

	//입찰자 정보 얻기
	public BidListDTO getBuyerInfoByCode(String code) throws Exception;
	
	//출금신청여부 업데이트
	public int modifyApplyYn(String code) throws Exception;
	
	//부품 정보 가져오기
	public BidComponentDTO getComponentDetail(String code) throws Exception;
	
	public MemberAccountDTO getAccountInfoByNumber(String number) throws Exception;
	//출금신청
	public int addApplyPayment(TradePaymentOutDTO tradePaymentOutDTO) throws Exception;
	
	public List<MemberAccountDTO> getMemberAccountById(String mId) throws Exception;
	
	//출금신청 화면
	public TradePriorityDTO getPaymentOutByCode(String code) throws Exception;
	
	
	//출금신청 가능한 리스트 가져오기
	public List<TradePriorityDTO> getPaymentAvailable(String mId) throws Exception;
	
	//출금신청한 파일
	public List<TradePriorityDTO> getPaymentApplyList(String mId) throws Exception;
	
	//출금완료 리스트
	public  List<TradePriorityDTO> getPaymentOutList(String mId) throws Exception;
	
	//판매자 발전소 목록의 수
	public int getBidComponentCount(@Param(value="mId") String mId
									,@Param(value="searchKeyCp") String searchKeyCp
									,@Param(value="searchValueCp") String searchValueCp
									,@Param(value="bidComponentDTO") BidComponentDTO bidComponentDTO) throws Exception;
	
	//해당 아이디의 부품공고 리스트를 가져옴
	public List<BidComponentDTO> getBidComponentById(@Param(value="mId") String mId
													,@Param(value="searchKeyCp") String searchKeyCp
													,@Param(value="searchValueCp") String searchValueCp
													,@Param(value="bidComponentDTO") BidComponentDTO bidComponentDTO) throws Exception;
	
	//해당 공고 입찰자 목록
	public List<BidListDTO> getBidderList(String code) throws Exception;
	
	//해당 아이디가 가지고 있는 부품 정보 
	public List<ComponentDTO> getComponent(String mId) throws Exception;
	
	//부품 코드로 부품 상세 정보 가져오기
	public ComponentDTO getComponentInformation(String code) throws Exception;
	
	//공고신청 중 발전소 선택시 선택한 발전소의 정보(잔존가치, 매입금액, 발전소시작일)을 가져오는 메서드
	public BusinessPlantDTO getPlantInformation(String code) throws Exception;
	
	//판매자 발전소 목록의 수
	public int getBidPlantCount(@Param(value="mId") String mId
								,@Param(value="searchKey") String searchKey
								,@Param(value="searchValue") String searchValue
								,@Param(value="bidPlantDTO") BidPlantDTO bidPlantDTO) throws Exception;
	//판매자의 발전소 공고 목록을 가져오는 메서드 
	public List<BidPlantDTO> getBidPlantbyId(@Param(value="mId") String mId
											,@Param(value="searchKey") String searchKey
											,@Param(value="searchValue") String searchValue
											,@Param(value="bidPlantDTO") BidPlantDTO bidPlantDTO) throws Exception;
	//공고 코드로 공고 신청 내역 불러오는 메서드
	public List<BidPlantDTO> getBidPlantbyCode(String code) throws Exception;
	
	///발전소 공고 내용 조회
	public List<BidPlantDTO> getBidPlantDetail(String code) throws Exception;
	
	//공고 신청 삭제 메서드
	public int removePlantApply(String code) throws Exception;

	
	//공고신청 수정 처리 메서드
	public int modifyPlantApply(BidPlantDTO bidPlantDto) throws Exception;
	
	//공고신청 메서드
	public int addPlantApply(BidPlantDTO bidPlantDto) throws Exception;
	
	//발전소 이름 가져오는 메서드
	public List<BusinessPlantDTO> getPlantName(String mId) throws Exception;
	
}
