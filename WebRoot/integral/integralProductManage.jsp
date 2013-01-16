<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>积分兑换管理</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>积分兑换管理</a></li>
			</ul>
		</div>
		<div class="row" style="margin-top: 20px;" id="literatureContent">
				<div class="span12">
						<i class="bookmarking" style="background: url('img/icon-organized.png') no-repeat;"></i>
						<ul id="tab" class="nav nav-tabs">
							<li class="active">
								<a href="#home" data-toggle="tab">产品列表</a>
							</li>
						</ul>
						<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
						 	<table id="productList"></table>
							<div id="donePager" style="text-align: center"></div>
						</div>
						<div class="modal" id="myModal1" style="display: none">

							<div class="modal-header">
								<a class="close" data-dismiss="modal">关闭</a>
								
							</div>
							<div class="modal-body">
								<img src="img/l3.png" alt="">
								<div style="">
								<h3 id="product_name"></h3><h3 id="product_points"></h3>
								</div>
							</div>
							<div class="modal-footer">
								<a href="#" class="btn">确认兑换</a>
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
		var commonQuery_for_product = ctxPath + "/common/entityList.action?entityName=Product";
		jQuery("#productList")
		.jqGrid(
				{	
					url : commonQuery_for_product,
					datatype : "json",
					colNames : [ "产品ID","产品名称", "兑换所需积分","发布日期" ],
					colModel : [ 
					{
						name : 'productID',
						index : 'productID',
						hidden:true,
						align : 'center',
					},{
						name : 'productName',
						index : 'productName',
						align : 'center',
						sortable : true
					},{
						name : 'productPoints',
						index : 'productPoints',
						align : 'center',
						sortable : true
					},{
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
					rownumbers: true,
					pager : '#donePager',
					sortname : 'productID',//默认排序方式
					viewrecords : true,
					sortorder : 'asc',
					caption : '产品列表',
					multiselect : false,
					height : "280",
					width:"945",
					onSelectRow:function(id){
						var rowdata = jQuery("#productList").jqGrid('getRowData', id);
								jQuery("#product_name").val(rowdata['productName']);
						  	 jQuery("#prodcut_points").val(rowdata['productPoints']);
								$('#myModal1').modal({
									keyboard : false
								});
					},
					gridComplete : function() {
						jQuery("#_empty", "#productList").addClass(
								"nodrag nodrop");
						jQuery("#productList").tableDnDUpdate();
						var ids = jQuery("#productList").jqGrid('getDataIDs');
						for ( var i = 0; i < ids.length; i++) {
							var productID=ids[i];
							var updateAction="<a href=\"javascript:void(0)\" onclick=updateProduct("+productID+") class=jqgridLinkStyle><s:text name='修改' /></a>";
							var deleteAction="<a href=\"javascript:void(0)\" onclick=deleteProduct("+productID+") class=jqgridLinkStyle><s:text name='删除' /></a>";
							jQuery("#productList").jqGrid('setRowData',
									ids[i], {
									act:updateAction+" | "+deleteAction
									});					
						}
						jQuery("#productList").closest(".ui-jqgrid-bdiv")
								.css( {
									'overflow' : 'hidden'
								});

					}
				});

	</script>
</body>
</html>
