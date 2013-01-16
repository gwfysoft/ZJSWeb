<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>员工管理</title>
<link type="text/css" rel="stylesheet"	href="<s:url value='/commons/plugin/zTree/css/zTreeStyle/zTreeStyle.css'/>"/>
</head>
	<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>员工管理</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<h4><s:text	name="员工查询" /></h4>
						</legend>
						
						<input class="span3" type="hidden" placeholder=".span3" id="employeeID" name="employeeID">

						<div class="control-group">        
							<label class="control-label" for="employeeName"><s:text name="员工名称" /></label>
							<div class="controls">	  			
								<input class="span3" type="text" placeholder="输入员工名称" id="employeeName" name="employeeName">
							</div>	
						</div>

						<div class="control-group"> 
							<label class="control-label" for="employeeMobile"><s:text name="员工手机" /></label>
							<div class="controls">					
								<input class="span3" type="text" placeholder="输入手机号码" id="employeeMobile" name="employeeMobile">
							</div>	
						</div>
						<div class="control-group"> 
							<label class="control-label" for="employeeType"><s:text name="员工类型" /></label>
							<div class="controls">	
								<select id="type">
									<option value="0"></option>
									<option value="1">MR</option>
									<option value="2">DSM</option>
									<option value="3">PS</option>
									<option value="4">MARKET</option>
						      </select>				
							</div>	
						</div>
						<div class="form-actions">
				            <button class="btn btn-primary" onclick="searchEmployee()"><s:text name="查询" /></button>
				            <button class="btn" type="reset"><s:text name="清除" /></button>
				        </div>		        				
					</fieldset>
				</form>
			</div>
			<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
			 	<table id="employeeList"></table>
				<div id="donePager" style="text-align: center"></div>
				<div style="height:50px;margin-top:5px;">
				<button  class="btn" id="addEmployee" onclick="addEmployee()">
					<s:text name="新建" />
				</button>
				</div>
			</div>
			
		</div>
		<!-- 新建员工modal -->
		<div  id="newEmployee">
			<div class="modal-header">
				<h3 id="new_employee_h_title"></h3>
			</div>
				<div class="form-horizontal" >
					<input type="hidden" id="employee_id" name="employeeID" />
					<div class="control-group">        
						<label class="control-label" for="employee_name">员工名称</label>
						<div class="controls">
							<input type=text id="employee_name" name="employeeName" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="employee_mobile">员工手机</label>
						<div class="controls">
							<input type=text id="employee_mobile" name="employeeMobile" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="employee_number">员工编号</label>
						<div class="controls">
						<input type=text id="employee_number" name="employeeNumber" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="employee_type">员工类型</label>
						<div class="controls">
							<select id="employee_type" name="employeeType">
									<option value="0"></option>
									<option value="1">MR</option>
									<option value="2">DSM</option>
									<option value="3">PS</option>
									<option value="4">MARKET</option>
						     </select>		
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="employee_parent_employeeName">上级员工</label>
							<input type="hidden" id="employee_parent_employeeID" name="parentEmployeeID" />
						<div class="controls">
							<input class="span2" id="employee_parent_employeeName" size="18" type="text" name="parentEmployeeName">
							<a href="#search_parent_modal" rel="modal:open" class="btn" style="color:#fff">选择</a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="employee_region_regionName">所属地区</label>
							<input type="hidden" id="region_id" name="regionID"/>
						<div class="controls">
							<input type="text" id="region_name" name="regionName" />
							<a href="#search_region_modal" rel="modal:open" class="btn" style="color:#fff">选择</a>
						</div>
					</div>
				</div>							
		</div>
		<!-- 上级员工modal -->
		<div id="search_parent_modal" style="display: none;">
				<div class="modal-header">
					<h3 id="parent_employee_h_title">上级员工查询</h3>
				</div>
				<div class="modal-body" style="height:350px;max-heigth:500px;">
					<form class="form-horizontal" id="addParentEmployeeForm">
							<input type="hidden" id="parent_employeeID" name="parentEmployeeID" />
							<div class="control-group">
								<label class="control-label" for="employeeName">
									员工名称
								</label>
								<div class="controls">
									<input type="text" id="parent_employeeName" name="employeeName"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="employeeMobile">
									员工手机
								</label>
								<div class="controls">
									<input type="text" id="parent_employeeMobile" name="employeeMobile"/>
								</div>
							</div>
							<div class="control-group">
								<button type="button" class="btn" id="search_parent_modal_search">
									查询
								</button>
								<button type="reset" class="btn">
									清除
								</button>
							</div>
					</form>
					<div class="jqgridList">
						<table id="parentEmployeeList"></table>
						<div id="donePager" style="text-align: center">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" rel="modal:close" id="add_parent_from_employee_list">添加所选员工</a>
				    <a href="#" class="btn" rel="modal:close" id="cancel_parent_from_employee_list">取消并关闭</a>
				</div>
			</div>
			<!-- 员工所属地区modal -->
			<div id="search_region_modal" style="display: none;">
				<div class="modal-header">
					<h3>地区选择</h3>
				</div>
				<div class="modal-body" style="height:350px;max-heigth:500px;">
					<form class="form-horizontal" id="add_employee_form">
							<div class="zTreeDemoBackground left">
							<ul id="menuTree" class="ztree"></ul>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" rel="modal:close" id="add_region_from_region_list">添加所选地区</a>
				    <a href="#" class="btn" rel="modal:close" id="cancel_region_from_region_list">取消并关闭</a>
				</div>
			</div>
			<!-- 员工调度modal -->
			<div id="transfer_modal" style="display: none;">
				<div class="modal-header">
					<h3>员工调度</h3>
				</div>
				<div class="form-horizontal" >
					<input type="hidden" id="current_mr_id" name="mrID" />
					<div class="control-group">
						<label class="control-label" for="employee_mr_employeeID">上级员工</label>
							<input type="hidden" id="employee_mr_employeeID" name="parentEmployeeID" />
						<div class="controls">
							<input class="span2" id="employee_mr_employeeName" size="18" type="text" name="parentEmployeeName"/>
							<a href="#search_parent_modal" rel="modal:open" class="btn" style="color:#fff">选择</a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="new_employee_employeeID">新代表</label>
							<input type="hidden" id="new_employee_employeeID" name="newEmployeeID" />
						<div class="controls">
							<input class="span2" id="new_employee_employeeName" size="18" type="text" name="newEmployeeName"/>
							<a href="#search_mr_modal" rel="modal:open" class="btn" style="color:#fff">选择</a>
						</div>
					</div>
				</div>
			</div>
		<!-- 销售代表modal -->
		<div id="search_mr_modal" style="display: none;">
				<div class="modal-header">
					<h3 >代表查询</h3>
				</div>
				<div class="modal-body" style="height:350px;max-heigth:500px;">
					<form class="form-horizontal" id="addMRForm">
							<input type="hidden" id="mr_employeeID" name="newEmployeeID" />
							<div class="control-group">
								<label class="control-label" for="mr_employeeName">
									代表名称
								</label>
								<div class="controls">
									<input type="text" id="mr_employeeName" name="employeeName"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="mr_employeeMobile">
									代表手机
								</label>
								<div class="controls">
									<input type="text" id="mr_employeeMobile" name="employeeMobile"/>
								</div>
							</div>
							<div class="control-group">
								<button type="button" class="btn" id="search_parent_modal_search">
									查询
								</button>
								<button type="reset" class="btn">
									清除
								</button>
							</div>
					</form>
					<div class="jqgridList">
						<table id="mrEmployeeList"></table>
						<div id="donePager" style="text-align: center">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" rel="modal:close" id="add_mr_from_employee_list">添加所选代表</a>
				    <a href="#" class="btn" rel="modal:close" id="cancel_mr_from_employee_list">取消并关闭</a>
				</div>
			</div>
			
		</section>
		<%@ include file="/commons/jsp/common_js.jsp"%>
		<script src="<%=path%>/employee/js/employee.js"/></script>
	<script>
		var commonQuery = ctxPath + "/common/entityList.action?entityName=Employee";
		jQuery("#employeeList")
		.jqGrid(
				{	
					url : commonQuery,
					datatype : "json",
					colNames : [ "<s:text name='员工ID'/>","<s:text name='员工编号'/>", "<s:text name='员工名称'/>","<s:text name='员工手机'/>","<s:text name='员工密码'/>","<s:text name='员工积分'/>","<s:text name='员工类型'/>","<s:text name='employeeType'/>","<s:text name='上级员工'/>","<s:text name='所属区域'/>","<s:text name='操作类型'/>" ],
					colModel : [ 
					{
						name : 'employeeID',
						index : 'employeeID',
						hidden:true,
						align : 'center',
					},{
						name : 'employeeNumber',
						index : 'employeeNumber',
						align : 'center',
						sortable : true
					},{
						name : 'employeeName',
						index : 'employeeName',
						align : 'center',
						sortable : true
					},{
						name : 'employeeMobile',
						index : 'employeeMobile',
						align : 'center',
						sortable : true
					},{
						name : 'password',
						index : 'password',
						hidden:true,
						align : 'center',
						sortable : true
					},{
						name : 'employeePoints',
						index : 'employeePoints',
						align : 'center',
						sortable : true
					},{
						name : 'employeeTypeMapping',
						index : 'employeeTypeMapping',
						align : 'center',
						sortable : true
					},
					{
						name : 'employeeType',
						hidden:true,
						index : 'employeeType',
						align : 'center',
						sortable : true
					},
					{
						name : 'parentEmployeeName',
						index : 'parentEmployeeName',
						align : 'center',
						sortable : true
					},
					{
						name : 'regionName',
						index : 'regionName',
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
						id : "employeeID"
					},
					rowNum : <%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
					rownumbers: true,
					pager : '#donePager',
					sortname : 'employeeID',//默认排序方式
					viewrecords : true,
					sortorder : 'asc',
					caption : '<s:text name='员工'/>',
					multiselect : false,
					height : "280",
					width:"945",
					gridComplete : function() {
						jQuery("#_empty", "#employeeList").addClass(
								"nodrag nodrop");
						jQuery("#employeeList").tableDnDUpdate();
						var ids = jQuery("#employeeList").jqGrid('getDataIDs');
						for ( var i = 0; i < ids.length; i++) {
							var transferAction;
							var employeeID = ids[i];
							var rowdata = jQuery("#employeeList").jqGrid('getRowData', employeeID);
							var employeeType=rowdata['employeeType'];
							var updateAction="<a href=\"javascript:void(0)\" onclick=updateEmployee("+employeeID+") class=jqgridLinkStyle><s:text name='修改' /></a>";
						     
							//var permissonAction="<a href=\"javascript:void(0)\" onclick=assignPermisson("+employeeID+") class=jqgridLinkStyle><s:text name='权限' /></a>";
							if(employeeType=="1"){
								 transferAction=" | "+"<a href=\"javascript:void(0)\" onclick=transferEmployee("+employeeID+") class=jqgridLinkStyle><s:text name='调度' /></a>"+" | ";
							}else{
								 transferAction=" | ";
							}
							var deleteAction="<a href=\"javascript:void(0)\" onclick=deleteEmployee("+employeeID+") class=jqgridLinkStyle><s:text name='删除' /></a>";
							jQuery("#employeeList").jqGrid('setRowData',
									ids[i], {
									act:updateAction+transferAction+deleteAction
									});					
						}
						jQuery("#employeeList").closest(".ui-jqgrid-bdiv")
								.css( {
									'overflow' : 'hidden'
								});

					}
				});
			jQuery("#newEmployee").dialog({
		            autoOpen: false,
		            height: 450,
		            width: 650,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                   saveEmployee();		                   
		                },
		                "取消": function() {
		                    jQuery( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
			jQuery("#transfer_modal").dialog({
		            autoOpen: false,
		            height: 250,
		            width: 450,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                  saveTransferEmployee();		                   
		                },
		                "取消": function() {
		                    jQuery( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
		
			//----------------上级员工查询-----------------
				var commonQuery_for_parent = ctxPath + "/common/entityList.action?entityName=Employee";
				jQuery("#parentEmployeeList").jqGrid({	
					url : commonQuery_for_parent,
					datatype : "json",
					colNames : [ "<s:text name='员工ID'/>", "<s:text name='员工名称'/>","<s:text name='员工手机'/>","<s:text name='员工类型'/>"],
					colModel : [ 
					{
						name : 'employeeID',
						index : 'employeeID',
						hidden:true,
						align : 'center',
					},{
						name : 'employeeName',
						index : 'employeeName',
						align : 'center',
						sortable : true
					},{
						name : 'employeeMobile',
						index : 'employeeMobile',
						align : 'center',
						sortable : true
					},{
						name : 'employeeTypeMapping',
						index : 'employeeTypeMapping',
						align : 'center',
						sortable : true
					}],
					jsonReader : {
						root : "dataRows",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						id : "employeeID"
					},
					rowNum : 10,
					recordpos: 'hidden',
					rownumbers: true,
					pager : '#donePager',
					sortname : 'employeeID',//默认排序方式
					viewrecords : true,
					sortorder : 'desc',
					caption : '员工列表',
					multiselect : false,
					height : "300",
					width:"570",
					gridComplete : function() {
						jQuery("#_empty", "#parentEmployeeList").addClass("nodrag nodrop");
						jQuery("#parentEmployeeList").tableDnDUpdate();
						var ids = jQuery("#parentEmployeeList").jqGrid('getDataIDs');
						jQuery("#parentEmployeeList").closest(".ui-jqgrid-bdiv").css({
							'overflow' : 'hidden'
						});
					}
				});
					//雇员类型
				var EMPLOYEETYPE={
					MR:1,
					DSM:2,
					PS:3,
					MARKET:4
				}
				
			//----------------MR查询-----------------
				var commonQuery_for_mr = ctxPath + "/common/entityList.action?entityName=Employee"+"&queryConditions[\"employeeType\"]="+EMPLOYEETYPE.MR;
				jQuery("#mrEmployeeList").jqGrid({	
					url : commonQuery_for_mr,
					datatype : "json",
					colNames : [ "<s:text name='mrID'/>", "<s:text name='代表名称'/>","<s:text name='代表手机'/>"],
					colModel : [ 
					{
						name : 'employeeID',
						index : 'employeeID',
						hidden:true,
						align : 'center',
					},{
						name : 'employeeName',
						index : 'employeeName',
						align : 'center',
						sortable : true
					},{
						name : 'employeeMobile',
						index : 'employeeMobile',
						align : 'center',
						sortable : true
					}],
					jsonReader : {
						root : "dataRows",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						id : "employeeID"
					},
					rowNum : 10,
					recordpos: 'hidden',
					rownumbers: true,
					pager : '#donePager',
					sortname : 'employeeID',//默认排序方式
					viewrecords : true,
					sortorder : 'desc',
					caption : '代表列表',
					multiselect : false,
					height : "300",
					width:"570",
					gridComplete : function() {
						jQuery("#_empty", "#mrEmployeeList").addClass("nodrag nodrop");
						jQuery("#mrEmployeeList").tableDnDUpdate();
						var ids = jQuery("#mrEmployeeList").jqGrid('getDataIDs');
						jQuery("#mrEmployeeList").closest(".ui-jqgrid-bdiv").css({
							'overflow' : 'hidden'
						});
					}
				});
				
				function searchEmployee(isParent){
					var employeeName=jQuery("#parent_employeeName").val();
					var employeeMobile=jQuery("#parent_employeeMobile").val();
					var employeeType=EMPLOYEETYPE.MR;
					var url = ctxPath+ "/common/entityList.action?entityName=Employee";
						if(isParent){
							url=url+"&queryConditions[\"employeeName\"]="+ employeeName+"&queryConditions[\"employeeMobile\"]="+ employeeMobile;
						}else{
							url=url+"&queryConditions[\"employeeName\"]="+ employeeName+"&queryConditions[\"employeeMobile\"]="+ employeeMobile+"&queryConditions[\"employeeType\"]="+employeeType;	
						}
						jQuery("#parentEmployeeList").jqGrid('setGridParam', {
						page : 1,
						url : url
					}).trigger("reloadGrid");
				}
				jQuery("#search_parent_modal_search").click(function(){
					searchEmployee(true);
				})
                jQuery("#search_mr_modal_search").click(function(){
					searchEmployee(false);
				})
				jQuery("#employee_parent_employeeName").click(function(){
					jQuery('#search_parent_modal').modal({
						keyboard : false
					});
				})
				jQuery("#new_employee_employeeName").click(function(){
					
				})
				jQuery("#employee_region_regionName").click(function(){
					jQuery('#search_region_modal').modal({
						keyboard : false
					});
				})
				jQuery("#add_parent_from_employee_list").click(function(){
					var selID=jQuery("#parentEmployeeList").jqGrid('getGridParam','selrow');
					if(!selID){
						hAlert("您还未选择!请重新选择,或者取消并关闭.");
						return false;
					}
					var rowdata = jQuery("#parentEmployeeList").jqGrid('getRowData', selID);	
					log(selID)
					log(rowdata)
					var employeeName = rowdata["employeeName"];
					jQuery("#employee_parent_employeeName").val(employeeName);
					jQuery("#employee_mr_employeeName").val(employeeName);
					jQuery("#employee_parent_employeeID").val(selID);	
					jQuery("#employee_mr_employeeID").val(selID);	
					
				})
				jQuery("#cancel_parent_from_employee_list").click(function(){
					jQuery("#employee_parent_employeeName").val("");
					jQuery("#employee_mr_employeeName").val("");
					jQuery("#employee_parent_employeeID").val("");	
					jQuery("#employee_mr_employeeID").val("");	
				})
				jQuery("#add_mr_from_employee_list").click(function(){
					var selID=jQuery("#mrEmployeeList").jqGrid('getGridParam','selrow');
					if(!selID){
						hAlert("您还未选择!请重新选择,或者取消并关闭.");
						return false;
					}
					var rowdata = jQuery("#mrEmployeeList").jqGrid('getRowData', selID);	
					log(selID)
					log(rowdata)
					var employeeName = rowdata["employeeName"];
					jQuery("#new_employee_employeeName").val(employeeName);
					jQuery("#new_employee_employeeID").val(selID);	
				})
				jQuery("#cancel_mr_from_employee_list").click(function(){
					jQuery("#new_employee_employeeName").val("");
					jQuery("#new_employee_employeeID").val("");	
				})
				//Region choose logic
				var setting = {
					view: {
					
					},
					data: {
						key:{
							name:"regionName"
						},
						simpleData: {
							enable: true,
							idKey:'regionID',
							pIdKey:'parentID',
							rootPId:0
						}
					},
					edit: {
						drag: {
							autoExpandTrigger: true,
							isMove:true,
							inner:true
						},
						enable: true,
						showRemoveBtn: false,
						showRenameBtn: false
					},
					callback: {
						onDblClick:clickHandler,
						onClick:clickHandler
					}
				};
				function clickHandler(event,treeId,treeNode){
					 jQuery("#region_id").val(treeNode.regionID);
					 jQuery("#region_name").val(treeNode.regionName);
				}
				jQuery("#cancel_region_from_region_list").click(function(){
					jQuery("#regionID").val("");
					jQuery("#region_name").val("");		
				})
				//定义节点数组，通过Ajax请求初始化数据
				var zNodes;
				jQuery.ajax({
					url : ctxPath + "/region/list.action",
					type : 'POST',
					dataType : 'json',
					success : function(res) {
						if(res.result=="success"){
							zNodes = res.regions;
							//hAlert("数据初始化成功!");
						    jQuery.fn.zTree.init(jQuery("#menuTree"), setting, zNodes);
						    zo = jQuery.fn.zTree.getZTreeObj("menuTree");
							jQuery.fn.zTree.getZTreeObj("menuTree").expandAll(true);
						}else{
							hAlert("数据初始化失败!");
						}
					},
					error : function() {
						hAlert("请求发生错误.");
					}
				});
			function addEmployee(){
					jQuery("#addEmployee").click(function(){
								$("#new_employee_h_title").text("新建员工");
								$("#employee_id").attr("value",'');
								$("#employee_type").attr("value",'0');
								$("#employee_name").attr("value",'' );
								$("#employee_mobile").attr("value",'');
								$("#employee_number").attr("value",'');
								$("#employee_parent_employeeID").attr("value",'');
								$("#region_id").attr("value",'');
								$("#region_name").attr("value",'');
								$("#employee_parent_employeeName").attr("value",'');
								jQuery( "#newEmployee" ).dialog( "open" );
					})
				}
			addEmployee();
			function saveEmployee(){
					var employeeID=jQuery("#employee_id").val();//隐藏域员工ID
					var employeeName=jQuery("#employee_name").val();//员工姓名
					var employeeMobile=jQuery("#employee_mobile").val();//员工手机号
					var regionID=jQuery("#region_id").val();//区域ID
					var parentEmployeeID=jQuery("#employee_parent_employeeID").val();//上级员工ID
					var employeeNumber=jQuery("#employee_number").val();//员工编号
					var employeeType=$("#employee_type").val();//员工类型
  					var type=parseInt(employeeType);
					
					jQuery.ajax({
						url : ctxPath + "/employee/add.action",
						data : {
							"employeeID" : employeeID,
							"employee.employeeName":employeeName,
							"employee.employeeMobile":employeeMobile,
							"employee.employeeType":employeeType,
							"regionID":regionID,
							"employee.employeeNumber":employeeNumber,
							"parentEmployeeID":parentEmployeeID	
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log("success")
							log(res)
							var result = res.result;
							
							if (result == "success") {								
								employee_reload("employeeList");
								$("#newEmployee").dialog( "close" );
							}
						},
						error : function(res) {
							log("error")
							log(res)
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
			}
				function updateEmployee(employeeID){
					var url = ctxPath + "/employee/add.action?employeeID="+ employeeID;
					jQuery.ajax({
						url : url,
						type : 'GET',
						dataType : 'json',
						success : function(res) {
							var result = res.result;
							var employeeID=res.employee.userID;
							var employeeName=res.employee.employeeName;
							var employeeMobile=res.employee.employeeMobile;
							var employeeNumber=res.employee.employeeNumber;
							var employeeType=res.employee.employeeType;
							var parentEmployeeID;
							var parentEmployeeName;
							if(res.employee.parentEmployee!=null){
								parentEmployeeID=res.employee.parentEmployee.userID;
								parentEmployeeName=res.employee.parentEmployee.employeeName;
							}
							var regionID=res.employee.region.regionID;
							var regionName=res.employee.region.regionName;
							
							if (result=="success") {
								$("#new_employee_h_title").text("修改员工");
								$("#employee_id").attr("value",employeeID);
								$("#employee_type").attr("value",employeeType);
								$("#employee_name").attr("value", employeeName);
								$("#employee_mobile").attr("value",employeeMobile);
								$("#employee_number").attr("value",employeeNumber);
								$("#employee_parent_employeeID").attr("value",parentEmployeeID);
								$("#region_id").attr("value",regionID);
								$("#region_name").attr("value",regionName);
								$("#employee_parent_employeeName").attr("value",parentEmployeeName);
								jQuery( "#newEmployee" ).dialog( "open" );
							}
						},
						error : function() {
							alert("操作失败，请确认您的操作无误！");
						}
					});
			}
			function transferEmployee(employeeID){
					jQuery("#current_mr_id").val(employeeID);
					jQuery("#transfer_modal").dialog('open');
			}
			function saveTransferEmployee(){
				var employeeID=jQuery("#current_mr_id").val();//隐藏域员工ID
				var newEmployeeID=jQuery("#new_employee_employeeID").val();
				var parentEmployeeID=jQuery("#employee_mr_employeeID").val();
				jQuery.ajax({
						url : ctxPath + "/employee/transfer.action",
						data : {
							"employeeID" : employeeID,
							"newEmployeeID":newEmployeeID,
							"parentEmployeeID":parentEmployeeID
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log("success")
							log(res)
							var result = res.result;
							
							if (result == "success") {								
								employee_reload("employeeList");
								$("#transfer_modal").dialog( "close" );
							}
						},
						error : function(res) {
							log("error")
							log(res)
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
			}
	
</script>
	</body>
</html>
