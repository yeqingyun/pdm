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

<script src="./include/js/gnwf/wfFieldStepRelate.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfFieldStepRelate'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
	if ($("#fieldId").length > 0)
		$("#fieldId").ligerTextBox();
	if ($("#stepId").length > 0)
		$("#stepId").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="fieldId" name="wfFieldStepRelate.fieldId" size="30" validate="{required:true}" value="<c:out value="${wfFieldStepRelate.fieldId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="stepId" name="wfFieldStepRelate.stepId" size="30" validate="{required:true}" value="<c:out value="${wfFieldStepRelate.stepId}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>