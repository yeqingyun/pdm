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

<script src="./include/js/com.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'Com'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#parent").length > 0)
		$("#parent").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
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
	if ($("#comNo").length > 0)
		$("#comNo").ligerTextBox();
	if ($("#comNm").length > 0)
		$("#comNm").ligerTextBox();
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
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="comId" name="com.comId" size="30" value="<c:out value="${com.comId}"/>"/></td>
			<td height="24" width="90" align="center">上级公司：</td><td><input type="text" id="parent" name="com.parent" size="30" value="<c:out value="${com.parent}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="com.leve" size="30" value="<c:out value="${com.leve}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="com.status" size="30" value="<c:out value="${com.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="com.createBy" size="30" value="<c:out value="${com.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="com.lastUpd" size="30" value="<c:out value="${com.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="com.createDate" size="30" value="<c:out value="${com.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="com.lastDate" size="30" value="<c:out value="${com.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">公司编号：</td><td><input type="text" id="comNo" name="com.comNo" size="30" value="<c:out value="${com.comNo}"/>"/></td>
			<td height="24" width="90" align="center">公司名称：</td><td><input type="text" id="comNm" name="com.comNm" size="30" value="<c:out value="${com.comNm}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="com.remark" size="30" value="<c:out value="${com.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>