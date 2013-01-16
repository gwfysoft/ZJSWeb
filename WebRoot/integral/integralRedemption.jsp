<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>积分兑换</title>
<style type="text/css">
</style>
</head>
<body>
		<section class="container" id="container">
			<div class="subnav">
				<ul class="nav nav-pills">
					<li class="">
						<a href="/index.jsp">首页</a>
					</li>
					<li class="active">
						<a>积分兑换</a>
					</li>
				</ul>
			</div>
			<div class="row" style="margin-top: 20px;" id="literatureContent">
				<div class="span12">
					<ul id="tab" class="nav nav-tabs">
						<li class="active">
							<a href="#home" data-toggle="tab">产品列表</a>
						</li>
					</ul>
					<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
						<table id="productList"></table>
						<div id="donePager" style="text-align: center"></div>
					</div>
				</div>
			</div>
			<hr />
			<div id="zhai-blogroll" style="margin-top: 15px">
				<ul id="zhaicarousel" class="jcarousel-skin-tango span12"></ul>
			</div>
			<div id="redemption_modal" style="display: none">
				<div class="modal-header">
					<h3 >积分兑换</h3>
				</div>
				<div class="modal-body" >
					<div class="control-group">
						<label class="control-label" for="product_name">产品名称</label>
						<input type="hidden" id="product_id" name="productID" />
						<div class="controls">
							<input class="span3" type="text"  id="product_name" name="productName" readonly="readonly">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="prodcut_points">兑换所需积分</label>
						<div class="controls">
							<input class="span3" type="text"  id="product_points" name="prodcutPoints" readonly="readonly">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="doctor_name">选择医师</label>
						<input type="hidden" id="doctor_id" name="doctorID" />
						<div class="controls">
							<input class="span2" id="doctor_name" size="18" type="text" name="doctorName">
							<a href="#doctor_choose_modal" rel="modal:open" class="btn" style="color:#fff">选择</a>
						</div>
					</div>
				</div>
			</div>
			<div id="doctor_choose_modal" style="display: none;">
				<div class="modal-header">
					<h3 id="doctor_h_title">医师查询</h3>
				</div>
				<div class="modal-body" style="height:350px;max-heigth:500px;">
					<form class="form-horizontal" id="doctorForm">
						<div class="control-group">
							<label class="control-label" for="doctorName"> 医师名称 </label>
							<div class="controls">
								<input type="text" id="doctorName" name="doctorName"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="employeeMobile"> 医师手机 </label>
							<div class="controls">
								<input type="text" id="doctorMobile" name="doctorMobile"/>
							</div>
						</div>
						<div class="control-group">
							<button type="button" class="btn" id="search_doctor">
								查询
							</button>
							<button type="reset" class="btn">
								清除
							</button>
						</div>
					</form>
					<div class="jqgridList">
						<table id="doctorList"></table>
						<div id="donePager" style="text-align: center"></div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" rel="modal:close" id="add_doctor_list">添加医师</a>
					<a href="#" class="btn" rel="modal:close" id="cancel_doctor_list">取消并关闭</a>
				</div>
			</div>
		</section>
	<%@ include file="/commons/jsp/common_js.jsp"%>
	<script>
		jQuery(function(){
			window.commonQuery_for_product = ctxPath + "/common/entityList.action?entityName=Product";
			jQuery("#productList").jqGrid({
				url : commonQuery_for_product,
				datatype : "json",
				colNames : ["产品ID", "产品名称", "兑换所需积分", "发布日期"],
				colModel : [{
					name : 'productID',
					index : 'productID',
					hidden : true,
					align : 'center',
				}, {
					name : 'productName',
					index : 'productName',
					align : 'center',
					sortable : true
				}, {
					name : 'productPoints',
					index : 'productPoints',
					align : 'center',
					sortable : true
				}, {
					name : 'productValidStartDate',
					index : 'productValidStartDate',
					align : 'center',
					sortable : true
				}],
				jsonReader : {
					root : "dataRows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					id : "productID"
				},
				rowNum : <%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
				rownumbers : true,
				pager : '#donePager',
				sortname : 'productID', //默认排序方式
				viewrecords : true,
				sortorder : 'asc',
				caption : '产品列表',
				multiselect : false,
				height : "280",
				width : "945",
				onSelectRow : function(id) {
					var rowdata = jQuery("#productList").jqGrid('getRowData', id);
					jQuery("#product_id").val(rowdata['productID']);
					jQuery("#product_name").val(rowdata['productName']);
					jQuery("#product_points").val(rowdata['productPoints']);
					openRedemption_modal();
				},
				gridComplete : function() {
					jQuery("#_empty", "#productList").addClass("nodrag nodrop");
					jQuery("#productList").tableDnDUpdate();
					jQuery("#productList").closest(".ui-jqgrid-bdiv").css({
						'overflow' : 'hidden'
					});

				}
			});

			var commonQuery_for_doctor = ctxPath + "/common/entityList.action?entityName=Doctor";
			jQuery("#doctorList").jqGrid({
				url : commonQuery_for_doctor,
				datatype : "json",
				colNames : ["医师ID", "医师名称", "医师手机", "医师积分", "医师状态"],
				colModel : [{
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
					name : 'doctorPoints',
					index : 'doctorPoints',
					align : 'center',
					sortable : true
				}, {
					name : 'doctorStatusMapping',
					index : 'doctorStatusMapping',
					align : 'center',
					sortable : true
				}],
				jsonReader : {
					root : "dataRows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					id : "doctorID"
				},
				rowNum : <%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
				rownumbers : true,
				pager : '#donePager',
				sortname : 'doctorID',
				viewrecords : true,
				sortorder : 'asc',
				caption : "<s:text name='医师'/>",
				multiselect : false,
				height : "320",
				width : "1070",
				gridComplete : function() {
					jQuery("#_empty", "#doctorList").addClass("nodrag nodrop");
					jQuery("#doctorList").tableDnDUpdate();
				}
			});

			/**
			 * redemption_modal dialog
			 */
			jQuery("#redemption_modal").dialog({
				autoOpen : false,
				height : 400,
				width : 650,
				modal : true,
				buttons : {
					"确认兑换" : function() {
						jQuery(this).css("btn");
						var productID = jQuery("#product_id").val();
						var productPoints = jQuery("#product_points").val();
						var doctorID = jQuery("#doctor_id").val();
						jQuery.ajax({
							url : ctxPath + "/productexchangerecord/add.action",
							data : {
								"productID" : productID,
								"productPoints" : productPoints,
								"doctorID" : doctorID
							},
							type : 'POST',
							dataType : 'json',
							success : function(res) {
								var result = res.result;
								if (result == "success") {
									jQuery("#redemption_modal").dialog("close");
									productex_reload();

								}
							},
							error : function(res) {
								hAlert("操作失败，请确认您的操作无误！");
							}
						});
					},
					"取消" : function() {
						jQuery(this).dialog("close");
					}
				},
				close : function() {
				}
			});
			function openRedemption_modal() {
				jQuery("#redemption_modal").dialog("open");
			}


			jQuery("#doctor_name").click(function() {
				jQuery('#doctor_choose_modal').modal({
					keyboard : false
				});
			})
			jQuery("#add_doctor_list").click(function() {
				var selID = jQuery("#doctorList").jqGrid('getGridParam', 'selrow');
				if (!selID) {
					hAlert("您还未选择!请重新选择,或者取消并关闭.");
					return false;
				}
				var rowdata = jQuery("#doctorList").jqGrid('getRowData', selID);
				var doctorName = rowdata["doctorName"];
				jQuery("#doctor_name").val(doctorName);
				jQuery("#doctor_id").val(selID);
			})
			function closeModal() {
				jQuery.modal.close();
			}

			function productex_reload() {
				jQuery("#productList").jqGrid('setGridParam', {
					page : 1,
					url : commonQuery_for_product
				}).trigger("reloadGrid");
			}
		})
	</script>
</body>
</html>
