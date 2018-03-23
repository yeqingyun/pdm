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

<script src="./include/js/zrsy/syLog.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'SyLog'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '日志ID', name: 'logId', align: 'left', width: 120 },
			{ display: '用户ID', name: 'userId', align: 'left', width: 120 },
			{ display: '日志代码', name: 'logCode', align: 'left', width: 120 },
			{ display: '日志名称', name: 'logNm', align: 'left', width: 120 },
			{ display: '日志明细', name: 'logText', align: 'left', width: 120 },
			{ display: '记录时间', name: 'logDate', align: 'left', width: 120 },
			{ display: '客户端IP', name: 'ipAddr', align: 'left', width: 120 },
			{ display: 'null', name: 'logType', align: 'left', width: 120 },
			{ display: 'null', name: 'remark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./SyLog!list.shtml',
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
	if ($("#logId").length > 0)
		$("#logId").ligerTextBox({width:145});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox({width:145});
	if ($("#logCode").length > 0)
		$("#logCode").ligerTextBox({width:145});
	if ($("#logNm").length > 0)
		$("#logNm").ligerTextBox({width:145});
	if ($("#logText").length > 0)
		$("#logText").ligerTextBox({width:145});
	if ($("#logDate").length > 0)
		$("#logDate").ligerDateEditor({showTime: false});
	if ($("#startLogDate").length > 0)
		$("#startLogDate").ligerDateEditor({showTime: false});
	if ($("#endLogDate").length > 0)
		$("#endLogDate").ligerDateEditor({showTime: false});
	if ($("#ipAddr").length > 0)
		$("#ipAddr").ligerTextBox({width:145});
	if ($("#logType").length > 0)
		$("#logType").ligerTextBox({width:145});
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">日志ID：</td><td><input type="text" id="logId" name="syLog.logId"/></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="syLog.userId"/></td>
			<td height="24" width="90" align="center">日志代码：</td><td><input type="text" id="logCode" name="syLog.logCode"/></td>
			<td height="24" width="90" align="center">日志名称：</td><td><input type="text" id="logNm" name="syLog.logNm"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">日志明细：</td><td><input type="text" id="logText" name="syLog.logText"/></td>
			<td height="24" width="90" align="center">记录时间始：</td><td><input type="text" id="startLogDate" name="syLog.startLogDate"/></td>
			<td height="24" width="90" align="center">记录时间止：</td><td><input type="text" id="endLogDate" name="syLog.endLogDate"/></td>
			<td height="24" width="90" align="center">客户端IP：</td><td><input type="text" id="ipAddr" name="syLog.ipAddr"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="logType" name="syLog.logType"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="remark" name="syLog.remark"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>