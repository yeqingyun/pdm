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

<script src="./include/js/zrsy/gpBtn.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'GpBtn'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#gpId").length > 0)
		$("#gpId").ligerComboBox();
	if ($("#nodeId").length > 0)
		$("#nodeId").ligerTextBox();
	if ($("#btnId").length > 0)
		$("#btnId").ligerComboBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="gpId" name="gpBtn.gpId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${gps}" var="gp">
						<option value="<c:out value="${gp.gpId}"/>"><c:out value="${gp.gpName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="nodeId" name="gpBtn.nodeId" size="30" value="<c:out value="${gpBtn.nodeId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="btnId" name="gpBtn.btnId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${btns}" var="btn">
						<option value="<c:out value="${btn.id}"/>"><c:out value="${btn.text}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>
</form>

</div>

</body>
</html>