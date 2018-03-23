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

<script src="./include/js/zrprjt/taskMsg.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'TaskMsg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			//{ display: '消息ID', name: 'msgId', align: 'left', width: 120 },
			{ display: '消息标题', name: 'title', align: 'left', width: 120 },
			{ display: '项目编号', name: 'prjtNo', align: 'left', width: 120 },
			{ display: '任务编号', name: 'taskNo', align: 'left', width: 120 },
			{ display: '流程编号', name: 'wfNo', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }},
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			{ display: '消息回复', name: 'reply', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./TaskMsg!list.shtml',
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
	if ($("#msgId").length > 0)
		$("#msgId").ligerTextBox();
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
	if ($("#reply").length > 0)
		$("#reply").ligerTextBox();
	if ($("#taskNo").length > 0)
		$("#taskNo").ligerComboBox();
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#title").length > 0)
		$("#title").ligerTextBox();

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
			<td height="24" width="90" align="center">消息标题：</td><td><input type="text" id="title" name="taskMsg.title"/></td>
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="prjtNo" name="taskMsg.prjtNo"/></td>
			<td height="24" width="90" align="center">流程编号：</td><td><input type="text" id="wfNo" name="taskMsg.wfNo"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">任务编号：</td>
			<td>
				<select id="taskNo" name="taskMsg.taskNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${tasks}" var="task">
						<option value="<c:out value="${task.taskNo}"/>"><c:out value="${task.taskNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="taskMsg.status"/></td>
		</tr>
		<!--tr>
			<td height="24" width="90" align="center">消息ID：</td><td><input type="text" id="msgId" name="taskMsg.msgId"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="taskMsg.createBy"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="taskMsg.lastUpd"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="taskMsg.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="taskMsg.endCreateDate"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastDate" name="taskMsg.startLastDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastDate" name="taskMsg.endLastDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">消息回复：</td><td><input type="text" id="reply" name="taskMsg.reply"/></td>
		</tr-->

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
