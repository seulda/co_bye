<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<main role="main" class="main-content">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-12 col-lg-10 col-xl-8">
				<h2>문의하기</h2>
				<button id="newBtn" onClick="location.href='/new'" class="btn btn-lg btn-primary" style="float:right; margin:10px 0px 30px 0px">글 작성</button>
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
							<th scope="col">&nbsp;0.</th>
							<td>SAMPLE TITLE</td>
							<td>USER</td>
							<td>Apr 24, 2021</td>
						</tr>
					</tbody>
				</table>
				
				<!-- accordion toggle start -->
				<div class="accordion w-100" id="accordion1">
					<c:forEach var="b" items="${list}" varStatus="status">
							<div class="card shadow">
								<div class="card-header" id="heading1">
									<a role="button" href="#collapse${status.count}" data-toggle="collapse" data-target="#collapse${status.count}" aria-expanded="false" aria-controls="collapse${status.count}" class="collapsed">
										<strong>Collapse one</strong>
										<span>&nbsp;${status.count}.</span>
										<span id="read">${b.b_title}</span>
										<span>${b.id}</span>
										<fmt:parseDate value="${b.b_time}" var="create" pattern="yyyy-MM-dd HH:mm:ss" />
										<span><fmt:formatDate value="${create}" pattern="MM월 dd일 HH:mm" /></span>
									</a>
								</div>
								<div id="collapse${status.count}" class="collapse" aria-labelledby="heading${status.count}" data-parent="#accordion1" style="">
									<div class="card-body">
										<form action="/view" method="post" id="frm${status.count}" name="frm${status.count}">
											<input type="hidden" id="num" value="${status.count}" />
											<input type="hidden" id="b_num" value="${b.b_num}" />
											<input type="text" id="id" placeholder="ID를 입력해주세요." />
											<input type="password" id="pw" placeholder="PW를 입력해주세요." />
											<input type="button" id="checkBtn" value="확인" />
										</form>
									</div>
								</div>
							</div>
					</c:forEach>
				</div>
				<!-- accordion toggle end -->
			</div>
			<!-- .col-12 -->
		</div>
		<!-- .row -->
	</div>
	<!-- .container-fluid -->
</main>
<!-- main -->


<script type="text/javascript">
	$(document).ready(function(e) {
		$('#checkBtn').click(function() {
			
			var num = $.trim($('#num').val());
			var set = '#frm' + num;

			alert("click checkBtn");

			if ($.trim($('#id').val()) === "") {
				$('#id').focus();
				alert("아이디를 입력해주세요.");
			} else if ($.trim($('#pw').val()) === "") {
				$('#pw').focus();
				alert("비밀번호를 입력해주세요.");
			} else {
				$.ajax({
					type : "POST",
					url : "/check",
					headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
					},
					data : JSON.stringify({
						"id" : $("#id").val(),
						"pw" : $("#pw").val(),
						"b_num" : $("#b_num").val()
					}),
					dataType : "text",
					success : function(data) {
						console.log("result : " + data);
						if ($.trim(data) === "success") {
							$(set).submit();
						} else if ($.trim(data) === "fail") {
							alert("ID/PW가 잘못되었습니다.");
							self.location = "/board";
						} else {
							alert("무엇인가 잘못되었습니다.");
							self.location = "/board";
						}
					},
					error : function(error) {
						alert("에러가 발생하였습니다.");
						self.location = "/board";
					}
				});
			}

		});
	});
</script>

<%@ include file="../layout/footer.jsp"%>
