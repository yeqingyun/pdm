<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>

<%@ include file="../IncudeExtendField.jsp"%>
<link type="text/css" rel="stylesheet" href="./include/css/filed.css"/>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="./include/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="./include/kindeditor-4.1.10/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="./include/kindeditor-4.1.10/lang/zh_CN.js"></script>
<span style="visibility:hidden">页面26jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>规格变更申请</strong></p>

<table id="OwnershipStructure" width="700" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
	 
  <tr>
    <td colspan="17" width="17%" height="30" align="center">产品型号</td>
    <td colspan="33" width="33%"><c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[0].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17" width="17%" align="center">申请日期</td>
    <td colspan="33" width="33%" ><c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[1].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  
  <tr>
    <td colspan="17" width="17%" height="30" align="center">申请部门</td>
    <td colspan="33" width="33%"><c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[2].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17" width="17%" align="center">申请人</td>
    <td colspan="33" width="33%"><c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[3].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr> 	
	
	
  <tr>
    <td width="100%" colspan="100" align="center" bgcolor="#F0F0F0">供应商所需资料及版本</td>
  </tr>
			
  <tr>
    <td colspan="12" width="12%" align="center">序号</td>
    <td colspan="44" width="44%" align="center">规格变更内容</td>
    <td colspan="44" width="44%" align="center">规格变更理由</td>
  </tr> 
  
  <tr>
     <td colspan="12" width="12%">&nbsp;
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
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[5].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[6].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr> 
  <tr>
     <td colspan="12" width="12%">&nbsp;
     <c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[7].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="<c:out value="${E9.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[8].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[9].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr> 
   <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[10].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E12.count}"/>].fieldText" value="<c:out value="${E12.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[11].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E13.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="<c:out value="${E13.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[12].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr> 
   <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E14.count}"/>].fieldText" value="<c:out value="${E14.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[13].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="44" width="44%"><c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E15.count}"/>].fieldText" value="<c:out value="${E15.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[14].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%"><c:choose>
			<c:when test="${E16.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E16.count}"/>].fieldText" value="<c:out value="${E16.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[15].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr> 
  <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E17.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E17.count}"/>].fieldText" value="<c:out value="${E17.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[16].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E18.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="<c:out value="${E18.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[17].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E19.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E19.count}"/>].fieldText" value="<c:out value="${E19.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[18].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr> 
  <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E20.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E20.count}"/>].fieldText" value="<c:out value="${E20.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[19].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E21.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E21.count}"/>].fieldText" value="<c:out value="${E21.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[20].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E22.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="<c:out value="${E22.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[21].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E23.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="<c:out value="${E23.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[22].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E24.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E24.count}"/>].fieldText" value="<c:out value="${E24.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[23].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E25.count}"/>].fieldText" value="<c:out value="${E25.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[24].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E26.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E26.count}"/>].fieldText" value="<c:out value="${E26.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[25].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E27.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E27.count}"/>].fieldText" value="<c:out value="${E27.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[26].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E28.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="<c:out value="${E28.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[27].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E29.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E29.count}"/>].fieldText" value="<c:out value="${E29.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[28].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E30.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E30.count}"/>].fieldText" value="<c:out value="${E30.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[29].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E31.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E31.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E31.count}"/>].fieldText" value="<c:out value="${E31.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[30].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E32.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E32.count}"/>].fieldText" value="<c:out value="${E32.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[31].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E33.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E33.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E33.count}"/>].fieldText" value="<c:out value="${E33.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[32].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E34.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E34.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E34.count}"/>].fieldText" value="<c:out value="${E34.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[33].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  <tr>
     <td colspan="12" width="12%">&nbsp;
     	<c:choose>
			<c:when test="${E35.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E35.count}"/>].fieldText" value="<c:out value="${E35.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[34].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E36.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E36.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E36.count}"/>].fieldText" value="<c:out value="${E36.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[35].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="44" width="44%">
    	<c:choose>
			<c:when test="${E37.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E37.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E37.count}"/>].fieldText" value="<c:out value="${E37.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[36].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>


  
  
  
 <tr>
    <td width="100%" colspan="100" height="30">
    <table width="100%">
    	<tr>
    		<td>
	    		部门负责人：
	    		<c:choose>
					<c:when test="${E38.isEdit == '1'}">
						<input type="Text" size="18" id="fieldContents[<c:out value="${E38.count}"/>].fieldText" 
							 name="fieldContents[<c:out value="${E38.count}"/>].fieldText" 
							<c:if test="${!empty E38.fieldText}">value="<c:out value="${E38.fieldText}"/>"</c:if>
							<c:if test="${empty E38.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
							 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
					</c:when>
					<c:otherwise>
						<c:out value="${fieldContents[37].fieldText}"/>
					</c:otherwise>
				</c:choose>
    		</td>
    		<td>
		    		日  期：
		  	<c:choose>
					<c:when test="${E39.isEdit == '1'}">
						<input type="Text" size="18" id="fieldContents[<c:out value="${E39.count}"/>].fieldText" 
							 name="fieldContents[<c:out value="${E39.count}"/>].fieldText" 
							<c:if test="${!empty E39.fieldText}">value="<c:out value="${E39.fieldText}"/>"</c:if>
							<c:if test="${empty E39.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
							style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
					</c:when>
					<c:otherwise>
						<c:out value="${fieldContents[38].fieldText}"/>
					</c:otherwise>
				</c:choose>
    		</td>
    	</tr>
    </table>
	</td>
  </tr>
  
  
  <tr>
    <td height="100" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">项目经理意见：</td>
	    </tr>
		<tr>
		  <td>
			<c:choose>
			    <c:when test="${E40.isEdit == '1'}">
			        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E40.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E40.count}"/>].fieldText"><c:out value="${E40.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[39].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="25" align="right"> 签  名：
			<c:choose>
			    <c:when test="${E41.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E41.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E41.count}"/>].fieldText" 
			            <c:if test="${!empty E41.fieldText}">value="<c:out value="${E41.fieldText}"/>"</c:if>
						<c:if test="${empty E41.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[40].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		    日期：
			<c:choose>
			    <c:when test="${E42.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E42.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E42.count}"/>].fieldText" 
						<c:if test="${!empty E42.fieldText}">value="<c:out value="${E42.fieldText}"/>"</c:if>
						<c:if test="${empty E42.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[41].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		     </td>
	    </tr>
     </table>
     </td>
  </tr>
  
    <tr>
    <td height="100" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">产品经理意见：</td>
	    </tr>
		<tr>
		  <td>
			<c:choose>
			    <c:when test="${E43.isEdit == '1'}">
			        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E43.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E43.count}"/>].fieldText"><c:out value="${E43.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[42].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="25" align="right"> 签  名：
			<c:choose>
			    <c:when test="${E44.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E44.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E44.count}"/>].fieldText" 
						<c:if test="${!empty E44.fieldText}">value="<c:out value="${E44.fieldText}"/>"</c:if>
						<c:if test="${empty E44.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[43].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		    日期：
			<c:choose>
			    <c:when test="${E45.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E45.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E45.count}"/>].fieldText" 
							<c:if test="${!empty E45.fieldText}">value="<c:out value="${E45.fieldText}"/>"</c:if>
							<c:if test="${empty E45.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[44].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		     </td>
	    </tr>
     </table>
     </td>
  </tr>
  
    <tr>
    <td height="100" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="25">研发总监意见：</td>
	    </tr>
		<tr>
		  <td>
			<c:choose>
			    <c:when test="${E46.isEdit == '1'}">
			        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E46.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E46.count}"/>].fieldText"><c:out value="${E46.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[45].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="25" align="right"> 签  名：
			<c:choose>
			    <c:when test="${E47.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E47.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E47.count}"/>].fieldText" 
						<c:if test="${!empty E47.fieldText}">value="<c:out value="${E47.fieldText}"/>"</c:if>
						<c:if test="${empty E47.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[46].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		    日期：
			<c:choose>
			    <c:when test="${E48.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E48.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E48.count}"/>].fieldText" 
							<c:if test="${!empty E48.fieldText}">value="<c:out value="${E48.fieldText}"/>"</c:if>
							<c:if test="${empty E48.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[47].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		     </td>
	    </tr>
     </table>
     </td>
  </tr>
  
  
  <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">产品规划委员会主任意见</td>
	    </tr>

		<c:choose>
			<c:when test="${E49.isEdit == '1'}">
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" name="fieldContents[<c:out value="${E49.count}"/>].fieldText" value="1"
						<c:if test="${E49.fieldText == 1 || empty E49.fieldText}">checked</c:if> />同意
					</div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" name="fieldContents[<c:out value="${E49.count}"/>].fieldText" value="2"
							<c:if test="${E49.fieldText == 2 || empty E49.fieldText}">checked</c:if> />不同意
					 </div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" name="fieldContents[<c:out value="${E49.count}"/>].fieldText" value="3"
							<c:if test="${E49.fieldText == 3 || empty E49.fieldText}">checked</c:if> />其他
						<input type="Text" size="20" id="fieldContents[<c:out value="${E50.count}"/>].fieldText"
							name="fieldContents[<c:out value="${E50.count}"/>].fieldText" value="<c:out value="${E50.fieldText}"/>"
							style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
					 </div></td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[48].fieldText == 1}">checked</c:if> />同意
					</div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[48].fieldText == 2}">checked</c:if> />不同意
					 </div></td>
				</tr>
				<tr>
					<td><div style="padding-left:50px">
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[48].fieldText == 3}">checked</c:if> />其他
						<input type="Text" size="20" value="<c:out value="${fieldContents[49].fieldText}"/>" disabled="disabled"
							style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
					 </div></td>
				</tr>
			</c:otherwise>
		</c:choose>
		<tr>
		  <td height="38" align="right"> 签  名：

<c:choose>
    <c:when test="${E51.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E51.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E51.count}"/>].fieldText" 
			<c:if test="${!empty E51.fieldText}">value="<c:out value="${E51.fieldText}"/>"</c:if>
			<c:if test="${empty E51.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[50].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		    日期：

<c:choose>
    <c:when test="${E52.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E52.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E52.count}"/>].fieldText" 
				<c:if test="${!empty E52.fieldText}">value="<c:out value="${E52.fieldText}"/>"</c:if>
				<c:if test="${empty E52.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
    </c:when>
    <c:otherwise>
        <input type="Text" size="20" value="<c:out value="${fieldContents[51].fieldText}"/>" disabled="disabled"
            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
    </c:otherwise>
</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
</table>
