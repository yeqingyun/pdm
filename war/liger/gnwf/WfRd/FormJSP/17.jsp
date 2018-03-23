<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面17jsp</span>
﻿<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>PCB投板申请单</strong></p>

<table width="680" align="center">
  <tr><td>
  <label for="shouban"></label>
  投板性质:

<c:choose>
    <c:when test="${E1.isEdit == '1'}">
        <input type="radio" name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="1"
            <c:if test="${E1.fieldText == 1 || empty E1.fieldText}">checked</c:if> />首板&nbsp;
        <input type="radio" name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="2"
            <c:if test="${E1.fieldText == 2 || empty E1.fieldText}">checked</c:if> />试产&nbsp;
        <input type="radio" name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="3"
            <c:if test="${E1.fieldText == 3 || empty E1.fieldText}">checked</c:if> />量产&nbsp;
    </c:when>
    <c:otherwise>
        <input type="radio" disabled="disabled" <c:if test="${fieldContents[0].fieldText == 1}">checked</c:if> />首板&nbsp;
        <input type="radio" disabled="disabled" <c:if test="${fieldContents[0].fieldText == 2}">checked</c:if> />试产&nbsp;
        <input type="radio" disabled="disabled" <c:if test="${fieldContents[0].fieldText == 3}">checked</c:if> />量产&nbsp;
    </c:otherwise>
</c:choose>
    其他编号：

<c:choose>
    <c:when test="${E2.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[1].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
  </td></tr>
</table>


<table width="680" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
  <tr height="30">
    <td colspan="2" align="center">产品型号</td>
    <td width="180" colspan="2">&nbsp;
		<c:choose>
		    <c:when test="${E3.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[2].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
    <td colspan="2" align="center">申请日期</td>
    <td colspan="3">&nbsp;
		<c:choose>
		    <c:when test="${E4.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[3].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td height="30" colspan="2" align="center">PCB版本</td>
    <td colspan="2">&nbsp;
		<c:choose>
		    <c:when test="${E5.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[4].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
    <td colspan="2" align="center">申请部门</td>
    <td colspan="3">&nbsp;
		<c:choose>
		    <c:when test="${E6.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[5].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td height="30" colspan="2"><div align="center">预期回板日期</div></td>
    <td height="30" colspan="2">&nbsp;
		<c:choose>
		    <c:when test="${E7.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[6].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
    <td height="30" colspan="2"><div align="center">申请人</div></td>
    <td height="30" colspan="3">&nbsp;
		<c:choose>
		    <c:when test="${E8.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[7].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td height="30" colspan="2"><div align="center">投板厂家</div></td>
    <td height="30" colspan="2">&nbsp;
		<c:choose>
		    <c:when test="${E9.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="<c:out value="${E9.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[8].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
    <td height="30" colspan="2"><div align="center">制板数量（块）</div></td>
    <td height="30" colspan="3">&nbsp;
		<c:choose>
		    <c:when test="${E10.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[9].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td height="30" colspan="2"><div align="center">供应商联系人</div></td>
    <td height="30" colspan="2">&nbsp;

<c:choose>
    <c:when test="${E11.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[10].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30" colspan="2"><div align="center">手机/电话</div></td>
    <td height="30" colspan="3">&nbsp;
		<c:choose>
		    <c:when test="${E12.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E12.count}"/>].fieldText" value="<c:out value="${E12.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[11].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td height="30" colspan="2"><div align="center">邮箱</div></td>
    <td height="30" colspan="2">&nbsp;
		<c:choose>
		    <c:when test="${E13.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="<c:out value="${E13.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[12].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
    <td height="30" colspan="2"><div align="center">传真</div></td>
    <td height="30" colspan="3">&nbsp;

<c:choose>
    <c:when test="${E14.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E14.count}"/>].fieldText" value="<c:out value="${E14.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[13].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
  </tr>
  <tr>
    <td height="185" colspan="2"><div align="center">附图</div></td>
    <td colspan="7">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" colspan="9"><div align="center">供应商所需资料及版本</div></td>
  </tr>
  <tr>
    <td width="80" height="30"><div align="center">序号</div></td>
    <td height="30" colspan="3"><div align="center">设计资料名称</div>      <div align="center"></div></td>
    <td width="90" height="30"><div align="center">序号</div></td>
    <td height="30" colspan="4"><div align="center">设计资料名称</div>      <div align="center"></div>      <div align="center"></div></td>
  </tr>
  <tr>
    <td height="30">

<c:choose>
    <c:when test="${E15.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E15.count}"/>].fieldText" value="<c:out value="${E15.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[14].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30" colspan="3">

<c:choose>
    <c:when test="${E16.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E16.count}"/>].fieldText" value="<c:out value="${E16.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[15].fieldText}"/>
    </c:otherwise>
</c:choose>
     </td>
    <td height="30">

<c:choose>
    <c:when test="${E17.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E17.count}"/>].fieldText" value="<c:out value="${E17.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[16].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30" colspan="4">

<c:choose>
    <c:when test="${E18.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="<c:out value="${E18.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[17].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
  </tr>
  <tr>
    <td height="30">

<c:choose>
    <c:when test="${E19.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E19.count}"/>].fieldText" value="<c:out value="${E19.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[18].fieldText}"/>
    </c:otherwise>
</c:choose>
     </td>
    <td height="30" colspan="3">

<c:choose>
    <c:when test="${E20.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E20.count}"/>].fieldText" value="<c:out value="${E20.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[19].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30">

<c:choose>
    <c:when test="${E21.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E21.count}"/>].fieldText" value="<c:out value="${E21.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[20].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30" colspan="4">

<c:choose>
    <c:when test="${E22.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="<c:out value="${E22.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[21].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
  </tr>
  <tr>
    <td height="30">

<c:choose>
    <c:when test="${E23.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="<c:out value="${E23.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[22].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30" colspan="3">

<c:choose>
    <c:when test="${E24.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E24.count}"/>].fieldText" value="<c:out value="${E24.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[23].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30">

<c:choose>
    <c:when test="${E25.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E25.count}"/>].fieldText" value="<c:out value="${E25.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[24].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
    <td height="30" colspan="4">

<c:choose>
    <c:when test="${E26.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E26.count}"/>].fieldText" value="<c:out value="${E26.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[25].fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
  </tr>
  
  <tr><td colspan="9">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">DQA意见：</td>
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
  </td></tr>
  
  <tr><td colspan="9">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">硬件部经理：</td>
		</tr>
			<tr>
			<td height="48"><c:choose>
			<c:when test="${E34.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E34.count}"/>].fieldText" 
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
				<c:when test="${E27.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
						 <c:if test="${!empty E27.fieldText}">value="<c:out value="${E27.fieldText}"/>"</c:if>
						 <c:if test="${empty E27.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[26].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E28.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
 						<c:if test="${!empty E28.fieldText}">value="<c:out value="${E28.fieldText}"/>"</c:if>
						<c:if test="${empty E28.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>   
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[27].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			
			</td>
		</tr>
		</table>
  </td></tr>
  
  <tr>
    <td height="173" colspan="9" valign="top"><p>备注：</p>
    <br/>

<c:choose>
    <c:when test="${E29.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E29.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E29.count}"/>].fieldText"><c:out value="${E29.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[28].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
    </td>
  </tr>
</table>
<table width="680" align="center">
  <tr><td>
	表格编号：

<c:choose>
    <c:when test="${E30.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E30.count}"/>].fieldText" value="<c:out value="${E30.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[29].fieldText}"/>
    </c:otherwise>
</c:choose>
  </td></tr>
</table>
