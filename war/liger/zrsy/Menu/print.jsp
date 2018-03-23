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
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'Menu'},
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
<body>

<div id="layout1">
	<div position="center">

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">ID号：</td><td><input type="text" id="id" name="menu.id" size="30" value="<c:out value="${menu.id}"/>"/></td>
			<td height="24" width="90" align="center">系统：</td>
			<td>
				<select id="syId" name="menu.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">上级ID：</td><td><input type="text" id="parent" name="menu.parent" size="30" value="<c:out value="${menu.parent}"/>"/></td>
			<td height="24" width="90" align="center">宽度：</td><td><input type="text" id="width" name="menu.width" size="30" value="<c:out value="${menu.width}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="menu.leve" size="30" value="<c:out value="${menu.leve}"/>"/></td>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="menu.sort" size="30" value="<c:out value="${menu.sort}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">菜单名：</td><td><input type="text" id="text" name="menu.text" size="30" value="<c:out value="${menu.text}"/>"/></td>
			<td height="24" width="90" align="center">单击事件：</td><td><input type="text" id="click" name="menu.click" size="30" value="<c:out value="${menu.click}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">图标：</td><td><input type="text" id="icon" name="menu.icon" size="30" value="<c:out value="${menu.icon}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>