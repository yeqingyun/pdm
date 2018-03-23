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

<script src="./include/js/common.js" type="text/javascript"></script>
<script src="./include/js/gnwf/wfStep.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfStep'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	check();
	/**
	if ($("#flowId").length > 0)
		$("#flowId").ligerComboBox();
	if ($("#stepType").length > 0)
		$("#stepType").ligerComboBox();
	if ($("#sort").length > 0)
		$("#sort").ligerTextBox();
	if ($("#isAuto").length > 0)
		$("#isAuto").ligerComboBox();
	if ($("#isUpdForm").length > 0)
		$("#isUpdForm").ligerComboBox();
	if ($("#isSysFinsh").length > 0)
		$("#isSysFinsh").ligerComboBox();
	if ($("#timeQty").length > 0)
		$("#timeQty").ligerTextBox();
	if ($("#isLastStep").length > 0)
		$("#isLastStep").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#waitAuxiliary").length > 0)
		$("#waitAuxiliary").ligerComboBox();
	if ($("#stepName").length > 0)
		$("#stepName").ligerTextBox();
	if ($("#stepDesc").length > 0)
		$("#stepDesc").ligerTextBox();
	if ($("#stepUri").length > 0)
		$("#stepUri").ligerTextBox();
	if ($("#roleId").length > 0)
		$("#roleId").ligerComboBox();
	**/
});
</script>

<script type="text/javascript">
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
 	    document.getElementById("acceptBy").value = sel.options[sel.selectedIndex].value;
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


