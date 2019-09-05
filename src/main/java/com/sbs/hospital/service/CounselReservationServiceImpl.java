package com.sbs.hospital.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.hospital.dao.CounselReservationDao;
import com.sbs.hospital.dto.CounselReservation;
import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Staff;

@Service
public class CounselReservationServiceImpl implements CounselReservationService {
	@Autowired
	CounselReservationDao crd;

	public CounselReservation getOneReservation(long loginedMemberId) {
		return crd.getOneReservation(loginedMemberId);
	}

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
}
