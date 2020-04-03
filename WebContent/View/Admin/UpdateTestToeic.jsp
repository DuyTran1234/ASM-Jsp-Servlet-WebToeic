<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html id="result-ajax">
<head>
<meta charset="UTF-8">
<script src="./bootstrap4/js/jquery.min.js"></script>
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
		function tableAppend() {
			  var trElement = document.createElement("tr");
			  
			  // Create <td> questionID
			  var tdElementQuestionID = document.createElement("td");		  
			  var inputElementQuestionID = document.createElement("input");
			  inputElementQuestionID.type = "text";
			  inputElementQuestionID.name = "questionID";
			  tdElementQuestionID.appendChild(inputElementQuestionID);
			  
			  // Create <td> questionID
			  var tdElementQuestionContent = document.createElement("td");
			  var inputElementQuestionContent = document.createElement("input");
			  inputElementQuestionContent.type = "text";
			  inputElementQuestionContent.name = "questionContent";
			  tdElementQuestionContent.appendChild(inputElementQuestionContent);
			  
			  // Create <td> optionA
			  var tdElementOptionA = document.createElement("td");
			  var inputElementOptionA = document.createElement("input");
			  inputElementOptionA.type = "text";
			  inputElementOptionA.name = "optionA";
			  tdElementOptionA.appendChild(inputElementOptionA);
			  
			  // Create <td> optionB
			  var tdElementOptionB = document.createElement("td");
			  var inputElementOptionB = document.createElement("input");
			  inputElementOptionB.type = "text";
			  inputElementOptionB.name = "optionB";
			  tdElementOptionB.appendChild(inputElementOptionB);
			  
			  // Create <td> optionC
			  var tdElementOptionC = document.createElement("td");
			  var inputElementOptionC = document.createElement("input");
			  inputElementOptionC.type = "text";
			  inputElementOptionC.name = "optionC";
			  tdElementOptionC.appendChild(inputElementOptionC);
			  
			  // Create <td> optionD
			  var tdElementOptionD = document.createElement("td");
			  var inputElementOptionD = document.createElement("input");
			  inputElementOptionD.type = "text";
			  inputElementOptionD.name = "optionD";
			  tdElementOptionD.appendChild(inputElementOptionD);
			  
			  // Create <td> result		 
			  var tdElementResult = document.createElement("td");
			  var inputElementResult = document.createElement("input");
			  inputElementResult.type = "text";
			  inputElementResult.name = "result";
			  tdElementResult.appendChild(inputElementResult); 
			  
			  // Create <td> path
			  var tdElementPath = document.createElement("td");
			  var inputElementPath = document.createElement("input");
			  inputElementPath.type = "file";
			  inputElementPath.name = "path";
			  tdElementPath.appendChild(inputElementPath);
			  
			  // append <td> tags in <tr> tag
			  trElement.appendChild(tdElementQuestionID);
			  trElement.appendChild(tdElementQuestionContent);
			  trElement.appendChild(tdElementOptionA);
			  trElement.appendChild(tdElementOptionB);
			  trElement.appendChild(tdElementOptionC);
			  trElement.appendChild(tdElementOptionD);
			  trElement.appendChild(tdElementResult);
			  trElement.appendChild(tdElementPath);
			  
			  // append <table> has id fileTable
			  document.getElementById("fileTableInsert").appendChild(trElement);
		}
</script>
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
	<div>
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
		
		<h3>Thêm câu hỏi</h3>
		<form action="" method="POST">
			<table id="fileTableInsert">
				<tr>
					<th>questionID</th>
					<th>questionContent</th>
					<th>optionA</th>
					<th>optionB</th>
					<th>optionC</th>
					<th>optionD</th>
					<th>result</th>
					<th>path</th>	
				</tr>
			</table>	
		</form>
		<button onclick="tableAppend()">Add Question</button>
		<!-- Script -->
	</div>
</body>
</html>