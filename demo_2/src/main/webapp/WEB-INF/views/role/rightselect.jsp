<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">选择权限 <span class="text-danger">(可拖拽)</span></h3>
	</div>
	<div class="panel-body">
		<c:forEach var="cat" items="${CATS}">
			<p>
				${cat.name}
				<c:forEach var="right" items="${cat.rights}">
					<c:set var="selected"
						value="${win:contains(SELECTED, right.rightId)}" />
					<c:set var="btnstyle"
						value="${win:ifelse(selected, 'primary', 'default')}" />

					<div class="winletdrag_cont pull-left" data-drag-group="rolerights"
						data-right-id="${right.rightId}">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-${btnstyle} btn-xs"
								onclick="win$.toggle(null, {rights: ['${right.rightId}']})">
								<span
									class="glyphicon glyphicon-${win:ifelse(selected, 'ok', 'hand-right')}"></span>
							</button>
							<button type="button"
								class="btn btn-${btnstyle} btn-xs winletdrag_drag">
								${right.name}</button>
						</div>
						&nbsp;
					</div>
				</c:forEach>
			<div class="clearfix"></div>
			</p>
		</c:forEach>

		<c:if test="${not empty SELECTED}">
			<center>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-default" onclick="win$.get(null, {rights: null})">
						<span class="glyphicon glyphicon-refresh"></span> 清除选择
					</button>
				</div>
			</center>
		</c:if>
	</div>
</div>