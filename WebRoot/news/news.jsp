<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<meta charset="utf-8">
<title>公告管理</title>
<style type="text/css">
</style>

	<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>公告管理</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<h4>公告查询</h4>
						</legend>
						
						<input class="span3" type="hidden" placeholder=".span3" id="search_news_id" name="newsID">

						<div class="control-group">        
							<label class="control-label" for="newsTitle">公告标题</label>
							<div class="controls">	  			
								<input class="span3" type="text" placeholder="输入公告标题" id="search_news_title" name="newsTitle">
							</div>	
						</div>
						<div class="control-group"> 
							<label class="control-label" for="newsType">公告类型</label>
							<div class="controls">	
								<select id="search_news_type">
									<option value="0"></option>
									<option value="1">网站公告</option>
									<option value="2">活动公告</option>
						      </select>				
							</div>	
						</div>
						<div class="form-actions">
				            <button class="btn" id="search_news">查询</button>
				            <button class="btn" type="reset">清除</button>
				        </div>		        				
					</fieldset>
				</form>
			</div>
			<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
			 	<table id="newsList"></table>
				<div id="donePager" style="text-align: center"></div>
				<div style="height:50px;margin-top:5px;">
				<button  class="btn" id="addNews" onclick="addNews()">
					新建
				</button>
				</div>
			</div>
		</div>
		
		<div  id="news_add">
			<div class="modal-header">
				<h3 id="news_h_title"></h3>
			</div>
				<div class="form-horizontal" id="addNewsForm">
				<input type="hidden" id="news_id" name="newsID" >
					<div class="control-group">        
						<label class="control-label" for="news_title">公告标题</label>
						<div class="controls">
							<input type=text id="news_title" name="newsTitle" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="news_type">公告类型</label>
						<div class="controls">
							<select id="news_type" name="newsType">
									<option value="0"></option>
									<option value="1">网站公告</option>
									<option value="2">活动公告</option>
						     </select>		
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="news_content">公告内容</label>
						<div class="controls">
							<textarea name="newsContent"  id="content" cols="100" rows="8" style="width:auto;height:400px;visibility:hidden"></textarea>
						</div>
					</div>
				</div>							
			</div>
			
		</section>
		<%@ include file="/commons/jsp/common_js.jsp"%>
		 <script type="text/javascript" src="<s:url value='/commons/plugin/kindeditor-4.1.4/kindeditor.js'/>"></script>
		<script src="<%=path%>/news/js/news.js"/></script>
		<script>
			var commonQuery = ctxPath + "/common/entityList.action?entityName=News";
		jQuery("#newsList")
		.jqGrid(
				{	
					url : commonQuery,
					datatype : "json",
					colNames : [ "公告ID","公告标题", "公告类型","发布人","发布时间","操作类型" ],
					colModel : [ 
					{
						name : 'newsID',
						index : 'newsID',
						hidden:true,
						align : 'center',
					},{
						name : 'newsTitle',
						index : 'newsTitle',
						align : 'center',
						sortable : true
					},{
						name : 'newsTypeMapping',
						index : 'newsTypeMapping',
						align : 'center',
						sortable : true
					},{
						name : 'realeaser',
						index : 'realeaser',
						align : 'center',
						sortable : true
					},{
						name : 'publishTime',
						index : 'publishTime',
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
						id : "newsID"
					},
					rowNum : <%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
					rownumbers: true,
					pager : '#donePager',
					sortname : 'newsID',//默认排序方式
					viewrecords : true,
					sortorder : 'asc',
					caption : '公告列表',
					multiselect : false,
					height : "280",
					width:"945",
					gridComplete : function() {
						jQuery("#_empty", "#newsList").addClass(
								"nodrag nodrop");
						jQuery("#newsList").tableDnDUpdate();
						var ids = jQuery("#newsList").jqGrid('getDataIDs');
						for ( var i = 0; i < ids.length; i++) {
							var newsID=ids[i];
							var updateAction="<a href=\"javascript:void(0)\" onclick=updateNews("+newsID+") class=jqgridLinkStyle><s:text name='修改' /></a>";
							var deleteAction="<a href=\"javascript:void(0)\" onclick=deleteNews("+newsID+") class=jqgridLinkStyle><s:text name='删除' /></a>";
							jQuery("#newsList").jqGrid('setRowData',
									ids[i], {
									act:updateAction+" | "+deleteAction
									});					
						}
						jQuery("#newsList").closest(".ui-jqgrid-bdiv")
								.css( {
									'overflow' : 'hidden'
								});

					}
				});
			var editor;
  			KindEditor.ready(function(K) {
			/* 初始化设置 */
				editor = K.create('textarea[name="newsContent"]', {
					cssPath : ctxPath + '/commons/plugin/kindeditor-4.1.4/code/prettify.css',
					//uploadJson : "<s:url value='/commons/plugins/kindeditor-4.1.4/jsp/upload_json.jsp'/>",
					uploadJson : ctxPath + '/commons/plugin/kindeditor-4.1.4/jsp/upload_json.jsp',
					fileManagerJson : ctxPath + '/commons/plugin/kindeditor-4.1.4/jsp/file_manager_json.jsp',
					allowFileManager : true,
					allowUpload : true, //允许上传图片 
					readonlyMode : false,
					resizeType :0,
					afterCreate:function(){
						  this.sync(); 
					}, 
					afterBlur:function(){ 
           				 this.sync(); 
     					   }       
				});
			});
  			jQuery("#news_add").dialog({
		            autoOpen: false,
		            height: 450,
		            width: 650,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                   saveNews();
		                },
		                "取消": function() {
		                    $( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
  			function addNews(){
					jQuery("#addNews").click(function(){
								$("#news_h_title").text("新建公告");
								$("#news_title").attr("value", '');
								$("#news_type").attr("value",'0');
								editor.html('');
								jQuery( "#news_add" ).dialog( "open" );
					});
  			}
  			addNews();
  			function saveNews(){
  				var newsID=$("#news_id").val();
  				var newsTitle=$("#news_title").val();
  				var newsType=$("#news_type").val();
  				var type=parseInt(newsType);
  				var newsContent=$("#content").val();
					jQuery.ajax({
						url : ctxPath + "/news/add.action",
						data : {
							"newsID" : newsID,
							"news.newsTitle":newsTitle,
							"news.newsContent":newsContent,
							"news.newsType":type	
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log(res);
							var result = res.result;
							if (result == "success") {
								$("#news_add").dialog( "close" );
								reload();
							}
						},
						error : function() {
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
  			}
  			function updateNews(newsID){
  					var url = ctxPath + "/news/add.action?newsID="+ newsID;
					jQuery.ajax({
						url : url,
						type : 'GET',
						dataType : 'json',
						success : function(res) {
							var result = res.result;
							var newsID=res.news.newsID;
							var newsTitle=res.news.newsTitle;
							var newsType=res.news.newsType;
							var newsContent=res.news.newsContent;
							if (result=="success") {
								$("#news_h_title").text("修改公告");
								$("#news_id").attr("value",newsID);
								$("#news_title").attr("value", newsTitle);
								$("#news_type").attr("value",newsType);
								editor.html(newsContent);
								jQuery( "#news_add" ).dialog( "open" );
							}
						},
						error : function() {
							alert("操作失败，请确认您的操作无误！");
						}
					});
  			}
  			jQuery("#search_news").click(function(){
  				searchNews();
  			});
  			jQuery("#search_news_reset").click(function(){
  				searchReset();
  			});
  			//updateNews(newsID);
</script>
			</div>
		</div>
	</body>
</html>
