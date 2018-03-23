<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

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
	
	
	 /**
	 
	 $("#prjtNo").ligerComboBox({url:"PrjtUsr!getpDefs.shtml",  width:186, textField:"prjtNm", valueField:"prjtNo", isMultiSelect: false,
			onSelected: function (newvalue)
	        {
				$.post("PrjtUsr!getpRoles.shtml",
						{'prjtNo':newvalue},
						function(data) {
				            $("#roleId").ligerGetComboBoxManager().setData(data);
						},
						"json"
					);
	        }
	  });
	 **/
	 
	 $("#roleId").ligerComboBox({url:"PrjtUsr!getpRoles.shtml?prjtNo="+$('#prjtNo', window.parent.document).val(),  width:186, textField:"roleNm", valueField:"roleId", isMultiSelect: false});
	 
	// $("#usrId").ligerComboBox({url:"PrjtUsr!getpUsers.shtml",  width:186, textField:"usrName", valueField:"id", isMultiSelect: false});
	
	 var statusData =
		    [{ id: 0, text: '无效' },
		    { id: 1, text: '有效' }
		   ];
	$("#status").ligerComboBox({ data: statusData, width:186, isMultiSelect: false });
	
	if ($("#priority").length > 0)
		$("#priority").ligerTextBox();
	
	

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

		    
<form>
	<input type="hidden" id="prjtNo" name="prjtNo" size="30" value="<c:out value="${prjtNo}"/>" />
	<table width="90%">
		<tr>
			<td height="280" valign="top" align="center"  >角色：</td>
			<td>
			
			      <select id="roleId" name="prjtUsr.roleId" style="width:180px" multiple size=15>
						<c:forEach items="${constantUsers}" var="u" varStatus="s">
							<option value="<c:out value="${u.userId}"/>" <c:if test="${s.count==1}">selected</c:if>>
								<c:out value="${u.usrName}"/>
							</option>
						</c:forEach>
					</select >
			
			   <input type="text" id="roleId" name="prjtUsr.roleId" size="30" validate="{required:true}" multiple size=15 />
			</td>
		
	     <td height="280" valign="top" align="center" >
			<table width="100%" border="0">
			<tr>
			<td height="25" align="left">
				<font color="blue">按名字搜索:</font><br>
				<input id="txt" name="wfRd.createName"  size="16" value="">&nbsp;
				<input id="txtBtnId" type="button" class="wfbtn" value="搜索" onclick="showtips('txt','mailAddr');" 
				 	<c:if test="${!empty constantUsers}">disabled="disabled"</c:if>>
			</td>
			</tr>
			<tr>
			<td id="selectTd" align="left">
				<br>
				<font color="blue">待选人员列表:</font>
				<c:if test="${empty constantUsers}">
					<select id="mailAddr" name="mailAddr" style="width:180px" multiple size=15></select>
				</c:if>
				<c:if test="${!empty constantUsers}">
					<select id="mailAddr" name="mailAddr" style="width:180px" multiple size=15>
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
			<td height="24" width="90" >优先级别：</td>
			<td><input type="text" id="priority" validate="{required:true}" name="prjtUsr.priority" size="30" /></td>
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
<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>