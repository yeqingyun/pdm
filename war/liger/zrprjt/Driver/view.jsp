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

<script src="./include/js/zrprjt/driver.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#driveId").length > 0)
		$("#driveId").ligerTextBox({width:145});
	if ($("#driveNo").length > 0)
		$("#driveNo").ligerTextBox({width:145});
	if ($("#driveNm").length > 0)
		$("#driveNm").ligerTextBox({width:145});
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox({width:145});
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

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">驱动Id：</td><td><input type="text" id="driveId" name="driver.driveId" validate="{required:true}" value="<c:out value="${driver.driveId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">驱动编号：</td><td><input type="text" id="driveNo" name="driver.driveNo" validate="{required:true}" value="<c:out value="${driver.driveNo}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">驱动名称：</td><td><input type="text" id="driveNm" name="driver.driveNm" validate="{required:true}" value="<c:out value="${driver.driveNm}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">被驱工作流Id：</td><td><input type="text" id="flowId" name="driver.flowId" validate="{required:true}" value="<c:out value="${driver.flowId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="driver.remark" validate="{required:true}" value="<c:out value="${driver.remark}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="driver.status" validate="{required:true}" value="<c:out value="${driver.status}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="driver.createBy" validate="{required:true}" value="<c:out value="${driver.createBy}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="driver.createDate" validate="{required:true}" value="<c:out value="${driver.createDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="driver.lastUpd" validate="{required:true}" value="<c:out value="${driver.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="driver.lastDate" validate="{required:true}" value="<c:out value="${driver.lastDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>