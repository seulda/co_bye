<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<!-- <main role="main" class="main-content">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-12">
				
			</div>
		</div>
		.row
	</div>
	.container-fluid
</main> -->
<!-- main -->

<div class="wrapper vh-100">
	<div class="align-items-center h-100 d-flex w-50 mx-auto">
		<div class="mx-auto text-center">
			<h1 class="display-1 m-0 font-weight-bolder text-muted"style="font-size: 80px;">ERROR</h1>
			<h1 class="mb-1 text-muted font-weight-bold">오류가 발생했습니다.	</h1>
			<h1 class="mb-1 text-muted font-weight-bold">An error has occurred.</h1>
			<h6 class="mb-3 text-muted">amekimm@gmail.com으로 문의 주시기 바랍니다.</h6>
			<h6 class="mb-3 text-muted">Please contact us at amekimm@gmail.com.</h6>
			<a href="/" class="btn btn-lg btn-primary px-5">Back to Main</a>
			
			<br><br>
			<div class="row">
				<div class="col-12 mb-4">
					<div class="card shadow">
						<div class="card-header">
							<strong class="h3 card-title"><c:out value="${exception.getMessage()}"></c:out></strong>
						</div>
						<div class="card-body">
							<ul>
								<c:forEach items="${exception.getStackTrace()}">
									<li><c:out value="${stack}"></c:out></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>


<%@ include file="../layout/footer.jsp"%>
