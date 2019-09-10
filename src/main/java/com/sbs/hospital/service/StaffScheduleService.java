package com.sbs.hospital.service;

import java.util.List;
import java.util.Map;

import com.sbs.hospital.dto.StaffSchedule;

public interface StaffScheduleService {

	public List<StaffSchedule> getStaffSchedule(int staffId);

	public Map<String, Object> addEmptySchedule(Map<String, Object> param);

	public Map<String, Object> deleteEmptySchedule(Map<String, Object> param);

}
