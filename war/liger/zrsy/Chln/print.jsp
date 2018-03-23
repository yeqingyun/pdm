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

<script src="./include/js/zrsy/chln.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'Chln'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#year").length > 0)
		$("#year").ligerTextBox();
	if ($("#month").length > 0)
		$("#month").ligerTextBox();
	if ($("#day").length > 0)
		$("#day").ligerTextBox();
	if ($("#chlnNo").length > 0)
		$("#chlnNo").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
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
	if ($("#chlnTyp").length > 0)
		$("#chlnTyp").ligerTextBox();

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
			<td height="24" width="90" align="center">年：</td><td><input type="text" id="year" name="chln.year" size="30" value="<c:out value="${chln.year}"/>"/></td>
			<td height="24" width="90" align="center">月：</td><td><input type="text" id="month" name="chln.month" size="30" value="<c:out value="${chln.month}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">日：</td><td><input type="text" id="day" name="chln.day" size="30" value="<c:out value="${chln.day}"/>"/></td>
			<td height="24" width="90" align="center">流水号：</td><td><input type="text" id="chlnNo" name="chln.chlnNo" size="30" value="<c:out value="${chln.chlnNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="chln.createBy" size="30" value="<c:out value="${chln.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="chln.lastUpd" size="30" value="<c:out value="${chln.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="chln.createDate" size="30" value="<c:out value="${chln.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="chln.lastDate" size="30" value="<c:out value="${chln.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">单据类型：</td><td><input type="text" id="chlnTyp" name="chln.chlnTyp" size="30" value="<c:out value="${chln.chlnTyp}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>