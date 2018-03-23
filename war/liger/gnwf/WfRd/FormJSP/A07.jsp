<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  

<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>SAP系统帐号注销申请</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align=center>
    <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">用户信息(Employee Information)</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="40" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>帐号</td>
	<td colspan="20" width="20%">
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E1.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>姓名</td>
    <td colspan="17" width="17%">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E2.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>联系方式</td>
    <td colspan="17" width="17%">
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E3.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="50" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>注销理由</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E4.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">信息审核</td>
  </tr>
   <tr>
    <td colspan="16" width="16%" rowspan="2" align="center" bgcolor="#F0F0F0">部门经理审核</td>
    <td colspan="84" width="84%">&nbsp;
    	<c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
		  			<c:if test="${E5.fieldText == 1 || empty E5.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
		  			<c:if test="${E5.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E5.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E5.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="50">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>备注：</td>
			</tr>
			<tr>
				<td height="70">
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E6.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
				</td>
			</tr>
			<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${EE3.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${EE3.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${EE3.count}"/>].fieldText" 
						 <c:if test="${!empty EE3.fieldText}">value="<c:out value="${EE3.fieldText}"/>"</c:if>
						 <c:if test="${empty EE3.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${EE3.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
			</tr>
		</table>
	</td>
  </tr>
  
  
  <!-- 
   <tr>
    <td colspan="16" width="16%" rowspan="2" align="center" bgcolor="#F0F0F0">财务总监审核</td>
    <td colspan="84" width="84%">&nbsp;
    	<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="1" 
		  			<c:if test="${E7.fieldText == 1 || empty E7.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="2" 
		  			<c:if test="${E7.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E7.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E7.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="50">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>备注：</td>
			</tr>
			<tr>
				<td height="70">
		<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E8.fieldText}"/>
			</c:otherwise>
		</c:choose>
				</td>
			</tr>
			<tr>
		</table>
	</td>
  </tr>
  -->
  
  
  <tr>
    <td colspan="16" width="16%" rowspan="2" align="center" bgcolor="#F0F0F0">SAP负责人审核</td>
    <td colspan="84" width="84%">&nbsp;
    	<c:choose>
			<c:when test="${EE1.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${EE1.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
		  			<c:if test="${EE1.fieldText == 1 || empty EE1.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${EE1.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
		  			<c:if test="${EE1.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${EE1.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${EE1.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="50">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>备注：</td>
			</tr>
			<tr>
				<td height="70">
		<c:choose>
			<c:when test="${EE2.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${EE2.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${EE2.count}"/>].fieldText"><c:out value="${EE2.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${EE2.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
				</td>
			</tr>
						<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${EE4.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${EE4.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${EE4.count}"/>].fieldText" 
						 <c:if test="${!empty EE4.fieldText}">value="<c:out value="${EE4.fieldText}"/>"</c:if>
						 <c:if test="${empty EE4.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${EE4.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
			</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">账号维护信息(由SAP维护工程师填写)</td>
  </tr>
  <tr>
	<td colspan="16" width="16%" rowspan="2" height="70" align="center" bgcolor="#F0F0F0">信息中心维护</td>
    <td colspan="84" width="84%">处理结果：
    	<c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="1" 
		  			<c:if test="${E9.fieldText == 1 || empty E9.fieldText}">checked</c:if> />注销成功&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="2" 
		  			<c:if test="${E9.fieldText == 2}">checked</c:if> />注销失败&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E9.fieldText == 1}">checked</c:if> />注销成功&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E9.fieldText == 2}">checked</c:if> />注销失败&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>回复内容：</td>
			</tr>
			<tr>
				<td height="50" >
		<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E10.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
</table>