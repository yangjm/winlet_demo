<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<win:form name="editRight" method="post" action="saveRight" validate="yes" focus="rightCode">
	<input type="hidden" name="rightId" value="${RIGHT.rightId}" />
	<input type="hidden" name="rightCatId" value="${RIGHT.rightCatId}" />
	<div class="form-group">
		<label for="rightCode">代码：</label>
		<input type="text" class="form-control" name="rightCode" id="rightCode" value="${RIGHT.rightCode}">
	</div>
	<div class="form-group">
		<label for="rightName">名称：</label>
		<input type="text" class="form-control" name="name" id="rightName" value="${RIGHT.name}">
	</div>
	<div class="form-group">
		<label for="rightDesc">说明：</label>
		<input type="text" class="form-control" name="description" id="rightDesc" value="${RIGHT.description}">
	</div>
	<input type="submit" style="display: none">
</win:form>

<win:dialog title="${win:ifelse(CAT.rightCatId eq 0, '添加', '编辑')} 权限">
	<win:button class="btn btn-primary" onclick="win$.submit('editRight')">保存</win:button>
	<win:button class="btn btn-default" data-dismiss="modal">取消</win:button>
</win:dialog>