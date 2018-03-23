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

<script src="./include/js/zrsy/billSubs.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'BillSubs'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#id").length > 0)
		$("#id").ligerTextBox({width:145});
	if ($("#syId").length > 0)
		$("#syId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#subs").length > 0)
		$("#subs").ligerTextBox({width:145});
	if ($("#currSn").length > 0)
		$("#currSn").ligerTextBox({width:145});
	if ($("#state").length > 0)
		$("#state").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">ID：</td><td><input type="text" id="id" name="billSubs.id" validate="{required:true}" value="<c:out value="${billSubs.id}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">项目Id：</td>
			<td>
				<select id="syId" name="billSubs.syId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${syDefs}" var="syDef">
						<option value="<c:out value="${syDef.syId}"/>"><c:out value="${syDef.syId}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">分段(A-Z)：</td><td><input type="text" id="subs" name="billSubs.subs" validate="{required:true}" value="<c:out value="${billSubs.subs}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">当前流水(00-99)：</td><td><input type="text" id="currSn" name="billSubs.currSn" validate="{required:true}" value="<c:out value="${billSubs.currSn}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="state" name="billSubs.state" validate="{required:true}" value="<c:out value="${billSubs.state}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>