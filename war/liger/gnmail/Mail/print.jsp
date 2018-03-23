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

<script src="./include/js/gnmail/mail.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'Mail'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#mailId").length > 0)
		$("#mailId").ligerTextBox({width:145});
	if ($("#cfgId").length > 0)
		$("#cfgId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#oexId").length > 0)
		$("#oexId").ligerTextBox({width:145});
	if ($("#senderId").length > 0)
		$("#senderId").ligerTextBox({width:145});
	if ($("#sender").length > 0)
		$("#sender").ligerTextBox({width:145});
	if ($("#acceptId").length > 0)
		$("#acceptId").ligerTextBox({width:145});
	if ($("#accept").length > 0)
		$("#accept").ligerTextBox({width:145});
	if ($("#title").length > 0)
		$("#title").ligerTextBox({width:145});
	if ($("#mailText").length > 0)
		$("#mailText").ligerTextBox({width:145});
	if ($("#mailDate").length > 0)
		$("#mailDate").ligerDateEditor({showTime: false});
	if ($("#startMailDate").length > 0)
		$("#startMailDate").ligerDateEditor({showTime: false});
	if ($("#endMailDate").length > 0)
		$("#endMailDate").ligerDateEditor({showTime: false});
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
<body>

<div id="layout1">
	<div position="center">

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">邮件ID：</td><td><input type="text" id="mailId" name="mail.mailId" validate="{required:true}" value="<c:out value="${mail.mailId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">邮件服务ID：</td>
			<td>
				<select id="cfgId" name="mail.cfgId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailCfgs}" var="mailCfg">
						<option value="<c:out value="${mailCfg.cfgId}"/>"><c:out value="${mailCfg.cfgId}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">业务ID：</td><td><input type="text" id="oexId" name="mail.oexId" validate="{required:true}" value="<c:out value="${mail.oexId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">发件人ID：</td><td><input type="text" id="senderId" name="mail.senderId" validate="{required:true}" value="<c:out value="${mail.senderId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">发件人：</td><td><input type="text" id="sender" name="mail.sender" validate="{required:true}" value="<c:out value="${mail.sender}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">收件人ID：</td><td><input type="text" id="acceptId" name="mail.acceptId" validate="{required:true}" value="<c:out value="${mail.acceptId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">收件人：</td><td><input type="text" id="accept" name="mail.accept" validate="{required:true}" value="<c:out value="${mail.accept}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">主题：</td><td><input type="text" id="title" name="mail.title" validate="{required:true}" value="<c:out value="${mail.title}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">邮件内容：</td><td><input type="text" id="mailText" name="mail.mailText" validate="{required:true}" value="<c:out value="${mail.mailText}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">邮件时间：</td><td><input type="text" id="mailDate" name="mail.mailDate" validate="{required:true}" value="<c:out value="${mail.mailDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mail.remark" validate="{required:true}" value="<c:out value="${mail.remark}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mail.status" validate="{required:true}" value="<c:out value="${mail.status}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mail.createBy" validate="{required:true}" value="<c:out value="${mail.createBy}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="mail.createDate" validate="{required:true}" value="<c:out value="${mail.createDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mail.lastUpd" validate="{required:true}" value="<c:out value="${mail.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="mail.lastUpdDate" validate="{required:true}" value="<c:out value="${mail.lastUpdDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>