<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面53jsp</span>
	<p align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
	<strong>PCN处理意见书</strong></p>
	
	<table   width="auto" border=1  bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
   		
   	
		<tr height="30">
	       	<td height="30"width="130"><div align="center"><span style="font-weight:bold;">供应商名称</span></div></td>
    		<td height="30" width="180">
    		<c:choose>
				<c:when test="${E1.isEdit == '1'}">
					<input type="Text" size="30"
						id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						value="<c:out value="${E1.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[0].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
    		<td height="30" width="130"><div align="center"><span style="font-weight:bold;">供应商联系人</span></div></td>
    		<td height="30" colspan="2">
    		<c:choose>
				<c:when test="${E2.isEdit == '1'}">
					<input type="Text" size="50"
						id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E2.count}"/>].fieldText"
						value="<c:out value="${E2.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[1].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
	    </tr>
	    
	    
	    <tr height="30">
	       	<td height="30"width="130"><div align="center"><span style="font-weight:bold;">供应商地址</span></div></td>
    		<td height="30" width="180">
    		<c:choose>
				<c:when test="${E3.isEdit == '1'}">
					<input type="Text" size="30"
						id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						value="<c:out value="${E3.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[2].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
    		<td height="30" width="130"><div align="center"><span style="font-weight:bold;">变更前物料编码</span></div></td>
    		<td height="30" colspan="2">
    		<c:choose>
				<c:when test="${E4.isEdit == '1'}">
					<input type="Text" size="50"
						id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E4.count}"/>].fieldText"
						value="<c:out value="${E4.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[3].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
	    </tr>
	   
	   
	   <tr height="30">
	       	<td height="30"width="130"><div align="center"><span style="font-weight:bold;">变更前物料型号</span></div></td>
    		<td height="30" width="180">
    		<c:choose>
				<c:when test="${E5.isEdit == '1'}">
					<input type="Text" size="30"
						id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
						value="<c:out value="${E5.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[4].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
    		<td height="30" width="130"><div align="center"><span style="font-weight:bold;"> 变更后物料型号</span></div></td>
    		<td height="30" colspan="2">
    		<c:choose>
				<c:when test="${E6.isEdit == '1'}">
					<input type="Text" size="50"
						id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E6.count}"/>].fieldText"
						value="<c:out value="${E6.fieldText}"/>">
				</c:when>
				<c:otherwise>
					<c:out value="${fieldContents[5].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
	    </tr>
	   
	    <tr height="30">
	       	<td height="30"width="130"><div align="center"><span style="font-weight:bold;">变更级别（请勾选“√”）</span></div></td>
    		<td height="30" colspan="5">
    		<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<div style="padding-left:10px">
						<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="1"
						<c:if test="${E7.fieldText == 1 || empty E7.fieldText}">checked</c:if> />一级变更
						&nbsp;&nbsp;&nbsp;
						<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="2"
							<c:if test="${E7.fieldText == 2 || empty E7.fieldText}">checked</c:if> />二级变更
						&nbsp;&nbsp;&nbsp;	
						<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="3"
							<c:if test="${E7.fieldText == 3 || empty E7.fieldText}">checked</c:if> />三级变更
					 	&nbsp;&nbsp;&nbsp;
						<input type="radio" name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="4"
							<c:if test="${E7.fieldText == 4 || empty E7.fieldText}">checked</c:if> />四级变更   
					 </div>
				
			</c:when>
			<c:otherwise>
				<div style="padding-left:10px">
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[6].fieldText == 1}">checked</c:if> />一级变更
						&nbsp;&nbsp;&nbsp;
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[6].fieldText == 2}">checked</c:if> />二级变更
						 &nbsp;&nbsp;&nbsp;
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[6].fieldText == 3}">checked</c:if> />三级变更
						&nbsp;&nbsp;&nbsp;
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[6].fieldText == 4}">checked</c:if> />四级变更   
					</div>
				
			</c:otherwise>
		</c:choose>
    		
    		</td>
	    </tr>
		
     
	    
	    
	    

		  
		<tr height="100">
	       	
    		<td height="100" colspan="5">
			采购策略部意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E8.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E8.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[7].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
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
				 &nbsp;日&nbsp;期：
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
				 &nbsp;
				
    		</td>
	    </tr>
	    
	    	<tr height="100">
	       	
    		<td height="100" colspan="5">
			结构部意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E11.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E11.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E11.count}"/>].fieldText"><c:out value="${E11.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[10].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
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
				 &nbsp;日&nbsp;期：
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
				 &nbsp;
				
    		</td>
	    </tr>
	    
	    	<tr height="100">
	       	
    		<td height="100" colspan="5">
			中试部意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E14.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E14.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[13].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
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
				 &nbsp;日&nbsp;期：
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
				 &nbsp;
				
    		</td>
	    </tr>
	    
	    	<tr height="100">
	       	
    		<td height="100" colspan="5">
			硬件部意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E17.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E17.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E17.count}"/>].fieldText"><c:out value="${E17.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[16].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
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
				 &nbsp;日&nbsp;期：
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
				 &nbsp;
				
    		</td>
	    </tr>
	    
	    	<tr height="100">
	       	
    		<td height="100" colspan="5">
			软件部意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E20.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E20.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E20.count}"/>].fieldText"><c:out value="${E20.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[19].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
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
				        <input type="Text" size="20" value="<c:out value="${fieldContents[21].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;
				
    		</td>
	    </tr>
	    
	    	<tr height="100">
	       	
    		<td height="100" colspan="5">
			材料认证部综合意见：
				
				</br>
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E23.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E23.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E23.count}"/>].fieldText"><c:out value="${E23.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[22].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
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
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E25.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
							<c:if test="${!empty E25.fieldText}">value="<c:out value="${E25.fieldText}"/>"</c:if>
							<c:if test="${empty E25.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[24].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;
				
    		</td>
	    </tr>
	 
		
		
</table>



   



