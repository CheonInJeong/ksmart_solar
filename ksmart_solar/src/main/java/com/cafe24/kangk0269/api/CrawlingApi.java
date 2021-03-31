package com.cafe24.kangk0269.api;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cafe24.kangk0269.dto.PlantKpxDTO;

import java.util.Iterator;
import org.jsoup.nodes.Element;


public class CrawlingApi {
	
	/*
	 * public void crawLingKpxData() throws IOException { String URL = ""; Document
	 * doc = Jsoup.connect(URL).get(); Elements elem = doc.select("");
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	
    public PlantKpxDTO crawLingKpxData() {
    	//크롤링할 url지정
    	String url = "https://www.kpx.or.kr/";
    	Document doc = null; 
    	
    	//Document에는 페이지의 전체 소스가 저장된다
	    try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
	    
        //select를 이용하여 원하는 태그를 선택한다. select는 원하는 값을 가져오기 위한 중요한 기능이다.
        //                               ==>원하는 값들이 들어있는 덩어리를 가져온다
        Elements element = doc.select("div.y_forecast_elec"); 
        Elements element2 = doc.select("div.m_today_rec"); 
      
        System.out.println(element2);
        
        //Iterator을 사용하여 하나씩 값 가져오기
        //덩어리안에서 필요한부분만 선택하여 가져올 수 있다.
        Iterator<Element> ie1 = element.select("tbody td").iterator();
        Iterator<Element> ie2 = element2.select("tbody td").iterator();
 
        PlantKpxDTO pk = new PlantKpxDTO();
        int count1 = 1;
        int count2 = 1;
        System.out.println("============================================================");
        while (ie1.hasNext()) {
        	String smpValue = "";
        	if(count1 == 1) {
        		//SMP 육지 날짜
        		smpValue = ie1.next().text();
        		int idx = smpValue.indexOf("(");
        		smpValue = smpValue.substring(0, idx);
        		if(smpValue.contains(".")) {
        			smpValue = smpValue.replace(".", "-");
        		}
        		pk.setPlKpxSmpShoreDate(smpValue);
        	}else if (count1 == 2) {
        		//SMP 육지 최고가
        		smpValue = ie1.next().text();
        		if(smpValue.contains("원")) {
        			int idx = smpValue.indexOf("원"); 
        			smpValue = smpValue.substring(0, idx);
        		}
        		pk.setPlKpxSmpShoreMax(smpValue);
        	}else if (count1 == 3) {
        		//SMP 육지 최저가
        		smpValue = ie1.next().text();
        		if(smpValue.contains("원")) {
        			int idx = smpValue.indexOf("원"); 
        			smpValue = smpValue.substring(0, idx);
        		}
        		pk.setPlKpxSmpShoreMin(smpValue);
        	}else if (count1 == 4) {
        		//SMP 육지 평균가
        		smpValue = ie1.next().text();
        		if(smpValue.contains("원")) {
        			int idx = smpValue.indexOf("원"); 
        			smpValue = smpValue.substring(0, idx);
        		}
        		pk.setPlKpxSmpShoreAvg(smpValue);
        	}else if (count1 == 5) {
        		//SMP 제주 날짜
        		smpValue = ie1.next().text();
        		int idx = smpValue.indexOf("(");
        		smpValue = smpValue.substring(0, idx);
        		if(smpValue.contains(".")) {
        			smpValue = smpValue.replace(".", "-");
        		}
        		pk.setPlKpxSmpJejuDate(smpValue);
        	}else if (count1 == 6) {
        		//SMP 제주 최고가
        		smpValue = ie1.next().text();
        		if(smpValue.contains("원")) {
        			int idx = smpValue.indexOf("원"); 
        			smpValue = smpValue.substring(0, idx);
        		}
        		pk.setPlKpxSmpJejuMax(smpValue);
        	}else if (count1 == 7) {
        		//SMP 제주 최저가
        		smpValue = ie1.next().text();
        		if(smpValue.contains("원")) {
        			int idx = smpValue.indexOf("원"); 
        			smpValue = smpValue.substring(0, idx);
        		}
        		pk.setPlKpxSmpJejuMin(smpValue);
        	}else if (count1 == 8) {
        		//SMP 제주 평균가
        		smpValue = ie1.next().text();
        		if(smpValue.contains("원")) {
        			int idx = smpValue.indexOf("원"); 
        			smpValue = smpValue.substring(0, idx);
        		}
        		pk.setPlKpxSmpJejuAvg(smpValue);
        	}
        	System.out.println(smpValue);
            count1++;
        }
        System.out.println("============================================================");
        System.out.println("============================================================");
        while (ie2.hasNext()) {
        	String smpValue = "";
        	if(count2 == 1) {
        		
        	}else if(count2 == 2) {
        		
        	}else if(count2 == 3) {
        		
        	}else if(count2 == 4) {
        		
        	}else if(count2 == 5) {
        		
        	}else if(count2 == 6) {
        		
        	}
        	System.out.println(smpValue);
        	count2++;
        }
        System.out.println("============================================================");
	 
        return pk; 
    }

	
}
