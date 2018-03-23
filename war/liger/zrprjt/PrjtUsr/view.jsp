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
<script src="./include/js/zrprjt/prjtUsr.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'PrjtUsr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	 check();
		
	 $("#prjtNo").ligerComboBox({url:"PrjtUsr!getpDefs.shtml",  width:186, textField:"prjtNm", valueField:"prjtNo", isMultiSelect: false,
			onSelected: function (newvalue)
	        {
				$.post("PrjtUsr!getpRoles.shtml",
						{'prjtNo':newvalue},
						function(data) {
							document.getElementById("prjtUsr.prjtNo").value = newvalue;
				            $("#roleId").ligerGetComboBoxManager().setData(data);
						},
						"json"
					);
	        }
	  });
	 
	 $("#roleId").ligerComboBox({data:null,  width:186, textField:"roleNm", valueField:"roleId", isMultiSelect: false});
	
	 $("#comId").ligerComboBox({url:"PrjtUsr!getpComs.shtml",  width:186, textField:"comNm", valueField:"comId", isMultiSelect: false,
			onSelected: function (newvalue)
	        {
				var str = '{';
				str += '"usr.comId":"'+newvalue+'",';
				str += '"usr.deptId":"'+$("#depId").ligerGetComboBoxManager().getValue()+'"';
				str += '}';
				
				$.post("PrjtUsr!getpUsers.shtml",
						JSON.parse(str),
						function(data) {
				            $("#usrId").ligerGetComboBoxManager().setData(data);
						},
						"json"
					);
	        }
	  });
	 
	 $("#depId").ligerComboBox({url:"PrjtUsr!getpDepts.shtml",  width:186, textField:"deptNm", valueField:"deptId", isMultiSelect: false,
			onSelected: function (newvalue)
	        {
				var str = '{';
				str += '"usr.deptId":"'+newvalue+'",';
				str += '"usr.comId":"'+$("#comId").ligerGetComboBoxManager().getValue()+'"';
				str += '}';
				
				$.post("PrjtUsr!getpUsers.shtml",
						JSON.parse(str),
						function(data) {
				            $("#usrId").ligerGetComboBoxManager().setData(data);
						},
						"json"
				);
	        }
	  });
	 
	 
	 $("#usrId").ligerComboBox({data:null,  width:186, textField:"usrName", valueField:"id", isMultiSelect: false});
	
	 var statusData =
		    [{ id: 0, text: '无效' },
		    { id: 1, text: '有效' }
		   ];
	$("#status").ligerComboBox({ data: statusData, width:186, isMultiSelect: false });
	
	if ($("#priority").length > 0)
		$("#priority").ligerTextBox();
	
	
	$.ligerui.get("prjtNo").selectValue(document.getElementById("prjtUsr.prjtNo").value);
    $.ligerui.get("roleId").selectValue(document.getElementById("prjtUsr.roleId").value);
    $.ligerui.get("comId").selectValue(document.getElementById("usr.comId").value);
    $.ligerui.get("depId").selectValue(document.getElementById("usr.deptId").value);
    $.ligerui.get("usrId").selectValue(document.getElementById("prjtUsr.usrId").value);
    $.ligerui.get("status").selectValue(document.getElementById("prjtUsr.status").value);
	

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

    <input type="hidden" id="prjtUsr.prjtNo" name ="prjtUsr.prjtNo" value="<c:out value="${prjtUsr.prjtNo}"/>"/>
    <input type="hidden" id="prjtUsr.roleId" name="prjtUsr.roleId"  value="<c:out value="${prjtUsr.roleId}"/>" />
    <input type="hidden" id="prjtUsr.usrId" name ="prjtUsr.usrId" value="<c:out value="${prjtUsr.usrId}"/>"/>
    <input type="hidden" id="prjtUsr.status" name="prjtUsr.status"  value="<c:out value="${prjtUsr.status}"/>" />
    
    <input type="hidden" id="usr.comId" name ="usr.comId" value="<c:out value="${usr.comId}"/>"/>
    <input type="hidden" id="usr.deptId" name="usr.deptId"  value="<c:out value="${usr.deptId}"/>" />
    
<form>

	<table width="90%">
		<tr>
		    <td height="24" width="90" >项目编号：</td>
			<td>
			    <input type="text" id="prjtNo" name="prjtUsr.prjtNo" size="30" validate="{required:true}" />
			</td>
			<td height="24" width="90" >角色：</td>
			<td>
			   <input type="text" id="roleId" name="prjtUsr.roleId" size="30" validate="{required:true}" />
			</td>
		</tr>
		<tr>
			 <td height="24" width="90" >公司：</td>
			 <td>
			    <input type="text" id="comId" name="comId" size="30" validate="{required:true}" />
			 </td>
			 
			  <td height="24" width="90" >部门：</td>
			 <td>
			    <input type="text" id="depId" name="comId" size="30" validate="{required:true}" />
			 </td>
		</tr>
		
		<tr>
			<td height="24" width="90" >用户：</td>
			 <td>
			    <input type="text" id="usrId" name="prjtUsr.usrId" size="30" validate="{required:true}" />
			 </td>
			<td height="24" width="90" >优先级别：</td>
			<td><input type="text" id="priority" name="prjtUsr.priority" validate="{required:true}" size="30" value="<c:out value="${prjtUsr.priority}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" >状态：</td>
			   <td>
			   <input type="text" id="status" name="prjtUsr.status" size="30" validate="{required:true}" />
			  </td>
		</tr>
		

	</table>
</form>

</div>

</body>
</html>