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
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Task'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '任务编号', name: 'taskNo', align: 'left', width: 120 },
			{ display: '任务名称', name: 'taskNm', align: 'left', width: 120 },
			{ display: '项目编号', name: 'prjtNo', align: 'left', width: 120 },
			{ display: '进度配置', name: 'schId', align: 'left', width: 120 },
			{ display: '上级任务', name: 'parent', align: 'left', width: 120 },
			{ display: '任务办理', name: 'tasker', align: 'left', width: 120 },
			{ display: '任务分派', name: 'sender', align: 'left', width: 120 },
			{ display: '序号', name: 'schNo', align: 'left', width: 120 },
			{ display: '级别', name: 'leve', align: 'left', width: 120 },
			{ display: '等级', name: 'grade', align: 'left', width: 120 },
			{ display: '百分比', name: 'perce', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }},
			{ display: '计划开始', name: 'planStaDate', align: 'left', width: 120 },
			{ display: '计划结束', name: 'planOveDate', align: 'left', width: 120 },
			{ display: '实际开始', name: 'staDate', align: 'left', width: 120 },
			{ display: '实际结束', name: 'oveDate', align: 'left', width: 120 },
		    /**{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			**/
			{ display: '备注', name: 'remark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Task!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

$(function(){
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

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
	 <tr>
	   <td height="24" width="90" align="center">任务编号：</td><td><input type="text" id="taskNo" name="task.taskNo"/></td>
			<td height="24" width="90" align="center">项目编号：</td>
			<td>
				<select id="prjtNo" name="task.prjtNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtDefs}" var="prjtDef">
						<option value="<c:out value="${prjtDef.prjtNo}"/>"><c:out value="${prjtDef.prjtNm}"/></option>
					</c:forEach>
				</select>
			</td>
			
			<td height="24" width="90" align="center">任务名称：</td><td><input type="text" id="taskNm" name="task.taskNm"/></td>
			<td height="24" width="90" align="center">上级任务：</td><td><input type="text" id="parent" name="task.parent"/></td>
	   </tr>
		<tr>
			<td height="24" width="90" align="center">进度配置：</td>
			<td>
				<select id="schId" name="task.schId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${schCfgs}" var="schCfg">
						<option value="<c:out value="${schCfg.schId}"/>"><c:out value="${schCfg.schNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">任务办理：</td><td><input type="text" id="tasker" name="task.tasker"/></td>
			<td height="24" width="90" align="center">任务分派：</td><td><input type="text" id="sender" name="task.sender"/></td>
			<td height="24" width="90" align="center">序号：</td><td><input type="text" id="schNo" name="task.schNo"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="task.leve"/></td>
			<td height="24" width="90" align="center">等级：</td><td><input type="text" id="grade" name="task.grade"/></td>
			<td height="24" width="90" align="center">百分比：</td><td><input type="text" id="perce" name="task.perce"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="task.status"/></td>
		</tr><!--tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="task.createBy"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="task.lastUpd"/></td>
			<td height="24" width="90" align="center">计划开始始：</td><td><input type="text" id="startPlanStaDate" name="task.startPlanStaDate"/></td>
			<td height="24" width="90" align="center">计划开始止：</td><td><input type="text" id="endPlanStaDate" name="task.endPlanStaDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">计划结束始：</td><td><input type="text" id="startPlanOveDate" name="task.startPlanOveDate"/></td>
			<td height="24" width="90" align="center">计划结束止：</td><td><input type="text" id="endPlanOveDate" name="task.endPlanOveDate"/></td>
			<td height="24" width="90" align="center">实际开始始：</td><td><input type="text" id="startStaDate" name="task.startStaDate"/></td>
			<td height="24" width="90" align="center">实际开始止：</td><td><input type="text" id="endStaDate" name="task.endStaDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">实际结束始：</td><td><input type="text" id="startOveDate" name="task.startOveDate"/></td>
			<td height="24" width="90" align="center">实际结束止：</td><td><input type="text" id="endOveDate" name="task.endOveDate"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="task.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="task.endCreateDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastDate" name="task.startLastDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastDate" name="task.endLastDate"/></td>
			
		</tr><tr>
			
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="task.remark"/></td>
		</tr-->

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
