package com.sbs.hospital.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.hospital.dto.CounselReservation;
import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Member;
import com.sbs.hospital.dto.Staff;
import com.sbs.hospital.dto.StaffSchedule;
import com.sbs.hospital.service.CounselReservationService;
import com.sbs.hospital.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CounselReservationController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private CounselReservationService crs;

	@RequestMapping("/reservation/counselReservation")
	public String Reservation(Model model) {
		List<Map<String, Object>> doctorList = crs.getDoctorList();
		
		List<Dept> d = crs.getDeptList();	

		model.addAttribute("deptList", d);
		List<Staff> s = crs.getStaffList();
		model.addAttribute("staffList", s);
		List<StaffSchedule> ss = crs.getEmptyStaffSchedule();
		model.addAttribute("emptyScheduleList", ss);

		return "reservation/counselReservation";
	}

	@RequestMapping("/reservation/reservationSearch")
	public String reservationSearch(@RequestParam(value = "id", defaultValue = "0") long id, Model model,
			HttpServletRequest req) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		long loginedMemberId = (long) req.getAttribute("loginedMemberId");
		List<CounselReservation> counselReservations = crs.getReservations(loginedMemberId);

		model.addAttribute("crs", counselReservations);

		return "reservation/reservationSearch";
	}
	
	@RequestMapping("/reservation/doReservation")
	public String doReservation(@RequestParam Map<String, Object> param, HttpServletRequest request, Model model) {
		long loginedMemberId = (long) request.getAttribute("loginedMemberId");
		param.put("loginedMemberId", loginedMemberId);
		Map<String, Object> rs = crs.addCounselReservation(param);		
		
		String resultCode = (String)rs.get("resultCode");
		if(resultCode.startsWith("S-")) {
			String redirectUrl = "/reservation/reservationSearch";
			model.addAttribute("redirectUrl", redirectUrl);
		}else {
			model.addAttribute("historyBack", true);
		}
		model.addAttribute("alertMsg", rs.get("msg"));
		return "common/redirect";
	}
	
	@RequestMapping("/reservation/cancelReservation")
	@ResponseBody
	public Map<String, Object> cancelReservation(@RequestParam Map<String, Object> param, HttpServletRequest request){		
		Map<String, Object> rs = crs.cancelReservation(param);
		
		boolean success = false;
		String resultCode = (String)rs.get("resultCode");
		if(resultCode.startsWith("S-")) {
			success = true;
		}
		return Maps.of("msg", rs.get("msg"), "success", success);		
	}
}
