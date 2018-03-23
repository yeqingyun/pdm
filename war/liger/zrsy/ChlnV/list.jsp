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

<script src="./include/js/zrsy/chlnV.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'ChlnV'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '<c:out value="${labchlnV.fileNm}"/><!--文件名-->', name: 'fileNm', align: 'left', width: 120 },
			{ display: '<c:out value="${labchlnV.chlnNo}"/><!--版本号-->', name: 'chlnNo', align: 'left', width: 120 },
			{ display: '<c:out value="${labchlnV.createBy}"/><!--创建人-->', name: 'createBy', align: 'left', width: 120 },
			{ display: '<c:out value="${labchlnV.createDate}"/><!--创建日期-->', name: 'createDate', align: 'left', width: 120 },
			{ display: '<c:out value="${labchlnV.lastUpd}"/><!--最后更新-->', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '<c:out value="${labchlnV.lastDate}"/><!--更新日期-->', name: 'lastDate', align: 'left', width: 120 },
			{ display: '<c:out value="${labchlnV.chlnTyp}"/><!--单据类型-->', name: 'chlnTyp', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./ChlnV!list.shtml',
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
	if ($("#fileNm").length > 0)
		$("#fileNm").ligerTextBox({width:145});
	if ($("#chlnNo").length > 0)
		$("#chlnNo").ligerTextBox({width:145});
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
	if ($("#chlnTyp").length > 0)
		$("#chlnTyp").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center"><c:out value="${labchlnV.fileNm}"/><!--文件名-->：</td><td><input type="text" id="fileNm" name="chlnV.fileNm"/></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.chlnNo}"/><!--版本号-->：</td><td><input type="text" id="chlnNo" name="chlnV.chlnNo"/></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.createBy}"/><!--创建人-->：</td><td><input type="text" id="createBy" name="chlnV.createBy"/></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.createDate1}"/><!--创建日期起-->：</td><td><input type="text" id="startCreateDate" name="chlnV.startCreateDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.createDate2}"/><!--创建日期止-->：</td><td><input type="text" id="endCreateDate" name="chlnV.endCreateDate"/></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.lastUpd}"/><!--最后更新-->：</td><td><input type="text" id="lastUpd" name="chlnV.lastUpd"/></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.lastDate1}"/><!--更新日期起-->：</td><td><input type="text" id="startLastDate" name="chlnV.startLastDate"/></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.lastDate2}"/><!--更新日期止-->：</td><td><input type="text" id="endLastDate" name="chlnV.endLastDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.chlnTyp}"/><!--单据类型-->：</td><td><input type="text" id="chlnTyp" name="chlnV.chlnTyp"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>