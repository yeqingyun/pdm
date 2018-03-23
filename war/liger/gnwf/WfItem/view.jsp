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

<script src="./include/js/gnwf/wfItem.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#itemId").length > 0)
		$("#itemId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#itemNo").length > 0)
		$("#itemNo").ligerTextBox();
	if ($("#desc2").length > 0)
		$("#desc2").ligerTextBox();
	if ($("#desc5").length > 0)
		$("#desc5").ligerTextBox();
	if ($("#itemName").length > 0)
		$("#itemName").ligerTextBox();
	if ($("#desc3").length > 0)
		$("#desc3").ligerTextBox();
	if ($("#desc4").length > 0)
		$("#desc4").ligerTextBox();
	if ($("#desc1").length > 0)
		$("#desc1").ligerTextBox();
	if ($("#orderBy").length > 0)
		$("#orderBy").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="itemId" name="wfItem.itemId" size="30" validate="{required:true}" value="<c:out value="${wfItem.itemId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="status" name="wfItem.status" size="30" validate="{required:true}" value="<c:out value="${wfItem.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="flowId" name="wfItem.flowId" size="30" validate="{required:true}" value="<c:out value="${wfItem.flowId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="itemNo" name="wfItem.itemNo" size="30" validate="{required:true}" value="<c:out value="${wfItem.itemNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc2" name="wfItem.desc2" size="30" validate="{required:true}" value="<c:out value="${wfItem.desc2}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc5" name="wfItem.desc5" size="30" validate="{required:true}" value="<c:out value="${wfItem.desc5}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="itemName" name="wfItem.itemName" size="30" validate="{required:true}" value="<c:out value="${wfItem.itemName}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc3" name="wfItem.desc3" size="30" validate="{required:true}" value="<c:out value="${wfItem.desc3}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc4" name="wfItem.desc4" size="30" validate="{required:true}" value="<c:out value="${wfItem.desc4}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc1" name="wfItem.desc1" size="30" validate="{required:true}" value="<c:out value="${wfItem.desc1}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="orderBy" name="wfItem.orderBy" size="30" validate="{required:true}" value="<c:out value="${wfItem.orderBy}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>