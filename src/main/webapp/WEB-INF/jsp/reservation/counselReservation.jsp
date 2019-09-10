<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sbs.hospital.dto.Member"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="pageTitle" value="진료 예약" />

<%@ include file="../part/head.jspf"%>

<!-- datePicker -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script>
	var deptList = {};
</script>

<c:forEach var="dept" items="${deptList}">
	<script>
		deptList[${dept.id}] = {};
		deptList[${dept.id}].id = ${dept.id};
		deptList[${dept.id}].name = '${dept.name}';
		deptList[${dept.id}].doctors = [];
	</script>
</c:forEach>

<c:forEach var="staff" items="${staffList}">
	<script>		
		var staff = {};
		staff.id = ${staff.id};
		staff.name = '${staff.name}';
		staff.schedules = [];
	</script>
	
	<c:forEach var="schedule" items="${emptyScheduleList}">
		<script>		
			if(${schedule.staffId} == ${staff.id}){
				
				var schedule = {};
				var time = '${schedule.startTime}'.split(" ");
				var date = time[0];
				var startTime = time[1];
				var endTime = ('${schedule.endTime}'.split(" "))[1];
	
				schedule.id = ${schedule.id};
				schedule.date = date;
				schedule.startTime = startTime;
				schedule.endTime = endTime;

				staff.schedules.push(schedule);				
			}
					
		</script>
	</c:forEach>
	
	<script>
		deptList[${staff.deptId}].doctors.push(staff);
	</script>
</c:forEach>



<script>
	$(function() {
		//모든 datepicker에 대한 공통 옵션 설정
		
		$.datepicker
				.setDefaults({
					dateFormat : 'yy-mm-dd' //Input Display Format 변경
					,
					showOtherMonths : true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
					,
					showMonthAfterYear : true //년도 먼저 나오고, 뒤에 월 표시
					,
					changeYear : true //콤보박스에서 년 선택 가능
					,
					changeMonth : true //콤보박스에서 월 선택 가능                
					,
					showOn : "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
					,
					buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
					,
					buttonImageOnly : true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
					,
					buttonText : "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트              
					,
					yearSuffix : "년" //달력의 년도 부분 뒤에 붙는 텍스트
					,
					monthNamesShort : [ '1', '2', '3', '4', '5', '6', '7', '8',
							'9', '10', '11', '12' ] //달력의 월 부분 텍스트
					,
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 Tooltip 텍스트
					,
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 부분 텍스트
					,
					dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일', '금요일',
							'토요일' ] //달력의 요일 부분 Tooltip 텍스트
					,
					minDate : "0" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
					,
					maxDate : "+2M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)       

				});
		
		$("#datepicker").on("keydown", function(){
			return false;
		})
		.attr("placeholder", "누르면 날짜 선택 가능");
		//input을 datepicker로 선언
		$("#datepicker").datepicker();

		//From의 초기값을 오늘 날짜로 설정
		//$('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
		

	});

	function categoryChange(obj) {
		var selectedDeptId = $(obj).val();

		var dept = deptList[selectedDeptId];

		var $doctorSelectBox = $('#doctor');

		$doctorSelectBox.empty();

		$doctorSelectBox.append('<option value="">의사 선택</option>');
		if ( selectedDeptId ) {
			for ( var i = 0; i < dept.doctors.length; i++ ) {
				var doctor = dept.doctors[i];				
				$doctorSelectBox.append('<option value="' + doctor.id + '">' + doctor.name + '</option>');
			}
		}		
		$('#emptyScheduleTime').empty();
	}

	var staffSchedule = [];
	function doctorChange(input){
		var selectedStaffId = $(input).val();
		var selectedDeptId = $("#dept").val();
		var dept = deptList[selectedDeptId];	
		
		
		for ( var i = 0; i < dept.doctors.length; i++ ) {			
			var doctor = dept.doctors[i];			
			if(doctor.id == selectedStaffId){
				staffSchedule = doctor.schedules;							
				break;		
			}				
		}	

		$('#emptyScheduleTime').empty();
	}

	function scheduleTimeChange(input){
						
		var $timeContainer = $("#emptyScheduleTime");
		$timeContainer.empty();
		
		var selectedDate = $(input).val();
		
		
		for(var i=0 ;i<staffSchedule.length ;i++){			
			var schedule = staffSchedule[i];
						
			if(schedule.date == selectedDate){
				$timeContainer.append("<option value='"+schedule.id+"'>"+schedule.startTime+ " ~ " +schedule.endTime + "</option>");
			}
		}
		if(!$timeContainer.children().length){
			$timeContainer.append("<option value=''>없음</option>");
		}
	}

	function submitCounselReservationForm(form){
		form.doctor.value = form.doctor.value.trim(); 
		form.body.value = form.body.value.trim();
		form.scheduleId.value = form.scheduleId.value.trim();
		
		var doctor = form.doctor.value;
		var scheduleId = form.scheduleId.value;
		var body = form.body.value;

		if(!doctor.length){
			alert("담당 의사를 선택해 주세요.");
			return ;
		}

		if(!scheduleId.length){
			alert("상담 시간대 선택해 주세요.");
			return ;
		}

		if(!body.length){
			alert("상담 내용을 작성해 주세요.");
			return ;
		}
		form.submit();
	}
	
</script>


<div class="article-detail table-common con">
	<form action="./doReservation" method="POST"
			onsubmit="submitCounselReservationForm(this); return false;">
		<input type="hidden" name="relType" value="counseling">
		<input type="hidden" name="scheduleType" value="counseling">
		<table>
			<colgroup>
				<col width="80">
			</colgroup>
			<tbody>
				<tr>
					<th>이름</th>
					<td><c:out value="${loginedMember.name}" escapeXml="true" /></td>
				</tr>
				<tr>
					<th>회원 번호</th>
					<td><c:out value="${loginedMember.id}" /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><c:out value="${loginedMember.email}" /></td>
				</tr>
			</tbody>
		</table>

		<table>
			<tr>
				<th>진료과 및 의료진 선택</th>
				<td><select id="dept" onchange="categoryChange(this);" name="dept">
						<option value="">의료과선택</option>
						<c:forEach var="dept" items="${deptList}">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
				</select>
				<td>
					<select id="doctor" name="doctor" onchange="doctorChange(this);">						
					</select>
				</td>
			<tr>
			<tr>
				<th>날짜 선택</th>
				<td><input onchange="scheduleTimeChange(this);" type="text" name="date" id="datepicker" readonly>	</td>				
			</tr>
			<tr>
				<th>시간 선택</th>
				<td>				
					<select id="emptyScheduleTime" name="scheduleId"></select>					
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td>				
					<textarea name="body"></textarea>					
				</td>
			</tr>
		</table>

		<input type="submit" value="예약하기">				
		<input type="reset"
			value="예약취소" onclick="location.href = '/';">
	</form>
</div>









<%@ include file="../part/foot.jspf"%>