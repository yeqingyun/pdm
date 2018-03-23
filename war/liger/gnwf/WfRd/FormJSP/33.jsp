<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面33jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>转批量生产申请单</strong></p>

<!-- 
<table width="680" align="center">
  <tr><td>
  <label for="shouban"></label></td>
    <td align="right">
    	编号:
<c:choose>
    <c:when test="${E1.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${E1.fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
    </td>
  </tr>
</table>
 -->
 
<table width="700" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
  <tr height="30">
    <td width="117" colspan="15" align="center">产品型号</td>
    <td width="117" colspan="15">&nbsp;
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
    <td width="100" colspan="15" align="center">部门</td>
    <td width="120" colspan="20">&nbsp;
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
    <td width="100" colspan="15" align="center">日期</td>
    <td width="120" colspan="20">&nbsp;
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
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">转批量申请描述：</td>
	    </tr>
		<tr>
		  <td height="56" >
			<c:choose>
			    <c:when test="${E5.isEdit == '1'}">
			        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[4].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：
			<c:choose>
			    <c:when test="${E6.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
						<c:if test="${!empty E6.fieldText}">value="<c:out value="${E6.fieldText}"/>"</c:if>
						<c:if test="${empty E6.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[5].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		    日期：
			<c:choose>
			    <c:when test="${E7.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
						<c:if test="${!empty E7.fieldText}">value="<c:out value="${E7.fieldText}"/>"</c:if>
						<c:if test="${empty E7.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[6].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">结构部意见：</td>
	    </tr>
		<tr>
		  <td height="56" >

<c:choose>
    <c:when test="${E8.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[7].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：
<c:choose>
    <c:when test="${E9.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
			<c:if test="${!empty E9.fieldText}">value="<c:out value="${E9.fieldText}"/>"</c:if>
			<c:if test="${empty E9.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[8].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E10.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
			<c:if test="${!empty E10.fieldText}">value="<c:out value="${E10.fieldText}"/>"</c:if>
			<c:if test="${empty E10.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[9].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">硬件部意见：</td>
	    </tr>
		<tr>
		  <td height="56" >

