<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/js/zrsy/menu.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Menu'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'ID号', name: 'id', align: 'left', width: 120 ,hide: 1},
			{ display: '系统', name: 'syName', align: 'left', width: 120 },
			{ display: '上级ID', name: 'parent', align: 'left', width: 120 },
			{ display: '级别', name: 'leve', align: 'left', width: 120 },
			{ display: '排序', name: 'sort', align: 'left', width: 120 },
			{ display: '菜单名', name: 'text', align: 'left', width: 120 },
			{ display: '单击事件', name: 'click', align: 'left', width: 120 },
			{ display: '宽度', name: 'width', align: 'left', width: 120},
			{ display: '图标', name: 'icon', align: 'left', width: 120}
			
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Menu!list.shtml',
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
		$("#id").ligerTextBox();
	if ($("#syId").length > 0)
		$("#syId").ligerComboBox();
	if ($("#parent").length > 0)
		$("#parent").ligerTextBox();
	if ($("#width").length > 0)
		$("#width").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
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
			<td height="24" width="90" align="center">系统：</td>
			<td>
				<select id="syId" name="menu.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">上级ID：</td><td><input type="text" id="parent" name="menu.parent"/></td>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="menu.sort"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">菜单名：</td><td><input type="text" id="text" name="menu.text"/></td>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="menu.leve"/></td>
			
			
			<td height="24" width="90" align="center">单击事件：</td><td><input type="text" id="click" name="menu.click"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
