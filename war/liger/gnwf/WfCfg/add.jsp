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

<script src="./include/js/gnwf/wfCfg.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
	if ($("#cateId").length > 0)
		$("#cateId").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#flowCode").length > 0)
		$("#flowCode").ligerTextBox();
	if ($("#flowName").length > 0)
		$("#flowName").ligerTextBox();
	if ($("#needQues").length > 0)
		$("#needQues").ligerComboBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">工作流编码：</td><td><input type="text" id="flowCode" name="wfCfg.flowCode" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowCode}"/>"/></td>
			<td height="24" width="90" align="center">工作流名称：</td><td><input type="text" id="flowName" name="wfCfg.flowName" size="30" validate="{required:true}" value="<c:out value="${wfCfg.flowName}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">流程分类：</td>
			<td>
				<select id="cateId" name="wfCfg.cateId">
					<c:forEach items="${cateList}" var="cate">
						<option value="<c:out value="${cate.cateId}"/>" <c:if test="${wfCfg.cateId==cate.cateId}">selected</c:if>><c:out value="${cate.cateName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<select id="status" name="wfCfg.status" style="width: 200px;">
					<option value="1" <c:if test="${wfCfg.status==1}">selected</c:if>>有效</option>
					<option value="0" <c:if test="${wfCfg.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">是否需要提问题：</td>
			<td>
				<select id="needQues" name="wfCfg.needQues" style="width: 200px;">
					<option value="1" <c:if test="${wfCfg.needQues==1}">selected</c:if>>需要</option>
					<option value="0" <c:if test="${wfCfg.needQues==0}">selected</c:if>>不需要</option>
				</select>
			</td>
		</tr>
	</table>
</form>

</div>

</body>
</html>