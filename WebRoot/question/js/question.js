function question_reload() {
	jQuery("#questionList").jqGrid('setGridParam', {
		page : 1,
		url : commonQuery
	}).trigger("reloadGrid");
}
function deleteQuestion(questionID) {
	if (!confirm("确认删除吗?")) {
		return;
	}
	jQuery.ajax( {
		url : ctxPath + "/question/delete.action",
		data : {
			"questionID" : questionID
		},
		type : 'POST',
		dataType : 'json',
		success : function(res) {
			var result = res.result;
			if (result == "success") {
				//highColuAlert('删除成功！');
					question_reload();
	}
},
error : function() {
	//highColuAlert('操作失败,Ajax调用错误,请联系管理员');
		hAlert("删除失败，请确认您的操作无误！");
	}
	});
}