<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo bài tập</title>
</head>
<body>
	<% if(request.getAttribute("exercise-name") != null) { %>
		<form action="" method="POST" enctype="multipart/form-data">
			<p>Tên bài tập: </p>
			<input type="text" value="<%=request.getAttribute("exercise-name")%>" readonly>
			<table id="fileTable">
				<tr>
					<td>
						<p>Nhập questionID:</p>
						<input type="text" name="question-id">
						<p>Nhập questionContent: </p>
						<input type="text" name="question-content">
						<p>Nhập optionA:</p>
						<input type="text" name="optionA" value="A. ">
						<p>Nhập optionB:</p>
						<input type="text" name="optionB" value="B. ">
						<p>Nhập optionC:</p>
						<input type="text" name="optionC" value="C. ">
						<p>Nhập optionD:</p>
						<input type="text" name="optionD" value="D. ">
						<p>Nhập result: </p>
						<input type="text" name="result">
					</td>
				</tr>
				<tr>
					<td>
						<p>Chọn file nghe (*chỉ sử dụng file có định dạng .mp3)</p>
						<input type="file" name="files"><br>
						<input id="addFile" type="button" value="Add File"/><br><br>
					</td>
				</tr>
			</table>
			<input type="submit" value="Add">
		</form>
	<%} else {%>
		<p style="color:red">Tồn tại tên bài tập trong cơ sở dữ liệu</p>
	<%} %>
	
	<!-- Script -->
	<script src="./bootstrap4/js/jquery.min.js"></script>
	<script>
    $(document)
            .ready(
                    function() {
                        //add more file components if Add is clicked
                        $('#addFile')
                                .click(
                                        function() {
                                            var fileIndex = $('#fileTable tr')
                                                    .children().length - 1;
                                            $('#fileTable')
                                                    .append("<tr>" +
                                        					"<td>" +
                                    						"<p>Nhập questionID:</p>" +
                                    						"<input type=\"text\" name=\"question-id\">" +
                                    						"<p>Nhập questionContent: </p>""
                                    						<input type="text" name="question-content">\
                                    						<p>Nhập optionA:</p>\
                                    						<input type="text" name="optionA" value="A. ">\
                                    						<p>Nhập optionB:</p>\
                                    						<input type="text" name="optionB" value="B. ">\
                                    						<p>Nhập optionC:</p>\
                                    						<input type="text" name="optionC" value="C. ">\
                                    						<p>Nhập optionD:</p>\
                                    						<input type="text" name="optionD" value="D. ">\
                                    						<p>Nhập result: </p>\
                                    						<input type="text" name="result">\
                                    					</td>\
                                    				</tr>\
                                    				<tr>\
                                    					<td>\
                                    						<p>Chọn file nghe (*chỉ sử dụng file có định dạng .mp3)</p>\
                                    						<input type="file" name="files">\
                                    						<input id="addFile" type="button" value="Add File"/><br><br>\
                                    					</td>\
                                    				</tr>");
                                        });
 
                    });
	</script>
</body>
</html>