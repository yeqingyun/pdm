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

<script src="./include/js/zrprjt/schCfg.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'SchCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#schId").length > 0)
		$("#schId").ligerTextBox();
	if ($("#parent").length > 0)
		$("#parent").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#typId").length > 0)
		$("#typId").ligerTextBox();
	if ($("#schNo").length > 0)
		$("#schNo").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#cfgTime").length > 0)
		$("#cfgTime").ligerTextBox();
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
	if ($("#schNm").length > 0)
		$("#schNm").ligerTextBox();
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
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="schId" name="schCfg.schId" size="30" value="<c:out value="${schCfg.schId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="parent" name="schCfg.parent" size="30" value="<c:out value="${schCfg.parent}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">流程ID：</td><td><input type="text" id="flowId" name="schCfg.flowId" size="30" value="<c:out value="${schCfg.flowId}"/>"/></td>
			<td height="24" width="90" align="center">分类ID：</td><td><input type="text" id="typId" name="schCfg.typId" size="30" value="<c:out value="${schCfg.typId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">序号：</td><td><input type="text" id="schNo" name="schCfg.schNo" size="30" value="<c:out value="${schCfg.schNo}"/>"/></td>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="schCfg.leve" size="30" value="<c:out value="${schCfg.leve}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工时：</td><td><input type="text" id="cfgTime" name="schCfg.cfgTime" size="30" value="<c:out value="${schCfg.cfgTime}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="schCfg.status" size="30" value="<c:out value="${schCfg.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="schCfg.createBy" size="30" value="<c:out value="${schCfg.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="schCfg.lastUpd" size="30" value="<c:out value="${schCfg.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="schCfg.createDate" size="30" value="<c:out value="${schCfg.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="schCfg.lastDate" size="30" value="<c:out value="${schCfg.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">任务名称：</td><td><input type="text" id="schNm" name="schCfg.schNm" size="30" value="<c:out value="${schCfg.schNm}"/>"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="schCfg.remark" size="30" value="<c:out value="${schCfg.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>