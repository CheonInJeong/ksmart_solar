package com.cafe24.kangk0269.serivce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.kangk0269.common.DepreciationCalculate;
import com.cafe24.kangk0269.dao.BusinessMapper;
import com.cafe24.kangk0269.dto.BusinessDTO;
import com.cafe24.kangk0269.dto.BusinessPlantDTO;
import com.cafe24.kangk0269.dto.PlantDepreciationDTO;

@Service
@Transactional
public class BusinessService {

	private final BusinessMapper businessMapper;

	@Autowired
	public BusinessService(BusinessMapper businessMapper) {
		this.businessMapper = businessMapper;
	}
	
	@PostConstruct
	public void initialize() {
		System.out.println("=========================================================");
		System.out.println("BusinessService bean 등록");
		System.out.println("=========================================================");
	}

	// 사업자인증신청 반려
	public int businessReturn(BusinessDTO business) {
		return businessMapper.businessReturn(business);
	}
	
	// 사업자인증신청 승인
	public int businessAdmit(BusinessDTO business) {
		return businessMapper.businessAdmit(business);
	}
	
	// 사업자신청 상세조회(아이디)
	public List<BusinessDTO> getBusinessInfoById(String mId) {
		return businessMapper.getBusinessInfoById(mId);
	}
	
	// 사업자신청 상세조회(사업자코드)
	public BusinessDTO getBusinessInfoBybzCode(String bzCode) {
		return businessMapper.getBusinessInfoBybzCode(bzCode);
	}
	
	// 전체 사업자신청 조회
	public List<BusinessDTO> getAllBusinessAdmitList(int start,int end,String searchKey,String searchValue){
		if(searchKey != null && searchKey != "") {
			if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}else if("bzCompanyName".equals(searchKey)) {
				searchKey = "bz_company_name";
			}else if("bzCeoName".equals(searchKey)) {
				searchKey = "bz_ceo_name";
			}else if("bzPlace".equals(searchKey)) {
				searchKey = "bz_place";
			}else if("bzType".equals(searchKey)){
				searchKey = "bz_type";
			}else {
				searchKey = "bz_check";
			}
		}
		return businessMapper.getAllBusinessAdmitList(start, end, searchKey, searchValue);
	}
	// 사업자인증신청 리스트 수
	public int getAllBusinessAdmitListCnt(String searchKey,String searchValue) {
		if(searchKey != null && searchKey != "") {
			if("mId".equals(searchKey)) {
				searchKey = "m_id";
			}else if("bzCompanyName".equals(searchKey)) {
				searchKey = "bz_company_name";
			}else if("bzCeoName".equals(searchKey)) {
				searchKey = "bz_ceo_name";
			}else if("bzPlace".equals(searchKey)) {
				searchKey = "bz_place";
			}else if("bzType".equals(searchKey)){
				searchKey = "bz_type";
			}else {
				searchKey = "bz_check";
			}
		}
		return businessMapper.getAllBusinessAdmitListCnt(searchKey, searchValue);
	}
	
	// 일반 사업자 신청(석인)
	public int addRecycleEntrepreneur(BusinessDTO bs){
		
		System.out.println(bs);
		
		return businessMapper.addRecycleEntrepreneur(bs);
	}
	
	
	//태양광 사업자 신청(3개테이블 insert, 석인)
	public int addSolarEntrepreneur(BusinessDTO bs, BusinessPlantDTO bp, PlantDepreciationDTO pd) throws ParseException {
		System.out.println(bs);
		System.out.println(bp);
		System.out.println(pd);
		
		
		String bzPlCode = businessMapper.getInsertBzPlCode();
		String bzCode = businessMapper.getInsertBzCode();
		String plDepUsed = "Y";
		int plDepServicelife = 240;
		String startDateString = pd.getPlDepStartDate();
		String buyDateString = pd.getPlDepBuyDate();
		int PlDepPriceBased = 0;
		System.out.println(startDateString + " <<< startDateString");
		System.out.println(buyDateString + " <<< buyDateString");
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = transFormat.parse(startDateString);
		Date buyDate = transFormat.parse(buyDateString);
		DepreciationCalculate dc = new DepreciationCalculate();
		
		if((buyDate.getTime() - startDate.getTime()) == 0) {
			plDepUsed = "N";
			PlDepPriceBased = pd.getPlDepPrice();
		}else {
			long diffTime = buyDate.getTime() - startDate.getTime();
			TimeUnit time = TimeUnit.DAYS; 
			long diffrence = time.convert(diffTime, TimeUnit.MILLISECONDS);
			System.out.println("The difference in days is : "+diffrence);
			System.out.println("The difference in Month is : "+diffrence/30);
			int diffenceMonth = (int) (diffrence/30);
			plDepServicelife = 240 - diffenceMonth;
			PlDepPriceBased = dc.calculPlDepPriceBased(pd.getPlDepPrice(), diffenceMonth);
		}
		
		bp.setBzPlCode(bzPlCode);
		bp.setBzCode(bzCode);
		bp.setBzPlCheck("N");
		pd.setBzPlCode(bzPlCode);
		pd.setPlDepPriceBased(PlDepPriceBased);
		pd.setPlDepUsed(plDepUsed);
		pd.setPlDepServicelife(plDepServicelife);
		 
		int addBusinessResult = businessMapper.addRecycleEntrepreneur(bs);
		System.out.println(bp.getBzCode() + " <<< getBzCode");
		System.out.println(businessMapper.getInsertBzCode() + " <<< 쿼리 실행결과");
		int addPlantResult = businessMapper.addSolarEntrepreneur(bp);
		int addDepreciation = businessMapper.addDepreciation(pd);
		int returnResult = 0;
		
		if(addBusinessResult == 1 && addPlantResult == 1 && addDepreciation == 1) {
			returnResult = 1;
		}
		return returnResult;
	}
	
	
	
}