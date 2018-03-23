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

<script src="./include/js/gnwf/wfDocRev.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfDocRev'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '评审时间', name: 'createDate', align: 'left', width: 120 },
			{ display: '文档ID', name: 'docId', align: 'left', width: 120 },
			{ display: '任务ID', name: 'taskId', align: 'left', width: 120 },
			{ display: '评审人', name: 'createBy', align: 'left', width: 120 },
			{ display: '评审ID', name: 'revId', align: 'left', width: 120 },
			{ display: '工作流编号', name: 'wfNo', align: 'left', width: 120 },
			{ display: '评审内容', name: 'revText', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfDocRev!list.shtml',
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
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#docId").length > 0)
		$("#docId").ligerTextBox();
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#revId").length > 0)
		$("#revId").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#revText").length > 0)
		$("#revText").ligerTextBox();

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
			<td height="24" width="90" align="center">评审时间始：</td><td><input type="text" id="startCreateDate" name="wfDocRev.startCreateDate"/></td>
			<td height="24" width="90" align="center">评审时间止：</td><td><input type="text" id="endCreateDate" name="wfDocRev.endCreateDate"/></td>
			<td height="24" width="90" align="center">文档ID：</td><td><input type="text" id="docId" name="wfDocRev.docId"/></td>
			<td height="24" width="90" align="center">任务ID：</td><td><input type="text" id="taskId" name="wfDocRev.taskId"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">评审人：</td><td><input type="text" id="createBy" name="wfDocRev.createBy"/></td>
			<td height="24" width="90" align="center">评审ID：</td><td><input type="text" id="revId" name="wfDocRev.revId"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfDocRev.wfNo"/></td>
			<td height="24" width="90" align="center">评审内容：</td><td><input type="text" id="revText" name="wfDocRev.revText"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>