<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Display Listening Exercise</title>
	<style>
        table, tr, td, th{
          border: 1px solid black;
          max-width: 300px;
          word-wrap: break-word
        }
    </style>
</head>
<body>
	
	<form action="DisplayListeningExerciseForward" method="post">
		<input type="submit" value="Refresh"><br><br>
	</form>
	
	<div id="result-exercise-pagination"></div>
	<br>
	
	<div>
		<ul class="pagination" id="pagination"></ul>
	</div>
	

	<!-- Script -->	
	<link href="./bootstrap4/css/bootstrap.min.css" rel="stylesheet">
    <script src="./bootstrap4/js/jquery.min.js"></script>
    <script src="./bootstrap4/js/bootstrap.min.js"></script>
    <script src="./bootstrap4/js/jquery.twbsPagination.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $(function () {
	    	var list = <%=request.getAttribute("list-listening-exercise")%>;
	    	var total = list.length;
	    	total = Math.ceil(total/3);
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: total,
	            visiblePages: 3,
	            onPageClick: function (event, page) {
	            	var result = "<table>" + "<tr>" + "<th>exerciseID</th>" + "<th>exerciseName</th>" + "<th>questionID</th>"+ "<th>questionContent</th>" + "<th>optionA</th>" + "<th>optionB</th>" + "<th>optionC</th>" + "<th>optionD</th>" + "<th>result</th>" + "<th>date</th>" + "<th>path</th>" + "</tr>";
	            	for(var i = (page - 1) * 3; i < (page - 1) * 3 + 3; i++) {
	            		result = result + "<tr>" 
		            	+ "<td>" + list[i].exerciseID + "</td>"
		            	+ "<td>" + list[i].exerciseName + "</td>"
		            	+ "<td>" + list[i].questionID + "</td>"
		            	+ "<td>" + list[i].questionContent + "</td>" 
		            	+ "<td>" + list[i].optionA + "</td>" 
		            	+ "<td>" + list[i].optionB + "</td>" 
		            	+ "<td>" + list[i].optionC + "</td>" 
		            	+ "<td>" + list[i].optionD + "</td>" 
		            	+ "<td>" + list[i].result + "</td>" 
		            	+ "<td>" + list[i].date + "</td>"
		            	+ "<td>" + list[i].path + "</td>"
		            	+ "</tr>";
		            	if((i + 1) == list.length) {
		            		break;
		            	}
	            	}
	         		result = result + "</table>";
	         	document.getElementById("result-exercise-pagination").innerHTML = result;
	            }
	        }).on('page', function (event, page) {
	            console.info(page + ' (from event listening)');
	        });
	    });
	</script>
	
</body>
</html>