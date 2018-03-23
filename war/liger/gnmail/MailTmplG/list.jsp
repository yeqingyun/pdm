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

<script src="./include/js/gnmail/mailTmplG.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'MailTmplG'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '模板ID', name: 'tmplId', align: 'left', width: 120 },
			{ display: '邮件组ID', name: 'groupId', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./MailTmplG!list.shtml',
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
		$("#tmplId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#groupId").length > 0)
		$("#groupId").ligerComboBox({selectBoxWidth:200,width:145});

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
			<td height="24" width="90" align="center">模板ID：</td>
			<td>
				<select id="tmplId" name="mailTmplG.tmplId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailTmpls}" var="mailTmpl">
						<option value="<c:out value="${mailTmpl.tmplId}"/>"><c:out value="${mailTmpl.tmplId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">邮件组ID：</td>
			<td>
				<select id="groupId" name="mailTmplG.groupId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailGroups}" var="mailGroup">
						<option value="<c:out value="${mailGroup.groupId}"/>"><c:out value="${mailGroup.groupId}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>