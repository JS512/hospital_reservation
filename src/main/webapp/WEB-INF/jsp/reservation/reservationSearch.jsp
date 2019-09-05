<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.hospital.dto.Member"%>
<%@ page import="com.sbs.hospital.dto.Staff"%>
<%@ page import="com.sbs.hospital.dto.CounselReservation"%>
<%@ page import="com.sbs.hospital.dto.Dept"%>
<%@ page import="com.sbs.hospital.dto.StaffSchedule"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="예약조회" />
<%@ include file="../part/head.jspf"%>

<div class="article-detail table-common con">
	<table>
		<colgroup>
			<col width="100">
		</colgroup>
		<tbody>
			<tr>
				<th>예약번호</th>
				<td><c:out value="${cr.id}" escapeXml="true" /></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><c:out value="${loginedMember.name}" /></td>
			</tr>
			<tr>
				<th>예약날짜</th>
				
				<td><c:out value="${fn:substring(cr.extra.startTime, 0, 16)}" /></td>
			</tr>
			<tr>
				<th>진료과</th>
				<td><c:out value="${cr.extra.deptName}" escapeXml="true" /></td>
			</tr>
			<tr>
				<th>전문담당의</th>
				
				<td><c:out value="${cr.extra.staffName}" /></td>
			</tr>
		</tbody>
	</table>
</div>


<%@ include file="../part/foot.jspf"%>