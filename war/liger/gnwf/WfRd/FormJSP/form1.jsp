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
    	<c:if test="${AA.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${AA.fieldName}"/>
	</td>
	<td colspan="84" width="84%">&nbsp;
		<c:choose>
			<c:when test="${AA.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${AA.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${AA.count}"/>].fieldText" value="<c:out value="${AA.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${AA.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">
		<c:if test="${BB.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${BB.fieldName}"/>
	</td>
	<td colspan="20" width="20%">
		<c:choose>
			<c:when test="${BB.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${BB.count}"/>].fieldText" value="1" 
		  			<c:if test="${BB.fieldText == 1 || empty BB.fieldText}">checked</c:if> />是&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${BB.count}"/>].fieldText" value="2" 
		  			<c:if test="${BB.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${BB.fieldText == 1}">checked</c:if> />是&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${BB.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:otherwise>
		</c:choose>
	 </td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">
		<c:if test="${CC.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${CC.fieldName}"/>
	</td>
    <td colspan="17" width="17%">
		<c:choose>
			<c:when test="${CC.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${CC.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${CC.fieldText == 1}">checked</c:if> />采购
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${CC.fieldText == 1}">checked</c:if> />采购
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">
		<c:if test="${DD.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${DD.fieldName}"/>
	</td>
    <td colspan="17" width="17%">下拉框
		<c:choose>
			<c:when test="${DD.isEdit == '1'}">
				<select name="fieldContents[<c:out value="${DD.count}"/>].fieldText">
					<option value="1" <c:if test="${DD.fieldText == 1}">selected</c:if>>111</option>
					<option value="2" <c:if test="${DD.fieldText == 2}">selected</c:if>>222</option>
				</select>
			</c:when>
			<c:otherwise>
				<select disabled="disabled">
					<option value="1" <c:if test="${DD.fieldText == 1}">selected</c:if>>111</option>
					<option value="2" <c:if test="${DD.fieldText == 2}">selected</c:if>>222</option>
				</select>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="60" align="center" bgcolor="#F0F0F0">
		<c:if test="${EE.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${EE.fieldName}"/>
	</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${EE.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${EE.count}"/>].fieldText" name="fieldContents[<c:out value="${EE.count}"/>].fieldText"><c:out value="${EE.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${EE.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
  	<td colspan="16" width="16%" height="60" align="center" bgcolor="#F0F0F0">
		<c:if test="${FF.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${FF.fieldName}"/>
	</td>
  	<td colspan="20" width="20%">
  		<c:choose>
			<c:when test="${FF.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${FF.count}"/>].fieldText" name="fieldContents[<c:out value="${FF.count}"/>].fieldText" value="<c:out value="${FF.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${FF.fieldText}"/>
			</c:otherwise>
		</c:choose>
  	</td>
  	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">
		<c:if test="${GG.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${GG.fieldName}"/>
	</td>
  	<td colspan="17" width="17%">
  		<c:choose>
			<c:when test="${GG.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${GG.count}"/>].fieldText" name="fieldContents[<c:out value="${GG.count}"/>].fieldText" value="<c:out value="${GG.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${GG.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">
		<c:if test="${HH.isNull == 'notNull'}"><font color="#FF0000">*</font></c:if>
		<c:out value="${HH.fieldName}"/>
	</td>
  	<td colspan="17" width="17%">
  		<c:choose>
			<c:when test="${HH.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${HH.count}"/>].fieldText" name="fieldContents[<c:out value="${HH.count}"/>].fieldText" value="<c:out value="${HH.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${HH.fieldText}"/>
			</c:otherwise>
		</c:choose>
  	</td>
  </tr>
</table>