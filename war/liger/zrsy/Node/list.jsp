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

<script src="./include/js/zrsy/node.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var checkBoxData = [{ CheckBox: 0, text: '否' }, { CheckBox: 1, text: '是'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Node'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'ID', name: 'id', align: 'left', width: 120 ,hide: 1},
			{ display: '系统', name: 'syName', align: 'left', width: 120 },
			{ display: '上级ID', name: 'parent', align: 'left', width: 120 },
			{ display: '所属菜单', name: 'menuId', align: 'left', width: 120 },
			{ display: '级别', name: 'leve', align: 'left', width: 120 },
			{ display: '排序', name: 'sort', align: 'left', width: 120 },
			{ display: '名称', name: 'text', align: 'left', width: 120 },
			{ display: 'URL', name: 'url', align: 'left', width: 120 },
			{ display: '宽度', name: 'nodeWidth', align: 'left', width: 120 },
			{ display: '复选', name: 'checkBox', align: 'left', width: 120,type:'int',
				editor: { type: 'select', data: checkBoxData, valueColumnName: 'checkBox' },
                render: function (item)
                {
                    if (parseInt(item.checkBox) == 0) return '否';
                    return '是';
                }}
			
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Node!list.shtml',
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
	if ($("#id").length > 0)
		$("#id").ligerTextBox({width:145});
	if ($("#syId").length > 0)
		$("#syId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#parent").length > 0)
		$("#parent").ligerTextBox({width:145});
	if ($("#menuId").length > 0)
		$("#menuId").ligerComboBox({width:145});
	if ($("#nodeWidth").length > 0)
		$("#nodeWidth").ligerTextBox({width:145});
	if ($("#checkBox").length > 0)
		$("#checkBox").ligerTextBox({width:145});
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox({width:145});
	if ($("#sort").length > 0)
		$("#sort").ligerTextBox({width:145});
	if ($("#text").length > 0)
		$("#text").ligerTextBox({width:145});
	if ($("#url").length > 0)
		$("#url").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">系统：</td>
			<td>
				<select id="syId" name="node.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">上级ID：</td><td><input type="text" id="parent" name="node.parent"/></td>
			<td height="24" width="90" align="center">所属菜单：</td>
			<td>
				<select id="menuId" name="node.menuId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${menus}" var="menu">
						<option value="<c:out value="${menu.id}"/>"><c:out value="${menu.text}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="node.leve"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="text" name="node.text"/></td>
			<td height="24" width="90" align="center">URL：</td><td><input type="text" id="url" name="node.url"/></td>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="node.sort"/></td>
		</tr>
	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
