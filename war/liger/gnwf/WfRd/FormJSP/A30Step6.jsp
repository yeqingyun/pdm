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
<strong>料号申请单（财务填单）</strong></p>

<!-- fieldText 没有ID 则可以为空  -->
<table id="OwnershipStructure" width="920" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0   style='border-collapse:collapse;background-color:#EFF0F2;font-size:11.0pt;word-break:break-all; word-wrap:break-all;'>
  <tr>
    <td colspan="100" width="100%" height="30">
		<table border="0" width="100%">
			<tr>
				<td colspan="15" width="15%">&nbsp;</td>
			    <td colspan="70" width="70%"  align="center">申请表</td>
			    <td colspan="15" width="15%"  align="right">&nbsp;</td>
			</tr>
		</table>
	</td>
  </tr>

    <tr id="StrRight">
		<td colspan="3" width="3%" align="center" height="50">序号</td>
		<td colspan="8" width="8%" align="center">物料组</td>
		<td colspan="9" width="9%" align="center">物料<br>编码</td>
		<td colspan="18" width="18%" align="center">物料描述</td>
		<td colspan="13" width="13%" align="center">大小量纲</td>
		<td colspan="17" width="17%" align="center">评估类</td>
		<td colspan="16" width="16%" align="center">价格标识</td>
		<td colspan="16" width="16%" align="center">单价*1000</td>
	</tr>

	<c:set var="rownum" value="0"/>
	<c:if test="${empty rows}">
		<c:set var="rownum" value="${rownum + 1}"/>
		<tr>
			<td colspan="3" width="3%" height="50" align="center">1</td>
			<td colspan="8" width="8%">
				<input type="hidden" name="fieldContents[<c:out value="${E1.count}"/>].rowId" value="1">
				<textarea rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E1.count}"/>].fieldText"><c:out value="${E1.fieldText}"/></textarea>
			</td>
			<td colspan="9" width="9%">
				<input type="hidden" name="fieldContents[<c:out value="${E2.count}"/>].rowId" value="1">
				<textarea rows="4" style="width:88%;overflow:hidden" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E2.count}"/>].fieldText"><c:out value="${E2.fieldText}"/></textarea>
			</td>
			<td colspan="18" width="18%">
				<input type="hidden" name="fieldContents[<c:out value="${E3.count}"/>].rowId" value="1">
				<textarea rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E3.count}"/>].fieldText"><c:out value="${E3.fieldText}"/></textarea>
			</td>
			<td colspan="13" width="13%">
				<input type="hidden" name="fieldContents[<c:out value="${E4.count}"/>].rowId" value="1">
				<textarea rows="4" style="width:96%;overflow:hidden" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E4.count}"/>].fieldText"><c:out value="${E4.fieldText}"/></textarea>
			</td>
			<td colspan="17" width="17%">
				<input type="hidden" name="fieldContents[<c:out value="${E5.count}"/>].rowId" value="1">
				<textarea rows="4" style="width:93%;overflow:hidden" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
			</td>
			<td colspan="16" width="16%">
				<input type="hidden" name="fieldContents[<c:out value="${E6.count}"/>].rowId" value="1">
				<textarea rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E6.count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
			</td>
			<td colspan="16" width="16%">
				<input type="hidden" name="fieldContents[<c:out value="${E7.count}"/>].rowId" value="1">
				<textarea rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
			</td>
		</tr>
	</c:if>
	
	<c:if test="${!empty rows}">
  	<c:forEach items="${rows}" var="row"> 
  		<c:set var="rownum" value="${rownum + 1}"/>
  				<c:forEach items="${row.fileds}" var="field">
					<c:if test="${field.fieldCode=='E2'}">
						<c:set var="E1" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E3'}">
						<c:set var="E2" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E4'}">
						<c:set var="E3" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E5'}">
						<c:set var="E4" value="${field}"/>
					</c:if>
					
					<c:if test="${field.fieldCode=='E54'}">
						<c:set var="E5" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E55'}">
						<c:set var="E6" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E56'}">
						<c:set var="E7" value="${field}"/>
					</c:if>
				</c:forEach>
		 <tr>
		 	<td colspan="3" align="center" height="50"><c:out value="${rownum}"/></td>
			<td colspan="8">
				<c:out value="${E1.fieldText}"/>
			</td>
			<td colspan="9">
				<c:out value="${E2.fieldText}"/>
			</td>
			<td colspan="18" >
				<c:out value="${E3.fieldText}"/>
			</td>
			<td colspan="13">
				<c:out value="${E4.fieldText}"/>
			</td>
			<td colspan="17">
				<c:if test="${empty E5}">
					<c:set var="E5Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty E5}">
					<c:set var="E5Count" value="${E5.count}"/>
				</c:if>
					<input type="hidden" name="fieldContents[<c:out value="${E5Count}"/>].fieldId" value="1042">
					<input type="hidden" name="fieldContents[<c:out value="${E5Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E5Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E5Count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
			</td>
			<td colspan="16">
				<c:if test="${empty E6}">
					<c:set var="E6Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty E6}">
					<c:set var="E6Count" value="${E6.count}"/>
				</c:if>
					<input type="hidden" name="fieldContents[<c:out value="${E6Count}"/>].fieldId" value="1043">
					<input type="hidden" name="fieldContents[<c:out value="${E6Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E6Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E6Count}"/>].fieldText"><c:out value="${E6.fieldText}"/></textarea>
			</td>
			<td colspan="16">
				<c:if test="${empty E7}">
					<c:set var="E7Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty E7}">
					<c:set var="E7Count" value="${E7.count}"/>
				</c:if>
					<input type="hidden" name="fieldContents[<c:out value="${E7Count}"/>].fieldId" value="1044">
					<input type="hidden" name="fieldContents[<c:out value="${E7Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea rows="4" style="width:95%;overflow:hidden" id="fieldContents[<c:out value="${E7Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E7Count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
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
