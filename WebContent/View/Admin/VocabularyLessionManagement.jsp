<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	table, tr, td{
	  border: 1px solid black;
	}
</style>
<meta charset="UTF-8">
<title>Quản lý bài tập từ vựng</title>
<script src="./bootstrap4/js/jquery.min.js"></script>
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
	<jsp:include page="/View/Header.jsp"></jsp:include>
	<br><br><br><br><br>
	
	<div class="site-wrap">
		<div class="site-section">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-5">
						<div class="row">
							<div class="col-md-12 form-group">
	 								<div id="result-pagination">
	 								</div>
	 								<br>
	 								<div class="col-md-12 form-group">
	 									<ul class="pagination" id="pagination"></ul>
	 								</div>
	 							</div>
							<div class="col-md-12 form-group">
								<form action="InsertVocabularyGrammarController" method="POST" enctype="multipart/form-data">
									<h5>Thêm file bài học từ vựng (*Chỉ sử dụng file .docx)</h5>									
										<table id="fileTable">
											<tr>	
												<td><p>Tên bài học:</p><input type="text" name="lessionID[0]"></td>
											</tr>
											<tr>
						                    	<td><input name="files[0]" type="file" /></td>
						               		</tr>
										</table><br>
										<input id="addFile" type="button" value="Add File" /><br><br>
									<input type="submit" value="Upload" class="btn btn-primary btn-lg px-5">					
								</form>
								<p style="color:red"><%=request.getAttribute("msgInsertVocabulary") != null? request.getAttribute("msgInsertVocabulary") : "" %></p>	
							</div>
							<div class="col-md-12 form-group">
								<h5>Nhập tên bài học cần sửa: </h5>
								<input type="text" id="lession-name-ajax">
								<br><br>
								<input type="button" class="btn btn-primary btn-lg px-5" value="Tìm" onclick="ajaxFindLession()">
								
							</div>
							<div id="result-ajax"  class="col-md-12 form-group">
								
							</div>
							<div class="col-md-12 form-group">
								<p style="color:red"><%=request.getAttribute("msgUpdateVocabulary")!=null? request.getAttribute("msgUpdateVocabulary") : "" %></p>
							</div>
							
							<div class="col-md-12 form-group">
								<p>Nhập tên bài học cần xoá:</p>
								<input type="text" id="lession-name-delete"><br><br>
								<input type="button" class="btn btn-primary btn-lg px-5" value="Xoá bài học" onclick="ajaxDeleteLession()">
							</div>
							<div class="col-md-12 form-group">
								<div id="result-ajax-delete"></div> 
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- SCRIPT -->
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
		function ajaxFindLession() {
			var xhttp = new XMLHttpRequest();
			var lessionName = document.getElementById("lession-name-ajax").value;
			var url = "UpdateVocabularyLessionForward?lessionName=" + lessionName;
			xhttp.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					document.getElementById("result-ajax").innerHTML = xhttp.responseText;
				}
			};
			xhttp.open("POST", url, true);
			xhttp.send();
		}
	</script>
	
	<script>
		function ajaxDeleteLession() {
			var xhttp = new XMLHttpRequest();
			var lessionName = document.getElementById("lession-name-delete").value;
			var url = "DeleteVocabularyLessionController?lessionName=" + lessionName;
			xhttp.onreadystatechange = function() {
				if(this.readyState == 4 && this.status == 200) {
					document.getElementById("result-ajax-delete").innerHTML = xhttp.responseText;
				}
			};
			xhttp.open("POST", url, true);
			xhttp.send();
		}
	</script>

	<jsp:include page="/View/Footer.jsp"></jsp:include>
</body>
</html>