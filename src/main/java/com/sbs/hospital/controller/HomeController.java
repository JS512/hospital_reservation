package com.sbs.hospital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import groovy.util.logging.Slf4j;
import jline.internal.Log;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping("/")
	public String showMain(HttpServletRequest request) {
		
		String role = (String)request.getAttribute("loginedMemberRole");
		
		if(role.equalsIgnoreCase("admin")) {
			Log.info(role);
			return "admin/home/main";
		}
		return "home/main";
	}

	@RequestMapping("/home/main")
	public String showMain2(HttpServletRequest request) {
		String role = (String)request.getAttribute("loginedMemberRole");
		
		if(role.equalsIgnoreCase("admin")) {
			Log.info(role);
			return "admin/home/main";
		}
		return "home/main";
	}

}
