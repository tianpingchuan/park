<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>注册</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="assert/layui/css/layui.css" media="all">
<link rel="stylesheet" href="assert/style/admin.css" media="all">
<link rel="stylesheet" href="assert/style/login.css" media="all">

<link href="assert/vendor/jquery-validation/validationEngine.jquery.css" rel="stylesheet">
</head>
<body>

<form id="form_login">
	<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
		<div class="layadmin-user-login-main">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<h2>注册</h2>
			</div>
			<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-nickname"></label> 
					<input type="text" name="userName" id="userName" lay-verify="required" placeholder="用户名称" class="layui-input validate[required,ajax[ajaxUserName]]">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-nickname"></label> 
					<input type="text" name="userCode" id="userCode" lay-verify="required" placeholder="用户账号" class="layui-input validate[required,ajax[ajaxUserCode]]">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="LAY-user-login-cellphone"></label> 
					<input type="text" name="userPhone" id="userPhone" lay-verify="required|phone" placeholder="手机号码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label> 
					<input type="password" name="userPass" id="userPass" lay-verify="pass" placeholder="密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-repass"></label> 
					<input type="password" name="repass" id="LAY-user-login-repass" lay-verify="required" placeholder="确认密码" class="layui-input">
				</div>
				<div class="layui-form-item">
					<button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit">注 册</button>
				</div>
				<div class="layui-trans layui-form-item layadmin-user-login-other">
					<a href="gologin" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
				</div>

			</div>
		</div>

	</div>
</form>
	<script src="assert/layui/layui.js"></script>
	
	<script src="assert/vendor/jquery/jquery.min.js"></script>
	
	<script src="assert/vendor/jquery-validation/jquery.validationEngine-zh_CN.js"></script>
	<script src="assert/vendor/jquery-validation/jquery.validationEngine.js"></script>
	
	<script>
	$(document).ready(function(){
//		对表单绑定校验
		$('#form_login').validationEngine('attach',{
			onValidationComplete:function(form,status) {
			}
		});
	});
	
		layui.use([ 'layer','form' ], function() {
			// layui是基于jQuery的,启用$符号。
			var $ = layui.$;
			var form = layui.form;
			var layer = layui.layer;
			form.render();

			//提交
			form.on('submit(LAY-user-reg-submit)', function(obj) {
				var field = obj.field;
				//确认密码
				if (field.userPass !== field.repass) {
					return layer.msg('两次密码输入不一致');
				}
				$.ajax({
					type:'post',
					url:'user/add2',
					data:$('#form_login').serialize(),
					dataType:'json',
					success:function(result){
						if(result){
							layer.msg('注册成功！');
						}
					}
				});

				return false;
			});
		});
	</script>
</body>
</html>