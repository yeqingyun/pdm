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

<script src="./include/js/gnwf/wfRdSign.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#signText").length > 0)
		$("#signText").ligerTextBox();
	if ($("#wfNoId").length > 0)
		$("#wfNoId").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfRdSign.createDate" size="30" validate="{required:true}" value="<c:out value="${wfRdSign.createDate}"/>"/></td>
			<td height="24" width="90" align="center">工作任务：</td><td><input type="text" id="taskId" name="wfRdSign.taskId" size="30" validate="{required:true}" value="<c:out value="${wfRdSign.taskId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户：</td><td><input type="text" id="userId" name="wfRdSign.userId" size="30" validate="{required:true}" value="<c:out value="${wfRdSign.userId}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfRdSign.status" size="30" validate="{required:true}" value="<c:out value="${wfRdSign.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">会签意见：</td><td><input type="text" id="signText" name="wfRdSign.signText" size="30" validate="{required:true}" value="<c:out value="${wfRdSign.signText}"/>"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNoId" name="wfRdSign.wfNoId" size="30" validate="{required:true}" value="<c:out value="${wfRdSign.wfNoId}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>