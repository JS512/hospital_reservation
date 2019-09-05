package com.sbs.hospital.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Center {
	private int id;
	private String regDate;
	private String name;
	private Map<String, Object> extra;
}
