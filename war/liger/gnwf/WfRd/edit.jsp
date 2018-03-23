<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

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

<script src="./include/js/gnwf/wfRd.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfRd'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
	if ($("#planSDate").length > 0)
		$("#planSDate").ligerDateEditor({showTime: false});
	if ($("#planEDate").length > 0)
		$("#planEDate").ligerDateEditor({showTime: false});
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox();
	if ($("#wfDesc").length > 0)
		$("#wfDesc").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form id="wfRd-form">
	<input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>" />
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">流程编号：</td>
			<td><c:out value="${wfRd.wfNo}"/></td>
			<td height="24" width="90" align="center">父流程编号：</td>
			<td>
				<c:if test="${empty wfRd.preWfNo}">无</c:if>
				<c:if test="${!empty wfRd.preWfNo}"><c:out value="${wfRd.preWfNo}"/></c:if>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">工作流标题：</td>
			<td><input type="text" id="wfName" name="wfRd.wfName" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfName}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<!-- 
				<select id="status" name="wfRd.status" style="width: 200px;">
					<option value="1" <c:if test="${wfRd.status==0}">selected</c:if>>待处理</option>
					<option value="1" <c:if test="${wfRd.status==1}">selected</c:if>>办理中</option>
					<option value="2" <c:if test="${wfRd.status==2}">selected</c:if>>已完成</option>
					<option value="3" <c:if test="${wfRd.status==3}">selected</c:if>>已中止</option>
					<option value="4" <c:if test="${wfRd.status==4}">selected</c:if>>已删除</option>
				</select>
				 -->
				
			<c:choose>
			<c:when test="${isLeader==1}">
				<select id="status" name="wfRd.status"" 
					<c:if test="${wfRd.status==3}">disabled="disabled"</c:if>>
					<%-- <option value="1" <c:if test="${wfRd.status==0}">selected</c:if>>待处理</option> --%>
					<option value="1" <c:if test="${wfRd.status==1}">selected</c:if>>办理中</option>
					<option value="2" <c:if test="${wfRd.status==2}">selected</c:if>>已完成</option>
					<option value="3" <c:if test="${wfRd.status==3}">selected</c:if>>已关闭</option>
					<%-- <option value="4" <c:if test="${wfRd.status==4}">selected</c:if>>已删除</option> --%>
				</select>
				<!-- 
				<c:if test="${wfRd.status==3}">
					<input type="button" value="重新启动流程" onclick="restartWfinfo();"/>
				</c:if>
				 -->
			</c:when>
			<c:when test="${isLeader!=1 && wfRd.status==0}">
					<select id="status" name="wfRd.status">
						<option value="1" <c:if test="${wfRd.status==0}">selected</c:if>>待处理</option>
						<option value="4" <c:if test="${wfRd.status==4}">selected</c:if>>已删除</option>
					</select>
			</c:when>
			<c:when test="${isLeader!=1 && wfRd.status!=0}">
				<c:choose>
					<c:when test="${wfRd.status==1}"><font color="blue">办理中</font></c:when>
					<c:when test="${wfRd.status==2}"><font color="blue">已完成</font></c:when>
					<c:when test="${wfRd.status==3}">
						<font color="blue">已中止</font>&nbsp;&nbsp;&nbsp;
						<input type="button" value="重新启动流程" onclick="restartWfinfo();"/>
					</c:when>
					<c:when test="${wfRd.status==4}"><font color="blue">已删除</font></c:when>
				</c:choose>
			</c:when>
		</c:choose>
				
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">计划开始时间：</td>
			<td>
				<fmt:formatDate value="${wfRd.planSDate}" var="planStaDate" type="date"/>
				<input type="text" id="planSDate" name="wfRd.planSDate" size="30" ltype="date" value="<c:out value="${planStaDate}"/>"/>
			</td>
			<td height="24" width="90" align="center">计划完成时间：</td>
			<td>
				<fmt:formatDate value="${wfRd.planEDate}" var="planEDate" type="date"/>
				<input type="text" id="planEDate" name="wfRd.planEDate" size="30" ltype="date" value="<c:out value="${planEDate}"/>"/>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">工作流描述：</td>
			<td><input type="text" id="wfDesc" name="wfRd.wfDesc" size="30"  value="<c:out value="${wfRd.wfDesc}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>