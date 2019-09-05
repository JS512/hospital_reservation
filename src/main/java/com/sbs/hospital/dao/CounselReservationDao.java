package com.sbs.hospital.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.hospital.dto.CounselReservation;
import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Staff;

@Mapper
public interface CounselReservationDao {
	public CounselReservation getOneReservation(long loginedMemberId);

	public List<Dept> getDeptList();

	public List<Staff> getStaffList();

	public List<Map<String, Object>> getDoctorList();
}
