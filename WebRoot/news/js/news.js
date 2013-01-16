function reload() {
	jQuery("#newsList").jqGrid('setGridParam', {
		page : 1,
		url : commonQuery
	}).trigger("reloadGrid");
}
function searchReset() {
	$("#search_news_title").attr("value", '');
	$("#search_news_type").attr("value", '0');
	reload();
}
function searchNews() {
	var newsTitle = $("#search_news_title").val();
	var newsType=$("#search_news_type").val();
	var url = ctxPath + "/common/entityList.action?entityName=News";
		url = url + "&queryConditions[\"newsTitle\"]=" + newsTitle+"&queryConditions[\"newsType\"]=" + newsType;
//		jQuery.ajax({
//						url : url,
//						type : 'POST',
//						dataType : 'json',
//						success : function(res) {
//							var result = res.result;
//							if (result == "success") {
//								reload();
//							}
//						},
//						error : function() {
//							hAlert("操作失败，请确认您的操作无误！");
//						}
//					});
	jQuery("#newsList").jqGrid('setGridParam', {
		page : 1,
		type: 'POST',
		url : url,
		dataType : 'json'
	}).trigger("reloadGrid");
}
function deleteNews(newsID) {
	if (!confirm("确认删除吗?")) {
		return;
	}
	jQuery.ajax( {
		url : ctxPath + "/news/delete.action",
		data : {
			"newsID" : newsID
		},
		type : 'POST',
		dataType : 'json',
		success : function(res) {
			var result = res.result;
			if (result == "success") {
				//highColuAlert('删除成功！');
					reload();
	}
},
error : function() {
	//highColuAlert('操作失败,Ajax调用错误,请联系管理员');
		hAlert("删除失败，请确认您的操作无误！");
	}
	});
}
