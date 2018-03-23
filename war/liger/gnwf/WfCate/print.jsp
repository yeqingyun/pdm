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

<script src="./include/js/gnwf/wfCate.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfCate'},
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
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#cateId").length > 0)
		$("#cateId").ligerTextBox();
	if ($("#cateParent").length > 0)
		$("#cateParent").ligerTextBox();
	if ($("#cateLevel").length > 0)
		$("#cateLevel").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#cateName").length > 0)
		$("#cateName").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfCate.createDate" size="30" validate="{required:true}" value="<c:out value="${wfCate.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfCate.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfCate.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">分类ID：</td><td><input type="text" id="cateId" name="wfCate.cateId" size="30" validate="{required:true}" value="<c:out value="${wfCate.cateId}"/>"/></td>
			<td height="24" width="90" align="center">上级分类：</td><td><input type="text" id="cateParent" name="wfCate.cateParent" size="30" validate="{required:true}" value="<c:out value="${wfCate.cateParent}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">分类级别：</td><td><input type="text" id="cateLevel" name="wfCate.cateLevel" size="30" validate="{required:true}" value="<c:out value="${wfCate.cateLevel}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfCate.status" size="30" validate="{required:true}" value="<c:out value="${wfCate.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfCate.createBy" size="30" validate="{required:true}" value="<c:out value="${wfCate.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfCate.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfCate.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">分类名称：</td><td><input type="text" id="cateName" name="wfCate.cateName" size="30" validate="{required:true}" value="<c:out value="${wfCate.cateName}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>