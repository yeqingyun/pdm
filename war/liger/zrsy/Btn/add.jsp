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

<script src="./include/js/zrsy/btn.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Btn'},
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
<input type="hidden" id="id" name="btn.id" size="30" value="<c:out value="${btn.id}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">系统：</td><td>
			<select id="syId" name="btn.syId" style="width:135px">
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td><td align="left"></td>
			<td height="24" width="90" align="center">可见：</td><td>
			<select id="disable" name="btn.disable" style="width:135px">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</td><td align="left"></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="text" name="btn.text" value="<c:out value="${btn.text}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td><td height="24" width="90" align="center">线条：</td><td><input type="text" id="line" name="btn.line" value="0" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
		<td align="left"></td></tr>
		<tr>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="btn.sort" value="0"  ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td><td height="24" width="90" align="center">图标：</td><td><input type="text" id="icon" name="btn.icon" value="<c:out value="${btn.icon}"/>"/></td>
		<td align="left"></td></tr>
		<tr>
			<td height="24" width="90" align="center">单击事件：</td><td><input type="text" id="click" name="btn.click" value="<c:out value="${btn.click}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">定制图标：</td><td><input type="text" id="img" name="btn.img" size="30" value="<c:out value="${btn.img}"/>"/></td>
			<td align="left"></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>