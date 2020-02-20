<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
    <%@page import = "BEAN.User" %>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <title>Academics</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


  <link href="https://fonts.googleapis.com/css?family=Muli:300,400,700,900" rel="stylesheet">
  <link rel="stylesheet" href="fonts/icomoon/style.css">

  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/jquery-ui.css">
  <link rel="stylesheet" href="css/owl.carousel.min.css">
  <link rel="stylesheet" href="css/owl.theme.default.min.css">
  <link rel="stylesheet" href="css/owl.theme.default.min.css">

  <link rel="stylesheet" href="css/jquery.fancybox.min.css">

  <link rel="stylesheet" href="css/bootstrap-datepicker.css">

  <link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">

  <link rel="stylesheet" href="css/aos.css">
  <link href="css/jquery.mb.YTPlayer.min.css" media="all" rel="stylesheet" type="text/css">

  <link rel="stylesheet" href="css/style.css">



</head>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

  <div class="site-wrap">

	    <div class="site-mobile-menu site-navbar-target">
	      <div class="site-mobile-menu-header">
	        <div class="site-mobile-menu-close mt-3">
	          <span class="icon-close2 js-menu-toggle"></span>
	        </div>
	      </div>
	      <div class="site-mobile-menu-body"></div>
	    </div>
	
	
	    <div class="py-2 bg-light">
	      <div class="container">
	        <div class="row align-items-center">
	          <div class="col-lg-9 d-none d-lg-block">
	            <a href="#" class="small mr-3"><span class="icon-question-circle-o mr-2"></span> Have a questions?</a> 
	            <a href="#" class="small mr-3"><span class="icon-phone2 mr-2"></span> 10 20 123 456</a> 
	            <a href="#" class="small mr-3"><span class="icon-envelope-o mr-2"></span> info@mydomain.com</a> 
	          </div>
	          <div class="col-lg-3 text-right">
	           	<c:if test="${sessionUser == null}">
	           		<a href="LoginForward" class="small mr-3"><span class="icon-unlock-alt"></span> Log In</a>
	            	<a href="RegisterForward" class="small btn btn-primary px-4 py-2 rounded-0"><span class="icon-users"></span> Register</a>
	           	</c:if>
	           	<c:if test="${sessionUser != null}">
	           		<c:if test="${sessionUser.getUserTypeID() == 2}">
	           			<a href="LogoutController" class="small mr-3"><span class="icon-unlock-alt"></span> Log Out</a>
	            		<a href="AccountManagementForward" class="small btn btn-primary px-4 py-2 rounded-0"><span class="icon-users"></span> ${sessionUser.getUserName()}</a>
	           		</c:if>
	           		<c:if test="${sessionUser.getUserTypeID() == 1}">
	           			<a href="LogoutController" class="small mr-3"><span class="icon-unlock-alt"></span> Log Out</a>
	            		<a href="AccountManagementForward" class="small btn btn-primary px-4 py-2 rounded-0"><span class="icon-users"></span> *Admin* ${sessionUser.getUserName()}</a>
	           		</c:if>
	           	</c:if>
	          </div>
	        </div>
	      </div>
	    </div>
	    <header class="site-navbar py-4 js-sticky-header site-navbar-target" role="banner">
	
	      <div class="container">
	        <div class="d-flex align-items-center">
	          <div class="site-logo">
	            <a href="HomeForward" class="d-block">
	              <img src="images/logo.jpg" alt="Image" class="img-fluid">
	            </a>
	          </div>
	          <div class="mr-auto">
	            <nav class="site-navigation position-relative text-right" role="navigation">
	              <ul class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block">
	                <li class="active">
	                  <a href="HomeForward" class="nav-link text-left">Trang chủ</a>
	                </li>
	                
	                <li>
	                  <a href="courses.html" class="nav-link text-left">Đề Toeic</a>
	                </li>
	                <li>
	                    <a href="ContactForward" class="nav-link text-left">Liên hệ</a>
	                  </li>
	              </ul>                                                                                                                                                                                                                                                                                          </ul>
	            </nav>
	          </div>
	          <div class="ml-auto">
	            <div class="social-wrap">
	              <a href="#"><span class="icon-facebook"></span></a>
	              <a href="#"><span class="icon-twitter"></span></a>
	              <a href="#"><span class="icon-linkedin"></span></a>
	
	              <a href="#" class="d-inline-block d-lg-none site-menu-toggle js-menu-toggle text-black"><span
	                class="icon-menu h3"></span></a>
	            </div>
	          </div>
	         
	        </div>
	      </div>
	
	    </header>
    <!-- Ngăn cách tiêu đề -->
	</div>
  <!-- .site-wrap -->


  <!-- loader -->
  <div id="loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#51be78"/></svg></div>

  <script src="js/jquery-3.3.1.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/jquery.countdown.min.js"></script>
  <script src="js/bootstrap-datepicker.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.fancybox.min.js"></script>
  <script src="js/jquery.sticky.js"></script>
  <script src="js/jquery.mb.YTPlayer.min.js"></script>
  <script src="js/main.js"></script>
</body>

</html>