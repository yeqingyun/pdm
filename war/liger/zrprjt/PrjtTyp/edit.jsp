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

<script src="./include/js/zrprjt/prjtTyp.js?ver=1.0" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtTyp'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	check();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#typNm").length > 0)
		$("#typNm").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
     <input type="hidden" id="prjtTyp.typId" name ="prjtTyp.typId" value="<c:out value="${prjtTyp.typId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">项目分类：</td><td><input type="text" id="typNm" name="prjtTyp.typNm" size="30" validate="{required:true}" value="<c:out value="${prjtTyp.typNm}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td>
			<td>
			    <select id="status" name="prjtTyp.status" style="width: 196px">
					<option value = "1" <c:if test="${prjtTyp.status==1}">selected</c:if>>有效</option>
					<option value = "0" <c:if test="${prjtTyp.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="prjtTyp.remark" size="30" value="<c:out value="${prjtTyp.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>