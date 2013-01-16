<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta charset="utf-8">
	<title>医师管理</title>
	<link type="text/css" rel="stylesheet"	href="<s:url value='/commons/plugin/zTree/css/zTreeStyle/zTreeStyle.css'/>"/>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>医师管理</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<h3><s:text	name="医师搜索" /></h3>
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
			<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
			 	<table id="doctorList"></table>
				<div id="donePager" style="text-align: center"></div>
				<div style="height:50px;margin-top:5px;">
				<button  class="btn" id="addDoctor">
					<s:text name="新建" />
				</button>
				</div>
			</div>
			
		</div>
		
		<div  id="newDoctor">
			<div class="modal-header">
				<h2>新建医师</h2>
			</div>
				<div class="form-horizontal" id="addDoctorForm">
					<div class="control-group">        
						<label class="control-label" for="doctor_doctorName"><s:text name="医师名称" /></label>
						<div class="controls">
							<s:textfield name="doctor.doctorName"></s:textfield>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="doctor_doctorMobile"><s:text name="医师手机"></s:text></label>
						<div class="controls">
							<s:textfield name="doctor.doctorMobile"></s:textfield>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="doctor_region_regionName"><s:text name="所属地区"></s:text></label>
							<s:hidden name="regionID" id="regionID"></s:hidden>
						<div class="controls">							
							<div class="input-prepend">
				                <span class="add-on">@</span><s:textfield cssClass="input-medium" name="doctor.region.regionName"></s:textfield>
				            </div>
							<a href="#search_region_modal" rel="modal:open" class="btn" style="color:#fff">选择</a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="doctor_doctorJobTitle"><s:text name="医师职称"></s:text></label>
						<div class="controls">
							<s:textfield  name="doctor.doctorJobTitle"></s:textfield>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="doctor_doctorTargetDept"><s:text name="目标科室"></s:text></label>
						<div class="controls">
							<s:textfield name="doctor.doctorTargetDept"></s:textfield>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="doctor_employeeMR_employeeName"><s:text name="销售代表"></s:text></label>
							<s:hidden name="employeeID" id="doctor_employeeMR_employeeID"></s:hidden>
						<div class="controls">
							
							<div class="input-prepend">
				                <span class="add-on">@</span><input class="input-medium" id="doctor_employeeMR_employeeName"  type="text">
				            </div>
							<a href="#search_mr_modal" rel="modal:open" class="btn" style="color:#fff">选择</a>
						</div>
					</div>
				</div>							
			</div>

			<!-- MR choose modal -->
			<div id="search_mr_modal" style="display: none;">
				<div class="modal-header">
					<h3>代表查询</h3>
				</div>
				<div class="modal-body" style="height:350px;max-heigth:500px;">
					<form class="form-horizontal" id="addDoctorForm">
							<input type="hidden" id="employeeID" name="employeeID" />
							<div class="control-group">
								<label class="control-label" for="employeeName">
									<s:text name="代表名称" />
								</label>
								<div class="controls">
									<input type="text" id="employeeName" name="employeeName"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="employeeMobile">
									<s:text name="代表手机" />
								</label>
								<div class="controls">
									<input type="text" id="employeeMobile" name="employeeMobile"/>
								</div>
							</div>
							<div class="control-group">
								<button type="button" class="btn" id="search_mr_modal_search">
									<s:text name="查询" />
								</button>
								<button type="reset" class="btn">
									<s:text name="清除" />
								</button>
							</div>
					</form>
					<div class="jqgridList">
						<table id="employeeList"></table>
						<div id="donePager" style="text-align: center">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" rel="modal:close" id="add_MR_from_employee_list">添加所选MR</a>
				    <a href="#" class="btn" rel="modal:close" id="cancel_MR_from_employee_list">取消并关闭</a>
				</div>
			</div>
			<!-- region choose modal -->
			<div id="search_region_modal" style="display: none;">
				<div class="modal-header">
					<h3>地区选择</h3>
				</div>
				<div class="modal-body" style="height:350px;max-heigth:500px;">
					<form class="form-horizontal" id="addDoctorForm">
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
			<!-- active option  modal -->
			<div id="active_option_modal" style="display: none;">
				<div class="modal-header">
					<h3>激活医师</h3>
				</div>
				<div class="modal-body" style="height:250px;">
					<form class="form-horizontal" id="activeForm">
							<div class="control-group">
								<label class="control-label" for="activeOption">
									输入激活选项
								</label>
								<div class="controls">
									<input type="text" id="active_option" name="activeOption"/>
									<input type="hidden" id="active_doctor_id" name="doctorID"/>
								</div>
							</div>
					</form>
				</div>
			</div>
	</section>
			<%@ include file="/commons/jsp/common_js.jsp"%>

			<script>		


			//update and delete doctor
				function updateDoctor(doctorID) {
					var ret=new Object();				
					if (doctorID)	{
						ret = jQuery("#doctorList").jqGrid('getRowData',doctorID);
						log(ret);
					}
					jQuery("#doctorID").val(ret.doctorID);							 						//隐藏域医师ID
					jQuery("#doctor_doctorName").val(ret.doctorName);					 					//医师姓名
					jQuery("#doctor_doctorMobile").val(ret.doctorMobile);				 					//医师手机号
					jQuery("#regionID").val(ret.regionID);							 						//区域ID
					jQuery("#doctor_region_regionName").val(ret.regionName);							 	//区域Name
					jQuery("#doctor_doctorJobTitle").val(ret.doctorJobTitle);				 				//医师职称
					jQuery("#doctor_doctorTargetDept").val(ret.doctorTargetDept);							//目标科室
					jQuery("#doctor_employeeMR_employeeID").val(ret.employeeID);		  					//销售代表ID
					jQuery("#doctor_employeeMR_employeeName").val(ret.employeeName);		  			 	//销售代表Name
					jQuery(jQuery("#newDoctor h3").get(0)).text("修改医师");
					
					jQuery( "#newDoctor" ).dialog( "open" );								
				}
				function deleteDoctor(doctorID) {
					if (!confirm("确认删除吗?")) {
						return;
					}
					jQuery.ajax({
						url : ctxPath + "/doctor/delete.action",
						data : {
							"doctorID" : doctorID
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log(res)
							var result = res.result;
							if (result == "success") {
								//highColuhAlert('删除成功！');
								jqgrid_reload("doctorList");
								hAlert('删除成功！');
							}
						},
						error : function() {
							//highColuhAlert('操作失败,Ajax调用错误,请联系管理员');
							hAlert("删除失败，请确认您的操作无误！");
						}
					});
				}
       			  function activeDoctor(){
       				 var doctorID=jQuery("#active_doctor_id").val();
					var activeOption=jQuery("#active_option").val();//医师激活选项
					jQuery.ajax({
					url : ctxPath + "/doctor/active.action",
					type : 'POST',
					data : {
						    "doctorID":doctorID,
							"activeOption" : activeOption
						},
					dataType : 'json',
					success : function(res) {
						if(res.result=="success"){
							hAlert("激活成功.");
							jQuery( "#active_option_modal" ).dialog( "close" );
						}
					},
					error : function() {
						hAlert("激活失败.");
					}
				});
				}
			 function openActiveDialog(doctorID){
				 jQuery("#active_doctor_id").val(doctorID);
				 jQuery("#active_option").val('');
				 jQuery( "#active_option_modal" ).dialog( "open" );	
			 }
			jQuery(function(){
				var commonQuery_for_doctor = ctxPath+ "/common/entityList.action?entityName=Doctor";
				jQuery("#doctorList").jqGrid({
					url : commonQuery_for_doctor,
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
					height : "320",
					width : "1070",
					gridComplete : function() {
						jQuery("#_empty", "#doctorList").addClass("nodrag nodrop");
						jQuery("#doctorList").tableDnDUpdate();
						var ids = jQuery("#doctorList").jqGrid('getDataIDs');
						for ( var i = 0; i < ids.length; i++) {
							var doctorID = ids[i];
							var updateAction = "<button href=\"javascript:void(0)\" onclick=updateDoctor("+ doctorID	+ ") class='btn'><s:text name='修改' /></button>";
							var activeAction = "<button href=\"javascript:void(0)\" onclick=openActiveDialog("+ doctorID	+ ") class='btn'><s:text name='激活' /></button>";
							var deleteAction = "<button href=\"javascript:void(0)\" onclick=deleteDoctor("+ doctorID	+ ") class='btn'><s:text name='删除' /></button>";
							jQuery("#doctorList").jqGrid('setRowData',ids[i],
									{
										act : updateAction + " "	+activeAction+ " "	+ deleteAction
									});
						}
						jQuery("#doctorList").closest(".ui-jqgrid-bdiv").css({
							'overflow' : 'hidden'
							});
						}
					});

				/**
				* newDoctor dialog
				*/
				jQuery("#newDoctor").dialog({
		            autoOpen: false,
		            height: 450,
		            width: 650,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                   saveDoctor(jQuery(this));		                   
		                },
		                "取消": function() {
		                    jQuery( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
				jQuery("#active_option_modal").dialog({
		            autoOpen: false,
		            height: 300,
		            width: 450,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                   activeDoctor(jQuery(this));		                   
		                },
		                "取消": function() {
		                    jQuery( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
				
				function addDoctor(){
					jQuery("#addDoctor").click(function(){
						jQuery(jQuery("#newDoctor h3").get(0)).text("新增医师");
						jQuery( "#newDoctor" ).dialog( "open" );
					})
				}

				addDoctor();

				//新增或修改Doctor,GuoJun
				function saveDoctor(dialog){					
					var doctorID=jQuery("#doctorID").val();//隐藏域医师ID
					var doctorName=jQuery("#doctor_doctorName").val();//医师姓名
					var doctorMobile=jQuery("#doctor_doctorMobile").val();//医师手机号
					var regionID=jQuery("#regionID").val();//区域ID
					var doctorJobTitle=jQuery("#doctor_doctorJobTitle").val();//医师职称
					var doctorTargetDept=jQuery("#doctor_doctorTargetDept").val();//目标科室
					var employeeID=jQuery("#doctor_employeeMR_employeeID").val();//销售代表ID
					
					jQuery.ajax({
						url : ctxPath + "/doctor/add.action",
						data : {
							"doctorID" : doctorID,
							"doctor.doctorName":doctorName,
							"doctor.doctorMobile":doctorMobile,
							"regionID":regionID,
							"doctor.doctorJobTitle":doctorJobTitle,
							"doctor.doctorTargetDept":doctorTargetDept,
							"employeeID":employeeID	
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log("success")
							log(res)
							var result = res.result;
							
							if (result == "success") {								
								jqgrid_reload("doctorList");
								dialog.dialog( "close" );
								hAlert('操作成功！');
							}
						},
						error : function(res) {
							log("error")
							log(res)
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
				}
	

				//----------------代表查询-----------------
				
				var commonQuery_for_mr = ctxPath + "/common/entityList.action?entityName=Employee";
				jQuery("#employeeList").jqGrid({	
					url : commonQuery_for_mr,
					datatype : "json",
					colNames : [ "<s:text name='代表ID'/>", "<s:text name='代表名称'/>","<s:text name='代表手机'/>","<s:text name='代表类型'/>"],
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
					caption : '销售代表列表',
					multiselect : false,
					height : "300",
					width:"570",
					gridComplete : function() {
						jQuery("#_empty", "#employeeList").addClass("nodrag nodrop");
						jQuery("#employeeList").tableDnDUpdate();
						var ids = jQuery("#employeeList").jqGrid('getDataIDs');
						jQuery("#employeeList").closest(".ui-jqgrid-bdiv").css({
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
				function searchEmployee(){
					var employeeName=jQuery("#employeeName").val();
					var employeeMobile=jQuery("#employeeMobile").val();
					var employeeType=EMPLOYEETYPE.MR;
					var url = ctxPath+ "/common/entityList.action?entityName=Employee";
						url=url+"&queryConditions[\"employeeName\"]="+ employeeName+"&queryConditions[\"employeeMobile\"]="+ employeeMobile+"&queryConditions[\"employeeType\"]="+employeeType;
						jQuery("#employeeList").jqGrid('setGridParam', {
						page : 1,
						url : url
					}).trigger("reloadGrid");
				}
				jQuery("#search_mr_modal_search").click(function(){
					searchEmployee();
				})

				jQuery("#doctor_employeeMR_employeeName").click(function(){
					jQuery('#search_mr_modal').modal({
						keyboard : false
					});
				})
				jQuery("#doctor_region_regionName").click(function(){
					jQuery('#search_region_modal').modal({
						keyboard : false
					});
				})
				jQuery("#add_MR_from_employee_list").click(function(){
					var selID=jQuery("#employeeList").jqGrid('getGridParam','selrow');
					if(!selID){
						hAlert("您还未选择!请重新选择MR,或者取消并关闭.");
						return false;
					}
					var rowdata = jQuery("#employeeList").jqGrid('getRowData', selID);	
					log(selID)
					log(rowdata)
					var employeeName = rowdata["employeeName"];
					jQuery("#doctor_employeeMR_employeeName").val(employeeName);
					jQuery("#doctor_employeeMR_employeeID").val(selID);		
				})
				jQuery("#cancel_MR_from_employee_list").click(function(){
					jQuery("#doctor_employeeMR_employeeName").val("");
					jQuery("#doctor_employeeMR_employeeID").val("");		
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
					 jQuery("#regionID").val(treeNode.regionID);
					 jQuery("#doctor_region_regionName").val(treeNode.regionName);
				}
				jQuery("#cancel_region_from_region_list").click(function(){
					jQuery("#regionID").val("");
					jQuery("#doctor_region_regionName").val("");		
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

			})
			</script>
</body>
</html>
