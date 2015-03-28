<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<win:form name="editUser" method="post" action="saveUser" validate="yes" focus="${win:ifelse(USER.userId eq 0, 'loginName', 'userName')}">
	<input type="hidden" name="userId" value="${USER.userId}" />
	<div class="row">
		<c:choose>
			<c:when test="${USER.userId eq 0}">
				<div class="form-group col-md-6">
					<label for="loginName">账号：</label>
					<div class="input-group">
						<div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
						<input type="text" class="form-control" name="loginName" id="loginName" value="${USER.loginName}">
					</div>
					<span class="validate_result" data-input="loginName"></span>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-md-6">
					<label>账号：</label>
					<div>${USER.loginName}</div>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="form-group col-md-6">
			<label for="userName">姓名：</label>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-smile-o"></i></div>
				<input type="text" class="form-control" name="userName" id="userName" value="${USER.userName}">
			</div>
			<span class="validate_result" data-input="userName"></span>
		</div>
	</div>
	<div class="form-group">
		<label for="userEmail">邮件：</label>
		<div class="input-group">
			<div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
			<input type="email" class="form-control" name="email" id="userEmail" value="${USER.email}">
		</div>
		<span class="validate_result" data-input="email"></span>
	</div>
	<div class="row">
		<div class="form-group col-md-6">
			<label for="password">密码：</label>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-key"></i></div>
	 			<input type="password" data-validate="no" class="form-control" name="password" id="password">
	 		</div>
			<span class="validate_result" data-input="password"></span>
		</div>
		<div class="form-group col-md-6">
			<label for="password2">确认密码：</label>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-key"></i></div>
				<input type="password" data-validate="no" class="form-control" name="password2" id="password2">
			</div>
			<span class="validate_result" data-input="password2"></span>
		</div>
	</div>
	<input type="submit" style="display: none">
</win:form>

<win:dialog title="${win:ifelse(USER.userId eq 0, '添加', '编辑')} 用户">
	<win:button class="btn btn-primary" onclick="win$.submit('editUser')">保存</win:button>
	<win:button class="btn btn-default" data-dismiss="modal">取消</win:button>
</win:dialog>