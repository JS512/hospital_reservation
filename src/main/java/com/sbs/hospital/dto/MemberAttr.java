package com.sbs.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAttr {
	private int id;
	private String regDate;
	private int memberId;
	private String attrName;
	private Object attrVal;
}
