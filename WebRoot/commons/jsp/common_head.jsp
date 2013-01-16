<%@ page language="java" import="com.jung.common.HttpReqUtil" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<!-- 全局java变量与方法区域 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html lang="cn">
	<head>
		<meta charset="utf-8">
		<title>文献宅及送: <sitemesh:write property='title'>这里放自己模块的标题</sitemesh:write></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="文献宅及送">
		<meta name="author" content="gwfy">
		<style type="text/css">
			body {
				padding-top: 120px;
			}
		</style>
		<link type="text/css" rel="stylesheet" href="<%=path%>/commons/css/bootstrap-github.css" />
		<link type="text/css" rel="stylesheet" href="<%=path%>/commons/css/prettify.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path%>/commons/css/start/jquery-ui-1.9.2.custom.min.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path%>/commons/css/skins/tango/skin.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path%>/commons/css/main.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path%>/commons/css/jquery.modal.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path%>/commons/js/vendor//jqgrid/ui.jqgrid.css" />
		
		<link type="text/css" rel="stylesheet" href="<s:url value='/commons/plugin/jqPagination/css/jqpagination.css'/>" />
		<link type="text/css" rel="stylesheet" href="<s:url value='/commons/plugin/DatePicker/skin/default/datepicker.css'/>"  />
		<link type="text/css" rel="stylesheet" href="<s:url value='/commons/plugin/alert/css/alert.css'/>" />
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
		<script src="js/vendor/html5.js"></script>
		<![endif]-->
		<link rel="shortcut icon" href="">
	
		<c:set var="ctxPath" value="${pageContext.request['contextPath']}"	scope="request" />
		
		<!-- 全局javascript变量与方法区域 -->
		<script>
			var defaultServerAddress = '<%=basePath%>';
			var defaultAddress = '<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()%>';
			var contextPath = '<%=request.getContextPath()%>';
			var __backURL = '<%=request.getHeader("Referer")%>';
			var ctxPath = "${ctxPath}";
			console.log("defaultServerAddress:"+defaultServerAddress)
			console.log("defaultAddress:"+defaultAddress)
			console.log("contextPath:"+contextPath)
			console.log("__backURL:"+__backURL)
			console.log("ctxPath:"+ctxPath)
		</script>
		<sitemesh:write property='head'/>
	</head>