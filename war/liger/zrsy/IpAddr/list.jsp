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

<script src="./include/js/zrsy/ipAddr.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
var isWideData = [{ IsWide: 0, text: '否' }, { IsWide: 1, text: '是'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'IpAddr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'IP标识', name: 'ipId', align: 'left', width: 120 },
			{ display: 'IP网段', name: 'ipSegment', align: 'left', width: 120 },
			{ display: '描述', name: 'addrDesc', align: 'left', width: 120 },
			{ display: '是否外网', name: 'isWide', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: isWideData, valueColumnName: 'isWide' },
                render: function (item)
                {
                    if (parseInt(item.isWide) == 0) return '否';
                    return '是';
                }
		},
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }
		},
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 ,hide:1},
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 ,hide:1},
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./IpAddr!list.shtml',
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
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#ipId").length > 0)
		$("#ipId").ligerTextBox({width:145});
	if ($("#isWide").length > 0)
		$("#isWide").ligerComboBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerComboBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox({width:145});
	if ($("#ipSegment").length > 0)
		$("#ipSegment").ligerTextBox({width:145});
	if ($("#addrDesc").length > 0)
		$("#addrDesc").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">IP网段：</td><td><input type="text" id="ipSegment" name="ipAddr.ipSegment"/></td>
			<td height="24" width="90" align="center">是否外网：</td><td>
			<select id="isWide" name="ipAddr.isWide" style="width:135px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
			<td height="24" width="90" align="center">状态：</td><td>
				<select id="status" name="ipAddr.status" style="width:135px">
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