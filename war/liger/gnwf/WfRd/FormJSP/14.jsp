<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面14jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>产品立项申请书</strong></p>

<table id="OwnershipStructure" width="700" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>


  <tr>
    <td width="100%" colspan="100" align="center" bgcolor="#F0F0F0">申请信息</td>
  </tr>
	 
  <tr>
    <td colspan="17" width="150" align="center">产品型号</td>
    <td colspan="33"width="180"> <c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[0].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="150" align="center">产品系列</td>
    <td colspan="33" width="180" > <c:choose>
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
    <td colspan="17" width="17%"align="center">产品定位</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[2].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">上市时间要求</td>
    <td colspan="33" width="33%" > <c:choose>
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
    <td colspan="17" width="17%"align="center">申请部门</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[4].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">项目经理</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[5].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  
  
  
  <tr>
    <td width="100%" colspan="100" align="center" bgcolor="#F0F0F0">产品基本配置：（详见产品定义书）</td>
  </tr>
  
  <tr>
    <td colspan="17" width="17%"align="center">硬件平台</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[6].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">频段</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[7].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>

  <tr>
    <td colspan="17" width="17%"align="center">整机尺寸</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="<c:out value="${E9.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[8].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="17"width="17%" align="center">前摄像头</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[9].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="17" width="17%"align="center">TP</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[10].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">后摄像头</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E12.count}"/>].fieldText" value="<c:out value="${E12.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[11].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>

  <tr>
    <td colspan="17" width="17%"align="center">LCD</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E13.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="<c:out value="${E13.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[12].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="17"width="17%" align="center">电池</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E14.count}"/>].fieldText" value="<c:out value="${E14.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[13].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
    <tr>
    <td colspan="17" width="17%"align="center">内存</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E15.count}"/>].fieldText" value="<c:out value="${E15.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[14].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">SIM卡座</td>
    <td colspan="33" width="33%" > <c:choose>
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
    <td colspan="17" width="17%" align="center">包装</td>
    <td colspan="83">
		<c:choose>
			<c:when test="${E17.isEdit == '1'}">
				<input type="Text" size="80" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E17.count}"/>].fieldText" value="<c:out value="${E17.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[16].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
 
  
  
  
  <tr>
    <td width="100%" colspan="100" align="center" bgcolor="#F0F0F0">结构主要供应商</td>
  </tr>
  
  <tr>
    <td colspan="17" width="17%"align="center">壳体</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E18.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="<c:out value="${E18.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[17].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">五金件</td>
    <td colspan="33" width="33%" > <c:choose>
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
    <td colspan="17" width="17%"align="center">按键</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E20.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E20.count}"/>].fieldText" value="<c:out value="${E20.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[19].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="17"width="17%" align="center">其它</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E21.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E21.count}"/>].fieldText" value="<c:out value="${E21.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[20].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  <tr>
    <td width="100%" colspan="100" align="center" bgcolor="#F0F0F0">关键项目进度计划</td>
  </tr>
  <tr>
    <td colspan="17" width="17%"align="center">投模时间</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E22.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="<c:out value="${E22.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[21].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">一次小批</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E23.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="<c:out value="${E23.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[22].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>

  <tr>
    <td colspan="17" width="17%"align="center">送检时间</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E24.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E24.count}"/>].fieldText" value="<c:out value="${E24.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[23].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="17"width="17%" align="center">二次小批</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E25.count}"/>].fieldText" value="<c:out value="${E25.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[24].fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="17" width="17%"align="center">中批时间</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E26.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E26.count}"/>].fieldText" value="<c:out value="${E26.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[25].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
    <td colspan="17"width="17%" align="center">封样时间</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E27.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E27.count}"/>].fieldText" value="<c:out value="${E27.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[26].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
   <tr>
    <td colspan="17" width="17%" align="center">批量生产时间</td>
    <td colspan="83">
		<c:choose>
			<c:when test="${E28.isEdit == '1'}">
				<input type="Text" size="80" id="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="<c:out value="${E28.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${fieldContents[27].fieldText}"/>
			</c:otherwise>
		</c:choose></td>
  </tr>
  
  
  <tr>
    <td colspan="100" width="100%" height="30" align="left" bgcolor="#F0F0F0">所需附件1、产品定义书V1.0；  2、项目进度计划表；   3、风险评估列表；</td>
  </tr>

  
  <%-- 
  <tr>
    <td colspan="17" width="17%"align="center">产品经理审核：</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E29.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
					<c:if test="${!empty E29.fieldText}">value="<c:out value="${E29.fieldText}"/>"</c:if>
					<c:if test="${empty E29.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				 >
			</c:when>
			<c:otherwise>
				<c:out value="${E29.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="17"width="17%" align="center">日期：</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E30.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
					<c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
					<c:if test="${empty E30.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if> 
 				>
			</c:when>
			<c:otherwise>
				<c:out value="${E30.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="17" width="17%"align="center">研发总监确认：</td>
    <td colspan="33"width="33%"> <c:choose>
			<c:when test="${E31.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E31.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E31.count}"/>].fieldText" 
				<c:if test="${!empty E31.fieldText}">value="<c:out value="${E31.fieldText}"/>"</c:if>
				<c:if test="${empty E31.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
			 >
			</c:when>
			<c:otherwise>
				<c:out value="${E31.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="17"width="17%" align="center">日期：</td>
    <td colspan="33" width="33%" > <c:choose>
			<c:when test="${E32.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
					<c:if test="${!empty E32.fieldText}">value="<c:out value="${E32.fieldText}"/>"</c:if>
					<c:if test="${empty E32.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if> 
				 >
			</c:when>
			<c:otherwise>
				<c:out value="${E32.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr> --%>
  
  
      <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">产品经理审核：</td>
	    </tr>
		<tr>
		  <td height="56" >&nbsp;
			<c:choose>
			    <c:when test="${E41.isEdit == '1'}">
			        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E41.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E41.count}"/>].fieldText"><c:out value="${E41.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[40].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：
			<c:choose>
			    <c:when test="${E29.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E29.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
						<c:if test="${!empty E29.fieldText}">value="<c:out value="${E29.fieldText}"/>"</c:if>
						<c:if test="${empty E29.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[28].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		    日期：
			<c:choose>
			    <c:when test="${E30.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
						<c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
						<c:if test="${empty E30.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[29].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
      <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td height="28">研发总监确认：</td>
	    </tr>
		<tr>
		  <td height="56" >&nbsp;
			<c:choose>
			    <c:when test="${E42.isEdit == '1'}">
			        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E42.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E42.count}"/>].fieldText"><c:out value="${E42.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[41].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：
			<c:choose>
			    <c:when test="${E31.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E31.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E31.count}"/>].fieldText" 
						<c:if test="${!empty E31.fieldText}">value="<c:out value="${E31.fieldText}"/>"</c:if>
						<c:if test="${empty E31.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[30].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		    日期：
			<c:choose>
			    <c:when test="${E32.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E32.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
						<c:if test="${!empty E32.fieldText}">value="<c:out value="${E32.fieldText}"/>"</c:if>
						<c:if test="${empty E32.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[31].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  
  <c:if test="${prjtDef.devDeptNameID==3}">
      <tr>
    <td height="127" colspan="100" valign="top">
    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    
    
		<tr>
		  <td height="28">研发副总意见</td>
	    </tr>
		
		<tr>
		  <td height="56" >&nbsp;
			<c:choose>
			    <c:when test="${E33.isEdit == '1'}">
			        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E33.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E33.count}"/>].fieldText"><c:out value="${E33.fieldText}"/></textarea>
			    </c:when>
			    <c:otherwise>
			        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[32].fieldText}"/></textarea>
			    </c:otherwise>
			</c:choose>
		  </td>
	    </tr>
		<tr>
		  <td height="38" align="right"> 签  名：
			<c:choose>
			    <c:when test="${E34.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E34.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E34.count}"/>].fieldText" 
						<c:if test="${!empty E34.fieldText}">value="<c:out value="${E34.fieldText}"/>"</c:if>
						<c:if test="${empty E34.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[33].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		    日期：
			<c:choose>
			    <c:when test="${E35.isEdit == '1'}">
			        <input type="Text" size="20" id="fieldContents[<c:out value="${E35.count}"/>].fieldText"
			            name="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
						<c:if test="${!empty E35.fieldText}">value="<c:out value="${E35.fieldText}"/>"</c:if>
						<c:if test="${empty E35.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
			    </c:when>
			    <c:otherwise>
			        <input type="Text" size="20" value="<c:out value="${fieldContents[34].fieldText}"/>" disabled="disabled"
			            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			    </c:otherwise>
			</c:choose>
		     </td>
	    </tr>
     </table></td>
  </tr>
  </c:if>
  <!-- 
  	E36
   -->
  
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
			<c:when test="${E37.isEdit == '1'}">
					<tr>
					<td width="25%" bordercolor="#FFFFFF" >
						<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" name="fieldContents[<c:out value="${E37.count}"/>].fieldText" value="1" 
								<c:if test="${E37.fieldText == 1 || empty E37.fieldText}">checked</c:if> />同意&nbsp;
						</td>
				   </tr>
				   
				  <tr>
					<td  bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" name="fieldContents[<c:out value="${E37.count}"/>].fieldText" value="2" 
								<c:if test="${E37.fieldText == 2}">checked</c:if> />不同意&nbsp;
						
					</td>
				 </tr>
				 <tr>
					<td   bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
					
					  <input type="radio" name="fieldContents[<c:out value="${E37.count}"/>].fieldText" value="3" 
								<c:if test="${E37.fieldText == 3}">checked</c:if> />其它&nbsp;
					  <input type="Text" size="50" id="fieldContents[<c:out value="${E37.count}"/>].fieldText" 
								 name="fieldContents[<c:out value="${E38.count}"/>].fieldText" value="<c:out value="${E38.fieldText}"/>" 
								 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
						
					</td>
				  </tr>
	</c:when>
	<c:otherwise>
		         <tr>
					<td  width="25%" bordercolor="#FFFFFF" >
						<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[36].fieldText == 1}">checked</c:if> />同意&nbsp;
						</td>
				   </tr>
				   
				  <tr>
					<td  bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
						<input type="radio" disabled="disabled" <c:if test="${fieldContents[36].fieldText == 2}">checked</c:if> />不同意&nbsp;
						
					</td>
				 </tr>
				 <tr>
					<td   bordercolor="#FFFFFF" >
					<td align="left" bordercolor="#FFFFFF" >
					  <input type="radio" disabled="disabled" <c:if test="${fieldContents[36].fieldText == 3}">checked</c:if> />其它&nbsp;
					  <input type="Text" size="50" value="<c:out value="${fieldContents[37].fieldText}"/>" disabled="disabled"
					   style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
						
					</td>
				  </tr>
	  </c:otherwise>
    </c:choose>
	</table>
	</td>
  </tr>
	<tr>
		<td align="right" style="padding-top: 10px;">
			签&nbsp;名：
		  <c:choose>
				<c:when test="${E39.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E39.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E39.count}"/>].fieldText" 
						 <c:if test="${!empty E39.fieldText}">value="<c:out value="${E39.fieldText}"/>"</c:if>
						 <c:if test="${empty E39.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${fieldContents[38].fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
		
		&nbsp;日&nbsp;期：<c:choose>
			<c:when test="${E40.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E40.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E40.count}"/>].fieldText" 
					 <c:if test="${!empty E40.fieldText}">value="<c:out value="${E40.fieldText}"/>"</c:if>
					 <c:if test="${empty E40.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${fieldContents[39].fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
			</td>
		</tr>
	  </table>
	
	</td>
  </tr>
</table>
