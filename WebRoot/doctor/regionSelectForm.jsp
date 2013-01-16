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
		<div class="contain">
			<div class="search">
				<fieldset>
					<legend>
						<font style="color: #6D93AB; font-weight: bold;"><s:text
								name="地区管理" />
						</font>
					</legend>
					<div>
						<div class="zTreeDemoBackground left">
							<ul id="menuTree" class="ztree"></ul>
						</div>
						<%--  
					<div align="center">
						<button class="blue" onclick="addTopMenu()">
								<s:text name="添加一级区域" />
						</button>
						<button class="blue" onclick="expandAll()">
								<s:text name="展开所有区域" />
						</button>
					</div>
					--%>
					</div>
				</fieldset>
			</div>
		</div>

		<%--     <%@include file="/commons/jsp/page_buttom.jsp"%>	 --%>
		<%@ include file="/commons/jsp/common_js.jsp"%>
				<script type="text/javascript"
			src="<s:url value='/region/js/region.js'/>">
</script>

		<SCRIPT type="text/javascript">
  		
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
				onDblClick:dbClick
			}
		};
		function dbClick(event,treeId,treeNode){
			 var returnValueObject = new Object();
			 returnValueObject.regionID = treeNode.regionID;
			 returnValueObject.regionName = treeNode.regionName;
			 window.returnValue = returnValueObject;
			 window.close();
		}
		jQuery(function(){
			//定义节点数组，通过Ajax请求初始化数据
			var zNodes;
			jQuery.ajax({
				url : ctxPath + "/region/list.action",
				type : 'POST',
				dataType : 'json',
				success : function(res) {
					if(res.result=="success"){
						zNodes = res.regions;
						//alert("数据初始化成功!");
					    $.fn.zTree.init($("#menuTree"), setting, zNodes);
					    zo = $.fn.zTree.getZTreeObj("menuTree");
						$.fn.zTree.getZTreeObj("menuTree").expandAll(true);
					}else{
						alert("数据初始化失败!");
					}
				},
				error : function() {
					alert("请求发生错误.");
				}
			});
			
		});
		
	</SCRIPT>
	</body>
</html>
