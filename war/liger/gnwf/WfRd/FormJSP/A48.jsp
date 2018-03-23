<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>测试计划报告</strong></p>

<c:if test="${!empty itemList}">
<table align="center" width="720" border="0">
<tr>
	<td>
		<input type="radio" name="a" value="1" checked="checked" onclick="displayCate('tr1');">环境测试
		<input type="radio" name="a" value="2" onclick="displayCate('tr2');">机械强度
		<input type="radio" name="a" value="3" onclick="displayCate('tr3');">寿命测试1
		<input type="radio" name="a" value="4" onclick="displayCate('tr4');">寿命测试2
		<input type="radio" name="a" value="5" onclick="displayCate('tr5');">外观工艺
		<input type="radio" name="a" value="6" onclick="displayCate('tr6');">整机功能性能
		<input type="radio" name="a" value="7" onclick="displayCate('tr7');">其他性能
		<input type="radio" name="a" value="8" onclick="displayCate('tr8');">充电及电量识别精度
		<input type="radio" name="a" value="9" onclick="displayCate('tr9');">ESD
	</td>
</tr>
<tr>
	<td>
    	<table align="center" width="720" border="1" height="65">
    	<c:forEach begin="1" end="9" step="1" varStatus="v">
    		<tr id="tr<c:out value="${v.count}"/>" <c:if test="${v.count>1}">style="display: none;"</c:if>>
    		<td>
    			<c:set var="n" value="0"/>
    			<c:forEach items="${itemList}" var="item" varStatus="vs">
    				<c:if test="${v.count==item.desc1}">
			    		<input type="checkbox" name="checkbox1" value="<c:out value="${item.desc1}"/>"/><c:out value="${item.itemName}"/>
			    		<c:set var="n" value="${n+1}"/>
			    		<c:if test="${n%6==0}"><br></c:if>
		    		</c:if>
		    		
		    	</c:forEach>
		    </td>
    		</tr>
    	</c:forEach>
		</table>
	</td>
</tr>
</table>
</c:if>

<table id="OwnershipStructure" width="720" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
<tr>
	<td colspan="100" width="100%" height="25"  bgcolor="#FFFFB0">
		<table width="100%" border="0">
			<tr>
	<td width="15%" bgcolor="#FFFFB0">&nbsp;</td>
    <td width="70%" align="center" bgcolor="#FFFFB0">测试报告</td>
	<td align="right" width="15%" bgcolor="#FFFFB0">
		<input type="button" id=add class="btn" value=" + " onclick="javascript:AddRow48();"/>&nbsp;
		<input type="button" id=add class="btn" value=" - " onclick='javascript:DelRow48("numI1","StrRight");'/>
	</td>
			</tr>
		</table>	
	</td>
  </tr>
  <tr id=StrRight>
    <td colspan="4" width="4%" height="50" align="center">序号</td>
    <td colspan="10" width="10%" align="center">项目<br>负责人</td>
	<td colspan="20" width="20%" align="center">测试项目</td>
	<td colspan="14" width="14%" align="center">执行者<br>测试工程师</td>
	<td colspan="12" width="12%" align="center">计划<br>开始时间</td>
	<td colspan="12" width="12%" align="center">计划<br>完成时间</td>
	<td colspan="10" width="10%" align="center">测试结果</td>
	<td colspan="18" width="18%" align="center">备注</td>
  </tr>
  <c:set var="rownum" value="0"/>
  
   
   	<c:if test="${!empty rows}">
  	<c:forEach items="${rows}" var="row"> 
  		<c:set var="rownum" value="${rownum + 1}"/>
  				<c:forEach items="${row.fileds}" var="field">
					<c:if test="${field.fieldCode=='E1'}">
						<c:set var="E1" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E2'}">
						<c:set var="E2" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E3'}">
						<c:set var="E3" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E4'}">
						<c:set var="E4" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E5'}">
						<c:set var="E5" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E6'}">
						<c:set var="E6" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E7'}">
						<c:set var="E7" value="${field}"/>
					</c:if>
				</c:forEach>
		 <tr>
		 	<td colspan="4" align="center" height="50"><c:out value="${rownum}"/></td>
			<td colspan="10">
				<c:if test="${E1.isEdit == '1'}">
					<c:out value="${E1.fieldText}"/>
				</c:if>
				<c:if test="${E1.isEdit != '1'}">
					<c:out value="${E1.fieldText}"/>
				</c:if>
			</td>
			<td colspan="20" <c:if test="${E2.isEdit == '1'}">bgcolor="#ffffff"</c:if>>
				<c:if test="${E2.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].fieldId" value="<c:out value="${E2.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E2.count}"/>].fieldText"><c:out value="${E2.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E2.isEdit != '1'}">
					<c:out value="${E2.fieldText}"/>
				</c:if>
			</td>
			<td colspan="14" >
				<c:if test="${E3.isEdit == '1'}">
					<input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].fieldId" value="<c:out value="${E3.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea  rows="4" style="width:94%;overflow:hidden" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E3.isEdit != '1'}">
					<c:out value="${E3.fieldText}"/>
				</c:if>
			</td>
			<td colspan="12">
				<c:if test="${E4.isEdit == '1'}">
					<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
					<input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].fieldId" value="<c:out value="${E4.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				</c:if>
				<c:if test="${E4.isEdit != '1'}">
					<c:out value="${E4.fieldText}"/>
				</c:if>
			</td>
			<td colspan="12">
				<c:if test="${E5.isEdit == '1'}">
					<textarea  rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
					<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].fieldId" value="<c:out value="${E5.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].rowId" value="<c:out value="${rownum}"/>">
				</c:if>
				<c:if test="${E5.isEdit != '1'}">
					<c:out value="${E5.fieldText}"/>
				</c:if>
			</td>
			<td colspan="10">
				<c:if test="${E6.isEdit == '1' && fn:contains(E3.fieldText, user.userName)}">
					<input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].fieldId" value="<c:out value="${E6.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea  rows="4" style="width:92%;overflow:hidden" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E6.isEdit != '1'}">
					<c:out value="${E6.fieldText}"/>
				</c:if>
			</td>
			<td colspan="18">
				<c:if test="${E7.isEdit == '1' && fn:contains(E3.fieldText, user.userName)}">
					<input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].fieldId" value="<c:out value="${E7.fieldId}"/>">
					<input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea  rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
				</c:if>
				<c:if test="${E7.isEdit != '1'}">
					<c:out value="${E7.fieldText}"/>
				</c:if>
			</td>
		</tr>
		
		<c:remove var="E1"/>
		<c:remove var="E2"/>
		<c:remove var="E3"/>
		<c:remove var="E4"/>
		<c:remove var="E5"/>
		<c:remove var="E6"/>
		<c:remove var="E7"/>
  	</c:forEach>
 	</c:if>
 	
   <input type="hidden" id="numI1" value="<c:out value="${rownum+1}"/>"/>
</table>
	