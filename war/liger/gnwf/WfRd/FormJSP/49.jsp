<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
		
<body>
<span style="visibility:hidden">页面49jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>物料风险评估报告</strong></p>

<table width="720" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
  <tr height="30">
  	<td colspan="6" align="center" style="background-color:yellow ;vertical-align:middle; text-align:center;">物料基本信息</td>
  </tr>
  <tr height="30">
    <td colspan="1"><div align="center">物料编码</div></td>
    <td colspan="1">
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
	<td colspan="1"><div align="center">物料型号</div></td>
    <td colspan="1">
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
	<td colspan="1"><div align="center">物料等级</div></td>
    <td colspan="1">&nbsp;
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
  </tr>
  
   <tr height="30">
    <td colspan="1"><div align="center">功能描述</div></td>
    <td colspan="1">&nbsp;
	<c:choose>
	    <c:when test="${E4.isEdit == '1'}">
	        <input type="Text" size="20" id="fieldContents[<c:out value='${E4.count}'/>].fieldText"
	            name="fieldContents[<c:out value='${E4.count}'/>].fieldText" value="<c:out value='${E4.fieldText}'/>" >
	    </c:when>
	    <c:otherwise>
	        <c:out value="${fieldContents[3].fieldText}"/>
	    </c:otherwise>
	</c:choose>
	</td>
	<td colspan="1"><div align="center">产品经理</div></td>
    <td colspan="1">&nbsp;
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
	<td colspan="1"><div align="center">项目名称</div></td>
    <td colspan="1">&nbsp;
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

   <tr height="30">
  	<td colspan="6" align="center" style="background-color:yellow ;vertical-align:middle; text-align:center;">风险评估与处理信息</td>
  </tr>
  
  <tr height="30">
  	<td colspan="6"style="background-color:gray ;" ><div>商务评估</div>

  	<div align="left">
  	签名：
				<c:choose>
				    <c:when test="${E7.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
							<c:if test="${!empty E7.fieldText}">value="<c:out value="${E7.fieldText}"/>"</c:if>
							<c:if test="${empty E7.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[6].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E8.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
							<c:if test="${!empty E8.fieldText}">value="<c:out value="${E8.fieldText}"/>"</c:if>
							<c:if test="${empty E8.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[7].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;
  	</div>
  	</td>
  </tr>
  
  
  
  <tr height="70">
    <td colspan="1" ><div align="center">风险分析</div></td>
    <td colspan="5">
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
  
  
   <tr height="70">
    <td colspan="1" ><div align="center">规避措施</div></td>
    <td colspan="5">
		<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E10.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E10.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[9].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
	</td>
  </tr>
    
  
<tr height="30">
  	<td colspan="6"style="background-color:gray ;" ><div>物料技术评估</div>

  	<div align="left">
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
							<c:if test="${!empty E12.fieldText}">value="<c:out value="${E41.fieldText}"/>"</c:if>
							<c:if test="${empty E12.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[11].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;
  	</div>
  	</td>
  </tr>

  <tr height="70">
    <td colspan="1" ><div align="center">风险分析</div></td>
    <td colspan="5">
		<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E13.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E13.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[12].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
	</td>
  </tr>
  
  
   <tr height="70">
    <td colspan="1" ><div align="center">规避措施</div></td>
    <td colspan="5">
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
	</td>
  </tr>
	


<tr height="30">
  	<td colspan="6"style="background-color:gray ;" ><div>研发评估（硬件）</div>

  	<div align="left">
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
  	</div>
  	</td>
  </tr>

  <tr height="70">
    <td colspan="1" ><div align="center">风险分析</div></td>
    <td colspan="5">
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
	</td>
  </tr>
  
  
   <tr height="70">
    <td colspan="1" ><div align="center">规避措施</div></td>
    <td colspan="5">
		<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E18.count}"/>].fieldText"/>
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
    

  <tr height="70">
    <td colspan="1" ><div align="center">是否纳入项目风险管理</div></td>
    <td colspan="5">
		<c:choose>
					<c:when test="${E19.isEdit == '1'}">
						
							<div style="padding-left: 50px">
									<input type="radio"
										name="fieldContents[<c:out value="${E19.count}"/>].fieldText"
										value="1"
										<c:if test="${E19.fieldText == 1 || empty E19.fieldText}">checked</c:if> />是
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio"
										name="fieldContents[<c:out value="${E19.count}"/>].fieldText"
										value="2"
										<c:if test="${E19.fieldText == 2 || empty E19.fieldText}">checked</c:if> />否
								</div>
						
						
					</c:when>
					<c:otherwise>
						<div style="padding-left: 50px">
									<input type="radio" disabled="disabled"
										<c:if test="${fieldContents[18].fieldText == 1}">checked</c:if> />是
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" disabled="disabled"
										<c:if test="${fieldContents[18].fieldText == 2}">checked</c:if> />否
								</div>
					</c:otherwise>
				</c:choose>
	</td>
  </tr>
  
  <td colspan="1"><div align="center">风险编号</div></td>
    <td colspan="5">&nbsp;
	<c:choose>
	    <c:when test="${E20.isEdit == '1'}">
	        <input type="Text" size="20" id="fieldContents[<c:out value='${E20.count}'/>].fieldText"
	            name="fieldContents[<c:out value='${E20.count}'/>].fieldText" value="<c:out value='${E20.fieldText}'/>" >
	    </c:when>
	    <c:otherwise>
	        <c:out value="${fieldContents[19].fieldText}"/>
	    </c:otherwise>
	</c:choose>
	</td>
  
  
  <tr height="30">
  	<td colspan="6" align="center" style="background-color:yellow ;vertical-align:middle; text-align:center;">审批信息</td>
  </tr>
  
  <tr>
			<td height="40"  colspan="6">
			产品经理意见：</br>
			<c:choose>
			    <c:when test="${E21.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E21.count}"/>].fieldText"><c:out value="${E21.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[20].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
	
			</br>

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
		
		
		<tr>
			<td height="40"  colspan="6">
			审核人意见：</br>
			<c:choose>
			    <c:when test="${E24.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E24.count}"/>].fieldText"><c:out value="${E24.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[23].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
	
			</br>

			签名：
			<c:choose>
			    <c:when test="${E25.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
						<c:if test="${!empty E25.fieldText}">value="<c:out value="${E25.fieldText}"/>"</c:if>
						<c:if test="${empty E25.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[24].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E26.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
						<c:if test="${!empty E26.fieldText}">value="<c:out value="${E26.fieldText}"/>"</c:if>
						<c:if test="${empty E26.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[25].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
		</td>
		</tr>
		
		
		<tr>
			<td height="40"  colspan="6">
			批准人意见：</br>
			<c:choose>
			    <c:when test="${E27.isEdit == '1'}">
			        <textarea class="filedEditbaleColor"  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E27.count}"/>].fieldText"><c:out value="${E27.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[26].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
	
			</br>

			签名：
			<c:choose>
			    <c:when test="${E28.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E28.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
						<c:if test="${!empty E28.fieldText}">value="<c:out value="${E28.fieldText}"/>"</c:if>
						<c:if test="${empty E28.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[27].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;日&nbsp;期：
			<c:choose>
			    <c:when test="${E29.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E29.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
						<c:if test="${!empty E29.fieldText}">value="<c:out value="${E29.fieldText}"/>"</c:if>
						<c:if test="${empty E29.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[28].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
			 &nbsp;
		</td>
		</tr>
</table>
</body>

