<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Test Toeic Ajax</title>
</head>
<body>
	<% if(request.getAttribute("listNameTestToeic") != null) { %>
		<c:forEach items="${listNameTestToeic}" var="value">
			<a target="_blank" href="UpdateTestToeicForward?testToeicNameSearch=${value}">${value}</a><br>
		</c:forEach>
	<%} else { %>
		<p>Không tìm thấy đề thi</p>
	<%} %>
</body>
</html>