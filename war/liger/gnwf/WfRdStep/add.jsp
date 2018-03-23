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

<script src="./include/js/gnwf/wfRdStep.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfRdStep'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
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
	if ($("#stepId").length > 0)
		$("#stepId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#stepUser").length > 0)
		$("#stepUser").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfRdStep.createDate" size="30" validate="{required:true}" value="<c:out value="${wfRdStep.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfRdStep.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfRdStep.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">步骤ID：</td><td><input type="text" id="stepId" name="wfRdStep.stepId" size="30" validate="{required:true}" value="<c:out value="${wfRdStep.stepId}"/>"/></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="wfRdStep.userId" size="30" validate="{required:true}" value="<c:out value="${wfRdStep.userId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">接收人：</td><td><input type="text" id="stepUser" name="wfRdStep.stepUser" size="30" validate="{required:true}" value="<c:out value="${wfRdStep.stepUser}"/>"/></td>
			<td height="24" width="90" align="center">工作流ID：</td><td><input type="text" id="flowId" name="wfRdStep.flowId" size="30" validate="{required:true}" value="<c:out value="${wfRdStep.flowId}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>