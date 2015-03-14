<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<win:form name="editUser" method="post" action="save" validate="yes" focus="name">
	<input type="hidden" name="userId" value="${USER.userId}">
	<div class="form-group">
		<label for="userName">姓名：</label>
		<input type="text" class="form-control" name="name" id="userName" value="${USER.name}">
	</div>
	<div class="form-group">
		<label for="userTitle">职位：</label>
		<input type="text" class="form-control" name="title" id="userTitle" value="${USER.title}">
	</div>
	<div class="form-group">
		<label for="userLoginId">账号：</label>
		<input type="text" class="form-control" name="loginId" id="userLoginId" value="${USER.loginId}">
	</div>
	<div class="form-group">
		<label for="userPassword">密码：</label>
		<input type="password" class="form-control" name="password" id="userPassword" value="${USER.password}">
	</div>
	<center>
		<button type="submit" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" onclick="win$.get('')">取消</button>
	</center>
</win:form>
