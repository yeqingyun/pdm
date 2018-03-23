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

<script src="./include/js/zrprjt/prjtAuth.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtAuth'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#roleId").length > 0)
		$("#roleId").ligerTextBox();
	if ($("#isRead").length > 0)
		$("#isRead").ligerTextBox();
	if ($("#isLoad").length > 0)
		$("#isLoad").ligerTextBox();
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
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerComboBox();

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
			<td height="24" width="90" align="center">ID：</td><td><input type="text" id="roleId" name="prjtAuth.roleId" size="30" value="<c:out value="${prjtAuth.roleId}"/>"/></td>
			<td height="24" width="90" align="center">查看：</td><td><input type="text" id="isRead" name="prjtAuth.isRead" size="30" value="<c:out value="${prjtAuth.isRead}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">上传及下载：</td><td><input type="text" id="isLoad" name="prjtAuth.isLoad" size="30" value="<c:out value="${prjtAuth.isLoad}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="prjtAuth.status" size="30" value="<c:out value="${prjtAuth.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="prjtAuth.createBy" size="30" value="<c:out value="${prjtAuth.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="prjtAuth.lastUpd" size="30" value="<c:out value="${prjtAuth.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="prjtAuth.createDate" size="30" value="<c:out value="${prjtAuth.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="prjtAuth.lastDate" size="30" value="<c:out value="${prjtAuth.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">项目编号：</td>
			<td>
				<select id="prjtNo" name="prjtAuth.prjtNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtDefs}" var="prjtDef">
						<option value="<c:out value="${prjtDef.prjtNo}"/>"><c:out value="${prjtDef.prjtNm}"/></option>
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