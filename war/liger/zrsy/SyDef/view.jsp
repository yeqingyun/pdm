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

<script src="./include/js/zrsy/syDef.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'SyDef'},
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
<input type="hidden" id="syId" name="syDef.syId" size="30" value="<c:out value="${syDef.syId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">系统名称：</td><td><input type="text" id="syName" name="syDef.syName" size="30" value="<c:out value="${syDef.syName}"/>" ltype="text" validate="{required:true}"/></td><td align="left"></td>
			<td height="24" width="90" align="center">系统描述：</td><td><input type="text" id="syText" name="syDef.syText" size="30" value="<c:out value="${syDef.syText}"/>" ltype="text" validate="{required:true}"/></td><td align="left"></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>