<c:choose>
    <c:when test="${E11.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E11.count}"/>].fieldText"><c:out value="${E11.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[10].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E12.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
			<c:if test="${!empty E12.fieldText}">value="<c:out value="${E12.fieldText}"/>"</c:if>
			<c:if test="${empty E12.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[11].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E13.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
			<c:if test="${!empty E13.fieldText}">value="<c:out value="${E13.fieldText}"/>"</c:if>
			<c:if test="${empty E13.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[12].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
  
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">ID部意见：</td>
	    </tr>
		<tr>
		  <td height="56" >

<c:choose>
    <c:when test="${E14.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[13].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E15.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
			<c:if test="${!empty E15.fieldText}">value="<c:out value="${E15.fieldText}"/>"</c:if>
			<c:if test="${empty E15.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[14].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E16.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
			<c:if test="${!empty E16.fieldText}">value="<c:out value="${E16.fieldText}"/>"</c:if>
			<c:if test="${empty E16.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[15].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
  
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">软件部意见：</td>
	    </tr>
		<tr>
		  <td height="56" >

<c:choose>
    <c:when test="${E17.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E17.count}"/>].fieldText"><c:out value="${E17.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[16].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E18.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
			<c:if test="${!empty E18.fieldText}">value="<c:out value="${E18.fieldText}"/>"</c:if>
			<c:if test="${empty E18.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[17].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E19.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
			<c:if test="${!empty E19.fieldText}">value="<c:out value="${E19.fieldText}"/>"</c:if>
			<c:if test="${empty E19.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[18].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
    
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">测试部意见：</td>
	    </tr>
		<tr>
		  <td height="56" >

<c:choose>
    <c:when test="${E20.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E20.count}"/>].fieldText"><c:out value="${E20.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[19].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E21.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
			<c:if test="${!empty E21.fieldText}">value="<c:out value="${E21.fieldText}"/>"</c:if>
			<c:if test="${empty E21.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[20].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E22.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
			<c:if test="${!empty E22.fieldText}">value="<c:out value="${E22.fieldText}"/>"</c:if>
			<c:if test="${empty E22.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[21].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
  
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">制造代表意见：（品质管理部）</td>
	    </tr>
		<tr>
		  <td height="56" >

<c:choose>
    <c:when test="${E23.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E23.count}"/>].fieldText"><c:out value="${E23.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[22].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E24.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
			<c:if test="${!empty E24.fieldText}">value="<c:out value="${E24.fieldText}"/>"</c:if>
			<c:if test="${empty E24.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[23].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E25.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
			<c:if test="${!empty E25.fieldText}">value="<c:out value="${E25.fieldText}"/>"</c:if>
			<c:if test="${empty E13.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[24].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
   
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">研发总监意见：</td>
	    </tr>
		<tr>
		  <td height="56" >

<c:choose>
    <c:when test="${E26.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E26.count}"/>].fieldText"><c:out value="${E26.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[25].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E27.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
			<c:if test="${!empty E27.fieldText}">value="<c:out value="${E27.fieldText}"/>"</c:if>
			<c:if test="${empty E27.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[26].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E28.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E28.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
			<c:if test="${!empty E28.fieldText}">value="<c:out value="${E28.fieldText}"/>"</c:if>
			<c:if test="${empty E28.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[27].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
  
    <c:if test="${prjtDef.devDeptNameID==3}">

   
 
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">研发副总意见：</td>
	    </tr>
		<tr>
		  <td height="56" >

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
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E30.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
			<c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
			<c:if test="${empty E30.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[29].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E31.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E31.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E31.count}"/>].fieldText" 
			<c:if test="${!empty E31.fieldText}">value="<c:out value="${E31.fieldText}"/>"</c:if>
			<c:if test="${empty E31.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[30].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  </c:if>
   
  
  
  
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">公司意见：</td>
	    </tr>

		<c:choose>
			<c:when test="${E32.isEdit == '1'}">
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" name="fieldContents[<c:out value="${E32.count}"/>].fieldText" value="1"
						<c:if test="${E32.fieldText == 1 || empty E32.fieldText}">checked</c:if> />同意
					</div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" name="fieldContents[<c:out value="${E32.count}"/>].fieldText" value="2"
							<c:if test="${E32.fieldText == 2 || empty E32.fieldText}">checked</c:if> />不同意
					 </div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" name="fieldContents[<c:out value="${E32.count}"/>].fieldText" value="3"
							<c:if test="${E32.fieldText == 3 || empty E32.fieldText}">checked</c:if> />其他
						<input type="Text" size="20" id="fieldContents[<c:out value="${E33.count}"/>].fieldText"
							name="fieldContents[<c:out value="${E33.count}"/>].fieldText" value="<c:out value="${E33.fieldText}"/>"
							style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
					 </div></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[31].fieldText == 1}">checked</c:if> />同意
					</div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[31].fieldText == 2}">checked</c:if> />不同意
					 </div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[31].fieldText == 3}">checked</c:if> />其他
						<input type="Text" size="20" value="<c:out value="${fieldContents[32].fieldText}"/>" disabled="disabled"
							style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
					 </div></td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E34.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E34.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E34.count}"/>].fieldText" 
			<c:if test="${!empty E34.fieldText}">value="<c:out value="${E34.fieldText}"/>"</c:if>
			<c:if test="${empty E34.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[33].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：
<c:choose>
    <c:when test="${E35.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E35.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
			<c:if test="${!empty E35.fieldText}">value="<c:out value="${E35.fieldText}"/>"</c:if>
			<c:if test="${empty E35.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[34].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  
<!--  
  <tr>
    <td height="90" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="22">备注：</td>
	    </tr>
		<tr>
		  <td height="50" >
<c:choose>
    <c:when test="${E18.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E18.count}"/>].fieldText"><c:out value="${E18.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${E18.fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
		  </td>
	    </tr>
     </table></td>
  </tr>
 -->
 
</table>

<!-- 
<table width="680" align="center">
  <tr><td>
<c:choose>
    <c:when test="${E51.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E51.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E51.count}"/>].fieldText" value="<c:out value="${E51.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${E51.fieldText}"/>
    </c:otherwise>
</c:choose>
    </td>
  </tr>
</table>
 -->