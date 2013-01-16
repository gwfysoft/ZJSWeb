<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>管理员管理</title>
<style type="text/css">
</style>
  <body>
  	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>管理员管理</a></li>
			</ul>
		</div>
	<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<h4>管理员查询</h4>
						</legend>
						<input class="span3" type="hidden" placeholder=".span3" id="search_admin_id" name="adminID">
						<div class="control-group">        
							<label class="control-label" for="name">管理员名称</label>
							<div class="controls">	  			
								<input class="span3" type="text" placeholder="输入管理员名称" id="search_admin_name" name="name">
							</div>	
						</div>
						<div class="form-actions">
				            <button class="btn btn-primary" onclick="searchAdmin()">查询</button>
				            <button class="btn" type="reset">清除</button>
				        </div>		        				
					</fieldset>
				</form>
			</div>
			<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
			 	<table id="adminList"></table>
				<div id="donePager" style="text-align: center"></div>
				<div style="height:50px;margin-top:5px;">
				<button  class="btn" id="addAdmin" onclick="addAdmin()">
					新建
				</button>
				</div>
			</div>
	</div>
	<div  id="admin_add">
			<div class="modal-header">
				<h3 id="admin_h_title"></h3>
			</div>
				<div class="form-horizontal" id="addQuestionForm">
				<input type="hidden" id="admin_id" name="adminID" >
					<div class="control-group">        
						<label class="control-label" for="admin_name">管理员名称</label>
						<div class="controls">
							<input type=text id="admin_name" name="name" />
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="admin_total_points">管理员密码</label>
						<div class="controls">
							<input type=text id="admin_password" name="passWord" />
						</div>
					</div>
				</div>							
		</div>
		</section>
		<%@ include file="/commons/jsp/common_js.jsp"%>
		<script src="<%=path%>/admin/js/admin.js" type="text/javascript"></script>
		<script type="text/javascript">
		var commonQuery = ctxPath + "/common/entityList.action?entityName=Admin";
		jQuery("#adminList")
		.jqGrid(
				{	
					url : commonQuery,
					datatype : "json",
					colNames : [ "<s:text name='管理员ID'/>", "<s:text name='管理员名称'/>","<s:text name='操作类型'/>" ],
					colModel : [ 
					{
						name : 'adminID',
						index : 'adminID',
						hidden:true,
						align : 'center',
					},{
						name : 'name',
						index : 'name',
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
						id : "adminID"
					},
					rowNum : <%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
					rownumbers: true,
					pager : '#donePager',
					sortname : 'adminID',//默认排序方式
					viewrecords : true,
					sortorder : 'asc',
					caption : '<s:text name='管理员'/>',
					multiselect : false,
					height : "280",
					width:"500",
					gridComplete : function() {
						jQuery("#_empty", "#adminList").addClass(
								"nodrag nodrop");
						jQuery("#adminList").tableDnDUpdate();
						var ids = jQuery("#adminList").jqGrid('getDataIDs');
						for ( var i = 0; i < ids.length; i++) {
							var adminID = ids[i];
							var updateAction="<a href=\"javascript:void(0)\" onclick=updateAdmin("+adminID+") class=jqgridLinkStyle><s:text name='修改' /></a>";
							var deleteAction="<a href=\"javascript:void(0)\" onclick=deleteAdmin("+adminID+") class=jqgridLinkStyle><s:text name='删除' /></a>";
							jQuery("#adminList").jqGrid('setRowData',
									ids[i], {
									act:updateAction+" | "+deleteAction
									});					
						}
						jQuery("#adminList").closest(".ui-jqgrid-bdiv")
								.css( {
									'overflow' : 'hidden'
								});

					}
				});
				jQuery("#admin_add").dialog({
		            autoOpen: false,
		            height: 250,
		            width: 650,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                  saveAdmin();
		                },
		                "取消": function() {
		                    $( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
				function addAdmin(){
					jQuery("#addAdmin").click(function(){
						$("#admin_h_title").text("新建管理员");
						$("#admin_name").attr("value", "");
						$("#admin_password").attr("value","");
							jQuery("#admin_add").dialog("open");
					});
				}
				addAdmin();
				function saveAdmin(){
					var adminID=$("#admin_id").val();
					var name=$("#admin_name").val();
					var passWord=$("#admin_password").val();
					
					jQuery.ajax({
						url : ctxPath + "/admin/add.action",
						data : {
							"adminID" : adminID,
							"admin.name":name,
							"admin.passWord":passWord
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log(res);
							var result = res.result;
							if (result == "success") {
								$("#admin_add").dialog( "close" );
								admin_reload();
							}
						},
						error : function() {
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
				}
				function updateAdmin(adminID){
					var url = ctxPath + "/admin/add.action?adminID="+ adminID;
					jQuery.ajax({
						url : url,
						type : 'GET',
						dataType : 'json',
						success : function(res) {
							var result = res.result;
							var adminID=res.admin.userID;
							var name=res.admin.name;
							var passWord=res.admin.passWord;
							if (result=="success") {
								$("#admin_h_title").text("修改管理员");
								$("#admin_id").attr("value",adminID);
								$("#admin_name").attr("value", name);
								$("#admin_password").attr("value",passWord);
								jQuery( "#admin_add" ).dialog( "open" );
							}
						},
						error : function() {
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
				}
</script>
  </body>
</html>
