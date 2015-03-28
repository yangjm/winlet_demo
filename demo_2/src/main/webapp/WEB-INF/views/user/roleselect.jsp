<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			选择角色 <span class="text-danger">(可拖拽)</span>
		</h3>
	</div>
	<div class="panel-body">
		<c:forEach var="role" items="${ROLES}">
			<c:set var="selected" value="${win:contains(SELECTED, role.roleId)}" />
			<c:set var="btnstyle"
				value="${win:ifelse(selected, 'primary', 'default')}" />
			<div class="winletdrag_cont bottom_space_10" data-drag-group="userrole"
				data-role-id="${role.roleId}">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-${btnstyle} btn-sm"
						onclick="win$.toggle(null, {roles: ['${role.roleId}']})">
						<span
							class="glyphicon glyphicon-${win:ifelse(selected, 'ok', 'hand-right')}"></span>
					</button>
					<button type="button"
						class="btn btn-${btnstyle} btn-sm winletdrag_drag">
						${role.name}</button>
				</div>
				&nbsp;
			</div>
		</c:forEach>

		<c:if test="${not empty SELECTED}">
			<center>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-default"
						onclick="win$.get(null, {roles: null})">
						<span class="glyphicon glyphicon-refresh"></span> 清除选择
					</button>
				</div>
			</center>
		</c:if>
	</div>
</div>