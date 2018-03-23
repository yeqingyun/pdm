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
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'Node'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#id").length > 0)
		$("#id").ligerTextBox();
	if ($("#syId").length > 0)
		$("#syId").ligerComboBox();
	if ($("#parent").length > 0)
		$("#parent").ligerTextBox();
	if ($("#menuId").length > 0)
		$("#menuId").ligerComboBox();
	if ($("#nodeWidth").length > 0)
		$("#nodeWidth").ligerTextBox();
	if ($("#checkBox").length > 0)
		$("#checkBox").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#sort").length > 0)
		$("#sort").ligerTextBox();
	if ($("#text").length > 0)
		$("#text").ligerTextBox();
	if ($("#url").length > 0)
		$("#url").ligerTextBox();

});
</script>
</head>
<body>

<div id="layout1">
	<div position="center">

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">ID：</td><td><input type="text" id="id" name="node.id" size="30" value="<c:out value="${node.id}"/>"/></td>
			<td height="24" width="90" align="center">系统：</td>
			<td>
				<select id="syId" name="node.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">上级ID：</td><td><input type="text" id="parent" name="node.parent" size="30" value="<c:out value="${node.parent}"/>"/></td>
			<td height="24" width="90" align="center">所属菜单：</td>
			<td>
				<select id="menuId" name="node.menuId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${menus}" var="menu">
						<option value="<c:out value="${menu.id}"/>"><c:out value="${menu.text}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">宽度：</td><td><input type="text" id="nodeWidth" name="node.nodeWidth" size="30" value="<c:out value="${node.nodeWidth}"/>"/></td>
			<td height="24" width="90" align="center">复选：</td><td><input type="text" id="checkBox" name="node.checkBox" size="30" value="<c:out value="${node.checkBox}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="node.leve" size="30" value="<c:out value="${node.leve}"/>"/></td>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="node.sort" size="30" value="<c:out value="${node.sort}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="text" name="node.text" size="30" value="<c:out value="${node.text}"/>"/></td>
			<td height="24" width="90" align="center">URL：</td><td><input type="text" id="url" name="node.url" size="30" value="<c:out value="${node.url}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>