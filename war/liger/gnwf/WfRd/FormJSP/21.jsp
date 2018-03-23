<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面21jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>风险评估单</strong></p>
<!-- 
<table width="680" align="center">
  <tr><td>
  <label for="shouban"></label></td>
    <td align="right">编号:
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
<table width="680" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
  <tr bgcolor="#CCCCCC" height="30">
    <td colspan="6"><div align="center">项目经理/PQA填写</div></td>
  </tr>
  <tr height="30">
    <td width="25%"><div align="center">产品型号</div></td>
    <td width="25%">
		<c:choose>
		    <c:when test="${E1.isEdit == '1'}">
		        <input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
		            name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
		    </c:when>
		    <c:otherwise>
		        <c:out value="${fieldContents[0].fieldText}"/>
		    </c:otherwise>
		</c:choose>
    </td>
    <td width="25%"><div align="center">产品系列</div></td>
    <td width="25%">
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
  <tr height="30">
    <td width="25%"><div align="center">提出阶段</div></td>
    <td width="25%">
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
    <td width="25%"><div align="center">申请人</div></td>
    <td width="25%">
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
    <td height="127" colspan="6" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">问题描述：</td>
	    </tr>
		<tr>
		  <td height="70" >&nbsp;
			<c:choose>
			    <c:when test="${E5.isEdit == '1'}">
			        <textarea  rows="4" cols="68" style="width: 660px" id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="68" readonly="readonly" style="width: 660px" class="textarea-hidden"><c:out value="${fieldContents[4].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
          </td>
	    </tr>
	    
	    
	    <tr>
		  <td height="25">是否有缓解措施：
			<c:choose>
			    <c:when test="${E6.isEdit == '1'}">
			        <input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="1"
			            <c:if test="${E6.fieldText == 1 || empty E6.fieldText}">checked</c:if> />是&nbsp;
			        <input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="2"
			            <c:if test="${E6.fieldText == 2 || empty E6.fieldText}">checked</c:if> />否&nbsp;
			    </c:when>
			    <c:otherwise>
			        <input type="radio" disabled="disabled" <c:if test="${fieldContents[5].fieldText == 1}">checked</c:if> />是&nbsp;
			        <input type="radio" disabled="disabled" <c:if test="${fieldContents[5].fieldText == 2}">checked</c:if> />否&nbsp;
			    </c:otherwise>
			</c:choose>
		</td>
	    </tr>
		<tr>
		  <td height="56" >&nbsp;
			<c:choose>
			    <c:when test="${E7.isEdit == '1'}">
			        <textarea  rows="4" cols="68" style="width: 660px" id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="68" style="width: 660px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[6].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
          </td>
	    </tr>
	    
	    
        
		<tr>
		  <td height="25" align="right">
		  
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">部门经理审核：</td>
	    </tr>
		<tr>
		  <td height="70" >&nbsp;
			<c:choose>
			    <c:when test="${E26.isEdit == '1'}">
			        <textarea rows="4" cols="68" style="width: 660px" id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E26.count}"/>].fieldText"><c:out value="${E26.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="68" readonly="readonly" style="width: 660px" class="textarea-hidden"><c:out value="${fieldContents[25].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
          </td>
	    </tr>

  
		<tr>
		  <td height="25" align="right">签  名：
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
		    &nbsp; </td>
	    </tr>
     </table>
		  
		  <%-- 部门经理审核：<c:out value="${E8.isEdit}"/>
			<c:choose>
			    <c:when test="${E8.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
						<c:if test="${!empty E8.fieldText}">value="<c:out value="${E8.fieldText}"/>"</c:if>
						<c:if test="${empty E8.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${E8.fieldText}"/>" disabled="disabled"
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
			        <input type="Text" size="20" value="<c:out value="${E9.fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose> --%>
		    &nbsp; </td>
	    </tr>
     </table></td>
  </tr>
  
  
    <tr>
    <td height="100" colspan="6" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">DQA意见：</td>
	    </tr>
		<tr>
		  <td height="70" >&nbsp;
			<c:choose>
			    <c:when test="${E10.isEdit == '1'}">
			        <textarea  rows="4" cols="68" style="width: 660px" id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="68" readonly="readonly" style="width: 660px" class="textarea-hidden"><c:out value="${fieldContents[9].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
          </td>
	    </tr>

  
		<tr>
		  <td height="25" align="right">签  名：
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
		    &nbsp; </td>
	    </tr>
     </table></td>
  </tr>
  
  
    <tr>
    <td height="100" colspan="6" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">项目经理意见：</td>
	    </tr>
		<tr>
		  <td height="70" >&nbsp;
			<c:choose>
			    <c:when test="${E13.isEdit == '1'}">
			        <textarea  rows="4" cols="68" style="width: 660px" id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="68" readonly="readonly" style="width: 660px" class="textarea-hidden"><c:out value="${fieldContents[12].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
          </td>
	    </tr>

  
		<tr>
		  <td height="25" align="right">签  名：
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
		    &nbsp; </td>
	    </tr>
     </table></td>
  </tr>
  
  
    <tr>
    <td height="100" colspan="6" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">研发总监意见：</td>
	    </tr>
		<tr>
		  <td height="70" >&nbsp;
			<c:choose>
			    <c:when test="${E16.isEdit == '1'}">
			        <textarea  rows="4" cols="68" style="width: 660px" id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E16.count}"/>].fieldText"><c:out value="${E16.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="68" readonly="readonly" style="width: 660px" class="textarea-hidden"><c:out value="${fieldContents[15].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
          </td>
	    </tr>

  
		<tr>
		  <td height="25" align="right">签  名：
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
		    &nbsp; </td>
	    </tr>
     </table></td>
  </tr>
  
    <c:if test="${prjtDef.devDeptNameID==3}">
  <tr>
    <td height="100" colspan="6" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">研发副总意见：</td>
	    </tr>
		<tr>
		  <td height="70" >&nbsp;
			<c:choose>
			    <c:when test="${E19.isEdit == '1'}">
			        <textarea  rows="4" cols="68" style="width: 660px" id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E19.count}"/>].fieldText"><c:out value="${E19.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="68" readonly="readonly" style="width: 660px" class="textarea-hidden"><c:out value="${fieldContents[18].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
          </td>
	    </tr>

  
		<tr>
		  <td height="25" align="right">签  名：
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
		    &nbsp; </td>
	    </tr>
     </table></td>
  </tr>
  </c:if>
  
<tr>
    <td colspan="100">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>公司意见：</td>
		</tr>
		<tr>
    <td colspan="100" width="100%"  height="50">
	<table width="100%">
		<c:choose>
			<c:when test="${E22.isEdit == '1'}">
					<tr>
					<td width="25%" bordercolor="#FFFFFF" >
						<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="1" 
								<c:if test="${E22.fieldText == 1 || empty E22.fieldText}">checked</c:if> />同意&nbsp;
						</td>
				   </tr>
				   
				  <tr>
					<td  bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="2" 
								<c:if test="${E22.fieldText == 2}">checked</c:if> />不同意&nbsp;
						
					</td>
				 </tr>
				 <tr>
					<td   bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
					
					  <input type="radio" name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="3" 
								<c:if test="${E22.fieldText == 3}">checked</c:if> />其它&nbsp;
					  <input type="Text" size="50" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
								 name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="<c:out value="${E23.fieldText}"/>" 
								 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
						
					</td>
				  </tr>
	</c:when>
	<c:otherwise>
		         <tr>
					<td  width="25%" bordercolor="#FFFFFF" >
						<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[21].fieldText == 1}">checked</c:if> />同意&nbsp;
						</td>
				   </tr>
				   
				  <tr>
					<td  bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[21].fieldText == 2}">checked</c:if> />不同意&nbsp;
						
					</td>
				 </tr>
				 <tr>
					<td   bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
					  <input type="radio" disabled="disabled" <c:if test="${fieldContents[21].fieldText == 3}">checked</c:if> />其它&nbsp;
					  <input type="Text" size="50" value="<c:out value="${fieldContents[22].fieldText}"/>" disabled="disabled"
					   style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
						
					</td>
				  </tr>
	  </c:otherwise>
    </c:choose>
	</table>
	</td>
  </tr>
	<tr>
		<td height="25" align="right" style="padding-top: 10px;">
			签&nbsp;名：
		  <c:choose>
				<c:when test="${E24.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
						 <c:if test="${!empty E24.fieldText}">value="<c:out value="${E24.fieldText}"/>"</c:if>
						 <c:if test="${empty E24.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[23].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
		
		&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 <c:if test="${!empty E25.fieldText}">value="<c:out value="${E25.fieldText}"/>"</c:if>
					 <c:if test="${empty E25.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[24].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			</td>
		</tr>
	  </table>
	
	</td>
  </tr>
  
</table>
<table width="680" align="center" style="font-size:13px">
<tr>
  <td width="506" align="left">&nbsp;</font>
  </td>
  <td width="162" align="right">表格编号：RCT-001</td>
  </tr>
</table>
