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

<script src="./include/js/gnwf/wfMatlEval.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#evalDate").length > 0)
		$("#evalDate").ligerDateEditor({showTime: false});
	if ($("#startEvalDate").length > 0)
		$("#startEvalDate").ligerDateEditor({showTime: false});
	if ($("#endEvalDate").length > 0)
		$("#endEvalDate").ligerDateEditor({showTime: false});
	if ($("#matlId").length > 0)
		$("#matlId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#isPass").length > 0)
		$("#isPass").ligerTextBox();
	if ($("#evaler").length > 0)
		$("#evaler").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#evalId").length > 0)
		$("#evalId").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#evalText").length > 0)
		$("#evalText").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">评审日期：</td><td><input type="text" id="evalDate" name="wfMatlEval.evalDate" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.evalDate}"/>"/></td>
			<td height="24" width="90" align="center">物料ID：</td><td><input type="text" id="matlId" name="wfMatlEval.matlId" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.matlId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="wfMatlEval.userId" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.userId}"/>"/></td>
			<td height="24" width="90" align="center">是否通过：</td><td><input type="text" id="isPass" name="wfMatlEval.isPass" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.isPass}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">1代表采购，2代表材料：</td><td><input type="text" id="evaler" name="wfMatlEval.evaler" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.evaler}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="status" name="wfMatlEval.status" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="evalId" name="wfMatlEval.evalId" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.evalId}"/>"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfMatlEval.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.wfNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">评审意见：</td><td><input type="text" id="evalText" name="wfMatlEval.evalText" size="30" validate="{required:true}" value="<c:out value="${wfMatlEval.evalText}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>