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

<script src="./include/js/gnmail/mailTmpl.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'MailTmpl'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '模板代码', name: 'tmplId', align: 'left', width: 120 },
			{ display: 'null', name: 'cfgId', align: 'left', width: 120 },
			{ display: '所有人', name: 'userId', align: 'left', width: 120 },
			{ display: '模板名称', name: 'tmplName', align: 'left', width: 120 },
			{ display: '内容', name: 'tmplText', align: 'left', width: 120 },
			{ display: '分类', name: 'type', align: 'left', width: 120 },
			{ display: '是否自动发送', name: 'isAuto', align: 'left', width: 120 },
			{ display: '开始日期', name: 'autoStart', align: 'left', width: 120 },
			{ display: '周期', name: 'autoCycle', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastUpdDate', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./MailTmpl!list.shtml',
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
	if ($("#tmplId").length > 0)
		$("#tmplId").ligerTextBox({width:145});
	if ($("#cfgId").length > 0)
		$("#cfgId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox({width:145});
	if ($("#tmplName").length > 0)
		$("#tmplName").ligerTextBox({width:145});
	if ($("#tmplText").length > 0)
		$("#tmplText").ligerTextBox({width:145});
	if ($("#type").length > 0)
		$("#type").ligerTextBox({width:145});
	if ($("#isAuto").length > 0)
		$("#isAuto").ligerTextBox({width:145});
	if ($("#autoStart").length > 0)
		$("#autoStart").ligerDateEditor({showTime: false});
	if ($("#startAutoStart").length > 0)
		$("#startAutoStart").ligerDateEditor({showTime: false});
	if ($("#endAutoStart").length > 0)
		$("#endAutoStart").ligerDateEditor({showTime: false});
	if ($("#autoCycle").length > 0)
		$("#autoCycle").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox({width:145});
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});

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
			<td height="24" width="90" align="center">模板代码：</td><td><input type="text" id="tmplId" name="mailTmpl.tmplId"/></td>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="cfgId" name="mailTmpl.cfgId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailCfgs}" var="mailCfg">
						<option value="<c:out value="${mailCfg.cfgId}"/>"><c:out value="${mailCfg.cfgId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">所有人：</td><td><input type="text" id="userId" name="mailTmpl.userId"/></td>
			<td height="24" width="90" align="center">模板名称：</td><td><input type="text" id="tmplName" name="mailTmpl.tmplName"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">内容：</td><td><input type="text" id="tmplText" name="mailTmpl.tmplText"/></td>
			<td height="24" width="90" align="center">分类：</td><td><input type="text" id="type" name="mailTmpl.type"/></td>
			<td height="24" width="90" align="center">是否自动发送：</td><td><input type="text" id="isAuto" name="mailTmpl.isAuto"/></td>
			<td height="24" width="90" align="center">开始日期始：</td><td><input type="text" id="startAutoStart" name="mailTmpl.startAutoStart"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">开始日期止：</td><td><input type="text" id="endAutoStart" name="mailTmpl.endAutoStart"/></td>
			<td height="24" width="90" align="center">周期：</td><td><input type="text" id="autoCycle" name="mailTmpl.autoCycle"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailTmpl.status"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mailTmpl.createBy"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="mailTmpl.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="mailTmpl.endCreateDate"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mailTmpl.lastUpd"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastUpdDate" name="mailTmpl.startLastUpdDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastUpdDate" name="mailTmpl.endLastUpdDate"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>