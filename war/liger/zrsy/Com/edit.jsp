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

<script src="./include/js/zrsy/com.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Com'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	check();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<form>
<input type="hidden" id="comId" name="com.comId" size="30" value="<c:out value="${com.comId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">上级公司：</td><td>
			<select id="parent" name="com.parent" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${coms}" var="co">
						<option value="<c:out value="${co.comId}"/>"<c:if test="${co.comId==com.comId}">selected</c:if>><c:out value="${co.comNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td align="left"></td>
			<td height="24" width="90" align="center">公司编号：</td><td><input type="text" id="comNo" name="com.comNo" size="30" value="<c:out value="${com.comNo}"/>" ltype="text" validate="{required:true}" /></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">公司名称：</td><td><input type="text" id="comNm" name="com.comNm" size="30" value="<c:out value="${com.comNm}"/>" ltype="text" validate="{required:true}" /></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="com.leve" size="30" value="<c:out value="${com.leve}"/>"  ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="com.status" style="width:135px">
					<option value="1"<c:if test="${com.status==1}">selected</c:if>>有效</option>
					<option value="0"<c:if test="${com.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
			<td align="left"></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="com.remark" size="30" value="<c:out value="${com.remark}"/>"/></td>
			<td align="left"></td>
		</tr>

	</table>
</form>
</div>

</body>
</html>