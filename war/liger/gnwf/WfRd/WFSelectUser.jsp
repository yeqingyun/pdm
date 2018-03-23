<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<style type="text/css">
	body{margin:0;}
</style>
<link rel="stylesheet" href="./include/css/ifrm.css" type="text/css"/>
<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
<script language="JavaScript" src="./include/js/gnwf/WFSelectUser.js"></script>
<script language="JavaScript" src="./include/js/common.js"></script>
<script type="text/javascript">
$(function(){
	//setChgDept('comId','deptId');		
}); 
</script>
</head>
<body>

<div style="width:800px;" class="popup-html">

<input type="hidden" id="actUri" value="UserToAdd">
<input type="hidden" id="addUri" value="UserToAdd">
<input type="hidden" id="taskType" value="<c:out value="${taskType}"/>">
<input type="hidden" id="stepId" value="<c:out value="${stepId}"/>">
<input type="hidden" id="count" value="<c:out value="${count}"/>">

<table align="center" width="100%" cellpadding="4" cellspacing="1" bgcolor="#dcdcdc">
	<tr bgcolor="#ffffff">
		<td width="18%" align="center" colspan="2">
			<input type="radio" name="userfrom" value="0" checked onclick="selectFromDept();"
				<c:if test="${!empty constantUsers}">disabled</c:if>/>从公司选&nbsp;&nbsp;&nbsp;
		  	<input type="radio" id="wfinfo" name="userfrom" value="1" onclick="selectFromWfinfo();" 
		  		<c:if test="${!empty constantUsers || empty wfinfoUsers}">disabled</c:if>/>本流程涉及人员&nbsp;&nbsp;&nbsp;
		  	<input type="radio" name="userfrom" value="2" onclick="selectFromLeader();" 
		  		<c:if test="${!empty constantUsers || empty leaderUsers}">disabled</c:if>/>本部门领导&nbsp;
		  	<input type="radio" name="userfrom" value="3" onclick="selectFromProject();" 
		  		<c:if test="${!empty constantUsers || empty projectUsers}">disabled</c:if>/>从项目组选&nbsp;
		</td>
		<!-- 
		<td width="18%" align="center">
			input type="text" id="seaText" name="seaText" size="18" value="">
			<input type="button" value="查找" /> 
		</td>
		-->
		<td width="8%"></td>
		<td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		任务接收人
		<!-- 
		<input type="text" id="date1"  style="width: 176px;" value="<fmt:formatDate value="${currentDate}"/>"   readOnly onClick="WdatePicker({el:'date1'});"/>
		日内完成
		 -->
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td height="280" valign="top" align="center">
			<table width="100%" border="0">
			<tr>
			<td height="25" align="left">
				<!-- background-color:#F0F0F0 -->
				<font color="blue">公司:</font>
				<select id="comId" name="comId" style="width:180px;"  
				<c:if test="${!empty constantUsers}">disabled</c:if> onchange="setChgDept('comId','deptId');">
					<c:forEach items="${baComs}" var="baCom">
						<option value="<c:out value="${baCom.comId}"/>"><c:out value="${baCom.comNm}"/></option>
					</c:forEach>
				</select>
			</td>
			</tr>
			<tr>
			<td align="left">
				<br>
				<font color="blue">部门:</font>
				<select id="deptId" name="deptId" style="width:180px;" multiple size=15   
				<c:if test="${!empty constantUsers}">disabled</c:if> onchange="setChgUser('deptId','mailAddr');">
					<option value="-1">请选择</option>
					<c:forEach items="${baDepts}" var="baDept">
						<option value="<c:out value="${baDept.deptId}"/>">
							<c:out value="${baDept.deptNm}"/>
						</option>
					</c:forEach>
				</select>
			</td>
			</tr>
			</table>
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
							<option value="<c:out value="${u.id}"/>" <c:if test="${s.count==1}">selected</c:if>>
								<c:out value="${u.usrName}"/>
							</option>
						</c:forEach>
					</select>
				</c:if>
			</td>
			</tr>
			</table>
		</td>
		
		<td valign="top" align="center">
			<input type="button" value="添加->" onclick="ckaddto('mailAddr','to')"/><br><br>
			<input type="button" value="<-删除" onclick="ckdelopt('to')"/>
		</td>
		<td valign="top" align="center">
			<select id="to" name="to" style="width:180px" multiple size=20></select>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<td align="center" colspan=4>
			<input type="button" value="确 定" onclick="retnPop('userId<c:out value="${count}"/>','userName<c:out value="${count}"/>','task<c:out value="${count}"/>');"/>
			&nbsp;&nbsp;&nbsp;
			<input type="button" value="取 消" onclick="closePop();"/>
		</td>
	</tr>
</table>

<!--<input type="hidden" id="wfinfoSelect" value='<option value=1>请选择1111</option>' >-->
<input type="hidden" id="wfinfoSelect" value="<c:out value="${wfinfoValue}"/>" >
<input type="hidden" id="leaderSelect" value="<c:out value="${leaderValue}"/>" >
<input type="hidden" id="projectSelect" value="<c:out value="${projectValue}"/>" >

<script type="text/javascript">
getPop('userId<c:out value="${count}"/>','userName<c:out value="${count}"/>');
</script>
 
</div>
</body>
</html>
