<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<win:form name="editRole" method="post" action="saveRole" validate="yes" focus="name">
	<input type="hidden" name="roleId" value="${ROLE.roleId}" />
	<div class="form-group">
		<label for="roleName">名称：</label>
		<input type="text" class="form-control" name="name" id="roleName" value="${ROLE.name}">
	</div>
	<div class="form-group">
		<label for="roleDesc">说明：</label>
		<textarea id="roleDesc" class="form-control" rows="3" name="description">${ROLE.description}</textarea>
	</div>
	<input type="submit" style="display: none">
</win:form>

<win:dialog title="${win:ifelse(CAT.rightCatId eq 0, '添加', '编辑')} 角色">
	<win:button class="btn btn-primary" onclick="win$.submit('editRole')">保存</win:button>
	<win:button class="btn btn-default" data-dismiss="modal">取消</win:button>
</win:dialog>