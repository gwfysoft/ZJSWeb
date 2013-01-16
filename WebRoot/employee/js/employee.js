function employee_reload() {
					jQuery("#employeeList").jqGrid('setGridParam', {
					page : 1,
						url : commonQuery
					}).trigger("reloadGrid");
				}
function reset(){
	$("#employeeName").attr("value",'');
	$("#employeeMobile").attr("value",'');
	employee_reload();
}
function searchEmployee(){
	var employeeName=$("#employeeName").val();
	var employeeMobile=$("#employeeMobile").val();
	var type=document.getElementById("type"); 
	var employeeType=type.options[type.selectedIndex].value;
	var url = ctxPath+ "/common/entityList.action?entityName=Employee";
	url=url+"&queryConditions[\"employeeName\"]="+ employeeName+"&queryConditions[\"employeeMobile\"]="+ employeeMobile+"&queryConditions[\"employeeType\"]="+employeeType;

//	alert(employeeName);
//	if(employeeName!=""){
//		alert("1");
//		url=url+"&queryConditions[\"employeeName\"]="+ employeeName;
//	}
//	if(employeeMobile!=""){
//		alert("2");
//		url=url+"&queryConditions[\"employeeMobile\"]="+ employeeMobile;
//	}
//	if(employeeType!=0){
//		alert("3");
//		url=url+"&queryConditions[\"employeeType\"]="+ employeeType;
//	}
//	alert(url);
		jQuery("#employeeList").jqGrid('setGridParam', {
		page : 1,
		url : url
	}).trigger("reloadGrid");
}
function deleteEmployee(employeeID){
	if (!confirm("确认删除吗?")) {
		return;
	}
	jQuery.ajax( {
		url : ctxPath+"/employee/delete.action",
		data : {
		"employeeID":employeeID
	},
	type : 'POST',
	dataType : 'json',
	success : function(res) {
		var result = res.result;
		if (result == "success") {
			//highColuAlert('删除成功！');
			employee_reload();
		} 
	},
	error : function() {
		//highColuAlert('操作失败,Ajax调用错误,请联系管理员');
		alert("删除失败，请确认您的操作无误！");
	}
	});
}
function assignPermisson(employeeID){
		window.location.href=ctxPath+"/user_competence/displayUserOwnedRoles.action?userID="+employeeID;
}
function transferEmployee(employeeID){
	window.location.href=ctxPath+"/employee/employeeTransfer.jsp?employeeID="+employeeID;
}
