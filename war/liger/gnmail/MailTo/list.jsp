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

<script src="./include/js/gnmail/mailTo.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'MailTo'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '邮件ID', name: 'mailId', align: 'left', width: 120 },
			{ display: '收件人ID', name: 'toId', align: 'left', width: 120 },
			{ display: '电子信箱地址', name: 'toMail', align: 'left', width: 120 },
			{ display: '类型（0主送，1抄送，2暗送）', name: 'toType', align: 'left', width: 120 },
			{ display: '收件人', name: 'toName', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./MailTo!list.shtml',
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
	if ($("#mailId").length > 0)
		$("#mailId").ligerTextBox({width:145});
	if ($("#toId").length > 0)
		$("#toId").ligerTextBox({width:145});
	if ($("#toMail").length > 0)
		$("#toMail").ligerTextBox({width:145});
	if ($("#toType").length > 0)
		$("#toType").ligerTextBox({width:145});
	if ($("#toName").length > 0)
		$("#toName").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">邮件ID：</td><td><input type="text" id="mailId" name="mailTo.mailId"/></td>
			<td height="24" width="90" align="center">收件人ID：</td><td><input type="text" id="toId" name="mailTo.toId"/></td>
			<td height="24" width="90" align="center">电子信箱地址：</td><td><input type="text" id="toMail" name="mailTo.toMail"/></td>
			<td height="24" width="90" align="center">类型（0主送，1抄送，2暗送）：</td><td><input type="text" id="toType" name="mailTo.toType"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">收件人：</td><td><input type="text" id="toName" name="mailTo.toName"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>