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
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
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
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">要求完成时间：</td><td><input type="text" id="reqDate" name="wfRdTask.reqDate" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.reqDate}"/>"/></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfRdTask.createDate" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.createDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">接收日期：</td><td><input type="text" id="acceptDate" name="wfRdTask.acceptDate" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.acceptDate}"/>"/></td>
			<td height="24" width="90" align="center">代理日期：</td><td><input type="text" id="agentDate" name="wfRdTask.agentDate" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.agentDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="endDate" name="wfRdTask.endDate" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.endDate}"/>"/></td>
			<td height="24" width="90" align="center">任务ID：</td><td><input type="text" id="taskId" name="wfRdTask.taskId" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.taskId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">上一任务ID：</td><td><input type="text" id="preTaskId" name="wfRdTask.preTaskId" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.preTaskId}"/>"/></td>
			<td height="24" width="90" align="center">步骤ID：</td><td><input type="text" id="stepId" name="wfRdTask.stepId" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.stepId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">发送人：</td><td><input type="text" id="createBy" name="wfRdTask.createBy" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.createBy}"/>"/></td>
			<td height="24" width="90" align="center">接收人：</td><td><input type="text" id="acceptBy" name="wfRdTask.acceptBy" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.acceptBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">代理人：</td><td><input type="text" id="agentBy" name="wfRdTask.agentBy" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.agentBy}"/>"/></td>
			<td height="24" width="90" align="center">任务类型：</td><td><input type="text" id="taskType" name="wfRdTask.taskType" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.taskType}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfRdTask.status" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.status}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="isSystemFinsh" name="wfRdTask.isSystemFinsh" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.isSystemFinsh}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="wfRdTask.remark" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.remark}"/>"/></td>
			<td height="24" width="90" align="center">工作流记录编号：</td><td><input type="text" id="wfNo" name="wfRdTask.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfRdTask.wfNo}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>