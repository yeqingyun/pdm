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

<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfQues'},
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
	if ($("#quesId").length > 0)
		$("#quesId").ligerTextBox();
	if ($("#scheId").length > 0)
		$("#scheId").ligerTextBox();
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#cateId").length > 0)
		$("#cateId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#quesLevel").length > 0)
		$("#quesLevel").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#isRisk").length > 0)
		$("#isRisk").ligerTextBox();
	if ($("#quesText").length > 0)
		$("#quesText").ligerTextBox();
	if ($("#result").length > 0)
		$("#result").ligerTextBox();

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
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfQues.createDate" size="30" validate="{required:true}" value="<c:out value="${wfQues.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfQues.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfQues.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">问题ID：</td><td><input type="text" id="quesId" name="wfQues.quesId" size="30" validate="{required:true}" value="<c:out value="${wfQues.quesId}"/>"/></td>
			<td height="24" width="90" align="center">项目进度ID：</td><td><input type="text" id="scheId" name="wfQues.scheId" size="30" validate="{required:true}" value="<c:out value="${wfQues.scheId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作任务ID：</td><td><input type="text" id="taskId" name="wfQues.taskId" size="30" validate="{required:true}" value="<c:out value="${wfQues.taskId}"/>"/></td>
			<td height="24" width="90" align="center">分类ID：</td><td><input type="text" id="cateId" name="wfQues.cateId" size="30" validate="{required:true}" value="<c:out value="${wfQues.cateId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">公司ID：</td><td><input type="text" id="comId" name="wfQues.comId" size="30" validate="{required:true}" value="<c:out value="${wfQues.comId}"/>"/></td>
			<td height="24" width="90" align="center">部门ID：</td><td><input type="text" id="deptId" name="wfQues.deptId" size="30" validate="{required:true}" value="<c:out value="${wfQues.deptId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">办理人：</td><td><input type="text" id="userId" name="wfQues.userId" size="30" validate="{required:true}" value="<c:out value="${wfQues.userId}"/>"/></td>
			<td height="24" width="90" align="center">问题等级：</td><td><input type="text" id="quesLevel" name="wfQues.quesLevel" size="30" validate="{required:true}" value="<c:out value="${wfQues.quesLevel}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfQues.status" size="30" validate="{required:true}" value="<c:out value="${wfQues.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfQues.createBy" size="30" validate="{required:true}" value="<c:out value="${wfQues.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfQues.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfQues.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfQues.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfQues.wfNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否风险流程：</td><td><input type="text" id="isRisk" name="wfQues.isRisk" size="30" validate="{required:true}" value="<c:out value="${wfQues.isRisk}"/>"/></td>
			<td height="24" width="90" align="center">问题描述：</td><td><input type="text" id="quesText" name="wfQues.quesText" size="30" validate="{required:true}" value="<c:out value="${wfQues.quesText}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">问题结论：</td><td><input type="text" id="result" name="wfQues.result" size="30" validate="{required:true}" value="<c:out value="${wfQues.result}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>