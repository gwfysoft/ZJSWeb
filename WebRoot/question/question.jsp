<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>题目管理</title>
<style type="text/css">
	.ui-widget-content a {
		color: rgb(255, 255, 255);
	}
</style>
</head>
	<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>题目管理</a></li>
			</ul>
		</div>

		<div class="row">
			<div class="span12">
				<form class="form-horizontal">
					<fieldset>
						<legend>
							<h2>题目查询</h2>
						</legend>
						
						<input class="span3" type="hidden" placeholder=".span3" id="search_question_id" name="questionID">

						<div class="control-group">        
							<label class="control-label" for="question_content">题目名称</label>
							<div class="controls">	  			
								<input class="span3" type="text" placeholder="输入题目名称" id="search_question_content" name="questionContent">
							</div>	
						</div>
						<div class="control-group"> 
							<label class="control-label" for="questionType">题目类型</label>
							<div class="controls">	
								<select id="search_question_type" class="span3">
									<option value="0"></option>
									<option value="1">选择题</option>
									<option value="2">问答题</option>
						      </select>				
							</div>	
						</div>
						<div class="form-actions">
				            <button class="btn" id="search_question">查询</button>
				            <button class="btn" type="reset">清除</button>
				        </div>		        				
					</fieldset>
				</form>
			</div>
			<div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
			 	<table id="questionList"></table>
				<div id="donePager" style="text-align: center"></div>
				<div style="height:50px;margin-top:5px;">
				<button  class="btn" id="addQuestion" onclick="addQuestion()">
					新建
				</button>
				</div>
			</div>
		</div>
		
		<div  id="question_add">
			<div class="modal-header">
				<h3 id="question_h_title"></h3>
			</div>
				<div class="form-horizontal" id="addQuestionForm">
				<input type="hidden" id="question_id" name="questionID" >
					<div class="control-group">        
						<label class="control-label" for="question_content">题目内容</label>
						<div class="controls">
							<input type=text id="question_content" name="questionContent" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="question_type">题目类型</label>
						<div class="controls">
							<select id="question_type" name="questionType" class="span3">
									<option value="2"></option>
									<option value="0">选择题</option>
									<option value="1">问答题</option>
						     </select>		
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="question_answer">题目答案</label>
						<div class="controls">
							<input type=text id="question_answer" name="questionAnswer" />
						</div>
					</div>
					<div class="control-group">        
						<label class="control-label" for="question_total_points">题目总分</label>
						<div class="controls">
							<input type=text id="question_total_points" name="questionTotalPoints" />
						</div>
					</div>
				</div>							
			</div>
			
		</section>
		<%@ include file="/commons/jsp/common_js.jsp"%>
		<script src="<%=path%>/question/js/question.js"/></script>
		<script>
			var commonQuery = ctxPath + "/common/entityList.action?entityName=Question";
		jQuery("#questionList")
		.jqGrid(
				{	
					url : commonQuery,
					datatype : "json",
					colNames : [ "题目ID","题目名称","题目答案", "题目类型","题目总分","题目状态","操作类型" ],
					colModel : [ 
					{
						name : 'questionID',
						index : 'questionID',
						hidden:true,
						align : 'center',
					},{
						name : 'questionContent',
						index : 'questionContent',
						align : 'center',
						sortable : true
					},{
						name : 'questionAnswer',
						index : 'questionAnswer',
						align : 'center',
						sortable : true
					}
					,{
						name : 'questionTypeMapping',
						index : 'questionTypeMapping',
						align : 'center',
						sortable : true
					},{
						name : 'questionTotalPoints',
						index : 'questionTotalPoints',
						align : 'center',
						sortable : true
					},{
						name : 'questionStatusMapping',
						index : 'questionStatusMapping',
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
						id : "questionID"
					},
					rowNum : <%=com.jung.common.Constants.DEFAULT_PAGE_SIZE%>,
					rownumbers: true,
					pager : '#donePager',
					sortname : 'questionID',//默认排序方式
					viewrecords : true,
					sortorder : 'asc',
					caption : '题目列表',
					multiselect : false,
					height : "350",
					width:"1070",
					gridComplete : function() {
						jQuery("#_empty", "#questionList").addClass(
								"nodrag nodrop");
						jQuery("#questionList").tableDnDUpdate();
						var ids = jQuery("#questionList").jqGrid('getDataIDs');
						for ( var i = 0; i < ids.length; i++) {
							var questionID=ids[i];
							var updateAction="<a href=\"javascript:void(0)\" onclick=updateQuestion("+questionID+") class=\"btn\">修改</a>";
							var deleteAction="<a href=\"javascript:void(0)\" onclick=deleteQuestion("+questionID+") class=\"btn\">删除</a>";
							jQuery("#questionList").jqGrid('setRowData',
									ids[i], {
									act:updateAction+"  "+deleteAction
									});					
						}
						jQuery("#questionList").closest(".ui-jqgrid-bdiv")
								.css( {
									'overflow' : 'hidden'
								});

					}
				});
  		
  			jQuery("#question_add").dialog({
		            autoOpen: false,
		            height: 450,
		            width: 650,
		            modal: true,
		            buttons: {
		                "保存": function() {
		                   jQuery(this).css("btn");
		                  saveQuestion();
		                },
		                "取消": function() {
		                    $( this ).dialog( "close" );
		                }
		            },
		            close: function() {
		            }
		        });
  			function addQuestion(){
					jQuery("#addQuestion").click(function(){
								$("#question_h_title").text("新建题目");
								$("#question_id").attr("value", '');
								$("#question_content").attr("value", '');
								$("#question_answer").attr("value", '');
								$("#question_total_points").attr("value", '');
								$("#question_type").attr("value",'2');
								jQuery( "#question_add" ).dialog( "open" );
					});
  			}
  			addQuestion();
  			function saveQuestion(){
  				var questionID=$("#question_id").val();
  				var questionContent=$("#question_content").val();
  				var questionAnswer=$("#question_answer").val();
  				var questionType=$("#question_type").val();
  				var questionTotalPoints=$("#question_total_points").val();
  				var type=parseInt(questionType);
  				var points=parseInt(questionTotalPoints);
					jQuery.ajax({
						url : ctxPath + "/question/add.action",
						data : {
							"questionID" : questionID,
							"question.questionAnswer":questionAnswer,
							"question.questionContent":questionContent,
							"question.questionTotalPoints":points,
							"question.questionType":type	
						},
						type : 'POST',
						dataType : 'json',
						success : function(res) {
							log(res);
							var result = res.result;
							if (result == "success") {
								$("#question_add").dialog( "close" );
								question_reload();
							}
						},
						error : function() {
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
  			}
  			function updateQuestion(questionID){
  					var url = ctxPath + "/question/add.action?questionID="+ questionID;
					jQuery.ajax({
						url : url,
						type : 'GET',
						dataType : 'json',
						success : function(res) {
							var result = res.result;
							var questionID=res.question.questionID;
							var questionType=res.question.questionType;
							var questionContent=res.question.questionContent;
							var questionAnswer=res.question.questionAnswer;
							var questionTotalPoints=res.question.questionTotalPoints;
							if (result=="success") {
								$("#question_h_title").text("修改题目");
								$("#question_id").attr("value",questionID);
								$("#question_content").attr("value", questionContent);
								$("#question_answer").attr("value", questionAnswer);
								$("#question_type").attr("value",questionType);
								$("#question_total_points").attr("value", questionTotalPoints);
								jQuery( "#question_add" ).dialog( "open" );
							}
						},
						error : function() {
							hAlert("操作失败，请确认您的操作无误！");
						}
					});
  			}
  			jQuery("#search_question").click(function(){
  				searchQuestion();
  			});
  			jQuery("#search_question_reset").click(function(){
  				searchReset();
  			});
  			//updateQuestion(questionID);
</script>
			</div>
		</div>
	</body>
</html>
