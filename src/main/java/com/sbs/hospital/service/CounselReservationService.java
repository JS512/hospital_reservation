package com.sbs.hospital.service;

import java.util.List;
import java.util.Map;

import com.sbs.hospital.dto.CounselReservation;
import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Staff;

public interface CounselReservationService {
	public CounselReservation getOneReservation(long loginedMemberId);
	public List<Dept> getDeptList();
	public List<Staff> getStaffList();
	public List<Map<String, Object>> getDoctorList();
}
