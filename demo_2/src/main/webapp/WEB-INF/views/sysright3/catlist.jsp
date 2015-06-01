<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<div class="bottom_space">
	<button type="button" class="btn btn-default" onclick="win$.post('editCat', {cid: 0})">
		<span class="glyphicon glyphicon-plus"></span> 添加权限组
	</button>
</div>

<div class="list-group">
	<c:forEach var="cat" items="${CATS}">
		<a href="javascript:win$.post('showRights', {cid: '${cat.rightCatId}'})" class="list-group-item ${win:if(cat.rightCatId eq param.selcat, 'active')}">
			<div class="btn-group pull-right" role="group">
		      <button type="button" class="btn btn-xs btn-info" onclick="win$.post('editCat', {cid: ${cat.rightCatId}})">
		        <span class="glyphicon glyphicon-pencil"></span>
			  </button>
		      <button type="button" class="btn btn-xs btn-warning" onclick="win$.post('deleteCat', {cid: ${cat.rightCatId}})">
		        <span class="glyphicon glyphicon-trash"></span>
		      </button>
		    </div>

			<h4 class="list-group-item-heading">${cat.name}</h4>

			<p class="list-group-item-text">代码：${cat.catCode}</p>
		</a>
	</c:forEach>
</div>
