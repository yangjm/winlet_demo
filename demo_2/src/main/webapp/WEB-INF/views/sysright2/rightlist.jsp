<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<div class="bottom_space clearfix">
	<button type="button" class="btn btn-default pull-right" onclick="win$.post('editRight', {rid: 0, cid: ${param.selcat}})">
		<span class="glyphicon glyphicon-plus"></span> 添加权限
	</button>
</div>

<div class="row">
	<c:forEach var="right" items="${RIGHTS.list}">
	  <div class="col-xs-6 col-md-4 col-lg-3">
	    <div class="well">
	    	<div class="pull-right">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-info btn-xs"
						onclick="win$.post('editRight', {rid: ${right.rightId}})">
						<span class="glyphicon glyphicon-pencil"></span>
					</button>
					<button type="button" class="btn btn-warning btn-xs"
						onclick="win$.post('deleteRight', {rid: ${right.rightId}})">
						<span class="glyphicon glyphicon-trash"></span>
					</button>
				</div>
	    	</div>
	    	<div>${right.name} (${right.rightCode})</div>
	    	<p>${right.description}</p>
	    </div>
	  </div>
  	</c:forEach>
</div>
