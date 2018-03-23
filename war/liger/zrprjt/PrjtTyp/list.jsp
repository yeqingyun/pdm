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

<script src="./include/js/zrprjt/prjtTyp.js?ver=1.0" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'PrjtTyp'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '分类ID', name: 'typId', align: 'left', width: 120 },
			{ display: '项目分类', name: 'typNm', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }},
			/**{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			**/
			{ display: '备注', name: 'remark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./PrjtTyp!list.shtml',
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

	if ($("#typNm").length > 0)
		$("#typNm").ligerTextBox();
	/**if ($("#typId").length > 0)
		$("#typId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
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
	
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
**/
});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<!--tr>
			<td height="24" width="90" align="center">分类ID：</td><td><input type="text" id="typId" name="prjtTyp.typId"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="prjtTyp.status"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="prjtTyp.createBy"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="prjtTyp.lastUpd"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="prjtTyp.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="prjtTyp.endCreateDate"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastDate" name="prjtTyp.startLastDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastDate" name="prjtTyp.endLastDate"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="prjtTyp.remark"/></td>
		</tr--><tr>
			<td height="24" width="90" align="center">项目分类：</td><td><input type="text" id="typNm" name="prjtTyp.typNm"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
