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
	<meta name="keywords" content=""
	/>
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
		<form action="user/dologin" id="form-login" method="post">
			<h2>来登录一哈
				<i class="fas fa-level-down-alt"></i>
			</h2>
			<div class="form-style-agile">
				<label>
					<i class="fas fa-user"></i>
					用户名
				</label>
				<input placeholder="Username" name="userCode" type="text" required="">
			</div>
			<div class="form-style-agile">
				<label>
					<i class="fas fa-unlock-alt"></i>
					密码
				</label>
				<input placeholder="Password" name="userPass" type="password" required="">
			</div>
			<div class="form-style-agile">
				<label>
					<i class="fas fa-unlock-alt"></i>
					验证码（忽略大小写）
				</label>
				<img src="check/code" onclick="this.src='check/code'"/>
				<input placeholder="验证码" id="code" type="text" required="">
			</div>
			<!-- checkbox -->
			<div class="wthree-text">
				<ul>
					<li>
						<label class="anim">
							<input type="checkbox" class="checkbox" name="remember" value="1">
							<span>记住密码</span>
						</label>
					</li>
					<li>
						<a >忘记密码没办法！！！</a>
					</li>
				</ul>
			</div>
			<!-- //checkbox -->
			<input type="submit" id="login" value="登录">
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
<script type="text/javascript">
$(document).ready(function(){
	$('#code').change(function(){
		var code = $('#code').val();
		$.ajax({
			type:'post',
	 		url : 'user/code/' + code,
	 		success : function(result) {
	 			if(result==1) {
	 				/* 验证码正确 */
	 			} else {
	 				alert("验证码错误");
	 				$('#code').val('');
	 			}
	 		}
	 	});
	});
});
</script>
</html>