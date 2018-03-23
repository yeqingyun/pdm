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

<script src="./include/js/gnwf/wfRd.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfRd'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#planSDate").length > 0)
		$("#planSDate").ligerDateEditor({showTime: false});
	if ($("#startPlanSDate").length > 0)
		$("#startPlanSDate").ligerDateEditor({showTime: false});
	if ($("#endPlanSDate").length > 0)
		$("#endPlanSDate").ligerDateEditor({showTime: false});
	if ($("#planEDate").length > 0)
		$("#planEDate").ligerDateEditor({showTime: false});
	if ($("#startPlanEDate").length > 0)
		$("#startPlanEDate").ligerDateEditor({showTime: false});
	if ($("#endPlanEDate").length > 0)
		$("#endPlanEDate").ligerDateEditor({showTime: false});
	if ($("#factSDate").length > 0)
		$("#factSDate").ligerDateEditor({showTime: false});
	if ($("#startFactSDate").length > 0)
		$("#startFactSDate").ligerDateEditor({showTime: false});
	if ($("#endFactSDate").length > 0)
		$("#endFactSDate").ligerDateEditor({showTime: false});
	if ($("#factEDate").length > 0)
		$("#factEDate").ligerDateEditor({showTime: false});
	if ($("#startFactEDate").length > 0)
		$("#startFactEDate").ligerDateEditor({showTime: false});
	if ($("#endFactEDate").length > 0)
		$("#endFactEDate").ligerDateEditor({showTime: false});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#scheId").length > 0)
		$("#scheId").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox();
	if ($("#wfDesc").length > 0)
		$("#wfDesc").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#projectNo").length > 0)
		$("#projectNo").ligerTextBox();
	if ($("#preWfNo").length > 0)
		$("#preWfNo").ligerTextBox();

});
</script>
</head>
<body>

<div id="layout1">
	<div position="center">

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">计划开始时间：</td><td><input type="text" id="planSDate" name="wfRd.planSDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.planSDate}"/>"/></td>
			<td height="24" width="90" align="center">计划完成时间：</td><td><input type="text" id="planEDate" name="wfRd.planEDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.planEDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">实际开始时间：</td><td><input type="text" id="factSDate" name="wfRd.factSDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.factSDate}"/>"/></td>
			<td height="24" width="90" align="center">实际完成时间：</td><td><input type="text" id="factEDate" name="wfRd.factEDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.factEDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfRd.createDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfRd.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">进度任务：</td><td><input type="text" id="scheId" name="wfRd.scheId" size="30" validate="{required:true}" value="<c:out value="${wfRd.scheId}"/>"/></td>
			<td height="24" width="90" align="center">工作流定义ID：</td><td><input type="text" id="flowId" name="wfRd.flowId" size="30" validate="{required:true}" value="<c:out value="${wfRd.flowId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfRd.status" size="30" validate="{required:true}" value="<c:out value="${wfRd.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfRd.createBy" size="30" validate="{required:true}" value="<c:out value="${wfRd.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新人：</td><td><input type="text" id="lastUpd" name="wfRd.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfRd.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">工作流标题：</td><td><input type="text" id="wfName" name="wfRd.wfName" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfName}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流内容：</td><td><input type="text" id="wfDesc" name="wfRd.wfDesc" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfDesc}"/>"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfRd.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="projectNo" name="wfRd.projectNo" size="30" validate="{required:true}" value="<c:out value="${wfRd.projectNo}"/>"/></td>
			<td height="24" width="90" align="center">上级工作流：</td><td><input type="text" id="preWfNo" name="wfRd.preWfNo" size="30" validate="{required:true}" value="<c:out value="${wfRd.preWfNo}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>