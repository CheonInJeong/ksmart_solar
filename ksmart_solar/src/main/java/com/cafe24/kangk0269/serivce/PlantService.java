package com.cafe24.kangk0269.serivce;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.cafe24.kangk0269.dao.PlantMapper;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.ComponentDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;
import com.cafe24.kangk0269.dto.PlantKpxDTO;
import com.cafe24.kangk0269.dto.PlantRadiationDTO;

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
	
	// 운영발전소리스트(아이디)
	public List<BusinessPlantDTO> getOperPlantListById(String mId){
		return plantMapper.getOperPlantListById(mId);
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
		PlantKpxDTO pk = new PlantKpxDTO();
		pk = plantMapper.getKpxTodayData();
		List<BusinessPlantDTO> plantList = plantMapper.getPlantListByCode(bzCode);
		model.addAttribute("bzPlName", plantList.get(0).getBzPlName());
		model.addAttribute("bzPlPower", plantList.get(0).getBzPlPower());
		model.addAttribute("smp", Double.parseDouble(pk.getPlKpxSmpShoreAvg().replace(",", "")));
		model.addAttribute("rec", Double.parseDouble(pk.getPlKpxRecAvg().replace(",", "")));
		return benefitGraph;
	}

	public PlantDepreciationDTO getPlantDepreciationByBzCode(String bzPlCode) {
		System.out.println("서비스실행");
		PlantDepreciationDTO pd = plantMapper.getPlantDepreciationByBzCode(bzPlCode);
		return pd;
	}
	
	
	//bz_pl_code 입력시 현재 잔존가치를 리턴해주는 메서드
	public int residualValue (String bz_pl_code, Model model) throws ParseException {
		double[] rateArray = {0.007936508,0.007539683,0.007142857,0.006746032,0.006349206,0.005952381,0.005555556,0.00515873,0.004761905,0.004365079,0.003968254,0.003571429,0.003174603,0.002777778,0.002380952,0.001984127,0.001587302,0.001190476,0.000793651,0.000396825};
		System.out.println(bz_pl_code + " <<< 입력받은 bz_pl_code 값");
		PlantDepreciationDTO pdc = getPlantDepreciationByBzCode(bz_pl_code);
		int residualValue = 0;
		double printRate = 0;
		int	diffenceMonth = 0;
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
			diffenceMonth = (int) (diffrence/30);
			  
			double sumRate = 0; 
			if((diffenceMonth/12) > 0) { 
				for(int i=0; i<diffenceMonth/12; i++) { 
				sumRate += rateArray[i]*12; 
				} 
			}
			sumRate += (rateArray[(diffenceMonth/12)])*((diffenceMonth%12));
			residualValue = (int) (priceBased - (priceBased * sumRate)); 
			printRate = Math.round((sumRate * 1000));
			System.out.println("///////////////////////////////////////");
			System.out.println("///////////////////////////////////////");
			System.out.println("감가율 : " + printRate/10 + "%");
			System.out.println("경과시간 : " + (diffenceMonth/12) + "년 " + diffenceMonth%12 + "개월" );
		}
		System.out.println("기준금액 : " + priceBased + "원");
		System.out.println("잔존가치 : " + residualValue + "원");
		System.out.println("///////////////////////////////////////");
		System.out.println("///////////////////////////////////////");
		
		//감가율*10 , 경과시간(연), 경과시간(월), 기준금액, 잔존가치
		//int[] returnArry = {(int)printRate, (diffenceMonth/12), (diffenceMonth%12), priceBased, residualValue};
		DecimalFormat formatter = new DecimalFormat("###,###");
		model.addAttribute("residualRate", printRate/10);
		model.addAttribute("pastYear", (diffenceMonth/12));
		model.addAttribute("pastMonth", (diffenceMonth%12));
		model.addAttribute("startDate", startDateString.replace("-", ""));
		model.addAttribute("basedPrice", formatter.format((priceBased/1000)*1000));
		model.addAttribute("basedPrice2", (priceBased/1000)*1000);
		model.addAttribute("residualPrice", formatter.format((residualValue/1000)*1000));
		
		return residualValue;
	}
	
	
	public int residualValue (String bz_pl_code) throws ParseException {
		double[] rateArray = {0.007936508,0.007539683,0.007142857,0.006746032,0.006349206,0.005952381,0.005555556,0.00515873,0.004761905,0.004365079,0.003968254,0.003571429,0.003174603,0.002777778,0.002380952,0.001984127,0.001587302,0.001190476,0.000793651,0.000396825};
		System.out.println(bz_pl_code + " <<< 입력받은 bz_pl_code 값");
		PlantDepreciationDTO pdc = getPlantDepreciationByBzCode(bz_pl_code);
		int residualValue = 0;
		double printRate = 0;
		int	diffenceMonth = 0;
		String startDateString = pdc.getPlDepStartDate();
		int priceBased = pdc.getPlDepPriceBased(); 
		if(startDateString != null) { 
			Date date = new Date(); 
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = transFormat.format(date); 
			Date nowDate = transFormat.parse(nowTime); 
			Date startDate = transFormat.parse(startDateString); 
			long diffTime = nowDate.getTime() -	startDate.getTime(); 
			TimeUnit time = TimeUnit.DAYS;
			long diffrence =	time.convert(diffTime, TimeUnit.MILLISECONDS);
			System.out.println("The difference in days is : "+diffrence);
			System.out.println("The difference in Month is : "+diffrence/30); 
			diffenceMonth = (int) (diffrence/30);
			
			double sumRate = 0; 
			if((diffenceMonth/12) > 0) { 
				for(int i=0; i<diffenceMonth/12; i++) { 
					sumRate += rateArray[i]*12; 
				} 
			}
			sumRate += (rateArray[(diffenceMonth/12)])*((diffenceMonth%12));
			residualValue = (int) (priceBased - (priceBased * sumRate)); 
			printRate = Math.round((sumRate * 1000));
			System.out.println("///////////////////////////////////////");
			System.out.println("///////////////////////////////////////");
			System.out.println("감가율 : " + printRate/10 + "%");
			System.out.println("경과시간 : " + (diffenceMonth/12) + "년 " + diffenceMonth%12 + "개월" );
		}
		System.out.println("기준금액 : " + priceBased + "원");
		System.out.println("잔존가치 : " + residualValue + "원");
		System.out.println("///////////////////////////////////////");
		System.out.println("///////////////////////////////////////");
		return residualValue;
	}
	
	//오늘 발전데이터 가져오기
	public void getGenerationAnalysisData(Model model, String bzCode) throws ParseException {
		BusinessPlantDTO bp = plantMapper.getPlantInfoBybzPlCode(bzCode);
		int addrCode = bp.getBzPlAddrCode();
		String addrCodeName = "서울";
		switch(addrCode) {
			case 1: addrCodeName = "서울";
				break;
			case 2: addrCodeName = "부산";
				break;
			case 3: addrCodeName = "대구";
				break;
			case 4: addrCodeName = "인천";
				break;
			case 5: addrCodeName = "광주";
				break;
			case 6: addrCodeName = "대전";
				break;
			case 7: addrCodeName = "부산";
				break;
			case 8: addrCodeName = "청주";
				break;
			case 9: addrCodeName = "수원";
				break;
			case 10: addrCodeName = "원주";
				break;
			case 11: addrCodeName = "충주";
				break;
			case 12: addrCodeName = "서산";
				break;
			case 13: addrCodeName = "전주";
				break;
			case 14: addrCodeName = "목포";
				break;
			case 15: addrCodeName = "포항";
				break;
			case 16: addrCodeName = "대구";
				break;
			case 17: addrCodeName = "제주";
				break;
		}
		Date date = new Date(); 
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = transFormat.format(date); 
		Date nowDate = transFormat.parse(nowTime);
		long yesterdayTime = (nowDate.getTime() - (24*60*60*1000));
		String yesterday = transFormat.format(yesterdayTime); 
		String startTime = yesterday + " 00";
		String endTime   = yesterday + " 23";
		String startMonth = nowTime.substring(0,8) + "01";
		int bzPlPower = bp.getBzPlPower();
		DecimalFormat formatter = new DecimalFormat("###,###");
		List<PlantRadiationDTO> pr = plantMapper.getRadiationData(addrCodeName, startTime, endTime);
		String sumMonthData = plantMapper.getRadiationSumMonthData(addrCodeName, startMonth, nowTime);
		int dayGen = 0;
		int monthGen = (int) Math.round((Double.parseDouble(sumMonthData) *10000/36) * ((double)bzPlPower/1000));
		int[] radData = new int[24];
		for(int i=0; i<24; i++) {
			String Icsr = pr.get(i).getPlRadIcsr();
			if("".equals(Icsr)) {
				radData[i] = 0;
			}else {
				radData[i] = (int) Math.round(Double.parseDouble(pr.get(i).getPlRadIcsr())*10000/36);
			}
			dayGen += radData[i] * ((double)bzPlPower/1000);
		}
		PlantKpxDTO pk = new PlantKpxDTO();
		pk = plantMapper.getKpxTodayData();
		double smp = Double.parseDouble(pk.getPlKpxSmpShoreAvg().replace(",", ""));
		double rec = Double.parseDouble(pk.getPlKpxRecAvg().replace(",", ""));
		
		//smp 수익 + rec 수익
		//smp 수익 = 발전량 * smp 가격
		//rec 수익 = 발전량 * (rec가격 /1000) * 가중치
		//수익 계산공식: 예상수익 = (발전량 * smp가격) + (발전량 * (rec가격/1000) * 가중치)
		System.out.println("일간 발전량 :" + formatter.format(dayGen));
		System.out.println("일간 예상수익 :" + formatter.format((dayGen*smp) + (rec/1000 * dayGen)));
		System.out.println("월간 발전량 :" + formatter.format(monthGen));
		System.out.println("월간 예상수익 :" + formatter.format((monthGen*smp) + (rec/1000 * monthGen)));
		System.out.println("기준일 :" + yesterday.substring(0,4) + "년 " + yesterday.substring(5,7) + "월 " + yesterday.substring(8,10) + "일" );
		System.out.println("기준월 :" + nowTime.substring(0,4) + "년 " + nowTime.substring(5,7) + "월");
		
		model.addAttribute("bzPlName", 		bp.getBzPlName());
		model.addAttribute("bzPlCode", 		bp.getBzPlCode());
		model.addAttribute("bzPlPower",	 	bzPlPower);
		model.addAttribute("rec",		 	formatter.format(rec));
		model.addAttribute("smp",	 		smp);
		model.addAttribute("dayGen", 		formatter.format(dayGen));
		model.addAttribute("datBenefit",	formatter.format((dayGen*smp) + (rec/1000 * dayGen)));
		model.addAttribute("monthGen", 		formatter.format(monthGen));
		model.addAttribute("monthBenefit",	formatter.format((monthGen*smp) + (rec/1000 * monthGen)));
		model.addAttribute("basedDate",		yesterday.substring(0,4) + "년 " + yesterday.substring(5,7) + "월 " + yesterday.substring(8,10) + "일" );
		model.addAttribute("basedMonth",	nowTime.substring(0,4) + "년 " + nowTime.substring(5,7) + "월");
	}
	
	public int[] getAjaxGenerationAnalysisData(String bzPlCode, String requestDate) throws ParseException {
		//처음 실행할때는 오늘날짜로 입력해서 실행
		BusinessPlantDTO bp = plantMapper.getPlantInfoBybzPlCode(bzPlCode);
		int addrCode = bp.getBzPlAddrCode();
		String addrCodeName = "서울";
		switch(addrCode) {
			case 1: addrCodeName = "서울";
				break;
			case 2: addrCodeName = "부산";
				break;
			case 3: addrCodeName = "대구";
				break;
			case 4: addrCodeName = "인천";
				break;
			case 5: addrCodeName = "광주";
				break;
			case 6: addrCodeName = "대전";
				break;
			case 7: addrCodeName = "부산";
				break;
			case 8: addrCodeName = "청주";
				break;
			case 9: addrCodeName = "수원";
				break;
			case 10: addrCodeName = "원주";
				break;
			case 11: addrCodeName = "충주";
				break;
			case 12: addrCodeName = "서산";
				break;
			case 13: addrCodeName = "전주";
				break;
			case 14: addrCodeName = "목포";
				break;
			case 15: addrCodeName = "포항";
				break;
			case 16: addrCodeName = "대구";
				break;
			case 17: addrCodeName = "제주";
				break;
		}
		Date date = new Date(); 
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = transFormat.format(date); 
		Date nowDate = transFormat.parse(nowTime);
		long yesterdayTime = (nowDate.getTime() - (24*60*60*1000));
		String yesterday = transFormat.format(yesterdayTime); 
		String startTime = yesterday + " 00";
		String endTime   = yesterday + " 23";
		
		/* 
		 * 검색기능 추가시 고려해야함
		String startTime = requestDate + " 00";
		String endTime   = requestDate + " 23";
		 * */
		List<PlantRadiationDTO> pr = plantMapper.getRadiationData(addrCodeName, startTime, endTime);
		int[] radData = new int[24];
		for(int i=0; i<pr.size(); i++) {
			String Icsr = pr.get(i).getPlRadIcsr();
			if("".equals(Icsr)) {
				radData[i] = 0;
			}else {
				radData[i] = (int) Math.round(Double.parseDouble(pr.get(i).getPlRadIcsr())*10000/36);
			}
		}
		return radData;
	}

	public int plantDelete(String plCode) {
		return plantMapper.plantDelete(plCode);
	}

	public int plantModify(BusinessPlantDTO bp) {
		System.out.println(bp);
		return plantMapper.plantModify(bp);
	}
	
	
	
	
}