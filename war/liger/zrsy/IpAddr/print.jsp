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

<script src="./include/js/zrsy/ipAddr.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'IpAddr'},
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
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#ipId").length > 0)
		$("#ipId").ligerTextBox({width:145});
	if ($("#isWide").length > 0)
		$("#isWide").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox({width:145});
	if ($("#ipSegment").length > 0)
		$("#ipSegment").ligerTextBox({width:145});
	if ($("#addrDesc").length > 0)
		$("#addrDesc").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="ipAddr.createDate" validate="{required:true}" value="<c:out value="${ipAddr.createDate}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="ipAddr.lastDate" validate="{required:true}" value="<c:out value="${ipAddr.lastDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">IP标识：</td><td><input type="text" id="ipId" name="ipAddr.ipId" validate="{required:true}" value="<c:out value="${ipAddr.ipId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">是否外网：</td><td><input type="text" id="isWide" name="ipAddr.isWide" validate="{required:true}" value="<c:out value="${ipAddr.isWide}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="ipAddr.status" validate="{required:true}" value="<c:out value="${ipAddr.status}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="ipAddr.createBy" validate="{required:true}" value="<c:out value="${ipAddr.createBy}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="ipAddr.lastUpd" validate="{required:true}" value="<c:out value="${ipAddr.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">IP网段：</td><td><input type="text" id="ipSegment" name="ipAddr.ipSegment" validate="{required:true}" value="<c:out value="${ipAddr.ipSegment}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">描述：</td><td><input type="text" id="addrDesc" name="ipAddr.addrDesc" validate="{required:true}" value="<c:out value="${ipAddr.addrDesc}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>