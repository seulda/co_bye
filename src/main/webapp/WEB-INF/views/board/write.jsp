<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<main role="main" class="main-content">
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-12">
				<div class="card shadow mb-4">
					<div class="card-header">
						<strong class="card-title">문의 사항을 입력해주세요. ID/PW는 분실시 찾을 수 없으니 메모해두길 권장합니다.</strong>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group mb-3">
									<label for="simpleinput">ID</label>
									<input type="text" id="id" class="form-control" placeholder="ID 입력">
								</div>
								<!-- <div class="form-group mb-3">
			                        <label for="example-email">Email</label>
			                        <input type="email" id="example-email" name="example-email" class="form-control" placeholder="Email">
			                    </div> -->
							</div>
							<div class="col-md-6">
								<div class="form-group mb-3">
									<label for="example-password">Password</label>
									<input type="password" id="pw" class="form-control" placeholder="PW 입력">
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group mb-3">
									<label for="simpleinput">Title</label>
									<input type="text" id="title" class="form-control" placeholder="제목을 입력해주세요.">
								</div>
								<div class="form-group mb-3">
									<label for="example-textarea">Content</label>
									<textarea class="form-control" id="content" placeholder="내용을 입력해주세요." rows="8"></textarea>
								</div>
								<!-- <div class="form-group mb-3">
									<label for="customFile">File input</label>
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="customFile">
										<label class="custom-file-label" for="customFile">Choose file</label>
									</div>
								</div> -->
							</div>
							<div class="col-md-12"><br>
								<button id="btn" class="btn btn-lg btn-primary px-5">작성</button>
								<button id="cBtn" class="btn btn-lg btn-primary px-5" onClick="location.href='/board'">취소</button>
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


<script type="text/javascript">

	$(document).ready(function(e){
		$('#btn').click(function(){
			
			if($.trim($('#id').val()) === "") {
			    $('#id').focus();
				alert("아이디를 입력해주세요.");
			}
			else if($.trim($('#pw').val()) === "") {
			    $('#pw').focus();
				alert("비밀번호를 입력해주세요.");
			}
			else if($.trim($('#title').val()) === "") {
			    $('#title').focus();
				alert("제목을 입력해주세요.");
			} 
			else if($.trim($('#content').val()) === "") {
			    $('#content').focus();
				alert("내용을 입력해주세요.");
			} 
			else {
				$.ajax({
					type: "POST",
					url: "/write",
					headers: {
						"Content-Type": "application/json",
		                "X-HTTP-Method-Override": "POST"
					},
					data : JSON.stringify({
						"id" : $("#id").val(),
						"pw" : $("#pw").val(),
						"b_title" : $("#title").val(),
						"b_content" : $("#content").val()
					}),
					dataType: "text",
					success: function(data) {
						console.log("result : " + data);
						if ($.trim(data) === "success") {
							alert("문의가 등록되었습니다.");
						} else if ($.trim(data) === "fail") {
							alert("등록에 실패하였습니다.");
						} else {
							alert("무엇인가 잘못되었습니다.");
						}
					},
					error : function(error) {
				        alert("에러가 발생하였습니다.");
				    },
				    complete : function() {
				    	self.location="/board";
				    }
				});
			}
			
		});
	});
	
</script>

<%@ include file="../layout/footer.jsp"%>
