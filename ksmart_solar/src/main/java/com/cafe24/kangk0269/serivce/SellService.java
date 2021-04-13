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

import com.cafe24.kangk0269.common.Criteria;
import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.common.Pagination;
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
	
	
	//계약 정보 얻기
	public List<TradePriorityDTO> getTradeInfo(String bPlCode){
		return sellMapper.getTradeInfo(bPlCode);
	}
	
	//발전소 공고 코드로 공고상태값 가져오기
	
	public BidPlantDTO getPlantAcStatusByCode(String bPlCode) {
		return sellMapper.getPlantAcStatusByCode(bPlCode);
		
	}
	
	//부품 재공고 신청
	public void addComponentRebidApply(BidComponentDTO bidComponentDTO,MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request ) throws Exception{
		System.out.println(bidComponentDTO.getbCpGroupcode());
		sellMapper.updateBComponentRecentlyYn(bidComponentDTO.getbCpGroupcode());
		sellMapper.addComponentRebidApply(bidComponentDTO);
		
		String bCpCode = sellMapper.getBidComponentCode(bidComponentDTO.getCpCode());
		
		List<FileDTO> filelist = fileUtils.parseFileInfo(bCpCode,1,"공고서류", multipartHttpServletRequest,request);
		
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.addFile(filelist);
		}
		
	}
	
	//발전소 재공고 신청
	public void addPlantRebidApply(BidPlantDTO bidPlantDTO,MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
		sellMapper.updateBPlantRecentlyYn(bidPlantDTO.getbPlGroupcode());
		sellMapper.addPlantRebidApply(bidPlantDTO);
		
		BidPlantDTO bidCode =sellMapper.getBidPlantCode(bidPlantDTO.getBzPlCode());
		System.out.println(bidCode.getbPlCode()+"<-------bidCode.getbPlCode()");
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidCode.getbPlCode(),1,"공고서류", multipartHttpServletRequest,request);
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.addFile(filelist);
		}
	}
	
	//팝업창 띄우기 위해서 
		//부품거래상태
	public List<BidComponentDTO> getBidComponentAcById(String id){
		return sellMapper.getBidComponentAcById(id);
	}
		//발전소거래상태
	public List<BidPlantDTO> getBidPlantAcById(String id) {
		return sellMapper.getBidPlantAcById(id);
	}
	
	//발전소 재공고 화면 
	public BidPlantDTO getBidPlantAcByIdCode(String id, String code){
		return sellMapper.getBidPlantAcByIdCode(id, code);
	}
	
	public BidComponentDTO getBidComponentAcByIdCode(String id, String code) {
		return sellMapper.getBidComponentAcByIdCode(id, code);
	}
	
	
	//update rank
	
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
		String bCpCode = sellMapper.getBidComponentCode(bidComponentDto.getCpCode());
		
		
		List<FileDTO> filelist = fileUtils.parseFileInfo(bCpCode,1,"공고서류", multipartHttpServletRequest,request);
		
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.removeApplyFile(bidComponentDto.getbCpCode());
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
		String bCpCode = sellMapper.getBidComponentCode(bidComponentDto.getCpCode());
		
		
		List<FileDTO> filelist = fileUtils.parseFileInfo(bCpCode,1,"공고서류", multipartHttpServletRequest,request);
		
		if (CollectionUtils.isEmpty(filelist) == false) {
			fileMapper.addFile(filelist);
		}
	}
		
	
	//부품공고상태(공고진행중 > 거래진행중)으로 바꾸는 메서드 실행
	public void updateComponentAcStatus() throws Exception{
		System.out.println("updateComponentAcStatus실행");
		List<BidComponentDTO> acList = sellMapper.getComponentAcStatus();
		
		for(int i =0; i<acList.size(); i++) {
			String decisionDate = acList.get(i).getbCpDateDecision1();
			
			if(decisionDate!=null) {
				decisionDate = decisionDate.substring(0,10);
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String today = format.format(date);
				if(decisionDate.equals(today)) {
					sellMapper.updateComponentAcStatus();
				}
			}
		}
	}
	
	
	//발전소공고상태(공고진행중 > 거래진행중)으로 바꾸는 메서드 실행
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
	public List<TradePriorityDTO> getPaymentAvailable(String mId, String searchKey, String searchValue,TradePriorityDTO tradePriorityDTO) throws Exception{
		if(searchKey!=null) {
			if("announcedTitle".equals(searchKey)) {
				searchKey = "announced_title";
			}else if("mIdBuyer".equals(searchKey)) {
				searchKey = "m_id_buyer";
			}
			
		}
		List<TradePriorityDTO> availableList = null;
		int availableCount = sellMapper.getPaymentAvailableCount(mId, searchKey, searchValue, tradePriorityDTO);
		
		Pagination pagination = new Pagination(tradePriorityDTO);
		pagination.setTotalRecordCount(availableCount);
		
		
		tradePriorityDTO.setPagination(pagination);
		
		if(availableCount>0) {
			availableList = sellMapper.getPaymentAvailable(mId, searchKey, searchValue, tradePriorityDTO);
		}
		
		return availableList;
	}
	
	//출금 신청 한 리스트
	public List<TradePriorityDTO> getPaymentApplyList(String mId, String searchKeyApply, String searchValueApply,TradePriorityDTO tradePriorityDTO) throws Exception{
		if(searchKeyApply!=null) {
			if("announcedTitle".equals(searchKeyApply)) {
				searchKeyApply = "announced_title";
			}else if("mIdBuyer".equals(searchKeyApply)) {
				searchKeyApply = "m_id_buyer";
			}
			
		}
		List<TradePriorityDTO> applyList = null;
		int applyCount = sellMapper.getPaymentApplyListCount(mId, searchKeyApply, searchValueApply, tradePriorityDTO);
		
		Pagination pagination = new Pagination(tradePriorityDTO);
		pagination.setTotalRecordCount(applyCount);
		
		
		tradePriorityDTO.setPagination(pagination);
		
		if(applyCount>0) {
			applyList = sellMapper.getPaymentApplyList(mId, searchKeyApply, searchValueApply, tradePriorityDTO);
		}
		
		return applyList;
	}
	
	public List<TradePriorityDTO> getPaymentOutList(String mId, String searchKeyFinish, String searchValueFinish,TradePriorityDTO tradePriorityDTO) throws Exception{
		if(searchKeyFinish!=null) {
			if("announcedTitle".equals(searchKeyFinish)) {
				searchKeyFinish = "announced_title";
			}else if("mIdBuyer".equals(searchKeyFinish)) {
				searchKeyFinish = "m_id_buyer";
			}
			
		}
		List<TradePriorityDTO> finishList = null;
		int finishCount = sellMapper.getPaymentOutListCount(mId, searchKeyFinish, searchValueFinish, tradePriorityDTO);
		
		Pagination pagination = new Pagination(tradePriorityDTO);
		pagination.setTotalRecordCount(finishCount);
		
		
		tradePriorityDTO.setPagination(pagination);
		
		if(finishCount>0) {
			finishList = sellMapper.getPaymentOutList(mId, searchKeyFinish, searchValueFinish, tradePriorityDTO);
		}
		
		return finishList;
	}
	
	//해당 아이디의 부품공고 리스트를 가져옴
	public List<BidComponentDTO> getBidComponentById(String mId, String searchKeyCp, String searchValueCp, BidComponentDTO bidComponentDTO) throws Exception{
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
		
		List<BidComponentDTO> bidComponentList = null;
		
		int bidComponentCount = sellMapper.getBidComponentCount(mId,searchKeyCp,searchValueCp, bidComponentDTO);
		
		Pagination pagination = new Pagination(bidComponentDTO);
		
		pagination.setTotalRecordCount(bidComponentCount);
		
		bidComponentDTO.setPagination(pagination);
		
		if(bidComponentCount > 0) {
			bidComponentList = sellMapper.getBidComponentById(mId,searchKeyCp,searchValueCp,bidComponentDTO);
		}
		
		
		
		
		return bidComponentList;
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
	
	public void removePlantApply(String code,String groupCode)  throws Exception{
		sellMapper.removePlantApply(code);
		fileMapper.removeApplyFile(code);
		
		String bPlCode = sellMapper.getRecentlybPlCodeByGroupcode(groupCode);
		sellMapper.updateBPlantRecentlyNy(bPlCode);
		
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
	

	public	List<BidPlantDTO> getBidPlantbyId(String mId, String searchKey, String searchValue,BidPlantDTO bidPlantDTO)  throws Exception{

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
		List<BidPlantDTO> bidPlantList = null;
		int bidPlantCount = sellMapper.getBidPlantCount(mId,searchKey,searchValue, bidPlantDTO);
		Pagination pagination = new Pagination(bidPlantDTO);
		pagination.setTotalRecordCount(bidPlantCount);
		bidPlantDTO.setPagination(pagination);
		if(bidPlantCount > 0) {
			bidPlantList = sellMapper.getBidPlantbyId(mId,searchKey,searchValue,bidPlantDTO);
		}
		
			
		return bidPlantList;
	}
	
	public List<BusinessPlantDTO> getPlantName(String mId)  throws Exception{
		List<BusinessPlantDTO> bzPlDto = sellMapper.getPlantName(mId);

		return bzPlDto;
	}

}
