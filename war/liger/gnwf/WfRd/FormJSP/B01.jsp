<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>产品质量纠正预防措施追踪单</strong></p>

<table id="OwnershipStructure" width="680" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
  <tr>
    <td width="60" rowspan="2" align="center">问题<br>描述</td>
    <td width="100" colspan="2"><p align="center">产品型号 </p></td>
    <td width="300"><p align="center">故障现象 </p></td>
    <td><p align="center">&nbsp;改善前出货量 </p></td>
  </tr>
  <tr>
    <td colspan="2" height="60" align="center">
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="15" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E1.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td align="center">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<textarea  rows="3" cols="35" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E2.count}"/>].fieldText"><c:out value="${E2.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E2.fieldText}"/>
			</c:otherwise>
		</c:choose>
    </td>
    <td align="center">
	<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<textarea  rows="3" cols="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<c:out value="${E3.fieldText}"/>
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="5">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td height="70" valign="top"><strong>原因分析：</strong><br>
			<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				&nbsp;&nbsp;&nbsp;<textarea  rows="4" cols="90" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<c:out value="${E4.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			失效分析部责任人：
			<c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E5.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日期：
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E6.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;	
		</td>
		</tr>
		</table>
	</td>
  </tr>
  <tr>
    <td colspan="5">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td height="70" valign="top"><strong>根本原因：</strong><br>
		<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				&nbsp;&nbsp;&nbsp;<textarea  rows="4" cols="90" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<c:out value="${E7.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			失效分析部责任人：
			<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E8.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日期：
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
		</c:choose>&nbsp;	
		</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  
  
  <tr>
    <td colspan="5">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td height="70" valign="top"><strong>改善措施：</strong><br>
		<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				&nbsp;&nbsp;&nbsp;<textarea  rows="4" cols="90" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<c:out value="${E10.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			工程部责任人：
			<c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E11.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日期：
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
		</c:choose>&nbsp;	
			</td>
		</tr>
		</table>
	</td>
  </tr>
  <tr>
    <td colspan="5">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td height="70" valign="top"><strong>改善措施导入时间：</strong><br>
		<c:choose>
			<c:when test="${E13.isEdit == '1'}">
				&nbsp;&nbsp;&nbsp;<textarea  rows="4" cols="90" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<c:out value="${E13.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td height="70" valign="top"><strong>改善导入起始号段：</strong><br>
		<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				&nbsp;&nbsp;&nbsp;<textarea  rows="4" cols="90" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<c:out value="${E14.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			工程部责任人：
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
			&nbsp;日期：
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
			&nbsp;	
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  
    <tr>
    <td colspan="5">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td height="70" valign="top"><strong>改善措施执行情况：</strong><br>
			<c:choose>
			<c:when test="${E17.isEdit == '1'}">
				&nbsp;&nbsp;&nbsp;<textarea  rows="4" cols="90" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E17.count}"/>].fieldText"><c:out value="${E17.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<c:out value="${E17.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			品质PQC责任人：
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
			&nbsp;日期：
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
			&nbsp;	
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="5">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<tr>
			<td height="70" valign="top"><strong>效果验证：</strong><br>
		<c:choose>
			<c:when test="${E20.isEdit == '1'}">
				&nbsp;&nbsp;&nbsp;<textarea  rows="4" cols="90" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E20.count}"/>].fieldText"><c:out value="${E20.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;&nbsp;<c:out value="${E20.fieldText}"/>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
		  <td align="right">
			品质部责任人：
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
			&nbsp;日期：
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
    <td colspan="2" align="center">是否关闭</td>
    <td colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="left">
			<c:choose>
			<c:when test="${E23.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="1" 
		  			<c:if test="${E23.fieldText == 1 || empty E23.fieldText}">checked</c:if> />是&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="2" 
		  			<c:if test="${E23.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E23.fieldText == 1}">checked</c:if> />是&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E23.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:otherwise>
		</c:choose>
			
			<br>
			</td>
		</tr>
		<tr>
		  <td align="right">失效分析部责任人：
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
		  &nbsp;日期：
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
		  &nbsp;</td>
		</tr>
		</table>
	</td>
  </tr>
</table>