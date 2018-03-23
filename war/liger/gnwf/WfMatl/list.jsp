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

<script src="./include/js/gnwf/wfMatl.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfMatl'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastUpdDate', align: 'left', width: 120 },
			{ display: '物料记录ID', name: 'matlId', align: 'left', width: 120 },
			{ display: '物料类型', name: 'matlType', align: 'left', width: 120 },
			{ display: '是否可替代', name: 'isSubs', align: 'left', width: 120 },
			{ display: '风险等级', name: 'risk', align: 'left', width: 120 },
			{ display: '批量', name: 'lotSize', align: 'left', width: 120 },
			{ display: '是否联板', name: 'isPanel', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '物料名称', name: 'matlName', align: 'left', width: 120 },
			{ display: '物料描述', name: 'matlDesc', align: 'left', width: 120 },
			{ display: '供应商', name: 'supplier', align: 'left', width: 120 },
			{ display: '备注', name: 'remark', align: 'left', width: 120 },
			{ display: '物料型号(外部编码)', name: 'supNo', align: 'left', width: 120 },
			{ display: '物料选型原因', name: 'matlEvalDesc', align: 'left', width: 120 },
			{ display: '物料编码', name: 'matlNo', align: 'left', width: 120 },
			{ display: '工作流编号', name: 'wfNo', align: 'left', width: 120 },
			{ display: '物料等级', name: 'matlLevel', align: 'left', width: 120 },
			{ display: 'null', name: 'historyLevel', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfMatl!list.shtml',
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
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#matlId").length > 0)
		$("#matlId").ligerTextBox();
	if ($("#matlType").length > 0)
		$("#matlType").ligerTextBox();
	if ($("#isSubs").length > 0)
		$("#isSubs").ligerTextBox();
	if ($("#risk").length > 0)
		$("#risk").ligerTextBox();
	if ($("#lotSize").length > 0)
		$("#lotSize").ligerTextBox();
	if ($("#isPanel").length > 0)
		$("#isPanel").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#matlName").length > 0)
		$("#matlName").ligerTextBox();
	if ($("#matlDesc").length > 0)
		$("#matlDesc").ligerTextBox();
	if ($("#supplier").length > 0)
		$("#supplier").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	if ($("#supNo").length > 0)
		$("#supNo").ligerTextBox();
	if ($("#matlEvalDesc").length > 0)
		$("#matlEvalDesc").ligerTextBox();
	if ($("#matlNo").length > 0)
		$("#matlNo").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#matlLevel").length > 0)
		$("#matlLevel").ligerTextBox();
	if ($("#historyLevel").length > 0)
		$("#historyLevel").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="wfMatl.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="wfMatl.endCreateDate"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastUpdDate" name="wfMatl.startLastUpdDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastUpdDate" name="wfMatl.endLastUpdDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">物料记录ID：</td><td><input type="text" id="matlId" name="wfMatl.matlId"/></td>
			<td height="24" width="90" align="center">物料类型：</td><td><input type="text" id="matlType" name="wfMatl.matlType"/></td>
			<td height="24" width="90" align="center">是否可替代：</td><td><input type="text" id="isSubs" name="wfMatl.isSubs"/></td>
			<td height="24" width="90" align="center">风险等级：</td><td><input type="text" id="risk" name="wfMatl.risk"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">批量：</td><td><input type="text" id="lotSize" name="wfMatl.lotSize"/></td>
			<td height="24" width="90" align="center">是否联板：</td><td><input type="text" id="isPanel" name="wfMatl.isPanel"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfMatl.status"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfMatl.createBy"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfMatl.lastUpd"/></td>
			<td height="24" width="90" align="center">物料名称：</td><td><input type="text" id="matlName" name="wfMatl.matlName"/></td>
			<td height="24" width="90" align="center">物料描述：</td><td><input type="text" id="matlDesc" name="wfMatl.matlDesc"/></td>
			<td height="24" width="90" align="center">供应商：</td><td><input type="text" id="supplier" name="wfMatl.supplier"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="wfMatl.remark"/></td>
			<td height="24" width="90" align="center">物料型号(外部编码)：</td><td><input type="text" id="supNo" name="wfMatl.supNo"/></td>
			<td height="24" width="90" align="center">物料选型原因：</td><td><input type="text" id="matlEvalDesc" name="wfMatl.matlEvalDesc"/></td>
			<td height="24" width="90" align="center">物料编码：</td><td><input type="text" id="matlNo" name="wfMatl.matlNo"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfMatl.wfNo"/></td>
			<td height="24" width="90" align="center">物料等级：</td><td><input type="text" id="matlLevel" name="wfMatl.matlLevel"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="historyLevel" name="wfMatl.historyLevel"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>