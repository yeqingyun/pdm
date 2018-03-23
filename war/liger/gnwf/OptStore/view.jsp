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

<script src="./include/js/gnwf/optStore.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#matlNo").length > 0)
		$("#matlNo").ligerTextBox({width:145});
	if ($("#gpCode").length > 0)
		$("#gpCode").ligerTextBox({width:145});
	if ($("#matlRev").length > 0)
		$("#matlRev").ligerTextBox({width:145});
	if ($("#matlNm").length > 0)
		$("#matlNm").ligerTextBox({width:145});
	if ($("#matlDesc").length > 0)
		$("#matlDesc").ligerTextBox({width:145});
	if ($("#optTyp").length > 0)
		$("#optTyp").ligerTextBox({width:145});
	if ($("#matlTyp").length > 0)
		$("#matlTyp").ligerTextBox({width:145});
	if ($("#lotSize").length > 0)
		$("#lotSize").ligerTextBox({width:145});
	if ($("#isPanel").length > 0)
		$("#isPanel").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox({width:145});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">物料编码：</td><td><input type="text" id="matlNo" name="optStore.matlNo" validate="{required:true}" value="<c:out value="${optStore.matlNo}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">物料组编号：</td><td><input type="text" id="gpCode" name="optStore.gpCode" validate="{required:true}" value="<c:out value="${optStore.gpCode}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">物料版本：</td><td><input type="text" id="matlRev" name="optStore.matlRev" validate="{required:true}" value="<c:out value="${optStore.matlRev}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">物料名称：</td><td><input type="text" id="matlNm" name="optStore.matlNm" validate="{required:true}" value="<c:out value="${optStore.matlNm}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">描述：</td><td><input type="text" id="matlDesc" name="optStore.matlDesc" validate="{required:true}" value="<c:out value="${optStore.matlDesc}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">优选类型：</td><td><input type="text" id="optTyp" name="optStore.optTyp" validate="{required:true}" value="<c:out value="${optStore.optTyp}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">物料类型：</td><td><input type="text" id="matlTyp" name="optStore.matlTyp" validate="{required:true}" value="<c:out value="${optStore.matlTyp}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">批量：</td><td><input type="text" id="lotSize" name="optStore.lotSize" validate="{required:true}" value="<c:out value="${optStore.lotSize}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否联板：</td><td><input type="text" id="isPanel" name="optStore.isPanel" validate="{required:true}" value="<c:out value="${optStore.isPanel}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="optStore.status" validate="{required:true}" value="<c:out value="${optStore.status}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="optStore.createBy" validate="{required:true}" value="<c:out value="${optStore.createBy}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="optStore.createDate" validate="{required:true}" value="<c:out value="${optStore.createDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="optStore.lastUpd" validate="{required:true}" value="<c:out value="${optStore.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="optStore.lastDate" validate="{required:true}" value="<c:out value="${optStore.lastDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>