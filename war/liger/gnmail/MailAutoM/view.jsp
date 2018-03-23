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

<script src="./include/js/gnmail/mailAutoM.js" type="text/javascript"></script>

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
		$("#mailId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#wfNO").length > 0)
		$("#wfNO").ligerTextBox({width:145});
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#acceptBy").length > 0)
		$("#acceptBy").ligerTextBox({width:145});
	if ($("#acceptDate").length > 0)
		$("#acceptDate").ligerDateEditor({showTime: false});
	if ($("#startAcceptDate").length > 0)
		$("#startAcceptDate").ligerDateEditor({showTime: false});
	if ($("#endAcceptDate").length > 0)
		$("#endAcceptDate").ligerDateEditor({showTime: false});
	if ($("#title").length > 0)
		$("#title").ligerTextBox({width:145});
	if ($("#mailText").length > 0)
		$("#mailText").ligerTextBox({width:145});
	if ($("#taskUri").length > 0)
		$("#taskUri").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox({width:145});

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="mailId" name="mailAutoM.mailId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mails}" var="mail">
						<option value="<c:out value="${mail.mailId}"/>"><c:out value="${mail.mailId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">工作流记录编号：</td><td><input type="text" id="wfNO" name="mailAutoM.wfNO" validate="{required:true}" value="<c:out value="${mailAutoM.wfNO}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作任务ID：</td><td><input type="text" id="taskId" name="mailAutoM.taskId" validate="{required:true}" value="<c:out value="${mailAutoM.taskId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建人ID：</td><td><input type="text" id="createBy" name="mailAutoM.createBy" validate="{required:true}" value="<c:out value="${mailAutoM.createBy}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建时间：</td><td><input type="text" id="createDate" name="mailAutoM.createDate" validate="{required:true}" value="<c:out value="${mailAutoM.createDate}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">接收人：</td><td><input type="text" id="acceptBy" name="mailAutoM.acceptBy" validate="{required:true}" value="<c:out value="${mailAutoM.acceptBy}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">接收时间：</td><td><input type="text" id="acceptDate" name="mailAutoM.acceptDate" validate="{required:true}" value="<c:out value="${mailAutoM.acceptDate}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">标题：</td><td><input type="text" id="title" name="mailAutoM.title" validate="{required:true}" value="<c:out value="${mailAutoM.title}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">邮件内容：</td><td><input type="text" id="mailText" name="mailAutoM.mailText" validate="{required:true}" value="<c:out value="${mailAutoM.mailText}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">任务连接URI：</td><td><input type="text" id="taskUri" name="mailAutoM.taskUri" validate="{required:true}" value="<c:out value="${mailAutoM.taskUri}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailAutoM.status" validate="{required:true}" value="<c:out value="${mailAutoM.status}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mailAutoM.remark" validate="{required:true}" value="<c:out value="${mailAutoM.remark}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>