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

<script src="./include/js/gnwf/optStore.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'OptStore'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '物料编码', name: 'matlNo', align: 'left', width: 120 },
			{ display: '物料组编号', name: 'gpCode', align: 'left', width: 120 },
			{ display: '物料版本', name: 'matlRev', align: 'left', width: 120 },
			{ display: '物料名称', name: 'matlNm', align: 'left', width: 120 },
			{ display: '描述', name: 'matlDesc', align: 'left', width: 120 },
			{ display: '优选类型', name: 'optTyp', align: 'left', width: 120 },
			{ display: '物料类型', name: 'matlTyp', align: 'left', width: 120 },
			{ display: '批量', name: 'lotSize', align: 'left', width: 120 },
			{ display: '是否联板', name: 'isPanel', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./OptStore!list.shtml',
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
	if ($("#matlNo").length > 0)
		$("#matlNo").ligerTextBox({width:145});
	if ($("#gpCode").length > 0)
		$("#gpCode").ligerTextBox({width:145});
	if ($("#matlRev").length > 0)
		$("#matlRev").ligerTextBox({width:145});
	if ($("#matlNm").length > 0)
		$("#matlNm").ligerTextBox({width:145});
	if ($("#matlDesc").length > 0)
		$("#matlDesc").ligerTextBox({width:145});
	if ($("#optTyp").length > 0)
		$("#optTyp").ligerTextBox({width:145});
	if ($("#matlTyp").length > 0)
		$("#matlTyp").ligerTextBox({width:145});
	if ($("#lotSize").length > 0)
		$("#lotSize").ligerTextBox({width:145});
	if ($("#isPanel").length > 0)
		$("#isPanel").ligerTextBox({width:145});
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
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});

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
			<td height="24" width="90" align="center">物料编码：</td><td><input type="text" id="matlNo" name="optStore.matlNo"/></td>
			<td height="24" width="90" align="center">物料组编号：</td><td><input type="text" id="gpCode" name="optStore.gpCode"/></td>
			<td height="24" width="90" align="center">物料版本：</td><td><input type="text" id="matlRev" name="optStore.matlRev"/></td>
			<td height="24" width="90" align="center">物料名称：</td><td><input type="text" id="matlNm" name="optStore.matlNm"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">描述：</td><td><input type="text" id="matlDesc" name="optStore.matlDesc"/></td>
			<td height="24" width="90" align="center">优选类型：</td><td><input type="text" id="optTyp" name="optStore.optTyp"/></td>
			<td height="24" width="90" align="center">物料类型：</td><td><input type="text" id="matlTyp" name="optStore.matlTyp"/></td>
			<td height="24" width="90" align="center">批量：</td><td><input type="text" id="lotSize" name="optStore.lotSize"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否联板：</td><td><input type="text" id="isPanel" name="optStore.isPanel"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="optStore.status"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="optStore.createBy"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="optStore.startCreateDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="optStore.endCreateDate"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="optStore.lastUpd"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastDate" name="optStore.startLastDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastDate" name="optStore.endLastDate"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>