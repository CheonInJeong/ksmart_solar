package com.cafe24.kangk0269;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
//MultipartAutoConfiguration.class를 자동으로 구성하지 않도록 설정
@SpringBootApplication(exclude= {MultipartAutoConfiguration.class})
@EnableScheduling
public class KsmartSolarApplication {

	public static void main(String[] args) {
		SpringApplication.run(KsmartSolarApplication.class, args);
	}

}
