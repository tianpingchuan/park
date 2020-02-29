<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-sm-12">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">车位号</th>
					<th scope="col">车位状态</th>
					<th scope="col">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty stallList}">
					<c:forEach items="${stallList}" var="stall" varStatus="status">
						<tr>
							<th scope="row">${status.index+1}</th>
							<td>${stall.stallCode}</td>
							<td>${stall.stallState==0?'可用':'不可用'}</td>
							<td>
								<!-- data-XXX H5以后允许自定义元素的属性 -->
								<a href="javascript:;" data-rowId="${stall.rowId}" id="delete">删除</a>
								<a href="javascript:;" data-rowId="${stall.rowId}" id="update">修改</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
<%-- 引入分页 --%>
<%@ include file="/page.jsp"%>