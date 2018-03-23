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

<script src="./include/js/gnmail/mailBook.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'MailBook'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '地址薄ID', name: 'bookId', align: 'left', width: 120 },
			{ display: '用户ID', name: 'userId', align: 'left', width: 120 },
			{ display: '分组ID', name: 'groupId', align: 'left', width: 120 },
			{ display: '电话1', name: 'extPhone', align: 'left', width: 120 },
			{ display: '电话1', name: 'phone', align: 'left', width: 120 },
			{ display: '手机', name: 'mobile', align: 'left', width: 120 },
			{ display: '邮件件地址', name: 'mailAddr', align: 'left', width: 120 },
			{ display: '名称', name: 'addrName', align: 'left', width: 120 },
			{ display: '备注', name: 'remark', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastUpdDate', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./MailBook!list.shtml',
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
	if ($("#bookId").length > 0)
		$("#bookId").ligerTextBox({width:145});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox({width:145});
	if ($("#groupId").length > 0)
		$("#groupId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#extPhone").length > 0)
		$("#extPhone").ligerTextBox({width:145});
	if ($("#phone").length > 0)
		$("#phone").ligerTextBox({width:145});
	if ($("#mobile").length > 0)
		$("#mobile").ligerTextBox({width:145});
	if ($("#mailAddr").length > 0)
		$("#mailAddr").ligerTextBox({width:145});
	if ($("#addrName").length > 0)
		$("#addrName").ligerTextBox({width:145});
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox({width:145});
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
			<td height="24" width="90" align="center">地址薄ID：</td><td><input type="text" id="bookId" name="mailBook.bookId"/></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="mailBook.userId"/></td>
			<td height="24" width="90" align="center">分组ID：</td>
			<td>
				<select id="groupId" name="mailBook.groupId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailGroups}" var="mailGroup">
						<option value="<c:out value="${mailGroup.groupId}"/>"><c:out value="${mailGroup.groupId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">电话1：</td><td><input type="text" id="extPhone" name="mailBook.extPhone"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">电话1：</td><td><input type="text" id="phone" name="mailBook.phone"/></td>
			<td height="24" width="90" align="center">手机：</td><td><input type="text" id="mobile" name="mailBook.mobile"/></td>
			<td height="24" width="90" align="center">邮件件地址：</td><td><input type="text" id="mailAddr" name="mailBook.mailAddr"/></td>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="addrName" name="mailBook.addrName"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mailBook.remark"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailBook.status"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mailBook.createBy"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="mailBook.startCreateDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="mailBook.endCreateDate"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mailBook.lastUpd"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastUpdDate" name="mailBook.startLastUpdDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastUpdDate" name="mailBook.endLastUpdDate"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>