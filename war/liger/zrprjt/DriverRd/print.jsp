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

<script src="./include/js/zrprjt/driverRd.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'DriverRd'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#logId").length > 0)
		$("#logId").ligerTextBox({width:145});
	if ($("#driverId").length > 0)
		$("#driverId").ligerTextBox({width:145});
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox({width:145});
	if ($("#driverDate").length > 0)
		$("#driverDate").ligerDateEditor({showTime: false});
	if ($("#startDriverDate").length > 0)
		$("#startDriverDate").ligerDateEditor({showTime: false});
	if ($("#endDriverDate").length > 0)
		$("#endDriverDate").ligerDateEditor({showTime: false});
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox({width:145});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});

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
			<td height="24" width="90" align="center">记录ID：</td><td><input type="text" id="logId" name="driverRd.logId" validate="{required:true}" value="<c:out value="${driverRd.logId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">驱动ID：</td><td><input type="text" id="driverId" name="driverRd.driverId" validate="{required:true}" value="<c:out value="${driverRd.driverId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="driverRd.wfNo" validate="{required:true}" value="<c:out value="${driverRd.wfNo}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">驱动时间：</td><td><input type="text" id="driverDate" name="driverRd.driverDate" validate="{required:true}" value="<c:out value="${driverRd.driverDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="driverRd.remark" validate="{required:true}" value="<c:out value="${driverRd.remark}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="driverRd.status" validate="{required:true}" value="<c:out value="${driverRd.status}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="driverRd.createBy" validate="{required:true}" value="<c:out value="${driverRd.createBy}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="driverRd.createDate" validate="{required:true}" value="<c:out value="${driverRd.createDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="driverRd.lastUpd" validate="{required:true}" value="<c:out value="${driverRd.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="driverRd.lastDate" validate="{required:true}" value="<c:out value="${driverRd.lastDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>