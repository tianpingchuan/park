<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zxx">
<head>
	<title>后台登录页面</title>
	<jsp:include page="/base.jsp"></jsp:include>
	<!-- Meta tag Keywords -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8" />
	<meta name="keywords" content=""/>
	<script>
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<!-- Meta tag Keywords -->
	<!-- css files -->
	<link rel="stylesheet" href="assert/css/style-login.css" type="text/css" media="all" />
	<!-- Style-CSS -->
	<link rel="stylesheet" href="assert/css/fontawesome-all.css">
	<!-- Font-Awesome-Icons-CSS -->
	<!-- //css files -->
	<!-- web-fonts -->
	<!-- <link href="http://maxcdn.bootstrapcdn.com/css?family=Josefin+Sans:100,100i,300,300i,400,400i,600,600i,700,700i" rel="stylesheet">
	<link href="http://maxcdn.bootstrapcdn.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet"> -->
	<!-- //web-fonts -->
</head>

<body>
	<!-- bg effect -->
	<div id="bg">
		<canvas></canvas>
		<canvas></canvas>
		<canvas></canvas>
	</div>
	<!-- //bg effect -->
	<!-- title -->
	<h1>欢迎来到平川帝国后台</h1>
	<!-- //title -->
	<!-- content -->
	<div class="sub-main-w3">
		<form>
			<h2>没有这个账号，请重新登录</h2><br/>
			<div class="form-style-agile">
				<a href="user/gologin">返回首页</a>
			</div>
		</form>
	</div>
	<!-- //content -->

	<!-- copyright -->
	<div class="footer">
		<p>平川商城 &copy;一切解释权归平川所有</p>
	</div>
	<!-- //copyright -->

	<!-- Jquery -->
	<script src="assert/js/jquery-3.3.1.min.js"></script>
	<!-- //Jquery -->

	<!-- effect js -->
	<script src="assert/js/canva_moving_effect.js"></script>
	<!-- //effect js -->

</body>
</html>