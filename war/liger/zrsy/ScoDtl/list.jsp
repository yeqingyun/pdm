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

<script src="./include/js/zrsy/scoDtl.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'ScoDtl'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '范围ID', name: 'scoId', align: 'left', width: 120 },
			{ display: '公司ID', name: 'comId', align: 'left', width: 120 },
			{ display: '部门ID', name: 'detpId', align: 'left', width: 120 },
			{ display: '用户Id', name: 'usrId', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./ScoDtl!list.shtml',
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
	if ($("#scoId").length > 0)
		$("#scoId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#detpId").length > 0)
		$("#detpId").ligerTextBox();
	if ($("#usrId").length > 0)
		$("#usrId").ligerTextBox();

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
			<td height="24" width="90" align="center">范围ID：</td><td><input type="text" id="scoId" name="scoDtl.scoId"/></td>
			<td height="24" width="90" align="center">公司ID：</td><td><input type="text" id="comId" name="scoDtl.comId"/></td>
			<td height="24" width="90" align="center">部门ID：</td><td><input type="text" id="detpId" name="scoDtl.detpId"/></td>
			<td height="24" width="90" align="center">用户Id：</td><td><input type="text" id="usrId" name="scoDtl.usrId"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>