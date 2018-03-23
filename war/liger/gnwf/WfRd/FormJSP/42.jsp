<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面42jsp</span>
	<p align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
	<strong>翻单变更确认单</strong></p>
<table width="780" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
 
<table width="780" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
 
		<tr height="30" >
	       	<td height="30"width="120"><div align="center"><span style="font-weight:bold;">订单号</span></div></td>
    		<td height="30" width="300">
    		<c:choose>
				<c:when test="${E1.isEdit == '1'}">
					<input type="Text" size="50"
						id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						value="<c:out value="${E1.fieldText}"/>">
				</c:when>
				<c:otherwise>
					 <c:out value="${fieldContents[0].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
    	 </tr>
    	 <tr height="30" >
    		<td height="30" width="120"><div align="center"><span style="font-weight:bold;">下单时间</span></div></td>
    		<td height="30" width="300">
    		<c:choose>
				    <c:when test="${E2.isEdit == '1'}">
				        <input type="Text" size="50" id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
							<c:if test="${!empty E2.fieldText}">value="<c:out value="${E2.fieldText}"/>"</c:if>
							<c:if test="${empty E2.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value=" <c:out value="${fieldContents[1].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
    		</td>
    		
	    </tr>
	   
	   	<tr height="30">
	       	<td height="30"width="120"><div align="center"><span style="font-weight:bold;">项目名称</span></div></td>
    		<td height="30" width="300">
    		<c:choose>
				<c:when test="${E3.isEdit == '1'}">
					<input type="Text" size="50"
						id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						value="<c:out value="${E3.fieldText}"/>">
				</c:when>
				<c:otherwise>
					 <c:out value="${fieldContents[2].fieldText}"/>
				</c:otherwise>
			</c:choose>
    		</td>
    		</tr>
    		<tr height="30" >
    		<td height="30" width="120"><div align="center"><span style="font-weight:bold;">颜色/数量</span></div></td>
    		<td height="30" width="300">
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
	    
	 
  
		<tr align="center">
	       	<td width="130"><div align="center"><span style="font-weight:bold;">变更需求评估</span></div></td>
			<td width="450">
  			<table width="80%"  border="0" cellpadding="0" cellspacing="0">
		
    			<tr height="30" width="450"><td width="450"><span style="font-weight:bold;">变更需求（客户经理填写）</span></td></tr>
    			<tr height="90"width="450"><td width="450">
				<input type="hidden" id="filedI2"  size="20" value="fieldContents[<c:out value="${E5.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E5.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[4].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				
				</br>
				签名：
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
				 &nbsp;日&nbsp;期：
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
				 &nbsp;
    			</td></tr>
    			
    			<tr height="30" width="450"><td width="450"><span style="font-weight:bold;">计调回复（计调填写）</span></td></tr>
    			<tr height="90"width="450"><td width="450">
    			对进度的影响：
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
    			</td></tr>
    			
    			<tr height="90"width="450"><td width="450">
    			产生的成本：
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
    			</td></tr>
    			
    			<tr height="30" width="450"><td width="450"><span style="font-weight:bold;">商务经理评估结果（商务采购部经理填写）</span></td></tr>
    			<tr height="90"width="450"><td width="450">
    			
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
				</td></tr>
    			
    			<tr height="30" width="450"><td width="450"><span style="font-weight:bold;">变更执行措施（客户经理填写）</span></td></tr>
    			<tr height="90"width="450"><td width="450">
    			
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
    			</td></tr>
    			
    		</table>  
    		
			</td>
	    </tr>
	    <br/>
  		<tr >
  		<td width="130"><div align="center"><span style="font-weight:bold;">品质确认时间</span></div></td>
  		<td width="450">
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
  		<br/>
  		<tr >
  		<td width="130"><div align="center"><span style="font-weight:bold;">工程确认时间</span></div></td>
  		<td width="450">
  		
				签名：
				<c:choose>
				    <c:when test="${E22.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
							<c:if test="${!empty E22.fieldText}">value="<c:out value="${E22.fieldText}"/>"</c:if>
							<c:if test="${empty E22.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[21].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E23.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
							<c:if test="${!empty E23.fieldText}">value="<c:out value="${E23.fieldText}"/>"</c:if>
							<c:if test="${empty E23.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[22].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;
  		</td>
  		
  		</tr>
	
	  
	  
	  
	  
	  
</table>		
		
</table>

</table>



