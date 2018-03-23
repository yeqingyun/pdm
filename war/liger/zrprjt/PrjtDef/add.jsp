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

<script src="./include/js/zrprjt/prjtDef.js?v=1.0" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtDef'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	//check();
	/**
	if ($("#typId").length > 0){
		var typId = $("#typId").ligerComboBox();
		typId.set("width", 186) 
	}
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#scope").length > 0)
		$("#scope").ligerTextBox();
	if ($("#prjtNm").length > 0)
		$("#prjtNm").ligerTextBox();
	if ($("#planStaDate").length > 0){
		var planStaDate = $("#planStaDate").ligerDateEditor({showTime: false});
		planStaDate.set("width", 186) 
		
	}
	if ($("#planOveDate").length > 0){
		
		var planOveDate = $("#planOveDate").ligerDateEditor({showTime: false});
		planOveDate.set("width", 186) 
	}
	
	if ($("#status").length > 0){
		var status = $("#status").ligerComboBox();
		status.set("width", 186) 
	}
	
	if ($("#perce").length > 0)
		$("#perce").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	**/
});
</script>

 

</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form id ="prjtForm" name ="prjtForm" action="PrjtDef!sav.shtml"  method="post" enctype="multipart/form-data">


	<table width="90%">
		<tr>
			<td height="24" width="90" >项目分类：</td>
			<td>
				<select id="typId" name="prjtDef.typId" style="width:186px" >
					<option value="">请选择</option>
					 <c:forEach items="${prjtTyps}" var="prjtTyp">
						<option value="<c:out value="${prjtTyp.typId}"/>"  <c:if test="${prjtTyp.typId==1}">selected</c:if> ><c:out value="${prjtTyp.typNm}"/></option>
					</c:forEach>
				</select>
			</td>
	   </tr>
	
	   
		<tr>
			<td height="24" width="90" >研发部门：</td>
			<td>
			   
			 <select id="devDeptNameID" name="prjtDef.devDeptNameID" style="width: 196px">
					<option value = "1" <c:if test="${prjtDef.devDeptNameID==1}">selected</c:if>>深圳研发院</option>
					<%-- <option value = "2" <c:if test="${prjtDef.devDeptNameID==2}">selected</c:if>>研发二部</option> --%>
					<option value = "3" <c:if test="${prjtDef.devDeptNameID==3}">selected</c:if>>北京研发院</option>
					<option value = "4" <c:if test="${prjtDef.devDeptNameID==4}">selected</c:if>>海外研发部</option>
				</select>
			   
			  </td>
	   </tr>
	   
		<tr>
			<td height="24" width="90" >项目名称：</td><td><input type="text" id="prjtNm" name="prjtDef.prjtNm" size="30" validate="{required:true}" value="<c:out value="${prjtDef.prjtNm}"/>"/></td>
		</tr>
	   
	   <tr>
			<td height="24" width="90" >备注：</td><td><input type="text" id="remark" name="prjtDef.remark" size="30" value="<c:out value="${prjtDef.remark}"/>"/></td>
	   
	   </tr>
	   
		<!--tr>
			<td height="24" width="190" >产品定义书:</td>
			<td>
				<input name="prjtDefDocFile" type="file" id="prjtDefDocFile" size="30"/>
			</td>
	   </tr  -->
		
		<!--tr>
		<td height="24" width="90" align="center">
		<input type="submit" value="提交" id="Button1" /> 
		</td>
		</tr-->

	</table>
	
<!--  
	<style type="text/css">
	.docRecord{margin:10px 20px;}
	</style>
	<div  style="width:900px; height:1000px; border:1px solid #99BCE8; " >
	<iframe name="flexpage-frame" src="plusgantt/demo/Gantt.html"  width="100%" height="100%"frameborder="0" allowtransparency="true"  id="flexpage-frame"></iframe>
	</div>
-->	
</form>

</div>

</body>
</html>