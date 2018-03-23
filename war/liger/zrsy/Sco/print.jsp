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

<script src="./include/js/zrsy/sco.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'Sco'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#scoId").length > 0)
		$("#scoId").ligerTextBox();
	if ($("#scope").length > 0)
		$("#scope").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#scoNm").length > 0)
		$("#scoNm").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="sco.createDate" size="30" validate="{required:true}" value="<c:out value="${sco.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="sco.lastDate" size="30" validate="{required:true}" value="<c:out value="${sco.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">范围ID：</td><td><input type="text" id="scoId" name="sco.scoId" size="30" validate="{required:true}" value="<c:out value="${sco.scoId}"/>"/></td>
			<td height="24" width="90" align="center">类型：</td><td><input type="text" id="scope" name="sco.scope" size="30" validate="{required:true}" value="<c:out value="${sco.scope}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="sco.status" size="30" validate="{required:true}" value="<c:out value="${sco.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="sco.createBy" size="30" validate="{required:true}" value="<c:out value="${sco.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="sco.lastUpd" size="30" validate="{required:true}" value="<c:out value="${sco.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">范围名称：</td><td><input type="text" id="scoNm" name="sco.scoNm" size="30" validate="{required:true}" value="<c:out value="${sco.scoNm}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>