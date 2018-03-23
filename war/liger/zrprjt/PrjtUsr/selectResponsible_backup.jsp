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

<script type="text/javascript">


function selcRspon(){
	var usrString="";
	var usrIds="";
	var deptNms="";
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i].usrId){
			if(usrIds.indexOf(checkedCustomer[i].usrId) <= -1) {
				usrIds+=checkedCustomer[i].usrId+",";
			}
		}
		if(checkedCustomer[i].usrName){
			if(usrString.indexOf(checkedCustomer[i].usrName) <= -1) {
				usrString=usrString+checkedCustomer[i].usrName+",";
			}
		}
		if(checkedCustomer[i].deptNm){
			if(deptNms.indexOf(checkedCustomer[i].deptNm) <= -1) {
				deptNms=deptNms+checkedCustomer[i].deptNm+",";
			}
		}
	}
	usrIds= usrIds.substring(0,usrIds.length-1);
	window.parent.setUsrData(usrIds,usrString,deptNms);
	window.parent.dialog33.close();
}
 var gridManager;
$(function () {
	$("#toolbar").ligerToolBar({ items: [
	                               		    { text: '确定', click: selcRspon },
	                               		]
	 });
	
	$("#maingrid").ligerGrid({
		columns: [

			{ display: '角色名', name: 'roleNm', align: 'left', width: 140 },
			{ display: '用户名', name: 'usrName', align: 'left', width: 70 },
			
			/* { display: '部门名称', name: 'deptNm', align: 'left', width: 140 } */
		],
		checkbox: true,
		rownumbers:true,
		url:'./PrjtUsr!prjtUsrlist.shtml?prjtNo='+$("#prjtNo").val(),
		width: '99.5%',
		height:'99%',
		onCheckRow: f_onCheckRow,
	    frozen:false,
		pageSizeOptions: [3,10, 20, 30, 40, 50, 100], 
        onError: function (a, b)
        { 
        }
		
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});
var checkedCustomer = [];
function findCheckedCustomer(data) {
	for(var i =0;i<checkedCustomer.length;i++) {
		if(checkedCustomer[i] == data) return i;
	}
	return -1;
}
function addCheckedCustomer(data) {
	if(findCheckedCustomer(data) == -1)
		checkedCustomer.push(data);
}
function removeCheckedCustomer(data){
	var i = findCheckedCustomer(data);
	if(i==-1) return;
	checkedCustomer.splice(i,1);
}
function f_isChecked(rowdata) {
	if (findCheckedCustomer(rowdata) == -1)
		return false;
	return true;
}
function f_onCheckRow(checked, data){
	if (checked){
			addCheckedCustomer(data);
	}
	else
		removeCheckedCustomer(data);
}
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">
<input type="hidden" id="prjtNo" name ="prjtNo" value="<c:out value="${prjtNo}"/>"/>
<input type="hidden" id="quesId" name ="quesId" value="<c:out value="${wfQues.quesId}"/>"/>
<!--div id="sform" style="margin:10px;height:30px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">角色名称：</td>
			<td>
			 <select id="roleId" name="prjtUsr.roleId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtRoles}" var="prjtRole">
						<option value="<c:out value="${prjtRole.roleId}"/>"><c:out value="${prjtRole.roleNm}"/></option>
					</c:forEach>
				</select>
			 </td>
			<td height="24" width="90" align="center">用户名：</td><td><input type="text" id="usrName" name="prjtUsr.usrName"/></td>
		</tr>

	</table>
</div  -->
<br></br>
<div id="maingrid" ></div>
</form>
</body>
</html>
