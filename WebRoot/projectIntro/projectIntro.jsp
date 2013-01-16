<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>项目介绍</title>
<style type="text/css">
#nymainleft_wrap_content a{
	display: block;
	width: 200px;
	margin-top:5px;
}
.totop {
	background: url(http://img01.taobaocdn.com/tps/i1/T12L9tXehKXXXXXXXX-22-56.png) no-repeat;
	width: 22px;
	height: 56px;
	left: 50%;
	bottom: 10px;
	margin-left: 500px;
	position: fixed;
	display: inline;
}
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页//</a></li>
				<li class="active"><a>项目介绍</a></li>
			</ul>
		</div>
		<div id="list_img_div">
			<div class="nymainleft" style="float:left;">
				<div id="nymainleft_wrap" style="margin-top: 10px;">
				<ul id="nymainleft_wrap_content" class="ui-sortable">
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs1" class="btn"><span>项目介绍</span></a>
					 
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs2" class="btn"><span>操作流程</span></a>
					 
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs8" class="btn"> <span>职责与分工</span></a>
					
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs3" class="btn"> <span>积分与奖励</span></a>
					
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs4" class="btn"> <span>短信平台介绍</span></a>
					
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs5" class="btn"> <span>网站平台</span></a>
					
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs9" class="btn"> <span>项目考核公式</span></a>
					
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs6" class="btn"> <span>Q &amp; A</span></a>
					
					<a href="<%=path%>/projectIntro/projectIntro.jsp#xmjs7" class="btn"> <span>注意事项</span></a>
					
				</ul>
			</div>

			 
			</div>
			<div class="nymainright" style="position: relative;left: 300px;">
			<div id="top"><img src="<%=path%>/projectIntro/img/small/xmjsht.jpg"></div>
			<div id="xmjs1"><a href="<%=path%>/projectIntro/img/big/xmjs1.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs1.jpg"></a></div>
			<div id="xmjs2"><a href="<%=path%>/projectIntro/img/big/xmjs3.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs3.jpg"></a></div>
			<div id="xmjs8"><a href="<%=path%>/projectIntro/img/big/xmjs5.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs5.jpg"></a></div>
			<div id="xmjs3"><a href="<%=path%>/projectIntro/img/big/xmjs7.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs7.jpg"></a></div>
			<div id="xmjs4"><a href="<%=path%>/projectIntro/img/big/xmjs8.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs8.jpg"></a></div>
			<div id="xmjs5"><a href="<%=path%>/projectIntro/img/big/xmjs9.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs9.jpg"></a></div>
			<div id="xmjs9"><a href="<%=path%>/projectIntro/img/big/xmjs12.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs12.jpg"></a></div>
			<div id="xmjs6"><a href="<%=path%>/projectIntro/img/big/xmjs14.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs14.jpg"></a></div>
			<div id="xmjs7"><a href="<%=path%>/projectIntro/img/big/xmjs16.jpg" target="_blank"><img src="<%=path%>/projectIntro/img/small/xmjs16.jpg"></a></div>
			</div>
			<a href="<%=path%>/projectIntro/projectIntro.jsp#top" id="J_ToTop" class="totop"></a>
	</section>
	<%@ include file="/commons/jsp/common_js.jsp"%>
</body>
</html>
