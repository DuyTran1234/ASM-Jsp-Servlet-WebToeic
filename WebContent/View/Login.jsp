<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

  <div class="site-wrap">

    <jsp:include page="Header.jsp"></jsp:include>
    <!--Ngan cach header-->
    <br><br><br><br><br>
    <div class="site-section ftco-subscribe-1 site-blocks-cover pb-4" style="background-image: url('images/bg_1.jpg')">
        <div class="container">
          <div class="row align-items-end justify-content-center text-center">
            <div class="col-lg-7">
              <h2 class="mb-0">Login</h2>
              <p>Đăng nhập</p>
            </div>
          </div>
        </div>
      </div> 
    
    <div class="custom-breadcrumns border-bottom">
      <div class="container">
        <a href="HomeForward">Home</a>
        <span class="mx-3 icon-keyboard_arrow_right"></span>
        <span class="current">Login</span>
      </div>
    </div>

    <div class="site-section">
        <div class="container">
			<form action="LoginController" method="POST">
            <div class="row justify-content-center">
                <div class="col-md-5">
                    
                        <div class="row">
                          <div class="col-md-12 form-group">
                              <label for="username">Username</label>
                              <input type="text" name="username" class="form-control form-control-lg">
                          </div>
                          <div class="col-md-12 form-group">
                              <label for="pword">Password</label>
                              <input type="password" name="pword" class="form-control form-control-lg">
                          </div>
                        </div>
	                        <p style="color:red">
	                        	<%=request.getAttribute("msgLogin") != null ? request.getAttribute("msgLogin") : "" %>
	                        </p>
                      <div class="row">
                          <div class="col-12">
                              <input type="submit" value="Log In" class="btn btn-primary btn-lg px-5">
                          </div>
                      </div>
                    
                </div>
            </div>
            </form>
        </div>
    </div>

    <jsp:include page="Footer.jsp"></jsp:include>

  </div>
  <!-- .site-wrap -->

  <!-- loader -->
</body>

</html>