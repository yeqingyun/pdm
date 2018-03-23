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

<script src="./include/js/gnwf/wfQuesDtl.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	//$.post("ligerToolBar1.shtml",
	//		{parm:'url'},
	//		function(data) {
	//			$("#toolbar").ligerToolBar(data);
	//		},
	//		"json"
	//);
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#title").length > 0)
		$("#title").ligerTextBox();
});
</script>
</head>
<body>

<%--<div id="toolbar"></div> --%>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<%--
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<select id="status" name="wfQuesDtl.status" style="width: 200px;" disabled="disabled">
					<option value="100"<c:if test="${wfQuesDtl.status==100}">selected</c:if>>发现</option>
					<option value="200"<c:if test="${wfQuesDtl.status==200}">selected</c:if>>回复</option>
					<option value="300"<c:if test="${wfQuesDtl.status==300}">selected</c:if>>关闭</option>
					<option value="400"<c:if test="${wfQuesDtl.status==400}">selected</c:if>>走风险</option>
				</select>
			</td>
		</tr>
	</table>
 --%>
<form>
	<input type="hidden" id="quesDtlId" name="wfQuesDtl.quesDtlId" size="30" value="<c:out value="${wfQuesDtl.quesDtlId}"/>"/>
	<input type="hidden" id="quesId" name="wfQuesDtl.quesId" value="<c:out value="${wfQuesDtl.quesId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">标题：</td><td><input type="text" id="title" name="wfQuesDtl.title" size="30" validate="{required:true}" value="<c:out value="${wfQuesDtl.title}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">问题办理人：</td>
			<td align=left><input type="text" id="userId" name="wfQuesDtl.userId" value="<c:out value="${wfQuesDtl.userId}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">内容：</td>
			<td>
				<textarea id="quesTxt" name="wfQuesDtl.quesTxt" style="width:400px;height: 150px" validate="{required:true}"><c:out value="${wfQuesDtl.quesTxt}"/></textarea>
			</td>
		</tr>

	</table>
</form>

</div>

</body>
</html>