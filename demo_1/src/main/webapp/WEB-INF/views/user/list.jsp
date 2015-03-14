<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<div class="well clear-fix">
	<win:form name="search" method="get" class="form-inline" role="form">
		<div class="form-group">
			<label for="searchName">姓名：</label>
			<input type="text" class="form-control" name="searchName" id="searchName" value="${param.searchName}">
			<label for="searchName">职位：</label>
			<input type="text" class="form-control" name="searchTitle" id="searchTitle" value="${param.searchTitle}">
			<label for="searchName">账号：</label>
			<input type="text" class="form-control" name="searchLogin" id="searchLogin" value="${param.searchLogin}">
		</div>
		<button type="submit" class="btn btn-default">查询</button>
	</win:form>
</div>

<tags:pagination totalPage="${USERS.totalPage}"
	currPage="${USERS.currentPage}" range="4" paramName="page" count="${USERS.totalCount}"/>

<div class="btn-group" role="group">
	<div class="btn-group" role="group">
		<button type="button" class="btn btn-default" onclick="win$.post('editUser', {uid: 0})">添加用户</button>
	</div>
</div>

<table class="table table-striped">
	<thead>
		<tr>
			<th><a href="javascript:win$.get(null, {sortby: 'name', sort:'asc'})">姓名</a></th>
			<th><a href="javascript:win$.get(null, {sortby: 'title', sort:'asc'})">职位</a></th>
			<th><a href="javascript:win$.get(null, {sortby: 'loginId', sort:'asc'})">账号</a></th>
			<th>操作</th>
		</tr>
	</thead>
	<c:forEach var="user" items="${USERS.list}">
		<tr>
			<td>${user.name}</td>
			<td>${user.title}</td>
			<td>${user.loginId}</td>
			<td align="right">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-default btn-xs"
						onclick="win$.post('editUser', {uid: ${user.userId}})">
						<span class="glyphicon glyphicon-pencil"></span>
					</button>
					<button type="button" class="btn btn-default btn-xs"
						onclick="win$.post('delUser', {uid: ${user.userId}})">
						<span class="glyphicon glyphicon-remove"></span>
					</button>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>

<tags:pagination totalPage="${USERS.totalPage}"
	currPage="${USERS.currentPage}" range="4" paramName="page" count="${USERS.totalCount}"/>
