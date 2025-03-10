package com.sbs.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	@Autowired
	@Qualifier("needToLoginInterceptor")
	HandlerInterceptor needToLoginInterceptor;

	@Autowired
	@Qualifier("needToLogoutInterceptor")
	HandlerInterceptor needToLogoutInterceptor;
	
	@Autowired
	@Qualifier("adminInterceptor")
	HandlerInterceptor adminInterceptor;
	
	@Autowired
	@Qualifier("needPermission1")
	HandlerInterceptor needPermission1;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/resource/**");	

		registry.addInterceptor(needToLoginInterceptor).addPathPatterns("/**").excludePathPatterns("/")
				.excludePathPatterns("/member/login").excludePathPatterns("/member/doLogin")
				.excludePathPatterns("/member/join").excludePathPatterns("/member/doJoin")
				.excludePathPatterns("/member/findInfo").excludePathPatterns("/member/confirm")
				.excludePathPatterns("/member/doSearchId").excludePathPatterns("/member/doSearchPw")
				.excludePathPatterns("/article/list").excludePathPatterns("/article/detail")
				.excludePathPatterns("/article/getReplies").excludePathPatterns("/resource/**");
		
		registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
		
		registry.addInterceptor(needPermission1).addPathPatterns("/schedule/staffSchedule", "/schedule/addEmptySchedule")
		.addPathPatterns("/schedule/deleteEmptySchedule");

		registry.addInterceptor(needToLogoutInterceptor).addPathPatterns("/member/login")
				.addPathPatterns("/member/doLogin").addPathPatterns("/member/join").addPathPatterns("/member/doJoin");
	}

}