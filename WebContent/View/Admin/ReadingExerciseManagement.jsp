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
                				
                			</div>
                		               	
                		    <div class="col-md-12 form-group">
                				<form action="InsertReadingExerciseController" method="POST" enctype="multipart/form-data">
                					<h5>Thêm file bài tập đọc (*Chỉ sử dụng file .xls)</h5>
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
						            <input type="submit" value="Upload" class="btn btn-primary btn-lg px-5">          				
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
	
	<jsp:include page="/View/Footer.jsp"></jsp:include>
</body>
</html>