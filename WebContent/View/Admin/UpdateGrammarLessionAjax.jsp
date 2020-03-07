<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
    <%@page import = "BEAN.Lession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Lession</title>
</head>
<body>
	<c:if test="${lessionUpdate != null}">
		<form action="UpdateGrammarLessionController" method="POST" enctype="multipart/form-data">
			<p>Tên bài: </p>
			<input type="text" name="lession-name-old" value="${lessionUpdate.getLessionName()}" readonly>
			<p>Sửa tên bài:</p>
			<input type="text" name="lession-name-update" value="${lessionUpdate.getLessionName()}">
			<p>Sửa nội dung bài học:</p>
			<input type="file" name="update-file">
			<br><br>
			<input type="submit" value="Cập nhật" class="btn btn-primary btn-lg px-5">
		
		</form>
	</c:if>
	<c:if test="${lessionUpdate == null}">
		<p style="color:red">Không tìm thấy tên bài học</p>
	</c:if>
</body>
</html>


