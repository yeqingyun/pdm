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

<script src="./include/js/gnwf/wfJob.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	if ($("#jobId").length > 0)
		$("#jobId").ligerTextBox();
	if ($("#comNm").length > 0)
		$("#comNm").ligerTextBox();
	if ($("#deptNm").length > 0)
		$("#deptNm").ligerTextBox();
	if ($("#jobName").length > 0)
		$("#jobName").ligerTextBox();
	if ($("#defQty").length > 0)
		$("#defQty").ligerTextBox();
	if ($("#factQty").length > 0)
		$("#factQty").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();

});
</script>
</head>
<body>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">工作岗位ID：</td><td><input type="text" id="jobId" name="hrJobCfg.jobId" size="30" value="<c:out value="${hrJobCfg.jobId}"/>"/></td>
			<td height="24" width="90" align="center">公司：</td><td><input type="text" id="comNm" name="hrJobCfg.comNm" size="30" value="<c:out value="${hrJobCfg.comNm}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">部门：</td><td><input type="text" id="deptNm" name="hrJobCfg.deptNm" size="30" value="<c:out value="${hrJobCfg.deptNm}"/>"/></td>
			<td height="24" width="90" align="center">岗位名称：</td><td><input type="text" id="jobName" name="hrJobCfg.jobName" size="30" value="<c:out value="${hrJobCfg.jobName}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">编制数：</td><td><input type="text" id="defQty" name="hrJobCfg.defQty" size="30" value="<c:out value="${hrJobCfg.defQty}"/>"/></td>
			<td height="24" width="90" align="center">任职数：</td><td><input type="text" id="factQty" name="hrJobCfg.factQty" size="30" value="<c:out value="${hrJobCfg.factQty}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<select id="status" name="hrJobCfg.status" value="<c:out value="${hrJobCfg.status}"/>">
					<option value="0"<c:if test="${hrJobCfg.status==0}">selected</c:if>>无效</option>
					<option value="1"<c:if test="${hrJobCfg.status==1}">selected</c:if>>有效</option>
				</select>
			</td>
			
		</tr>

	</table>
</form>

</div>

</body>
</html>