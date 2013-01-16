<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>项目管理</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页</a></li>
				<li class="active"><a>项目管理</a></li>
			</ul>
		</div>
		<div class="row" id="homepageInfo" style="height:600px;margin-top:20px;">
                <div class="span12">
                    <div id="literatureDatepickerWraper">
                        选择日期:
                        <input type="text" id="literatureDatepicker" />
                    </div>
                </div>
                <div class="span11" id="literatureContentList">
                    <ul class="nav nav-tabs" style="color: white">
                        <li class="active" >
                            <a class="btn" href="#literatureTabmkb" data-toggle="tab">大区</a>
                        </li>
                        <li>
                            <a class="btn" href="#literatureTabalb" data-toggle="tab">地区</a>
                        </li>
                        <li>
                            <a class="btn" href="#literatureTabkrt" data-toggle="tab">小区</a>
                        </li>
                        <li>
                            <a class="btn" href="#literatureTaba" data-toggle="tab">医院</a>
                        </li>
                    </ul>
                </div>
                <div class="span11" style="position:relative;float:left;top:10px;margin-left:80px;">
                    <table id="projectManage"></table>
                    <!--<div id="donePager" style="text-align: center">
                    </div>-->
                </div>
            </div>
            
	</section>
	<%@ include file="/commons/jsp/common_js.jsp"%>
	<script>
		jQuery(function() {
			$("#literatureDatepicker").datepicker({
				dateFormat : 'yy-MM',
				changeMonth : true,
				changeYear : true
			});
			jQuery("#projectManage").jqGrid({
				url : 'server.php?q=4',
				datatype : "json",
				colNames : ['id', '大区', '总分', '数', '率', '数', '率', '数', '率', '数', '率', '积分数', '正确率', '排名', '数', '率', '数', '率', '数', '率'],
				colModel : [{
					name : 'id',
					index : 'id',
					label : '主键ID',
					hidden : true,
					align : 'center'
				}, {
					name : 'regionName',
					index : 'regionName',
					width : 80,
					label : '区域名称',
					align : 'center'
				}, {
					name : 'sumPoint',
					index : 'sumPoint',
					label : '总分',
					width : 70
				}, {
					name : 'activateNumber',
					index : 'activateNumber',
					label : '医师激活数',
					width : 75,
					align : 'center'
				}, {
					name : 'activateRate',
					index : 'activateRate',
					label : '医师激活率',
					width : 75,
					align : 'center'
				}, {
					name : 'mrLoginNumber',
					index : 'mrLoginNumber',
					label : '代表登录数',
					width : 75,
					align : 'center'
				}, {
					name : 'mrLoginRate',
					index : 'mrLoginRate',
					label : '代表登录率',
					width : 75,
					align : 'center'
				}, {
					name : 'downloadNumber',
					index : 'downloadNumber',
					label : '医师资料下载数',
					width : 100,
					align : 'center'
				}, {
					name : 'downloadRate',
					index : 'downloadRate',
					label : '医师资料下载率',
					width : 70,
					sortable : false
				}, {
					name : 'answerNumber',
					index : 'answerNumber',
					label : '医师答题数',
					width : 75,
					align : 'center'
				}, {
					name : 'answerRate',
					index : 'answerRate',
					label : '医师答题率',
					width : 75,
					align : 'center'
				}, {
					name : 'answerSumPoint',
					index : 'answerSumPoint',
					width : 100,
					label : '答题质量-积分数',
					align : 'center'
				}, {
					name : 'answerAccuracy',
					index : 'answerAccuracy',
					width : 100,
					label : '答题质量-正确率',
					align : 'center'
				}, {
					name : 'ranking',
					index : 'ranking',
					label : '排名',
					align : 'center'
				}, {
					name : 'mkbAnswerNumber',
					index : 'mkbAnswerNumber',
					label : '弥可保答题数',
					width : 75,
					align : 'center'
				}, {
					name : 'mkbAnswerRate',
					index : 'mkbAnswerRate',
					label : '弥可保答题率',
					width : 75,
					align : 'center'
				}, {
					name : 'albAnswerNumber',
					index : 'albAnswerNumber',
					label : '奥力宝答题数',
					width : 75,
					align : 'center'
				}, {
					name : 'albAnswerRate',
					index : 'albAnswerRate',
					label : '奥力宝答题率',
					width : 75,
					align : 'center'
				}, {
					name : 'krtAnswerNumber',
					index : 'krtAnswerNumber',
					label : '快如妥答题数',
					align : 'center'
				}, {
					name : 'krtAnswerRate',
					index : 'krtAnswerRate',
					label : '快如妥答题率',
					width : 75,
					align : 'center'
				}],
				rowNum : 10,
				width : "1000",
				//rowList : [10, 20, 30],
				//pager : '#donePager',
				viewrecords : true,
				sortorder : "desc",
				jsonReader : {
					repeatitems : false
				},
				caption : "区域统计图",
				height : '410'
			});
			jQuery("#projectManage").jqGrid('setGroupHeaders', {
				useColSpanStyle : true,
				groupHeaders : [{
					startColumnName : 'activateNumber',
					numberOfColumns : 2,
					titleText : '医师激活'
				}, {
					startColumnName : 'mrLoginNumber',
					numberOfColumns : 2,
					titleText : '代表登录'
				}, {
					startColumnName : 'downloadNumber',
					numberOfColumns : 2,
					titleText : '医师资料下载'
				}, {
					startColumnName : 'answerNumber',
					numberOfColumns : 2,
					titleText : '医师答题'
				}, {
					startColumnName : 'answerSumPoint',
					numberOfColumns : 2,
					titleText : '答题质量'
				}, {
					startColumnName : 'mkbAnswerNumber',
					numberOfColumns : 2,
					titleText : '弥可保答题'
				}, {
					startColumnName : 'albAnswerNumber',
					numberOfColumns : 2,
					titleText : '奥力宝答题'
				}, {
					startColumnName : 'krtAnswerNumber',
					numberOfColumns : 2,
					titleText : '快如妥答题'
				}]
			});
		});
	</script>
</body>
</html>
