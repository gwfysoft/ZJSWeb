<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<meta charset="utf-8">
<title>产品管理</title>
<style type="text/css">
</style>

	<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>产品管理</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<h4>产品查询</h4>
						</legend>
						
						<input class="span3" type="hidden" placeholder=".span3" id="search_product_id" name="productID">

						<div class="control-group">        
							<label class="control-label" for="question_content">产品名称</label>
							<div class="controls">	  			
								<input class="span3" type="text" placeholder="输入产品名称" id="search_product_name" name="productName">
							</div>	
						</div>
						<div class="form-actions">
				            <button class="btn" id="search_product">查询</button>
				            <button class="btn" type="reset">清除</button>
				        </div>		        				
					</fieldset>
				</form>
			</div>
			<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
			 	<table id="productList"></table>
				<div id="donePager" style="text-align: center"></div>
				<div style="height:50px;margin-top:5px;">
				<button  class="btn" id="addProduct" onclick="addProduct()">
					新建
				</button>
				</div>
			</div>
		</div>
		
		<div  id="product_add">
			<div class="modal-header">
				<h3 id="product_h_title"></h3>
			</div>
				<div class="form-horizontal" id="addProductForm">
					<input type="hidden" id="product_id" name="productID" >
					<input type="hidden" id="product_image_path" name="productImagePath" >
					<div class="control-group">        
						<label class="control-label" for="product_name">产品名称</label>
						<div class="controls">
							<input type=text id="product_name" name="productName" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="product_points">所需积分</label>
						<div class="controls">
							<input type=text id="product_points" name="productPoints" />
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="product_order">排序位置</label>
						<div class="controls">
							<input type=text id="product_order" name="productOrder" />
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="product_valid_start_date">有效开始日期</label>
						<div class="controls">
							<input type=text id="product_valid_start_date" name="productValidStartDate" onclick="WdatePicker()"/>
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="product_invalid_end_date">失效日期</label>
						<div class="controls">
							<input type=text id="product_invalid_end_date" name="productValidEndDate" onclick="WdatePicker()"/>
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="product_image">产品图片</label>
						<img id="loading" src="product/image/loading.gif" style="display:none;">
						<div class="controls">
							<input type="file" id="product_image" name="image"/>
							 <input type="button" value="上传" class="btn" id="product_image_upload"/>
						</div>
					</div>
				</div>							
			</div>
			
		</section>
		<%@ include file="/commons/jsp/common_js.jsp"%>
		<script src="<%=path%>/product/js/ajaxfileupload.js"/></script>
		<script src="<%=path%>/product/js/product.js"/></script>
		<script>
			var commonQuery_for_product = ctxPath + "/common/entityList.action?entityName=Product";
		jQuery("#productList")
		.jqGrid(
				{	
					url : commonQuery_for_product,
					datatype : "json",
					colNames : [ "产品ID","产品名称", "兑换所需积分","排序序号","有效开始日期","失效日期","操作类型" ],
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
						name : 'productOrder',
						index : 'productOrder',
						align : 'center',
						sortable : true
					},{
						name : 'productValidStartDate',
						index : 'productValidStartDate',
						align : 'center',
						sortable : true
					},{
						name : 'productValidEndDate',
						index : 'productValidEndDate',
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
  		
  			jQuery("#product_add").dialog({
		            autoOpen: false,
		            height: 450,
		            width: 650,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                  saveProduct();
		                },
		                "取消": function() {
		                    $( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
  			function addProduct(){
					jQuery("#addProduct").click(function(){
								$("#product_h_title").text("新建产品");
								$("#product_id").attr("value","");
								$("#product_name").attr("value", '');
								$("#product_points").attr("value",'');
								$("#product_order").attr("value",'');
								$("#product_image_path").attr("value",'');
								jQuery( "#product_add" ).dialog( "open" );
					});
  			}
  			addProduct();
  			function saveProduct(){
  				var productID=$("#product_id").val();
  				var productName=$("#product_name").val();
  				var productPoints=$("#product_points").val();
  				var productOrder=$("#product_order").val();
  				var prodcutValidDate=$("#product_valid_start_date").val();
  				var productInvalidDate=$("#product_invalid_end_date").val();
  				var productImagePath=$("#product_image_path").val();
  				var points=parseInt(productPoints);
  				var order=parseInt(productOrder);
					jQuery.ajax({
						url : ctxPath + "/product/add.action",
						data : {
							"productID" : productID,
							"product.productName":productName,
							"product.productPoints":points,
							"product.productOrder":order,	
							"productValidStartDate":prodcutValidDate,
							"productValidEndDate":productInvalidDate,
							"product.productImgPath":productImagePath
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log(res);
							var result = res.result;
							if (result == "success") {
								$("#product_add").dialog( "close" );
								product_reload();
							}
						},
						error : function() {
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
  			}
  			function updateProduct(productID){
  					var url = ctxPath + "/product/add.action?productID="+ productID;
					jQuery.ajax({
						url : url,
						type : 'GET',
						dataType : 'json',
						success : function(res) {
							var result = res.result;
							var productID=res.product.productID;
							var productName=res.product.productName;
							var productPoints=res.product.productPoints;
							var productOrder=res.product.productOrder;
							var productValidStartDate=res.product.productValidStartDate;
							var productValidEndDate=res.product.productValidEndDate;
							var productImgPath=res.product.productImgPath;
							if (result=="success") {
								$("#product_h_title").text("修改产品");
								$("#product_id").attr("value",productID);
								$("#product_name").attr("value", productName);
								$("#product_points").attr("value",productPoints);
								$("#product_order").attr("value", productOrder);
								$("#product_valid_start_date").attr("value",productValidStartDate);
								$("#product_invalid_end_date").attr("value",productValidEndDate);
								//$("#product_image").attr("value",productImgPath);
								jQuery( "#product_add" ).dialog( "open" );
							}
						},
						error : function() {
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
  			}
  			jQuery("#search_product").click(function(){
  				searchProduct();
  			});
  			jQuery("#search_product_reset").click(function(){
  				searchReset();
  			});
  			jQuery("#product_image_upload").click(function(){
  				var path=$("#product_image").val();
  				var image=$("#product_image").val();
  				/**
  						jQuery.ajax({
						url : ctxPath + "/product/upload.action",
						data : {
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log(res);
							var result = res.result;
							var savePath=res.savePath;
							if (result == "success") {
								$("#product_image_path").val(savePath);
								hAlert("上传成功");
							}
						},
						error : function() {
							hAlert("上传失败！");
						}
					});
					**/
					 ajaxFileUpload();
  			});
		  	function ajaxFileUpload()
			{
		  		 $("#loading")  
		        .ajaxStart(function(){  
		            $(this).show();  
		        })
		        .ajaxComplete(function(){  
		            $(this).hide();  
		        });
				//starting setting some animation when the ajax starts and completes
				/*
					prepareing ajax file upload
					url: the url of script file handling the uploaded files
		                        fileElementId: the file type of input element id and it will be the index of  $_FILES Array()
					dataType: it support json, xml
					secureuri:use secure protocol
					success: call back function when the ajax complete
					error: callback function when the ajax failed
					
		                */
				$.ajaxFileUpload
				(
					{
						url:ctxPath +"/product/upload.action", 
						secureuri:false,
						fileElementId:'product_image',
						dataType: 'json',
						success: function (data, status)
						{
						     if(data.result=="success"){
						    	 hAlert("上传成功");
						     }
						     $("#product_image_path").val(data.fullPath);
						},
						error: function (data, status, e)
						{
							alert(e);
						}
					}
				)
				return false;
			}  
  			//updateProduct(productID);
</script>
			</div>
		</div>
	</body>
</html>
