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

<script src="./include/js/gnwf/wfReply.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfReply'},
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
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#replyId").length > 0)
		$("#replyId").ligerTextBox();
	if ($("#quesId").length > 0)
		$("#quesId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#replyText").length > 0)
		$("#replyText").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfReply.createDate" size="30" validate="{required:true}" value="<c:out value="${wfReply.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfReply.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfReply.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">回复ID：</td><td><input type="text" id="replyId" name="wfReply.replyId" size="30" validate="{required:true}" value="<c:out value="${wfReply.replyId}"/>"/></td>
			<td height="24" width="90" align="center">问题编号：</td><td><input type="text" id="quesId" name="wfReply.quesId" size="30" validate="{required:true}" value="<c:out value="${wfReply.quesId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="wfReply.userId" size="30" validate="{required:true}" value="<c:out value="${wfReply.userId}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfReply.status" size="30" validate="{required:true}" value="<c:out value="${wfReply.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfReply.createBy" size="30" validate="{required:true}" value="<c:out value="${wfReply.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfReply.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfReply.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">回复内容：</td><td><input type="text" id="replyText" name="wfReply.replyText" size="30" validate="{required:true}" value="<c:out value="${wfReply.replyText}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>