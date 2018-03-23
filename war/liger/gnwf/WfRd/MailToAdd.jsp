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
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
<script language="JavaScript" src="./include/js/gnwf/MailTo.js"></script>
<script language="JavaScript" src="./include/js/common.js"></script>
<script type="text/javascript">
$(function(){
	setChgDept('comId','deptId');
}); 
</script>
</head>
<body>

<div style="width:710px;" class="popup-html frmInsetCon">
<input type="hidden" id="actUri" value="MailToAdd">
<input type="hidden" id="addUri" value="MailToAdd">

<table cellpadding=0 cellspacing=0 width=95%  class="inputTable">
<tr>
		<td width="18%" align="center">
			<select id="comId" name="comId" style="width:180px"
				onchange="setChgDept('comId','deptId');">
				<option value="-1">请选择公司</option>
				<c:forEach items="${baComs}" var="baCom">
					<option value="<c:out value="${baCom.comId}"/>"><c:out value="${baCom.comNm}"/></option>
				</c:forEach>
			</select>
		</td>
		<td width="18%" align="center">
			<!-- input type="text" id="seaText" name="seaText" size="18" value="">
			<input type="button" value="查找" /> -->
		</td>
		<td width="8%"></td>
		<td width="20%">收件（抄送）人</td>
	</tr>
	<tr>
		<td height="280" valign="top" align="center">
			<select id="deptId" name="deptId" style="width:180px" multiple size=20 onchange="chgbook('deptId','mailAddr');">
				
			</select>
		</td>
		<td valign="top" align="center">
			<select id="mailAddr" name="mailAddr" style="width:180px" multiple size=20></select>       
		</td>
		<td valign="top" align="center">
			<br>
			<input type="button" value="收件人->" onclick="ckaddto('mailAddr','to')"/><br>
			<input type="button" value="<-收件人" onclick="ckdelopt('to')"/><br>
			<br><br><br>
			<br><br><br>
			<br>
			<input type="button" value="  抄送->"  onclick="ckaddto('mailAddr','cc')"/><br>
			<input type="button" value="<-抄送  "  onclick="ckdelopt('cc')"/><br>
		</td>
		<td valign="top" align="center">
			<select id="to" name="to" style="width:180px" multiple size=10></select>
			<br>
			<select id="cc" name="cc" style="width:180px" multiple size=9></select>     
		</td>
	</tr>
	<tr>
		<td align="center" colspan=4>
			<input type="button" value="确 定" onclick="retnPop();"/>
			<input type="button" value="取 消" onclick="closePop();"/>
		</td>
	</tr>
</table>

<script type="text/javascript">
getPop();
</script>
</div>
</body>
</html>
