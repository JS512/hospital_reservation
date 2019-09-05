package com.sbs.hospital.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("adminInterceptor")
public class AdminInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if(session.getAttribute("loginedMemberId") != null) {
			if(request.getAttribute("loginedMemberRole").equals("") || request.getAttribute("loginedMemberRole") == null) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().write("<script>");
				response.getWriter().write("alert('권한이 없습니다.');");
				response.getWriter().write("history.back();");
				response.getWriter().write("</script>");
				return false;
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
