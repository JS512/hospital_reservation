package com.sbs.hospital.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.hospital.dto.CounselReservation;
import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Staff;
import com.sbs.hospital.dto.StaffSchedule;

@Mapper
public interface CounselReservationDao {
	public CounselReservation getOneReservation(Map<String, Object> param);

	public List<Dept> getDeptList();

	public List<Staff> getStaffList();

	public List<Map<String, Object>> getDoctorList();

	public List<StaffSchedule> getEmptyStaffSchedule();

	public void addCounselReservation(Map<String, Object> param);

	public void updateStaffSchedule(Map<String, Object> param);

	public StaffSchedule getOneStaffSchedule(Map<String, Object> param);

	public List<CounselReservation> getReservations(long loginedMemberId);

	public void cancelReservation(Map<String, Object> param);

	
}
