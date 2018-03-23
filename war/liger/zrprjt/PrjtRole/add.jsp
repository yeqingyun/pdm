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
	// flowIdmanager = $("#prjtNo").ligerGetComboBoxManager();
	 
	 $("#prjtTypId").ligerComboBox({url:"PrjtRole!getTyps.shtml",  width:186, textField:"typNm", valueField:"typId", isMultiSelect: false});
	 
	
	 
	var isReadData =
		 [
			{id:0,text:'可查看'},
			{id:1,text:'可上传'},
			{id:2,text:'可下载'},
			{id:3,text:'可查看，上传，下载'}
		   ];
	$("#isRead").ligerComboBox({ data: isReadData, width:186, isMultiSelect: false });
	
	
	var roleTypData =
		   [
			{id:0,text:'人员不随项目变更'},
			{id:1,text:'人员随项目变更'}
		   ];
	$("#roleTyp").ligerComboBox({ data: roleTypData, width:186, isMultiSelect: false });
	
	
	var statusData =
		    [{ id: 0, text: '无效' },
		    { id: 1, text: '有效' }
		   ];
	$("#status").ligerComboBox({ data: statusData, width:186, isMultiSelect: false });
	
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
	   <tr>
			<td height="24" width="90" >项目类型：</td>
			<td>
			<input type="text" id="prjtTypId" name="prjtRole.prjtTypId" size="30" validate="{required:true}" />
				<!--select id="prjtNo" name="prjtRole.prjtNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtDefs}" var="prjtDef">
						<option value="<c:out value="${prjtDef.prjtNo}"/>"><c:out value="${prjtDef.prjtNm}"/></option>
					</c:forEach>
				</select-->
			</td>
			<td height="24" width="90" >角色名称：</td><td><input type="text" id="roleNm" validate="{required:true}" name="prjtRole.roleNm" size="30" value="<c:out value="${prjtRole.roleNm}"/>"/></td>
		</tr>
		<tr>
		    <td height="24" width="90" >权限：</td>
		 	<td>
		 	   <input type="text" id="isRead" name="prjtRole.isRead" size="30" validate="{required:true}" />
			   <!--select id="isRead" name="prjtRole.isRead" style="width: 196px">
			        <option value="">请选择</option>
					<option value="0">可查看</option>
					<option value="1">可上传</option>
					<option value="2">可下载</option>
					<option value="3">可查看，上传，下载</option>
				</select  -->
			</td>
			
			
			<td height="24" width="90" >角色类型：</td>
			<td>
			  <input type="text" id="roleTyp" name="prjtRole.roleTyp" size="30" validate="{required:true}" />
			 <!-- select id="status" name="prjtRole.status" style="width: 196px">
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select  -->
			</td>
			
		</tr>
		<tr>
			<td height="24" width="90" >状态：</td>
			<td>
			  <input type="text" id="status" name="prjtRole.status" size="30" validate="{required:true}" />
			 <!-- select id="status" name="prjtRole.status" style="width: 196px">
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select  -->
			</td>
			<td height="24" width="90" >备注：</td><td><input type="text" id="remark" name="prjtRole.remark" size="30" value="<c:out value="${prjtRole.remark}"/>"/></td>
		</tr>
		
		<!-- tr>
			<input type="text" id="status" name="prjtRole.status" size="30" value="<c:out value="${prjtRole.status}"/>"/>
			<td height="24" width="90" align="center">ID：</td><td><input type="text" id="roleId" name="prjtRole.roleId" size="30" value="<c:out value="${prjtRole.roleId}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="prjtRole.createBy" size="30" value="<c:out value="${prjtRole.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="prjtRole.lastUpd" size="30" value="<c:out value="${prjtRole.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="prjtRole.createDate" size="30" value="<c:out value="${prjtRole.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="prjtRole.lastDate" size="30" value="<c:out value="${prjtRole.lastDate}"/>"/></td>
		</tr><tr>
		</tr-->

	</table>
</form>

</div>

</body>
</html>