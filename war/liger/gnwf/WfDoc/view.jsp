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

<script src="./include/js/gnwf/wfDoc.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
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
	if ($("#docId").length > 0)
		$("#docId").ligerTextBox();
	if ($("#taskId").length > 0)
		$("#taskId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#cateId").length > 0)
		$("#cateId").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#deptDocId").length > 0)
		$("#deptDocId").ligerTextBox();
	if ($("#docName").length > 0)
		$("#docName").ligerTextBox();
	if ($("#docVer").length > 0)
		$("#docVer").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#uriLink").length > 0)
		$("#uriLink").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfDoc.createDate" size="30" validate="{required:true}" value="<c:out value="${wfDoc.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfDoc.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfDoc.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">文档ID：</td><td><input type="text" id="docId" name="wfDoc.docId" size="30" validate="{required:true}" value="<c:out value="${wfDoc.docId}"/>"/></td>
			<td height="24" width="90" align="center">任务ID：</td><td><input type="text" id="taskId" name="wfDoc.taskId" size="30" validate="{required:true}" value="<c:out value="${wfDoc.taskId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfDoc.status" size="30" validate="{required:true}" value="<c:out value="${wfDoc.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfDoc.createBy" size="30" validate="{required:true}" value="<c:out value="${wfDoc.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="wfDoc.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfDoc.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="cateId" name="wfDoc.cateId" size="30" validate="{required:true}" value="<c:out value="${wfDoc.cateId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="flowId" name="wfDoc.flowId" size="30" validate="{required:true}" value="<c:out value="${wfDoc.flowId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="deptDocId" name="wfDoc.deptDocId" size="30" validate="{required:true}" value="<c:out value="${wfDoc.deptDocId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">文档名称：</td><td><input type="text" id="docName" name="wfDoc.docName" size="30" validate="{required:true}" value="<c:out value="${wfDoc.docName}"/>"/></td>
			<td height="24" width="90" align="center">版本号：</td><td><input type="text" id="docVer" name="wfDoc.docVer" size="30" validate="{required:true}" value="<c:out value="${wfDoc.docVer}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfDoc.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfDoc.wfNo}"/>"/></td>
			<td height="24" width="90" align="center">URI连接：</td><td><input type="text" id="uriLink" name="wfDoc.uriLink" size="30" validate="{required:true}" value="<c:out value="${wfDoc.uriLink}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>