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

<script src="./include/js/gnwf/wfCfg.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfCfg'},
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
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerTextBox();
	if ($("#cateId").length > 0)
		$("#cateId").ligerTextBox();
	if ($("#isFirstStep").length > 0)
		$("#isFirstStep").ligerTextBox();
	if ($("#sphere").length > 0)
		$("#sphere").ligerTextBox();
	if ($("#relateId").length > 0)
		$("#relateId").ligerTextBox();
	if ($("#limit").length > 0)
		$("#limit").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#hasQuesMoudle").length > 0)
		$("#hasQuesMoudle").ligerTextBox();
	if ($("#scheCfgId").length > 0)
		$("#scheCfgId").ligerTextBox();
	if ($("#flowCode").length > 0)
		$("#flowCode").ligerTextBox();
	if ($("#flowName").length > 0)
		$("#flowName").ligerTextBox();
	if ($("#flowUri").length > 0)
		$("#flowUri").ligerTextBox();
	if ($("#printUri").length > 0)
		$("#printUri").ligerTextBox();
	if ($("#specialClass").length > 0)
		$("#specialClass").ligerTextBox();
	if ($("#addRdExtendUri").length > 0)
		$("#addRdExtendUri").ligerTextBox();
	if ($("#flowDesc").length > 0)
		$("#flowDesc").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfCfg.createDate" size="30" validate="{required:true}" value="<c:out value="${wfCfg.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfCfg.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfCfg.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流配置ID：</td><td><input type="text" id="flowId" name="wfCfg.flowId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowId}"/>"/></td>
			<td height="24" width="90" align="center">公司ID：</td><td><input type="text" id="comId" name="wfCfg.comId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.comId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">部门ID：</td><td><input type="text" id="deptId" name="wfCfg.deptId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.deptId}"/>"/></td>
			<td height="24" width="90" align="center">流程分类：</td><td><input type="text" id="cateId" name="wfCfg.cateId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.cateId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否创建第一步骤：</td><td><input type="text" id="isFirstStep" name="wfCfg.isFirstStep" size="30" validate="{required:true}" value="<c:out value="${wfCfg.isFirstStep}"/>"/></td>
			<td height="24" width="90" align="center">适用范围（0:所有看，1：本人）：</td><td><input type="text" id="sphere" name="wfCfg.sphere" size="30" validate="{required:true}" value="<c:out value="${wfCfg.sphere}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="relateId" name="wfCfg.relateId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.relateId}"/>"/></td>
			<td height="24" width="90" align="center">权限：</td><td><input type="text" id="limit" name="wfCfg.limit" size="30" validate="{required:true}" value="<c:out value="${wfCfg.limit}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfCfg.status" size="30" validate="{required:true}" value="<c:out value="${wfCfg.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfCfg.createBy" size="30" validate="{required:true}" value="<c:out value="${wfCfg.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfCfg.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfCfg.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="hasQuesMoudle" name="wfCfg.hasQuesMoudle" size="30" validate="{required:true}" value="<c:out value="${wfCfg.hasQuesMoudle}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="scheCfgId" name="wfCfg.scheCfgId" size="30" validate="{required:true}" value="<c:out value="${wfCfg.scheCfgId}"/>"/></td>
			<td height="24" width="90" align="center">工作流编码：</td><td><input type="text" id="flowCode" name="wfCfg.flowCode" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowCode}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作程名称：</td><td><input type="text" id="flowName" name="wfCfg.flowName" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowName}"/>"/></td>
			<td height="24" width="90" align="center">工作流URI配置：</td><td><input type="text" id="flowUri" name="wfCfg.flowUri" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowUri}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">打印URI配置：</td><td><input type="text" id="printUri" name="wfCfg.printUri" size="30" validate="{required:true}" value="<c:out value="${wfCfg.printUri}"/>"/></td>
			<td height="24" width="90" align="center">特殊流程：</td><td><input type="text" id="specialClass" name="wfCfg.specialClass" size="30" validate="{required:true}" value="<c:out value="${wfCfg.specialClass}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="addRdExtendUri" name="wfCfg.addRdExtendUri" size="30" validate="{required:true}" value="<c:out value="${wfCfg.addRdExtendUri}"/>"/></td>
			<td height="24" width="90" align="center">工作流描述：</td><td><input type="text" id="flowDesc" name="wfCfg.flowDesc" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowDesc}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>