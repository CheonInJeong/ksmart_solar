package com.cafe24.kangk0269.serivce;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.FileMapper;
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
	@Autowired
	private final FileMapper fileMapper;
	
	public SellService(SellMapper sellMapper,FileUtils fileUtils, FileMapper fileMapper) {
		this.sellMapper = sellMapper;
		this.fileUtils = fileUtils;
		this.fileMapper = fileMapper;
	}
	
	//upcate rank
	
	public void modifyRank(int rank,String code) throws Exception {
		sellMapper.modifyRank(rank, code);
	}
	
	//rank check
	public List<BidListDTO> rankCheck(String bCode) throws Exception {
		return sellMapper.rankCheck(bCode);
	}
	
	//부품공고 수정
	public void modifyComponentSell(BidComponentDTO bidComponentDto,
									MultipartHttpServletRequest multipartHttpServletRequest,
									HttpServletRequest request) throws Exception {
		fileMapper.removeApplyFile(bidComponentDto.getbCpCode());
		sellMapper.modifyComponentSell(bidComponentDto);
		BidComponentDTO bidCode = sellMapper.getBidComponentCode(bidComponentDto.getbCpCode());
		
		
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidComponentDto.getbCpCode(),1,"공고서류", multipartHttpServletRequest,request);
		
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.addFile(filelist);
		}
	}
	
	//부품공고 삭제
	public void removeComponentSell(String code)  throws Exception {
		sellMapper.removeComponentSell(code);
	}
	
	//부품공고 등록
	public void addComponentApply(BidComponentDTO bidComponentDto,
								  MultipartHttpServletRequest multipartHttpServletRequest,
								  HttpServletRequest request) throws Exception {
		sellMapper.addComponentApply(bidComponentDto);
		System.out.println(bidComponentDto.getCpCode()+"<-----부품코드");
		//조회처리과정추가
		BidComponentDTO bidCode = sellMapper.getBidComponentCode(bidComponentDto.getCpCode());
		
		
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidCode.getbCpCode(),1,"공고서류", multipartHttpServletRequest,request);
		
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.addFile(filelist);
		}
	}
		
	
	//공고상태(공고진행중 > 거래진행중)으로 바꾸는 메서드 실행
	public void updateAcStatus() throws Exception{
		System.out.println("updateAcStatus 실행");
		List<BidPlantDTO> acList = sellMapper.getAcStatus();
		for(int i=0 ; i<acList.size();i++) {
			String decisionDate = acList.get(i).getbPlDateDecision1();
			
			if(decisionDate!=null) {
				decisionDate = decisionDate.substring(0,10);
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String today = format.format(date);
				if(decisionDate.equals(today)) {
					sellMapper.updateAcStatus();
				}
			}
		}
	}
	
	
	//서류적합성 수정
	public void modifyDocumentCheck(String code, String check) throws Exception {
		sellMapper.modifyDocumentCheck(code, check);
	}
	
	//입찰자 정보 얻기
	public BidListDTO getBuyerInfoByCode(String code) throws Exception {
		
		return sellMapper.getBuyerInfoByCode(code);
		
	}
	
	//입찰자 서류 얻기
	public List<FileDTO> getBidderFileList(String code) throws Exception{
		FileDTO fileDto = new FileDTO();
		fileDto.setFileSortIdx(2);
		fileDto.setRelatedTableCode(code);
		
		return fileMapper.getFileList(fileDto);
	}
	
	//출금신청
	public void addApplyPayment(TradePaymentOutDTO tradePaymentOutDTO) throws Exception {
		sellMapper.addApplyPayment(tradePaymentOutDTO);
		String trPrCode = tradePaymentOutDTO.getTrPrCode();
		sellMapper.modifyApplyYn(trPrCode);
	}
	
	
	public MemberAccountDTO getAccountInfoByAccount(String number)  throws Exception{
		return sellMapper.getAccountInfoByNumber(number);
	}
	
	//회원 계좌 얻기
	public List<MemberAccountDTO> getMemberAccountById(String mId) throws Exception {
		return sellMapper.getMemberAccountById(mId);
	}
	
	//출금신청 화면
	public TradePriorityDTO getPaymentOutByCode(String code) throws Exception {
		return sellMapper.getPaymentOutByCode(code);
	}
	
	//출금 가능 한 리스트
	public List<TradePriorityDTO> getPaymentAvailable(String mId) throws Exception{
		return sellMapper.getPaymentAvailable(mId);
	}
	
	//출금 신청 한 리스트
	public List<TradePriorityDTO> getPaymentApplyList(String mId) throws Exception{
		return sellMapper.getPaymentApplyList(mId);
	}
	
	public List<TradePriorityDTO> getPaymentOutList(String mId) throws Exception{
		return sellMapper.getPaymentOutList(mId);
	}
	
	//해당 아이디의 부품공고 리스트를 가져옴
	public List<BidComponentDTO> getBidComponentById(String mId, String searchKeyCp, String searchValueCp) throws Exception{
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
	public List<BidListDTO> getBidderList(String code) throws Exception {
		return sellMapper.getBidderList(code);
	}
	
	//부품 정보 조회
	
	public ComponentDTO getComponentInformation(String code) throws Exception {
		return sellMapper.getComponentInformation(code);
	}
	
	//부품 리스트 조회
	public List<ComponentDTO> getComponent(String mId) throws Exception{
		return sellMapper.getComponent(mId);
	}
	//부품 공고 내용 조회
	public BidComponentDTO getComponentDetail(String code) throws Exception {
		return sellMapper.getComponentDetail(code);
	}
	
	//부품등록
	public void addComponent(ComponentDTO componentDto) throws Exception {
		sellMapper.addComponent(componentDto);
	}
	
	//판매자가 등록한 서류 얻기
	public List<FileDTO> getsellerFileList(String code) throws Exception{
		FileDTO fileDto = new FileDTO();
		fileDto.setFileSortIdx(1);
		fileDto.setRelatedTableCode(code);
		
		return fileMapper.getFileList(fileDto);
	}
	
	//발전소 공고 내용 조회
	public List<BidPlantDTO> getBidPlantDetail(String code) throws Exception{
		return sellMapper.getBidPlantDetail(code);
	}
	
	public void removePlantApply(String code)  throws Exception{
		sellMapper.removePlantApply(code);
		fileMapper.removeApplyFile(code);
	}
	
	public List<BidPlantDTO> getBidPlantbyCode(String code) throws Exception{
		return sellMapper.getBidPlantbyCode(code);
	}
	//발전소 공고 수정
	public void modifyPlantApply(BidPlantDTO bidPlantDto,
								 MultipartHttpServletRequest multipartHttpServletRequest,
								 HttpServletRequest request) throws Exception {
		sellMapper.modifyPlantApply(bidPlantDto);
		System.out.println(bidPlantDto.getbPlCode()+"<---파일 업데이트 getPlCode");
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidPlantDto.getbPlCode(),1,"공고서류", multipartHttpServletRequest,request);
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.removeApplyFile(bidPlantDto.getbPlCode());
			fileMapper.addFile(filelist);
		}
	}
	//발전소 신청
	public void addPlantApply(BidPlantDTO bidPlantDto,
					 		  MultipartHttpServletRequest multipartHttpServletRequest,
					 		  HttpServletRequest request) throws Exception {
		sellMapper.addPlantApply(bidPlantDto);
		//조회처리과정추가
		BidPlantDTO bidCode =sellMapper.getBidPlantCode(bidPlantDto.getBzPlCode());
		System.out.println(bidCode.getbPlCode()+"<-------bidCode.getbPlCode()");
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidCode.getbPlCode(),1,"공고서류", multipartHttpServletRequest,request);
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.addFile(filelist);
		}
	}
	
	public BusinessPlantDTO getPlantInformation(String code)  throws Exception{
		BusinessPlantDTO bzPlantDto = sellMapper.getPlantInformation(code);
		return bzPlantDto;
	}
	

	public	List<BidPlantDTO> getBidPlantbyId(String mId, String searchKey, String searchValue)  throws Exception{

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
	
	public List<BusinessPlantDTO> getPlantName(String mId)  throws Exception{
		List<BusinessPlantDTO> bzPlDto = sellMapper.getPlantName(mId);

		return bzPlDto;
	}

}
