package com.cafe24.kangk0269.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.kangk0269.interceptor.Commoninterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private final Commoninterceptor commoninterceptor;
	
	public WebConfig(Commoninterceptor commoninterceptor) {
		this.commoninterceptor = commoninterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commoninterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/css/*");
		
	}
}
