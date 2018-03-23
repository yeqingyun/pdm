<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>物料风险评估报告</strong></p>

<table id="OwnershipStructure" width="700" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
  <tr>
    <td colspan="100" width="100%"  height="36" bgcolor="#FFFFB0" align="center">物料基本信息</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center"  height="28">物料编码</td>
    <td colspan="28" width="28%" >
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="18" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E1.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="16" width="16%"  align="center">物料型号</td>
    <td colspan="20" width="20%">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="18" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E2.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="10" width="10%" align="center">物料当前等级</td>
    <td colspan="10" width="10%" >
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="16" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E3.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center"  height="28">功能描述</td>
    <td colspan="28" width="28%">
		<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="Text" size="18" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E4.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="16" width="16%" align="center">产品经理</td>
    <td colspan="20" width="20%">
		<c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="Text" size="18" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E5.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="10" width="10%" align="center">项目名称</td>
    <td colspan="10" width="10%">
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="16" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E6.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  
  <tr>
    <td colspan="100" width="100%" height="36" bgcolor="#FFFFB0" align="center">风险评估与处理信息</td>
  </tr>
  <tr>
    <td colspan="100" width="100%" bgcolor="#CCCCCC">
		<table width="100%" border="0" cellpadding="1" cellspacing="1">
		<td align="left">商务评估</td>
		<td align="right">签名：
		<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E7.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
		</td>
		<td width="25%" align="right">日期：
		<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E8.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
		</td> 
		</table>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center" height="56">风险分析</td>
    <td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<textarea  rows=4  style="width:95%" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E9.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center" height="56">规避措施</td>
    <td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<textarea  rows=4  style="width:95%" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E10.count}"/>].fieldText"><c:out value="${E10.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E10.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="100" width="100%" bgcolor="#CCCCCC">
		<table width="100%" border="0">
		<td align="left">物料技术评估</td>
		<td align="right">签名：<c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E11.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose></td>
		<td width="25%" align="right">日期：
		<c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E12.count}"/>].fieldText" value="<c:out value="${E12.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E12.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
		</td> 
		</table>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center" height="56">风险分析</td>
    <td  colspan="84" width="84%" >
		<c:choose>
			<c:when test="${E13.isEdit == '1'}">
				<textarea  rows=4  style="width:95%" id="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E13.count}"/>].fieldText"><c:out value="${E13.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E13.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center" height="56">规避措施</td>
    <td colspan="84" width="84%" >
		<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<textarea  rows=4  style="width:95%" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E14.count}"/>].fieldText"><c:out value="${E14.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E14.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td  colspan="100" width="100%" bgcolor="#CCCCCC">
			<table width="100%" border="0">
		<td align="left">研发评估（硬件）</td>
		<td align="right">签名：
		<c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E15.count}"/>].fieldText" value="<c:out value="${E15.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E15.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
		</td>
		<td width="25%" align="right">日期：
		<c:choose>
			<c:when test="${E16.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E16.count}"/>].fieldText" value="<c:out value="${E16.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E16.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
		</td> 
		</table>
	
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center" height="56">风险分析</td>
    <td colspan="84" width="84%" >
		<c:choose>
			<c:when test="${E17.isEdit == '1'}">
				<textarea  rows=4  style="width:95%" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E17.count}"/>].fieldText"><c:out value="${E17.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E17.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" align="center" height="56">规避措施</td>
    <td colspan="84" width="84%" >
		<c:choose>
			<c:when test="${E18.isEdit == '1'}">
				<textarea  rows=4  style="width:95%" id="fieldContents[<c:out value="${E18.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E18.count}"/>].fieldText"><c:out value="${E18.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E18.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  

  <tr>
    <td colspan="16" width="16%" align="center">是否纳入项目风险管理</td>
    <td colspan="84" width="84%">&nbsp;&nbsp;
		<c:choose>
			<c:when test="${E19.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E19.count}"/>].fieldText" value="1" 
		  			<c:if test="${E19.fieldText == 1 || empty E19.fieldText}">checked</c:if> />是&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E19.count}"/>].fieldText" value="2" 
		  			<c:if test="${E19.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E19.fieldText == 1}">checked</c:if> />是&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E19.fieldText == 2}">checked</c:if> />否&nbsp;
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td  colspan="16" width="16%" align="center" height="30">风险编号</td>
    <td  colspan="84" width="84%">
		<c:choose>
			<c:when test="${E20.isEdit == '1'}">
				<input type="Text" size="30" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E20.count}"/>].fieldText" value="<c:out value="${E20.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E20.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="100" width="100%" height="36" bgcolor="#FFFFB0" align="center">审批信息</td>
  </tr>
  <tr>
    <td colspan="100" width="100%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>产品经理意见：</td>
		</tr>
		<tr>
			<td height="48" >
			<c:choose>
			<c:when test="${E21.isEdit == '1'}">
				&nbsp;<textarea  rows=4  style="width:95%" id="fieldContents[<c:out value="E20E1.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E21.count}"/>].fieldText"><c:out value="${E21.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E21.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
			<c:when test="${E22.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="<c:out value="${E22.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E22.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E23.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E23.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="<c:out value="${E23.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E23.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;	
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  
  <tr>
    <td colspan="100" width="100%">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>审核人意见：</td>
		</tr>
		<tr>
			<td height="48" >
			<c:choose>
			<c:when test="${E24.isEdit == '1'}">
				&nbsp;<textarea  rows=4 style="width:95%" id="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E24.count}"/>].fieldText"><c:out value="${E24.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;	<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E24.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E25.count}"/>].fieldText" value="<c:out value="${E25.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E25.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E26.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E26.count}"/>].fieldText" value="<c:out value="${E26.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E26.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;		  </td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  <tr>
    <td colspan="100" width="100%">
		    <table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>批准人意见：</td>
		</tr>
		<tr>
			<td height="48" >
			<c:choose>
			<c:when test="${E27.isEdit == '1'}">
				&nbsp;<textarea  rows=4 style="width:95%" id="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E27.count}"/>].fieldText"><c:out value="${E27.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				&nbsp;<textarea rows="4" style="width:95%" readonly="readonly" class="textarea-hidden"><c:out value="${E27.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
			<c:when test="${E28.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="<c:out value="${E28.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E28.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日&nbsp;期：
			<c:choose>
			<c:when test="${E29.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E29.count}"/>].fieldText" value="<c:out value="${E29.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E29.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;	
		</td>
		</tr>
		</table>
	</td>
  </tr>
</table>
 <table width="700" border="0" align="center">
	<tr>
	  <td align="left" width="80%">注：蓝色斜字体在实际使用本模板时须删除，根据风险类型，附相应的报告。</td>
	  <td align="right" width="20%">RDA-R-005&nbsp;&nbsp;V1.0</td>
	</tr>
</table>