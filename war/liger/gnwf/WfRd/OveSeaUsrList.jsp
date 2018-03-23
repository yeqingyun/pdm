<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css" / >
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrprjt/prjtUsr.js" type="text/javascript"></script>

<script type="text/javascript">
var urlPath='<%=request.getContextPath()%>';
	$(function(){
		initPro($("#wfRdwfNo").val());
	})
 function initPro(wfNo){
	 	//alert($('#wfRdwfNo').val()); 		
	     $.ajax({
				type: "POST",
				url:  "./WfRd!OveSeaUsrList1.shtml",
				data: {"wfRdwfNo":$('#wfRdwfNo').val()},
				dataType:'json',
				success	: function(data){

				     var options = '';
				     for(var i=0;i<data.allUsr.length;i++){
				    	 options +=  ("<option id="+data.allUsr[i].wfusrid+" value="+data.allUsr[i].usrname+">"+data.allUsr[i].usrname+"</option>");
	    				
				     }
				     $("#mailUser").html(options);
				     
				}
		});
	   } 



	function selectprojectUser(id,name) {
		//alert("tttt");
		var sel = $("#mailUser")[0]
		//alert(sel.options[sel.selectedIndex].id);
		window.parent.selectprojectUser(
				sel.options[sel.selectedIndex].id,
				$("#mailUser").val());
		//window.parent.document.getElementById("userName0").value = $("#mailUser").val();
		
		window.parent.closepop();
		//window.parent.rv(sel.options[sel.selectedIndex].id);
		
		
		
	} 




</script>
</head>
<body>


<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
   <input type="hidden" id="prjtUsr.prjtNo" name ="prjtUsr.prjtNo" value="<c:out value="${prjtUsr.prjtNo}"/>"/>
   <input type="hidden" id="isAdd" name ="isAdd" value="<c:out value="${isAdd}"/>"/>
   
   <input type="hidden" id="prjtUsr.roleTyp" name ="prjtUsr.roleTyp" value="<c:out value="${prjtUsr.roleTyp}"/>"/>
   
    <input type="hidden" id="prjtUsr.prjtTypId" name ="prjtUsr.prjtTypId" value="<c:out value="${prjtUsr.prjtTypId}"/>"/>
    
 <input type="hidden" id="wfRdwfNo" name="wfRd.wfNo"
				value="<c:out value="${wfRd.wfNo}"/>"> <input type="hidden"
				id="wfRd.flowId" name="wfRd.flowId"
				value="<c:out value="${wfRd.flowId}"/>"> <input
				type="hidden" id="currentTask.taskId" name="currentTask.taskId"
				value="<c:out value="${currentTask.taskId}"/>"> <input
				type="hidden" id="currentTask.sort" name="currentTask.sort"
				value="<c:out value="${currentTask.sort}"/>"> <input
				type="hidden" id="currentTask.createBy" name="currentTask.createBy"
				value="<c:out value="${currentTask.createBy}"/>"> <input
				type="hidden" id="currentTask.isLastStep"
				name="currentTask.isLastStep"
				value="<c:out value="${currentTask.isLastStep}"/>"> <input
				type="hidden" id="checkReSub" name="checkReSub"
				value="<c:out value="${checkReSub}"/>">
				
				<input type="hidden" id="taskStepId" name="taskStepId" value="<c:out value="${taskStepId}"/>">
				<input type="hidden" id="currentTaskId" name="currentTaskId" value="<c:out value="${currentTaskId}"/>">
				<input type="hidden" id="wfRd.projectNo" name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>">
				
				<input type="hidden" id="gpNames" name="gpNames" size="30" value="<c:out value="${sessionScope.SYUSR.gpNames}"/>" />
<form>
	<table width="100%"  >
		
	
		
		<td height="280" valign="top" align="center" >
			<table width="100%" border="0">
				<tr>
				<td id="selectTd" align="left">
					<br>
					<font color="blue">人员列表:</font>
					<br>
					<select id="mailUser" name="mailUser" style="width:186px"  size=15></select>
				</td>
				</tr>
			</table>
		</td>
	</table>
	
	 <table width="100%">
	  <tr>
	  <td></td>
	 
	  <td align="right"><input type="button" class="wfbigbtn2" value="  确定   " onclick="selectprojectUser('mailUser')"/></td>
	  </tr>
	 </table>
</form>
</html>