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

<script src="./include/js/zrprjt/driverDtl.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'DriverDtl'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#driveId").length > 0)
		$("#driveId").ligerTextBox({width:145});
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox({width:145});
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">驱动Id：</td><td><input type="text" id="driveId" name="driverDtl.driveId" validate="{required:true}" value="<c:out value="${driverDtl.driveId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">工作流Id：</td><td><input type="text" id="flowId" name="driverDtl.flowId" validate="{required:true}" value="<c:out value="${driverDtl.flowId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">任务Id：</td><td><input type="text" id="taskId" name="driverDtl.taskId" validate="{required:true}" value="<c:out value="${driverDtl.taskId}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>