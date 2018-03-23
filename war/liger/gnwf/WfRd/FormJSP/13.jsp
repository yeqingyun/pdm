<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面13jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>产品定义书会签表单</strong></p>

<table id="OwnershipStructure" width="720" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>

 <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">产品经理</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
		<tr>
			<td height="48"><c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E1.count }"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[0].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E2.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
						 <c:if test="${!empty E2.fieldText}">value="<c:out value="${E2.fieldText}"/>"</c:if>
						 <c:if test="${empty E2.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[1].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					    <c:if test="${!empty E3.fieldText}">value="<c:out value="${E3.fieldText}"/>"</c:if>
						<c:if test="${empty E3.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if> 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[2].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">项目经理</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[3].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E5.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
						 <c:if test="${!empty E5.fieldText}">value="<c:out value="${E5.fieldText}"/>"</c:if>
						 <c:if test="${empty E5.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[4].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
 						<c:if test="${!empty E6.fieldText}">value="<c:out value="${E6.fieldText}"/>"</c:if>
						<c:if test="${empty E6.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[5].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">材料认证工程师</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[6].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E8.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
						 <c:if test="${!empty E8.fieldText}">value="<c:out value="${E8.fieldText}"/>"</c:if>
						 <c:if test="${empty E8.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[7].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
 						<c:if test="${!empty E9.fieldText}">value="<c:out value="${E9.fieldText}"/>"</c:if>
						<c:if test="${empty E9.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[8].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">结构工程师</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[9].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E11.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
						 <c:if test="${!empty E11.fieldText}">value="<c:out value="${E11.fieldText}"/>"</c:if>
						 <c:if test="${empty E11.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[10].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
 						<c:if test="${!empty E12.fieldText}">value="<c:out value="${E12.fieldText}"/>"</c:if>
						<c:if test="${empty E12.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[11].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">PCB工程师</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E13.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[12].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E14.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
						 <c:if test="${!empty E14.fieldText}">value="<c:out value="${E14.fieldText}"/>"</c:if>
						 <c:if test="${empty E14.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[13].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
 						<c:if test="${!empty E15.fieldText}">value="<c:out value="${E15.fieldText}"/>"</c:if>
						<c:if test="${empty E15.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[14].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">基带工程师</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E16.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E16.count}"/>].fieldText"><c:out value="${E16.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[15].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E17.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
						 <c:if test="${!empty E17.fieldText}">value="<c:out value="${E17.fieldText}"/>"</c:if>
						 <c:if test="${empty E17.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[16].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E18.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
 						<c:if test="${!empty E18.fieldText}">value="<c:out value="${E18.fieldText}"/>"</c:if>
						<c:if test="${empty E18.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[17].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">SPM</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E19.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E19.count}"/>].fieldText"><c:out value="${E19.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[18].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E20.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
						 <c:if test="${!empty E20.fieldText}">value="<c:out value="${E20.fieldText}"/>"</c:if>
						 <c:if test="${empty E20.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[19].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E21.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
 						<c:if test="${!empty E21.fieldText}">value="<c:out value="${E21.fieldText}"/>"</c:if>
						<c:if test="${empty E21.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[20].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">射频工程师</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E22.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E22.count}"/>].fieldText"><c:out value="${E22.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[21].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E23.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
						 <c:if test="${!empty E23.fieldText}">value="<c:out value="${E23.fieldText}"/>"</c:if>
						 <c:if test="${empty E23.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[22].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E24.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
 						<c:if test="${!empty E24.fieldText}">value="<c:out value="${E24.fieldText}"/>"</c:if>
						<c:if test="${empty E24.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[23].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
   <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">音频工程师</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E25.count}"/>].fieldText"><c:out value="${E25.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[24].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E26.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
						 <c:if test="${!empty E26.fieldText}">value="<c:out value="${E26.fieldText}"/>"</c:if>
						 <c:if test="${empty E26.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[25].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E27.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
 						<c:if test="${!empty E27.fieldText}">value="<c:out value="${E27.fieldText}"/>"</c:if>
						<c:if test="${empty E27.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[26].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
   <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">RF专家组</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E28.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E28.count}"/>].fieldText"><c:out value="${E28.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[27].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
		 签&nbsp;名：<c:choose>
				<c:when test="${E29.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
						 <c:if test="${!empty E29.fieldText}">value="<c:out value="${E29.fieldText}"/>"</c:if>
						 <c:if test="${empty E29.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[28].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E30.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
 						<c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
						<c:if test="${empty E30.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[29].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">ID工程师</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E31.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E31.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E31.count}"/>].fieldText"><c:out value="${E31.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[30].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E32.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
						 <c:if test="${!empty E32.fieldText}">value="<c:out value="${E32.fieldText}"/>"</c:if>
						 <c:if test="${empty E32.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[31].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E33.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E33.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E33.count}"/>].fieldText" 
 						<c:if test="${!empty E33.fieldText}">value="<c:out value="${E33.fieldText}"/>"</c:if>
						<c:if test="${empty E33.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[32].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  

  
   <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">研发总监</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">说明：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E34.isEdit == '1'}">
				<textarea rows="4" cols="70" id="fieldContents[<c:out value="${E34.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E34.count}"/>].fieldText"><c:out value="${E34.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[33].fieldText}"/></textarea>
			</c:otherwise>
		</c:choose></td>
		</tr>
		<tr>
			<td align="right">
			
			
		 签&nbsp;名：<c:choose>
				<c:when test="${E35.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
						 <c:if test="${!empty E35.fieldText}">value="<c:out value="${E35.fieldText}"/>"</c:if>
						 <c:if test="${empty E35.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[34].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E36.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E36.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E36.count}"/>].fieldText" 
 						<c:if test="${!empty E36.fieldText}">value="<c:out value="${E36.fieldText}"/>"</c:if>
						<c:if test="${empty E36.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[35].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
   
  
</table>