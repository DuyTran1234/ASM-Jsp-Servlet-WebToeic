<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Exercise Listening</title>
</head>
<body>
	<% if(request.getAttribute("listListening") != null) {%>
		<c:forEach items="${listListening}" var="value">
			<a target="_blank" href="UpdateListeningExerciseForward?exerciseListen=${value.getExerciseName()}">${value.getExerciseName()}</a><br>
		</c:forEach>
	<%} else {%>
		<p>Không tìm thấy bài tập</p>
	<%} %>
</body>
</html>











