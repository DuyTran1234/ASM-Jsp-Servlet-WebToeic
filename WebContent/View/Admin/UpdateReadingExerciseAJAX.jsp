<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Exercise</title>
</head>
<body>
	<% if(request.getAttribute("exercise-name-old") != null) { %>
		<form action="UpdateReadingExerciseController" method="POST" enctype="multipart/form-data">
			<p>Tên bài tập:</p>
			<input type="text" value="<%=request.getAttribute("exercise-name-old") %>" name="exercise-old" readonly><br>
			<p>Sửa tên bài () : </p>
			<input type="text" name="exercise-new" value="<%=request.getAttribute("exercise-name-old") %>"><br>
			<p>Chọn file cập nhật (* Chỉ sử dụng file có định dạng .xls hoặc .xlsx)</p>
			<input type="file" name="my-file"><br><br>
			<input type="submit" value="Cập nhật" class="btn btn-primary btn-lg px-5">			
		</form>
	<%}
	else {
	%>
		<p style="color:red">Không tồn tại tên bài học</p>
	<%}%>

</body>
</html>






