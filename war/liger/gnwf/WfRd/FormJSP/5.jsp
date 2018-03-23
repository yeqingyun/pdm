<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面5jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>产品调研需求单</strong></p>

<table id="OwnershipStructure" width="700" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>


    <tr>
    <td width="100%" colspan="100" align="center" bgcolor="#F0F0F0">调研需求部门填写</td>
    
	</tr>
	<tr>
    <td colspan="17" width="17%" align="center">产品型号</td>
    <td colspan="83"><c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[0].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    
	</tr>
  <tr>
    <td colspan="17" width="17%"align="center">调研对象</td>
    <td colspan="33" width="33%" ><c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[1].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
		
		
	<td colspan="17" align="center">调研数量</td>
    <td colspan="33" ><c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[2].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
   
  </tr>
  <tr>
    <td colspan="17" align="center">需求部门</td>
    <td colspan="33" ><c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[3].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
		
    <td colspan="17" align="center">提出时间</td>
    <td colspan="33" ><c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[4].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose></td>
		
  </tr>
  
  <tr>
    <td colspan="17" align="center">提出人</td>
    <td colspan="33" ><c:choose>
				<c:when test="${E6.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
						 <c:if test="${!empty E6.fieldText}">value="<c:out value="${E6.fieldText}"/>"</c:if>
						 <c:if test="${empty E6.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[5].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose></td>
		
    <td colspan="17" align="center">完成时间</td>
    <td colspan="33" ><c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[6].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
		
  </tr>
 
			
  
  
  
  <tr>
    <td colspan="100">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>调研需求描述：</td>
		</tr>
		<tr>
			<td height="36"><c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[7].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		
		</table>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="100">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>调研需求涉及图样：</td>
		</tr>
		<tr>
			<td height="66"><c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[8].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		
		</table>
	</td>
  </tr>
  
  
   <tr>
    <td colspan="17" align="center">审核人：</td>
    <td colspan="33"><c:choose>
				<c:when test="${E10.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
						 <c:if test="${!empty E10.fieldText}">value="<c:out value="${E10.fieldText}"/>"</c:if>
						 <c:if test="${empty E10.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[9].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose></td>
    <td colspan="17" align="center">审核日期：</td>
    <td colspan="33" ><c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[10].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose></td>
  </tr>
 
  
  
  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">用户调研组填写</td>
  </tr>
  
  
  
  
  
  
  
  
  
  <tr>
    <td colspan="100">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>调研需求处理意见：</td>
		</tr>
		<tr>
			<td height="36"><c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E12.count}"/>].fieldText"><c:out value="${E12.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[11].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			用户研究主管签名：
		<c:choose>
				<c:when test="${E13.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
						 <c:if test="${!empty E13.fieldText}">value="<c:out value="${E13.fieldText}"/>"</c:if>
						 <c:if test="${empty E13.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[12].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E14.count}"/>].fieldText" value="<c:out value="${E14.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[13].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			</td>
		</tr>
	  </table>
	
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="100">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>备注：</td>
		</tr>
		<tr>
			<td height="36"><c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E15.count}"/>].fieldText"><c:out value="${E15.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[14].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
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
