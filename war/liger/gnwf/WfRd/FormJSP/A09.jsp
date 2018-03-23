<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>ABAP开发申请</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align=center>
  <tr>
	<td colspan="100" width="100%" height="40" bgcolor="#F0F0F0" align="center"><p align="center">开发申请表</p></td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center" height="80" bgcolor="#F0F0F0">开发需求描述</td>
    <td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E1.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  <!-- 
    <tr>
    <td colspan="16" width="16%" rowspan="2" align="center" bgcolor="#F0F0F0">模组组长</td>
    <td colspan="84" width="84%">&nbsp;
		<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="1" 
		  			<c:if test="${E4.fieldText == 1 || empty E4.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="2" 
		  			<c:if test="${E4.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E4.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E4.fieldText == 2}">checked</c:if> />不同意&nbsp;
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
			<c:when test="${E5.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E5.fieldText}"/>
			</c:otherwise>
		</c:choose>
				</td>
			</tr>
			<tr>
		</table>
	</td>
  </tr>
  
  
  
      <tr>
    <td colspan="16" width="16%" rowspan="2" align="center" bgcolor="#F0F0F0">SAP主管</td>
    <td colspan="84" width="84%">&nbsp;
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="1" 
		  			<c:if test="${E6.fieldText == 1 || empty E6.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="2" 
		  			<c:if test="${E6.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E6.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E6.fieldText == 2}">checked</c:if> />不同意&nbsp;
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
			<c:when test="${E7.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E7.fieldText}"/>
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
    <td colspan="16" width="16%" rowspan="2" align="center" bgcolor="#F0F0F0">ABAP组长审核</td>
    <td colspan="84" width="84%">&nbsp;
		<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
		  			<c:if test="${E8.fieldText == 1 || empty E8.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
		  			<c:if test="${E8.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E8.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E8.fieldText == 2}">checked</c:if> />不同意&nbsp;
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
			<c:when test="${E9.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E9.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
				</td>
			</tr>
			<tr>
		</table>
	</td>
  </tr>
  
  
  
  
      <tr>
    <td colspan="16" width="16%" align="center" height="80" bgcolor="#F0F0F0">ABAP开发人员</td>
    <td colspan="84" width="84%"  height="50">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>解决方案：</td>
			</tr>
			<tr>
				<td height="80">
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
			<tr>
		</table>
	</td>
  </tr>
</table>
