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

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrsy/node.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Node'},
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
<input type="hidden" id="id" name="node.id" size="30" value="<c:out value="${node.id}"/>"/>
	<table width="90%">
		<tr>
		<td height="24" width="90" align="center">系统：</td>
			<td>
				<select id="syId" name="node.syId" style="width:135px">
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td><td align="left"></td>
			<td height="24" width="90" align="center">上级ID：</td>
			<td>
				<select id="parent" name="node.parent" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${nodes}" var="_node">
						<option value="<c:out value="${_node.id}"/>"<c:if test="${_node.id==node.parent}">selected</c:if>><c:out value="${_node.text}"/></option>
					</c:forEach>
				</select>
			</td>
			<td align="left"></td>
		</tr><tr>
		
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="text" name="node.text" size="30" value="<c:out value="${node.text}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">所属菜单：</td>
			<td>
				<select id="menuId" name="node.menuId" style="width:135px">
					<c:forEach items="${menus}" var="menu">
						<option value="<c:out value="${menu.id}"/>"><c:out value="${menu.text}"/></option>
					</c:forEach>
				</select>
			</td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">宽度：</td><td><input type="text" id="nodeWidth" name="node.nodeWidth" size="30" value="120" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">复选：</td><td>
			<select id="checkBox" name="node.checkBox" style="width:135px">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="node.leve" size="30" value="0" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="node.sort" size="30" value="0" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">URL：</td><td><input type="text" id="url" name="node.url" size="30" value="<c:out value="${node.url}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>