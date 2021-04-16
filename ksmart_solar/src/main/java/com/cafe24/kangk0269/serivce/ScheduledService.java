package com.cafe24.kangk0269.serivce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.kangk0269.common.FileUtils;
import com.cafe24.kangk0269.dao.FileMapper;
import com.cafe24.kangk0269.dao.ScheduledMapper;
import com.cafe24.kangk0269.dao.SellMapper;
import com.cafe24.kangk0269.dto.TradePriorityDTO;


@Service
public class ScheduledService {
	@Autowired
	private final ScheduledMapper scheduledMapper;

	
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
	
}
