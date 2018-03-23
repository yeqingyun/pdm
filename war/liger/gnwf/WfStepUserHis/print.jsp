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

<script src="./include/js/gnwf/wfStepUserHis.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfStepUserHis'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#stepID").length > 0)
		$("#stepID").ligerTextBox();
	if ($("#taskType").length > 0)
		$("#taskType").ligerTextBox();
	if ($("#owner").length > 0)
		$("#owner").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();

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
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="stepID" name="wfStepUserHis.stepID" size="30" validate="{required:true}" value="<c:out value="${wfStepUserHis.stepID}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="taskType" name="wfStepUserHis.taskType" size="30" validate="{required:true}" value="<c:out value="${wfStepUserHis.taskType}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="owner" name="wfStepUserHis.owner" size="30" validate="{required:true}" value="<c:out value="${wfStepUserHis.owner}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="userId" name="wfStepUserHis.userId" size="30" validate="{required:true}" value="<c:out value="${wfStepUserHis.userId}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>