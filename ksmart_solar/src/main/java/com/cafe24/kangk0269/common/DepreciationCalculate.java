package com.cafe24.kangk0269.common;



public class DepreciationCalculate {
	
	
	//발전소 감가 관련 메서드
	public int calculPlDepPriceBased(int plDepPrice, int plDepServicelife) {
		
		double[] rateArray = {0.007936508,0.007539683,0.007142857,0.006746032,0.006349206,0.005952381,0.005555556,0.00515873,0.004761905,0.004365079,0.003968254,0.003571429,0.003174603,0.002777778,0.002380952,0.001984127,0.001587302,0.001190476,0.000793651,0.000396825};
		int pastMonth = plDepServicelife;
		double sumRate = 0;
		
		//사용 개월수만큼 비율을 더하여 총 합산 비율을 구하는 반복문
		if((pastMonth/12) > 0) {
			for(int i=0; i<pastMonth/12; i++) {
				sumRate += rateArray[i]*12;
			}
		}
		sumRate += (rateArray[(pastMonth/12)])*((pastMonth%12));
		System.out.println((1 - sumRate) + " <<< sumRate");
		
		int PlDepPriceBased = (int) (plDepPrice/(1 - sumRate));
		System.out.println("결과 값 : " + PlDepPriceBased);
		
		return PlDepPriceBased;
	}
	
	
	//bz_pl_code를 입력하면 수익분석 그래프 데이터를 리턴하는 메서드
	
	
	
}
