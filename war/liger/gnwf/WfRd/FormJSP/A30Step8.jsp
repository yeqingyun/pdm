<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>


<%@ page import="java.util.List"%>
<%@ page import="gnwf.vo.WfRdField"%>
<%--  <script language="JavaScript" src="./include/js/gnwf/WfRd_special.js"></script> 
--%>
    <%@ include file="../IncudeExtendField.jsp"%> 
 
 
 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title> 
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="./include/css/global.css" />
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css" / >
<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
 <script src="./include/js/Xmlhttp.js" type="text/javascript"></script> 
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="./include/js/gnwf/WfRd_special.js" type="text/javascript"></script>



<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>料号申请单(采购填单表)</strong></p>

<!-- fieldText 没有ID 则可以为空  -->
<table id="OwnershipStructure" width="980" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0   style='border-collapse:collapse;background-color:#EFF0F2;font-size:11.0pt;word-break:break-all; word-wrap:break-all;'>
  <tr>
    <td colspan="100" width="100%" height="30">
		<table border="0" width="100%">
			<tr>
				<td colspan="10" width="10%">&nbsp;</td>
			    <td colspan="85" width="85%"  align="center">申请表</td>
			    <td colspan="10" width="10%"  align="right">&nbsp;</td>
			</tr>
		</table>
	</td>
  </tr>

    <tr id="StrRight">
	    <td colspan="3" width="3%" height="50" align="center">序号</td>
	    <td colspan="6" width="6%" align="center">物料组</td>
	    <td colspan="8" width="8%" align="center">物料<br>编码</td>
	    <td colspan="11" width="11%" align="center">物料描述</td>
	    <td colspan="6" width="6%" align="center">大小<br/>量纲</td>
	    <td colspan="6" width="6%" align="center">净重</td>
	    <td colspan="6" width="6%" align="center">毛重</td>
	    <td colspan="5" width="5%" align="center">重量<br>单位</td>
	    <td colspan="6" width="6%" align="center">采购<br>周期</td>
	    <td colspan="5" width="5%" align="center">最小<br>批量</td>
	    <td colspan="7" width="7%" align="center">海关<br>型号</td>
	    <td colspan="6" width="6%" align="center">海关<br>物料号</td>
	    <td colspan="5" width="5%" align="center">采购<br>单位</td>
	    <td colspan="5" width="5%" align="center">分子</td>
	    <td colspan="5" width="5%" align="center">舍入值</td>
	    <td colspan="5" width="5%" align="center">分母</td>
	    <td colspan="5" width="5%" align="center">是否<br>保税仓</td>
	</tr>		
	

	<c:set var="rownum" value="0"/>
	<c:if test="${empty rows}">
		<c:set var="rownum" value="${rownum + 1}"/>
		<tr>
			<td colspan="3" width="3%" height="50" align="center">1</td>
			<td colspan="6" width="6%">
				<input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].rowId" value="1">
				<textarea class="filedEditbaleColor"  rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
			</td>
		</tr>
	</c:if>
	
	<c:if test="${!empty rows}">
  	<c:forEach items="${rows}" var="row"> 
  		<c:set var="rownum" value="${rownum + 1}"/>
  				<c:forEach items="${row.fileds}" var="field">
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
					
					<c:if test="${field.fieldCode=='E14'}">
						<c:set var="F14" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E15'}">
						<c:set var="F15" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E16'}">
						<c:set var="F16" value="${field}"/>
					</c:if>
					
					<c:if test="${field.fieldCode=='E19'}">
						<c:set var="F19" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E20'}">
						<c:set var="F20" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E21'}">
						<c:set var="F21" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E22'}">
						<c:set var="F22" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E23'}">
						<c:set var="F23" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E24'}">
						<c:set var="F24" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E25'}">
						<c:set var="F25" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E26'}">
						<c:set var="F26" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E35'}">
						<c:set var="F27" value="${field}"/>
					</c:if>
				</c:forEach>
		 <tr>
		 	<td colspan="3" align="center" height="50"><c:out value="${rownum}"/></td>
		 	<td colspan="6">
				<c:out value="${E2.fieldText}"/>
			</td>
			<td colspan="8">
				<c:out value="${E3.fieldText}"/>
			</td>
			<td colspan="11" >
				<c:out value="${E4.fieldText}"/>
			</td>
			<td colspan="6">
				<c:out value="${E5.fieldText}"/>
			</td>
			
			<td colspan="6">
				<c:out value="${F14.fieldText}"/>
			</td>
			<td colspan="6">
				<c:out value="${F15.fieldText}"/>
			</td>
			<td colspan="5">
				<c:out value="${F16.fieldText}"/>
			</td>
			
			
			<td colspan="6">
				<c:if test="${empty F19}">
					<c:set var="F19Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F19}">
					<c:set var="F19Count" value="${F19.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F19Count}"/>].fieldId" value="1007">  
					<input type="hidden" name="fieldContents[<c:out value="${F19Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${F19Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F19Count}"/>].fieldText"><c:out value="${F19.fieldText}"/></textarea>
			</td>
			<td colspan="5">
				<c:if test="${empty F20}">
					<c:set var="F20Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F20}">
					<c:set var="F20Count" value="${F20.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F20Count}"/>].fieldId" value="1008">  
					<input type="hidden" name="fieldContents[<c:out value="${F20Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F20Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F20Count}"/>].fieldText"><c:out value="${F20.fieldText}"/></textarea>
			</td>
			<td colspan="7">
				<c:if test="${empty F21}">
					<c:set var="F21Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F21}">
					<c:set var="F21Count" value="${F21.count}"/>
				</c:if>
				 	<input type="hidden" name="fieldContents[<c:out value="${F21Count}"/>].fieldId" value="1009"> 
					<input type="hidden" name="fieldContents[<c:out value="${F21Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F21Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F21Count}"/>].fieldText"><c:out value="${F21.fieldText}"/></textarea>
			</td>
			<td colspan="6">
				<c:if test="${empty F22}">
					<c:set var="F22Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F22}">
					<c:set var="F22Count" value="${F22.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F22Count}"/>].fieldId" value="1010"> 
					<input type="hidden" name="fieldContents[<c:out value="${F22Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F22Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F22Count}"/>].fieldText"><c:out value="${F22.fieldText}"/></textarea>
			</td>
			<td colspan="5">
				<c:if test="${empty F23}">
					<c:set var="F23Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F23}">
					<c:set var="F23Count" value="${F23.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F23Count}"/>].fieldId" value="1011"> 
					<input type="hidden" name="fieldContents[<c:out value="${F23Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F23Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F23Count}"/>].fieldText"><c:out value="${F23.fieldText}"/></textarea>
			</td>
			<td colspan="5">
				<c:if test="${empty F24}">
					<c:set var="F24Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F24}">
					<c:set var="F24Count" value="${F24.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F24Count}"/>].fieldId" value="1012"> 
					<input type="hidden" name="fieldContents[<c:out value="${F24Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F24Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F24Count}"/>].fieldText"><c:out value="${F24.fieldText}"/></textarea>
			</td>
			
			<td colspan="5">
				<c:if test="${empty F25}">
					<c:set var="F25Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F25}">
					<c:set var="F25Count" value="${F25.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F25Count}"/>].fieldId" value="1013"> 
					<input type="hidden" name="fieldContents[<c:out value="${F25Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${F25Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F25Count}"/>].fieldText"><c:out value="${F25.fieldText}"/></textarea>
			</td>
			<td colspan="5">
				<c:if test="${empty F26}">
					<c:set var="F26Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F26}">
					<c:set var="F26Count" value="${F26.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F26Count}"/>].fieldId" value="1014"> 
					<input type="hidden" name="fieldContents[<c:out value="${F26Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${F26Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F26Count}"/>].fieldText"><c:out value="${F26.fieldText}"/></textarea>
			</td>
			<td colspan="5">
				<c:if test="${empty F27}">
					<c:set var="F27Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F27}">
					<c:set var="F27Count" value="${F27.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F27Count}"/>].fieldId" value="1023"> 
					<input type="hidden" name="fieldContents[<c:out value="${F27Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${F27Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F27Count}"/>].fieldText"><c:out value="${F27.fieldText}"/></textarea>
			</td>
		</tr>
		
		<c:remove var="E2"/>
		<c:remove var="E3"/>
		<c:remove var="E4"/>
		<c:remove var="E5"/>
		
		<c:remove var="F14"/>
		<c:remove var="F15"/>
		<c:remove var="F16"/>
		
		
		<c:remove var="F19"/>
		<c:remove var="F20"/>
		<c:remove var="F21"/>
		<c:remove var="F22"/>
		<c:remove var="F23"/>
		<c:remove var="F24"/>
		<c:remove var="F25"/>
		<c:remove var="F26"/>
		<c:remove var="F27"/>
  	</c:forEach>
 	</c:if>
 <input type="hidden" id="numI1" value="<c:out value="${rownum+1}"/>"/>
 
</table>
