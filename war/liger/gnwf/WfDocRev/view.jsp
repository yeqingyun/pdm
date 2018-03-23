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

<script src="./include/js/gnwf/wfDocRev.js" type="text/javascript"></script>

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
	if ($("#docId").length > 0)
		$("#docId").ligerTextBox();
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#revId").length > 0)
		$("#revId").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#revText").length > 0)
		$("#revText").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">评审时间：</td><td><input type="text" id="createDate" name="wfDocRev.createDate" size="30" validate="{required:true}" value="<c:out value="${wfDocRev.createDate}"/>"/></td>
			<td height="24" width="90" align="center">文档ID：</td><td><input type="text" id="docId" name="wfDocRev.docId" size="30" validate="{required:true}" value="<c:out value="${wfDocRev.docId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">任务ID：</td><td><input type="text" id="taskId" name="wfDocRev.taskId" size="30" validate="{required:true}" value="<c:out value="${wfDocRev.taskId}"/>"/></td>
			<td height="24" width="90" align="center">评审人：</td><td><input type="text" id="createBy" name="wfDocRev.createBy" size="30" validate="{required:true}" value="<c:out value="${wfDocRev.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">评审ID：</td><td><input type="text" id="revId" name="wfDocRev.revId" size="30" validate="{required:true}" value="<c:out value="${wfDocRev.revId}"/>"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfDocRev.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfDocRev.wfNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">评审内容：</td><td><input type="text" id="revText" name="wfDocRev.revText" size="30" validate="{required:true}" value="<c:out value="${wfDocRev.revText}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>