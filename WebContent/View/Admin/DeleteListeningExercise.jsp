<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<style>
        table, th, tr, td{
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
<title>Xoá bài tập nghe</title>
</head>
<body>
	<div id="response-ajax">
		<h2>${listeningExerciseName}</h2>
		<form action="DeleteListeningExerciseController?exerciseName=${listeningExerciseName}&DeleteAll=true" method="POST">
			<input type="submit" value="Xoá tất cả bài tập">
		</form><br><br>
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
				<th></th>
			</tr>
			<c:forEach items="${listListeningExercise}" var="value">
				<tr>
					<td><input type="text" name="exerciseID" value="${value.getExerciseID()}" readonly></td>
					<td><input type="text" name="exerciseName" value="${value.getExerciseName()}" readonly></td>
					<td><input type="text" name="questionID" value="${value.getQuestionID()}" readonly></td>
					<td><input type="text" name="questionContent" value="${value.getQuestionContent()}" readonly></td>
					<td><input type="text" name="optionA" value="${value.getOptionA()}" readonly></td>
					<td><input type="text" name="optionB" value="${value.getOptionB()}" readonly></td>
					<td><input type="text" name="optionC" value="${value.getOptionC()}" readonly></td>
					<td><input type="text" name="optionD" value="${value.getOptionD()}" readonly></td>
					<td><input type="text" name="result" value="${value.getResult()}" readonly></td>
					<td><input type="text" name="date" value="${value.getDate()}" readonly></td>
					<td><input type="text" name="path" value="${value.getPath()}" readonly></td>
					
					<td><input type="button" value="Xoá" onclick="deleteExerciseBasedId(${value.getExerciseID()})"></td>
					
				</tr>
			</c:forEach>
		</table>
		<br>
		<p><%=request.getAttribute("msgDatabaseDelete") != null? request.getAttribute("msgDatabaseDelete") : "" %></p>
	</div>
		
		<!-- Script -->
		
		<script>
			function deleteExerciseBasedId(ID) {
				var xhttp = new XMLHttpRequest();
				var exerciseID = ID;
				var exerciseName = '<%=request.getAttribute("listeningExerciseName")%>';
				var url = "DeleteListeningExerciseController?exerciseID=" + exerciseID + "&exerciseName=" + exerciseName;
				xhttp.onreadystatechange = function() {
					if(this.readyState == 4 && this.status == 200) {
						document.getElementById("response-ajax").innerHTML = this.responseText;
					}
				};
				xhttp.open("POST", url, true);
				xhttp.send();
			}
		</script>

</body>
</html>
