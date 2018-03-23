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

<script src="./include/js/zrsy/btn.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var disableData = [{ Disable: 0, text: '否' }, { Disable: 1, text: '是'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Btn'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'ID', name: 'id', align: 'left', width: 120 ,hide: 1},
			{ display: '系统', name: 'syId', align: 'left', width: 120 },
			{ display: '排序', name: 'sort', align: 'left', width: 120 },
			{ display: '名称', name: 'text', align: 'left', width: 120 },
			{ display: '单击事件', name: 'click', align: 'left', width: 120 },
			{ display: '可见', name: 'disable', align: 'left', width: 120,type:'int',
				editor: { type: 'select', data: disableData, valueColumnName: 'disable' },
                render: function (item)
                {
                    if (parseInt(item.disable) == 0) return '否';
                    return '是';
                }},
			{ display: '线条', name: 'line', align: 'left', width: 120 },
			{ display: '图标', name: 'icon', align: 'left', width: 120}

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Btn!list.shtml',
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
	if ($("#syId").length > 0)
		$("#syId").ligerComboBox();
	if ($("#line").length > 0)
		$("#line").ligerTextBox();
	if ($("#disable").length > 0)
		$("#disable").ligerComboBox();
	if ($("#sort").length > 0)
		$("#sort").ligerTextBox();
	if ($("#text").length > 0)
		$("#text").ligerTextBox();
	if ($("#click").length > 0)
		$("#click").ligerTextBox();
	if ($("#icon").length > 0)
		$("#icon").ligerTextBox();

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
			<td height="24" width="90" align="center">系统：</td><td>
			<select id="syId" name="btn.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">可见：</td><td>
				<select id="disable" name="btn.disable" style="width:135px">
					<option value="">请选择</option>
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</td>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="btn.sort"/></td>
			
		</tr>
		<tr>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="text" name="btn.text"/></td>
			<td height="24" width="90" align="center">单击事件：</td><td><input type="text" id="click" name="btn.click"/></td>
		</tr>
	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
