package com.cafe24.kangk0269.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;
import com.cafe24.kangk0269.dto.PlantKpxDTO;
import com.cafe24.kangk0269.dto.PlantRadiationDTO;

@Mapper
public interface PlantMapper {

	// 운영발전소리스트(아이디)
	public List<BusinessPlantDTO> getOperPlantListById(String mId);
	
	// 발전소인증신청 반려
	public int plantReturn(BusinessPlantDTO plant);
	
	// 발전소인증신청 승인
	public int plantAdmit(BusinessPlantDTO plant);
	
	// 발전소인증신청 상세정보
	public BusinessPlantDTO getPlantInfoBybzPlCode(String bzPlCode);
	
	// 발전소인증신청목록
	public List<BusinessPlantDTO> getAllPlantAdmitList();
	
	//발전소리스트(아이디)
	public List<BusinessPlantDTO> getPlantListById(String SID);

	//발전소리스트(코드)
	public List<BusinessPlantDTO> getPlantListByCode(String bzCode);

	//KPX 크롤링
	public int crawLingKpxData(PlantKpxDTO pk);
	
	public PlantKpxDTO getKpxTodayData();
	
	public PlantDepreciationDTO getPlantDepreciationByBzCode(String bzPlCode);

	public List<ComponentDTO> getComponentListById(String SID);

	public List<PlantRadiationDTO> getRadiationData(String addrCodeName, String startTime, String endTime);
	
	public String getRadiationSumMonthData(String addrCodeName, String startTime, String endTime);

	public int plantDelete(String plCode);

	public int plantModify(BusinessPlantDTO bp);

	public List<PlantRadiationDTO> searchRadiation(String dataTime);
}