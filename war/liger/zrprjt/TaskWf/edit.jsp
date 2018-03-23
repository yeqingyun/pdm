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

<script src="./include/js/zrprjt/taskWf.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'TaskWf'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#schId").length > 0)
		$("#schId").ligerTextBox();
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
	if ($("#taskNo").length > 0)
		$("#taskNo").ligerComboBox();
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">任务配置ID：</td><td><input type="text" id="schId" name="taskWf.schId" size="30" value="<c:out value="${taskWf.schId}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="taskWf.status" size="30" value="<c:out value="${taskWf.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="taskWf.createBy" size="30" value="<c:out value="${taskWf.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="taskWf.lastUpd" size="30" value="<c:out value="${taskWf.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="taskWf.createDate" size="30" value="<c:out value="${taskWf.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="taskWf.lastDate" size="30" value="<c:out value="${taskWf.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">项目编号：</td>
			<td>
				<select id="taskNo" name="taskWf.taskNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${tasks}" var="task">
						<option value="<c:out value="${task.taskNo}"/>"><c:out value="${task.taskNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="prjtNo" name="taskWf.prjtNo" size="30" value="<c:out value="${taskWf.prjtNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">流程编号：</td><td><input type="text" id="wfNo" name="taskWf.wfNo" size="30" value="<c:out value="${taskWf.wfNo}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>