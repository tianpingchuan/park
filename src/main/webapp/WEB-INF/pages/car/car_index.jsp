<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 引入自定义的权限管理标签 -->
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					可取车
				</div>
				<div class="layui-card-body">
					<!-- 搜索表单 开始  -->
					<form class="layui-form" id="form_search" lay-filter="form_search">
						<div class="layui-search-form">
							<input type="hidden" name="userId" value="${userBuyer.rowId}"/>
						</div>
					</form>
					<!-- 搜索表单 结束  -->
					<!-- 页面表格 开始  -->
					<table id="list_table" lay-filter="filter_table"></table>
					<!-- 页面表格 结束  -->
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="hideURL" value="car"/>
<input type="hidden" id="hideTitle" value="取车"/>
<!-- 修改，删除 按钮 -->
<!-- 此处注意：必须有lay-event 才能通过table.on完成事件的绑定 -->
<script type="text/html" id="roleBtnTpl">
	<a class="layui-btn layui-btn-xs" lay-event="delete">取车</a>
</script>

<!-- 引入自定义的JS脚本 -->
<script type="text/javascript" src="assert/pages/js/sys/car.js"></script>
