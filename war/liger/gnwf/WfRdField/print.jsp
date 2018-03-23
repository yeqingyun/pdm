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

<script src="./include/js/gnwf/wfRdField.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfRdField'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#fieldId").length > 0)
		$("#fieldId").ligerTextBox();
	if ($("#wfId").length > 0)
		$("#wfId").ligerTextBox();
	if ($("#rowId").length > 0)
		$("#rowId").ligerTextBox();
	if ($("#fieldText").length > 0)
		$("#fieldText").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();

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
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="fieldId" name="wfRdField.fieldId" size="30" validate="{required:true}" value="<c:out value="${wfRdField.fieldId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="wfId" name="wfRdField.wfId" size="30" validate="{required:true}" value="<c:out value="${wfRdField.wfId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="rowId" name="wfRdField.rowId" size="30" validate="{required:true}" value="<c:out value="${wfRdField.rowId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="fieldText" name="wfRdField.fieldText" size="30" validate="{required:true}" value="<c:out value="${wfRdField.fieldText}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">集合序列：</td><td><input type="text" id="wfNo" name="wfRdField.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfRdField.wfNo}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>