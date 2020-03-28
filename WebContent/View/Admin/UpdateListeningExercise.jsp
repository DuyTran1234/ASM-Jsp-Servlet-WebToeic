<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style>
        table, th{
          border: 1px solid black;
          max-width: 138px;
          max-height: 50px;
          word-wrap: break-word
        }
        input {
        	max-width: 125px;
          	max-height: 50px;
          	 word-wrap: break-word
        }
    </style>
<title>Sửa bài tập nghe</title>
</head>
<body>
	<h2>${listeningExerciseName}</h2>
	<form enctype="multipart/form-data" method="post" action="UpdateListeningExerciseController?exerciseNameListen=${listeningExerciseName}">
		<table>
			<tr>
				<th>exerciseID</th>
				<th>exerciseName</th>
				<th>questionID</th>
				<th>questionContent</th>
				<th>optionA</th>
				<th>optionB</th>
				<th>optionC</th>
				<th>optionD</th>
				<th>result</th>
				<th>date</th>
				<th>path</th>
				<th>Edit file</th>
			</tr>
			<c:forEach items="${listListeningExercise}" var="value">
				<tr>
					<td><input type="text" name="exerciseID" value="${value.getExerciseID()}" readonly></td>
					<td><input type="text" name="exerciseName" value="${value.getExerciseName()}"></td>
					<td><input type="text" name="questionID" value="${value.getQuestionID()}"></td>
					<td><input type="text" name="questionContent" value="${value.getQuestionContent()}"></td>
					<td><input type="text" name="optionA" value="${value.getOptionA()}"></td>
					<td><input type="text" name="optionB" value="${value.getOptionB()}"></td>
					<td><input type="text" name="optionC" value="${value.getOptionC()}"></td>
					<td><input type="text" name="optionD" value="${value.getOptionD()}"></td>
					<td><input type="text" name="result" value="${value.getResult()}"></td>
					<td><input type="text" name="date" value="${value.getDate()}" readonly></td>
					<td><input type="text" name="path" value="${value.getPath()}" readonly></td>
					<td><input type="file" name="file"></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<input type="submit" value="Câp nhật">
	</form>
	<p><%=request.getAttribute("msgUpdateDatabase")!=null?request.getAttribute("msgUpdateDatabase"):"" %></p>
	<p><%=request.getAttribute("msgCheckFileName")!=null?request.getAttribute("msgCheckFileName"):"" %></p>
	<p><%=request.getAttribute("msgWriteFile")!=null?request.getAttribute("msgWriteFile"):"" %></p>
	<p><%=request.getAttribute("msgUpdateListeningExercise")!=null?request.getAttribute("msgUpdateListeningExercise"):"" %></p>
</body>
</html>

















