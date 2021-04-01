package com.cafe24.kangk0269.serivce;

import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.cafe24.kangk0269.dao.PlantMapper;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;
import com.cafe24.kangk0269.dto.PlantKpxDTO;

@Service
@Transactional
public class PlantService {

	private final PlantMapper plantMapper;

	@Autowired
	public PlantService(PlantMapper plantMapper) {
		this.plantMapper = plantMapper;
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("PlantService bean 등록");
		System.out.println("=========================================================");
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

	public List<BusinessPlantDTO> getPlantListByCode(Model model, String bzCode) {
		List<BusinessPlantDTO> plantList = plantMapper.getPlantListByCode(bzCode);
		return plantList;
	}
	
	public List<BusinessPlantDTO> getPlantDetail(Model model, String bzCode) {
		List<BusinessPlantDTO> plantList = plantMapper.getPlantListByCode(bzCode);
		PlantKpxDTO pk = plantMapper.getKpxTodayData();
		String bzPlCode = plantList.get(0).getBzPlCode();
		PlantDepreciationDTO pd = plantMapper.getPlantDepreciationByBzCode(bzPlCode);
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		model.addAttribute("area", formatter.format(plantList.get(0).getBzPlArea()));
		model.addAttribute("power", formatter.format(plantList.get(0).getBzPlPower()));
		model.addAttribute("depPrice", formatter.format(pd.getPlDepPrice()));
		model.addAttribute("basedPrice", formatter.format(pd.getPlDepPriceBased()));
		model.addAttribute("plantKpx", pk);
		model.addAttribute("plantDepreciation", pd);
		model.addAttribute("plantListByCode", plantList);
		
		
		
		double plantGenDay = 111.12;
		double plantGenMonth = 36542.12;
		model.addAttribute("plantGenDay", plantGenDay);
		model.addAttribute("plantGenMonth", plantGenMonth);
		
		return plantList;
	}

	public int crawLingKpxData(PlantKpxDTO pk) {
		int result = plantMapper.crawLingKpxData(pk);
		return result;
	}
	
	
}