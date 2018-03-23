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

<script src="./include/js/gnmail/mailAutoM.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'MailAutoM'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'null', name: 'mailId', align: 'left', width: 120 },
			{ display: '工作流记录编号', name: 'wfNO', align: 'left', width: 120 },
			{ display: '工作任务ID', name: 'taskId', align: 'left', width: 120 },
			{ display: '创建人ID', name: 'createBy', align: 'left', width: 120 },
			{ display: '创建时间', name: 'createDate', align: 'left', width: 120 },
			{ display: '接收人', name: 'acceptBy', align: 'left', width: 120 },
			{ display: '接收时间', name: 'acceptDate', align: 'left', width: 120 },
			{ display: '标题', name: 'title', align: 'left', width: 120 },
			{ display: '邮件内容', name: 'mailText', align: 'left', width: 120 },
			{ display: '任务连接URI', name: 'taskUri', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '备注', name: 'remark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./MailAutoM!list.shtml',
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
		$("#mailId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#wfNO").length > 0)
		$("#wfNO").ligerTextBox({width:145});
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#acceptBy").length > 0)
		$("#acceptBy").ligerTextBox({width:145});
	if ($("#acceptDate").length > 0)
		$("#acceptDate").ligerDateEditor({showTime: false});
	if ($("#startAcceptDate").length > 0)
		$("#startAcceptDate").ligerDateEditor({showTime: false});
	if ($("#endAcceptDate").length > 0)
		$("#endAcceptDate").ligerDateEditor({showTime: false});
	if ($("#title").length > 0)
		$("#title").ligerTextBox({width:145});
	if ($("#mailText").length > 0)
		$("#mailText").ligerTextBox({width:145});
	if ($("#taskUri").length > 0)
		$("#taskUri").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="mailId" name="mailAutoM.mailId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mails}" var="mail">
						<option value="<c:out value="${mail.mailId}"/>"><c:out value="${mail.mailId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">工作流记录编号：</td><td><input type="text" id="wfNO" name="mailAutoM.wfNO"/></td>
			<td height="24" width="90" align="center">工作任务ID：</td><td><input type="text" id="taskId" name="mailAutoM.taskId"/></td>
			<td height="24" width="90" align="center">创建人ID：</td><td><input type="text" id="createBy" name="mailAutoM.createBy"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建时间始：</td><td><input type="text" id="startCreateDate" name="mailAutoM.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建时间止：</td><td><input type="text" id="endCreateDate" name="mailAutoM.endCreateDate"/></td>
			<td height="24" width="90" align="center">接收人：</td><td><input type="text" id="acceptBy" name="mailAutoM.acceptBy"/></td>
			<td height="24" width="90" align="center">接收时间始：</td><td><input type="text" id="startAcceptDate" name="mailAutoM.startAcceptDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">接收时间止：</td><td><input type="text" id="endAcceptDate" name="mailAutoM.endAcceptDate"/></td>
			<td height="24" width="90" align="center">标题：</td><td><input type="text" id="title" name="mailAutoM.title"/></td>
			<td height="24" width="90" align="center">邮件内容：</td><td><input type="text" id="mailText" name="mailAutoM.mailText"/></td>
			<td height="24" width="90" align="center">任务连接URI：</td><td><input type="text" id="taskUri" name="mailAutoM.taskUri"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailAutoM.status"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mailAutoM.remark"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>