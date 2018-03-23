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

<script src="./include/js/gnwf/wfMatlEval.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfMatlEval'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '评审日期', name: 'evalDate', align: 'left', width: 120 },
			{ display: '物料ID', name: 'matlId', align: 'left', width: 120 },
			{ display: '用户ID', name: 'userId', align: 'left', width: 120 },
			{ display: '是否通过', name: 'isPass', align: 'left', width: 120 },
			{ display: '1代表采购，2代表材料', name: 'evaler', align: 'left', width: 120 },
			{ display: 'null', name: 'status', align: 'left', width: 120 },
			{ display: 'null', name: 'evalId', align: 'left', width: 120 },
			{ display: '工作流编号', name: 'wfNo', align: 'left', width: 120 },
			{ display: '评审意见', name: 'evalText', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfMatlEval!list.shtml',
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
	if ($("#evalDate").length > 0)
		$("#evalDate").ligerDateEditor({showTime: false});
	if ($("#startEvalDate").length > 0)
		$("#startEvalDate").ligerDateEditor({showTime: false});
	if ($("#endEvalDate").length > 0)
		$("#endEvalDate").ligerDateEditor({showTime: false});
	if ($("#matlId").length > 0)
		$("#matlId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#isPass").length > 0)
		$("#isPass").ligerTextBox();
	if ($("#evaler").length > 0)
		$("#evaler").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#evalId").length > 0)
		$("#evalId").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#evalText").length > 0)
		$("#evalText").ligerTextBox();

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
			<td height="24" width="90" align="center">评审日期始：</td><td><input type="text" id="startEvalDate" name="wfMatlEval.startEvalDate"/></td>
			<td height="24" width="90" align="center">评审日期止：</td><td><input type="text" id="endEvalDate" name="wfMatlEval.endEvalDate"/></td>
			<td height="24" width="90" align="center">物料ID：</td><td><input type="text" id="matlId" name="wfMatlEval.matlId"/></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="wfMatlEval.userId"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否通过：</td><td><input type="text" id="isPass" name="wfMatlEval.isPass"/></td>
			<td height="24" width="90" align="center">1代表采购，2代表材料：</td><td><input type="text" id="evaler" name="wfMatlEval.evaler"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="status" name="wfMatlEval.status"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="evalId" name="wfMatlEval.evalId"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfMatlEval.wfNo"/></td>
			<td height="24" width="90" align="center">评审意见：</td><td><input type="text" id="evalText" name="wfMatlEval.evalText"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>