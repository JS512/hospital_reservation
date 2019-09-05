<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.hospital.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="회원 관리 페이지" />
<%@ include file="../part/head.jspf"%>

<div class="article-detail table-common con">
	
	<table>
		<tr>
			<th>이름</th>
			<th>아이디</th>
			<th>이메일</th>			
			<th>역할</th>			
			<th>소속</th>			
			<th>역할 지정</th>
		</tr>
		
		<c:forEach items="${list }" var="member">
			<tr>
				<td>${member.loginId}</td>
				<td>${member.name }</td>
				<td>${member.email }</td>
				<td>${member.extra.staffType }</td>
				<td>${member.extra.centerName} >> ${member.extra.deptName }</td>				
				<td><a href="/admin/modifyMemberType?id=${member.id }">역할 지정</a></td>				
			</tr>	
				
		</c:forEach>		
	</table>
</div>

<%@ include file="../part/foot.jspf"%>