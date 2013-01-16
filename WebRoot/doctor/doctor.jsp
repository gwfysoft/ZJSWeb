<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta charset="utf-8">
	<title>首页</title>
</head>

<body>
	<section class="container" id="container">
		<div class="subnav">
		    <ul class="nav nav-pills">
		      <li class=""><a href="/index.jsp">首页//</a></li>
		      <li class="active"><a href="#code">医师搜索</a></li>
		    </ul>
		</div>
		<!-- serach start -->
		<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<s:text	name="医师搜索" />
						</legend>
						
						<input class="span3" type="hidden" placeholder=".span3" id="doctorID" name="doctorID">

						<div class="control-group">        
							<label class="control-label" for="doctorName"><s:text name="医师名称" /></label>
							<div class="controls">	  			
								<input class="span3" type="text" placeholder="输入医师名称" id="doctorName" name="doctorName">
							</div>	
						</div>

						<div class="control-group"> 
							<label class="control-label" for="doctorMobile"><s:text name="医师手机" /></label>
							<div class="controls">					
								<input class="span3" type="text" placeholder="输入手机号码" id="doctorMobile" name="doctorMobile">
							</div>	
						</div>

						<div class="form-actions">
				            <button class="btn btn-primary" onclick="searchDoctor()"><s:text name="查询" /></button>
				            <button class="btn" type="reset"><s:text name="清除" /></button>
				        </div>		        				
					</fieldset>
				</form>
			</div>
		</div>
<%@ include file="/commons/jsp/common_js.jsp"%>
		<!-- search end -->
		<div class="jqgridList">
			<table id="doctorList"></table>
			<div id="donePager" style="text-align: center"></div>
			<button class="blue" style="cursor: pointer" onclick="addDoctor()">
				<s:text name="新建" />
			</button>
			<script src="<%=path%>/doctor/js/doctor.js" /></script>
			<script>
				var commonQuery = ctxPath+ "/common/entityList.action?entityName=Doctor";
				jQuery("#doctorList")
						.jqGrid(
								{
									url : commonQuery,
									datatype : "json",
									colNames : [ "<s:text name='医师ID'/>",
											"<s:text name='医师名称'/>",
											"<s:text name='医师手机'/>",
											"<s:text name='医师职称'/>",
											"<s:text name=' 医师积分'/>",
											"<s:text name='医师状态'/>",
											"<s:text name='目标科室'/>",
											"<s:text name='销售代表'/>",
											"<s:text name='所属地区'/>",
											"<s:text name='操作类型'/>" ],
									colModel : [ {
										name : 'doctorID',
										index : 'doctorID',
										hidden : true,
										align : 'center',
									}, {
										name : 'doctorName',
										index : 'doctorName',
										align : 'center',
										sortable : true
									}, {
										name : 'doctorMobile',
										index : 'doctorMobile',
										align : 'center',
										sortable : true
									}, {
										name : 'doctorJobTitle',
										index : 'doctorJobTitle',
										align : 'center',
										sortable : true
									}, {
										name : 'doctorPoints',
										index : 'doctorPoints',
										align : 'center',
										sortable : true
									}, {
										name : 'doctorStatusMapping',
										index : 'doctorStatusMapping',
										align : 'center',
										sortable : true
									}, {
										name : 'doctorTargetDept',
										index : 'doctorTargetDept',
										align : 'center',
										sortable : true
									}, {
										name : 'employeeName',
										index : 'employeeName',
										align : 'center',
										sortable : true
									}, {
										name : 'regionName',
										index : 'regionName',
										align : 'center',
										sortable : true
									}, {
										name : 'act',
										index : 'act',
										label : '操作类型',
										width : "300",
										sortable : false,
										align : 'center'
									} ],
									jsonReader : {
										root : "dataRows",
										page : "page",
										total : "total",
										records : "records",
										repeatitems : false,
										id : "doctorID"
									},
									rowNum :<%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
									rownumbers : true,
									pager : '#donePager',
									sortname : 'doctorID',//默认排序方式
									viewrecords : true,
									sortorder : 'asc',
									caption : '<s:text name='医师'/>',
									multiselect : false,
									height : "280",
									width : "945",
									gridComplete : function() {
										jQuery("#_empty", "#doctorList")
												.addClass("nodrag nodrop");
										jQuery("#doctorList").tableDnDUpdate();
										var ids = jQuery("#doctorList").jqGrid(
												'getDataIDs');
										for ( var i = 0; i < ids.length; i++) {
											var doctorID = ids[i];
											var updateAction = "<a href=\"javascript:void(0)\" onclick=updateDoctor("
													+ doctorID
													+ ") class=jqgridLinkStyle><s:text name='修改' /></a>";
											var deleteAction = "<a href=\"javascript:void(0)\" onclick=deleteDoctor("
													+ doctorID
													+ ") class=jqgridLinkStyle><s:text name='删除' /></a>";
											jQuery("#doctorList").jqGrid(
													'setRowData',
													ids[i],
													{
														act : updateAction
																+ " | "
																+ deleteAction
													});
										}
										jQuery("#doctorList").closest(
												".ui-jqgrid-bdiv").css({
											'overflow' : 'hidden'
										});

									}
								});
			</script>
		</div>
	</section>
</body>
</html>
