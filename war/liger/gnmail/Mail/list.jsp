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

<script src="./include/js/gnmail/mail.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'Mail'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '邮件ID', name: 'mailId', align: 'left', width: 120 },
			{ display: '邮件服务ID', name: 'cfgId', align: 'left', width: 120 },
			{ display: '业务ID', name: 'oexId', align: 'left', width: 120 },
			{ display: '发件人ID', name: 'senderId', align: 'left', width: 120 },
			{ display: '发件人', name: 'sender', align: 'left', width: 120 },
			{ display: '收件人ID', name: 'acceptId', align: 'left', width: 120 },
			{ display: '收件人', name: 'accept', align: 'left', width: 120 },
			{ display: '主题', name: 'title', align: 'left', width: 120 },
			{ display: '邮件内容', name: 'mailText', align: 'left', width: 120 },
			{ display: '邮件时间', name: 'mailDate', align: 'left', width: 120 },
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
		url:'./Mail!list.shtml',
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
	if ($("#cfgId").length > 0)
		$("#cfgId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#oexId").length > 0)
		$("#oexId").ligerTextBox({width:145});
	if ($("#senderId").length > 0)
		$("#senderId").ligerTextBox({width:145});
	if ($("#sender").length > 0)
		$("#sender").ligerTextBox({width:145});
	if ($("#acceptId").length > 0)
		$("#acceptId").ligerTextBox({width:145});
	if ($("#accept").length > 0)
		$("#accept").ligerTextBox({width:145});
	if ($("#title").length > 0)
		$("#title").ligerTextBox({width:145});
	if ($("#mailText").length > 0)
		$("#mailText").ligerTextBox({width:145});
	if ($("#mailDate").length > 0)
		$("#mailDate").ligerDateEditor({showTime: false});
	if ($("#startMailDate").length > 0)
		$("#startMailDate").ligerDateEditor({showTime: false});
	if ($("#endMailDate").length > 0)
		$("#endMailDate").ligerDateEditor({showTime: false});
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
			<td height="24" width="90" align="center">邮件ID：</td><td><input type="text" id="mailId" name="mail.mailId"/></td>
			<td height="24" width="90" align="center">邮件服务ID：</td>
			<td>
				<select id="cfgId" name="mail.cfgId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailCfgs}" var="mailCfg">
						<option value="<c:out value="${mailCfg.cfgId}"/>"><c:out value="${mailCfg.cfgId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">业务ID：</td><td><input type="text" id="oexId" name="mail.oexId"/></td>
			<td height="24" width="90" align="center">发件人ID：</td><td><input type="text" id="senderId" name="mail.senderId"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">发件人：</td><td><input type="text" id="sender" name="mail.sender"/></td>
			<td height="24" width="90" align="center">收件人ID：</td><td><input type="text" id="acceptId" name="mail.acceptId"/></td>
			<td height="24" width="90" align="center">收件人：</td><td><input type="text" id="accept" name="mail.accept"/></td>
			<td height="24" width="90" align="center">主题：</td><td><input type="text" id="title" name="mail.title"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">邮件内容：</td><td><input type="text" id="mailText" name="mail.mailText"/></td>
			<td height="24" width="90" align="center">邮件时间始：</td><td><input type="text" id="startMailDate" name="mail.startMailDate"/></td>
			<td height="24" width="90" align="center">邮件时间止：</td><td><input type="text" id="endMailDate" name="mail.endMailDate"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mail.remark"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mail.status"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mail.createBy"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="mail.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="mail.endCreateDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mail.lastUpd"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastUpdDate" name="mail.startLastUpdDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastUpdDate" name="mail.endLastUpdDate"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>