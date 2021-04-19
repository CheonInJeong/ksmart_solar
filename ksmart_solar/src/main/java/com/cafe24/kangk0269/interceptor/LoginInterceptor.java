package com.cafe24.kangk0269.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class LoginInterceptor implements HandlerInterceptor{
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String sessionId    = (String)session.getAttribute("SID");
		String sessionLevel = (String)session.getAttribute("SLEVEL");
		String sessionName  = (String)session.getAttribute("SNAME");
		@SuppressWarnings("unchecked")
		List<String> sessionURI   = (List<String>)session.getAttribute("SURI");
		//요청받은 uri
		String requestUri   = request.getRequestURI();

		log.info("LoginInterceptor****************************START");
		log.info("sessionId				:::: {}",sessionId);
		log.info("sessionLevel			:::: {}",sessionLevel);
		log.info("sessionName			:::: {}",sessionName);
		log.info("sessionURI			:::: {}",sessionURI);


		//System.out.println(sessionURI);
		

		if(sessionId == null ||sessionLevel == null || sessionURI==null) {
			if(requestUri.indexOf("/member/memberSignUp") > -1) {
				return true;
			}
			response.sendRedirect("/login");
			return false;
		}else {

			if(!sessionURI.contains(requestUri)) {
				response.sendRedirect("/");
			
			
				return false;
			}
		
			
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("LoginInterceptor****************************END");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
