<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ attribute name="attr" type="java.lang.String" required="true"%>
<%@ attribute name="title" type="java.lang.String" required="true"%>

<%
	String sort = request.getParameter("sort");
	String dir = request.getParameter("dir");
	boolean up = false;
	boolean down = false;
	
	if (attr.equals(sort)) {
		up = "asc".equalsIgnoreCase(dir);
		down = "desc".equalsIgnoreCase(dir);
		
		if (up)
			sort = "desc";
		else
			sort = "asc";
	} else
		sort = "asc";
%>
<a href="javascript:win$.get(null, {sort: '<%= attr %>', dir:'<%= sort %>'})"><%= title %></a><% if (up) { %> <span class="glyphicon glyphicon-chevron-up"></span><% } else if (down) { %> <span class="glyphicon glyphicon-chevron-down"></span><% } %>
