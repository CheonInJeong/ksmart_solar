package com.cafe24.kangk0269.serivce;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.BoardMapper;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.BoardDto;
import com.cafe24.kangk0269.dto.BoardFileDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Transactional
@Service
public class SellService {

	@Autowired
	private final SellMapper sellMapper;
	
	public SellService(SellMapper sellMapper) {
		this.sellMapper = sellMapper;
	}
	
	public int addPlantApply(BidPlantDTO bidPlantDto) {
		int result = sellMapper.addPlantApply(bidPlantDto);
		return result;
	}
	
	public BusinessPlantDTO getPlantInformation(String code){
		BusinessPlantDTO bzPlantDto = sellMapper.getPlantInformation(code);
		return bzPlantDto;
	}
	
	public	List<BidPlantDTO> getBidPlantbyId(){
		List<BidPlantDTO> bidPlantList = sellMapper.getBidPlantbyId();
		
		/*
		 * int numberOfBidder = sellMapper.getNumberOfBidder(); int highestPrice =
		 * sellMapper.getHighestPriceByCode();
		 * 
		 * Map<String, Object> map = new HashMap<String,Object>();
		 * map.put("bidPlantList", bidPlantList); map.put("numberOfBidder",
		 * numberOfBidder); map.put("highestPrice", highestPrice);
		 */
		
		return bidPlantList;
	}
	
	public List<BusinessPlantDTO> getPlantName(String mId){
		List<BusinessPlantDTO> bzPlDto = sellMapper.getPlantName(mId);
		return bzPlDto;
	}

}
