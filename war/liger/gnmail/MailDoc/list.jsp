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

<script src="./include/js/gnmail/mailDoc.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'MailDoc'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '附件ID', name: 'docId', align: 'left', width: 120 },
			{ display: '邮件ID', name: 'mailId', align: 'left', width: 120 },
			{ display: '文档名称', name: 'docName', align: 'left', width: 120 },
			{ display: 'URI连接', name: 'uriLink', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./MailDoc!list.shtml',
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
	if ($("#docId").length > 0)
		$("#docId").ligerTextBox({width:145});
	if ($("#mailId").length > 0)
		$("#mailId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#docName").length > 0)
		$("#docName").ligerTextBox({width:145});
	if ($("#uriLink").length > 0)
		$("#uriLink").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">附件ID：</td><td><input type="text" id="docId" name="mailDoc.docId"/></td>
			<td height="24" width="90" align="center">邮件ID：</td>
			<td>
				<select id="mailId" name="mailDoc.mailId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mails}" var="mail">
						<option value="<c:out value="${mail.mailId}"/>"><c:out value="${mail.mailId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">文档名称：</td><td><input type="text" id="docName" name="mailDoc.docName"/></td>
			<td height="24" width="90" align="center">URI连接：</td><td><input type="text" id="uriLink" name="mailDoc.uriLink"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>