<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="비밀번호 수정" />
<%@ include file="../part/head.jspf"%>

<script>
	function modifyFormSubmited(form) {
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('기존 비밀번호를 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		
		form.newloginPw.value = form.newloginPw.value.trim();
		if (form.newloginPw.value.length == 0) {
			alert('변경할 비밀번호를 입력해주세요.');
			form.newloginPw.focus();
			return;
		}
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPwConfirm.value.length == 0) {
			alert('기존 비밀번호를 입력해주세요.');
			form.loginPwConfirm.focus();
			return;
		}
		if(form.newloginPw.value != form.loginPwConfirm.value ) {
			alert('변경할 비밀번호가 같지 않습니다.');
			form.loginPwConfirm.value = "";
			form.loginPwConfirm.focus();
			return false;
			}
		form.submit();
	}
</script>

<c:if test="${param.errorField != null}">
<script>
	$(function() {
		$('input[name="${param.errorField}"]').focus();
		$('input[name="${param.errorField}"]').addClass('error-field');
		$('input[name="${param.errorField}"]').keyup(function() {
			$(this).removeClass('error-field');
		});
	});
</script>
</c:if>

<div class="con table-common">
	<form action="./doModifyPassword" method="POST"
		onsubmit="modifyFormSubmited(this); return false;">
		<table>
			<colgroup>
				<col width="150">
			</colgroup>
			<tbody>
				<tr>
					<th>기존 비밀번호</th>
					<td><input type="password" name="loginPw" placeholder="기존 비밀번호를 입력해주세요."></td>
				</tr>
				<tr>
					<th>변경 비밀번호</th>
					<td><input type="password" name="newloginPw" placeholder="변경할 비밀번호를 입력해주세요."></td>
				</tr>
				<tr>
					<th>변경 비밀번호 확인</th>
					<td><input type="password" name="loginPwConfirm"placeholder="변경할 비밀번호를 입력해주세요."></td>
				</tr>
				<tr>
					<th>수정</th>
					<td><input class="btn-a" type="submit" value="수정"> <input
						class="btn-a" type="reset" value="취소"
						onclick="location.href = './myPage';"></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
<%@ include file="../part/foot.jspf"%>