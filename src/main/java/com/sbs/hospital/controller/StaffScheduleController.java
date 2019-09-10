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

import com.sbs.hospital.dto.Member;
import com.sbs.hospital.dto.Staff;
import com.sbs.hospital.dto.StaffSchedule;
import com.sbs.hospital.service.StaffScheduleService;

@Controller
@RequestMapping("/schedule")
public class StaffScheduleController {
	@Autowired
	private StaffScheduleService staffScheduleService;
	
	@RequestMapping("/staffSchedule")
	public String staffSchedule(Model model, HttpServletRequest request) {
		Member member = (Member)request.getAttribute("loginedMember");		
		List<StaffSchedule> scheduleList = staffScheduleService.getStaffSchedule(member.getStaffId());
		
		model.addAttribute("scheduleList", scheduleList);
		
		return "reservation/staffSchedule";
	}
	
	@RequestMapping("/addEmptySchedule")
	@ResponseBody
	public Map<String, Object> addEmptySchedule(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request) {
		Staff staff = (Staff)request.getAttribute("loginedMemberStaff");
		param.put("loginedStaff", staff);
		Map<String, Object> rs = staffScheduleService.addEmptySchedule(param);
		
		boolean success = false;
		String resultCode = (String)rs.get("resultCode");
		if(resultCode.startsWith("S-")) {
			success = true;
		}
		return Maps.of("msg", rs.get("msg"), "success", success, "id", param.get("id"));
	}
	
	@RequestMapping("/deleteEmptySchedule")
	@ResponseBody
	public Map<String, Object> deleteEmptySchedule(@RequestParam Map<String, Object> param, HttpServletRequest request){
		Staff staff = (Staff)request.getAttribute("loginedMemberStaff");
		param.put("loginedStaff", staff);
		Map<String, Object> rs = staffScheduleService.deleteEmptySchedule(param);
		
		boolean success = false;
		String resultCode = (String)rs.get("resultCode");
		if(resultCode.startsWith("S-")) {
			success = true;
		}
		return Maps.of("msg", rs.get("msg"), "success", success);
	}
}
