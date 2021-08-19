<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<main role="main" class="main-content">
	<div class="container-fluid">
		<div class="alert alert-info" role="alert">
			<span class="fe fe-alert-circle fe-16 mr-2"></span>COBYE의 데이터는 재사용이 가능하며, 공적인 효력이 없음을 안내합니다.&nbsp;&nbsp;&nbsp;또한 해당 데이터를 사용하면서 발생하는 문제의 책임은 전적으로 사용자에게 있습니다.
		</div>
		<div class="alert alert-dark" role="alert">
			<div class="Clock">
				<span>현재 시간&nbsp;:&nbsp;</span><span id="Clockday">00/00/00</span>&nbsp;&nbsp;<span id="Clock">00:00</span>
			</div>
		</div>
		<c:if test="${e eq 'e'}">
			<div class="alert alert-danger" role="alert">
				<span class="fe fe-alert-circle fe-16 mr-2"></span>현재 공공데이터포털의 데이터 응답이 원활하지 않아 상세한 정보 제공이 어려운 상태입니다.
			</div>
		</c:if>
		<br><br>
		<div class="row justify-content-center">
			<div class="col-12">
				<!-- start section -->
				<div class="row">
					<div class="col-md-4 col-xl-4 mb-3">
						<div class="card shadow border-0">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col-3 text-center">
										<span class="circle circle-sm bg-primary"><i class="fe fe-16 fe-filter text-white mb-0"></i></span>
									</div>
									<div class="col pr-0">
										<span class="h6 mb-0">집계 기준 일자</span><br>
										<span class="h4 mb-0">&nbsp;${state.ndate} 0시</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-xl-4 mb-3">
						<div class="card shadow border-0">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col-3 text-center">
										<span class="circle circle-sm bg-primary"><i class="fe fe-16 fe-filter text-white mb-0"></i></span>
									</div>
									<div class="col pr-0">
										<span class="h6 mb-0">추가 확진자</span><br>
										<span class="h3 mb-0">&nbsp;<fmt:formatNumber value="${state.addCnt}" pattern="#,###,###" /> 명</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-xl-4 mb-3">
						<div class="card shadow border-0">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col-3 text-center">
										<span class="circle circle-sm bg-primary"><i class="fe fe-16 fe-filter text-white mb-0"></i></span>
									</div>
									<div class="col pr-0">
										<span class="h6 mb-0">누적 확진자</span><br>
										<span class="h3 mb-0">&nbsp;${state.allCnt} 명</span>
										<%-- <span class="h3 mb-0">&nbsp;<fmt:formatNumber value="${state.allCnt}" pattern="#,###,###" /> 명</span> --%>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- end section -->
				
				<br><br><hr><br>
				
				<!-- start section -->
				<div class="row">
					<div class="col-12 mb-4">
						<div class="card shadow">
							<div class="card-header">
								<strong class="h3 card-title">cobye</strong>
							</div>
							<div class="card-body">
								<p>cobye는 공공데이터포털에서 제공해주는 정보를 기반으로 제작된 사이트입니다.</p>
								<p>cobye에서 제공하는 데이터에는 공적인 효력이 없음을 안내드립니다.</p>
								<p>해당 데이터를 사용하면서 발생하는 문제의 책임은 데이터 사용자에게 있습니다.</p>
								<p>사이트 이용 중 문의사항은 [amekimm@gmail.com]으로 연락주시기 바랍니다.</p>
								<br><br><br>
								<p style="text-align: right;">개발자 김다슬&nbsp;</p>
							</div>
						</div>
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
