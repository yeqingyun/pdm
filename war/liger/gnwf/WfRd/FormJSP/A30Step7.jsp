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
<strong>料号申请单(计调填单表)</strong></p>

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
	    <td colspan="3" width="3%" height="50" align="center">序号</td>
	    <td colspan="8" width="8%" align="center">物料组<br></td>
	    <td colspan="9" width="9%" align="center">物料<br>编码</td>
	    <td colspan="20" width="20%" align="center">物料描述</td>
	    <td colspan="12" width="12%" align="center">大小量纲</td>
	    <td colspan="8" width="8%" align="center">部件<br>报废率</td>
	    <td colspan="5" width="5%" align="center">MRP<br>类型</td>
	    <td colspan="6" width="6%" align="center">MRP<br>控制者</td>
	    <td colspan="5" width="5%" align="center">批量<br>周期</td>
	    <td colspan="9" width="9%" align="center">最大<br/>库存水平</td>
	    <td colspan="8" width="8%" align="center">安全库存</td>
	    <td colspan="7" width="7%" align="center">生产<br/>调度员</td>
	    <!-- <td colspan="7" width="7%" align="center">外部采<br/>购地点</td> -->
	</tr>	
	

	<c:set var="rownum" value="0"/>
	<c:if test="${empty rows}">
		<c:set var="rownum" value="${rownum + 1}"/>
		<tr>
			<td colspan="3" width="3%" height="50" align="center">1</td>
			<td colspan="7" width="7%">
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
					
					
					<c:if test="${field.fieldCode=='E27'}">
						<c:set var="F27" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E28'}">
						<c:set var="F28" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E29'}">
						<c:set var="F29" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E30'}">
						<c:set var="F30" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E31'}">
						<c:set var="F31" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E32'}">
						<c:set var="F32" value="${field}"/>
					</c:if>
					<c:if test="${field.fieldCode=='E33'}">
						<c:set var="F33" value="${field}"/>
					</c:if>
					
				</c:forEach>
		 <tr>
		 	<td colspan="3" align="center" height="50"><c:out value="${rownum}"/></td>
			<td colspan="8">
				<c:out value="${E2.fieldText}"/>
			</td>
			<td colspan="9">
				<c:out value="${E3.fieldText}"/>
			</td>
			<td colspan="20" >
				<c:out value="${E4.fieldText}"/>
			</td>
			<td colspan="12">
				<c:out value="${E5.fieldText}"/>
			</td>
			
			<td colspan="8">
				<c:if test="${empty F27}">
					<c:set var="F27Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F27}">
					<c:set var="F27Count" value="${F27.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F27Count}"/>].fieldId" value="1015"> 
					<input type="hidden" name="fieldContents[<c:out value="${F27Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:86%;overflow:hidden" id="fieldContents[<c:out value="${F27Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F27Count}"/>].fieldText"><c:out value="${F27.fieldText}"/></textarea>
						
			</td>
			<td colspan="5">
				<c:if test="${empty F28}">
					<c:set var="F28Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F28}">
					<c:set var="F28Count" value="${F28.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F28Count}"/>].fieldId" value="1016"> 
					<input type="hidden" name="fieldContents[<c:out value="${F28Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F28Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F28Count}"/>].fieldText"><c:out value="${F28.fieldText}"/></textarea>
						
			</td>
			<td colspan="6">
				<c:if test="${empty F29}">
					<c:set var="F29Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F29}">
					<c:set var="F29Count" value="${F29.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F29Count}"/>].fieldId" value="1017">  
					<input type="hidden" name="fieldContents[<c:out value="${F29Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:91%;overflow:hidden" id="fieldContents[<c:out value="${F29Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F29Count}"/>].fieldText"><c:out value="${F29.fieldText}"/></textarea>
						
			</td>
			<td colspan="5">
				<c:if test="${empty F30}">
					<c:set var="F30Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F30}">
					<c:set var="F30Count" value="${F30.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F30Count}"/>].fieldId" value="1018">  
					<input type="hidden" name="fieldContents[<c:out value="${F30Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${F30Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F30Count}"/>].fieldText"><c:out value="${F30.fieldText}"/></textarea>
						
			</td>
			<td colspan="9">
				<c:if test="${empty F31}">
					<c:set var="F31Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F31}">
					<c:set var="F31Count" value="${F31.count}"/>
				</c:if>
					  <input type="hidden" name="fieldContents[<c:out value="${F31Count}"/>].fieldId" value="1019">  
					<input type="hidden" name="fieldContents[<c:out value="${F31Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:89%;overflow:hidden" id="fieldContents[<c:out value="${F31Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F31Count}"/>].fieldText"><c:out value="${F31.fieldText}"/></textarea>
						
			</td>
			<td colspan="8">
				<c:if test="${empty F32}">
					<c:set var="F32Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F32}">
					<c:set var="F32Count" value="${F32.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F32Count}"/>].fieldId" value="1020">  
					<input type="hidden" name="fieldContents[<c:out value="${F32Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:90%;overflow:hidden" id="fieldContents[<c:out value="${F32Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F32Count}"/>].fieldText"><c:out value="${F32.fieldText}"/></textarea>
			</td>
			
			<td colspan="7">
				<c:if test="${empty F33}">
					<c:set var="F33Count" value="${size}"/>
					<c:set var="size" value="${size+1}"/>
				</c:if>
				<c:if test="${!empty F33}">
					<c:set var="F33Count" value="${F33.count}"/>
				</c:if>
					 <input type="hidden" name="fieldContents[<c:out value="${F33Count}"/>].fieldId" value="1021">  
					<input type="hidden" name="fieldContents[<c:out value="${F33Count}"/>].rowId" value="<c:out value="${rownum}"/>">
					<textarea class="filedEditbaleColor"  rows="4" style="width:84%;overflow:hidden" id="fieldContents[<c:out value="${F33Count}"/>].fieldText" 
						name="fieldContents[<c:out value="${F33Count}"/>].fieldText"><c:out value="${F33.fieldText}"/></textarea>
			</td>
		</tr>
		
		<c:remove var="E2"/>
		<c:remove var="E3"/>
		<c:remove var="E4"/>
		<c:remove var="E5"/>
		

		<c:remove var="F27"/>
		<c:remove var="F28"/>
		<c:remove var="F29"/>
		<c:remove var="F30"/>
		<c:remove var="F31"/>
		<c:remove var="F32"/>
		<c:remove var="F33"/>
  	</c:forEach>
 	</c:if>
 <input type="hidden" id="numI1" value="<c:out value="${rownum+1}"/>"/>
 
</table>
