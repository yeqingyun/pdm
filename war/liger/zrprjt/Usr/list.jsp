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

<script src="./include/js/usr.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Usr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'Id', name: 'id', align: 'left', width: 120 },
			{ display: '公司', name: 'comId', align: 'left', width: 120 },
			{ display: '部门', name: 'deptId', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			{ display: '登录账号', name: 'login', align: 'left', width: 120 },
			{ display: '密码', name: 'pwd', align: 'left', width: 120 },
			{ display: '用户号', name: 'usrNo', align: 'left', width: 120 },
			{ display: '用户名称', name: 'usrName', align: 'left', width: 120 },
			{ display: '电子邮箱', name: 'email', align: 'left', width: 120 },
			{ display: '备注', name: 'remark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Usr!list.shtml',
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
	if ($("#id").length > 0)
		$("#id").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerTextBox();
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
	if ($("#login").length > 0)
		$("#login").ligerTextBox();
	if ($("#pwd").length > 0)
		$("#pwd").ligerTextBox();
	if ($("#usrNo").length > 0)
		$("#usrNo").ligerTextBox();
	if ($("#usrName").length > 0)
		$("#usrName").ligerTextBox();
	if ($("#email").length > 0)
		$("#email").ligerTextBox();
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
			<td height="24" width="90" align="center">Id：</td><td><input type="text" id="id" name="usr.id"/></td>
			<td height="24" width="90" align="center">公司：</td><td><input type="text" id="comId" name="usr.comId"/></td>
			<td height="24" width="90" align="center">部门：</td><td><input type="text" id="deptId" name="usr.deptId"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="usr.status"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="usr.createBy"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="usr.lastUpd"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="usr.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="usr.endCreateDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastDate" name="usr.startLastDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastDate" name="usr.endLastDate"/></td>
			<td height="24" width="90" align="center">登录账号：</td><td><input type="text" id="login" name="usr.login"/></td>
			<td height="24" width="90" align="center">密码：</td><td><input type="text" id="pwd" name="usr.pwd"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户号：</td><td><input type="text" id="usrNo" name="usr.usrNo"/></td>
			<td height="24" width="90" align="center">用户名称：</td><td><input type="text" id="usrName" name="usr.usrName"/></td>
			<td height="24" width="90" align="center">电子邮箱：</td><td><input type="text" id="email" name="usr.email"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="usr.remark"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
