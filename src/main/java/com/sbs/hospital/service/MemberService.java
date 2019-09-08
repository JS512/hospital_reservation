    
package com.sbs.hospital.service;

import java.util.List;
import java.util.Map;

import com.sbs.hospital.dto.Dept;
import com.sbs.hospital.dto.Member;
import com.sbs.hospital.dto.Staff;

public interface MemberService {
	public Member getOne(long id);

	public Map<String, Object> login(Map<String, Object> param);

	public void add(Map<String, Object> param);

	public Map<String, Object> doubleCheck(Map<String, Object> param);

	public Map<String, Object> updateAuthStatus(Map<String, Object> param);

	public Map<String, Object> updateDelStatus(Map<String, Object> param);

	public Map<String, Object> update(Map<String, Object> param);

	public Map<String, Object> updatepassword(Map<String, Object> param);

	public Map<String, Object> searchPw(Map<String, Object> param);

	public Map<String, Object> searchId(Map<String, Object> param);

	public String getMemberRole(long loginedMemberId);
	
	public List<Member> getAllMembers(Map<String, Object> param);

	public List<Dept> getAllDepts();

	public Member getOneMemberWithStaffDept(Map<String, Object> param);

	public Map<String, Object> updateMemberType(Map<String, Object> param);

	public Staff getMemberStaff(int staffId);
		
}