<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-sm-12 col-md-5">
		<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">显示 ${pageData.beginIndex} 到 ${pageData.endIndex} 共 ${pageData.countIndex} 记录</div>
	</div>
	<div class="col-sm-12 col-md-7 float-right">
		<ul class="pagination float-right" id="ul_pagination">
			<li class="page-item ${pageData.pageCurrent==1?'disabled':''}"><a class="page-link " href="javascript:page_prev();" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<c:if test="${!empty pageData.pageList }">
			<c:forEach items="${pageData.pageList}" var="page">
			<li class="page-item ${page == pageData.pageCurrent?'active':''}">
			<c:if test="${page == pageData.pageCurrent}">
				<span class="page-link">${page}</span>
			</c:if>
			<c:if test="${page != pageData.pageCurrent}">
				<a class="page-link" href="javascript:page_select(${page});">${page}</a>
			</c:if>
			</li>	
			</c:forEach>
			</c:if>
			<li class="page-item ${pageData.pageCurrent == pageData.pageMaxNo?'disabled':''}"><a class="page-link" href="javascript:page_next();" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>
	</div>
</div>