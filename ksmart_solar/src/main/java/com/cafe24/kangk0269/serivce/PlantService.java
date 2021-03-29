package com.cafe24.kangk0269.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.dao.PlantMapper;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;

@Service
@Transactional
public class PlantService {

	private final PlantMapper plantMapper;

	@Autowired
	public PlantService(PlantMapper plantMapper) {
		this.plantMapper = plantMapper;
	}
	
	// 발전소인증신청 반려
	public int plantReturn(BusinessPlantDTO plant) {
		return plantMapper.plantReturn(plant);
	}
	
	// 발전소인증신청 승인
	public int plantAdmit(BusinessPlantDTO plant) {
		return plantMapper.plantAdmit(plant);
	}
	
	// 발전소 인증 신청 상세정보
	public BusinessPlantDTO getPlantInfoBybzPlCode(String bzPlCode) {
		return plantMapper.getPlantInfoBybzPlCode(bzPlCode);
	}
	
	// 전체 발전소 인증 신청 목록
	public List<BusinessPlantDTO> getAllPlantAdmitList() {

		return plantMapper.getAllPlantAdmitList();
	}
	
	public List<BusinessPlantDTO> getPlantListById(String SID){
		List<BusinessPlantDTO> plantList = plantMapper.getPlantListById(SID);
		return plantList;
	}
	
	
}