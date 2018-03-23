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

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/gnmail/mailTmpl.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'MailTmpl'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
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

      check();
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">模板代码：</td><td><input type="text" id="tmplId" name="mailTmpl.tmplId" validate="{required:true}" value="<c:out value="${mailTmpl.tmplId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="cfgId" name="mailTmpl.cfgId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailCfgs}" var="mailCfg">
						<option value="<c:out value="${mailCfg.cfgId}"/>"><c:out value="${mailCfg.cfgId}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">所有人：</td><td><input type="text" id="userId" name="mailTmpl.userId" validate="{required:true}" value="<c:out value="${mailTmpl.userId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">模板名称：</td><td><input type="text" id="tmplName" name="mailTmpl.tmplName" validate="{required:true}" value="<c:out value="${mailTmpl.tmplName}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">内容：</td><td><input type="text" id="tmplText" name="mailTmpl.tmplText" validate="{required:true}" value="<c:out value="${mailTmpl.tmplText}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">分类：</td><td><input type="text" id="type" name="mailTmpl.type" validate="{required:true}" value="<c:out value="${mailTmpl.type}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">是否自动发送：</td><td><input type="text" id="isAuto" name="mailTmpl.isAuto" validate="{required:true}" value="<c:out value="${mailTmpl.isAuto}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">开始日期：</td><td><input type="text" id="autoStart" name="mailTmpl.autoStart" validate="{required:true}" value="<c:out value="${mailTmpl.autoStart}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">周期：</td><td><input type="text" id="autoCycle" name="mailTmpl.autoCycle" validate="{required:true}" value="<c:out value="${mailTmpl.autoCycle}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailTmpl.status" validate="{required:true}" value="<c:out value="${mailTmpl.status}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mailTmpl.createBy" validate="{required:true}" value="<c:out value="${mailTmpl.createBy}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="mailTmpl.createDate" validate="{required:true}" value="<c:out value="${mailTmpl.createDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mailTmpl.lastUpd" validate="{required:true}" value="<c:out value="${mailTmpl.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="mailTmpl.lastUpdDate" validate="{required:true}" value="<c:out value="${mailTmpl.lastUpdDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>