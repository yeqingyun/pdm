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

<script src="./include/js/gnwf/wfCate.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfCate'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
   	if ($("#cateParent").length > 0)
		$("#cateParent").ligerComboBox();
	if ($("#cateLevel").length > 0)
		$("#cateLevel").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#cateName").length > 0)
		$("#cateName").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">上级分类：</td>
			<td>
				<select id="cateParent" name="wfCate.cateParent">
					<option value="0">-请选择-</option>
					<c:forEach items="${cateList}" var="cate">
						<option value="<c:out value="${cate.cateId}"/>" <c:if test="${cate.cateId==wfCate.cateParent}">selected</c:if>><c:out value="${cate.cateName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">分类级别：</td>
			<td>
				<select id="cateLevel" name="wfCate.cateLevel">
					<option value="0" <c:if test="${wfCate.cateLevel==0}">selected</c:if>>0级</option>
					<option value="1" <c:if test="${wfCate.cateLevel==1}">selected</c:if>>1级</option>
					<option value="2" <c:if test="${wfCate.cateLevel==2}">selected</c:if>>2级</option>
					<option value="3" <c:if test="${wfCate.cateLevel==3}">selected</c:if>>3级</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<select id="status" name="wfCate.status">
					<option value="1" <c:if test="${wfCate.status==1}">selected</c:if>>有效</option>
					<option value="0" <c:if test="${wfCate.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
			<td height="24" width="90" align="center">分类名称：</td>
			<td><input type="text" id="cateName" name="wfCate.cateName" size="30" validate="{required:true}" value="<c:out value="${wfCate.cateName}"/>"/>
			</td>
		</tr>
	</table>
</form>

</div>

</body>
</html>