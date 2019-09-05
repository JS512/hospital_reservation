<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.hospital.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="회원 수정 페이지" />
<%@ include file="../part/head.jspf"%>
<script>
	function modifyMemberType__checkForm(form){
		form.staffType.value = form.staffType.value.trim();
		var staffType = form.staffType.value;		

		if(!confirm("해당 회원의 정보를 수정하시겠습니까?")){			
			return ;
		}

		form.submit();
	}
</script>
<form onsubmit="modifyMemberType__checkForm(this); return false;" action="/admin/doModifyMemberType">
<input type="hidden" name="id" value="${param.id }">
	<div class="article-detail table-common con">
		<table>
			<tr>
				<th colspan="2">변경 회원 정보</th>
			</tr>
			
			<tr>
				<th>아이디</th>
				<td>${member.loginId}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${member.name }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${member.email }</td>			
			</tr>
			<tr>
				<th>역할</th>
				<td>					
					<input type="text" name="staffType" value="${member.extra.staffType }">
				</td>
			</tr>
			<tr>
				<th>소속</th>
				<td>
					<p><c:out value="${member.extra.centerName }"/> >> <c:out value="${member.extra.deptName }"/></p>	
					<select name="deptId">
						<option value="0">소속없음</option>
						<c:forEach items="${deptList }" var="dept">
							<c:if test="${member.extra.deptId == dept.id}">
								<option value="${dept.id }" selected>${dept.name }</option>	
							</c:if>
							<c:if test="${member.extra.deptId != dept.id}">
								<option value="${dept.id }" >${dept.name }</option>	
							</c:if>								
						</c:forEach>						
					</select>
				</td>
			</tr>
		</table>
		<button>수정</button>
	</div>
	
</form>
<%@ include file="../part/foot.jspf"%>