<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<main role="main" class="main-content">
	<div class="container-fluid">
		<div class="alert alert-info" role="alert">
			<span class="fe fe-alert-circle fe-16 mr-2"></span>COBYE의 데이터는 재사용이 가능하며, 공적인 효력이 없음을 안내합니다.&nbsp;&nbsp;&nbsp;또한 해당 데이터를 사용하면서 발생하는 문제는 전적으로 사용자에게 있습니다.
		</div>
		<div class="alert alert-danger" role="alert">
			<span class="fe fe-alert-circle fe-16 mr-2"></span>현재 공공데이터포털의 데이터 응답이 원활하지 않아 상세한 정보 제공이 어려운 상태입니다.
		</div>
		<br><br>
		<div class="row justify-content-center">
			<div class="col-12">
				<!-- start section -->
				<div class="row">
					<div class="col-md-12 col-xl-12 mb-12">
						<div class="card shadow border-0">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col-1 text-center">
										<span class="circle circle-sm bg-primary"><i class="fe fe-16 fe-filter text-white mb-0"></i></span>
									</div>
									<div class="col pr-0">
										<span class="h5 mb-0">기준일자 : ${state.ndate}</span><br>
										<span class="h3 mb-0">&nbsp;<fmt:formatNumber value="${state.addCnt}" pattern="#,###,###" /> 명</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12 col-xl-12 mb-12" style="margin-top: 2%;">
						<div class="card shadow border-0">
							<div class="card-body">
								<div class="row align-items-center">
									<div class="col-1 text-center">
										<span class="circle circle-sm bg-primary"><i class="fe fe-16 fe-filter text-white mb-0"></i></span>
									</div>
									<div class="col pr-0">
										<span class="h5 mb-0">누적 확진자</span><br>
										<span class="h3 mb-0">&nbsp;${state.allCnt} 명</span>
										<%-- <span class="h3 mb-0">&nbsp;<fmt:formatNumber value="${state.allCnt}" pattern="#,###,###" /> 명</span> --%>
									</div>
								</div>
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


<%@ include file="../layout/footer.jsp"%>
