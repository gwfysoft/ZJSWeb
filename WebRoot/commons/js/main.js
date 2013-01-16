function log(log){
	if(console&&console.log){
		console.log(log);
	}else{
		alert(log);
	}
}
function jqgrid_reload(jqgrid_id) {
	jQuery("#"+jqgrid_id).trigger("reloadGrid");
}