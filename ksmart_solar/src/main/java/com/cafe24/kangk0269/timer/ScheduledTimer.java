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
import com.cafe24.kangk0269.serivce.BidListService;
import com.cafe24.kangk0269.serivce.PlantService;
import com.cafe24.kangk0269.serivce.PolicyService;
import com.cafe24.kangk0269.serivce.ScheduledService;
import com.cafe24.kangk0269.serivce.SellService;

 
@Component
public class ScheduledTimer {
	
	@Autowired
	private PlantService plantService;
	@Autowired
	private SellService sellService;
	@Autowired
	private PolicyService policyService;
	@Autowired
	private BidListService bidListService;
	@Autowired
	private ScheduledService scheduledService;
	
	@Scheduled(cron = "0  00  8  *  *  *") 
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
	
	
	@Scheduled(cron = "5  00  0  *  *  *")
	public void updateComponentAcStatus() throws Exception {
	}
	
	
	@Scheduled(cron = "0  00  0  *  *  *") 
	public void updateBidListStatus() throws IOException, ParseException, ClassNotFoundException, SQLException {
		try {
			//공고마감일에 입찰자 수 0인 경우 거래실패로 상태변경
			scheduledService.updateBidStatus(); //이부분 안되요
			//부품공고상태(공고진행중>공고마감)으로 바꾸는 메서드 실행
			sellService.updateComponentAcStatus();
			//발전소공고상태(공고진행중 > 공고마감)으로 바꾸는 메서드 실행
			sellService.updateAcStatus();
			//입찰상태(입찰성공 > 입찰종료,계약중,계약대기  입찰대기>입찰실패)으로 바꾸는 메서드 실행
			//입찰대기>입찰실패
			bidListService.updateBidListsatus();
			//입찰성공 > 입찰종료,계약중,계약대기
			bidListService.updateBidListsatus3();
			
			//공고상태(공고마감 > 공고취소 or 거래진행중)으로 바꾸는 메서드 실행
			bidListService.updateBidMemberStatus();
			//공고가 거래진행중으로 바뀌었을때 입찰자들중 1순위를 낙찰자 테이블에 입력
			bidListService.addTradePriority();
			//거래 진행중 1순위가 취소하여 2순위와 거래해야하는 상태, 다음순위가 없어 거래실패
			scheduledService.updatePriority();
			//1순위 낙찰자가 계약마감일에 계약중인 상태인 경우 거래대금테이블에 입력
			scheduledService.updatePayIn();
			//1순위의 거래상태가 대금 미납인 경우 상태변경(1순위의 입찰자테이블, 낙찰자우선순위테이블 상태변경 및 2순위 낙찰자우선테이블 삽입)
			scheduledService.updatePaymentStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	  //테스트
	  @Scheduled(cron = "0/5  *  *  *  *  *") 
	  public void updateBidListStatustest() throws IOException, ParseException, ClassNotFoundException, SQLException {
		  try { 
				//bidListService.updateBidListsatus3();
			  	//공고마감일에 입찰자 수 0인 경우 거래실패로 상태변경
				//scheduledService.updateBidStatus();
				//부품공고상태(공고진행중>공고마감)으로 바꾸는 메서드 실행
				//sellService.updateComponentAcStatus();
				//발전소공고상태(공고진행중 > 공고마감)으로 바꾸는 메서드 실행
				//sellService.updateAcStatus();
				//입찰상태(입찰성공 > 입찰종료,계약중,계약대기  입찰대기>입찰실패)으로 바꾸는 메서드 실행
				//입찰대기>입찰실패
				//bidListService.updateBidListsatus();
		  } catch (Exception e) { 
			  e.printStackTrace(); 
		  } 
	  }
	

	//예약한 시간에 정책 적용 상태 바꾸는 메서드
	@Scheduled(cron = "30  00  0  *  *  *") 
	public void updateDepositStatus() throws IOException, ParseException, ClassNotFoundException, SQLException {
		try {
			//예치금 
			policyService.updateDeposit();
			//거래기간
			policyService.updateTrade();
			//수수료정책
			policyService.updateCommission();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
