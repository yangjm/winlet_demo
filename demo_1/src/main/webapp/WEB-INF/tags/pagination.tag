<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="count" type="java.lang.Integer" required="true"%>
<%@ attribute name="totalPage" type="java.lang.Integer" required="true"%>
<%@ attribute name="currPage" type="java.lang.Integer" required="true"%>
<%@ attribute name="range" type="java.lang.Integer" required="true"%>
<%@ attribute name="paramName" type="java.lang.String" required="true"%>

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

	<div class="pull-left">${count} 个记录</div>
</div>