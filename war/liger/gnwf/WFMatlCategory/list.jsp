<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wFMatlCategory.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WFMatlCategory'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'null', name: 'categoryId', align: 'left', width: 120 },
			{ display: 'null', name: 'status', align: 'left', width: 120 },
			{ display: 'null', name: 'desc1', align: 'left', width: 120 },
			{ display: 'null', name: 'desc2', align: 'left', width: 120 },
			{ display: 'null', name: 'categoryNo', align: 'left', width: 120 },
			{ display: 'null', name: 'desc3', align: 'left', width: 120 },
			{ display: 'null', name: 'categoryName', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WFMatlCategory!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

$(function(){
	if ($("#categoryId").length > 0)
		$("#categoryId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#desc1").length > 0)
		$("#desc1").ligerTextBox();
	if ($("#desc2").length > 0)
		$("#desc2").ligerTextBox();
	if ($("#categoryNo").length > 0)
		$("#categoryNo").ligerTextBox();
	if ($("#desc3").length > 0)
		$("#desc3").ligerTextBox();
	if ($("#categoryName").length > 0)
		$("#categoryName").ligerTextBox();

});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="categoryId" name="wFMatlCategory.categoryId"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="status" name="wFMatlCategory.status"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc1" name="wFMatlCategory.desc1"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc2" name="wFMatlCategory.desc2"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="categoryNo" name="wFMatlCategory.categoryNo"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc3" name="wFMatlCategory.desc3"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="categoryName" name="wFMatlCategory.categoryName"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>