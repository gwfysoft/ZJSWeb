function product_reload() {
	jQuery("#productList").jqGrid('setGridParam', {
		page : 1,
		url : commonQuery_for_product
	}).trigger("reloadGrid");
}
function searchReset() {
	$("#search_product_name").attr("value", '');
	product_reload();
}
function searchProduct() {
	var productName = $("#search_product_name").val();
	var url = ctxPath + "/common/entityList.action?entityName=Product";
		url = url + "&queryConditions[\"newsTitle\"]=" + newsTitle+"&queryConditions[\"newsType\"]=" + newsType;
	jQuery("#productList").jqGrid('setGridParam', {
		page : 1,
		type: 'POST',
		url : url,
		dataType : 'json'
	}).trigger("reloadGrid");
}
function deleteProduct(productID) {
	if (!confirm("确认删除吗?")) {
		return;
	}
	jQuery.ajax( {
		url : ctxPath + "/product/delete.action",
		data : {
			"productID" : productID
		},
		type : 'POST',
		dataType : 'json',
		success : function(res) {
			var result = res.result;
			if (result == "success") {
				//highColuAlert('删除成功！');
					product_reload();
	}
},
error : function() {
	//highColuAlert('操作失败,Ajax调用错误,请联系管理员');
		hAlert("删除失败，请确认您的操作无误！");
	}
	});
}