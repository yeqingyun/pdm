<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>PCN处理意见书</strong></p>

<table id="OwnershipStructure" width="700" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
	<tr>
    <td colspan="20" width="20%" align="center" height="25">供应商名称</td>
    <td colspan="30" width="30%" >
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E1.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="20" width="20%" align="center">供应商联系人</td>
    <td colspan="30" width="30%">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E2.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	</tr>
  <tr>
    <td colspan="20" align="center" height="25">供应商地址</td>
    <td colspan="30" >
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E3.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="20" align="center">变更前物料编码</td>
    <td colspan="30" >
		<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E4.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="20" align="center" height="25">变更前物料编码</td>
    <td colspan="30" >
		<c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E5.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="20" align="center">变更后物料型号</td>
    <td colspan="30" >
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E6.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="28" width="28%" align="center" height="25">变更级别（请勾选“√”）</td>
    <td colspan="72" width="72%">&nbsp;
		<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="1" 
		  			<c:if test="${E7.fieldText == 1 || empty E7.fieldText}">checked</c:if> />一级变更&nbsp;&nbsp;&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="2" 
		  			<c:if test="${E7.fieldText == 2}">checked</c:if> />二级变更&nbsp;&nbsp;&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="3" 
		  			<c:if test="${E7.fieldText == 3}">checked</c:if> />三级变更&nbsp;&nbsp;&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="4" 
		  			<c:if test="${E7.fieldText == 4}">checked</c:if> />四级变更&nbsp;&nbsp;&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E7.fieldText == 1}">checked</c:if> />一级变更&nbsp;&nbsp;&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E7.fieldText == 2}">checked</c:if> />二级变更&nbsp;&nbsp;&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E7.fieldText == 3}">checked</c:if> />三级变更&nbsp;&nbsp;&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E7.fieldText == 4}">checked</c:if> />四级变更&nbsp;&nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
			
  
  
  
  <tr>
    <td colspan="100">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td>采购策略部意见：</td>
		</tr>
		<tr>
			<td height="45" >
			<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<textarea  rows="4" style="width: 99%" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E8.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			签&nbsp;名：
		<c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="<c:out value="${E9.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E9.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E10.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;	
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="100">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td>结构部意见：</td>
		</tr>
		<tr>
			<td height="45" >
			<c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<textarea  rows="4" style="width: 99%" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E11.count}"/>].fieldText"><c:out value="${E11.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E11.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E12.count}"/>].fieldText" value="<c:out value="${E12.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E12.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E13.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="<c:out value="${E13.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E13.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  
  <tr>
    <td colspan="100">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td>中试部意见：</td>
		</tr>
		<tr>
			<td height="45" >
			<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<textarea rows="4" style="width: 99%" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E14.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
		<c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E15.count}"/>].fieldText" value="<c:out value="${E15.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E15.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E16.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E16.count}"/>].fieldText" value="<c:out value="${E16.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E16.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;		  </td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="100">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td>硬件部意见：</td>
		</tr>
		<tr>
			<td height="45" >
			<c:choose>
			<c:when test="${E17.isEdit == '1'}">
				<textarea  rows="4" style="width: 99%" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E17.count}"/>].fieldText"><c:out value="${E17.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E17.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
			<c:when test="${E18.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="<c:out value="${E18.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E18.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E19.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E19.count}"/>].fieldText" value="<c:out value="${E19.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E19.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;		  </td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  
  <tr>
    <td colspan="100">
    	<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td>软件部意见：</td>
		</tr>
		<tr>
			<td height="45" >
			<c:choose>
			<c:when test="${E20.isEdit == '1'}">
				<textarea  rows="4" style="width: 99%" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E20.count}"/>].fieldText"><c:out value="${E20.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E20.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
			<c:when test="${E21.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E21.count}"/>].fieldText" value="<c:out value="${E21.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E21.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E22.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="<c:out value="${E22.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E22.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;	
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  
  <tr>
    <td colspan="100">
	<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td>材料认证部综合意见：</td>
		</tr>
		<tr>
			<td height="45" >
			<c:choose>
			<c:when test="${E23.isEdit == '1'}">
				<textarea  rows="4" style="width: 99%" id="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E23.count}"/>].fieldText"><c:out value="${E23.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E23.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			签&nbsp;名：
			<c:choose>
			<c:when test="${E24.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E24.count}"/>].fieldText" value="<c:out value="${E24.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E24.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
		<c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E25.count}"/>].fieldText" value="<c:out value="${E25.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E25.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
		&nbsp;	
		</td>
		</tr>
		</table>
	
	</td>
  </tr>
</table>
<table width="700" border="0" align="center">
	<tr>
	  <td align="right">RDA-R-001&nbsp;&nbsp;V1.0
	  </td>
	</tr>
</table>