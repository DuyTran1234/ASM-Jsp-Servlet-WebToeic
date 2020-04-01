<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="./bootstrap/js/jquery.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
<title>Quản lý đề thi thử Toeic</title>
</head>
<body>
	<jsp:include page="/View/Header.jsp"></jsp:include>
	<br><br><br><br><br>
	
	<div class="site-wrap">
		<div class="site-section">
			<div class="container">
            	<div class="row justify-content-center">     		
                	<div class="col-md-5">
                	
                		<div class="row">
                			<div class="col-md-12 form-group">
                				<form>
                					<input type="submit" value="Xem danh sách đề thi" class="btn btn-primary btn-lg px-5">
                				</form>
                			</div>
                		</div>
                		
                		<div class="row">
                			<div class="col-md-12 form-group">
                				<form action="CreateTestToeicForward" method="POST" target="_blank">
                					<input type="submit" value="Tạo đề thi mới" class="btn btn-primary btn-lg px-5">
                				</form>
                			</div>
                		</div>
                	</div>
                </div>
            </div>
        </div>
	</div>
	
	<!-- Script -->
	
	
	<jsp:include page="/View/Footer.jsp"></jsp:include>
</body>
</html>