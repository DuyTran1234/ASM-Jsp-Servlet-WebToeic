<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="./bootstrap4/js/jquery.min.js"></script>
<style>
	table, tr, td{
	  border: 1px solid black;
	}
</style>
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
                                                    .append(
                                                    		'<tr>'
                                                    	  + '<td><p>Tên chủ đề bài tập:</p><input type="text" name="lessionID[' + fileIndex + ']"></td>'		
                                                    	  + '</tr>'
                                                          + '<tr>'
                                                          + '   <td><input type="file" name="files['+ fileIndex +']" /></td>'
                                                          + '</tr>');
                                        });
 
                    });
</script>
<title>Quản lý bài tập đọc</title>
</head>
<body>
	<jsp:include page="/View/Header.jsp"></jsp:include>
	<br><br><br><br><br>
	
	<div class="site-wrap">
		<div class="site-section">
			<div class="container">
            	<div class="row justify-content-center">           		
                	<div class="col-md-5">
                		<div class="row">
                		             		
                			<div class="col-md-12 form-group">
                				<div id="result-pagination"></div> 								
	 							<br>
                				<div class="col-md-12 form-group">
	 								<ul class="pagination" id="pagination"></ul>
	 							</div>
                			</div>
                		               	
                		    <div class="col-md-12 form-group">
                				<form action="InsertReadingExerciseController" method="POST" enctype="multipart/form-data">
                					<h5>Thêm file bài tập đọc (*Chỉ sử dụng file .xls hoặc .xlsx)</h5>
                					<table id="fileTable">
						            	<tr>
						            		<td><p>Tên chủ đề bài tập:</p><input type="text" name="lessionID[0]"></td>
						            	</tr>
						                <tr>
						                    <td><input name="files[0]" type="file" /></td>
						                </tr>					                
						            </table>  
						            <br/>
						            <input id="addFile" type="button" value="Add File"/><br><br>
						            <input type="submit" value="Upload" class="btn btn-primary btn-lg px-5"><br>
						            <p style="color:red"><%=request.getAttribute("msgInsertReadingExercise")!=null? request.getAttribute("msgInsertReadingExercise") : "" %></p>       				
                				</form>
                			</div>	


                			
                		</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Script -->
	<script src="./bootstrap4/js/jquery.min.js"></script>
    <script src="./bootstrap4/js/bootstrap.min.js"></script>
    <script src="./bootstrap4/js/jquery.twbsPagination.js" type="text/javascript"></script>
	 
	 
	<script type="text/javascript">
    $(function () {
    	var listValue = <%=request.getAttribute("listJSON-value")%>;
    	var listName = <%=request.getAttribute("listJSON-name")%>;
    	var total = listName.length;
    	   	
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: total,
            visiblePages: 3,
            onPageClick: function (event, page) {
            	var result = "<table>" + "<tr>" + "<th>ExerciseID</th>" + "<th>ExerciseName</th>" + "<th>QuesionID</th>" + "<th>QuestionContent</th>" + "<th>OptionA</th>" + "<th>OptionB</th>" + "<th>OptionC</th>" + "<th>OptionD</th>" + "<th>result</th>" + "<th>date</th>" + "</tr>";
            	for(var i = 0; i < listValue.length; i++) {
            		if(listName[page] == listValue[i].exerciseName) {
            			result = result + "<tr>" + "<td>" + list[i].exerciseID + "</td>"+ "<td>" + list[i].exerciseName + "</td>"+ "<td>" + list[i].questionID + "</td>"+ "<td>" + list[i + "</td>" + "<td>" + list[i].optionA + "</td>" + "<td>" + list[i].optionB + "</td>" + "<td>" + list[i].optionC + "</td>" + "<td>" + list[i].optionD + "</td>" + "<td>" + list[i].result + "</td>" + "<td>" + list[i].date + "</td>" + "</tr>";                		
            		}
            	}
            	result = result + "</table>";
         		document.getElementById("result-pagination").innerHTML = result;
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });
	</script>
	
	
	<jsp:include page="/View/Footer.jsp"></jsp:include>
</body>
</html>