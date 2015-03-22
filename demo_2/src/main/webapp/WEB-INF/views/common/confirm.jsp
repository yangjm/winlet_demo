<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

${MESSAGE}

<win:dialog title="<span class='glyphicon glyphicon-exclamation-sign'></span> 请确认">
	<win:button class="btn btn-primary" onclick="win$.post('${ACTION}', {${PARAMS}}, function(data, textStatus, jqXHR){${FUNCTION}})">确认</win:button>
	<win:button class="btn btn-default" data-dismiss="modal">取消</win:button>
</win:dialog>