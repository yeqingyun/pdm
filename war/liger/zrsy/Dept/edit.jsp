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

<script src="./include/js/zrsy/dept.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Dept'},
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
<input type="hidden" id="deptId" name="dept.deptId" size="30" value="<c:out value="${dept.deptId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">公司：</td>
			<td>
				<select id="comId" name="dept.comId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${coms}" var="com">
						<option value="<c:out value="${com.comId}"/>"<c:if test="${com.comId==dept.comId}">selected</c:if>><c:out value="${com.comNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td align="left"></td>
			<td height="24" width="90" align="center">上级部门：</td><td>
			<select id="parent" name="dept.parent" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${depts}" var="dep">
						<option value="<c:out value="${dep.deptId}"/>"<c:if test="${dep.deptId==dept.parent}">selected</c:if>><c:out value="${dep.deptNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">部门编号：</td><td><input type="text" id="deptNo" name="dept.deptNo" size="30" value="<c:out value="${dept.deptNo}"/>"ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">部门名称：</td><td><input type="text" id="deptNm" name="dept.deptNm" size="30" value="<c:out value="${dept.deptNm}"/>"ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="dept.leve" size="30" value="<c:out value="${dept.leve}"/>" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">状态：</td><td>
				<select id="status" name="dept.status" style="width:135px">
					<option value="1"<c:if test="${dept.status==1}">selected</c:if>>有效</option>
					<option value="0"<c:if test="${dept.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="dept.remark" size="30" value="<c:out value="${dept.remark}"/>"/></td>
			<td align="left"></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>