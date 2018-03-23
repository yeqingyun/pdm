<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="./include/css/global.css" />
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<link type="text/css" rel="stylesheet" href="./include/css/process.css">
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="./include/js/jquery-form.js"></script>
<script src="./include/liger/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="./include/js/gnwf/xheditor-1.1.13-zh-cn.min.js"></script>
<script src="./include/js/zrprjt/prjtDef.js?v=1.0"
	type="text/javascript"></script>

<script type="text/javascript">
function sendAgentRequest() {
	var userName = $("#userName").val();
	if(userName==null || userName == '') {
		return false;
	}
	var isForFlow = $("#isForFlow").val();
	$("#wfRd-form")[0].action="WfRdView!setAgentBy.shtml";
	if(isForFlow == "1"){
		$("#wfRd-form").ajaxSubmit({
			success:function(data){
				 window.parent.postMessage(JSON.stringify(data),"*");
			}
		});
	}else{
		document.getElementById("wfRd-form").submit();
	}
}
var oldParam;
var sel;
var txt;
function showtips2(){
	var param = document.getElementById("userName").value;
	txt = document.getElementById("userName");
	sel = document.getElementById("sel")
	var j = 0;
	if (param != null && param!=' ') {
		if(param.length>0 && param.length<5 && oldParam!=param){
			$.ajax({
				 type:"post",  
			       url:"Usr!selectUserWithDept.shtml?usr.usrName="+encodeURI(encodeURI(param)),
			       dataType:"json",
			       success: function fun1(jsonData) { 
			    	    $("#"+"sel").empty();
			   			$.each(jsonData,function(i,item){
							var dept = "";
							if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
								dept = " -- " + item.deptNm;
							}
							//15*n px;
							if(i>10){
								$("#"+"sel").css("height","200px");
							}else
								{
								$("#"+"sel").css("height", ((i+1)*20) + "px");
							}
							$("#"+"sel").append("<option value='"+item.id+"' onclick='rv()'>" + item.usrName + dept+"</option>");  
						});
			   			if(jsonData.length>0){
							sel.size = (j > 1) ? j : 2;
							sel.size = (sel.size>17) ? 17 :sel.size;
							sel.style.display = '';
						}else{
							c();
						}
			       }
			});
		}
	}else{
		c();
	}
	oldParam = param;
}
function enterTips() {
	sel = document.getElementById("sel");
	txt = document.getElementById("userName")

	//var sel = document.getElementById("sel");
	if(txt.value.length<=0){
		sel.style.display = 'none';
	}
	var code;
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){
		code = event.keyCode;
	}else{
		code = enterTips.caller.arguments[0].which;
	}
	if (sel.style.display != 'none') {
		if (code == 13)
			event.srcElement.value = sel.value, sel.style.display = 'none';
		if (code == 40)
			sel.focus();
	}
}
var updataArr=[];
var updataItem;
var mainUserCount = 0;
function rv() {
	sel = document.getElementById("sel");
	txt = document.getElementById("userName")
		sel.style.display = 'none';
	    txt.value = sel.options[sel.selectedIndex].text;
	    document.getElementById("agentBy").value = sel.options[sel.selectedIndex].value;
	    var currentid=sel.options[sel.selectedIndex].value;
	    var roleid=$("#sel").attr("data-roleId");
	    var rowid=$("#sel").attr("data-id");
	    $(updataArr).each(function(i,item){
	    		//{id:1,roleId:1,usrId:777}
	    		if(item.id==rowid){
	    			item.usrId=currentid;
	    			updataItem=item;
	    		}
	    	});

	    	if(updataItem!=null){
	    		//updataItem.usrId=currentid;
	    		
	    	}else{
	    		if(rowid == "undefined"){
	    			updataItem={roleId:roleid,usrId:currentid};
	    		}else{
	    			updataItem={id:rowid,roleId:roleid,usrId:currentid};
	    		}
	    		//相同的角色只能有一个项目组成员
	    		var exist = false;
	    		 $(updataArr).each(function(i,item){
	    			 if(item.roleId == updataItem.roleId){
	    				 item.usrId =  updataItem.usrId;
	    				 exist= true;
	    			 }
	    		 });
	    		 if(!exist){
		    		updataArr.push(updataItem);
	    		 }
	    		updataItem = null;
	    	}
	oldParam = txt.value;
	c();
}


function c() {
	if(sel!=null){
		sel.style.display = 'none';
	}
}
document.onclick = function() {
	c();
}
</script>
<style type="text/css">
.innerTable th,.innerTable td {
	border-right: 1px solid #9FC2E5;
	border-bottom: 1px solid #9FC2E5;
	background: #fff;
	text-align: left;
	padding: 0 7px;
	
}
.innerTable th{
font-size: 14px;
font-weight: bold;
}
</style>
</head>
<body>
	<div id="toolbar"></div>
	<div class="frmInsetCon" style="background-color: #FFFEF8;">
		<form id="wfRd-form" name="wfRd-form" method="post" >
			<input type="hidden" id="isForFlow" name="isForFlow" value="<c:out value="${isForFlow}"/>"/>
			<input type="hidden" id="wfNo" name="wfNo" value="<c:out value="${wfRd.wfNo}"/>"> 
			<input type="hidden" id="taskStepId" name="taskStepId" value="<c:out value="${taskStepId}"/>">
			<input type="hidden" id="currentTaskId" name="currentTaskId" value="<c:out value="${currentTaskId}"/>">
			
			<input type="hidden" id="wfRd.wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>"> 
			<input type="hidden" id="currentTask.taskStepId" name="currentTask.taskStepId" value="<c:out value="${taskStepId}"/>">
			<input type="hidden" id="currentTask.taskId" name="currentTask.taskId" value="<c:out value="${currentTaskId}"/>">
			<br> <br>
				<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="23%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>设置代办人</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn3"></a></td>
					</tr>
				</table>
			    </div>
			  <br>
			<div id ="wfdocgrid"  class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=50% style="background:#9FC2E5;" class="innerTable" align=center>
					<tr bgcolor="#EFF0F2">
						<th width="15%">接收人（<font
							color="red">不能为空</font>）</th>
					</tr>
						<tr bgcolor="#FFFFFF" height="35">
                            <!-- 接收人 -->  
							<td>
								<input type="hidden" id="agentBy" name="agentBy" > 
					            <input type="text"  autoComplete="Off" id="userName"
						        onkeydown="enterTips()"
						        onkeyup="showtips2();if(event.keyCode==27)c();"><br>
					            <select id="sel" onkeydown="if(event.keyCode==13)rv()" style="width: 140px; position: absolute; z-index: 1000; display: none"></select>
							</td>
						</tr>
				</table>
				</div>
				<br>
			<table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0 width="50%" align=center>
				<tr>
					<td align="right">
						<input id="closeBtn" type="button" value="确认" class="wfbigbtn" onClick="sendAgentRequest()"/>
					</td>
			 </table>
		</form>
		<br>
	</div>
</body>
</html>