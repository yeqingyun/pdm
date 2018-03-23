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

<script src="./include/js/zrprjt/task.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'Task'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#schId").length > 0)
		$("#schId").ligerComboBox();
	if ($("#tasker").length > 0)
		$("#tasker").ligerTextBox();
	if ($("#sender").length > 0)
		$("#sender").ligerTextBox();
	if ($("#schNo").length > 0)
		$("#schNo").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#grade").length > 0)
		$("#grade").ligerTextBox();
	if ($("#perce").length > 0)
		$("#perce").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#planStaDate").length > 0)
		$("#planStaDate").ligerDateEditor({showTime: false});
	if ($("#startPlanStaDate").length > 0)
		$("#startPlanStaDate").ligerDateEditor({showTime: false});
	if ($("#endPlanStaDate").length > 0)
		$("#endPlanStaDate").ligerDateEditor({showTime: false});
	if ($("#planOveDate").length > 0)
		$("#planOveDate").ligerDateEditor({showTime: false});
	if ($("#startPlanOveDate").length > 0)
		$("#startPlanOveDate").ligerDateEditor({showTime: false});
	if ($("#endPlanOveDate").length > 0)
		$("#endPlanOveDate").ligerDateEditor({showTime: false});
	if ($("#staDate").length > 0)
		$("#staDate").ligerDateEditor({showTime: false});
	if ($("#startStaDate").length > 0)
		$("#startStaDate").ligerDateEditor({showTime: false});
	if ($("#endStaDate").length > 0)
		$("#endStaDate").ligerDateEditor({showTime: false});
	if ($("#oveDate").length > 0)
		$("#oveDate").ligerDateEditor({showTime: false});
	if ($("#startOveDate").length > 0)
		$("#startOveDate").ligerDateEditor({showTime: false});
	if ($("#endOveDate").length > 0)
		$("#endOveDate").ligerDateEditor({showTime: false});
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
		$("#taskNo").ligerTextBox();
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerComboBox();
	if ($("#parent").length > 0)
		$("#parent").ligerTextBox();
	if ($("#taskNm").length > 0)
		$("#taskNm").ligerTextBox();
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
			<td height="24" width="90" align="center">配置ID：</td>
			<td>
				<select id="schId" name="task.schId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${schCfgs}" var="schCfg">
						<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">任务办理：</td><td><input type="text" id="tasker" name="task.tasker" size="30" value="<c:out value="${task.tasker}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">任务分派：</td><td><input type="text" id="sender" name="task.sender" size="30" value="<c:out value="${task.sender}"/>"/></td>
			<td height="24" width="90" align="center">序号：</td><td><input type="text" id="schNo" name="task.schNo" size="30" value="<c:out value="${task.schNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="task.leve" size="30" value="<c:out value="${task.leve}"/>"/></td>
			<td height="24" width="90" align="center">等级：</td><td><input type="text" id="grade" name="task.grade" size="30" value="<c:out value="${task.grade}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">百分比：</td><td><input type="text" id="perce" name="task.perce" size="30" value="<c:out value="${task.perce}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="task.status" size="30" value="<c:out value="${task.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="task.createBy" size="30" value="<c:out value="${task.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="task.lastUpd" size="30" value="<c:out value="${task.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">计划开始：</td><td><input type="text" id="planStaDate" name="task.planStaDate" size="30" value="<c:out value="${task.planStaDate}"/>"/></td>
			<td height="24" width="90" align="center">计划结束：</td><td><input type="text" id="planOveDate" name="task.planOveDate" size="30" value="<c:out value="${task.planOveDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">实际开始：</td><td><input type="text" id="staDate" name="task.staDate" size="30" value="<c:out value="${task.staDate}"/>"/></td>
			<td height="24" width="90" align="center">实际结束：</td><td><input type="text" id="oveDate" name="task.oveDate" size="30" value="<c:out value="${task.oveDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="task.createDate" size="30" value="<c:out value="${task.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="task.lastDate" size="30" value="<c:out value="${task.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="taskNo" name="task.taskNo" size="30" value="<c:out value="${task.taskNo}"/>"/></td>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="prjtNo" name="task.prjtNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtDefs}" var="prjtDef">
						<option value="<c:out value="${prjtDef.prjtNo}"/>"><c:out value="${prjtDef.prjtNm}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="parent" name="task.parent" size="30" value="<c:out value="${task.parent}"/>"/></td>
			<td height="24" width="90" align="center">任务名称：</td><td><input type="text" id="taskNm" name="task.taskNm" size="30" value="<c:out value="${task.taskNm}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="task.remark" size="30" value="<c:out value="${task.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>