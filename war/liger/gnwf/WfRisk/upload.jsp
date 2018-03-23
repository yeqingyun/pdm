<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.header{
	position:absolute;
    top:50%;
    left:50%;
    width:348px;
    height:60px;
    margin-top:-30px;
    margin-left:-150px;

    line-height:10px;
    font-size:13px;
    text-align:center;
}
</style>
<script type="text/javascript">
function formSubmit() {
	document.getElementById("form").action=$("#action").val();
	document.getElementById("form").submit();
}
</script>
</head>
<body>

<form method="post" enctype="multipart/form-data" id="form">
<input type="hidden" id="action" value="<c:out value="${action}"/>"/>
<input type="hidden" id="prjtNo" name="prjtNo" value="<c:out value="${prjtNo}"/>"/>
<div class="header">
	<input type="file" id="fileInp" name="fileInp"/>
	<input type="button" value="导入" onclick="formSubmit();"/><br/><font size="-1" color="#ff4500"><c:out value="${msg}"/></font>
</div>
</form>
</body>
</html>
