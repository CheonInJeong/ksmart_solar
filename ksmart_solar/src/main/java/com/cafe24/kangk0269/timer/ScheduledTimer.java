package com.cafe24.kangk0269.timer;

import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cafe24.kangk0269.api.CrawlingApi;
import com.cafe24.kangk0269.api.RadiationApi;
import com.cafe24.kangk0269.dto.PlantKpxDTO;
import com.cafe24.kangk0269.serivce.PlantService;
import com.cafe24.kangk0269.serivce.PolicyService;
import com.cafe24.kangk0269.serivce.SellService;

 
@Component
public class ScheduledTimer {
	
	@Autowired
	private PlantService plantService;
	@Autowired
	private SellService sellService;
	@Autowired
	private PolicyService policyService;
	
	@Scheduled(cron = "0  01  8  *  *  *") 
	public void radiationApiTimer() throws IOException, ParseException, ClassNotFoundException, SQLException {
		RadiationApi raApi = new RadiationApi();
		raApi.weatherApiAction();
	}
	
	@Scheduled(cron = "0  00  8  *  *  *") 
	public void kpxApiTimer() {
		CrawlingApi crawlingApi = new CrawlingApi();
		PlantKpxDTO pk = new PlantKpxDTO();
		pk = crawlingApi.crawLingKpxData();
		System.out.println(pk);
		plantService.crawLingKpxData(pk);
	}
	
	
	//부품공고상태(공고진행중>공고마감)으로 바꾸는 메서드 실행
	@Scheduled(cron = "30 11 23 * * *")
	public void updateComponentAcStatus() throws Exception {
		sellService.updateComponentAcStatus();
	}
	
	//발전소공고상태(공고진행중 > 공고마감)으로 바꾸는 메서드 실행
	@Scheduled(cron = "0  00  0  *  *  *") 
	public void updateAcStatus() throws Exception {
			sellService.updateAcStatus();
	
	}
	
	//예약한 시간에 예치금 정책 적용 상태 바꾸는 메서드
	@Scheduled(cron = "30  00  0  *  *  *") 
	public void updateDepositStatus() throws IOException, ParseException, ClassNotFoundException, SQLException {
		try {
			policyService.updateDeposit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//예약한 시간에 거래기간 정책 적용 상태 바꾸는 메서드
	@Scheduled(cron = "00  01  0  *  *  *") 
	public void updateTradeStatus() throws IOException, ParseException, ClassNotFoundException, SQLException {
		try {
			policyService.updateTrade();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//예약한 시간에 수수료 정책 적용 상태 바꾸는 메서드
	@Scheduled(cron = "30  01  0  *  *  *") 
	public void updateCommissionStatus() throws IOException, ParseException, ClassNotFoundException, SQLException {
		try {
			policyService.updateCommission();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
		
		
}
