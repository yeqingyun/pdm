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

<script src="./include/js/gnmail/mailTo.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#mailId").length > 0)
		$("#mailId").ligerTextBox({width:145});
	if ($("#toId").length > 0)
		$("#toId").ligerTextBox({width:145});
	if ($("#toMail").length > 0)
		$("#toMail").ligerTextBox({width:145});
	if ($("#toType").length > 0)
		$("#toType").ligerTextBox({width:145});
	if ($("#toName").length > 0)
		$("#toName").ligerTextBox({width:145});

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">邮件ID：</td><td><input type="text" id="mailId" name="mailTo.mailId" validate="{required:true}" value="<c:out value="${mailTo.mailId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">收件人ID：</td><td><input type="text" id="toId" name="mailTo.toId" validate="{required:true}" value="<c:out value="${mailTo.toId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">电子信箱地址：</td><td><input type="text" id="toMail" name="mailTo.toMail" validate="{required:true}" value="<c:out value="${mailTo.toMail}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">类型（0主送，1抄送，2暗送）：</td><td><input type="text" id="toType" name="mailTo.toType" validate="{required:true}" value="<c:out value="${mailTo.toType}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">收件人：</td><td><input type="text" id="toName" name="mailTo.toName" validate="{required:true}" value="<c:out value="${mailTo.toName}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>