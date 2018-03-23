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
<script src="./include/js/zrprjt/prjtRole.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtRole'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	check();
	
	if ($("#roleNm").length > 0)
		$("#roleNm").ligerTextBox();
	
	//$("#prjtNo").ligerComboBox({url:"PrjtRole!getpDefs.shtml",  width:186, textField:"prjtNm", valueField:"prjtNo", isMultiSelect: false});
	 //flowIdmanager = $("#prjtNo").ligerGetComboBoxManager();
	 
	 $("#prjtTypId").ligerComboBox({url:"PrjtRole!getTyps.shtml",  width:186, textField:"typNm", valueField:"typId", isMultiSelect: false});
	
	 
	var isReadData =
		 [
			{id:0,text:'可查看'},
			{id:1,text:'可上传'},
			{id:2,text:'可下载'},
			{id:3,text:'可查看，上传，下载'}
		   ];
	$("#isRead").ligerComboBox({ data: isReadData, width:186, isMultiSelect: false });
	 
	var statusData =
		    [{ id: 0, text: '无效' },
		    { id: 1, text: '有效' }
		   ];
	$("#status").ligerComboBox({ data: statusData, width:186, isMultiSelect: false });
	
	var roleTypData =
		   [
			{id:0,text:'人员不随项目变更'},
			{id:1,text:'人员随项目变更'}
		   ];
	$("#roleTyp").ligerComboBox({ data: roleTypData, width:186, isMultiSelect: false });
	
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	
	
	
	$.ligerui.get("prjtTypId").selectValue(document.getElementById("prjtRole.prjtTypId").value);
    $.ligerui.get("isRead").selectValue(document.getElementById("prjtRole.isRead").value);
    $.ligerui.get("status").selectValue(document.getElementById("prjtRole.status").value);
    $.ligerui.get("roleTyp").selectValue(document.getElementById("prjtRole.roleTyp").value);

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
   
    <input type="hidden" id="prjtRole.prjtTypId" name ="prjtRole.prjtTypId" value="<c:out value="${prjtRole.prjtTypId}"/>"/>
    <input type="hidden" id="prjtRole.isRead" name="prjtRole.isRead" value="<c:out value="${prjtRole.isRead}"/>" />
    <input type="hidden" id="prjtRole.status" name="prjtRole.status" value="<c:out value="${prjtRole.status}"/>" />
    <input type="hidden" id="prjtRole.roleTyp" name="prjtRole.roleTyp" value="<c:out value="${prjtRole.roleTyp}"/>" />
<form >


    <input type="hidden" id="prjtRole.roleId" name ="prjtRole.roleId" value="<c:out value="${prjtRole.roleId}"/>"/>
	<table width="90%">
	   <tr>
			<td height="24" width="90" >项目名称：</td>
			<td>
			    <input type="text" id="prjtTypId" name="prjtRole.prjtTypId" size="30" validate="{required:true}" />
			</td>
			<td height="24" width="90" >角色名称：</td><td><input type="text" id="roleNm"  validate="{required:true}"name="prjtRole.roleNm" size="30" value="<c:out value="${prjtRole.roleNm}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" >权限：</td>
			  <td>
			    <input type="text" id="isRead" name="prjtRole.isRead" size="30" validate="{required:true}" />
			  </td>
			<td height="24" width="90" >角色类型：</td>
			 <td>
			    <input type="text" id="roleTyp" name="prjtRole.roleTyp" size="30" validate="{required:true}" />
			 </td>
		</tr>
		<tr>
		    <td height="24" width="90" >状态：</td>
			 <td>
			    <input type="text" id="status" name="prjtRole.status" size="30" validate="{required:true}" />
			 </td>
			<td height="24" width="90" >备注：</td><td><input type="text" id="remark" name="prjtRole.remark" size="30" value="<c:out value="${prjtRole.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>