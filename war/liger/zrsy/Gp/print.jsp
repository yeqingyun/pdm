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

<script src="./include/js/zrsy/gp.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'Gp'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#gpId").length > 0)
		$("#gpId").ligerTextBox();
	if ($("#syId").length > 0)
		$("#syId").ligerComboBox();
	if ($("#gpName").length > 0)
		$("#gpName").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();

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
			<td height="24" width="90" align="center">组Id：</td><td><input type="text" id="gpId" name="gp.gpId" size="30" value="<c:out value="${gp.gpId}"/>"/></td>
			<td height="24" width="90" align="center">系统：</td>
			<td>
				<select id="syId" name="gp.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">组名称：</td><td><input type="text" id="gpName" name="gp.gpName" size="30" value="<c:out value="${gp.gpName}"/>"/></td>
			<td height="24" width="90" align="center">说明：</td><td><input type="text" id="remark" name="gp.remark" size="30" value="<c:out value="${gp.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>