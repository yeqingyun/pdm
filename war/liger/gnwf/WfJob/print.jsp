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

<script src="./include/js/gnwf/wfJob.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfJob'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#jobId").length > 0)
		$("#jobId").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#isUpdOrLoad").length > 0)
		$("#isUpdOrLoad").ligerTextBox();
	if ($("#annexUpdOrLoad").length > 0)
		$("#annexUpdOrLoad").ligerTextBox();

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
			<td height="24" width="90" align="center">工作岗位ID：</td><td><input type="text" id="jobId" name="wfJob.jobId" size="30" validate="{required:true}" value="<c:out value="${wfJob.jobId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="flowId" name="wfJob.flowId" size="30" validate="{required:true}" value="<c:out value="${wfJob.flowId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="isUpdOrLoad" name="wfJob.isUpdOrLoad" size="30" validate="{required:true}" value="<c:out value="${wfJob.isUpdOrLoad}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="annexUpdOrLoad" name="wfJob.annexUpdOrLoad" size="30" validate="{required:true}" value="<c:out value="${wfJob.annexUpdOrLoad}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>