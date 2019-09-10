package com.sbs.hospital.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sbs.hospital.dto.StaffSchedule;

@Mapper
public interface StaffScheduleDao {

	public List<StaffSchedule> getStaffSchedule(int staffId);

	public void addEmptySchedule(Map<String, Object> param);

	public StaffSchedule getOneStaffSchedule(Map<String, Object> param);

	public void deleteEmptySchedule(Map<String, Object> param);

	public void cancelStaffSchedule(Map<String, Object> param);

}
