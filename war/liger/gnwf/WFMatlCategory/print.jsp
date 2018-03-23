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

<script src="./include/js/gnwf/wFMatlCategory.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WFMatlCategory'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#categoryId").length > 0)
		$("#categoryId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#desc1").length > 0)
		$("#desc1").ligerTextBox();
	if ($("#desc2").length > 0)
		$("#desc2").ligerTextBox();
	if ($("#categoryNo").length > 0)
		$("#categoryNo").ligerTextBox();
	if ($("#desc3").length > 0)
		$("#desc3").ligerTextBox();
	if ($("#categoryName").length > 0)
		$("#categoryName").ligerTextBox();

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
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="categoryId" name="wFMatlCategory.categoryId" size="30" validate="{required:true}" value="<c:out value="${wFMatlCategory.categoryId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="status" name="wFMatlCategory.status" size="30" validate="{required:true}" value="<c:out value="${wFMatlCategory.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc1" name="wFMatlCategory.desc1" size="30" validate="{required:true}" value="<c:out value="${wFMatlCategory.desc1}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc2" name="wFMatlCategory.desc2" size="30" validate="{required:true}" value="<c:out value="${wFMatlCategory.desc2}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="categoryNo" name="wFMatlCategory.categoryNo" size="30" validate="{required:true}" value="<c:out value="${wFMatlCategory.categoryNo}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="desc3" name="wFMatlCategory.desc3" size="30" validate="{required:true}" value="<c:out value="${wFMatlCategory.desc3}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="categoryName" name="wFMatlCategory.categoryName" size="30" validate="{required:true}" value="<c:out value="${wFMatlCategory.categoryName}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>