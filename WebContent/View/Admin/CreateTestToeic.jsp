<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo đề thi thử Toeic</title>
<script src="./bootstrap4/js/jquery.min.js"></script>
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
</head>
<body>
	<form action="CreateTestToeicController" method="post" enctype="multipart/form-data">
		<div>
			<h3>Nhập tên bài tập:</h3>
			<input type="text" id="test-toeic-name" onkeyup="checkToeicName()" name="test-toeic-name-upload">
			<div id="check-toeic-name-ajax"></div><br><br>
			
			<table id="fileTable">
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
			</table><br>
			<input id="addFile" type="button" value="Add File" />
		</div>
		<br><br><br>
		<input type="submit" value="Tạo đề thi">
	</form>
	<p style="color:red"><%=request.getAttribute("msgDB")!=null?request.getAttribute("msgDB"):"" %></p>
	<p style="color:red"><%=request.getAttribute("msgPatternFile")!=null?request.getAttribute("msgPatternFile"):"" %></p>
	<p style="color:red"><%=request.getAttribute("msgWriteFile")!=null?request.getAttribute("msgWriteFile"):"" %></p>
	<p style="color:red"><%=request.getAttribute("msgTestToeicName")!=null?request.getAttribute("msgTestToeicName"):"" %></p>
	<p style="color:red"><%=request.getAttribute("msgFileUpload")!=null?request.getAttribute("msgFileUpload"):"" %></p>
	
	<!-- Script -->
	<script>
		function checkToeicName() {
			var xhttp = new XMLHttpRequest();
			var testToeicName = document.getElementById("test-toeic-name").value;
			var url = "CheckTestToeicNameAjax?testToeicName=" + testToeicName;
			if(testToeicName != "") {
				xhttp.onreadystatechange = function() {
					if(this.readyState == 4 && this.status == 200) {
						document.getElementById("check-toeic-name-ajax").innerHTML = this.responseText;
					}
				};
			}
			else {
				document.getElementById("check-toeic-name-ajax").innerHTML = "";
			}
			xhttp.open("POST", url, true);
			xhttp.send();
		}
	</script>
	
	<script>
    $(document)
            .ready(
                    function() {
                        //add more file components if Add is clicked
                        $('#addFile')
                                .click(
                                        function() {
                                            var fileIndex = $('#fileTable tr')
                                                    .children().length - 1;
                                            $('#fileTable')
                                                    .append('<tr>'
                                                    + '<td>' + '<input type="text" name="questionID">' + '</td>'
                                                    + '<td>' + '<input type="text" name="questionContent">' + '</td>'
                                                    + '<td>' + '<input type="text" name="optionA">' + '</td>'
                                                    + '<td>' + '<input type="text" name="optionB">' + '</td>'
                                                    + '<td>' + '<input type="text" name="optionC">' + '</td>'
                                                    + '<td>' + '<input type="text" name="optionD">' + '</td>'
                                                    + '<td>' + '<input type="text" name="result">' + '</td>'                                                  
                                                    + '<td>' + '<input type="file" name="path">' + '</td>'                                               
                                                    + '</tr>'
                                                    );
                                        });
 
                    });
	</script>
	
	
</body>
</html>