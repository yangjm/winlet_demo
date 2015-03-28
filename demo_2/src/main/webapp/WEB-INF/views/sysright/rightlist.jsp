<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<div class="bottom_space">
	<button type="button" class="btn btn-default" onclick="win$.post('editRight', {rid: 0, cid: ${param.selcat}})">
		<span class="glyphicon glyphicon-plus"></span> 添加权限
	</button>
</div>

<table class="table table-striped">
	<thead>
		<tr>
			<th><tags:sort attr="rightCode" title="代码" /></th>
			<th><tags:sort attr="name" title="名称" /></th>
			<th>说明</th>
			<th>操作</th>
		</tr>
	</thead>
	<c:forEach var="right" items="${RIGHTS.list}">
		<tr>
			<td>${right.rightCode}</td>
			<td>${right.name}</td>
			<td>${right.description}</td>
			<td align="right">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-info btn-xs"
						onclick="win$.post('editRight', {rid: ${right.rightId}})">
						<span class="glyphicon glyphicon-pencil"></span>
					</button>
					<button type="button" class="btn btn-warning btn-xs"
						onclick="win$.post('deleteRight', {rid: ${right.rightId}})">
						<span class="glyphicon glyphicon-trash"></span>
					</button>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>

<tags:pagination totalPage="${RIGHTS.totalPage}"
	currPage="${RIGHTS.currentPage}" range="4" paramName="page" count="${RIGHTS.totalCount}" />
