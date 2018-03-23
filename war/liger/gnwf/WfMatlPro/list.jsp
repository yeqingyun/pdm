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

<script src="./include/js/gnwf/wfMatlPro.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfMatlPro'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '采购审批日期', name: 'purRevDate', align: 'left', width: 120 },
			{ display: '材料审批日期', name: 'matRevDate', align: 'left', width: 120 },
			{ display: '产品经理审批日期', name: 'managerRevDate', align: 'left', width: 120 },
			{ display: 'MatlProId', name: 'matlProId', align: 'left', width: 120 },
			{ display: '采购是否通过', name: 'isPurPass', align: 'left', width: 120 },
			{ display: '材料审批结果', name: 'isMatPass', align: 'left', width: 120 },
			{ display: '产品经理审批结果', name: 'isManagerPass', align: 'left', width: 120 },
			{ display: '工作流编号', name: 'wfNo', align: 'left', width: 120 },
			{ display: '项目名称', name: 'proName', align: 'left', width: 120 },
			{ display: '设计人员', name: 'designName', align: 'left', width: 120 },
			{ display: '产品经理', name: 'manageName', align: 'left', width: 120 },
			{ display: '功能描述', name: 'proDesc', align: 'left', width: 120 },
			{ display: '产品目前处于研发流程的阶段', name: 'curStep', align: 'left', width: 120 },
			{ display: '产品经理审批人', name: 'managerReview', align: 'left', width: 120 },
			{ display: '产品经理审批意见', name: 'managerRemark', align: 'left', width: 120 },
			{ display: '报告版本', name: 'curVersion', align: 'left', width: 120 },
			{ display: '成本要求', name: 'proCost', align: 'left', width: 120 },
			{ display: '采购工程师', name: 'purReview', align: 'left', width: 120 },
			{ display: '采购意见', name: 'purRemark', align: 'left', width: 120 },
			{ display: '材料工程师', name: 'matReveiw', align: 'left', width: 120 },
			{ display: '材料审批意见', name: 'matRemark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfMatlPro!list.shtml',
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
	if ($("#purRevDate").length > 0)
		$("#purRevDate").ligerDateEditor({showTime: false});
	if ($("#startPurRevDate").length > 0)
		$("#startPurRevDate").ligerDateEditor({showTime: false});
	if ($("#endPurRevDate").length > 0)
		$("#endPurRevDate").ligerDateEditor({showTime: false});
	if ($("#matRevDate").length > 0)
		$("#matRevDate").ligerDateEditor({showTime: false});
	if ($("#startMatRevDate").length > 0)
		$("#startMatRevDate").ligerDateEditor({showTime: false});
	if ($("#endMatRevDate").length > 0)
		$("#endMatRevDate").ligerDateEditor({showTime: false});
	if ($("#managerRevDate").length > 0)
		$("#managerRevDate").ligerDateEditor({showTime: false});
	if ($("#startManagerRevDate").length > 0)
		$("#startManagerRevDate").ligerDateEditor({showTime: false});
	if ($("#endManagerRevDate").length > 0)
		$("#endManagerRevDate").ligerDateEditor({showTime: false});
	if ($("#matlProId").length > 0)
		$("#matlProId").ligerTextBox();
	if ($("#isPurPass").length > 0)
		$("#isPurPass").ligerTextBox();
	if ($("#isMatPass").length > 0)
		$("#isMatPass").ligerTextBox();
	if ($("#isManagerPass").length > 0)
		$("#isManagerPass").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#proName").length > 0)
		$("#proName").ligerTextBox();
	if ($("#designName").length > 0)
		$("#designName").ligerTextBox();
	if ($("#manageName").length > 0)
		$("#manageName").ligerTextBox();
	if ($("#proDesc").length > 0)
		$("#proDesc").ligerTextBox();
	if ($("#curStep").length > 0)
		$("#curStep").ligerTextBox();
	if ($("#managerReview").length > 0)
		$("#managerReview").ligerTextBox();
	if ($("#managerRemark").length > 0)
		$("#managerRemark").ligerTextBox();
	if ($("#curVersion").length > 0)
		$("#curVersion").ligerTextBox();
	if ($("#proCost").length > 0)
		$("#proCost").ligerTextBox();
	if ($("#purReview").length > 0)
		$("#purReview").ligerTextBox();
	if ($("#purRemark").length > 0)
		$("#purRemark").ligerTextBox();
	if ($("#matReveiw").length > 0)
		$("#matReveiw").ligerTextBox();
	if ($("#matRemark").length > 0)
		$("#matRemark").ligerTextBox();

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
			<td height="24" width="90" align="center">采购审批日期始：</td><td><input type="text" id="startPurRevDate" name="wfMatlPro.startPurRevDate"/></td>
			<td height="24" width="90" align="center">采购审批日期止：</td><td><input type="text" id="endPurRevDate" name="wfMatlPro.endPurRevDate"/></td>
			<td height="24" width="90" align="center">材料审批日期始：</td><td><input type="text" id="startMatRevDate" name="wfMatlPro.startMatRevDate"/></td>
			<td height="24" width="90" align="center">材料审批日期止：</td><td><input type="text" id="endMatRevDate" name="wfMatlPro.endMatRevDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">产品经理审批日期始：</td><td><input type="text" id="startManagerRevDate" name="wfMatlPro.startManagerRevDate"/></td>
			<td height="24" width="90" align="center">产品经理审批日期止：</td><td><input type="text" id="endManagerRevDate" name="wfMatlPro.endManagerRevDate"/></td>
			<td height="24" width="90" align="center">MatlProId：</td><td><input type="text" id="matlProId" name="wfMatlPro.matlProId"/></td>
			<td height="24" width="90" align="center">采购是否通过：</td><td><input type="text" id="isPurPass" name="wfMatlPro.isPurPass"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">材料审批结果：</td><td><input type="text" id="isMatPass" name="wfMatlPro.isMatPass"/></td>
			<td height="24" width="90" align="center">产品经理审批结果：</td><td><input type="text" id="isManagerPass" name="wfMatlPro.isManagerPass"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfMatlPro.wfNo"/></td>
			<td height="24" width="90" align="center">项目名称：</td><td><input type="text" id="proName" name="wfMatlPro.proName"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">设计人员：</td><td><input type="text" id="designName" name="wfMatlPro.designName"/></td>
			<td height="24" width="90" align="center">产品经理：</td><td><input type="text" id="manageName" name="wfMatlPro.manageName"/></td>
			<td height="24" width="90" align="center">功能描述：</td><td><input type="text" id="proDesc" name="wfMatlPro.proDesc"/></td>
			<td height="24" width="90" align="center">产品目前处于研发流程的阶段：</td><td><input type="text" id="curStep" name="wfMatlPro.curStep"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">产品经理审批人：</td><td><input type="text" id="managerReview" name="wfMatlPro.managerReview"/></td>
			<td height="24" width="90" align="center">产品经理审批意见：</td><td><input type="text" id="managerRemark" name="wfMatlPro.managerRemark"/></td>
			<td height="24" width="90" align="center">报告版本：</td><td><input type="text" id="curVersion" name="wfMatlPro.curVersion"/></td>
			<td height="24" width="90" align="center">成本要求：</td><td><input type="text" id="proCost" name="wfMatlPro.proCost"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">采购工程师：</td><td><input type="text" id="purReview" name="wfMatlPro.purReview"/></td>
			<td height="24" width="90" align="center">采购意见：</td><td><input type="text" id="purRemark" name="wfMatlPro.purRemark"/></td>
			<td height="24" width="90" align="center">材料工程师：</td><td><input type="text" id="matReveiw" name="wfMatlPro.matReveiw"/></td>
			<td height="24" width="90" align="center">材料审批意见：</td><td><input type="text" id="matRemark" name="wfMatlPro.matRemark"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>