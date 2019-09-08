package com.sbs.hospital.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.hospital.dto.Member;
import com.sbs.hospital.dto.Staff;
import com.sbs.hospital.service.MemberService;


@Component("beforeActionInterceptor")
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	MemberService memberService;

	@Value("${custom.logoText}")
	String logoText;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		request.setAttribute("logoText", logoText);
		request.setAttribute("logoHtml", logoText);

		if (session.getAttribute("loginedMemberId") != null) {
			long loginedMemberId = (long) session.getAttribute("loginedMemberId");

			Member member = memberService.getOne(loginedMemberId);
			Staff staff = memberService.getMemberStaff(member.getStaffId());

			request.setAttribute("isLogined", true);
			request.setAttribute("loginedMember", member);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMemberLoginId", member.getLoginId());
			request.setAttribute("loginedMemberStaff", staff);
			
			String role = memberService.getMemberRole(loginedMemberId);
			if(role == null) {
				request.setAttribute("loginedMemberRole", "");
			}else {
				request.setAttribute("loginedMemberRole", role);
			}
		} else {
			request.setAttribute("isLogined", false);
			request.setAttribute("loginedMember", null);
			request.setAttribute("loginedMemberId", 0L);
			request.setAttribute("loginedMemberLoginId", "");
			request.setAttribute("loginedMemberRole", "");
			request.setAttribute("loginedMemberStaff", null);
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}