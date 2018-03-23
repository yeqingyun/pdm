<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>样品测试申请单</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align=center>
  <tr>
    <td colspan="16" width="16%" height="30" align="center">样品名称</td>
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
	<td colspan="15" width="15%" align="center">送样数量</td>
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
	<td colspan="15" width="15%" align="center">接收样品时间</td>
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
    <td colspan="16" width="16%" height="30" align="center">申请部门</td>
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
	<td colspan="15" width="15%" align="center">申请人</td>
    <td colspan="15" width="15%">
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
	<td colspan="15" width="15%" align="center">计划完成时间</td>
    <td colspan="15" width="15%">
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E6.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  
    <tr>
  	<td colspan="16" width="16%"  align="center">样品描述<br>及测试原因：</td>
    <td colspan="84" width="84%"  height="80">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>详细内容：</td>
			</tr>
			<tr>
				<td height="120" >
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
  
  
  
  
  
  <tr>
  	<td colspan="16" width="16%"  align="center">检测项目及要求:</td>
    <td colspan="84" width="84%"  height="80">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>详细内容：</td>
			</tr>
			<tr>
				<td height="120" >
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
		</table>
	</td>
  </tr>
  


  <tr>
  	<td colspan="16" width="16%"  align="center">备注:</td>
    <td colspan="84" width="84%"  height="80">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>详细内容：</td>
			</tr>
			<tr>
				<td height="90" >
					<c:choose>
						<c:when test="${E9.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E9.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>



  <tr>
    <td colspan="16" width="16%" height="30" align="center">测试人：</td>
	<td colspan="34" width="34%">
		<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E10.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="25" width="25%" align="center">审核：</td>
    <td colspan="25" width="25%">
		<c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E11.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>

</table>