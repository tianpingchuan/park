<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId"/>
	<input type="hidden" name="userId" value="${userBuyer.rowId}"/>
	<input type="hidden" name="userName" value="${userBuyer.userName}"/>
	<input type="hidden" name="stallCode" id="stallCode" value=""/>
	<div class="layui-form-item">
		<label class="layui-form-label">车牌号</label>
		<div class="layui-input-block">
			<input name="carNum" lay-verify="required" id="carNum" class="layui-input" placeholder="请输入车牌号" autocomplete="off">
		</div>
	</div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="car_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>