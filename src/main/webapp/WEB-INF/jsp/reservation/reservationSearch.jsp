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

<script>
	function cancelReservation(btn){
		if(!confirm("선택한 예약을 삭제 하시겠습니까?")){
			return ;
		}

		$.get("/reservation/cancelReservation",
			{
				id : $(btn).attr("data-id")
			},
			function(data){
				alert(data.msg);
				if(data.success){
					$(btn).parent().parent().remove();
				}
			},
			"json"
		);
	}
</script>

<div class="article-detail table-common con">
	<table>
		<colgroup>
			<col width="100">
		</colgroup>
		<thead>
			<tr>
				<th>예약번호</th>
				<th>이름</th>
				<th>예약날짜</th>
				<th>진료과</th>			
				<th>전문담당의</th>				
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${crs }" var="cr">
				<tr>				
					<td><c:out value="${cr.id}" escapeXml="true" /></td>				
					<td><c:out value="${loginedMember.name}" /></td>				
					<td><c:out value="${fn:substring(cr.extra.startTime, 0, 16)}" /></td>				
					<td><c:out value="${cr.extra.deptName}" escapeXml="true" /></td>				
					<td><c:out value="${cr.extra.staffName}" /></td>
					<td><button data-id="${cr.id }" type="button" onclick="cancelReservation(this);">예약 취소</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<%@ include file="../part/foot.jspf"%>