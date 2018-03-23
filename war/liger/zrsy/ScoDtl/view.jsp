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

<script src="./include/js/zrsy/scoDtl.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#scoId").length > 0)
		$("#scoId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#detpId").length > 0)
		$("#detpId").ligerTextBox();
	if ($("#usrId").length > 0)
		$("#usrId").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">范围ID：</td><td><input type="text" id="scoId" name="scoDtl.scoId" size="30" validate="{required:true}" value="<c:out value="${scoDtl.scoId}"/>"/></td>
			<td height="24" width="90" align="center">公司ID：</td><td><input type="text" id="comId" name="scoDtl.comId" size="30" validate="{required:true}" value="<c:out value="${scoDtl.comId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">部门ID：</td><td><input type="text" id="detpId" name="scoDtl.detpId" size="30" validate="{required:true}" value="<c:out value="${scoDtl.detpId}"/>"/></td>
			<td height="24" width="90" align="center">用户Id：</td><td><input type="text" id="usrId" name="scoDtl.usrId" size="30" validate="{required:true}" value="<c:out value="${scoDtl.usrId}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>