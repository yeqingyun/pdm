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

<script src="./include/js/gnwf/wfRdSign.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfRdSign'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '工作任务', name: 'taskId', align: 'left', width: 120 },
			{ display: '用户', name: 'userId', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '会签意见', name: 'signText', align: 'left', width: 120 },
			{ display: '工作流编号', name: 'wfNoId', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfRdSign!list.shtml',
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
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#signText").length > 0)
		$("#signText").ligerTextBox();
	if ($("#wfNoId").length > 0)
		$("#wfNoId").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="wfRdSign.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="wfRdSign.endCreateDate"/></td>
			<td height="24" width="90" align="center">工作任务：</td><td><input type="text" id="taskId" name="wfRdSign.taskId"/></td>
			<td height="24" width="90" align="center">用户：</td><td><input type="text" id="userId" name="wfRdSign.userId"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfRdSign.status"/></td>
			<td height="24" width="90" align="center">会签意见：</td><td><input type="text" id="signText" name="wfRdSign.signText"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNoId" name="wfRdSign.wfNoId"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>