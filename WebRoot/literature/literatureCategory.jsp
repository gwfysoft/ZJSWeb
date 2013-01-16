<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>文献类别管理</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>文献类别管理</a></li>
			</ul>
		</div>
		 <div class="row" style="height:200px;text-align:center; margin-top:20px;">
             <div class="span12">
                                            文献类别名称:<input type="text"/>                   
                </div>
                 <div class="span12">
                                            文献类别编号:<input type="text"/>
                </div>
                <div class="span12">
                  <a class="btn" href="#literatureTabmkb">确认添加</a>
                </div>
             </div>
	</section>
    <%@ include file="/commons/jsp/common_js.jsp"%>
</body>
</html>
