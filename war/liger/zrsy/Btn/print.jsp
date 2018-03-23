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

<script src="./include/js/zrsy/btn.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'Btn'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#id").length > 0)
		$("#id").ligerTextBox();
	if ($("#syId").length > 0)
		$("#syId").ligerTextBox();
	if ($("#line").length > 0)
		$("#line").ligerTextBox();
	if ($("#disable").length > 0)
		$("#disable").ligerTextBox();
	if ($("#sort").length > 0)
		$("#sort").ligerTextBox();
	if ($("#text").length > 0)
		$("#text").ligerTextBox();
	if ($("#click").length > 0)
		$("#click").ligerTextBox();
	if ($("#icon").length > 0)
		$("#icon").ligerTextBox();

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
			<td height="24" width="90" align="center">ID：</td><td><input type="text" id="id" name="btn.id" size="30" value="<c:out value="${btn.id}"/>"/></td>
			<td height="24" width="90" align="center">系统：</td><td><input type="text" id="syId" name="btn.syId" size="30" value="<c:out value="${btn.syId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">线条：</td><td><input type="text" id="line" name="btn.line" size="30" value="<c:out value="${btn.line}"/>"/></td>
			<td height="24" width="90" align="center">可见：</td><td><input type="text" id="disable" name="btn.disable" size="30" value="<c:out value="${btn.disable}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="btn.sort" size="30" value="<c:out value="${btn.sort}"/>"/></td>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="text" name="btn.text" size="30" value="<c:out value="${btn.text}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">单击事件：</td><td><input type="text" id="click" name="btn.click" size="30" value="<c:out value="${btn.click}"/>"/></td>
			<td height="24" width="90" align="center">图标：</td><td><input type="text" id="icon" name="btn.icon" size="30" value="<c:out value="${btn.icon}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>