package com.sbs.hospital.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Member;
import com.sbs.hospital.service.MemberService;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/admin_member")
	public String admin_member(Model model, HttpSession session) {		
		List<Member> list = memberService.getAllMembers(Maps.of("loginedMemberId", session.getAttribute("loginedMemberId")));
		model.addAttribute("list", list);
		return "admin/admin_member/memberList";
	}
	
	@RequestMapping("/modifyMemberType")
	public String modifyMemberType(@RequestParam Map<String, Object> param, Model model, HttpSession session) {		
		Member member = memberService.getOneMemberWithStaffDept(param);
		List<Dept> deptList = memberService.getAllDepts();	
		
		model.addAttribute("member", member);
		model.addAttribute("deptList", deptList);
		return "admin/admin_member/modifyMemberType";
	}
	
	@RequestMapping("/doModifyMemberType")	
	public String doModifyMemberType(@RequestParam Map<String, Object> param, Model model) {
		
		Map<String ,Object> rs = memberService.updateMemberType(param);	
		String resultCode = (String)rs.get("resultCode");
		model.addAttribute("alertMsg", rs.get("msg"));
		if(resultCode.startsWith("S-")) {			
			model.addAttribute("redirectUrl", "/admin/admin_member");
		}else {
			model.addAttribute("historyBack", true);
		}
		
		
		return "common/redirect";
	}	

	@RequestMapping("/myPage")
	public String myPage(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/myPage";
	}

	@RequestMapping("/modify")
	public String modify(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/modify";
	}

	@RequestMapping("/doModify")
	@ResponseBody
	public String doModify(Model model, @RequestParam Map<String, Object> param, HttpSession session,
			HttpServletRequest rq) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);
		
		Member member = (Member) rq.getAttribute("loginedMember");
		String loginPw = member.getLoginPw();
		
		String loginPwConfirm = (String)param.get("loginPwConfirm");
		
		if(loginPw.equals(loginPwConfirm)) {
			Map<String, Object> updateRs = memberService.update(param);

			StringBuilder sb = new StringBuilder();

			sb.append("<script>");

			String msg = (String) updateRs.get("msg");

			sb.append("alert('" + msg + "');");

			if (((String) updateRs.get("resultCode")).startsWith("S-")) {
				sb.append("location.replace('./myPage');");
			} 

			sb.append("</script>");

			return sb.toString();
		}
		else {
			String returnMsg = "<script>alert('비밀번호를 올바르게 입력해주세요!');</script>";
			returnMsg += "<script>location.replace('./modify?errorField=loginPwConfirm');</script>";
			return returnMsg;
		}
	}

	@RequestMapping("/modifypassword")
	public String modifypassword(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/modifypassword";
	}

	@RequestMapping("/doModifyPassword")
	@ResponseBody
	public String doModifyPassword(Model model, @RequestParam Map<String, Object> param, HttpSession session, HttpServletRequest rq) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);
		
		Member member = (Member) rq.getAttribute("loginedMember");
		
		String loginPw = member.getLoginPw();   
		
		String loginPwConfirm = (String)param.get("loginPw");
		
		if(loginPw.equals(loginPwConfirm)) {
			
			Map<String, Object> updateRs = memberService.updatepassword(param);

			StringBuilder sb = new StringBuilder();

			sb.append("<script>");

			String msg = (String) updateRs.get("msg");

			sb.append("alert('" + msg + "');");

			if (((String) updateRs.get("resultCode")).startsWith("S-")) {
				sb.append("location.replace('./myPage');");
			}

			sb.append("</script>");

			return sb.toString();
		}
		else {
			String returnMsg = "<script>alert('기존 비밀번호를 올바르게 입력해주세요!');</script>";
			returnMsg += "<script>location.replace('./modifypassword?errorField=loginPw');</script>";
			return returnMsg;
		}
	}
}
