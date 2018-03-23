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

<script src="./include/js/gnwf/wfRdTask.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfRdTask'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '要求完成时间', name: 'reqDate', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '接收日期', name: 'acceptDate', align: 'left', width: 120 },
			{ display: '代理日期', name: 'agentDate', align: 'left', width: 120 },
			{ display: 'null', name: 'endDate', align: 'left', width: 120 },
			{ display: '任务ID', name: 'taskId', align: 'left', width: 120 },
			{ display: '上一任务ID', name: 'preTaskId', align: 'left', width: 120 },
			{ display: '步骤ID', name: 'stepId', align: 'left', width: 120 },
			{ display: '发送人', name: 'createBy', align: 'left', width: 120 },
			{ display: '接收人', name: 'acceptBy', align: 'left', width: 120 },
			{ display: '代理人', name: 'agentBy', align: 'left', width: 120 },
			{ display: '任务类型', name: 'taskType', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: 'null', name: 'isSystemFinsh', align: 'left', width: 120 },
			{ display: '备注', name: 'remark', align: 'left', width: 120 },
			{ display: '工作流记录编号', name: 'wfNo', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfRdTask!list.shtml',
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
	if ($("#reqDate").length > 0)
		$("#reqDate").ligerDateEditor({showTime: false});
	if ($("#startReqDate").length > 0)
		$("#startReqDate").ligerDateEditor({showTime: false});
	if ($("#endReqDate").length > 0)
		$("#endReqDate").ligerDateEditor({showTime: false});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#acceptDate").length > 0)
		$("#acceptDate").ligerDateEditor({showTime: false});
	if ($("#startAcceptDate").length > 0)
		$("#startAcceptDate").ligerDateEditor({showTime: false});
	if ($("#endAcceptDate").length > 0)
		$("#endAcceptDate").ligerDateEditor({showTime: false});
	if ($("#agentDate").length > 0)
		$("#agentDate").ligerDateEditor({showTime: false});
	if ($("#startAgentDate").length > 0)
		$("#startAgentDate").ligerDateEditor({showTime: false});
	if ($("#endAgentDate").length > 0)
		$("#endAgentDate").ligerDateEditor({showTime: false});
	if ($("#endDate").length > 0)
		$("#endDate").ligerDateEditor({showTime: false});
	if ($("#startEndDate").length > 0)
		$("#startEndDate").ligerDateEditor({showTime: false});
	if ($("#endEndDate").length > 0)
		$("#endEndDate").ligerDateEditor({showTime: false});
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#preTaskId").length > 0)
		$("#preTaskId").ligerTextBox();
	if ($("#stepId").length > 0)
		$("#stepId").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#acceptBy").length > 0)
		$("#acceptBy").ligerTextBox();
	if ($("#agentBy").length > 0)
		$("#agentBy").ligerTextBox();
	if ($("#taskType").length > 0)
		$("#taskType").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#isSystemFinsh").length > 0)
		$("#isSystemFinsh").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
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
			<td height="24" width="90" align="center">要求完成时间始：</td><td><input type="text" id="startReqDate" name="wfRdTask.startReqDate"/></td>
			<td height="24" width="90" align="center">要求完成时间止：</td><td><input type="text" id="endReqDate" name="wfRdTask.endReqDate"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="wfRdTask.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="wfRdTask.endCreateDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">接收日期始：</td><td><input type="text" id="startAcceptDate" name="wfRdTask.startAcceptDate"/></td>
			<td height="24" width="90" align="center">接收日期止：</td><td><input type="text" id="endAcceptDate" name="wfRdTask.endAcceptDate"/></td>
			<td height="24" width="90" align="center">代理日期始：</td><td><input type="text" id="startAgentDate" name="wfRdTask.startAgentDate"/></td>
			<td height="24" width="90" align="center">代理日期止：</td><td><input type="text" id="endAgentDate" name="wfRdTask.endAgentDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null始：</td><td><input type="text" id="startEndDate" name="wfRdTask.startEndDate"/></td>
			<td height="24" width="90" align="center">null止：</td><td><input type="text" id="endEndDate" name="wfRdTask.endEndDate"/></td>
			<td height="24" width="90" align="center">任务ID：</td><td><input type="text" id="taskId" name="wfRdTask.taskId"/></td>
			<td height="24" width="90" align="center">上一任务ID：</td><td><input type="text" id="preTaskId" name="wfRdTask.preTaskId"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">步骤ID：</td><td><input type="text" id="stepId" name="wfRdTask.stepId"/></td>
			<td height="24" width="90" align="center">发送人：</td><td><input type="text" id="createBy" name="wfRdTask.createBy"/></td>
			<td height="24" width="90" align="center">接收人：</td><td><input type="text" id="acceptBy" name="wfRdTask.acceptBy"/></td>
			<td height="24" width="90" align="center">代理人：</td><td><input type="text" id="agentBy" name="wfRdTask.agentBy"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">任务类型：</td><td><input type="text" id="taskType" name="wfRdTask.taskType"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfRdTask.status"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="isSystemFinsh" name="wfRdTask.isSystemFinsh"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="wfRdTask.remark"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流记录编号：</td><td><input type="text" id="wfNo" name="wfRdTask.wfNo"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>