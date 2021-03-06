package com.cafe24.kangk0269.serivce;

import java.util.Iterator;
import java.util.List;

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
	
	
	//파라미터 있어야는뎅....//parameter : String mId 추가하기
	public List<BusinessPlantDTO> getPlantName(){
		List<BusinessPlantDTO> bzPlDto = sellMapper.getPlantName();
		return bzPlDto;
	}

}
