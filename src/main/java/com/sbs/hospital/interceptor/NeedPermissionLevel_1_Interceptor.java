package com.sbs.hospital.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.hospital.dto.Member;

@Component("needPermission1")
public class NeedPermissionLevel_1_Interceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginedMemberId") != null) {
			Member member = (Member)request.getAttribute("loginedMember");
			if(member.getPermissionLevel() < 1) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().write("<script>");
				response.getWriter().write("alert('접근 권한이 없습니다.');");
				response.getWriter().write("history.back();");
				response.getWriter().write("</script>");
				return false;
			}
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
}
