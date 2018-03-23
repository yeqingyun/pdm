<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#toptoolbar").ligerToolBar({ items: [
            { text: '保存', click: rsave },
   		]
   	});
	if ($("#planEDate").length > 0)
		$("#planEDate").ligerDateEditor({showTime: false});
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox();
	if ($("#wfDesc").length > 0)
		$("#wfDesc").ligerTextBox();
       check();
});
</script>
</head>
<body>

<div id="toptoolbar"></div>

<div id="eform"  style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form id="wfRd-form" name="wfRd-form">
	<input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>"/>
	<input type="hidden" id="quesIds" name="wfRd.quesIds" value="<c:out value="${wfRd.quesIds}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">工作流名称：</td>
			<td><input type="text" id="wfName" name="wfRd.wfName" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfName}"/>"/></td>
			<td height="24" width="90" align="center">工作流描述：</td>
			<td><input type="text" id="wfDesc" name="wfRd.wfDesc" size="30" value="<c:out value="${wfRd.wfDesc}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">计划完成时间：</td>
			<td><input type="text" id="planEDate" name="wfRd.planEDate" size="30"  value="<c:out value="${wfRd.planEDate}"/>"/></td>
		</tr>
	</table>
</form>

</div>

</body>
</html>