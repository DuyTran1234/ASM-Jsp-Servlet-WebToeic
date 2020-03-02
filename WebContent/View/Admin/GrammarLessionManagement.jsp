<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý bài tập ngữ pháp</title>
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
	 								<div id="result-pagination">
	 								</div>
	 							</div>
	 							<div class="col-md-12 form-group">
	 								<ul class="pagination" id="pagination"></ul>
	 							</div>
	 							<br><br>
	 							<div class="col-md-12 form-group">
						            <h5>Thêm file bài học ngữ pháp (*Chỉ sử dụng file .docx)</h5>
						 
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
					       
							<br>
					        
					        
	 						<div class="col-md-12 form-group">
						    	<h5>Sửa bài học</h5>
						        <p>Nhập tên bài học cần sửa: </p>
								<input type="text" id="editLessionName"> 
					        </div>
					            <br>		  					            
					        <div class="col-12">		            	
		                      	<input type="button" value="Sửa" class="btn btn-primary btn-lg px-5" onclick="updateLessionAjax()">	
	                       		<br>
	                       		<div id="update-lession-ajax"></div>
	                       		<p><%=request.getAttribute("msgUpdateFile")!= null? request.getAttribute("msgUpdateFile") : "" %></p>
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
    	var list = <%=request.getAttribute("listJSON")%>;
    	var total = list.length;
    	
    	
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: total,
            visiblePages: 3,
            onPageClick: function (event, page) {
            	var result = "<table>" + "<tr>" + "<th>Lession Name</th>" + "<th>Content</th>" + "<th>Date</th>"+ "</tr>";
            	result = result + "<tr>" + "<td>" + list[page-1].lessionName + "</td>"+ "<td>" + list[page-1].content + "</td>"+ "<td>" + list[page-1].dateToday + "</td>"+ "</tr>";
         		result = result + "</table>"
         	document.getElementById("result-pagination").innerHTML = result;
            }
        }).on('page', function (event, page) {
            console.info(page + ' (from event listening)');
        });
    });
	</script>
	<script>
		function updateLessionAjax() {
			var lessionName = document.getElementById("editLessionName").value;
			var url = "UpdateGrammarLessionForward?lessionName=" + lessionName;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					document.getElementById("update-lession-ajax").innerHTML = xhttp.responseText;
				}
			};
			xhttp.open("POST", url, true);
			xhttp.send();
		}
	</script>

	
	<jsp:include page="/View/Footer.jsp"/>
</body>
</html>