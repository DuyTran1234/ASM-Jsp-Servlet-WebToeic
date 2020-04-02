<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./bootstrap4/css/bootstrap.min.css" rel="stylesheet">
<style>
       table, tr, th, td{
          border: 1px solid black;
          max-width: 138px;
          max-height: 50px;
          word-wrap: break-word
        }
</style>
<title>Danh sách đề thi thử TOEIC</title>
</head>
<body>
	<form action="DisplayTestToeicForward" method="post">
		<input type="submit" value="Refresh">
	</form>
	<p style="color:red"><%=request.getAttribute("msgDisplay")!=null?request.getAttribute("msgDisplay"):"" %></p>
	<div id="list-toeic-pagination"></div><br>
	<div>
		<ul class="pagination" id="pagination"></ul>
	</div>
	<!-- Script -->
	<script src="./bootstrap4/js/jquery.min.js"></script>
	<script src="./bootstrap4/js/bootstrap.min.js"></script>
	<script src="./bootstrap4/js/jquery.twbsPagination.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	    $(function () {
	    	var list = <%=request.getAttribute("list-test-toeic-JSON")%>;
	    	var total = list.length;
	    	total = Math.ceil(total/5);
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: total,
	            visiblePages: 3,
	            onPageClick: function (event, page) {
	            	var result = "<table>" + "<tr>" + "<th>id</th>" + "<th>testToeicName</th>" + "<th>questionID</th>"+ "<th>questionContent</th>" + "<th>optionA</th>" + "<th>optionB</th>" + "<th>optionC</th>" + "<th>optionD</th>" + "<th>result</th>" + "<th>date</th>" + "<th>path</th>" + "</tr>";
	            	for(var i = (page - 1) * 5; i < (page - 1) * 5 + 5; i++) {
	            		result = result + "<tr>" 
		            	+ "<td>" + list[i].id + "</td>"
		            	+ "<td>" + list[i].testToeicName + "</td>"
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
	         	document.getElementById("list-toeic-pagination").innerHTML = result;
	            }
	        }).on('page', function (event, page) {
	            console.info(page + ' (from event listening)');
	        });
	    });
	</script>
</body>
</html>