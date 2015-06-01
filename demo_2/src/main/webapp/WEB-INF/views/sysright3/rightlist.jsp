<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th><tags:sort attr="rightCode" title="代码" /></th>
			<th><tags:sort attr="name" title="名称" /></th>
			<th>说明</th>
		</tr>
	</thead>
	<c:forEach var="right" items="${RIGHTS.list}">
		<tr>
			<td>${right.rightCode}</td>
			<td>${right.name}</td>
			<td>${right.description}</td>
		</tr>
	</c:forEach>
</table>

<tags:pagination totalPage="${RIGHTS.totalPage}"
	currPage="${RIGHTS.currentPage}" range="4" paramName="rpage" count="${RIGHTS.totalCount}" />
