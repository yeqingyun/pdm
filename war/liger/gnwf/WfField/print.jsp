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

<script src="./include/js/gnwf/wfField.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfField'},
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
	if ($("#fieldId").length > 0)
		$("#fieldId").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#stepId").length > 0)
		$("#stepId").ligerTextBox();
	if ($("#fieldType").length > 0)
		$("#fieldType").ligerTextBox();
	if ($("#isGather").length > 0)
		$("#isGather").ligerTextBox();
	if ($("#isNull").length > 0)
		$("#isNull").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#isList").length > 0)
		$("#isList").ligerTextBox();
	if ($("#fieldCode").length > 0)
		$("#fieldCode").ligerTextBox();
	if ($("#fieldName").length > 0)
		$("#fieldName").ligerTextBox();
	if ($("#fieldSql").length > 0)
		$("#fieldSql").ligerTextBox();
	if ($("#fieldJs").length > 0)
		$("#fieldJs").ligerTextBox();
	if ($("#defaultValue").length > 0)
		$("#defaultValue").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfField.createDate" size="30" validate="{required:true}" value="<c:out value="${wfField.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfField.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfField.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">字段ID：</td><td><input type="text" id="fieldId" name="wfField.fieldId" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldId}"/>"/></td>
			<td height="24" width="90" align="center">工作流ID：</td><td><input type="text" id="flowId" name="wfField.flowId" size="30" validate="{required:true}" value="<c:out value="${wfField.flowId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">步骤ID：</td><td><input type="text" id="stepId" name="wfField.stepId" size="30" validate="{required:true}" value="<c:out value="${wfField.stepId}"/>"/></td>
			<td height="24" width="90" align="center">字段类型：</td><td><input type="text" id="fieldType" name="wfField.fieldType" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldType}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否集合：</td><td><input type="text" id="isGather" name="wfField.isGather" size="30" validate="{required:true}" value="<c:out value="${wfField.isGather}"/>"/></td>
			<td height="24" width="90" align="center">是否允许空：</td><td><input type="text" id="isNull" name="wfField.isNull" size="30" validate="{required:true}" value="<c:out value="${wfField.isNull}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfField.status" size="30" validate="{required:true}" value="<c:out value="${wfField.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfField.createBy" size="30" validate="{required:true}" value="<c:out value="${wfField.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfField.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfField.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="isList" name="wfField.isList" size="30" validate="{required:true}" value="<c:out value="${wfField.isList}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">字段名：</td><td><input type="text" id="fieldCode" name="wfField.fieldCode" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldCode}"/>"/></td>
			<td height="24" width="90" align="center">中文名：</td><td><input type="text" id="fieldName" name="wfField.fieldName" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldName}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">Sql配置：</td><td><input type="text" id="fieldSql" name="wfField.fieldSql" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldSql}"/>"/></td>
			<td height="24" width="90" align="center">JS函数：</td><td><input type="text" id="fieldJs" name="wfField.fieldJs" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldJs}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="defaultValue" name="wfField.defaultValue" size="30" validate="{required:true}" value="<c:out value="${wfField.defaultValue}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>