<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="./bootstrap4/js/jquery.min.js"></script>
<title>Quản lý bài tập nghe</title>
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
                				<h5>Tạo bài tập mới:</h5>
                				<p>Nhập tên bài tập cần tạo:</p>
                				<input type="text" id="listening-exercise-new"><br><br>
                				<input type="button" class="btn btn-primary btn-lg px-5" onclick="createListeningExercise()">
                			</div>
                			<div class="col-md-12 form-group">
                				<div id="create-listening-exercise-ajax"></div>
                			</div>               		           		
                		</div>
              		
                		<div class="row">
                		
                		</div>
                		
                	</div>
                </div>
             </div>
		</div>
	</div>
	<!-- Script -->
	<script src="./bootstrap4/js/jquery.min.js"></script>
    <script src="./bootstrap4/js/bootstrap.min.js"></script>
    <script src="./bootstrap4/js/jquery.twbsPagination.js" type="text/javascript"></script>
    
    <script>
    	function createListeningExercise() {
    		var exerciseName = document.getElementById("listening-exercise-new").value;
    		var url = "CreateListeningExerciseForward?exerciseName=" + exerciseName;
    		var xhttp = new XMLHttpRequest();
    		xhttp.onreadystatechange = function() {
    			if(this.readyState == 4 && this.status == 200) {
    				document.getElementById("create-listening-exercise-ajax").innerHTML = xhttp.responseText;
    			}
    		}
    		xhttp.open("POST", url, true);
    		xhttp.send();
    	}
    </script>
	
	<jsp:include page="/View/Footer.jsp"></jsp:include>
</body>
</html>