<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý bài tập ngữ pháp</title>
<script src="./js/jquery-3.3.1.min.js"></script>

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
                                                    	  + '<td><p>Tên bài học:</p><input type="text" name="lessionID[' + fileIndex + ']"></td>'		
                                                    	  + '</tr>'
                                                          + '<tr>'
                                                          + '   <td><input type="file" name="files['+ fileIndex +']" /></td>'
                                                          + '</tr>');
                                        });
 
                    });
</script>
</head>
<body>
	<jsp:include page="/View/Header.jsp"/>
	<br><br><br><br>
        
	<div class="site-wrap">
		<div class="site-section">
			<div class="container">
            	<div class="row justify-content-center">
                	<div class="col-md-5">
                		<div class="row">
	                		<form method="POST" action="UploadGrammarLession" enctype="multipart/form-data" accept-charset="UTF-8">
	 							
	 							<div class="col-md-12 form-group">
						            <p>Thêm file bài học ngữ pháp (*Chỉ sử dụng file .docx)</p>
						 
						            <table id="fileTable">
						            	<tr>
						            		<td><p>Tên bài học:</p><input type="text" name="lessionID[0]"></td>
						            	</tr>
						                <tr>
						                    <td><input name="files[0]" type="file" /></td>
						                </tr>
						            </table>
						            <br />
						            <input id="addFile" type="button" value="Add File" />
					            </div>
					            <br>		  					            
					            <div class="col-12">		
					            	<p style="color:red"><%=request.getAttribute("msgUploadFile")!=null? request.getAttribute("msgUploadFile") : "" %></p>		            	
		                      		<input type="submit" value="Upload" class="btn btn-primary btn-lg px-5">	
	                       		</div>					           
					        </form>		
					        			        					  	        
                		</div>
                	</div>
                </div>
                <div class="col-md-12 form-group">				    	
					<pre style="white-space: pre-wrap;align-text:center;font-size:20px"><%=request.getAttribute("Content")!=null ? request.getAttribute("Content") : "" %></pre>
				</div>
            </div>
            
		</div>
	</div>
	
	
	
	<jsp:include page="/View/Footer.jsp"/>
</body>
</html>