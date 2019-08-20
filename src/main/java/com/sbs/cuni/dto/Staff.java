package com.sbs.cuni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Staff {
	private int id;
	private String regDate;
	private String name;
	private String StaffType;
	private int deptId;
}
