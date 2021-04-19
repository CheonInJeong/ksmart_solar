package com.cafe24.kangk0269.serivce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.BidComponentMapper;
import com.cafe24.kangk0269.dao.BidPlantMapper;
import com.cafe24.kangk0269.dao.FileMapper;
import com.cafe24.kangk0269.dao.PolicyMapper;
import com.cafe24.kangk0269.dao.ScheduledMapper;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dao.TradeMapper;
import com.cafe24.kangk0269.dto.BidComponentDTO;
import com.cafe24.kangk0269.dto.BidListDTO;
import com.cafe24.kangk0269.dto.BidPlantDTO;
import com.cafe24.kangk0269.dto.TradePriorityDTO;


@Service
public class ScheduledService {
	@Autowired
	private final ScheduledMapper scheduledMapper;
	@Autowired
	private BidComponentMapper bidComponentMapper;
	@Autowired
	private BidPlantMapper bidPlantMapper;
	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private PolicyMapper policyMapper;

	
	public ScheduledService(ScheduledMapper scheduledMapper) {
		this.scheduledMapper = scheduledMapper;
	}
	
	public void updatePayIn() throws ParseException {
		
		List<TradePriorityDTO> tradePriority = scheduledMapper.getPriority();
		for(int i= 0; i<tradePriority.size();i++) {
			String lastDate = tradePriority.get(i).getTrPrConclusionDate2();
			int status = tradePriority.get(i).getTrTypeCode();
			
			if(lastDate!=null) {
				lastDate = lastDate.substring(0,10);
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String today = format.format(date);
				
				
				
				if(lastDate.equals(today)&&status==6) {
					System.out.println("실행할 코드 작성");
					//내일날짜구하기
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(format.parse(today));
					calendar.add(Calendar.DATE, 1);
					String tomorrow = format.format(calendar.getTime());
					scheduledMapper.addPayIn(tradePriority.get(i),tomorrow);
				
				}
			}
	
		}
		
	}
	public void updatePriority() {
		List<TradePriorityDTO> priorityFail = scheduledMapper.getPriorityFail();
		int tradePerioddate = policyMapper.getTradePeriod();
		for(int i=0; i<priorityFail.size(); i++) {
			String announcedCode = priorityFail.get(i).getAnnouncedCode();
			int rank = priorityFail.get(i).getTrPrRank();
			rank++;
			String bidListDTO = scheduledMapper.getBidListNextRank(announcedCode, rank);
			System.out.println(bidListDTO);
			if(bidListDTO!=null) {
				if(bidListDTO.equals("1")) {
					System.out.println("발전소");
					BidPlantDTO BidPlantTradeList = bidPlantMapper.getBidPlantTradeNext(announcedCode, rank);
					Map<String,Object> List = new HashMap<String, Object>();
					List.put("tradePerioddate", tradePerioddate);
					List.put("BidPlantTradeList", BidPlantTradeList);
					tradeMapper.addTradePriority(List);
				}
				if(bidListDTO.equals("2")) {
					System.out.println("부품");
					BidComponentDTO BidComTradeList = bidComponentMapper.getBidComTradeNext(announcedCode, rank);
					Map<String,Object> List = new HashMap<String, Object>();
					List.put("tradePerioddate", tradePerioddate);
					List.put("BidComTradeList", BidComTradeList);
					tradeMapper.addTradePriority(List);
				}
			}
		}
	}
}
