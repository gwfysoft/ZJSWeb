function admin_reload() {
	jQuery("#adminList").jqGrid('setGridParam', {
		page : 1,
		url : commonQuery
	}).trigger("reloadGrid");
}
function reset(){
	$("#name").attr("value",'');
	admin_reload();
}
function searchAdmin(){
	var name=$("#name").val();
	var url = ctxPath+ "/common/entityList.action?entityName=Admin";
	if(name){
		url=url+"&queryConditions[\"name\"]="+ name;
	}
	jQuery("#adminList").jqGrid('setGridParam', {
		page : 1,
		url : url
	}).trigger("reloadGrid");
}
function deleteAdmin(adminID){
	if(!confirm("确认删除吗?"))
	{
		return;
	}
	jQuery.ajax( {
		url : ctxPath+"/admin/delete.action",
		data : {
		"adminID":adminID
	},
	type : 'POST',
	dataType : 'json',
	success : function(res) {
		var result = res.result;
		if (result == "success") {
			admin_reload();
		} 
	},
	error : function() {
		//highColuAlert('操作失败,Ajax调用错误,请联系管理员');
		hAlert("删除失败，请确认您的操作无误！");
	}
	});
}
