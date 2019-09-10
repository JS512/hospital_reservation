<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%@ page import="com.sbs.hospital.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="진료 가능 시간 설정" />
<%@ include file="../part/head.jspf"%>
<c:set var="isEmpty" value="${empty scheduleList }"/>
<!-- dateTimePicker -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.css"
	type="text/css" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.min.js"></script>
<script>
	var isEmpty = ${isEmpty};
	$(function() {
		//모든 datepicker에 대한 공통 옵션 설정
		$("input[name='startTime'], input[name='endTime']").datetimepicker({						
			minDate:'0',
			maxDate:'+1970/03/02',
			step:30,			
			gotoCurrent:true,			
	        lang:'ko',
	        format:'Y-m-d H:i:00'
	    })
	    .on("keydown", function(){
		    return false;
	    })
	    .attr("autocomplete", "off");

	});

	function showAddEmptySchedule(){
		if($(".addEmptyScheduleForm").is(":visible")){
			$(".addEmptyScheduleForm").hide();
		}else{
			$(".addEmptyScheduleForm").show();
		}
	}

	function addEmptySchedule(form){
		form.startTime.value = form.startTime.value.trim();
		form.endTime.value = form.endTime.value.trim();
		
		var startTime = form.startTime.value;
		var endTime = form.endTime.value;
		
		if(!startTime.length || !endTime.length){
			alert("빈칸없이 채워주세요.");
			return ;
		}

		$.post("/schedule/addEmptySchedule",
			{
				startTime : startTime,
				endTime : endTime
			},
			function(data){				
				if(data.success){					
					
					drawEmptySchedule(form, data.id);				
					$(form).find("input[type='text']").val("");

					
				}else{
					alert(data.msg);
				}
			},
			"json"
		);
		
		return ;
	}

	function drawEmptySchedule(form, id){
		var html ="<tr class='scheduleList'><td>" + form.startTime.value + "</td>"+
		    "<td>" + form.endTime.value + "</td>"+
		    "<td></td><td>"+
		    "<button type='button' onclick='deleteEmptySchedule(this);' data-id='"+id+"'>삭제</button></td></tr>";
		    		    
		if(isEmpty){
			isEmpty = false;
			$(".scheduleList").remove();
			$("table").append(html);
				
		}else{
			$(".scheduleList").last().after(html);
		}
		

	}

	function deleteEmptySchedule(btn){
		if(!confirm("해당 스케줄을 삭제하시겠 습니까?")){
			return ;
		}

		var scheduleId = $(btn).attr("data-id");

		$.post("/schedule/deleteEmptySchedule",
			{
				id : scheduleId
			},
			function(data){
				alert(data.msg);
				if(data.success){
					$(btn).parent().parent().remove();
					if(!$(".scheduleList").length){
						isEmpty = true;
						$("table").append('<tr class="scheduleList">'+
						  		'<th colspan="4">스케줄 없음</th>'+
							  	'</tr>');
					}
				}
			}
		)
		
		
	}
</script>

<div class="article-detail table-common con">
	<input type="button" onclick="showAddEmptySchedule();" value="일정 추가">
	<form onsubmit="addEmptySchedule(this); return false;">
		<table>
		  <tr>   
		    <th>시작 시간</th>
		    <th>종료 시간</th>
		    <th>예약 내용</th>
		    <th>비고</th>
		  </tr>
		  <tr class="addEmptyScheduleForm" hidden>		  	
			  	<td><input type="text" name="startTime"></td>
			  	<td><input type="text" name="endTime"></td>
			  	<td></td>
			  	<td><input type="submit" value="추가"></td>		  	
		  </tr>
		  <c:if test="${empty scheduleList }">
		  	<tr class="scheduleList">
		  		<th colspan="4">스케줄 없음</th>
		  	</tr>
		  </c:if>
		  <c:forEach items="${scheduleList }" var="schedule">
			  <tr class="scheduleList">
			    <td>${schedule.startTime }</td>
			    <td>${schedule.endTime }</td>
			    <td>${schedule.scheduleType }</td>
			    <td>
			    	<c:if test="${schedule.relId == 0}">
			    		<button type="button" onclick="deleteEmptySchedule(this);" data-id="${schedule.id }">삭제</button>
			    	</c:if>
		    	</td>
			  </tr>
		  </c:forEach>
		</table>
	</form>
	<hr>
	
</div>

<%@ include file="../part/foot.jspf"%>