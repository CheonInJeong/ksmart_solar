package com.cafe24.kangk0269.serivce;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dao.BidListMapper;
import com.cafe24.kangk0269.dao.BidPlantMapper;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.FileDTO;

@Service
@Transactional
public class BidListService {
	private final BidListMapper bidListMapper;
	private final FileUtils fileUtils;
	private final SellMapper sellMapper;
	
	@Autowired
	public BidListService(BidListMapper bidListMapper,FileUtils fileUtils,SellMapper sellMapper) {
		this.bidListMapper = bidListMapper; 
		this.fileUtils = fileUtils; 
		this.sellMapper = sellMapper; 
	}
	
	public double getDepositRate() {
		return bidListMapper.getDepositRate();
	}
	//공고신청
	public void addbidList(BidListDTO bidListDTO,MultipartHttpServletRequest multipartHttpServletRequest ,HttpServletRequest request) throws Exception {
		System.out.println("확인");
		bidListMapper.addbidList(bidListDTO);
		String bCode = bidListMapper.getBidCode(bidListDTO.getAnnouncedCode(), bidListDTO.getmId());
		System.out.println(bCode+"--------------------------등록한 입찰 코드");
		
		List<FileDTO> filelist = fileUtils.parseFileInfo(bCode,2,"입찰서류", multipartHttpServletRequest,request);
		if (CollectionUtils.isEmpty(filelist) == false) {
			sellMapper.addFile(filelist);
		}
		 
	}
	
	public int bidCancel(String bCode) {
		return bidListMapper.bidCancel(bCode);
	}
	public int getBidListCount(String announceCode, String id) {
		return bidListMapper.getBidListCount(announceCode, id);
	}
	public BidListDTO getBidList(String announceCode, String id) {
		return getBidList(announceCode,id,null);
	}
	public BidListDTO getBidList(String bCode) {
		System.out.println("확인");
		return getBidList(null,null,bCode);
	}
	public BidListDTO getBidList(String announceCode, String id,String bCode) {
		return bidListMapper.getBidList(announceCode, id,bCode);
	}

}
