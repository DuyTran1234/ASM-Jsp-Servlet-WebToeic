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
		<h5 style="color:green">Tên phù hợp để tạo</h5>
	<%} else {%>
		<h5 style="color:red">Tồn tại tên bài tập trong cơ sở dữ liệu</h5>
	<%} %>
</body>
</html>