</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<input type="hidden" id="stepId" name="wfStep.stepId" size="30" value="<c:out value="${wfStep.stepId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">工作流定义ID：</td>
			<td>
				<select id="flowId" name="wfStep.flowId">
					<option value="-1" selected>请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${wfStep.flowId==cfg.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">是否系统自动驱动：</td>
			<td>
				<select id="isSysStartUp" name="wfStep.isSysStartUp" style="width: 200px;">
					<option value="0" <c:if test="${wfStep.isSysStartUp==0}">selected</c:if>>否</option>
					<option value="1" <c:if test="${wfStep.isSysStartUp==1}">selected</c:if>>是</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">排序：</td><td><input type="text" id="sort" name="wfStep.sort" size="30" validate="{required:true}" value="<c:out value="${wfStep.sort}"/>"/></td>
			<td height="24" width="90" align="center">是否自动流转：</td>
			<td>
				<select id="isAuto" name="wfStep.isAuto" style="width: 200px;">
					<option value="1" <c:if test="${wfStep.isAuto==1}">selected</c:if>>是</option>
					<option value="0" <c:if test="${wfStep.isAuto==0}">selected</c:if>>否</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">协办是否可以更改表单：</td>
			<td>
				<select id="isUpdForm" name="wfStep.isUpdForm" style="width: 200px;">
					<option value="0" <c:if test="${wfStep.isUpdForm==0}">selected</c:if>>否</option>
					<option value="1" <c:if test="${wfStep.isUpdForm==1}">selected</c:if>>是</option>
				</select>
			</td>
			<td height="24" width="90" align="center">超时系统是否自动完成：</td>
			<td>
				<select id="isSysFinsh" name="wfStep.isSysFinsh" style="width: 200px;">
					<option value="0" <c:if test="${wfStep.isSysFinsh==0}">selected</c:if>>否</option>
					<option value="1" <c:if test="${wfStep.isSysFinsh==1}">selected</c:if>>是</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">计划天数：</td>
			<td>
				<select id="timeQty" name="wfStep.timeQty" style="width: 200px;">
					<option value="1" <c:if test="${wfStep.timeQty==1}">selected</c:if>>1天</option>
					<option value="2" <c:if test="${wfStep.timeQty==2}">selected</c:if>>2天</option>
					<option value="3" <c:if test="${wfStep.timeQty==3}">selected</c:if>>3天</option>
					<option value="4" <c:if test="${wfStep.timeQty==4}">selected</c:if>>4天</option>
					<option value="5" <c:if test="${wfStep.timeQty==5}">selected</c:if>>5天</option>
					<option value="6" <c:if test="${wfStep.timeQty==6}">selected</c:if>>6天</option>
					<option value="7" <c:if test="${wfStep.timeQty==7}">selected</c:if>>7天</option>
					<option value="8" <c:if test="${wfStep.timeQty==8}">selected</c:if>>8天</option>
					<option value="9" <c:if test="${wfStep.timeQty==9}">selected</c:if>>9天</option>
					<option value="10" <c:if test="${wfStep.timeQty==10}">selected</c:if>>10天</option>
					<option value="11" <c:if test="${wfStep.timeQty==11}">selected</c:if>>11天</option>
					<option value="12" <c:if test="${wfStep.timeQty==12}">selected</c:if>>12天</option>
					<option value="13" <c:if test="${wfStep.timeQty==13}">selected</c:if>>13天</option>
					<option value="14" <c:if test="${wfStep.timeQty==14}">selected</c:if>>14天</option>
				</select>
			</td>
			<td height="24" width="90" align="center">步骤类型：</td>
			<td>
				<select id="stepType" name="wfStep.stepType">
					<option value="1" <c:if test="${wfStep.stepType==1}">selected</c:if>>普通</option>
					<option value="2" <c:if test="${wfStep.stepType==2}">selected</c:if>>分支</option>
					<option value="3" <c:if test="${wfStep.stepType==3}">selected</c:if>>并发</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">最后步骤：</td>
			<td>
				<select id="isLastStep" name="wfStep.isLastStep" style="width: 200px;">
					<option value="1" <c:if test="${wfStep.isLastStep==1}">selected</c:if>>是</option>
					<option value="0" <c:if test="${wfStep.isLastStep==0}">selected</c:if>>否</option>
				</select>
			</td>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<select id="status" name="wfStep.status" style="width: 200px;">
					<option value="1" <c:if test="${wfStep.status==1}">selected</c:if>>有效</option>
					<option value="0" <c:if test="${wfStep.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">步骤名称：</td><td><input type="text" id="stepName" name="wfStep.stepName" size="30" validate="{required:true}" value="<c:out value="${wfStep.stepName}"/>"/></td>
			<td height="24" width="90" align="center">步骤描述：</td><td><input type="text" id="stepDesc" name="wfStep.stepDesc" size="30"  value="<c:out value="${wfStep.stepDesc}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">主办是否必须等待协办：</td>
			<td>
				<select id="waitAuxiliary" name="wfStep.waitAuxiliary" style="width: 200px;">
					<option value="0" <c:if test="${wfStep.waitAuxiliary==0}">selected</c:if>>否</option>
					<option value="1" <c:if test="${wfStep.waitAuxiliary==1}">selected</c:if>>是</option>
				</select>
			</td>
			<td height="24" width="90" align="center">本步骤对应页面：</td>
			<td><input type="text" id="stepUri" name="wfStep.stepUri" size="30" value="<c:out value="${wfStep.stepUri}"/>"/></td>
		</tr>
		
		<tr>
			<td>下一步骤</td>
			<td colspan="3">
				<c:set var="n" value="1"/>
				<c:forEach items="${nextSteps}" var="step">
					 <input type="Checkbox" name="wfStepNexts"
						value="<c:out value="${step.stepId}"/>"  <c:out value="${step.isChecked}"/>
						<c:if test="${step.stepId == wfStep.stepId}">disabled="disabled"</c:if>>
						
						<c:out value="${step.sort}"/>--<c:out value="${step.stepName}"/>
					<c:if test="${n%4==0}"><br></c:if>
					<c:set var="n" value="${n+1}"/>
				</c:forEach>
			</td>
		</tr>
		
		<tr>
		<td>主导人</td>
		<td colspan="3">
			<table width="100%">
			<tr>
				<td>
					 <input type="hidden" id="acceptBy" name="acceptBy" > 
					 <input type="text"  autoComplete="Off"
						id="userName"
						onkeydown="enterTips()"
						onkeyup="showtips2();if(event.keyCode==27)c();">
					<select id="sel" onkeydown="if(event.keyCode==13)rv()" style="width: 140px; position: absolute; z-index: 1000; display: none"></select>
				    <input type="button" class="btn" value="添加" onclick="addUser()"/>
					
				</td>
				
				<td>
					<select id="roleId" name="roleId">
						<option value="-1">请选择</option>
						<c:forEach items="${roleList}" var="role">
							<option value="<c:out value="${role.roleId}"/>"><c:out value="${role.roleNm}"/></option>
						</c:forEach>
					</select>
					<input type="button" class="btn" value="添加" onclick="addRole()">
				</td>
			</tr>
			<tr>
				<td colspan="3" bgcolor="#ffffff">
					<c:set var="countI" value="0"/>
					<table id="leadertable" width="60%" cellpadding="4" cellspacing="1" bgcolor="#D3E1F1">
						<tr bgcolor="#EFF0F2">
							<td>可选人/项目角色</td>
							<td>主办/协办</td>
							<td width="30%">操作</td>
						</tr>
							<c:forEach items="${userList}" var="sUser">
								<tr id="idRow<c:out value='${countI}'/>" bgcolor="#FFFFFF"  onmouseout="this.style.backgroundColor='#FFFFFF';">
									<td>
										<c:if test="${!empty sUser.userId && sUser.userId!=0}"> 
											<input type="hidden" id="<c:out value='${countI}'/>" name="userList" value="<c:out value='${sUser.userId}'/>">
											<c:out value="${sUser.userName}"/>
										</c:if>
										<c:if test="${empty sUser.userId || sUser.userId==0}">
											<input type="hidden" id="<c:out value='${countI}'/>" name="roleList" value="<c:out value='${sUser.prjtRoleId}'/>">
											<c:out value="${sUser.prjtRoleName}"/>
										</c:if>
									</td>
									<td>
											<input type="radio" name="userType<c:out value='${countI}'/>" value="1" 
												<c:if test="${sUser.userType==1}">checked</c:if>>主办&nbsp;&nbsp;
											<input type="radio" name="userType<c:out value='${countI}'/>" value="2" 
												<c:if test="${sUser.userType==2}">checked</c:if>>协办
									</td>
									<td>
										<a href="javascript:;" onclick="delUser('leadertable','idRow<c:out value='${countI}'/>')"><font color="red">删除</font></a>
									</td>
								</tr>
								<c:set var="countI" value="${countI+1}"/>
							</c:forEach>
					</table>
				</td>
			</tr>
			</table>
		</td>
	</tr>

	</table>
</form>

</div>

</body>
</html>