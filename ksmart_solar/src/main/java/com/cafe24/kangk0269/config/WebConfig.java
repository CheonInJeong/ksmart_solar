package com.cafe24.kangk0269.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.kangk0269.interceptor.Commoninterceptor;
import com.cafe24.kangk0269.interceptor.LoginInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	private final Commoninterceptor commonInterceptor;
	private final LoginInterceptor loginInterceptor;
	
	public WebConfig(Commoninterceptor commonInterceptor, LoginInterceptor loginInterceptor) {
		this.commonInterceptor = commonInterceptor;
		this.loginInterceptor = loginInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/css/*");
		
	
		
		
	}
}