<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>积分兑换期数管理</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>积分兑换期数管理</a></li>
			</ul>
		</div>

		<div  id="addAndUpdateProductExchangePeriod_modal" style="display:none">
			<div class="modal-header">
				<h3 id="product_period_h_title"></h3>
			</div>
				<div class="form-horizontal" id="addAndUpdateProductExchangePeriod_form">
					<input type="hidden" id="periodId_input" name="periodId" >
					<div class="control-group">        
						<label class="control-label" for="productExchangePeriodName_input">期数名称</label>
						<div class="controls">
							<input type="text" id="productExchangePeriodName_input" name="productExchangePeriodName" />
						</div>
					</div>
				</div>							
			</div>

		<div class="row" style="margin-top: 20px;" id="literatureContent">
				<div class="span12">
						<ul id="tab" class="nav nav-tabs">
							<li class="active">
								<a href="#home" data-toggle="tab">期数列表</a>
							</li>
						</ul>
						<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
						 	<table id="periodList"></table>
							<div id="donePager" style="text-align: center"></div>
							<div style="height:50px;margin-top:5px;">
								<button  class="btn" id="addPeriod">
									<s:text name="新建" />
								</button>
							</div>
						</div>					
				</div>
			</div>
				<hr />
				<div id="zhai-blogroll" style="margin-top: 15px">
					<ul id="zhaicarousel" class="jcarousel-skin-tango span12"></ul>
				</div>
	</section>
	<%@ include file="/commons/jsp/common_js.jsp"%>
	<script>
		var commonQuery_for_product = ctxPath + "/common/entityList.action?entityName=ProductExchangePeriod";
		jQuery("#periodList").jqGrid({
			url : commonQuery_for_product,
			datatype : "json",
			colNames : ["ID", "积分兑换期数", "最后修改人", "更新日期","操作类型" ],
			colModel : [{
				name : 'periodId',
				index : 'periodId',
				hidden : true,
				align : 'center',
			}, {
				name : 'productExchangePeriodName',
				index : 'productExchangePeriodName',
				align : 'center',
				width : "25%",
				sortable : true
			}, {
				name : 'inputUser',
				index : 'inputUser',
				align : 'center',
				width : "25%",
				sortable : true
			}, {
				name : 'lastUpdate',
				index : 'lastUpdate',
				align : 'center',
				width : "25%",
				sortable : true
			},	{
				name : 'act',
				index : 'act',
				label : '操作类型',
				width : "25%",
				sortable : false,
				align : 'center'
			} ],
			jsonReader : {
				root : "dataRows",
				page : "page",
				total : "total",
				records : "records",
				repeatitems : false,
				id : "periodId"
			},
			rowNum : '<%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>',
			rownumbers : true,
			pager : '#donePager',
			sortname : 'periodId', //默认排序方式
			viewrecords : true,
			sortorder : 'asc',
			caption : '期数列表',
			multiselect : false,
			height : "350",
			width : "1010",
			onSelectRow : function(id) {
				var rowdata = jQuery("#periodList").jqGrid('getRowData', id);
				jQuery("#productExchangePeriodName_input").val(rowdata['productName']);
				jQuery("#prodcut_points").val(rowdata['productPoints']);
				$('#myModal1').modal({
					keyboard : false
				});
			},
			gridComplete : function() {
				jQuery("#_empty", "#periodList").addClass("nodrag nodrop");
				jQuery("#periodList").tableDnDUpdate();
				var ids = jQuery("#periodList").jqGrid('getDataIDs');
				for ( var i = 0; i < ids.length; i++) {
					var doctorID = ids[i];
					var updateAction = "<button href=\"javascript:void(0)\" onclick=addAndUpdateProductExchangePeriod("+ doctorID	+ ") class='btn'><s:text name='修改' /></button>";
					var deleteAction = "<button href=\"javascript:void(0)\" onclick=deleteProductExchangePeriod("+ doctorID	+ ") class='btn'><s:text name='删除' /></button>";
					jQuery("#periodList").jqGrid('setRowData',ids[i],
							{
								act : updateAction + " "	+ deleteAction
							});
				}
				jQuery("#periodList").closest(".ui-jqgrid-bdiv").css({
					'overflow' : 'hidden'
					});
				}
		});

		jQuery("#addAndUpdateProductExchangePeriod_modal").dialog({
			autoOpen : false,
			height : 450,
			width : 650,
			modal : true,
			buttons : {
				"保存" : function() {
					jQuery(this).css("btn");
					addAndUpdateProductExchangePeriodAjax();
				},
				"取消" : function() {
					jQuery(this).dialog("close");
				}
			},
			close : function() {
			}
		});
		
		

		function addAndUpdateProductExchangePeriod(jQgrid_id, periodId) {
			jQuery("#addAndUpdateProductExchangePeriod_modal").dialog("open");
			if (periodId) {//update
				fillFormFromJQGrid(jQgrid_id, periodId);
			} else {//add
				clearForm(periodId);
			}
		}
		jQuery("#addPeriod").click(function(){
			addAndUpdateProductExchangePeriod("periodList");
		});
		
		function clearForm(_id) {
			jQuery("#" + _id + " :input").val("");
		}

		function fillFormFromJQGrid(jQgrid_id, row_id) {
			var ret = new Object();
			if (jQgrid_id && row_id) {
				ret = jQuery("#" + jQgrid_id).jqGrid('getRowData', row_id);
				jQuery("#periodId_input").val(ret.periodId);
				jQuery("#productExchangePeriodName_input").val(ret.productExchangePeriodName);
				log(ret);
			} else {
				log("ZJS-----:function:fillFormFromJQGrid Args error.");
				return false;
			}

		}

		function addAndUpdateProductExchangePeriodAjax() {
			var periodId = jQuery("#periodId_input").val();
			var productExchangePeriodName = jQuery("#productExchangePeriodName_input").val();
			jQuery.ajax({
				url : ctxPath + "/productexchange/addAndUpdateProductExchangePeriod.action",
				data : {
					"periodId" : periodId,
					"productExchangePeriod.productExchangePeriodName" : productExchangePeriodName
				},
				type : 'POST',
				dataType : 'json',
				success : function(res) {
					log("success")
					log(res)
					var result = res.result;
					if (result == "success") {
						jQuery("#addAndUpdateProductExchangePeriod_modal").dialog("close");
						jqgrid_reload("doctorList");
						hAlert('操作成功！');
					}
				},
				error : function(res) {
					hAlert("操作失败，请确认您的操作无误！");
				}
			});

		}

		function deleteProductPeriod() {

		}


		jQuery("#search_product").click(function() {
			searchProduct();
		});

		jQuery("#search_product_reset").click(function() {
			searchReset();
		});
	</script>
</body>
</html>
