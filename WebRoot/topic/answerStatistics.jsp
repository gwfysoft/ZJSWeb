<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>答案统计</title>
<style type="text/css">
</style>
</head>
<body>
	<section class="container" id="container">
		<div class="subnav">
			<ul class="nav nav-pills">
				<li class=""><a href="/index.jsp">首页//</a></li>
				<li class="active"><a>答案统计</a></li>
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
                    <table id="integralStatisticsTable"></table>
                    <!--<div id="donePager" style="text-align: center">
                    </div>-->
                </div>
            </div>
	</section>
	<%@ include file="/commons/jsp/common_js.jsp"%>
	<script>
            jQuery(function() {
                function setCenter(id) {
                    var clientWidth = window.document.body.clientWidth;
                    var divWidth = $("#" + id).width();
                    var leftOffset = (clientWidth - divWidth) / 2;
                    $("#" + id).css({
                        left : leftOffset
                    });
                }
                $("#literatureDatepicker").datepicker({
                    dateFormat : 'yy-MM',
                    changeMonth : true,
                    changeYear : true
                });
                
                $("#zhai-middle-area,#zhai-small-area,#zhai-hospital-area,#modal-backdrop-middle,#modal-backdrop-small,#modal-backdrop-hospital").hide();
                $("#modal-backdrop-middle,#modal-backdrop-small,#modal-backdrop-hospital").addClass("ufo-modal-backdrop");
                
                $("#modal-backdrop-middle").click(function(){
                    $(this).fadeOut("slow");
                    $("#zhai-middle-area,#modal-backdrop-middle").fadeOut("slow");
                })
                $("#modal-backdrop-small").click(function(){
                    $(this).fadeOut("slow");
                    $("#zhai-small-area,#modal-backdrop-small").fadeOut("slow");
                })
                $("#modal-backdrop-hospital").click(function(){
                    $(this).fadeOut("slow");
                    $("#zhai-hospital-area,#modal-backdrop-hospital").fadeOut("slow");
                })
                
                
                function showSearchResult(type, id) {
                    switch (type) {
                        case "small":
                            $("#modal-backdrop-small,#zhai-small-area").fadeIn("slow");
                            //$("#zhai-middle-area,#zhai-hospital-area").fadeOut("slow");
                            setCenter("zhai-small-area");
                            break;
                        case "middle":
                            $("#modal-backdrop-middle,#zhai-middle-area").fadeIn("slow");
                            //$("#zhai-small-area,#zhai-hospital-area").fadeOut("slow");
                            setCenter("zhai-middle-area");
                            break;
                        case "hospital":
                            $("#modal-backdrop-hospital,#zhai-hospital-area").fadeIn("slow");
                            //$("#zhai-small-area,#zhai-middle-area").fadeOut("slow");
                            setCenter("zhai-hospital-area");
                            break;
                    }
                }
                jQuery("#integralStatisticsTable").jqGrid({
                    //url : './js/simulationIntegralJson.json',
                    datatype : "local",
                    colNames : ['id', '区域名', '平均获得积分数', '答题率', '答题总人数', '答题数'],
                    colModel : [{
                        name : 'id',
                        index : 'id',
                        label : '主键ID',
                        hidden : true,
                        align : 'center'
                    }, {
                        name : 'regionName',
                        index : 'regionName',
                        width : 200,
                        label : '区域名称',
                        align : 'center'
                    }, {
                        name : 'integralTotal',
                        index : 'integralTotal',
                        label : '平均获得积分数',
                        width : 200
                    }, {
                        name : 'productTotal',
                        index : 'productTotal',
                        label : '答题率',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'peopleTotal',
                        index : 'peopleTotal',
                        label : '答题总人数',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'integralPeriod',
                        index : 'integralPeriod',
                        label : '答题数',
                        width : 200,
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
                    caption : "答题统计图(大区)",
                    height : '310',
                    onSelectRow : function(id) {
                       showSearchResult("middle",id);
                    }
                });
                
                jQuery("#zhai-middle-area-table").jqGrid({
                    //url : './js/simulationIntegralJson.json',
                    datatype : "local",
                    colNames : ['id', '区域名', '平均获得积分数', '答题率', '答题总人数', '答题数'],
                    colModel : [{
                        name : 'id',
                        index : 'id',
                        label : '主键ID',
                        hidden : true,
                        align : 'center'
                    }, {
                        name : 'regionName',
                        index : 'regionName',
                        width : 200,
                        label : '区域名称',
                        align : 'center'
                    }, {
                        name : 'integralTotal',
                        index : 'integralTotal',
                        label : '平均获得积分数',
                        width : 200
                    }, {
                        name : 'productTotal',
                        index : 'productTotal',
                        label : '答题率',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'peopleTotal',
                        index : 'peopleTotal',
                        label : '答题总人数',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'integralPeriod',
                        index : 'integralPeriod',
                        label : '答题数',
                        width : 200,
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
                    caption : "答题统计图(地区)",
                    height : '310',
                    onSelectRow : function(id) {
                       showSearchResult("middle",id);
                    }
                });
                
                jQuery("#zhai-small-area-table").jqGrid({
                    //url : './js/simulationIntegralJson.json',
                    datatype : "local",
                    colNames : ['id', '区域名', '平均获得积分数', '答题率', '答题总人数', '答题数'],
                    colModel : [{
                        name : 'id',
                        index : 'id',
                        label : '主键ID',
                        hidden : true,
                        align : 'center'
                    }, {
                        name : 'regionName',
                        index : 'regionName',
                        width : 200,
                        label : '区域名称',
                        align : 'center'
                    }, {
                        name : 'integralTotal',
                        index : 'integralTotal',
                        label : '平均获得积分数',
                        width : 200
                    }, {
                        name : 'productTotal',
                        index : 'productTotal',
                        label : '答题率',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'peopleTotal',
                        index : 'peopleTotal',
                        label : '答题总人数',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'integralPeriod',
                        index : 'integralPeriod',
                        label : '答题数',
                        width : 200,
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
                    caption : "答题统计图(小区)",
                    height : '310',
                    onSelectRow : function(id) {
                       showSearchResult("middle",id);
                    }
                });
                
                jQuery("#zhai-hospital-area-table").jqGrid({
                    //url : './js/simulationIntegralJson.json',
                    datatype : "local",
                    colNames : ['id', '区域名', '平均获得积分数', '答题率', '答题总人数', '答题数'],
                    colModel : [{
                        name : 'id',
                        index : 'id',
                        label : '主键ID',
                        hidden : true,
                        align : 'center'
                    }, {
                        name : 'regionName',
                        index : 'regionName',
                        width : 200,
                        label : '区域名称',
                        align : 'center'
                    }, {
                        name : 'integralTotal',
                        index : 'integralTotal',
                        label : '平均获得积分数',
                        width : 200
                    }, {
                        name : 'productTotal',
                        index : 'productTotal',
                        label : '答题率',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'peopleTotal',
                        index : 'peopleTotal',
                        label : '答题总人数',
                        width : 200,
                        align : 'center'
                    }, {
                        name : 'integralPeriod',
                        index : 'integralPeriod',
                        label : '答题数',
                        width : 200,
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
                    caption : "答题统计图(医院)",
                    height : '310',
                    onSelectRow : function(id) {
                       showSearchResult("middle",id);
                    }
                });
                var bigAreaData = [
                                {id:"1",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"2",regionName:"东北大区",integralTotal:"2231",productTotal:"22132",peopleTotal:"3231",integralPeriod:"2012-11-01"},
                                {id:"3",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-9-01"},
                                {id:"4",regionName:"西南大区",integralTotal:"555",productTotal:"222",peopleTotal:"232",integralPeriod:"2006-10-01"},
                                {id:"5",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"6",regionName:"西南大区",integralTotal:"66",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"7",regionName:"西南大区",integralTotal:"127631",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"8",regionName:"北京大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"9",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"10",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"11",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"12",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"},
                                {id:"13",regionName:"西南大区",integralTotal:"1231",productTotal:"222",peopleTotal:"232",integralPeriod:"2007-10-01"}
                            ];
                for(var i=0;i<=bigAreaData.length;i++){
                    jQuery("#integralStatisticsTable").jqGrid('addRowData',i+1,bigAreaData[i]);
                    jQuery("#zhai-middle-area-table").jqGrid('addRowData',i+1,bigAreaData[i]);
                    jQuery("#zhai-small-area-table").jqGrid('addRowData',i+1,bigAreaData[i]);
                    jQuery("#zhai-hospital-area-table").jqGrid('addRowData',i+1,bigAreaData[i]);
                }
            });
            </script>
</body>
</html>