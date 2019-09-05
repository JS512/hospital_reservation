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
public class CounselReservation {
	private int id;
	private String regDate;
	private int clientMemberId;
	private int staffScheduleId;
	private String body;
	
	private Map<String, String> extra;
}