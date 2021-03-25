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
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.FileDTO;

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
	

	public	List<BidPlantDTO> getBidPlantbyId(String mId){
		List<BidPlantDTO> bidPlantList = sellMapper.getBidPlantbyId(mId);
		
		return bidPlantList;
	}
	
	public List<BusinessPlantDTO> getPlantName(String mId){
		List<BusinessPlantDTO> bzPlDto = sellMapper.getPlantName(mId);

		return bzPlDto;
	}

}
