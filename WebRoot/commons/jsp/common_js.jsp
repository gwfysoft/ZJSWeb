<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/commons/jsp/taglibs.jsp"%>
<!--  javascript ================================================== -->
<script src="${ctxPath}/commons/js/vendor/jquery-1.8.3.min.js"></script>
<script src="${ctxPath}/commons/js/vendor/jquery-ui-1.9.2.custom.min.js"></script>
<script src="${ctxPath}/commons/js/vendor/modernizr-2.6.2.min.js"></script>
<script src="${ctxPath}/commons/js/vendor/bootstrap.js"></script>
<script src="${ctxPath}/commons/js/vendor/bootstrap-carousel.js"></script>
<script src="${ctxPath}/commons/js/vendor/bootstrap-tab.js"></script>
<script src="${ctxPath}/commons/js/vendor/jquery.jcarousel.min.js"></script>
<script src="${ctxPath}/commons/js/vendor/jquery.modal.js"></script>
<script src="${ctxPath}/commons/js/vendor/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctxPath}/commons/js/vendor/jqgrid/i18n/grid.locale-cn.js"></script>
<script src="${ctxPath}/commons/js/vendor/jqgrid/jquery.jqGrid.min.js"></script>
<script src="${ctxPath}/commons/js/vendor/jqgrid/jquery.tablednd.js"></script>
<!-- javascript lib file -->
<script src="<s:url value='/commons/js/common.js'/>"></script>
<script src="<s:url value='/commons/plugin/jqPagination/js/jquery.jqpagination.js'/>"></script>
<script src="<s:url value='/commons/plugin/alert/jquery.alert.js'/>"></script>
<script src="<s:url value='/commons/plugin/DatePicker/WdatePicker.js'/>"></script>
<!-- add by Luxinglin -->
<script src="<s:url value='/commons/js/function.js'/>"></script>
<script src="<s:url value='/commons/js/jquery.form.js'/>"></script>
<script src="<s:url value='/commons/js/main.js'/>"></script>
<script>
			var mycarousel_itemList = [{
				url : ctxPath+"/commons/img/ad01.png",
				title : "Flower1"
			}, {
				url : ctxPath+"/commons/img/ad02.png",
				title : "Flower2"
			}, {
				url : ctxPath+"/commons/img/ad03.png",
				title : "Flower3"
			}, {
				url : ctxPath+"/commons/img/ad04.png",
				title : "Flower4"
			}, {
				url : ctxPath+"/commons/img/ad01.png",
				title : "Flower1"
			}, {
				url : ctxPath+"/commons/img/ad02.png",
				title : "Flower2"
			}, {
				url : ctxPath+"/commons/img/ad03.png",
				title : "Flower3"
			}, {
				url : ctxPath+"/commons/img/ad04.png",
				title : "Flower4"
			}, {
				url : ctxPath+"/commons/img/ad01.png",
				title : "Flower1"
			}, {
				url : ctxPath+"/commons/img/ad02.png",
				title : "Flower2"
			}, {
				url : ctxPath+"/commons/img/ad03.png",
				title : "Flower3"
			}, {
				url : ctxPath+"/commons/img/ad04.png",
				title : "Flower4"
			}, {
				url : ctxPath+"/commons/img/ad01.png",
				title : "Flower1"
			}, {
				url : ctxPath+"/commons/img/ad01.png",
				title : "Flower1"
			}];

			function mycarousel_itemLoadCallback(carousel, state) {
				for (var i = carousel.first; i <= carousel.last; i++) {
					if (carousel.has(i)) {
						continue;
					}

					if (i > mycarousel_itemList.length) {
						break;
					}

					carousel.add(i, mycarousel_getItemHTML(mycarousel_itemList[i - 1]));
				}
			};

			function mycarousel_getItemHTML(item) {
				return '<img src="' + item.url + '" width="75" height="75" alt="' + item.url + '" />';
			};

			jQuery(function() {
				$("#regionProgressContent").sortable();
				$("#regionProgressContent").disableSelection();

				$("#regionProgressContent li").addClass("btn");

				jQuery('#zhaicarousel').jcarousel({
					scroll : 1,
					auto : 3,
					wrap : 'circular',

					size : mycarousel_itemList.length,
					itemLoadCallback : {
						onBeforeAnimation : mycarousel_itemLoadCallback
					}
				});
			});
</script>