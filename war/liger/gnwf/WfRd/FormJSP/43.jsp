<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
		
<body>
<span style="visibility:hidden">页面43jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>风 险 评 估 单</strong></p>

<table width="680" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
  <tr height="30">
    <td width="180"><div align="center">产品型号</div></td>
    <td width="180">&nbsp;
	<c:choose>
	    <c:when test="${E1.isEdit == '1'}">
	        <input type="Text" size="20" id="fieldContents[<c:out value='${E1.count}'/>].fieldText"
	            name="fieldContents[<c:out value='${E1.count}'/>].fieldText" value="<c:out value='${E1.fieldText}'/>" >
	    </c:when>
	    <c:otherwise>
	        <c:out value="${fieldContents[0].fieldText}"/>
	    </c:otherwise>
	</c:choose>
	</td>
	<td><div align="center">项目经理</div></td>
    <td>&nbsp;
	<c:choose>
	    <c:when test="${E2.isEdit == '1'}">
	        <input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
	            name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
	    </c:when>
	    <c:otherwise>
	   
	       <c:out value="${fieldContents[1].fieldText}"/>
	        
	      
	    </c:otherwise>
	</c:choose>
	</td>
  </tr>

  <tr>
    <td height="30"><div align="center">风险来源</div></td>
    <td height="30" colspan="3"> 
    <c:choose>
    <c:when test="${E3.isEdit == '1'}">
        <input name="fieldContents[<c:out value="${E3.count}"/>].fieldText"  type="checkbox" value="1"
            <c:if test="${E3.fieldText == 1}">checked</c:if> />
		 立项评审
        <input name="fieldContents[<c:out value="${E3.count}"/>].fieldText"  type="checkbox" value="2"
            <c:if test="${E3.fieldText == 2}">checked</c:if> />
		T1
		<input name="fieldContents[<c:out value="${E3.count}"/>].fieldText"  type="checkbox" value="3"
            <c:if test="${E3.fieldText == 3}">checked</c:if> />
		 T2
        <input name="fieldContents[<c:out value="${E11.count}"/>].fieldText"  type="checkbox" value="4"
            <c:if test="${E3.fieldText == 4}">checked</c:if> />
		中批
		<input name="fieldContents[<c:out value="${E3.count}"/>].fieldText"  type="checkbox" value="5"
            <c:if test="${E3.fieldText == 5}">checked</c:if> />
		 批量
        <input name="fieldContents[<c:out value="${E3.count}"/>].fieldText"  type="checkbox" value="6"
            <c:if test="${E3.fieldText == 6}">checked</c:if> />
		其他
    </c:when>
    <c:otherwise>
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[2].fieldText == 1}">checked</c:if> />
		 立项评审
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[2].fieldText == 2}">checked</c:if> />
		T1
		<input type="checkbox" disabled="disabled" <c:if test="${fieldContents[2].fieldText == 3}">checked</c:if> />
		 T2
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[2].fieldText == 4}">checked</c:if> />
		中批
		<input type="checkbox" disabled="disabled" <c:if test="${fieldContents[2].fieldText == 5}">checked</c:if> />
		 批量
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[2].fieldText == 6}">checked</c:if> />
		其他
    </c:otherwise>
    </c:choose>
	</td>
  </tr>
  
  
  
  
  
 
    
    
    
   <c:if test="${fn:contains(showFiledIds,E4.fieldId)&&fn:contains(showFiledIds,E5.fieldId)&&fn:contains(showFiledIds,E6.fieldId)}">
     <tr>
    <td height="100" colspan="4" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">结构部意见：</td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E4.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[3].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E5.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
						<c:if test="${!empty E5.fieldText}">value="<c:out value="${E5.fieldText}"/>"</c:if>
						<c:if test="${empty E5.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[4].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E6.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
						<c:if test="${!empty E6.fieldText}">value="<c:out value="${E6.fieldText}"/>"</c:if>
						<c:if test="${empty E6.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[5].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
			</td>
		</tr>
	 </table>
	 	 </td>
   </tr>
	 </c:if> 
	 

	 
	
	 <c:if test="${fn:contains(showFiledIds,E7.fieldId)&&fn:contains(showFiledIds,E8.fieldId)&&fn:contains(showFiledIds,E9.fieldId)}">
	  <tr>
    <td height="100" colspan="4" valign="top">
	 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">硬件部意见：</td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E7.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[6].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E8.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
						<c:if test="${!empty E8.fieldText}">value="<c:out value="${E8.fieldText}"/>"</c:if>
						<c:if test="${empty E8.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[7].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E9.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
						<c:if test="${!empty E9.fieldText}">value="<c:out value="${E9.fieldText}"/>"</c:if>
						<c:if test="${empty E9.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[8].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
			</td>
		</tr>
	 </table>
	  </td>
   </tr>
	 </c:if>
	
	 
	
	 
	 <c:if test="${fn:contains(showFiledIds,E10.fieldId)&&fn:contains(showFiledIds,E11.fieldId)&&fn:contains(showFiledIds,E12.fieldId)}">
	  <tr>
    <td height="100" colspan="4" valign="top">
	  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">ID部意见：</td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E10.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[9].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E11.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
						<c:if test="${!empty E11.fieldText}">value="<c:out value="${E11.fieldText}"/>"</c:if>
						<c:if test="${empty E11.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[10].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E12.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
						<c:if test="${!empty E12.fieldText}">value="<c:out value="${E12.fieldText}"/>"</c:if>
						<c:if test="${empty E12.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[11].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
			</td>
		</tr>
	 </table>
	   </td>
   </tr>
	 </c:if>
	
	 
	
    
	  <c:if test="${fn:contains(showFiledIds,E13.fieldId)&&fn:contains(showFiledIds,E14.fieldId)&&fn:contains(showFiledIds,E15.fieldId)}">
	   <tr>
    <td height="100" colspan="4" valign="top">
	 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">软件部意见：</td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E13.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[12].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E14.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
						<c:if test="${!empty E14.fieldText}">value="<c:out value="${E14.fieldText}"/>"</c:if>
						<c:if test="${empty E14.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[13].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E15.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
						<c:if test="${!empty E15.fieldText}">value="<c:out value="${E15.fieldText}"/>"</c:if>
						<c:if test="${empty E15.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[14].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
			</td>
		</tr>
	 </table>
	   </td>
   </tr>
	  </c:if>
	 
	
	 
	
    
	 <c:if test="${fn:contains(showFiledIds,E16.fieldId)&&fn:contains(showFiledIds,E17.fieldId)&&fn:contains(showFiledIds,E18.fieldId)}">
	  <tr>
    <td height="100" colspan="4" valign="top">
	 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">测试部意见：</td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E16.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E16.count}"/>].fieldText"><c:out value="${E16.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[15].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E17.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
						<c:if test="${!empty E17.fieldText}">value="<c:out value="${E17.fieldText}"/>"</c:if>
						<c:if test="${empty E17.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[16].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E18.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
						<c:if test="${!empty E18.fieldText}">value="<c:out value="${E18.fieldText}"/>"</c:if>
						<c:if test="${empty E18.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[17].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
			</td>
		</tr>
	 </table>
	 	  </td>
   </tr>
	 </c:if>

	 
	 
	 <c:if test="${fn:contains(showFiledIds,E19.fieldId)&&fn:contains(showFiledIds,E20.fieldId)&&fn:contains(showFiledIds,E21.fieldId)}">
	 <tr>
    <td height="100" colspan="4" valign="top">
	 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">工程部意见：<span style="color: red">（风险来源：只有在转 批量 时）</span></td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E19.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E19.count}"/>].fieldText"><c:out value="${E19.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[18].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E20.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
						<c:if test="${!empty E20.fieldText}">value="<c:out value="${E20.fieldText}"/>"</c:if>
						<c:if test="${empty E20.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[19].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E21.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
						<c:if test="${!empty E21.fieldText}">value="<c:out value="${E21.fieldText}"/>"</c:if>
						<c:if test="${empty E21.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[20].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
			</td>
		</tr>
	 </table>
	   
	  </td>
   </tr>
	  </c:if>
	  
	
	 
	
	  <c:if test="${fn:contains(showFiledIds,E22.fieldId)&&fn:contains(showFiledIds,E23.fieldId)&&fn:contains(showFiledIds,E24.fieldId)}">
	   <tr>
    <td height="100" colspan="4" valign="top">
	  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">品质管理部意见：<span style="color: red">（风险来源：只有在转 批量 时）</span></td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E22.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E22.count}"/>].fieldText"><c:out value="${E22.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[21].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E23.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
						<c:if test="${!empty E23.fieldText}">value="<c:out value="${E23.fieldText}"/>"</c:if>
						<c:if test="${empty E23.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[22].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E24.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
						<c:if test="${!empty E24.fieldText}">value="<c:out value="${E24.fieldText}"/>"</c:if>
						<c:if test="${empty E24.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[23].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
			</td>
		</tr>
	 </table>
	 	 </td>
   </tr>
	  </c:if>

	 
	 <tr>
    <td height="100" colspan="4" valign="top"> 
	 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">研发总监意见</span></td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E25.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E25.count}"/>].fieldText"><c:out value="${E25.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[24].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E26.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
						<c:if test="${!empty E26.fieldText}">value="<c:out value="${E26.fieldText}"/>"</c:if>
						<c:if test="${empty E26.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[25].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E27.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
						<c:if test="${!empty E27.fieldText}">value="<c:out value="${E27.fieldText}"/>"</c:if>
						<c:if test="${empty E27.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[26].fieldText}"/>" disabled="disabled"
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
    <td height="100" colspan="4" valign="top">
	 <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">研发副总意见：<span style="color: red">（研发一部和二部没有此角色，只有研发三部有此角色，故需要特殊流程处理）</span></span></td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E28.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E28.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E28.count}"/>].fieldText"><c:out value="${E28.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[27].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E29.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E29.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
						<c:if test="${!empty E29.fieldText}">value="<c:out value="${E29.fieldText}"/>"</c:if>
						<c:if test="${empty E29.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[28].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E30.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
						<c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
						<c:if test="${empty E30.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[29].fieldText}"/>" disabled="disabled"
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
    <td height="100" colspan="4" valign="top">
	  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td height="20">公司意见：</td>
		</tr>
		<tr>
			<td height="40" >
			<c:choose>
			    <c:when test="${E31.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E31.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E31.count}"/>].fieldText"><c:out value="${E31.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[30].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="30" align="right">
			签名：
			<c:choose>
			    <c:when test="${E32.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E32.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
						<c:if test="${!empty E32.fieldText}">value="<c:out value="${E32.fieldText}"/>"</c:if>
						<c:if test="${empty E32.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[31].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E33.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E33.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
						<c:if test="${!empty E33.fieldText}">value="<c:out value="${E33.fieldText}"/>"</c:if>
						<c:if test="${empty E33.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[32].fieldText}"/>" disabled="disabled"
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
</body>

