<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib uri="http://www.aggrepoint.com/winlet/site/local" prefix="site" %>
<!DOCTYPE html>
<html>
	<head>
	    <meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />

		<title><site:name /></title>

		<link rel="stylesheet" type="text/css" href="/demo_2/resources/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="/demo_2/resources/bootstrap/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="/demo_2/resources/winlet_local/winlet_local.min.css">
		<link rel="stylesheet" type="text/css" href="/demo_2/resources/style.css">
		<script src="/demo_2/resources/jquery/jquery-1.11.1.min.js"></script>
		<script src="/demo_2/resources/bootstrap/js/bootstrap.min.js"></script>
		<script src="/demo_2/resources/winlet_local/winlet_local_bootstrap.min.js"></script>

		<script>
		$(function() {
			WinletJSEngine.init({top: $("nav.navbar-fixed-top").outerHeight(true)});
		});
		</script>
	</head>
	<body role="document">
	    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">Winlet Demo</a>
	        </div>
	        <div class="navbar-collapse collapse">
	          <ul class="nav navbar-nav">
	          	<site:tree level="0" name="page0">
	          		<site:eval type="hassub" name="isHassub"/>
	          		<site:eval type="focus" name="isFocus"/>

	            	<% if (isHassub.booleanValue()) { %>
			            <li class="dropdown<% if (isFocus.booleanValue()) { %> active<% } %>">
			              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
			              	<% if (isFocus.booleanValue()) { %> <site:name page="AE_CURRENT"/><% } else { %><site:name /><% } %> <b class="caret"></b></a>
			              <ul class="dropdown-menu">
			              	<site:tree from="page0">
			                	<li><a href="<site:url/>"><site:name/></a></li>
			                </site:tree>
			              </ul>
			            </li>
	            	<% } else { %>
		            	<li<% if (isFocus.booleanValue()) { %> class="active"<% } %>><a href="<site:url/>"><site:name/></a></li>
	            	<% } %>
	            </site:tree>
	          </ul>
	          <ul class="nav navbar-nav navbar-right">
	            <li>
	            	<span class="navbar-text">
	            		<site:area name="top" />
	            	</span>
	            </li>
	          </ul>
	        </div>
	      </div>
	    </nav>

	    <div class="container main" role="main">
       		<site:area name="main"/>
	     </div>
	</body>
</html>