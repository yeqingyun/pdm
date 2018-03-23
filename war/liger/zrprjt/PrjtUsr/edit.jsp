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
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtUsr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	 check();
	 
	 //$("#usrId").ligerComboBox({data:null,  width:186, textField:"usrName", valueField:"id", isMultiSelect: false});
	 
	 var statusData =
		    [{ id: 0, text: '无效' },
		    { id: 1, text: '有效' }
		   ];
	$("#status").ligerComboBox({ data: statusData, width:186, isMultiSelect: false });
	
	if ($("#priority").length > 0)
		$("#priority").ligerTextBox();
    $.ligerui.get("status").selectValue(document.getElementById("prjtUsr.status").value);
    $("#usrId").append("<option value='"+document.getElementById("usr.id").value+"'>" + document.getElementById("usr.usrName").value + document.getElementById("usr.deptNm").value+"</option>"); 
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
    
    <input type="hidden" id="prjtUsr.id" name="prjtUsr.id"  value="<c:out value="${prjtUsr.id}"/>" />
    
    <input type="hidden" id="prjtUsr.status" name="prjtUsr.status"  value="<c:out value="${prjtUsr.status}"/>" />
    
    <input type="hidden" id="usr.id" name ="usr.id" value="<c:out value="${usr.id}"/>"/>
     <input type="hidden" id="usr.usrName" name ="usr.usrName" value="<c:out value="${usr.usrName}"/>"/>
    <input type="hidden" id="usr.deptNm" name="usr.deptNm"  value="<c:out value="${usr.deptNm}"/>" />
    
    
    <input type="hidden" id="prjtUsr.oldUsrId" name="prjtUsr.oldUsrId"  value="<c:out value="${prjtUsr.usrId}"/>" />
    <input type="hidden" id="prjtUsr.oldStatus" name="prjtUsr.oldStatus"  value="<c:out value="${prjtUsr.status}"/>" />
    
    
<form>

	<table width="90%">
		<tr>
		    <td height="24" width="90" >项目类型：</td>
			<td>
                <c:out value="${prjtUsr.prjtTypNm}"/>
			</td>
		</tr>
		
		<tr>
			<td height="24" width="90" >角色：</td>
			<td>
			    <c:out value="${prjtUsr.roleNm}"/>
			</td>
	    </tr>
	    
	    <tr>
			<td height="24" width="90" >优先级别：</td>
			<td>
			  <c:out value="${prjtUsr.priority}"/>
			  <!--input type="text" id="priority" name="prjtUsr.priority" validate="{required:true}" size="30" value="<c:out value="${prjtUsr.priority}"/>"/  -->
			
			</td>
		</tr>
		<tr>	
			
		 <td height="24" width="90"  >人员：</td>	
		 <td height="280" valign="top" align="center" >
			<table width="100%" border="0">
				<tr>
				<td height="25" align="left">
					<font color="blue">按名字搜索:</font><br>
					<input id="txt" name="usrName"  size="22" value="">&nbsp;
					<input id="txtBtnId" type="button" class="wfbtn" value="搜索" onclick="showtips('txt','usrId');" 
					 	<c:if test="${!empty constantUsers}">disabled="disabled"</c:if>>
				</td>
				</tr>
				
				<tr>
				<td id="selectTd" align="left">
					<br>
					<font color="blue">待选人员列表:</font>
					<br>
					<c:if test="${empty constantUsers}">
						<select id="usrId" name="prjtUsr.usrId" style="width:186px" multiple size=10></select>
					</c:if>
					<c:if test="${!empty constantUsers}">
						<select id="usrId" name="prjtUsr.usrId" style="width:186px" multiple size=10>
							<c:forEach items="${constantUsers}" var="u" varStatus="s">
								<option value="<c:out value="${u.userId}"/>" <c:if test="${s.count==1}">selected</c:if>>
									<c:out value="${u.usrName}"/>
								</option>
							</c:forEach>
						</select>
					</c:if>
				</td>
				</tr>
			</table>
		</td>
		</tr>
		
		
		
		<tr>
			<td height="24" wi33dth="90" >状态：</td>
			   <td>
			   <input type="text" id="status" name="prjtUsr.status" size="30" validate="{required:true}" />
			  </td>
		</tr>
		

	</table>
</form>

</div>

</body>
</html>