<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>系统后台</title>
<jsp:include page="/base.jsp"></jsp:include>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<!-- 引入layui的样式表 -->
<link rel="stylesheet" href="assert/layui/css/layui.css" media="all">
<!-- 客户自定义的样式表 -->
<link rel="stylesheet" href="assert/pages/css/custom.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">停车系统</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<!-- <ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul> -->
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> 
				<img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img"> 
				${userBuyer.userName}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="loginout">退了</a></li>
			</ul>
		</div>
		<!-- 左侧菜单 开始 -->
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<div class="layui-logo">
					<span>停车系统</span>
				</div>

				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test" id="left_nav_tree">
					<li class="layui-nav-item"><a href="javascript:;">
							<i class="layui-icon layui-icon-home"></i>系统首页</a>
						<dl class="layui-nav-child">
							<dd>
								 <a href="stallclient"><i class="layui-icon layui-icon-user"></i>车位信息</a>
							</dd>
							<dd>
								<a href="car"><i class="layui-icon layui-icon-console"></i>取车</a>
							</dd>
							<dd>
								<a href="record"><i class="layui-icon layui-icon-auz"></i>停车记录</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>
		<!-- 左侧菜单 结束 -->
		<div class="layui-body" id="layui-body-main"></div>
	</div>
</body>
<!-- 引入layui的 开发脚本 -->
<script type="text/javascript" src="assert/layui/layui.js"></script>
<!-- 引入系统的重新的ajax脚本 -->
<script type="text/javascript" src="assert/pages/js/app_ajax.js"></script>
<!-- 引入系统的通用脚本  -->
<script type="text/javascript" src="assert/pages/js/app_core.js"></script>
<script>
	//此处引入treeTable的layui插件 次配置应该在项目中运行一次即可，不可以重复加载。
	layui.config({
		base : 'assert/layui/'//你存放新模块的目录，注意，不是layui的模块目录
	}).extend({
		treeTable : 'treeTable/treeTable',
		iconPicker : 'iconPicker/iconPicker'
	});
	//JavaScript代码区域
	layui.use([ 'element', 'form', 'layer' ], function() {
		var element = layui.element;
		var $ = layui.$;
		var form = layui.form;
		var layer = layui.layer;
		//绑定左侧表单二级目录的点击
		$('#left_nav_tree').find('.layui-nav-child').off('click', 'a').on(
				'click', 'a', function() {
					$.ajax({
						url : $(this).attr('href'),
						success : function(htmlData) {
							$('#layui-body-main').html(htmlData);
							//尝试解决搜索表单 select ,radio等不显示的问题
							if ($('#form_search')) {
								//尝试更新一下搜索表单
								form.render(null, 'form_search');
							}
						}
					});
					return false;
				});
	});
</script>
</html>
