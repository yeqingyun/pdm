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

<script src="./include/js/gnwf/wfItem.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfItem'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'null', name: 'itemId', align: 'left', width: 120 },
			{ display: 'null', name: 'status', align: 'left', width: 120 },
			{ display: 'null', name: 'flowId', align: 'left', width: 120 },
			{ display: 'null', name: 'itemNo', align: 'left', width: 120 },
			{ display: 'null', name: 'desc2', align: 'left', width: 120 },
			{ display: 'null', name: 'desc5', align: 'left', width: 120 },
			{ display: 'null', name: 'itemName', align: 'left', width: 120 },
			{ display: 'null', name: 'desc3', align: 'left', width: 120 },
			{ display: 'null', name: 'desc4', align: 'left', width: 120 },
			{ display: 'null', name: 'desc1', align: 'left', width: 120 },
			{ display: 'null', name: 'orderBy', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfItem!list.shtml',
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
	if ($("#itemId").length > 0)
		$("#itemId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#itemNo").length > 0)
		$("#itemNo").ligerTextBox();
	if ($("#desc2").length > 0)
		$("#desc2").ligerTextBox();
	if ($("#desc5").length > 0)
		$("#desc5").ligerTextBox();
	if ($("#itemName").length > 0)
		$("#itemName").ligerTextBox();
	if ($("#desc3").length > 0)
		$("#desc3").ligerTextBox();
	if ($("#desc4").length > 0)
		$("#desc4").ligerTextBox();
	if ($("#desc1").length > 0)
		$("#desc1").ligerTextBox();
	if ($("#orderBy").length > 0)
		$("#orderBy").ligerTextBox();

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
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="itemId" name="wfItem.itemId"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="status" name="wfItem.status"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="flowId" name="wfItem.flowId"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="itemNo" name="wfItem.itemNo"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc2" name="wfItem.desc2"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc5" name="wfItem.desc5"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="itemName" name="wfItem.itemName"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc3" name="wfItem.desc3"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc4" name="wfItem.desc4"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc1" name="wfItem.desc1"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="orderBy" name="wfItem.orderBy"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>