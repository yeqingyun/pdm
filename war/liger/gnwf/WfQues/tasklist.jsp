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

<script src="./include/js/gnwf/wfQues.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
	$("#toptoolbar").ligerToolBar({ items: [
            { text: '转入问题管理', click: transmgr },
   		    { line:true },
   		    { text: '刷新', click: reloadGrid }
   		]
   	});
});

</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>
<div id="toptoolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
<input type="hidden" id="taskId" value="<c:out value="${taskId}"/>"/>
<input type="hidden" id="wfNo" value="<%=request.getParameter("wfNo")%>"/>
<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>