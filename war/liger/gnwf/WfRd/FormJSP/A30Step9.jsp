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
<strong>料号申请单(采购转仓库填单表)</strong></p>

<!-- fieldText 没有ID 则可以为空  -->
<table id="OwnershipStructure" width="980" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0   style='border-collapse:collapse;background-color:#EFF0F2;font-size:11.0pt;word-break:break-all; word-wrap:break-all;'>
  <tr>
    <td colspan="105" width="105%" height="30">
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
	    <td colspan="8" width="8%" align="center">物料组</td>
	    <td colspan="8" width="8%" align="center">物料<br>编码</td>
	    <td colspan="20" width="20%" align="center">物料描述</td>
	    <td colspan="13" width="13%" align="center">大小<br/>量纲</td>
	    <td colspan="8" width="8%" align="center">净重</td>
	    <td colspan="8" width="8%" align="center">毛重</td>
	    <td colspan="8" width="8%" align="center">重量<br>单位</td>
	    <td colspan="8" width="8%" align="center">是否<br>保税仓</td>
	    <td colspan="8" width="8%" align="center">生产仓储地点</td>
	    <td colspan="8" width="8%" align="center">外部采购地点</td>
	    
	</tr>		
	

	<c:set var="rownum" value="0"/>
	<c:if test="${empty rows}">
		<c:set var="rownum" value="${rownum + 1}"/>
		<tr>
			<td colspan="3" width="3%" height="50" align="center">1</td>
			<td colspan="8" width="8%">
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
					
					<c:if test="${field.fieldCode=='E35'}">
						<c:set var="F35" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E34'}">
						<c:set var="F34" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E36'}">
						<c:set var="F36" value="${field}"/>
					</c:if>
				</c:forEach>
		 <tr>
		 	<td colspan="3" align="center" height="50"><c:out value="${rownum}"/></td>
		 	<td colspan="8">
				<c:out value="${E2.fieldText}"/>
			</td>
			<td colspan="8">
				<c:out value="${E3.fieldText}"/>
			</td>
			<td colspan="20" >
				<c:out value="${E4.fieldText}"/>
			</td>
			<td colspan="13">
				<c:out value="${E5.fieldText}"/>
			</td>
			
			<td colspan="8">
				<c:out value="${F14.fieldText}"/>
			</td>
			<td colspan="8">
				<c:out value="${F15.fieldText}"/>
			</td>
			<td colspan="8">
				<c:out value="${F16.fieldText}"/>
			</td>
			
			<td colspan="8">
				<c:out value="${F35.fieldText}"/>
			</td>
			
			<td colspan="8">
				<c:if test="${empty F34}">
					<c:set var="F34Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F34}">
					<c:set var="F34Count" value="${F34.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F34Count}"/>].fieldId" value="1022">  
					<input type="hidden" name="fieldContents[<c:out value="${F34Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F34Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F34Count}"/>].fieldText"><c:out value="${F34.fieldText}"/></textarea>
			</td>
			<td colspan="8">
				<c:if test="${empty F36}">
					<c:set var="F36Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F36}">
					<c:set var="F36Count" value="${F36.count}"/>
				</c:if>
				 	<input type="hidden" name="fieldContents[<c:out value="${F36Count}"/>].fieldId" value="1024"> 
					<input type="hidden" name="fieldContents[<c:out value="${F36Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F36Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F36Count}"/>].fieldText"><c:out value="${F36.fieldText}"/></textarea>
			</td>
			
			
			
			
			
			
			
		</tr>
		
		<c:remove var="E2"/>
		<c:remove var="E3"/>
		<c:remove var="E4"/>
		<c:remove var="E5"/>
		
		<c:remove var="F14"/>
		<c:remove var="F15"/>
		<c:remove var="F16"/>
		
		
		<c:remove var="F35"/>
		<c:remove var="F34"/>
		<c:remove var="F36"/>
		
  	</c:forEach>
 	</c:if>
 <input type="hidden" id="numI1" value="<c:out value="${rownum+1}"/>"/>
 
</table>
