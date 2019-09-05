package com.sbs.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Dept {
	private int id;
	private String regDate;
	private String name;
	private String centerId;
}
