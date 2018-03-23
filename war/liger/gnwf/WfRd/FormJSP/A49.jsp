<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>新信息系统立项申请表</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066EE cellpadding=3 align=center>
    <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">
  		<p style='font-size:12.0pt;font-family:  宋体;'><strong>项目信息</strong></p>
    </td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="36" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>项目名称</td>
	<td colspan="24" width="24%">
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E1.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>申请日期</td>
    <td colspan="45" width="45%">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E2.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="150" align="center" bgcolor="#F0F0F0">项目综<br>合说明</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<textarea  rows="8" cols="80" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="8" cols="80" readonly="readonly" class="textarea-hidden"><c:out value="${E3.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>

  <tr>
    <td colspan="16" width="16%" height="36" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>项目推进部门</td>
	<td colspan="24" width="24%">
		<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E4.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>项目责任人</td>
    <td colspan="45" width="45%">
		<c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E5.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>



  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">
    	<p style='font-size:12.0pt;font-family:  宋体;'><strong>审批意见栏</strong></p>
    </td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">职能部门经理汇签</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" height="50">
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E6.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E7.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
						 <c:if test="${!empty E7.fieldText}">value="<c:out value="${E7.fieldText}"/>"</c:if>
						 <c:if test="${empty E7.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E7.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>&nbsp;
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">职能部门主管副总</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" height="50">
		<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E8.count}"/>].fieldText"><c:out value="${E8.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E8.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E9.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
						 <c:if test="${!empty E9.fieldText}">value="<c:out value="${E9.fieldText}"/>"</c:if>
						 <c:if test="${empty E9.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E9.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>&nbsp;
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">信息中心经理</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" height="50">
		<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E10.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E11.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
						 <c:if test="${!empty E11.fieldText}">value="<c:out value="${E11.fieldText}"/>"</c:if>
						 <c:if test="${empty E11.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E11.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>&nbsp;
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">信息中心总监</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" height="50">
		<c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E12.count}"/>].fieldText"><c:out value="${E12.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E12.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E13.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
						 <c:if test="${!empty E13.fieldText}">value="<c:out value="${E13.fieldText}"/>"</c:if>
						 <c:if test="${empty E13.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E13.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>&nbsp;
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">信息中心主管副总</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" height="50">
		<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E14.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="84" width="84%" align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E15.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
						 <c:if test="${!empty E15.fieldText}">value="<c:out value="${E15.fieldText}"/>"</c:if>
						 <c:if test="${empty E15.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E15.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>&nbsp;
			</td>
		</tr>
		</table>
	</td>
  </tr>
</table>