<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<main role="main" class="main-content">
	<div class="container-fluid">
		<div class="alert alert-info" role="alert"><span class="fe fe-alert-circle fe-16 mr-2"></span>실시간 확진자의 집계는 실제 확진자 수와 다를 수 있으며, 공적인 효력이 없음을 안내합니다.&nbsp;&nbsp;해당 데이터를 사용하면서 발생하는 문제의 책임은 전적으로 사용자에게 있습니다.</div>
		<div class="alert alert-dark" role="alert">
			<div class="Clock"><span>현재 시간&nbsp;:&nbsp;</span><span id="Clockday">00/00/00</span>&nbsp;&nbsp;<span id="Clock">00:00</span></div>
		</div><br>
		<div class="row justify-content-center">
			<div class="col-12">
				<div class="row">
					<div class="col-md-6 col-xl-6 mb-4">
						<div class="card shadow bg-primary text-white border-0">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col-3 text-center">
										<span class="circle circle-sm bg-primary-light"><i class="fe fe-16 fe-alert-circle text-white mb-0"></i></span>
									</div>
									<div class="col pr-0">
										<p class="small text-muted mb-0">실시간 추가 확진자</p>
										<span class="h3 mb-0 text-white">&nbsp;<fmt:formatNumber value="${mCnt}" pattern="#,###,###" /> 명</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-xl-6 mb-4">
						<div class="card shadow border-0">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col-3 text-center">
										<span class="circle circle-sm bg-primary"><i class="fe fe-16 fe-filter text-white mb-0"></i></span>
									</div>
									<div class="col pr-0">
										<p class="small text-muted mb-0">어제 확진자</p>
										<span class="h3 mb-0">&nbsp;<fmt:formatNumber value="${state.addCnt}" pattern="#,###,###" /> 명</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- end section -->
				
				<br><hr>
				<div class="row">
					<div class="col-md-12"><br>
						<h4 style="text-align:center; margin:30px 0px;">실시간 코로나 확진 현황 (재난문자)</h4>
						<c:choose>
						<c:when test="${n ne null}">
							<h6 style="text-align:center; margin:40px 0px;">금일 집계된 데이터는 없습니다.</h6>
						</c:when>
						<c:when test="${dlist ne null}">
						<table class="table table-borderless table-striped">
							<thead>
								<tr role="row">
									<th>발생일시</th>
									<th>지역</th>
									<th>재난 문자 내용</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${dlist}">
								<fmt:parseDate value="${list.create_date}" var="dt" pattern="yyyy/MM/dd HH:mm:ss" />
								<tr>
									<th scope="col"><fmt:formatDate value="${dt}" pattern="MM월 dd일 HH시 mm분" /></th>
									<td>${list.location_name}</td>
									<td>${list.msg}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						</c:when></c:choose>
					</div>
				</div>
				<!-- end section -->
			</div>
		</div>
		<!-- .row -->
	</div>
	<!-- .container-fluid -->
</main>
<!-- main -->

<script>
	function Clock() {
		var date = new Date();
		var YYYY = String(date.getFullYear());
		var MM = String(date.getMonth() + 1);
		var DD = Zero(date.getDate());
		var hh = Zero(date.getHours());
		var mm = Zero(date.getMinutes());
		var ss = Zero(date.getSeconds());
		var Week = Weekday(); Write(YYYY, MM, DD, hh, mm, ss, Week);
		//시계에 1의자리수가 나올때 0을 넣어주는 함수 (ex : 1초 -> 01초)
		function Zero(num) {
			return (num < 10 ? '0' + num : '' + num);
		}
		//요일을 추가해주는 함수
		function Weekday() {
			var Week = ['일', '월', '화', '수', '목', '금', '토'];
			var Weekday = date.getDay(); return Week[Weekday];
		}
		//시계부분을 써주는 함수
		function Write(YYYY, MM, DD, hh, mm, ss, Week) {
			var Clockday = document.getElementById("Clockday");
			var Clock = document.getElementById("Clock");
			Clockday.innerText = YYYY + '/' + MM + '/' + DD + '(' + Week + ')';
			Clock.innerText = hh + ':' + mm + ':' + ss;
		}
	}
	setInterval(Clock, 1000);
	//1초(1000)마다 Clock함수를 재실행 한다
</script>

<%@ include file="../layout/footer.jsp"%>
