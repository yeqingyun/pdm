<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>

<script language="JavaScript" src="./include/js/gnoa/WfRd.js"></script>

<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>供应商评审</strong></p>

<!-- fieldText 没有ID 则可以为空  -->
<table id="OwnershipStructure" width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align=center>
  <tr>
    <td colspan="100" width="100%" height="30">
		<table border="0" width="100%">
			<tr>
				<td colspan="15" width="15%">&nbsp;</td>
			    <td colspan="70" width="70%"  align="center" >供应商评审信息</td>
			    <td colspan="15" width="15%"  align="right">
					<input type="button" value="增加" onclick="AddRow();">&nbsp;&nbsp;
					<input type="button" value="删除" onclick="DelRow('numI1','StrRight');">
				</td>
			</tr>
		</table>
	</td>
  </tr>
  
  <tr>
  	<td colspan="16" width="16%" height="40" align="center">单选</td>
  	<td colspan="20" width="20%">
		<c:choose>
			<c:when test="${BB.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${BB.count}"/>].fieldText" value="1" 
		  			<c:if test="${BB.fieldText == 1}">checked</c:if> />是&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${BB.count}"/>].fieldText" value="2" 
		  			<c:if test="${BB.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${BB.fieldText == 1}">checked</c:if> />是&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${BB.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:otherwise>
		</c:choose>
  	</td>
  	<td colspan="15" width="15%" align="center">多选</td>
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
  	<td colspan="15" width="15%" align="center">下拉框</td>
  	<td colspan="17" width="17%">
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
  
  

    <tr id="StrRight">
		<td colspan="30" height="30">扩展字段XX</td>
		<td colspan="30">扩展字段YY</td>
		<td colspan="40">扩展字段ZZ</td>
	</tr>

	<c:set var="rownum" value="0"/>
	<c:if test="${empty rows}">
		<c:set var="rownum" value="${rownum + 1}"/>
		<tr height="25">
			<td colspan="30">
				<input type="hidden" name="fieldContents[<c:out value="${XX.count}"/>].rowId" value="1">
				<input type="Text" size="20" style="display: none;" name="fieldContents[<c:out value="${XX.count}"/>].fieldText" value="<c:out value="${XX.fieldText}"/>">
			</td>
			<td colspan="30">
				<input type="hidden" name="fieldContents[<c:out value="${YY.count}"/>].rowId" value="1">
				<input type="Text" size="20" name="fieldContents[<c:out value="${YY.count}"/>].fieldText" value="<c:out value="${YY.fieldText}"/>">
			</td>
			<td colspan="40">
				<input type="hidden" name="fieldContents[<c:out value="${ZZ.count}"/>].rowId" value="1">
				<input type="Text" size="20" name="fieldContents[<c:out value="${ZZ.count}"/>].fieldText" value="<c:out value="${ZZ.fieldText}"/>">
			</td>
		</tr>
	</c:if>
	
	<c:if test="${!empty rows}">
  	<c:forEach items="${rows}" var="row"> 
  				<c:set var="rownum" value="${rownum + 1}"/>
  				<c:forEach items="${row.fileds}" var="field">
					<c:if test="${field.fieldCode=='XX'}">
						<c:set var="f1" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='YY'}">
						<c:set var="f2" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='ZZ'}">
						<c:set var="f3" value="${field}"/>
					</c:if>
				</c:forEach>
		 <tr>
			<td colspan="30">
				<c:if test="${f1.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${f1.count}"/>].fieldId" value="<c:out value="${f1.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${f1.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<input type="Text" size="20" name="fieldContents[<c:out value="${f1.count}"/>].fieldText" value="<c:out value="${f1.fieldText}"/>">
				</c:if>
				<c:if test="${f1.isEdit != '1'}">
					<c:out value="${f1.fieldText}"/>
				</c:if>
			</td>
			<td colspan="30">
				<c:if test="${f2.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${f2.count}"/>].fieldId" value="<c:out value="${f2.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${f2.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<input type="Text" size="20" name="fieldContents[<c:out value="${f2.count}"/>].fieldText" value="<c:out value="${f2.fieldText}"/>">
				</c:if>
				<c:if test="${f2.isEdit != '1'}">
					<c:out value="${f2.fieldText}"/>
				</c:if>
			</td>
			<td colspan="40" >
				<c:if test="${f3.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${f3.count}"/>].fieldId" value="<c:out value="${f3.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${f3.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<input type="Text" size="20" name="fieldContents[<c:out value="${f3.count}"/>].fieldText" value="<c:out value="${f3.fieldText}"/>">
				</c:if>
				<c:if test="${f3.isEdit != '1'}">
					<c:out value="${f3.fieldText}"/>
				</c:if>
			</td>
		</tr>
		
		<c:remove var="f1"/>
		<c:remove var="f2"/>
		<c:remove var="f3"/>
  	</c:forEach>
 	</c:if>
 <input type="hidden" id="numI1" value="<c:out value="${rownum+1}"/>"/>
 
</table>

<!-- 
	    	<c:if test="${EEE1.isEdit == '1'}">
				<input type="hidden" name="fieldContents[<c:out value="${EEE1.count}"/>].fieldId" value="<c:out value="${EEE1.fieldId}"/>">
				<input type="hidden" name="fieldContents[<c:out value="${EEE1.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				<textarea rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${EEE1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${EEE1.count}"/>].fieldText"><c:out value="${EEE1.fieldText}"/></textarea>
			</c:if>
			<c:if test="${EEE1.isEdit != '1'}">
				<c:out value="${EEE1.fieldText}"/>
			</c:if>
 -->
