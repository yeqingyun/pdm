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

<script src="./include/js/gnwf/wfQuesRelate.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfQuesRelate'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
	if ($("#quesId").length > 0)
		$("#quesId").ligerTextBox();
	if ($("#isRisk").length > 0)
		$("#isRisk").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">问题Id：</td><td><input type="text" id="quesId" name="wfQuesRelate.quesId" size="30" validate="{required:true}" value="<c:out value="${wfQuesRelate.quesId}"/>"/></td>
			<td height="24" width="90" align="center">是否风险流程：</td><td><input type="text" id="isRisk" name="wfQuesRelate.isRisk" size="30" validate="{required:true}" value="<c:out value="${wfQuesRelate.isRisk}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfQuesRelate.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfQuesRelate.wfNo}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>