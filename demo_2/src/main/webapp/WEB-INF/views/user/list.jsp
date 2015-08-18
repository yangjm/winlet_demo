<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<div class="well clear-fix">
	<win:form name="search" method="get" class="form-inline" role="form">
		<div class="form-group">
		    <div class="input-group">
				<div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
				<input type="text" class="form-control" name="sLoginName" value="${param.sLoginName}" placeholder="账号">
			</div>
		</div>
		<div class="form-group">
		    <div class="input-group">
				<div class="input-group-addon"><i class="fa fa-smile-o"></i></div>
				<input type="text" class="form-control" name="sUserName" value="${param.sUserName}" placeholder="姓名">
			</div>
		</div>
		<div class="form-group">
		    <div class="input-group">
				<div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
				<input type="text" class="form-control" name="sEmail" value="${param.sEmail}"  placeholder="邮件">
			</div>
		</div>
		<button type="submit" class="btn btn-default">搜索</button>
	</win:form>
</div>

<div class="btn-group bottom_space" role="group">
	<button type="button" class="btn btn-default"
		onclick="win$.post('editUser', {uid: 0})">
		<span class="glyphicon glyphicon-plus"></span> 添加用户
	</button>
	<button type="button" class="btn btn-default" onclick="win$.post('assignRoles', 'userList')">
		<span class="glyphicon glyphicon-check"></span> 批量授权
	</button>
</div>

<win:form name="userList" method="post">
<table class="table table-striped">
	<thead>
		<tr>
			<th><input type="checkbox" onclick="$(this).parents('form').find('input:checkbox').prop('checked', this.checked)" /></th>
			<th>用户</th>
			<th>角色 <span class="text-danger">(可拖拽)</span></th>
		</tr>
	</thead>
	<c:forEach var="user" items="${USERS.list}">
		<tr>
			<td><input name="userid" type="checkbox" value="${user.userId}" ${win:if(win:contains(CHECKED, user.userId), "checked=checked")}/></td>
			<td>
				<div class="btn-group pull-right" role="group">
					<button type="button" class="btn btn-info btn-xs"
						onclick="win$.post('editUser', {uid: ${user.userId}})">
						<span class="glyphicon glyphicon-pencil"></span>
					</button>
					<button type="button" class="btn btn-warning btn-xs"
						onclick="win$.post('deleteUser', {uid: ${user.userId}})">
						<span class="glyphicon glyphicon-trash"></span>
					</button>
				</div>

				<div>${user.loginName}</div>
				<div>${user.userName}</div>
				<div>${user.email}</div>
			</td>
			<td>
				<div class="well winletdrag_area" data-drag-group="userrole" data-user-id="${user.userId}">
					<c:forEach var="role" items="${user.roles}">
						<div class="winletdrag_cont pull-left" data-drag-group="userrole" data-user-id="${user.userId}" data-role-id="${role.roleId}">
							<span class="label label-default winletdrag_drag">${role.name}</span>&nbsp;
						</div>
					</c:forEach>
					<div class="clearfix"></div>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>
</win:form>

<tags:pagination totalPage="${USERS.totalPage}"
	currPage="${USERS.currentPage}" range="4" paramName="page" count="${USERS.totalCount}" />

<script>
// 拖动完毕后的处理
WinletDrag.config("userrole", {
	showDropLocation: false,
	moveAfterDrag: false,
	dragEnd: function(cont, location) {
		if ($(cont).attr("data-user-id") && location == null) // 将已经分配的角色拖出了角色区域，删除
			win$.post('removeRole', {userid: $(cont).attr("data-user-id"), roleid: $(cont).attr("data-role-id")});
	},
	dragged: function(cont, location) {
		if ($(cont).attr("data-user-id")) { // 拖动了已经分配的角色
			if ($(cont).attr("data-user-id") != $(location.area).attr("data-user-id")) // 拖到了其他角色的权限区域
				win$.post('moveRole', {from: $(cont).attr("data-user-id"), to: $(location.area).attr("data-user-id"), roleid: $(cont).attr("data-role-id")});
		} else { // 将未分配的角色拖动到用户角色区域，添加
			win$.post('addRole', {userid: $(location.area).attr("data-user-id"), roleid: $(cont).attr("data-role-id")});
		}			
	}
});
</script>