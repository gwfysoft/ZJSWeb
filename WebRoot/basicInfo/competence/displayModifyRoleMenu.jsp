<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<%@ include file="/commons/jsp/common_head_for_jsp.jsp"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>修改用户角色</title>
<link type="text/css"
			href="<%=path%>/basicInfo/competence/css/demo.css" rel="stylesheet">
			<link type="text/css" rel="stylesheet"
				href="<%=path%>/commons/plugin/zTree/css/zTreeStyle/zTreeStyle.css" />
			<link type="text/css" rel="stylesheet"
				href="<s:url value='/commons/css/commons.css'/>" />
			<link type="text/css" rel="stylesheet"
				href="<s:url value='/commons/css/layer.css'/>" />
</head>
<body>
<section class="container" id="container">
		<div class="contain">
			<div class="content_wrap" style="margin: auto;padding-top:120px">
				<div style="margin: auto; margin-top: 10px;">
					<form
						action="<%=path%>/user_competence/modifyRoleOwnedMenus.action"
						id="menuForm">
						角色名称:
						<input type="text" name="roleName" value="${roleName}"  style="height:24px"/>
						<input type="hidden" id="mId" name="roleForUserMenus" />
						<input type="hidden" id="modifyCode" name="modifyCode" />
						<input type="hidden" name=roleID value="${roleID}" />
							<input type="hidden" name=userID value="${userID}" />
						<input type="hidden" name="checkedMenusID"
							value="${checkedMenusID}" />
					</form>
				</div>
				<div class="zTreeDemoBackground left" style="margin: auto;">
					<ul id="treeDemo" class="ztree">

					</ul>
				</div>

			</div>
			<br />
			<br />
			<div style="margin: auto; width: 600px; margin-top: 10px;">
				<input style="cursor:pointer" type="button" value="修改" onclick="updateRC()" />
				<input style="cursor:pointer" type="button" value="返回" onclick="javascript:history.go(-1)" />
			</div>
		</div>
</section>
<%@ include file="/commons/jsp/common_js.jsp"%>
			<script type="text/javascript"
			src="<%=path%>/commons/plugin/zTree/js/jquery.ztree.core-3.2.js">
		</script>
		<script
			src="<%=path%>/commons/plugin/zTree/js/jquery.ztree.excheck-3.2.js"
			type="text/javascript">
		</script>
		<script
			src="<%=path%>/commons/plugin/zTree/js/jquery.ztree.exedit-3.2.js"
			type="text/javascript">
		</script>
				<script type="text/javascript">

var setting = {
	view : {
		dblClickExpand : true

	},
	check : {
		enable : true,
		nocheckInherit : true
	},

	data : {
		simpleData : {
			enable : true
		}
	}
};

$(document).ready(function(){
            	var zNodes = ${menuTree};
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                $("#1").attr("checked","true");
            });
           function updateRC(){
        	
        	var mId="";
        	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
        	nodes=treeObj.getCheckedNodes(true);
        	for(var i=0;i<nodes.length;i++){
        		if(mId!=""){
        			mId = mId+","+nodes[i].id;    
        		}
        		else{
        			mId = nodes[i].id;
        		}  
        	}
        	document.getElementById("mId").value=mId;
        	$("#menuForm").submit();
        }
           function deleteRoleForUser(){
        	     if (!confirm("确认删除吗?")) {
						return;
				}
            	window.location.href = "<%=path%>/user_competence/deleteRoleForUser.action?roleID="+'${roleID}'+"&userID="+'${userID}';
           }
        </script>
	</body>
</html>
