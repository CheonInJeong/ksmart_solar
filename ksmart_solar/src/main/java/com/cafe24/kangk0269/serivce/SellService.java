package com.cafe24.kangk0269.serivce;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.FileDTO;
import com.cafe24.kangk0269.dto.MemberAccountDTO;
import com.cafe24.kangk0269.dto.TradePaymentOutDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;

@Transactional
@Service
public class SellService {

	@Autowired
	private final SellMapper sellMapper;
	@Autowired
	private final FileUtils fileUtils;
	
	public SellService(SellMapper sellMapper,FileUtils fileUtils) {
		this.sellMapper = sellMapper;
		this.fileUtils = fileUtils;
	}
	
	//서류적합성 수정
	public void modifyDocumentCheck(String code, String check) {
		sellMapper.modifyDocumentCheck(code, check);
	}
	
	//입찰자 정보 얻기
	public BidListDTO getBuyerInfoByCode(String code) {
		return sellMapper.getBuyerInfoByCode(code);
		
	}
	
	//출금신청
	public void addApplyPayment(TradePaymentOutDTO tradePaymentOutDTO) {
		sellMapper.addApplyPayment(tradePaymentOutDTO);
		String trPrCode = tradePaymentOutDTO.getTrPrCode();
		sellMapper.modifyApplyYn(trPrCode);
	}
	
	
	public MemberAccountDTO getAccountInfoByAccount(String number) {
		return sellMapper.getAccountInfoByNumber(number);
	}
	
	//회원 계좌 얻기
	public List<MemberAccountDTO> getMemberAccountById(String mId) {
		return sellMapper.getMemberAccountById(mId);
	}
	
	//출금신청 화면
	public TradePriorityDTO getPaymentOutByCode(String code) {
		return sellMapper.getPaymentOutByCode(code);
	}
	
	//출금 가능 한 리스트
	public List<TradePriorityDTO> getPaymentOutList(String mId){
		return sellMapper.getPaymentOutList(mId);
	}
	//해당 아이디의 부품공고 리스트를 가져옴
	public List<BidComponentDTO> getBidComponentById(String mId, String searchKeyCp, String searchValueCp){
		if(searchKeyCp!=null) {
			if("bCpCode".equals(searchKeyCp)) {
				searchKeyCp ="b_cp_code";
			}else if("cpName".equals(searchKeyCp)) {
				searchKeyCp ="cp_name";
			}else if("bCpDateBidding1".equals(searchKeyCp)) {
				searchKeyCp ="b_cp_date_bidding1";
			}else if("bCpTitle".equals(searchKeyCp)) {
				searchKeyCp = "b_cp_title";
			}else {
				searchKeyCp = "b_cp_status";
			}
		}
		return sellMapper.getBidComponentById(mId,searchKeyCp,searchValueCp);
	}
	
	
	//입찰자목록조회
	public List<BidListDTO> getPlantBidderList(String code) {
		return sellMapper.getPlantBidderList(code);
	}
	
	//부품 정보 조회
	
	public ComponentDTO getComponentInformation(String code) {
		return sellMapper.getComponentInformation(code);
	}
	
	//부품 리스트 조회
	public List<ComponentDTO> getComponent(String mId){
		return sellMapper.getComponent(mId);
	}
	//부품 공고 내용 조회
	public BidComponentDTO getComponentDetail(String code) {
		return sellMapper.getComponentDetail(code);
	}
	
	
	//발전소 공고 내용 조회
	public List<BidPlantDTO> getBidPlantDetail(String code){
		return sellMapper.getBidPlantDetail(code);
	}
	
	public void removePlantApply(String code) {
		sellMapper.removePlantApply(code);
	}
	
	public List<BidPlantDTO> getBidPlantbyCode(String code){
		return sellMapper.getBidPlantbyCode(code);
	}
	//발전소 공고 수정
	public void modifyPlantApply(BidPlantDTO bidPlantDto,MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request) throws Exception {
		sellMapper.modifyPlantApply(bidPlantDto);
		System.out.println(bidPlantDto.getbPlCode()+"<---파일 업데이트 getPlCode");
		sellMapper.modifyFile(bidPlantDto.getbPlCode());
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidPlantDto.getbPlCode(),1,"발전소공고신청서류", multipartHttpServletRequest,request);
		if (CollectionUtils.isEmpty(filelist) == false) {
			sellMapper.addFile(filelist);
		}
	}
	//발전소 신청
	public void addPlantApply(BidPlantDTO bidPlantDto,MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request) throws Exception {
		sellMapper.addPlantApply(bidPlantDto);
		System.out.println(bidPlantDto.getbPlCode()+"<------파일 관련 공고 코드");
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidPlantDto.getbPlCode(),1,"발전소공고신청서류", multipartHttpServletRequest,request);
		if (CollectionUtils.isEmpty(filelist) == false) {
			sellMapper.addFile(filelist);
		}
	}
	
	public BusinessPlantDTO getPlantInformation(String code){
		BusinessPlantDTO bzPlantDto = sellMapper.getPlantInformation(code);
		return bzPlantDto;
	}
	

	public	List<BidPlantDTO> getBidPlantbyId(String mId, String searchKey, String searchValue ){

		if(searchKey!=null) {
			if("bPlCode".equals(searchKey)) {
				searchKey = "b_pl_code";
			}else if("bzPlName".equals(searchKey)) {
				searchKey = "bz_pl_name";
			}else if("bPlDateBidding1".equals(searchKey)){
				searchKey = "b_pl_date_bidding1";
			}else if("bPlTitle".equals(searchKey)){
				searchKey = "b_pl_title";
			}else {
				searchKey = "b_pl_status";
			}
			
			
		}
		List<BidPlantDTO> bidPlantList = sellMapper.getBidPlantbyId(mId,searchKey,searchValue);
			
		return bidPlantList;
	}
	
	public List<BusinessPlantDTO> getPlantName(String mId){
		List<BusinessPlantDTO> bzPlDto = sellMapper.getPlantName(mId);

		return bzPlDto;
	}

}
