<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>角色管理</title>
<style type="text/css">
</style>
</head>
  <body>
  <section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页//</a></li>
				<li class="active"><a>角色管理</a></li>
			</ul>
		</div>
	<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<s:text	name="查找用户角色" />
						</legend>
						
						<input class="span3" type="hidden" placeholder=".span3" id="roleID" name="roleID">

						<div class="control-group">        
							<label class="control-label" for="doctorName"><s:text name="角色名称" /></label>
							<div class="controls">	  			
								<input class="span3" type="text" placeholder="输入角色名称" id="roleSimp" name="roleSimp">
							</div>	
						</div>

						<div class="form-actions">
				            <button class="btn btn-primary" onclick="searchRoleForUser()"><s:text name="查询" /></button>
				            <button class="btn" type="reset"><s:text name="清除" /></button>
				        </div>		        				
					</fieldset>
				</form>
			</div>
		</div>
		<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
			 	<table id="roleForUserList"></table>
				<div id="donePager" style="text-align: center"></div>
				<div style="height:50px;margin-top:5px;">
				<button  class="btn" id="addDoctor" onclick="addRoleForUser()">
					<s:text name="新建" />
				</button>
				</div>
		</div>
	<%@ include file="/commons/jsp/common_js.jsp"%>
  <script src="<%=path%>/basicInfo/competence/js/user_competence.js"
			type="text/javascript"></script>
	<script type="text/javascript">
var commonQuery = ctxPath + "/common/entityList.action?entityName=RoleForUser";
jQuery("#roleForUserList")
		.jqGrid(
				{	
					url : commonQuery,
					datatype : "json",
					colNames : [ "<s:text name='角色ID'/>", "<s:text name='角色名称'/>","<s:text name='操作类型'/>" ],
					colModel : [ 
					{
						name : 'roleID',
						index : 'roleID',
						align : 'center',
					},{
						name : 'roleSimp',
						index : 'roleSimp',
						editable : true,
						align : 'center',
						sortable : true
					},{
						name : 'act',
						index : 'act',
						label : '操作类型',
						width : "300",
						sortable : false,
						align : 'center'
					}],
					jsonReader : {
						root : "dataRows",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						id : "roleID"
					},
					rowNum : <%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
					rownumbers: true,
					pager : '#donePager',
					sortname : 'roleID',//默认排序方式
					viewrecords : true,
					sortorder : 'asc',
					caption : '<s:text name='用户角色'/>',
					multiselect : false,
					height : "280",
					width:"945",
					gridComplete : function() {
						jQuery("#_empty", "#roleForUserList").addClass(
								"nodrag nodrop");
						jQuery("#roleForUserList").tableDnDUpdate();
						var ids = jQuery("#roleForUserList").jqGrid('getDataIDs');
						for ( var i = 0; i < ids.length; i++) {
							var rolecID = ids[i];
								+rolecID+")>"+"<s:text name='alter' />"+"</button>";
							var updateAction="<a href=\"javascript:void(0)\" onclick=updateRoleForUser("+rolecID+") class=jqgridLinkStyle><s:text name='修改' /></a>";
								+rolecID+")>"+"<s:text name='delete' />"+"</button>";
							var deleteAction="<a href=\"javascript:void(0)\" onclick=deleteRoleForUser("+rolecID+") class=jqgridLinkStyle><s:text name='删除' /></a>";
							jQuery("#roleForUserList").jqGrid('setRowData',
									ids[i], {
									act:updateAction+" | "+deleteAction
									});					
						}
						jQuery("#roleForUserList").closest(".ui-jqgrid-bdiv")
								.css( {
									'overflow' : 'hidden'
								});

					}
				});
</script>
</div>
</section>
  </body>
</html>
