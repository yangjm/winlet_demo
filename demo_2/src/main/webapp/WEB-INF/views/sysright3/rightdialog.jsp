<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<win:include window="rightListWin">
	<win:param name="selcat" value="${catId}" />
	<win:param name="rpsize" value="3" />
</win:include>

<win:dialog title="权限">
	<win:button class="btn btn-default" data-dismiss="modal">关闭</win:button>
</win:dialog>