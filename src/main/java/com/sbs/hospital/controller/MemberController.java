package com.sbs.hospital.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.hospital.dto.Member;
import com.sbs.hospital.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {

	@Autowired
	MemberService memberService;

	@RequestMapping("member/login")
	public String login() {
		return "member/login";
	}

	@RequestMapping("member/doLogin")
	public String doLogin(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		Map<String, Object> rs = memberService.login(param);

		String resultCode = (String) rs.get("resultCode");
		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/home/main";
		}

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
			session.setAttribute("loginedMemberId", rs.get("loginedMemberId"));
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";
	}

	@RequestMapping("member/doLogout")
	public String doLogout(HttpSession session, Model model) {
		session.removeAttribute("loginedMemberId");
		model.addAttribute("alertMsg", "로그아웃 되었습니다.");
		model.addAttribute("redirectUrl", "/member/login");
		return "common/redirect";
	}

	@RequestMapping("member/join")
	public String join() {
		return "member/join";
	}

	@RequestMapping("member/doJoin")
	public String showDoJoin(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		Map<String, Object> rs = memberService.doubleCheck(param);

		String resultCode = (String) rs.get("resultCode");

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/member/login";
		}

		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";
	}

	@RequestMapping("member/confirm")
	public String confirm(@RequestParam Map<String, Object> param, Model model) {
		Map<String, Object> rs = memberService.updateAuthStatus(param);
		String msg = (String) rs.get("msg");
		String resultCode = (String) rs.get("resultCode");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = "/member/login";
		model.addAttribute("redirectUrl", redirectUrl);

		return "common/redirect";
	}

	@RequestMapping("member/myPage")
	public String myPage(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/myPage";
	}

	@RequestMapping("member/modify")
	public String modify(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/modify";
	}

	@RequestMapping("/member/doModify")
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

	@RequestMapping("member/modifypassword")
	public String modifypassword(Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		Member member = memberService.getOne(loginedMemberId);
		model.addAttribute("member", member);
		return "member/modifypassword";
	}

	@RequestMapping("/member/doModifyPassword")
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

	@RequestMapping("member/doSecession")
	public String secession(@RequestParam Map<String, Object> param, Model model, HttpSession session) {
		long loginedMemberId = (long) session.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);
		Map<String, Object> rs = memberService.updateDelStatus(param);
		session.removeAttribute("loginedMemberId");

		String msg = (String) rs.get("msg");
		String resultCode = (String) rs.get("resultCode");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = "/member/login";
		model.addAttribute("redirectUrl", redirectUrl);

		return "common/redirect";
	}

	@RequestMapping("member/findInfo")
	public String findInfo() {
		return "member/findInfo";
	}

	@RequestMapping("member/doSearchId")
	public String doSearchId(@RequestParam Map<String, Object> param, HttpSession session, Model model) {
		Map<String, Object> rs = memberService.searchId(param);

		String resultCode = (String) rs.get("resultCode");

		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/member/login";
		}

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
			session.setAttribute("loginedMemberId", rs.get("loginedMemberId"));
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";
	}

	@RequestMapping("member/doSearchPw")
	public String doSearchPw(@RequestParam Map<String, Object> param, HttpSession session, Model model) {
		Map<String, Object> rs = memberService.searchPw(param);

		String resultCode = (String) rs.get("resultCode");

		String msg = (String) rs.get("msg");

		model.addAttribute("alertMsg", msg);

		String redirectUrl = (String) param.get("redirectUrl");

		if (redirectUrl == null || redirectUrl.length() == 0) {
			redirectUrl = "/member/login";
		}

		if (resultCode.startsWith("S-")) {
			model.addAttribute("redirectUrl", redirectUrl);
			session.setAttribute("loginedMemberId", rs.get("loginedMemberId"));
		} else {
			model.addAttribute("historyBack", true);
		}

		return "common/redirect";
	}

}