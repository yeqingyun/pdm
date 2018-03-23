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

<script src="./include/js/gnwf/wfStep.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfStep'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
	if ($("#flowId").length > 0)
		$("#flowId").ligerComboBox();
	if ($("#sort").length > 0)
		$("#sort").ligerTextBox();
	if ($("#stepName").length > 0)
		$("#stepName").ligerTextBox();
	if ($("#stepType").length > 0)
		$("#stepType").ligerComboBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">所属工作流：</td>
			<td>
				<select id="flowId" name="wfStep.flowId">
					<option value="-1" selected>请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${wfStep.flowId==cfg.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">排序：</td>
			<td>
				<input type="text" id="sort" name="wfStep.sort" size="30" validate="{required:true}" value="<c:out value="${wfStep.sort}"/>"/>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">步骤名称：</td>
			<td>
				<input type="text" id="stepName" name="wfStep.stepName" size="30" validate="{required:true}" value="<c:out value="${wfStep.stepName}"/>"/>
			</td>
			<td height="24" width="90" align="center">步骤描述：</td>
			<td>
				<select id="stepType" name="wfStep.stepType">
					<option value="1" <c:if test="${wfStep.stepType==1}">selected</c:if>>普通</option>
					<option value="2" <c:if test="${wfStep.stepType==2}">selected</c:if>>分支</option>
					<option value="3" <c:if test="${wfStep.stepType==3}">selected</c:if>>并发</option>
				</select>
			</td>
		</tr>
	</table>
</form>

</div>

</body>
</html>