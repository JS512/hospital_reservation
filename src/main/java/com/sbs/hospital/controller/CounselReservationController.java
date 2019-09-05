package com.sbs.hospital.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sbs.hospital.dto.CounselReservation;
import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Member;
import com.sbs.hospital.dto.Staff;
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

		return "reservation/counselReservation";
	}

	@RequestMapping("reservation/reservationSearch")
	public String reservationSerach(@RequestParam(value = "id", defaultValue = "0") long id, Model model,
			HttpServletRequest req) {
		Member loginedMember = (Member) req.getAttribute("loginedMember");
		long loginedMemberId = (long) req.getAttribute("loginedMemberId");
		CounselReservation counselReservation = crs.getOneReservation(loginedMemberId);

		model.addAttribute("cr", counselReservation);

		return "reservation/reservationSearch";
	}
}
