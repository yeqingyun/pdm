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

<script src="./include/js/zrsy/syLog.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#logId").length > 0)
		$("#logId").ligerTextBox({width:145});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox({width:145});
	if ($("#logCode").length > 0)
		$("#logCode").ligerTextBox({width:145});
	if ($("#logNm").length > 0)
		$("#logNm").ligerTextBox({width:145});
	if ($("#logText").length > 0)
		$("#logText").ligerTextBox({width:145});
	if ($("#logDate").length > 0)
		$("#logDate").ligerDateEditor({showTime: false});
	if ($("#startLogDate").length > 0)
		$("#startLogDate").ligerDateEditor({showTime: false});
	if ($("#endLogDate").length > 0)
		$("#endLogDate").ligerDateEditor({showTime: false});
	if ($("#ipAddr").length > 0)
		$("#ipAddr").ligerTextBox({width:145});
	if ($("#logType").length > 0)
		$("#logType").ligerTextBox({width:145});
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
			<td height="24" width="90" align="center">日志ID：</td><td><input type="text" id="logId" name="syLog.logId" validate="{required:true}" value="<c:out value="${syLog.logId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="syLog.userId" validate="{required:true}" value="<c:out value="${syLog.userId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">日志代码：</td><td><input type="text" id="logCode" name="syLog.logCode" validate="{required:true}" value="<c:out value="${syLog.logCode}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">日志名称：</td><td><input type="text" id="logNm" name="syLog.logNm" validate="{required:true}" value="<c:out value="${syLog.logNm}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">日志明细：</td><td><input type="text" id="logText" name="syLog.logText" validate="{required:true}" value="<c:out value="${syLog.logText}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">记录时间：</td><td><input type="text" id="logDate" name="syLog.logDate" validate="{required:true}" value="<c:out value="${syLog.logDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">客户端IP：</td><td><input type="text" id="ipAddr" name="syLog.ipAddr" validate="{required:true}" value="<c:out value="${syLog.ipAddr}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="logType" name="syLog.logType" validate="{required:true}" value="<c:out value="${syLog.logType}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="remark" name="syLog.remark" validate="{required:true}" value="<c:out value="${syLog.remark}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>