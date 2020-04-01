<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5 style="color:red"><%=request.getAttribute("msgCheckToeicName")!= null? request.getAttribute("msgCheckToeicName") : "" %></h5>
	<h5 style="color:red"><%=request.getAttribute("msgDB")!= null? request.getAttribute("msgDB") : "" %></h5>
</body>
</html>