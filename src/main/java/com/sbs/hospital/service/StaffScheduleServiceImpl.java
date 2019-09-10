package com.sbs.hospital.service;

import java.util.List;
import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.hospital.dao.StaffScheduleDao;
import com.sbs.hospital.dto.Staff;
import com.sbs.hospital.dto.StaffSchedule;

@Service
public class StaffScheduleServiceImpl implements StaffScheduleService{
	@Autowired
	private StaffScheduleDao staffScheduleDao;
	
	public List<StaffSchedule> getStaffSchedule(int staffId){		
		return staffScheduleDao.getStaffSchedule(staffId);
	}
	
	public Map<String, Object> addEmptySchedule(Map<String, Object> param){
		String msg = "";
		String resultCode = "";
		try {
			staffScheduleDao.addEmptySchedule(param);
			resultCode = "S-1";
		}catch(Exception e) {
			msg = "오류 발생";
			resultCode = "F-1";
			e.printStackTrace();			
		}
		
		return Maps.of("msg",msg, "resultCode", resultCode);
	}
	
	public Map<String, Object> deleteEmptySchedule(Map<String, Object> param){
		String msg = "";
		String resultCode = "";
		try {
			StaffSchedule schedule = staffScheduleDao.getOneStaffSchedule(param);
			Staff staff = (Staff)param.get("loginedStaff");
			if(schedule == null) {
				msg = "존재 하지 않는 스케줄";
				resultCode = "F-1";
			}else {
				if(staff.getId() != schedule.getStaffId()) {
					msg = "삭제 권한 없습니다.";
					resultCode = "F-1";
				}else if(schedule.getRelId() != 0) {
					msg = "이미 예약되어 있는 스케줄 입니다.";
					resultCode = "F-1";
				}
				else{
					staffScheduleDao.deleteEmptySchedule(param);
					msg = "삭제 성공";
					resultCode = "S-1";
				}
			}

		}catch(Exception e) {
			msg = "오류 발생";
			resultCode = "F-1";
			e.printStackTrace();			
		}
		
		return Maps.of("msg",msg, "resultCode", resultCode);
	}
}
