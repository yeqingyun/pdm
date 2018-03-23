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

<script src="./include/js/zrsy/chln.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Chln'},
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
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">年：</td><td><input type="text" id="year" name="chln.year" size="30" value="<c:out value="${chln.year}"/>" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td height="24" width="90" align="center">月：</td><td><input type="text" id="month" name="chln.month" size="30" value="<c:out value="${chln.month}"/>" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">日：</td><td><input type="text" id="day" name="chln.day" size="30" value="<c:out value="${chln.day}"/>" ltype='spinner' ligerui="{type:'int'}" validate="{required:true}"/></td>
			<td height="24" width="90" align="center">流水号：</td><td><input type="text" id="chlnNo" name="chln.chlnNo" size="30" value="<c:out value="${chln.chlnNo}"/>" ltype="text" validate="{required:true}"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">单据类型：</td><td><input type="text" id="chlnTyp" name="chln.chlnTyp" size="30" value="<c:out value="${chln.chlnTyp}"/>" ltype="text" validate="{required:true}"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>