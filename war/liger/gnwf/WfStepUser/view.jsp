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

<script src="./include/js/gnwf/wfStepUser.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#stepId").length > 0)
		$("#stepId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#userType").length > 0)
		$("#userType").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">步骤ID：</td><td><input type="text" id="stepId" name="wfStepUser.stepId" size="30" validate="{required:true}" value="<c:out value="${wfStepUser.stepId}"/>"/></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="wfStepUser.userId" size="30" validate="{required:true}" value="<c:out value="${wfStepUser.userId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">任务类型：</td><td><input type="text" id="userType" name="wfStepUser.userType" size="30" validate="{required:true}" value="<c:out value="${wfStepUser.userType}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>