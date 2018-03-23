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

<script src="./include/js/gnmail/mailCfg.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#cfgId").length > 0)
		$("#cfgId").ligerTextBox({width:145});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox({width:145});
	if ($("#mailAddr").length > 0)
		$("#mailAddr").ligerTextBox({width:145});
	if ($("#pwd").length > 0)
		$("#pwd").ligerTextBox({width:145});
	if ($("#mailName").length > 0)
		$("#mailName").ligerTextBox({width:145});
	if ($("#mailSign").length > 0)
		$("#mailSign").ligerTextBox({width:145});
	if ($("#servIpAddr").length > 0)
		$("#servIpAddr").ligerTextBox({width:145});
	if ($("#smpt").length > 0)
		$("#smpt").ligerTextBox({width:145});
	if ($("#pop3").length > 0)
		$("#pop3").ligerTextBox({width:145});
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
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">邮件配置：</td><td><input type="text" id="cfgId" name="mailCfg.cfgId" validate="{required:true}" value="<c:out value="${mailCfg.cfgId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="mailCfg.userId" validate="{required:true}" value="<c:out value="${mailCfg.userId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">邮箱地址：</td><td><input type="text" id="mailAddr" name="mailCfg.mailAddr" validate="{required:true}" value="<c:out value="${mailCfg.mailAddr}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">密码：</td><td><input type="text" id="pwd" name="mailCfg.pwd" validate="{required:true}" value="<c:out value="${mailCfg.pwd}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="mailName" name="mailCfg.mailName" validate="{required:true}" value="<c:out value="${mailCfg.mailName}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">签名：</td><td><input type="text" id="mailSign" name="mailCfg.mailSign" validate="{required:true}" value="<c:out value="${mailCfg.mailSign}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">服务器地址：</td><td><input type="text" id="servIpAddr" name="mailCfg.servIpAddr" validate="{required:true}" value="<c:out value="${mailCfg.servIpAddr}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">Smt端口：</td><td><input type="text" id="smpt" name="mailCfg.smpt" validate="{required:true}" value="<c:out value="${mailCfg.smpt}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">POP3端口：</td><td><input type="text" id="pop3" name="mailCfg.pop3" validate="{required:true}" value="<c:out value="${mailCfg.pop3}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mailCfg.remark" validate="{required:true}" value="<c:out value="${mailCfg.remark}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailCfg.status" validate="{required:true}" value="<c:out value="${mailCfg.status}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mailCfg.createBy" validate="{required:true}" value="<c:out value="${mailCfg.createBy}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="mailCfg.createDate" validate="{required:true}" value="<c:out value="${mailCfg.createDate}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mailCfg.lastUpd" validate="{required:true}" value="<c:out value="${mailCfg.lastUpd}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="mailCfg.lastUpdDate" validate="{required:true}" value="<c:out value="${mailCfg.lastUpdDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>