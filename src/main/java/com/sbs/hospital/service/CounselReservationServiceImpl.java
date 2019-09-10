package com.sbs.hospital.service;

import java.util.List;
import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.hospital.dao.CounselReservationDao;
import com.sbs.hospital.dao.StaffScheduleDao;
import com.sbs.hospital.dto.CounselReservation;
import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Staff;
import com.sbs.hospital.dto.StaffSchedule;

@Service
public class CounselReservationServiceImpl implements CounselReservationService {
	@Autowired
	private CounselReservationDao crd;
	@Autowired
	private StaffScheduleDao ssd;

//	public CounselReservation getOneReservation(long loginedMemberId) {
//		return crd.getOneReservation(loginedMemberId);
//	}

	@Override
	public List<Dept> getDeptList() {
		return crd.getDeptList();
	}

	@Override
	public List<Staff> getStaffList() {

		return crd.getStaffList();
	}

	@Override
	public List<Map<String, Object>> getDoctorList() {
		return crd.getDoctorList();
	}
	
	public List<StaffSchedule> getEmptyStaffSchedule(){
		return crd.getEmptyStaffSchedule();
	}
	
	public Map<String, Object> addCounselReservation(Map<String, Object> param){
		String msg = "";
		String resultCode = "S-1";
		try {
			StaffSchedule schedule = crd.getOneStaffSchedule(param);
			if(schedule != null) {
				if(schedule.getRelId() == 0) {
					crd.addCounselReservation(param);
					crd.updateStaffSchedule(param);
					
					msg = "예약 되었습니다.";
					resultCode = "S-1";
				}else {
					msg = "이미 예약 된 시간대 입니다.";
					resultCode = "F-1";
				}
			}else {
				msg = "존재하지 않거나 삭제된 시간대 입니다.";
				resultCode = "F-1";
			}
		}catch(Exception e) {
			e.printStackTrace();
			msg = "오류가 발생 되었습니다.";
			resultCode = "F-1";
		}
		
		return Maps.of("msg", msg, "resultCode", resultCode);
	}
	
	public List<CounselReservation> getReservations(long loginedMemberId){
		return crd.getReservations(loginedMemberId);
	}
	
	public Map<String, Object> cancelReservation(Map<String, Object> param){
		String msg = "";
		String resultCode = "S-1";
		try {
			CounselReservation reservation = crd.getOneReservation(param);
			if(reservation != null) {
				
				crd.cancelReservation(param);
				ssd.cancelStaffSchedule(param);
				
				msg = "취소 되었습니다.";
				resultCode = "S-1";	
				
			}else {
				msg = "존재하지 않거나 삭제된 예약 입니다.";
				resultCode = "F-1";
			}
		}catch(Exception e) {
			e.printStackTrace();
			msg = "오류가 발생 되었습니다.";
			resultCode = "F-1";
		}
		
		return Maps.of("msg", msg, "resultCode", resultCode); 
	}
}
