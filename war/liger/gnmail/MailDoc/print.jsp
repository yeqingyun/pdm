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

<script src="./include/js/gnmail/mailDoc.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'MailDoc'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#docId").length > 0)
		$("#docId").ligerTextBox({width:145});
	if ($("#mailId").length > 0)
		$("#mailId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#docName").length > 0)
		$("#docName").ligerTextBox({width:145});
	if ($("#uriLink").length > 0)
		$("#uriLink").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">附件ID：</td><td><input type="text" id="docId" name="mailDoc.docId" validate="{required:true}" value="<c:out value="${mailDoc.docId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">邮件ID：</td>
			<td>
				<select id="mailId" name="mailDoc.mailId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mails}" var="mail">
						<option value="<c:out value="${mail.mailId}"/>"><c:out value="${mail.mailId}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">文档名称：</td><td><input type="text" id="docName" name="mailDoc.docName" validate="{required:true}" value="<c:out value="${mailDoc.docName}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">URI连接：</td><td><input type="text" id="uriLink" name="mailDoc.uriLink" validate="{required:true}" value="<c:out value="${mailDoc.uriLink}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>