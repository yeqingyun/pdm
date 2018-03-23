<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<link rel="stylesheet" href="./include/css/ifrm.css" type="text/css"/>
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css"/>
</head>

<SCRIPT language="javascript"> 
　　function printit(){ 
		//if (confirm('确定打印吗？')){ 
			window.print();
		//} 
　　} 
</SCRIPT>
</head>

<style media=print> 
.noprint{display:none;} 
</style>

<body>


<!-- 表单 -->
<form action="" id="form"  name="form" method="post">
<% 
	String path = (String)request.getAttribute("includeJspUri");
	if(path!=null){
%>
	<jsp:include flush="true" page="<%=path%>"></jsp:include>
<% 
	}
%>
</form>
<!-- 表单 -->

<script type="text/javascript">
 for (var i=0;i<document.form.elements.length;i++){
	var e = form.elements[i];
	if(e.type=="button"){			// || e.type=="text"
		e.style.display="none";
	}else if(e.type=="radio"){
		if(e.checked==false){
			e.disabled="disabled"
		}
	}
}
</script>

</body>
</html>
