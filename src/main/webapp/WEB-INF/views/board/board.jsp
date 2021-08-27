<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<main role="main" class="main-content">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-12 col-lg-10 col-xl-8">
				<h2>문의하기</h2>
				<button id="newBtn" onClick="location.href='/write'" class="btn btn-lg btn-primary" style="float:right; margin:10px 0px 30px 0px">글 작성</button>
				<table class="table border bg-white">
					<thead>
						<tr>
							<th>No.</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="col">0.</th>
							<td><a href="/view">SAMPLE TITLE</a></td>
							<td>USER</td>
							<td>Apr 24, 2021</td>
						</tr>
						
						<c:forEach var="b" items="${list}" varStatus="status">
							<tr>
								<input type="hidden" id="num" value="${b.b_num}" />
								<th scope="col">&nbsp;${status.count}.</th>
								<td><a href="/view">${b.b_title}</a></td>
								<td>${b.id}</td>
								<fmt:parseDate value="${b.b_time}" var="create" pattern="yyyy-MM-dd HH:mm:ss" />
								<td><fmt:formatDate value="${create}" pattern="MM월 dd일 HH:mm" /></td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>

			</div>
			<!-- .col-12 -->
		</div>
		<!-- .row -->
	</div>
	<!-- .container-fluid -->
</main>
<!-- main -->


<%@ include file="../layout/footer.jsp"%>
