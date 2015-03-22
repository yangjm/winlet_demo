<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<%@ attribute name="count" type="java.lang.Integer" required="true"%>
<%@ attribute name="totalPage" type="java.lang.Integer" required="true"%>
<%@ attribute name="currPage" type="java.lang.Integer" required="true"%>
<%@ attribute name="range" type="java.lang.Integer" required="true"%>
<%@ attribute name="paramName" type="java.lang.String" required="true"%>
<%@ attribute name="pageSizeParam" type="java.lang.String" required="false"%>
<%@ attribute name="recordName" type="java.lang.String" required="false"%>

<%
	int startPage = currPage - range;
	if (startPage < 1) {
		range += (1 - startPage);
		startPage = 1;
	}

	int endPage = currPage + range;
	if (endPage > totalPage) {
		startPage -= endPage - totalPage;
		if (startPage < 1)
			startPage = 1;
		endPage = totalPage;
	}
	
	String pageSize = null;
	if (pageSizeParam != null)
		pageSize = request.getParameter(pageSizeParam);
	if (pageSize == null)
		pageSize = "10";
	request.setAttribute("__pageSize__", pageSize);
%>
<div class="clearfix">
	<div class="pull-right">
		<nav>
			<ul class="pagination pagination-sm">
				<%
					if (startPage > 1) {
				%>
				<li><a href="javascript:win$.get(null, {${paramName}: '1'})">1</a></li>
				<%
					if (startPage > 2) {
				%>
				<li><span>...</span></li>
				<%
					}
					}
				%>
		
				<%
					for (int i = startPage; i <= endPage; i++) {
						if (i == currPage) {
				%>
				<li class="active"><span><%=i%></span></li>
				<%
					} else {
				%>
				<li><a href="javascript:win$.get(null, {${paramName}: '<%=i%>'})"><%=i%></a></li>
				<%
					}
					}
				%>
		
				<%
					if (endPage < totalPage - 1) {
				%>
				<li><span>...</span></li>
				<%
					}
				%>
				<%
					if (endPage < totalPage) {
				%>
				<li><a href="javascript:win$.get(null, {${paramName}: '<%=totalPage%>'})"><%=totalPage%></a></li>
				<%
					}
				%>
			</ul>
		</nav>
	</div>

	<div class="pull-left">${count} ${win:ifelse(recordName == null, '个记录', recordName)}</div>
<% if (pageSizeParam != null) { %>
	<div class="pull-left">
		<div class="dropdown">,&nbsp;
		  <button class="btn btn-default dropdown-toggle btn-xs" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
		    <%= pageSize %>
		    <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
		  	<c:forEach var="p" items="${cl.sysItemsPerPage}">
		  		<c:if test="${not (pageSize eq p.code)}">
		    	<li role="presentation"><a role="menuitem" tabindex="-1" href="javascript:win$.get(null, {${pageSizeParam}: '${p.code}'})">${p.value}</a></li>
		    	</c:if>
		    </c:forEach>
		  </ul>
		每页
		</div>
	</div>
<% } %>
</div>