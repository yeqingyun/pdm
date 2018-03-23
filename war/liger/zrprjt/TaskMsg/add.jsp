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

<script src="./include/js/zrprjt/taskMsg.js?v=1.0" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'TaskMsg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#msgId").length > 0)
		$("#msgId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#reply").length > 0)
		$("#reply").ligerTextBox();
	if ($("#taskNo").length > 0)
		$("#taskNo").ligerComboBox();
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#title").length > 0)
		$("#title").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">消息标题：</td><td><input type="text" id="title" name="taskMsg.title" size="30" value="<c:out value="${taskMsg.title}"/>"/></td>
			<td height="24" width="90" align="center">任务编号：</td>
			<td>
				<select id="taskNo" name="taskMsg.taskNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${tasks}" var="task">
						<option value="<c:out value="${task.taskNo}"/>"><c:out value="${task.taskNm}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="prjtNo" name="taskMsg.prjtNo" size="30" value="<c:out value="${taskMsg.prjtNo}"/>"/></td>
			<td height="24" width="90" align="center">流程编号：</td><td><input type="text" id="wfNo" name="taskMsg.wfNo" size="30" value="<c:out value="${taskMsg.wfNo}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="taskMsg.status" size="30" value="<c:out value="${taskMsg.status}"/>"/></td>
		</tr>
		<!--tr>
			<td height="24" width="90" align="center">任务消息ID：</td><td><input type="text" id="msgId" name="taskMsg.msgId" size="30" value="<c:out value="${taskMsg.msgId}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="taskMsg.createBy" size="30" value="<c:out value="${taskMsg.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="taskMsg.lastUpd" size="30" value="<c:out value="${taskMsg.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="taskMsg.createDate" size="30" value="<c:out value="${taskMsg.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="taskMsg.lastDate" size="30" value="<c:out value="${taskMsg.lastDate}"/>"/></td>
			<td height="24" width="90" align="center">消息回复：</td><td><input type="text" id="reply" name="taskMsg.reply" size="30" value="<c:out value="${taskMsg.reply}"/>"/></td>
		</tr-->
		<tr>
		</tr>

	</table>
</form>

</div>

</body>
</html>