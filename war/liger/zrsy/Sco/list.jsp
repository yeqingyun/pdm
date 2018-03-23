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

<script src="./include/js/zrsy/sco.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Sco'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			
			{ display: '范围ID', name: 'scoId', align: 'left', width: 120 },
			{ display: '范围名称', name: 'scoNm', align: 'left', width: 120 },
			{ display: '类型', name: 'scope', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }
		},
			{ display: '创建人', name: 'createBy', align: 'left', width: 120  ,hide: 1},
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120  ,hide: 1}
			

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Sco!list.shtml',
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
	if ($("#scope").length > 0)
		$("#scope").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#scoNm").length > 0)
		$("#scoNm").ligerTextBox();
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
		<td height="24" width="90" align="center">范围名称：</td><td><input type="text" id="scoNm" name="sco.scoNm"/></td>
			<td height="24" width="90" align="center">类型：</td><td><input type="text" id="scope" name="sco.scope"/></td>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="sco.status" style="width:135px">
					<option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			</td>
		</tr>
	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>