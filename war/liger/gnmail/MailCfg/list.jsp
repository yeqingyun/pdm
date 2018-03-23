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
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'MailCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '邮件配置', name: 'cfgId', align: 'left', width: 120 },
			{ display: '用户ID', name: 'userId', align: 'left', width: 120 },
			{ display: '邮箱地址', name: 'mailAddr', align: 'left', width: 120 },
			{ display: '密码', name: 'pwd', align: 'left', width: 120 },
			{ display: '名称', name: 'mailName', align: 'left', width: 120 },
			{ display: '签名', name: 'mailSign', align: 'left', width: 120 },
			{ display: '服务器地址', name: 'servIpAddr', align: 'left', width: 120 },
			{ display: 'Smt端口', name: 'smpt', align: 'left', width: 120 },
			{ display: 'POP3端口', name: 'pop3', align: 'left', width: 120 },
			{ display: '备注', name: 'remark', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastUpdDate', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./MailCfg!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

$(function(){
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

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">邮件配置：</td><td><input type="text" id="cfgId" name="mailCfg.cfgId"/></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="mailCfg.userId"/></td>
			<td height="24" width="90" align="center">邮箱地址：</td><td><input type="text" id="mailAddr" name="mailCfg.mailAddr"/></td>
			<td height="24" width="90" align="center">密码：</td><td><input type="text" id="pwd" name="mailCfg.pwd"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="mailName" name="mailCfg.mailName"/></td>
			<td height="24" width="90" align="center">签名：</td><td><input type="text" id="mailSign" name="mailCfg.mailSign"/></td>
			<td height="24" width="90" align="center">服务器地址：</td><td><input type="text" id="servIpAddr" name="mailCfg.servIpAddr"/></td>
			<td height="24" width="90" align="center">Smt端口：</td><td><input type="text" id="smpt" name="mailCfg.smpt"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">POP3端口：</td><td><input type="text" id="pop3" name="mailCfg.pop3"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mailCfg.remark"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailCfg.status"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mailCfg.createBy"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="mailCfg.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="mailCfg.endCreateDate"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mailCfg.lastUpd"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastUpdDate" name="mailCfg.startLastUpdDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastUpdDate" name="mailCfg.endLastUpdDate"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>