<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<div id="viewport"></div>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<div id="zhai-header"
				style="position: relative; margin-bottom: 60px; padding-top: 20px;">

				<div class="span3">
					<h1 class="logo" style="margin-top: -15px;">
						<a href=""><img src="${ctxPath}/commons/img/logo.png"
							alt="文献宅及送"></a>
					</h1>
				</div>

				<div class="menu span9 pull-right" id="headerTools"
					style="margin-left: 30px;">
					<ul class="menu-list"
						style="margin: 0px; position: relative; left: 570px;">
						<li class="menu-item"><a
							href="//gathercontent.com/how-it-works">设为首页</a></li>
						<li class="menu-item"><a href="//gathercontent.com/features">加入收藏</a>
						</li>
						<li class="menu-item highlight-item"><a
							href="//gathercontent.com/signup">Get Started Now</a></li>
					</ul>
				</div>
			</div>

			<div>
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active">
							<a href="/index.jsp">
								<i	class="icon-th-large"></i>网站首页
							</a>
						</li>
						<li class>
							<a href="/projectIntro/projectIntro.jsp">
								<i class="icon-barcode"></i>项目介绍
							</a>
						</li>
						<li>
							<a href="/projectManage/projectManage.jsp">
								<i class="icon-camera"></i>项目管理
							</a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"	data-toggle="dropdown">
								<i class="icon-share"></i>积分兑换 <b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="/integral/integralRedemption.jsp">
										<i class="icon-glass"></i>积分兑换
									</a>
								</li>
								<li>
									<a href="/product/product.jsp">
										<i class="icon-glass"></i>兑换产品管理
									</a>
								</li>
								<li>
									<a href="/integral/integralPeriodManage.jsp">
										<i class="icon-glass"></i>兑换期数管理
									</a>
								</li>
								<li>
									<a href="/integral/integralStatistics.jsp">
										<i class="icon-glass"></i>兑换统计
									</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="icon-eject"></i>文献资料 <b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="/literature/literatureViewList.jsp">
										<i	class="icon-glass"></i>文献资料查看
									</a>
								</li>
<%--								<li>--%>
<%--									<a href="/literature/literaturePeriods.jsp">--%>
<%--										<i	class="icon-glass"></i>文献期数管理--%>
<%--									</a>--%>
<%--								</li>--%>
								<li>
									<a href="/literature/literatureCategory.jsp">
										<i class="icon-glass"></i>文献类别管理
									</a>
								</li>
								<li>
									<a href="/literature/literatureMaterial.jsp">
										<i	class="icon-glass"></i>文献资料管理
									</a>
								</li>
								<li>
									<a href="/literature/literatureUpload.jsp">
										<i class="icon-glass"></i>文献上传管理
									</a>
								</li>
							</ul></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"	data-toggle="dropdown">
								<i class="icon-warning-sign"></i>活动公告栏	
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/activityBulletin/activityBulletinBoard.jsp"><i class="icon-glass"></i>活动公告栏</a></li>
<%--								<li><a href="/activityBulletin/activityBulletinManage.jsp"><i class="icon-glass"></i>活动公告管理</a></li>--%>
<%--								<li><a href="/activityBulletin/siteBulletinManage.jsp"><i class="icon-glass"></i>网站公告管理</a></li>--%>
								<li><a href="/news/news.jsp"><i class="icon-glass"></i>公告管理</a></li>
							</ul></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="icon-resize-small"></i>人员管理
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/user/doctorManage.jsp"><i class="icon-glass"></i>医师管理</a></li>
								<li><a href="/employee/employee.jsp"><i class="icon-glass"></i>员工管理</a></li>
<%--								<li><a href="/user/personalSchedulingManage.jsp"><i class="icon-glass"></i>人员调度管理</a></li>--%>
							</ul></li>

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="icon-retweet"></i>题目管理 
								<b	class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/question/question.jsp"><i class="icon-glass"></i>题目管理</a></li>
								<li><a href="/topic/answerStatistics.jsp"><i class="icon-glass"></i>答题统计</a></li>
							</ul></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle"	data-toggle="dropdown">
								<i class="icon-resize-vertical"></i>基础设置管理

								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/admin/admin.jsp"><i class="icon-glass"></i>管理员帐户管理</a></li>
<%--								<li><a href="/basicInfo/competence/roleForUserList.jsp"><i class="icon-glass"></i>角色管理</a></li>--%>
<%--								<li><a href="/authority/userRoleManage.jsp"><i class="icon-glass"></i>用户角色分配</a></li>--%>
<%--								<li><a href="/authority/roleAuthorityManage.jsp"><i class="icon-glass"></i>角色权限分配</a></li>--%>
								<li><a href="/region/region.jsp"><i class="icon-glass"></i>区域管理</a></li>
							</ul></li>
					</ul>

				</div>
			</div>
		</div>
	</div>
</header>