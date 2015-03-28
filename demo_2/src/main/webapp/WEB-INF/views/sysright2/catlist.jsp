<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<div class="bottom_space">
	<button type="button" class="btn btn-default" onclick="win$.post('editCat', {cid: 0})">
		<span class="glyphicon glyphicon-plus"></span> 添加权限组
	</button>
</div>

<c:forEach var="cat" items="${CATS}">
<div class="panel panel-default">
	<div class="panel-heading clearfix">
		<div class="pull-right">
			<div class="btn-group" role="group">
				<button type="button" class="btn btn-info btn-sm"
					onclick="win$.post('editCat', {cid: ${cat.rightCatId}})">
					<span class="glyphicon glyphicon-pencil"></span>
				</button>
				<button type="button" class="btn btn-warning btn-sm"
					onclick="win$.post('deleteCat', {cid: ${cat.rightCatId}})">
					<span class="glyphicon glyphicon-trash"></span>
				</button>
			</div>
		</div>
		<div>权限组：${cat.name} (代码：${cat.catCode})</div>
	</div>

	<div class="container-fluid">
	<p></p>
		<win:include window="rightListWin">
			<win:param name="selcat" value="${cat.rightCatId}" />
			<win:param name="rpsize" value="1000" />
		</win:include>
	</div>
</div>
</c:forEach>
