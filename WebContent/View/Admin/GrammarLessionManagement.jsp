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
                                                            '<tr><td>'
                                                                    + '   <input type="file" name="files['+ fileIndex +']" />'
                                                                    + '</td></tr>');
                                        });
 
                    });
</script>
</head>
<body>
	<jsp:include page="/View/Header.jsp"/>
	<br><br><br><br><br>
        
	<div class="site-wrap">
		<div class="site-section">
			<div class="container">
            	<div class="row justify-content-center">
                	<div class="col-md-5">
                		<div class="row">
	                		<form method="post" action="" enctype="multipart/form-data">
	 
					            <p>Thêm file bài học ngữ pháp</p>
					 
					            <table id="fileTable">
					                <tr>
					                    <td><input name="files[0]" type="file" /></td>
					                </tr>
					            </table>
					            <br />
					            <input type="submit" value="Upload" />
					            <input id="addFile" type="button" value="Add File" />
					        </form>
                		</div>
                	</div>
                </div>
            </div>
		</div>
	</div>
	
	
	
	<jsp:include page="/View/Footer.jsp"/>
</body>
</html>