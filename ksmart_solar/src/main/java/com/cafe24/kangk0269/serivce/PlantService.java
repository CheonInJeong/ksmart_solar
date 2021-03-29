package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.cafe24.kangk0269.dao.PlantMapper;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Service
@Transactional
public class PlantService {

	private final PlantMapper plantMapper;

	@Autowired
	public PlantService(PlantMapper plantMapper) {
		this.plantMapper = plantMapper;
	}

	public List<BusinessPlantDTO> getAllPlantAdmitList() {

		return plantMapper.getAllPlantAdmitList();
	}
	
	public List<BusinessPlantDTO> getPlantListById(String SID){
		List<BusinessPlantDTO> plantList = plantMapper.getPlantListById(SID);
		return plantList;
	}

	public List<BusinessPlantDTO> getPlantListByCode(Model model, String bzCode) {
		List<BusinessPlantDTO> plantList = plantMapper.getPlantListByCode(bzCode);
		
		//발전량 예시(구현 예정)
		double plantGenDay = 111.12;
		double plantGenMonth = 36542.12;
		
		model.addAttribute("plantGenDay", plantGenDay);
		model.addAttribute("plantGenMonth", plantGenMonth);
		
		return plantList;
	}
	
	
}