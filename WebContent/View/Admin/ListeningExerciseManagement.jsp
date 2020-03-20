<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="./bootstrap4/js/jquery.min.js"></script>
<meta charset="UTF-8">
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
                				<form action="CreateReadingExerciseController" method="POST" enctype="multipart/form-data">
	                				<h5>Tạo bài tập mới:</h5>
	                				<p>Nhập tên bài tập cần tạo:</p>
	                				<input type="text" id="listening-exercise-new" name="exercise-listening-name">
	                				<input type="button" value="Kiểm tra" class="btn btn-primary btn-lg px-5" onclick="createListeningExercise()">
	                				<p id="create-listening-exercise-ajax"></p>
	                				<table id="fileTableListening">																											
									</table>
									<br>
									<input id="addFileListening" type="button" value="Add Question"/><br><br>
									<input type="submit" value="Tạo" class="btn btn-primary btn-lg px-5">								
	                			</form>
	                			<p style="color:red"><%=request.getAttribute("msgCreateListening")!=null? request.getAttribute("msgCreateListening") : "" %></p>                				
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
    
	<script>
    $(document)
            .ready(
                    function() {
                        //add more file components if Add is clicked
                        $('#addFileListening')
                                .click(
                                        function() {
                                            var fileIndex = $('#fileTableListening tr')
                                                    .children().length;
                                            $('#fileTableListening')
                                                    .append('<tr>' +
        											'<td>' +
    												'<p>Nhập questionID:</p>' +
    												'<input type="text" name="question-id">' +
    												'<p>Nhập questionContent: </p>' +
    												'<input type="text" name="question-content">' +
    												'<p>Nhập optionA:</p>' +
    												'<input type="text" name="optionA" value="A. ">' +
    												'<p>Nhập optionB:</p>' +
    												'<input type="text" name="optionB" value="B. ">' +
    												'<p>Nhập optionC:</p>' +
    												'<input type="text" name="optionC" value="C. ">' +
    												'<p>Nhập optionD:</p>' +
    												'<input type="text" name="optionD" value="D. ">' +
    												'<p>Nhập result: </p>' +
    												'<input type="text" name="result">' +
    												'<p>Chọn file nghe (*chỉ sử dụng file có định dạng .mp3)</p>' +
        											'<input type="file" name="files"><br><br><br>' +	
    											'</td>' +
    										'</tr>');
                                        });
 
                    });
	</script>
	
	<jsp:include page="/View/Footer.jsp"></jsp:include>
</body>
</html>