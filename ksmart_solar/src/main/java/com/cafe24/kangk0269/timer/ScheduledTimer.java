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
import com.cafe24.kangk0269.serivce.SellService;

 
@Component
public class ScheduledTimer {
	
	@Autowired
	private PlantService plantService;
	@Autowired
	private SellService sellService;
	
	//매일 오전 8시에 실행 되는 api
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
	//공고상태(공고진행중 > 거래진행중)으로 바꾸는 메서드 실행
	@Scheduled(cron = "0  00  0  *  *  *") 
	public void updateAcStatus() throws IOException, ParseException, ClassNotFoundException, SQLException {
		try {
			sellService.updateAcStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
