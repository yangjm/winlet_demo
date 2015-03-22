<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<win:form name="editRightCat" method="post" action="saveCat" validate="yes" focus="catCode">
	<input type="hidden" name="rightCatId" value="${CAT.rightCatId}" />
	<div class="form-group">
		<label for="catCode">代码：</label>
		<input type="text" class="form-control" name="catCode" id="catCode" value="${CAT.catCode}">
	</div>
	<div class="form-group">
		<label for="catName">名称：</label>
		<input type="text" class="form-control" name="name" id="catName" value="${CAT.name}">
	</div>
	<input type="submit" style="display: none">
</win:form>

<win:dialog title="${win:ifelse(CAT.rightCatId eq 0, '添加', '编辑')} 权限组">
	<win:button class="btn btn-primary" onclick="win$.submit('editRightCat')">保存</win:button>
	<win:button class="btn btn-default" data-dismiss="modal">取消</win:button>
</win:dialog>