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

<script src="./include/js/gnwf/wfRdField.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfRdField'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'null', name: 'fieldId', align: 'left', width: 120 },
			{ display: 'null', name: 'wfId', align: 'left', width: 120 },
			{ display: 'null', name: 'rowId', align: 'left', width: 120 },
			{ display: 'null', name: 'fieldText', align: 'left', width: 120 },
			{ display: '集合序列', name: 'wfNo', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfRdField!list.shtml',
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
	if ($("#fieldId").length > 0)
		$("#fieldId").ligerTextBox();
	if ($("#wfId").length > 0)
		$("#wfId").ligerTextBox();
	if ($("#rowId").length > 0)
		$("#rowId").ligerTextBox();
	if ($("#fieldText").length > 0)
		$("#fieldText").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();

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
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="fieldId" name="wfRdField.fieldId"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="wfId" name="wfRdField.wfId"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="rowId" name="wfRdField.rowId"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="fieldText" name="wfRdField.fieldText"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">集合序列：</td><td><input type="text" id="wfNo" name="wfRdField.wfNo"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>