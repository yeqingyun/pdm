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

<script src="./include/js/zrsy/chln.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Chln'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '流水号', name: 'chlnNo', align: 'left', width: 120 },
			{ display: '年', name: 'year', align: 'left', width: 120 },
			{ display: '月', name: 'month', align: 'left', width: 120 },
			{ display: '日', name: 'day', align: 'left', width: 120 },
			{ display: '单据类型', name: 'chlnTyp', align: 'left', width: 120 },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 ,hide: 1},
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 ,hide: 1},
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 }
			

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Chln!list.shtml',
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
	if ($("#year").length > 0)
		$("#year").ligerTextBox();
	if ($("#month").length > 0)
		$("#month").ligerTextBox();
	if ($("#day").length > 0)
		$("#day").ligerTextBox();
	if ($("#chlnNo").length > 0)
		$("#chlnNo").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#chlnTyp").length > 0)
		$("#chlnTyp").ligerTextBox();

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
			<td height="24" width="90" align="center">流水号：</td><td><input type="text" id="chlnNo" name="chln.chlnNo"/></td>
			<td height="24" width="90" align="center">年：</td><td><input type="text" id="year" name="chln.year"/></td>
			<td height="24" width="90" align="center">月：</td><td><input type="text" id="month" name="chln.month"/></td>
			<td height="24" width="90" align="center">日：</td><td><input type="text" id="day" name="chln.day"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">单据类型：</td><td><input type="text" id="chlnTyp" name="chln.chlnTyp"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="chln.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="chln.endCreateDate"/></td>
		</tr>
			

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
