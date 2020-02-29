<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>平川商城后台</title>
<jsp:include page="/base.jsp"></jsp:include>
<!-- Custom fonts for this template-->
<link href="assert/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<!-- Page level plugin CSS-->
<link href="assert/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

<link href="assert/vendor/jquery-validation/validationEngine.jquery.css" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="assert/css/sb-admin.css" rel="stylesheet">

<!-- http://www.jq22.com/demo/jquery-treetable201707260025/  -->
<!-- 树形结构 -->
<link href="assert/vendor/jquery-treetable/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
<link href="assert/vendor/jquery-treetable/css/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css" />

<!-- include libraries(jQuery, bootstrap) -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link href="assert/vendor/summernote/summernote-bs4.css" rel="stylesheet">

</head>
<body id="page-top">
	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
		<a class="navbar-brand mr-1" href="index.jsp">商城</a>
		<button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>

		<!-- Navbar Search -->
		<form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
				<div class="input-group-append">
					<button class="btn btn-primary" type="button">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</form>

		<!-- Navbar -->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown no-arrow mx-1"><a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fas fa-bell fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown">
					<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
			<li class="nav-item dropdown no-arrow mx-1"><a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="messagesDropdown">
					<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
				
				<c:if test="${!empty user}">
				<li>
					<button>管理员：${user.userName}</button>
				</li>
				</c:if>
				<li>
					<a class="dropdown-item" href="user/loginout">退出登录</a>
				</li>
			<li class="nav-item dropdown no-arrow"><a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right" id="left_login" aria-labelledby="userDropdown" >
					<a class="dropdown-item" href="user/loginin">管理员注册</a>
					<div class="dropdown-divider"></div>
				</div></li>
		</ul>

	</nav>

	<div id="wrapper">
		<!-- 左侧 菜单开始  -->
		<!-- Sidebar -->
		<ul class="sidebar navbar-nav" id="left_menu">
			
			<li class="nav-item"><a class="nav-link" href="user/index"> <i class="fas fa-fw fa-table"></i> <span>用户管理</span></a></li>
			<li class="nav-item"><a class="nav-link" href="stall/index"> <i class="fas fa-fw fa-table"></i> <span>停车位管理</span></a></li>
			
		</ul>
		<!-- 左侧 菜单结束  -->
		<!-- 主页面开始  -->
		<div id="content-wrapper">
			<div class="container-fluid" id="container-admin">
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
					<li class="breadcrumb-item active">Overview</li>
				</ol>

				<!-- Icon Cards-->
				<div class="row">
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-primary o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-comments"></i>
								</div>
								<div class="mr-5">26 New Messages!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#"> <span class="float-left">View Details</span> <span class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-warning o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-list"></i>
								</div>
								<div class="mr-5">11 New Tasks!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#"> <span class="float-left">View Details</span> <span class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-success o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-shopping-cart"></i>
								</div>
								<div class="mr-5">123 New Orders!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#"> <span class="float-left">View Details</span> <span class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
					<div class="col-xl-3 col-sm-6 mb-3">
						<div class="card text-white bg-danger o-hidden h-100">
							<div class="card-body">
								<div class="card-body-icon">
									<i class="fas fa-fw fa-life-ring"></i>
								</div>
								<div class="mr-5">13 New Tickets!</div>
							</div>
							<a class="card-footer text-white clearfix small z-1" href="#"> <span class="float-left">View Details</span> <span class="float-right"> <i class="fas fa-angle-right"></i>
							</span>
							</a>
						</div>
					</div>
				</div>

				<!-- Area Chart Example-->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-chart-area"></i> Area Chart Example
					</div>
					<div class="card-body">
						<canvas id="myAreaChart" width="100%" height="30"></canvas>
					</div>
					<div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
				</div>


			<!-- Sticky Footer -->
			<footer class="sticky-footer">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright © Your Website 2019</span>
					</div>
				</div>
			</footer>

		</div>
		<!-- /.content-wrapper -->
		<!-- 主页面结束  -->
	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<script src="assert/vendor/jquery/jquery.min.js"></script>
	<script src="assert/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assert/vendor/jquery.blockui.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="assert/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script src="assert/vendor/chart.js/Chart.min.js"></script>
	<script src="assert/vendor/datatables/jquery.dataTables.js"></script>
	<script src="assert/vendor/datatables/dataTables.bootstrap4.js"></script>
	
	<script src="assert/vendor/jquery-validation/jquery.validationEngine-zh_CN.js"></script>
	<script src="assert/vendor/jquery-validation/jquery.validationEngine.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="assert/js/sb-admin.min.js"></script>
	<script type="text/javascript" src="assert/js/eoa-index.js"></script>
	<!-- Demo scripts for this page-->
	<script src="assert/js/demo/datatables-demo.js"></script>
	<script src="assert/js/demo/chart-area-demo.js"></script>
</body>

<script type="text/javascript" src="assert/vendor/jquery-treetable/js/jquery.treetable.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<script src="assert/vendor/summernote/summernote-bs4.js"></script>
<script src="assert/vendor/summernote/lang/summernote-zh-CN.js"></script>
</html>
