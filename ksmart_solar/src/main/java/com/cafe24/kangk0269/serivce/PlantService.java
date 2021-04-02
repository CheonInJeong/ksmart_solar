package com.cafe24.kangk0269.serivce;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.cafe24.kangk0269.dao.PlantMapper;
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
		System.out.println(plantList);
		System.out.println(plantList.get(0));
		DecimalFormat formatter = new DecimalFormat("###,###");
		model.addAttribute("area", formatter.format(plantList.get(0).getBzPlArea()));
		model.addAttribute("power", formatter.format(plantList.get(0).getBzPlPower()));
		model.addAttribute("plName", plantList.get(0).getBzPlName());
		model.addAttribute("depPrice", formatter.format(pd.getPlDepPrice()));
		model.addAttribute("basedPrice", formatter.format(pd.getPlDepPriceBased()));
		model.addAttribute("startDate", pd.getPlDepStartDate());
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

	public int[] getBenefitAnalysis(Model model, String bzCode) {
		//리턴해야할 것들
		//240개 값, 유지비용,
		int[] benefitGraph = new int[240];
		System.out.println(bzCode);
		System.out.println(model.getAttribute("plName"));
		
		
		List<BusinessPlantDTO> plantList = plantMapper.getPlantListByCode(bzCode);
		model.addAttribute("plantName", plantList.get(0).getBzPlName());
		
		
		
		
		
		return benefitGraph;
	}

	public PlantDepreciationDTO getPlantDepreciationByBzCode(String bzPlCode) {
		System.out.println("서비스실행");
		PlantDepreciationDTO pd = plantMapper.getPlantDepreciationByBzCode(bzPlCode);
		return pd;
	}
	
	
	//bz_pl_code 입력시 현재 잔존가치를 리턴해주는 메서드
	public int noResidualValue (String bz_pl_code) throws ParseException {
		double[] rateArray = {0.007936508,0.007539683,0.007142857,0.006746032,0.006349206,0.005952381,0.005555556,0.00515873,0.004761905,0.004365079,0.003968254,0.003571429,0.003174603,0.002777778,0.002380952,0.001984127,0.001587302,0.001190476,0.000793651,0.000396825};
		System.out.println(bz_pl_code + " <<< 입력받은 bz_pl_code 값");
		PlantDepreciationDTO pdc = getPlantDepreciationByBzCode(bz_pl_code);
		int residualValue = 0;
		
		String startDateString = pdc.getPlDepStartDate();
		int priceBased = pdc.getPlDepPriceBased(); 
		if(startDateString != null) { 
			Date date = new
			Date(); SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = transFormat.format(date); Date nowDate =
			transFormat.parse(nowTime); 
			Date startDate = transFormat.parse(startDateString); 
			long diffTime = nowDate.getTime() -	startDate.getTime(); 
			TimeUnit time = TimeUnit.DAYS; 
			long diffrence =	time.convert(diffTime, TimeUnit.MILLISECONDS);
			System.out.println("The difference in days is : "+diffrence);
			System.out.println("The difference in Month is : "+diffrence/30); 
			int	diffenceMonth = (int) (diffrence/30);
			  
			double sumRate = 0; 
			if((diffenceMonth/12) > 0) { 
				for(int i=0; i<diffenceMonth/12; i++) { 
				sumRate += rateArray[i]*12; 
				} 
			} 
			sumRate += (rateArray[(diffenceMonth/12)])*((diffenceMonth%12));
			residualValue = (int) (priceBased - (priceBased * sumRate)); 
		}
		return residualValue;
	}

}