<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>

<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>供应商信息签核</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align=center>
  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">供应商签核表</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">
    	<!--<c:if test="${AA.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if><c:out value="${AA.fieldName}"/>-->
		输入框1
	</td>
	<td colspan="84" width="84%">&nbsp;
		<c:choose>
			<c:when test="${EEE1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" value="<c:out value="${EEE1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${EEE1.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">
		输入框2---仅有下划线
	</td>
	<td colspan="84" width="84%">&nbsp;
		<c:choose>
			<c:when test="${EEE1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" value="<c:out value="${EEE1.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${EEE1.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
    <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">
		输入框2---仅有下划线--自带系统签名
	</td>
	<td colspan="84" width="84%">&nbsp;
			<c:choose>
				<c:when test="${EEE1.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
						 <c:if test="${!empty EEE1.fieldText}">value="<c:out value="${EEE1.fieldText}"/>"</c:if>
						 <c:if test="${empty EEE1.fieldText}">value="<c:out value="${currentUserName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${EEE1.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
	</td>
  </tr>
  
  
    <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">
		输入框2---仅有下划线--自带当天日期
	</td>
	<td colspan="84" width="84%">&nbsp;
			<c:choose>
				<c:when test="${EEE1.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
  						<c:if test="${!empty EEE1.fieldText}">value="<c:out value="${EEE1.fieldText}"/>"</c:if>
						<c:if test="${empty EEE1.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${EEE1.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">
		单选框Radio
	</td>
	<td colspan="20" width="20%">
		<c:choose>
			<c:when test="${EEE1.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" value="1" 
		  			<c:if test="${EEE1.fieldText == 1 || empty EEE1.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" value="2" 
		  			<c:if test="${EEE1.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${EEE1.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${EEE1.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:otherwise>
		</c:choose>
	 </td>
	 
	 
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">
		多选checkbox
	</td>
    <td colspan="17" width="17%">
		<c:choose>
			<c:when test="${EEE1.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${EEE1.fieldText == 1}">checked</c:if> />采购
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${EEE1.fieldText == 1}">checked</c:if> />采购
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">
		下拉框
	</td>
    <td colspan="17" width="17%">
		<c:choose>
			<c:when test="${EEE1.isEdit == '1'}">
				<select name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText">
					<option value="1" <c:if test="${EEE1.fieldText == 1}">selected</c:if>>111</option>
					<option value="2" <c:if test="${EEE1.fieldText == 2}">selected</c:if>>222</option>
				</select>
			</c:when>
			<c:otherwise>
				<select disabled="disabled">
					<option value="1" <c:if test="${EEE1.fieldText == 1}">selected</c:if>>111</option>
					<option value="2" <c:if test="${EEE1.fieldText == 2}">selected</c:if>>222</option>
				</select>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="60" align="center" bgcolor="#F0F0F0">
		Textarea输入框
	</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${EEE1.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText"><c:out value="${EEE1.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${EEE1.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
</table>