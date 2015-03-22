<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win" %>

用户： ${u.name} | <a href="javascript:win$.post('logout')">退出登录</a>