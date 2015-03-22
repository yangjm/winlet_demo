<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<div class="jumbotron">
	<h1>用户登录</h1>
	<p>(演示管理员账号：admin/admin)</p>
	<p>
		<win:form name="login" action="login" method="post">
			<div class="form-group row">
				<div class="col-xs-5">
					<label for="loginName">登录账号</label> <input
						type="text" class="form-control" name="loginName" id="loginName" maxlength="20" size="20"
						placeholder="Input your user name or email">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-xs-5">
					<label for="password">登录密码</label> <input type="password"
						class="form-control" name="password" id="password" maxlength="20" size="20"
						placeholder="Input your password">
				</div>
			</div>
			<c:if test="${not empty ERROR}">
				<div class="form-group row">
					<div class="col-xs-12">
						<span class="text-danger">${ERROR}</span>
					</div>
				</div>
			</c:if>
			<center>
				<button type="submit" class="btn btn-primary">登录</button>
			</center>
		</win:form>
	</p>
</div>
