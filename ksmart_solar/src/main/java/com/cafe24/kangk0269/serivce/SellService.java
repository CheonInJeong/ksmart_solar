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
	
	public void addPlantApply(BidPlantDTO bidPlantDto,MultipartHttpServletRequest multipartHttpServletRequest,HttpServletRequest request) throws Exception {
		String bzPlCode = bidPlantDto.getBzPlCode();
		System.out.println(bzPlCode+"<---발전소코드 서비스");
		
		sellMapper.addPlantApply(bidPlantDto);
		
		System.out.println(bidPlantDto.getbPlCode()+"<------파일 관련 공고 코드");
		List<FileDTO> filelist = fileUtils.parseFileInfo(bidPlantDto.getbPlCode(), multipartHttpServletRequest,request);
		System.out.println("실행확인1");
		if (CollectionUtils.isEmpty(filelist) == false) {
			System.out.println("실행확인2");
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
