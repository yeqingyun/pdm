<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title></title>

    <link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
    <link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    
    <script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
    <script src="./include/liger/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(function () {
		$("#lay1").ligerLayout();
		
		$("#navtab1").ligerTab(); 
		
		navtab = $("#navtab1").ligerGetTabManager();
		navtab.addHeight(0);
	});

	function addtab(tabid,text, url) {
		navtab.addTabItem({tabid:tabid,text:text,url:url});
		navtab.addHeight(0);
	}
	</script>

</head>

<body style="padding-top:0px;padding-left:5px;padding-right:5px;">
<div id="lay1">

<div position="center" id="navtab1" name="navtab1">
	<c:forEach items="${syDefs}" var="syDef">
		<div tabid="<c:out value="${syDef.syId}"/>" title="<c:out value="${syDef.syName}"/>" lselected="true" style="height:92%">
			<iframe frameborder="0" height="100%" src="./Gp!auth.shtml?gp.gpId=<c:out value="${gp.gpId}"/>&syDef.syId=<c:out value="${syDef.syId}"/>"></iframe>
		</div>
	</c:forEach>
</div>

</div> 

</body>

</html>
