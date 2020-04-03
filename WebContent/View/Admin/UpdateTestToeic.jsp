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
          max-width: 130px;
          max-height: 50px;
          word-wrap: break-word
        }
        input {
        	max-width: 121px;
          	max-height: 50px;
          	word-wrap: break-word
        }
</style>
<title>Cập nhật đề thi</title>
<script>
	function deleteQuestionTestToeic(id) {
		var xhttp = new XMLHttpRequest();
		var testToeicName = '<%=request.getAttribute("testToeicName")%>' ;
		var url = "DeleteTestToeicAjaxController?id=" + id + "&testToeicName=" + testToeicName;
		xhttp.onreadystatechange = function() {
			if(this.readyState == 4 && this.status == 200) {
				document.getElementById("result-ajax").innerHTML = this.responseText;
			}
		};
		xhttp.open("POST", url, true);
		xhttp.send();
	}
</script>
</head>
<body>
	<div id="result-ajax">
		<h5><%=request.getAttribute("testToeicName")%></h5>
		<table>
			<tr>
				<th></th>
				<th>id</th>
				<th>testToeicName</th>
				<th>questionID</th>
				<th>questionContent</th>
				<th>optionA</th>
				<th>optionB</th>
				<th>optionC</th>
				<th>optionD</th>
				<th>result</th>
				<th>date</th>
				<th>path</th>
				<th>File</th>		
			</tr>
			<c:forEach items="${listTestToeic}" var="value">
				<tr>
					<td><input type="button" value="Xoá" onclick="deleteQuestionTestToeic(${value.getId()})"></td>
					<td><input type="text" value="${value.getId()}" readonly></td>
					<td><input type="text" value="${value.getTestToeicName()}" readonly></td>
					<td><input type="text" value="${value.getQuestionID()}"></td>
					<td><input type="text" value="${value.getQuestionContent()}"></td>
					<td><input type="text" value="${value.getOptionA()}"></td>
					<td><input type="text" value="${value.getOptionB()}"></td>
					<td><input type="text" value="${value.getOptionC()}"></td>
					<td><input type="text" value="${value.getOptionD()}"></td>
					<td><input type="text" value="${value.getResult()}"></td>
					<td><input type="text" value="${value.getDate()}" readonly></td>
					<td><input type="text" value="${value.getPath()}" readonly></td>
					<td><input type="file"></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>