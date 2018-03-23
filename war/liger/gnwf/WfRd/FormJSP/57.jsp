<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面57jsp</span>
	<p align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
	<strong>问题转风险意见书</strong></p>
	
	<table   width="auto" border=1  bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
   		

		<tr height="100">
	       	
    		<td height="100" colspan="5">
			部门经理意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E1.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E1.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[0].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
				<c:choose>
				    <c:when test="${E2.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
							<c:if test="${!empty E2.fieldText}">value="<c:out value="${E2.fieldText}"/>"</c:if>
							<c:if test="${empty E2.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[1].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E3.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
							<c:if test="${!empty E3.fieldText}">value="<c:out value="${E3.fieldText}"/>"</c:if>
							<c:if test="${empty E3.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[2].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;
				
    		</td>
	    </tr>
	    
	    	<tr height="100">
	       	
    		<td height="100" colspan="5">
			DQA意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E4.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E4.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[3].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
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



   



