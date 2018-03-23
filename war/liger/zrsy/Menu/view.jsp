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

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrsy/menu.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'Menu'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	check();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
<input type="hidden" id="id" name="menu.id" size="30" value="<c:out value="${menu.id}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">系统：</td>
			<td>
				<select id="syId" name="menu.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"<c:if test="${syDef.syId==menu.syId}">selected</c:if>><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td align="left"></td>
			<td height="24" width="90" align="center">上级ID：</td><td><input type="text" id="parent" name="menu.parent" size="30" value="<c:out value="${menu.parent}"/>"  ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">菜单名：</td><td><input type="text" id="text" name="menu.text" size="30" value="<c:out value="${menu.text}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">宽度：</td><td><input type="text" id="width" name="menu.width" size="30" value="<c:out value="${menu.width}"/>" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
		<td align="left"></td></tr><tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="menu.leve" size="30" value="<c:out value="${menu.leve}"/>"  ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="menu.sort" size="30" value="<c:out value="${menu.sort}"/>"  ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
		<td align="left"></td></tr><tr>
			<td height="24" width="90" align="center">单击事件：</td><td><input type="text" id="click" name="menu.click" size="30" value="<c:out value="${menu.click}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">图标：</td><td><input type="text" id="icon" name="menu.icon" size="30" value="<c:out value="${menu.icon}"/>"/></td>
		<td align="left"></td></tr>

	</table>
</form>

</div>

</body>
</html>