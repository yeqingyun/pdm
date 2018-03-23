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

<script src="./include/js/zrsy/gpUsr.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'GpUsr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#gpId").length > 0)
		$("#gpId").ligerComboBox();
	if ($("#usrId").length > 0)
		$("#usrId").ligerComboBox();

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
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="gpId" name="gpUsr.gpId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${gps}" var="gp">
						<option value="<c:out value="${gp.gpId}"/>"><c:out value="${gp.gpName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="usrId" name="gpUsr.usrId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${usrs}" var="usr">
						<option value="<c:out value="${usr.id}"/>"><c:out value="${usr.usrName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>