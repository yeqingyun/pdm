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

<script src="./include/js/gnwf/wfMatl.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfMatl'},
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
	if ($("#matlId").length > 0)
		$("#matlId").ligerTextBox();
	if ($("#matlType").length > 0)
		$("#matlType").ligerTextBox();
	if ($("#isSubs").length > 0)
		$("#isSubs").ligerTextBox();
	if ($("#risk").length > 0)
		$("#risk").ligerTextBox();
	if ($("#lotSize").length > 0)
		$("#lotSize").ligerTextBox();
	if ($("#isPanel").length > 0)
		$("#isPanel").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#matlName").length > 0)
		$("#matlName").ligerTextBox();
	if ($("#matlDesc").length > 0)
		$("#matlDesc").ligerTextBox();
	if ($("#supplier").length > 0)
		$("#supplier").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	if ($("#supNo").length > 0)
		$("#supNo").ligerTextBox();
	if ($("#matlEvalDesc").length > 0)
		$("#matlEvalDesc").ligerTextBox();
	if ($("#matlNo").length > 0)
		$("#matlNo").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#matlLevel").length > 0)
		$("#matlLevel").ligerTextBox();
	if ($("#historyLevel").length > 0)
		$("#historyLevel").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfMatl.createDate" size="30" validate="{required:true}" value="<c:out value="${wfMatl.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfMatl.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfMatl.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">物料记录ID：</td><td><input type="text" id="matlId" name="wfMatl.matlId" size="30" validate="{required:true}" value="<c:out value="${wfMatl.matlId}"/>"/></td>
			<td height="24" width="90" align="center">物料类型：</td><td><input type="text" id="matlType" name="wfMatl.matlType" size="30" validate="{required:true}" value="<c:out value="${wfMatl.matlType}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否可替代：</td><td><input type="text" id="isSubs" name="wfMatl.isSubs" size="30" validate="{required:true}" value="<c:out value="${wfMatl.isSubs}"/>"/></td>
			<td height="24" width="90" align="center">风险等级：</td><td><input type="text" id="risk" name="wfMatl.risk" size="30" validate="{required:true}" value="<c:out value="${wfMatl.risk}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">批量：</td><td><input type="text" id="lotSize" name="wfMatl.lotSize" size="30" validate="{required:true}" value="<c:out value="${wfMatl.lotSize}"/>"/></td>
			<td height="24" width="90" align="center">是否联板：</td><td><input type="text" id="isPanel" name="wfMatl.isPanel" size="30" validate="{required:true}" value="<c:out value="${wfMatl.isPanel}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfMatl.status" size="30" validate="{required:true}" value="<c:out value="${wfMatl.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfMatl.createBy" size="30" validate="{required:true}" value="<c:out value="${wfMatl.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfMatl.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfMatl.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">物料名称：</td><td><input type="text" id="matlName" name="wfMatl.matlName" size="30" validate="{required:true}" value="<c:out value="${wfMatl.matlName}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">物料描述：</td><td><input type="text" id="matlDesc" name="wfMatl.matlDesc" size="30" validate="{required:true}" value="<c:out value="${wfMatl.matlDesc}"/>"/></td>
			<td height="24" width="90" align="center">供应商：</td><td><input type="text" id="supplier" name="wfMatl.supplier" size="30" validate="{required:true}" value="<c:out value="${wfMatl.supplier}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="wfMatl.remark" size="30" validate="{required:true}" value="<c:out value="${wfMatl.remark}"/>"/></td>
			<td height="24" width="90" align="center">物料型号(外部编码)：</td><td><input type="text" id="supNo" name="wfMatl.supNo" size="30" validate="{required:true}" value="<c:out value="${wfMatl.supNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">物料选型原因：</td><td><input type="text" id="matlEvalDesc" name="wfMatl.matlEvalDesc" size="30" validate="{required:true}" value="<c:out value="${wfMatl.matlEvalDesc}"/>"/></td>
			<td height="24" width="90" align="center">物料编码：</td><td><input type="text" id="matlNo" name="wfMatl.matlNo" size="30" validate="{required:true}" value="<c:out value="${wfMatl.matlNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfMatl.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfMatl.wfNo}"/>"/></td>
			<td height="24" width="90" align="center">物料等级：</td><td><input type="text" id="matlLevel" name="wfMatl.matlLevel" size="30" validate="{required:true}" value="<c:out value="${wfMatl.matlLevel}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="historyLevel" name="wfMatl.historyLevel" size="30" validate="{required:true}" value="<c:out value="${wfMatl.historyLevel}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>