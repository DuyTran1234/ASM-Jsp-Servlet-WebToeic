<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Vocabulary Lession</title>
</head>
<body>
	<c:if test="${lessionNameOld != null}">
		<form action="UpdateVocabularyLessionController" method="POST" enctype="multipart/form-data">
			<p>Tên bài học:</p>
			<input type="text" name="lession-name-old" value="${lessionNameOld}" readonly>
			<p>Sửa tên bài:</p>
			<input type="text" name="lession-name-new" value="${lessionNameOld}">
			<input type="file" name="my-file"><br><br>
			<input type="submit" value="Update" class="btn btn-primary btn-lg px-5">
		</form>
	</c:if>
	
	<c:if test="${lessionNameOld == null}">
		<div class="col-md-12 form-group">
			<p style="color:red">Không tồn tại tên bài học</p>
		</div>
	</c:if>
</body>
</html>


