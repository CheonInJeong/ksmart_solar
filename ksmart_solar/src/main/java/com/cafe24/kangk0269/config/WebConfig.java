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
		
		//허용되는것만쓰는거....
		//localhost는 인터셉터를 거치지 않게
		//css파일은 인터셉터를 거치지 않게 
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/")
		.excludePathPatterns("/favicon.ico")
		.excludePathPatterns("/ajax/**")
		.excludePathPatterns("/css/**")
		.excludePathPatterns("/img/**")
		.excludePathPatterns("/lib/**")
		.excludePathPatterns("/signup")
		.excludePathPatterns("/login")
		.excludePathPatterns("/logout")
		.excludePathPatterns("/logoutKakao")
		.excludePathPatterns("/notice/noticeList")
		.excludePathPatterns("/notice/history")
		.excludePathPatterns("/notice/announcement")
		.excludePathPatterns("/help/notice")
		.excludePathPatterns("/help/getNotice");
	}
}

