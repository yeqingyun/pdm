<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>程序开发描述</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align="center">
  <tr>
    <td colspan="16" width="16%" height="30" align="center">项目名称</td>
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
	<td colspan="15" width="15%" align="center">要求时间</td>
    <td colspan="15" width="15%">
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
	<td colspan="15" width="15%" align="center">备注</td>
    <td colspan="15" width="15%">
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E3.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" rowspan="2" align="center">开发描述：</td>
    <td colspan="84" width="84%">类型：
    	<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="1" 
		  			<c:if test="${E4.fieldText == 1 || empty E4.fieldText}">checked</c:if> />新开发&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="2" 
		  			<c:if test="${E4.fieldText == 2}">checked</c:if> />修改&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E4.fieldText == 1}">checked</c:if> />新开发&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E4.fieldText == 2}">checked</c:if> />修改&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>开发细节：</td>
			</tr>
			<tr>
				<td height="100" >
					<c:choose>
						<c:when test="${E5.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E5.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
  
  
  
    <tr>
    <td colspan="16" width="16%" rowspan="2" align="center">责任人:</td>
    <td colspan="84" width="84%">是否完毕：
    	<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="1" 
		  			<c:if test="${E6.fieldText == 1 || empty E6.fieldText}">checked</c:if> />已完毕&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="2" 
		  			<c:if test="${E6.fieldText == 2}">checked</c:if> />未完毕&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E6.fieldText == 1}">checked</c:if> />已完毕&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E6.fieldText == 2}">checked</c:if> />未完毕&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>回复内容：</td>
			</tr>
			<tr>
				<td height="70" >
					<c:choose>
						<c:when test="${E7.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E7.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
  


</table>