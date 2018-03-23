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


<script src="./include/js/gnwf/wfField.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfField'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
	if ($("#flowId").length > 0)
		$("#flowId").ligerComboBox();
	if ($("#fieldType").length > 0)
		$("#fieldType").ligerComboBox();
	if ($("#fieldCode").length > 0)
		$("#fieldCode").ligerTextBox();
	if ($("#fieldName").length > 0)
		$("#fieldName").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">工作流ID：</td>
			<td>
				<select id="flowId" name="wfField.flowId">
					<option value="-1" selected>请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${wfField.flowId==cfg.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">字段类型：</td>
			<td>
				<select id="fieldType" name="wfField.fieldType">
					<option value="1" <c:if test="${wfField.fieldType==1}">selected</c:if>>textarea</option>
					<option value="2" <c:if test="${wfField.fieldType==2}">selected</c:if>>text_签名</option>
					<option value="3" <c:if test="${wfField.fieldType==3}">selected</c:if>>text_日期</option>
					<option value="4" <c:if test="${wfField.fieldType==4}">selected</c:if>>radio</option>
					<option value="5" <c:if test="${wfField.fieldType==5}">selected</c:if>>checkbox</option>
					<option value="6" <c:if test="${wfField.fieldType==6}">selected</c:if>>text_line</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">字段名：</td><td><input type="text" id="fieldCode" name="wfField.fieldCode" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldCode}"/>"/></td>
			<td height="24" width="90" align="center">中文名：</td><td><input type="text" id="fieldName" name="wfField.fieldName" size="30" validate="{required:true}" value="<c:out value="${wfField.fieldName}"/>"/></td>
		</tr>
		
		<tr>
			<td height="24" width="90" align="center">标题：</td>
			<td><input type="text" id="fieldTitle" name="wfField.fieldTitle"fieldTitle"" size="30"  value="<c:out value="${wfField.fieldTitle}"/>"/></td>
			
			<td height="24" width="90" align="center">是否需要在APP上填写：</td>
			<td>
				<select id="needFilledOnAPP" name="wfField.needFilledOnAPP">
					<option value="0" <c:if test="${wfField.needFilledOnAPP==0}">selected</c:if>>不需要</option>
					<option value="1" <c:if test="${wfField.needFilledOnAPP==1}">selected</c:if>>需要</option>
				</select>
			</td>
		</tr>
		
	</table>
</form>

</div>

</body>
</html>