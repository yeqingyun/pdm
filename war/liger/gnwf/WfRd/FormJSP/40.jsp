<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面40jsp</span>
	<p align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
	<strong>附配件认证表</strong></p>
	
	<table   width="auto" border=1  bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
   
	    <tr height="30">
	       	<td width="100"><div align="center">项目名称</div></td>
	       	<td width="100"><div align="center">物料编号</div></td>
	       	<td width="130"><div align="center">物料描述</div></td>
	       	<td width="130"><div align="center">大小量纲</div></td>
	       	<td width="130"><div align="center">备注</div></td>
	      	
	    </tr>
    
	    <tr>
		<td height="30"><c:choose>
				<c:when test="${E1.isEdit == '1'}">
					<input type="Text" size="20"
						id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						value="<c:out value="${E1.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[0].fieldText}"/>
				</c:otherwise>
			</c:choose></td>
		<td>&nbsp; <c:choose>
				<c:when test="${E2.isEdit == '1'}">
					<input type="Text" size="20"
						id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E2.count}"/>].fieldText"
						value="<c:out value="${E2.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[1].fieldText}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td><c:choose>
				<c:when test="${E3.isEdit == '1'}">
					<input type="Text" size="20"
						id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						value="<c:out value="${E3.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[2].fieldText}"/>
				</c:otherwise>
			</c:choose></td>
		<td>&nbsp; <c:choose>
				<c:when test="${E4.isEdit == '1'}">
					<input type="Text" size="20"
						id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E4.count}"/>].fieldText"
						value="<c:out value="${E4.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[3].fieldText}"/>
				</c:otherwise>
			</c:choose>
		</td>
		<td height="30"><c:choose>
				<c:when test="${E5.isEdit == '1'}">
					<input type="Text" size="20"
						id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
						value="<c:out value="${E5.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[4].fieldText}"/>
				</c:otherwise>
			</c:choose></td>
	</tr>
     
  <tr>
    <td height="30"><div align="center">测试项</div></td>
    <td height="30" colspan="4"> 
    <c:choose>
    <c:when test="${E6.isEdit == '1'}">
        <input name="fieldContents[<c:out value="${E6.count}"/>].fieldText"  type="checkbox" value="1"
            <c:if test="${E6.fieldText == '1'}">checked</c:if> />
		硬件测试&nbsp;&nbsp;&nbsp;
        <input name="fieldContents[<c:out value="${E7.count}"/>].fieldText"  type="checkbox" value="2"
            <c:if test="${E7.fieldText == '2'}">checked</c:if> />
		可靠性测试&nbsp;&nbsp;&nbsp;
		<input name="fieldContents[<c:out value="${E8.count}"/>].fieldText"  type="checkbox" value="3"
            <c:if test="${E8.fieldText == '3'}">checked</c:if> />
		试产
    </c:when>
    <c:otherwise>
         <input name="fieldContents[<c:out value="${E6.count}"/>].fieldText"  type="checkbox" value="1"
            <c:if test="${fieldContents[5].fieldText == '1'}">checked</c:if> />
		硬件测试&nbsp;&nbsp;&nbsp;
        <input name="fieldContents[<c:out value="${E7.count}"/>].fieldText"  type="checkbox" value="2"
            <c:if test="${fieldContents[6].fieldText == '2'}">checked</c:if> />
		可靠性测试&nbsp;&nbsp;&nbsp;
		<input name="fieldContents[<c:out value="${E8.count}"/>].fieldText"  type="checkbox" value="3"
            <c:if test="${fieldContents[7].fieldText == '3'}">checked</c:if> />
		试产
    </c:otherwise>
    </c:choose>
	</td>
  </tr>
		  
	   <tr>
	    <td height="100" colspan="5" valign="top">
	    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="20">硬件测试备注：</td>
			</tr>
			<tr>
				<td height="40" >
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E9.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E9.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[8].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">
				签名：
				<c:choose>
				    <c:when test="${E10.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
							<c:if test="${!empty E10.fieldText}">value="<c:out value="${E10.fieldText}"/>"</c:if>
							<c:if test="${empty E10.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[9].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E11.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
							<c:if test="${!empty E11.fieldText}">value="<c:out value="${E11.fieldText}"/>"</c:if>
							<c:if test="${empty E11.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[10].fieldText}"/>" disabled="disabled"
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
	    <td height="100" colspan="5" valign="top">
	    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="20">可靠性测试备注：</td>
			</tr>
			<tr>
				<td height="40" >
				       
				<c:choose>
				    <c:when test="${E12.isEdit == '1'}">
				        <textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E12.count}"/>].fieldText"><c:out value="${E12.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[11].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">
				签名：
				<c:choose>
				    <c:when test="${E13.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
							<c:if test="${!empty E13.fieldText}">value="<c:out value="${E13.fieldText}"/>"</c:if>
							<c:if test="${empty E13.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[12].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E14.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
							<c:if test="${!empty E14.fieldText}">value="<c:out value="${E14.fieldText}"/>"</c:if>
							<c:if test="${empty E14.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[13].fieldText}"/>" disabled="disabled"
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
	    <td height="100" colspan="5" valign="top">
	    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="20">试产结论备注：</td>
			</tr>
			<tr>
				<td height="40" >
				       
				<c:choose>
				    <c:when test="${E15.isEdit == '1'}">
				        <textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E15.count}"/>].fieldText"><c:out value="${E15.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[14].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">
				签名：
				<c:choose>
				    <c:when test="${E16.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
							<c:if test="${!empty E16.fieldText}">value="<c:out value="${E16.fieldText}"/>"</c:if>
							<c:if test="${empty E16.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[15].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E17.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
							<c:if test="${!empty E17.fieldText}">value="<c:out value="${E17.fieldText}"/>"</c:if>
							<c:if test="${empty E17.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[16].fieldText}"/>" disabled="disabled"
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
	    <td height="100" colspan="5" valign="top">
	    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="20">认证结论：</td>
			</tr>
			<tr>
				<td height="40" >
				       
				<c:choose>
				    <c:when test="${E18.isEdit == '1'}">
				        <textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E18.count}"/>].fieldText"><c:out value="${E18.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[17].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">
				签名：
				<c:choose>
				    <c:when test="${E19.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
							<c:if test="${!empty E19.fieldText}">value="<c:out value="${E19.fieldText}"/>"</c:if>
							<c:if test="${empty E19.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[18].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E20.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
							<c:if test="${!empty E20.fieldText}">value="<c:out value="${E20.fieldText}"/>"</c:if>
							<c:if test="${empty E20.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[19].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;
				</td>
			</tr>
		 </table>
		</td>
	  </tr>
	     
	
			<%-- <tr>
				<td height="30"  colspan="5" align="right">
				签 名：
				<c:choose>
				    <c:when test="${E21.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
							<c:if test="${!empty E21.fieldText}">value="<c:out value="${E21.fieldText}"/>"</c:if>
							<c:if test="${empty E21.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${E21.fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				  &nbsp;日&nbsp;期： 
				<c:choose>
				    <c:when test="${E22.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
							<c:if test="${!empty E22.fieldText}">value="<c:out value="${E22.fieldText}"/>"</c:if>
							<c:if test="${empty E22.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${E22.fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				  &nbsp;
				</td>
			</tr> --%>
			
		 
		  
		
</table>

