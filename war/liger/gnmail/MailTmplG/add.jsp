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

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/gnmail/mailTmplG.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'MailTmplG'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#tmplId").length > 0)
		$("#tmplId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#groupId").length > 0)
		$("#groupId").ligerComboBox({selectBoxWidth:200,width:145});

      check();
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">模板ID：</td>
			<td>
				<select id="tmplId" name="mailTmplG.tmplId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailTmpls}" var="mailTmpl">
						<option value="<c:out value="${mailTmpl.tmplId}"/>"><c:out value="${mailTmpl.tmplId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">邮件组ID：</td>
			<td>
				<select id="groupId" name="mailTmplG.groupId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailGroups}" var="mailGroup">
						<option value="<c:out value="${mailGroup.groupId}"/>"><c:out value="${mailGroup.groupId}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>
</form>

</div>

</body>
</html>