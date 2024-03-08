package com.gdj.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gdj.boot.interceptor.LoginInterceptor;
import com.gdj.boot.interceptor.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private TestInterceptor testInterceptor;
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		//어떤 URL이 왔을 때 어떤 Interceptor 를 거치게 할것이냐
//		registry.addInterceptor(testInterceptor)
//				.addPathPatterns("/notice/**")
//				.excludePathPatterns("/notice/add");
//		
//		registry.addInterceptor(loginInterceptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns("/admin");
	}
	
	
	
}
