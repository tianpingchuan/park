<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					车位信息
				</div>
				<div class="layui-card-body">
					<!-- 搜索表单 开始  -->
					<form class="layui-form" id="form_search" lay-filter="form_search">
						<div class="layui-search-form">
							<div class="layui-inline">
								<select name="stallState">
									<option value=>车位状态</option>
									<option value="1">不可用</option>
									<option value="0">可停车</option>
								</select>
							</div>
							<div class="layui-inline">
								<input name="stallCode" placeholder="车位号" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<!-- 搜索按钮 -开始 -->
								<button lay-submit lay-filter="btn_search" title="搜索" class="layui-btn layui-btn-primary layui-btn-sm layui-tips" >
									<i class="layui-icon layui-icon-search"></i>
								</button>
								<!-- 搜索按钮 -结束 -->
								<!-- 重置按钮 - 开始 -->
								<!--想自动弹出tip信息 两个条件  class="layui-tips" title="信息" -->
								<button type="reset" title="重置" class="layui-btn layui-btn-primary layui-btn-sm layui-tips">
									<i class="layui-icon layui-icon-refresh"></i>
								</button>
								<!-- 重置按钮 - 结束 -->
							</div>
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
<input type="hidden" id="hideURL" value="stallclient"/>
<input type="hidden" id="hideTitle" value="车位"/>
<!-- 修改，删除 按钮 -->
<!-- 此处注意：必须有lay-event 才能通过table.on完成事件的绑定 -->
<script type="text/html" id="BtnTpl">
{{# if(d.stallState ==1){ }}
	<a class="layui-btn layui-btn-xs layui-btn-disabled" >停车</a>
{{# }else{  }}
	<a class="layui-btn layui-btn-xs" lay-event="edit">停车</a>
 {{#  } }}
</script>
<!-- layui 定义的模板数据 -->
<script type="text/html" id="stallTpl">
 {{# if(d.stallState ==1){ }}
    <span class="layui-badge layui-bg-danger">已有车辆</span>
  {{#  } else { }}
    <span class="layui-badge layui-bg-cyan">可停车</span>
  {{#  } }}
</script>
<!-- 引入自定义的JS脚本 -->
<script type="text/javascript" src="assert/pages/js/sys/stall.js"></script>