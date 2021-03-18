package com.cafe24.kangk0269.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		//파일 인코딩 UTF-8 로 설정
		commonsMultipartResolver.setDefaultEncoding("UTF-8");
		//업로드 되는 파일의 크기 제한. 바이트 단위 설정
		commonsMultipartResolver.setMaxUploadSizePerFile(10*1024*1024);
		return commonsMultipartResolver;
	}
}
