package com.sbs.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StaffSchedule {
	private int id;
	private String regDate;
	private int deptId;
	private int staffId;
	private String startTime;
	private String endTime;
	private String scheduleType;
	private String relType;
	private int relId;
}