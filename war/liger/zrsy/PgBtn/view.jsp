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

<script src="./include/js/zrsy/pgBtn.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#nodeId").length > 0)
		$("#nodeId").ligerTextBox({width:145});
	if ($("#btnId").length > 0)
		$("#btnId").ligerTextBox({width:145});
	if ($("#pgTyp").length > 0)
		$("#pgTyp").ligerTextBox({width:145});

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">功能：</td><td><input type="text" id="nodeId" name="pgBtn.nodeId" validate="{required:true}" value="<c:out value="${pgBtn.nodeId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">按钮：</td><td><input type="text" id="btnId" name="pgBtn.btnId" validate="{required:true}" value="<c:out value="${pgBtn.btnId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">页类型：</td><td><input type="text" id="pgTyp" name="pgBtn.pgTyp" validate="{required:true}" value="<c:out value="${pgBtn.pgTyp}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>