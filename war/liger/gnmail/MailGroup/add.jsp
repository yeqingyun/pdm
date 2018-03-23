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

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/gnmail/mailGroup.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'MailGroup'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#groupId").length > 0)
		$("#groupId").ligerTextBox({width:145});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox({width:145});
	if ($("#groupParent").length > 0)
		$("#groupParent").ligerTextBox({width:145});
	if ($("#groupName").length > 0)
		$("#groupName").ligerTextBox({width:145});
	if ($("#groupLevel").length > 0)
		$("#groupLevel").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox({width:145});
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});

      check();
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">分组ID：</td><td><input type="text" id="groupId" name="mailGroup.groupId" validate="{required:true}" value="<c:out value="${mailGroup.groupId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="mailGroup.userId" validate="{required:true}" value="<c:out value="${mailGroup.userId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">上级分组：</td><td><input type="text" id="groupParent" name="mailGroup.groupParent" validate="{required:true}" value="<c:out value="${mailGroup.groupParent}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">分组名称：</td><td><input type="text" id="groupName" name="mailGroup.groupName" validate="{required:true}" value="<c:out value="${mailGroup.groupName}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">组级别：</td><td><input type="text" id="groupLevel" name="mailGroup.groupLevel" validate="{required:true}" value="<c:out value="${mailGroup.groupLevel}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailGroup.status" validate="{required:true}" value="<c:out value="${mailGroup.status}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="mailGroup.createDate" validate="{required:true}" value="<c:out value="${mailGroup.createDate}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mailGroup.lastUpd" validate="{required:true}" value="<c:out value="${mailGroup.lastUpd}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="mailGroup.lastUpdDate" validate="{required:true}" value="<c:out value="${mailGroup.lastUpdDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>