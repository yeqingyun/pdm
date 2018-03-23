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

<script src="./include/js/gnwf/wfStep.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfStep'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
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
	if ($("#stepId").length > 0)
		$("#stepId").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#preStep").length > 0)
		$("#preStep").ligerTextBox();
	if ($("#stepType").length > 0)
		$("#stepType").ligerTextBox();
	if ($("#sort").length > 0)
		$("#sort").ligerTextBox();
	if ($("#isAuto").length > 0)
		$("#isAuto").ligerTextBox();
	if ($("#isUpdForm").length > 0)
		$("#isUpdForm").ligerTextBox();
	if ($("#isSysFinsh").length > 0)
		$("#isSysFinsh").ligerTextBox();
	if ($("#timeQty").length > 0)
		$("#timeQty").ligerTextBox();
	if ($("#selectCom").length > 0)
		$("#selectCom").ligerTextBox();
	if ($("#isLastStep").length > 0)
		$("#isLastStep").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#selectDept").length > 0)
		$("#selectDept").ligerTextBox();
	if ($("#waitAuxiliary").length > 0)
		$("#waitAuxiliary").ligerTextBox();
	if ($("#stepName").length > 0)
		$("#stepName").ligerTextBox();
	if ($("#stepDesc").length > 0)
		$("#stepDesc").ligerTextBox();
	if ($("#stepUri").length > 0)
		$("#stepUri").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfStep.createDate" size="30" validate="{required:true}" value="<c:out value="${wfStep.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfStep.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfStep.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">步骤ID：</td><td><input type="text" id="stepId" name="wfStep.stepId" size="30" validate="{required:true}" value="<c:out value="${wfStep.stepId}"/>"/></td>
			<td height="24" width="90" align="center">工作流定义ID：</td><td><input type="text" id="flowId" name="wfStep.flowId" size="30" validate="{required:true}" value="<c:out value="${wfStep.flowId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">上一步骤：</td><td><input type="text" id="preStep" name="wfStep.preStep" size="30" validate="{required:true}" value="<c:out value="${wfStep.preStep}"/>"/></td>
			<td height="24" width="90" align="center">步骤类型：</td><td><input type="text" id="stepType" name="wfStep.stepType" size="30" validate="{required:true}" value="<c:out value="${wfStep.stepType}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="wfStep.sort" size="30" validate="{required:true}" value="<c:out value="${wfStep.sort}"/>"/></td>
			<td height="24" width="90" align="center">是否自动流转：</td><td><input type="text" id="isAuto" name="wfStep.isAuto" size="30" validate="{required:true}" value="<c:out value="${wfStep.isAuto}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="isUpdForm" name="wfStep.isUpdForm" size="30" validate="{required:true}" value="<c:out value="${wfStep.isUpdForm}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="isSysFinsh" name="wfStep.isSysFinsh" size="30" validate="{required:true}" value="<c:out value="${wfStep.isSysFinsh}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="timeQty" name="wfStep.timeQty" size="30" validate="{required:true}" value="<c:out value="${wfStep.timeQty}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="selectCom" name="wfStep.selectCom" size="30" validate="{required:true}" value="<c:out value="${wfStep.selectCom}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后步骤：</td><td><input type="text" id="isLastStep" name="wfStep.isLastStep" size="30" validate="{required:true}" value="<c:out value="${wfStep.isLastStep}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfStep.status" size="30" validate="{required:true}" value="<c:out value="${wfStep.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfStep.createBy" size="30" validate="{required:true}" value="<c:out value="${wfStep.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfStep.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfStep.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="selectDept" name="wfStep.selectDept" size="30" validate="{required:true}" value="<c:out value="${wfStep.selectDept}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="waitAuxiliary" name="wfStep.waitAuxiliary" size="30" validate="{required:true}" value="<c:out value="${wfStep.waitAuxiliary}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">步骤名称：</td><td><input type="text" id="stepName" name="wfStep.stepName" size="30" validate="{required:true}" value="<c:out value="${wfStep.stepName}"/>"/></td>
			<td height="24" width="90" align="center">步骤描述：</td><td><input type="text" id="stepDesc" name="wfStep.stepDesc" size="30" validate="{required:true}" value="<c:out value="${wfStep.stepDesc}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">本步骤对应页面：</td><td><input type="text" id="stepUri" name="wfStep.stepUri" size="30" validate="{required:true}" value="<c:out value="${wfStep.stepUri}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>