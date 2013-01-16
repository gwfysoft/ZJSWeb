<%@ page language="java" import="com.jung.common.HttpReqUtil" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<!-- 全局java变量与方法区域 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<c:set var="ctxPath" value="${pageContext.request['contextPath']}"	scope="request" />
