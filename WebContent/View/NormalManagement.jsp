<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý tài khoản</title>

</head>
<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
	<jsp:include page="Header.jsp"></jsp:include>
	<br><br><br><br><br>
	<div class="site-wrap">
		<div class="site-section">
        	<div class="container">
            	<div class="row justify-content-center">
                	<div class="col-md-5">
                		<form action="NormalManagementController" method="post">
                    	<div class="row">
                        
	                          <div class="col-md-12 form-group">
	                            <label for="username">Full Name</label>
	                            <input type="text" name="fullname" class="form-control form-control-lg" value="${sessionUser.getFullName()}">
	                          </div>
	                          <div class="col-md-12 form-group">
	                            <label for="email">Email</label>
	                            <input type="email" name="email" class="form-control form-control-lg" value="${sessionUser.getEmail()}" readonly="true">
	                          </div>
	                          <div class="col-md-12 form-group">
	                            <label>Phone Number</label>
	                            <input type="text" name="phone-number" class="form-control form-control-lg" value="${sessionUser.getPhoneNumber()}">
	                          </div>
	                          <div class="col-md-12 form-group">
	                            <label>Address</label>
	                            <input type="text" name="address" class="form-control form-control-lg" value="${sessionUser.getAddress()}">
	                          </div>
                        </div>
                        <div class="row">
	                        <div class="col-12">
	                        	<p style="color:red"><%=request.getAttribute("msgUpdate")!=null? request.getAttribute("msgUpdate") : "" %></p>
	                            <input type="submit" value="Update Info" class="btn btn-primary btn-lg px-5">
	                        </div>
                        </div>
                        </form>
                        <br><br>
                        <form action="UpdatePasswordController" method="post">
                    	<div class="row">
                        
	                          <div class="col-md-12 form-group">
	                            <label for="username">Mật khẩu hiện tại</label>
	                            <input type="password" name="present-password" class="form-control form-control-lg">
	                          </div>
	                          <div class="col-md-12 form-group">
	                            <label for="email">Mật khẩu mới</label>
	                            <input type="password" name="new-password" class="form-control form-control-lg">
	                          </div>
	                          <div class="col-md-12 form-group">
	                            <label for="email">Xác nhận mật khẩu mới</label>
	                            <input type="password" name="confirm-password" class="form-control form-control-lg">
	                          </div>
                        </div>
                        <div class="row">
	                        <div class="col-12">
	                        	<p style="color:red"><%=request.getAttribute("msgUpdatePassword")!=null? request.getAttribute("msgUpdatePassword") : "" %></p>
	                            <input type="submit" value="Update Password" class="btn btn-primary btn-lg px-5">
	                        </div>
                        </div>
                        </form>
                        
                    </div>
                	</div>
            	</div> 
        	</div>
    	</div>
	
	</div>
	
	<jsp:include page="Footer.jsp"></jsp:include>
	
	<!-- Script tag -->
</body>
</html>