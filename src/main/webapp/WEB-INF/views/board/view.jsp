<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<main role="main" class="main-content">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-12">
				<div class="card shadow mb-4">
					<div class="card-header">
						<!-- <strong class="card-title" style="font-size:20px;">여기가 글 제목</strong> -->
						<strong class="card-title" style="font-size:20px;">${read.b_title}</strong>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group mb-6">
									<!-- <span>여기가 글 내용</span> -->
									<span>${read.b_content}</span>
								</div>
							</div>
							<div class="col-md-12">
								<button id="updateBtn" class="btn btn-lg btn-primary px-5">수정</button>
								<button id="deleteBtn" class="btn btn-lg btn-primary px-5">삭제</button>
								<button id="listBtn" onClick="location.href='/board'" class="btn btn-lg btn-primary px-5">목록</button>
							</div>
						</div>
					</div>
				</div>
				<!-- / .card -->
			</div>
			<!-- .col-12 -->
		</div>
		<!-- .row -->
	</div>
	<!-- .container-fluid -->
</main>
<!-- main -->


<%@ include file="../layout/footer.jsp"%>
