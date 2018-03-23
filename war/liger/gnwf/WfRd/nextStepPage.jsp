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
<script src="./include/liger/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js"
	type="text/javascript"></script>

<!-- <script type="text/javascript" src="./include/js/oa.js"></script> -->
<script type="text/javascript" src="./include/js/gnwf/wfRd.js"></script>
<script type="text/javascript" src="./include/js/gnwf/NextStepPage.js"></script>
<!-- <script type="text/javascript" src="./include/cal/WdatePicker.js"></script> -->

<script type="text/javascript" src="./include/js/gnwf/MailTo.js"></script>
<script type="text/javascript"
	src="./include/js/gnwf/xheditor-1.1.13-zh-cn.min.js"></script>
<script src="./include/js/zrprjt/prjtDef.js?v=1.0"
	type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	/**
	$.post("ligerToolBar!custom.shtml",
			{parm:'WfRd',pageType:'12'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	**/
});

/*============================================================*/
 var oldParam;
 var sel;
 var txt;
 function showtips2(index){
 	var param = document.getElementById("userName"+index).value;
 	txt = document.getElementById("userName"+index);
 	sel = document.getElementById("sel"+index)
 	var j = 0;
 	if (param != null && param!=' ') {
 		if(param.length>0 && param.length<5 && oldParam!=param){
 			$.ajax({
 				 type:"post",  
 			       url:"Usr!selectUserWithDept.shtml?usr.usrName="+encodeURI(encodeURI(param)),
 			       dataType:"json",
 			       success: function fun1(jsonData) { 
 			    	    $("#"+"sel"+index).empty();
 			   			$.each(jsonData,function(i,item){
 							var dept = "";
 							if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
 								dept = " -- " + item.deptNm;
 							}
 							
 							//15*n px;
 							if(i>10){
 								$("#"+"sel"+index).css("height","200px");
 							}else
 								{
 								$("#"+"sel"+index).css("height", ((i+1)*20) + "px");
 							}
 							$("#"+"sel"+index).append("<option value='"+item.id+"' onclick='rv("+index+")'>" + item.usrName + dept+"</option>");  
 							
 							
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



 function enterTips(index) {
 	sel = document.getElementById("sel"+index);
 	txt = document.getElementById("userName"+index)
 
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
 function rv(index) {
	// alert("sb");
	sel = document.getElementById("sel"+index);
 	txt = document.getElementById("userName"+index)
 		sel.style.display = 'none';
 	    txt.value = sel.options[sel.selectedIndex].text;
 	    document.getElementById("userId"+index).value = sel.options[sel.selectedIndex].value;
		
		
		var td1 = document.getElementById("task"+index);
		//alert("td1"+td1);
		//if(td1!=null){
			//if(td1.innerHTML!="&nbsp;" && td1.innerHTML!=""){
				//alert("主办人只能增加一个。。");
			//}else{
				
				var hiddens = "";
				hiddens += "<input type=\"hidden\" name=\"wfTasks["+index+"].stepId\" value=\""+document.getElementById("nextSteps["+index+"].stepId").value+"\">";
				hiddens += "<input type=\"hidden\" name=\"wfTasks["+index+"].acceptBy\" value=\""+sel.options[sel.selectedIndex].value+"\">";
				hiddens += "<input type=\"hidden\" name=\"wfTasks["+index+"].taskType\" value=\""+1+"\">";
				//mainUserCount++;
				td1.innerHTML =  hiddens;
			//}
		//}
		
 	    var currentid=sel.options[sel.selectedIndex].value;
 	    var roleid=$("#sel"+index).attr("data-roleId");
 	    var rowid=$("#sel"+index).attr("data-id");
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
     //editgridManager.endEdit(index);
 	oldParam = txt.value;
 	c();
 }
 
 
 
 function c() {
 	if(sel!=null){
 		sel.style.display = 'none';
 	}
 	if(xbsel!=null){
		xbsel.style.display = 'none';
	}
 }
 document.onclick = function() {
 	c();
 }
/*==========================================================================*/
var xbipt;
var xbsel;
function showxbSel(index){
	

	var j = 0;
	xbipt = document.getElementById("xbipt"+index);
	xbsel = document.getElementById("xbsel"+index);
	var param = document.getElementById("xbipt"+index).value;
	if(param.length>0){
		$.ajax({
			type:"post",  
		    url:"Usr!selectUserWithDept.shtml?usr.usrName="+encodeURI(encodeURI(param)),
		    dataType:"json",
		    success: function fun1(jsonData){
		        $("#"+"xbsel"+index).empty();
	   			$.each(jsonData,function(i,item){
					var dept = "";
					if(item.deptNm!=null && item.deptNm!='undefined' && item.deptNm.length>0){
						dept = " -- " + item.deptNm;
					}
					
					//15*n px;
					if(i>10){
						$("#"+"xbsel"+index).css("height","200px");
					}else
						{
						$("#"+"xbsel"+index).css("height", ((i+1)*20) + "px");
					}
					$("#"+"xbsel"+index).append("<option value='"+item.id+"' onclick='setxbV("+index+")'>" + item.usrName + dept+"</option>");  
					
					
				});
		   			if(jsonData.length>0){
						xbsel.size = (j > 1) ? j : 2;
						xbsel.size = (xbsel.size>17) ? 17 :xbsel.size;
						xbsel.style.display = '';
					}else{
						if(xbsel!=null){
					 		xbsel.style.display = 'none';
					 	}
					}
		    }
		});
	}
	if(xbipt.value.length<=1){
 		xbsel.style.display = 'none';
 	}
}
function toxbSelFocus(index){
	xbsel = document.getElementById("xbsel"+index);
 	xbipt = document.getElementById("xbipt"+index)
 
 	//var sel = document.getElementById("sel");
 	if(xbipt.value.length<=0){
 		xbsel.style.display = 'none';
 	}
	var code;
	//浏览器兼容判断
	if(navigator.appName == "Microsoft Internet Explorer"){
		code = event.keyCode;
	}else{
		code = toxbSelFocus.caller.arguments[0].which;
	}
 	if (xbsel.style.display != 'none') {
 		if (code == 13)
 			event.srcElement.value = xbsel.value, xbsel.style.display = 'none';
 		if (code == 40)
 			xbsel.focus();
 	}
}
function setxbV(index){
 	xbsel = document.getElementById("xbsel"+index);
 	xbipt = document.getElementById("xbipt"+index)
 	xbsel.style.display = 'none';
 	xbipt.value = xbsel.options[xbsel.selectedIndex].text;
    var currentid=xbsel.options[xbsel.selectedIndex].value;
    var roleid=$("#xbsel"+index).attr("data-roleId");
    var rowid=$("#xbsel"+index).attr("data-id");
}

function selectprojectUser(id,name) {
	$("#userId"+selectedUIndex).val(id);
	$("#userName"+selectedUIndex).val(name);
	var td1 = document.getElementById("task"+selectedUIndex);	
	var hiddens = "";
	hiddens += "<input type=\"hidden\" name=\"wfTasks["+selectedUIndex+"].stepId\" value=\""+document.getElementById("nextSteps["+selectedUIndex+"].stepId").value+"\">";
	hiddens += "<input type=\"hidden\" name=\"wfTasks["+selectedUIndex+"].acceptBy\" value=\""+id+"\">";
	hiddens += "<input type=\"hidden\" name=\"wfTasks["+selectedUIndex+"].taskType\" value=\""+1+"\">";
	td1.innerHTML =  hiddens;
} 
// function hidexbSel(index){
//  	xbsel = document.getElementById("xbsel"+index);
//  	xbsel.style.display = 'none';
// }





//选择项目组成员
$(function() {
	function syncdata() {
		$("#docDesc").val();
	}
});

var autoid=1;
var editgridManager;
var statusData = [{ Status: -1, text: '未配置人员'},{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
var roleTypData = [{ RoleTyp: 0, text: '人员不随项目变更' }, { RoleTyp: 1, text: '人员随项目变更'}];
var upTypData = [{ UpTyp: 1, text: '加入项目组' }, { UpTyp: 0, text: '离开项目组'}];


var selectWin =null;
//open select window


var pop;
var selectedUIndex;
function selectPrjtUsers(element){
	var domName = $(element).parent().parent().find("[id^=userName]").attr("id");
	var keyName = "userName";
	selectedUIndex = domName.substring(keyName.length,domName.length);
	//rv(<c:out value="${i}"/>)
	//alert(selectedUIndex);
	
	 if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
		//alert("fff");
		selectPrjtUsers2();
	}else{ 
		//弹出改人员权限角色
		//alert(document.getElementById('gpNames').value);
		/* alert("ddd");
		alert($('#wfRdwfNo').val()); */
		//alert(${wfRd.wfNo}.val()+"111111111");
		//alert(${wfRd.wfNo}+"1111122221111");
		pop =  $.ligerDialog.open({title:'选择项目组成员', height: 390, width: 500,/*  url: './PrjtUsr!addMailUser.shtml',  */
			  url : './WfRd!OveSeaUsrList.shtml?wfRd.wfNo='
				+ $('#wfRdwfNo').val()  ,
			showMax: true, showToggle: true, showMin: true, isResize: true
		});
	}
}

function closepop(){
	pop.close();
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
		<form id="wfRd-form" name="wfRd-form" method="post"
			action="WfRdView.shtml" onsubmit="return check();">

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

			<br> <br>


			<!-- 转交界面 -->
			<c:if test="${!empty nextSteps}">
				<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="23%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>任务转交</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn3"></a></td>
					</tr>
				</table>
			    </div>
			  <br>
			  
		     <input type="hidden" id="taskCount"value="<c:out value="${taskCount}"/>">
					
			<div id ="wfdocgrid"  class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=50% style="background:#9FC2E5;" class="innerTable" align=center>
					<tr bgcolor="#EFF0F2">
						<th width="25%">下一步骤（<font color="red">勾选需转交步骤</font>）
						</th>
						<th width="15%">主办人（<font
							color="red">不能为空</font>）</td>
						<!-- th width="15%">协办人（<font color="red">可以为空</font>） -->
						</th>
						<th width="15%">计划完成时间</th>
						<c:if   test="${wfRd.flowId==40||wfRd.flowId==42}">
						<th width="15%">选择已定人员</th>
						</c:if>
						<%-- <input  id="wfRd.wfNo" name="wfRd.wfNo"
						value="<c:out value="${wfRd.wfNo}"/>"> 多了一个 --%>
					</tr>



					<!-- c:set var="n" value="0" / -->
					<c:set var="i" value="0" />
					
					<c:forEach items="${nextSteps}" var="step" varStatus="vs">
						<tr bgcolor="#FFFFFF" height="35">
						
							<!-- 下一步骤 --> 
							<td>
							
							    <c:choose>
							     
							       <c:when test="${step.stepType==2}">
									   <input type="Checkbox" id="nextSteps[<c:out value="${i}"/>].stepId" 
									      name="nextSteps[<c:out value="${i}"/>].stepId" value="<c:out value="${step.stepId}"/>"
										<c:if test="${vs.last==true && vs.count==1 && currentTask.isLastStep!=1}">checked</c:if>
										onclick="selectStep('<c:out value="${i}"/>','<c:out value="${step.stepType}"/>')" >
							      </c:when>
							      
							      <c:when test="${step.stepType==3 || step.stepType==1}">
									   <input type="Checkbox" id="nextSteps[<c:out value="${i}"/>].stepId" 
									      name="nextSteps[<c:out value="${i}"/>].stepId" value="<c:out value="${step.stepId}"/>"
										<c:if test="${vs.last==true && vs.count==1 && currentTask.isLastStep!=1}">checked</c:if>
										onclick="selectStep('<c:out value="${i}"/>','<c:out value="${step.stepType}"/>')" checked="checked">
							      </c:when>
							    </c:choose>
								
								<c:if test="${currentTask.sort>step.sort}">
									<font style="color: red">退回至：</font>
								</c:if> <font style="color: blue;"> <c:out
										value="${step.stepName}" />&nbsp;&nbsp;
							</font> <c:if test="${step.stepType==1}">&nbsp;</c:if> <c:if
									test="${step.stepType==2}">(分支)</c:if> <c:if
									test="${step.stepType==3}">(并发)</c:if>
						    </td>
						    <!-- 下一步骤 --> 


                            <!-- 主办人 -->  
							<td>
							<%-- <c:out value="${i}"/> --%>
								 <input type="hidden" id="userId<c:out value="${i}"/>"value="<c:out value="${step.idText}"/>"> 
								 <b id="task<c:out value="${i}"/>"><c:out value="${step.taskText}" escapeXml="false" /></b>
								 <input type="text"  autoComplete="Off"
									id="userName<c:out value="${i}"/>"
									value="<c:out value="${step.idText}"/>"
									onkeydown="enterTips(<c:out value="${i}"/>)"
									onkeyup="showtips2(<c:out value="${i}"/>);if(event.keyCode==27)c();">
								<br><select id="sel<c:out value="${i}"/>" style="width: 140px; position: absolute; z-index: 1000; display: none"
									 onkeydown="if(event.keyCode==13)rv(<c:out value="${i}"/>)"></select> 
								<c:set var="i" value="${i+1}" /> 
							</td>
							 <!-- 主办人 -->  



                          <!-- 
							<td id="xtd<c:out value="${n}"/>">
								<input type="hidden" id="userId<c:out value="${i}"/>" value="<c:out value="${step.idText2}"/>">
								<b id="task<c:out value="${i}"/>"><c:out value="${step.taskText2}" escapeXml="true" /></b>
								<input type="text"  autoComplete="Off" id="xbipt<c:out value="${n}"/>" onkeyup="showxbSel(<c:out value="${n}"/>)" onkeydown="toxbSelFocus(<c:out value="${n}"/>)"><br>
								<select id="xbsel<c:out value="${n}"/>" style="width: 140px; position: absolute; z-index: 1000; display: none" onkeydown="if(event.keyCode==13)setxbV(<c:out value="${n}"/>)">
								</select>
								<c:set var="i" value="${i+1}"/> 
							</td>
						协办人 -->  


                            <!-- 计划完成时间 -->  
							<td>
								<input type="text"
											name="nextSteps[<c:out value="${i}"/>].taskTime"
											id="date<c:out value="${i}"/>"
											style="width: 100px; height: 17px;" readOnly
											value="<fmt:formatDate value="${currentDate}"/>"
											onClick="WdatePicker({el:'date<c:out value="${i}"/>'});" />
							</td>
						   <!-- 计划完成时间 -->  
						  
						   <c:if   test="${wfRd.flowId==40||wfRd.flowId==42}">
							<td>
						<%-- 	<c:out value="${i}"/> --%>
								<input type="button" value="选择项目组成员" onclick="selectPrjtUsers(this);"></input>
							</td>
							</c:if>
							
						</tr>
						<!--c:set var="n" value="${n+1}" /  -->
					</c:forEach>
					<!-- input type="hidden" id="n" value="<c:out value="${n}"/>" -->
				</table>
				
				</div>
				<br>
			</c:if>
			<!-- 转交界面 -->
			<table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0 width="50%" align=center>
				<tr>
					<td align="right">
						<input id="closeBtn" type="button" value="确认完成" class="wfbigbtn"   onclick="sendTask();" />
						<!-- input id="closeBtn" type="button" value=" 返回上一页" class="wfbigbtn"   onclick="setAgentBy();" / -->
					</td>
			 </table>



			<!-- 邮件
			<IMG align=top src="./include/img/workflow/green_arrow.gif"> <SPAN
				class=big3> <B>邮件阅知：</B></SPAN>&nbsp; <br> <br> <input
				type="hidden" id="mail.to" name="mail.to"
				value='<c:out value="${mailName}"/>'> <input type="hidden"
				id="mail.cc" name="mail.cc" value="">
			<table style="BORDER-COLLAPSE: collapse" class=small border=1
				cellSpacing=0 borderColor=#b8d1e2 cellPadding=3 width="72%"
				align=center>
				<tr class=TableHeader>
					<td colspan="4">阅知人选</td>
				</tr>
				<tr bgcolor="#ffffff">
					<td width="10%" align="center">提示：</td>
					<td><font color="red">主办人及协办人不用阅知</font>(系统会自动发邮件)</td>
				</tr>
				<tr bgcolor="#ffffff">
					<td width="10%" align="center"><input type="button"
						class="wfbtn" value=" 选择收件人 "
						onclick="openPop('MailToAdd.shtml');"></td>
					<td>
						<table style="BORDER-COLLAPSE: collapse" class=small border=1
							cellSpacing=0 borderColor=gray bgcolor="#F0F0F0" cellPadding=3
							width="600">
							<tr height="22">
								<td id="to"><c:if test="${empty mailName}">&nbsp;</c:if> <c:out
										value="${mailName}" /></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="#ffffff">
					<td width="10%" align="center">抄送人：</td>
					<td>
						<table style="BORDER-COLLAPSE: collapse" class=small border=1
							cellSpacing=0 borderColor=gray bgcolor="#F0F0F0" cellPadding=3
							width="600">
							<tr height="22">
								<td id="cc">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		 邮件 -->

			<!--
<br>
<br>
<div class="botBtn" style="text-align: left;">
	<input type="button" value="<<返回上一页" class="wfbigbtn"  onclick="backJobPage();">
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="  确认完成  " class="wfbigbtn"  onclick="sendTask();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
-->
		</form>
		<br>

		<%@ include file="mailpop.jsp"%>
	</div>

</body>
</html>