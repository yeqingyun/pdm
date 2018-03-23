<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深&nbsp;圳&nbsp;市&nbsp;金&nbsp;立&nbsp;通&nbsp;信&nbsp;设&nbsp;备&nbsp;有&nbsp;限&nbsp;公&nbsp;司<br/>
供应商资格评审报告</strong></p>
<table border=1 bordercolor=#000000 align=center width=680 cellpadding=1 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
	<tr>
    <td width="14%" align="center">供应商名称</td>
    <td colspan="4" align="center">
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="40" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="40" value="<c:out value="${E1.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td  width="14%" align="center">供应商地址</td>
    <td  colspan="4" align="center">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="40" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="40" value="<c:out value="${E2.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td  width="14%" align="center">供应物料</td>
    <td width="35%" align="center">
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="25" value="<c:out value="${E3.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
    <td width="11%" align="center">物料类别</td>
    <td width="40%" colspan="2" align="center">
		<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="25" value="<c:out value="${E4.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td  width="14%" align="center">审核类型</td>
    <td  colspan="4" align="center">
		<c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="1" 
		  			<c:if test="${E5.fieldText == 1 || empty E5.fieldText}">checked</c:if> />首次认证&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="2" 
		  			<c:if test="${E5.fieldText == 2}">checked</c:if> />重新认证&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="3" 
		  			<c:if test="${E5.fieldText == 3}">checked</c:if> />整改后认证&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="4" 
		  			<c:if test="${E5.fieldText == 4}">checked</c:if> />其它&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E5.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E5.fieldText == 2}">checked</c:if> />重新认证&nbsp;
				<input type="radio" disabled="disabled" <c:if test="${E5.fieldText == 3}">checked</c:if> />整改后认证&nbsp;
				<input type="radio" disabled="disabled" <c:if test="${E5.fieldText == 4}">checked</c:if> />其它&nbsp;
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${E6.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td  width="14%" align="center">审核时间</td>
    <td  colspan="4" align="center">
		<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="Text" size="25" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="25" value="<c:out value="${E7.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
      日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 共
      <c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<input type="Text" size="10" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="10" value="<c:out value="${E8.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	  天</td>
  </tr>
  <tr>
    <td width="14%" align="center">审核人员 </td>
    <td align="left">&nbsp;组长:<c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="<c:out value="${E9.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${E9.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
    </td>
    <td colspan="3" align="left">&nbsp;审核员:<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<input type="Text" size="35" id="fieldContents[<c:out value="${E10.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="35" value="<c:out value="${E10.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
  </tr>

  
  
  
  <tr>
    <td width="14%"><p align="center">设计开发<br>评审结论</p></td>
    <td colspan="4" valign="top">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			<p>根据《供应商评审检查表》审查结果评分为:    
		<c:choose>
			<c:when test="${E11.isEdit == '1'}">
				<input type="Text" size="6" id="fieldContents[<c:out value="${E11.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="6" value="<c:out value="${E11.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
    分，基于以下原因:<br>&nbsp;
		<c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${E12.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E12.count}"/>].fieldText" value="<c:out value="${E12.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78"  value="<c:out value="${E12.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
		<c:choose>
			<c:when test="${XE12.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${XE12.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${XE12.count}"/>].fieldText" value="<c:out value="${XE12.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${XE12.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
	</tr>
	<tr>
		<td>&nbsp;
		<c:choose>
			<c:when test="${E13.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="1" 
		  			<c:if test="${E13.fieldText == 1 || empty E13.fieldText}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="2" 
		  			<c:if test="${E13.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="3" 
		  			<c:if test="${E13.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E13.fieldText == 1}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E13.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
			  	<input type="radio" disabled="disabled" <c:if test="${E13.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:otherwise>
		</c:choose>
        </td>
	</tr>
	<tr>
		<td align="right">
			签名:
			<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E14.count}"/>].fieldText" readonly="readonly"
					 name="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
					 <c:if test="${!empty E14.fieldText}">value="<c:out value="${E14.fieldText}"/>"</c:if>
					 <c:if test="${empty E14.fieldText}">value="<c:out value="${user.userName}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E14.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			&nbsp;日期:
			   <c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E15.count}"/>].fieldText" 
					 <c:if test="${!empty E15.fieldText}">value="<c:out value="${E15.fieldText}"/>"</c:if>
					 <c:if test="${empty E15.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if> 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E15.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	      &nbsp;		
	   </td>
	</tr>
	</table>

			   
    </td>
  </tr>
  
  
  
  
  
  
  <tr>
    <td width="14%"><p align="center">工艺工程<br>评审结论</p></td>
    <td colspan="4" valign="top">
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
		根据《供应商评审检查表》审查结果评分为:
      <c:choose>
			<c:when test="${E16.isEdit == '1'}">
				<input type="Text" size="6" id="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E16.count}"/>].fieldText" value="<c:out value="${E16.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="6" value="<c:out value="${E16.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
      分，基于以下原因: <br>&nbsp;
		<c:choose>
			<c:when test="${E17.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E17.count}"/>].fieldText" value="<c:out value="${E17.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${E17.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
		<c:choose>
			<c:when test="${XE17.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${XE17.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${XE17.count}"/>].fieldText" value="<c:out value="${XE17.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${XE17.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
	</tr>
	<tr>
		<td>&nbsp;
		<c:choose>
			<c:when test="${E18.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="1" 
		  			<c:if test="${E18.fieldText == 1 || empty E18.fieldText}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="2" 
		  			<c:if test="${E18.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="3" 
		  			<c:if test="${E18.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E18.fieldText == 1}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E18.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
			  	<input type="radio" disabled="disabled" <c:if test="${E18.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td align="right">
		签名:
	  <c:choose>
			<c:when test="${E19.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E19.count}"/>].fieldText" readonly="readonly" 
					 name="fieldContents[<c:out value="${E19.count}"/>].fieldText" 
					 <c:if test="${!empty E19.fieldText}">value="<c:out value="${E19.fieldText}"/>"</c:if>
					 <c:if test="${empty E19.fieldText}">value="<c:out value="${user.userName}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E19.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	  &nbsp;日期:
	  <c:choose>
			<c:when test="${E20.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E20.count}"/>].fieldText" 
					 <c:if test="${!empty E20.fieldText}">value="<c:out value="${E20.fieldText}"/>"</c:if>
					 <c:if test="${empty E20.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E20.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	  &nbsp;		
	  </td>
	</tr>
	</table>
    </td>
  </tr>
  
  
  
  <tr>
    <td width="14%"><p align="center">品质认证<br>评审结论</p></td>
    <td colspan="4" valign="top">
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			根据《供应商评审检查表》审查结果评分为:
			<c:choose>
			<c:when test="${E21.isEdit == '1'}">
				<input type="Text" size="6" id="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E21.count}"/>].fieldText" value="<c:out value="${E21.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="6" value="<c:out value="${E21.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
      分，基于以下原因:<br>&nbsp;
		<c:choose>
			<c:when test="${E22.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="<c:out value="${E22.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${E22.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
		<c:choose>
			<c:when test="${XE22.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${XE22.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${XE22.count}"/>].fieldText" value="<c:out value="${XE22.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${XE22.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose></td>
	</tr>
	<tr><td>&nbsp;
		<c:choose>
			<c:when test="${E23.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="1" 
		  			<c:if test="${E23.fieldText == 1 || empty E23.fieldText}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="2" 
		  			<c:if test="${E23.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="3" 
		  			<c:if test="${E23.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E23.fieldText == 1}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E23.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
			  	<input type="radio" disabled="disabled" <c:if test="${E23.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td align="right">
		签名:
		<c:choose>
			<c:when test="${E24.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E24.count}"/>].fieldText" readonly="readonly" 
					 name="fieldContents[<c:out value="${E24.count}"/>].fieldText" 
					 <c:if test="${!empty E24.fieldText}">value="<c:out value="${E24.fieldText}"/>"</c:if>
					 <c:if test="${empty E24.fieldText}">value="<c:out value="${user.userName}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E24.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
    日期:
		<c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E25.count}"/>].fieldText" 
					 <c:if test="${!empty E25.fieldText}">value="<c:out value="${E25.fieldText}"/>"</c:if>
					 <c:if test="${empty E25.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E25.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	&nbsp;	
	</td>
	</tr>
	</table>
	
	</td>
  </tr>
  
  
  
  <tr>
    <td width="14%"><p align="center">质量管理<br>体系结论</p></td>
    <td colspan="4" valign="top">
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">
			根据《供应商评审检查表》审查结果评分为:
		<c:choose>
			<c:when test="${E26.isEdit == '1'}">
				<input type="Text" size="6" id="fieldContents[<c:out value="${E26.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E26.count}"/>].fieldText" value="<c:out value="${E26.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="6" value="<c:out value="${E26.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
      分，基于以下原因:<br>&nbsp;
		<c:choose>
			<c:when test="${E27.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E27.count}"/>].fieldText" value="<c:out value="${E27.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${E27.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
		<c:choose>
			<c:when test="${XE27.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${XE27.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${XE27.count}"/>].fieldText" value="<c:out value="${XE27.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${XE27.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr><td>
	  &nbsp;
		<c:choose>
			<c:when test="${E28.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="1" 
		  			<c:if test="${E28.fieldText == 1 || empty E28.fieldText}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="2" 
		  			<c:if test="${E28.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
				<input type="radio" name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="3" 
		  			<c:if test="${E28.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E28.fieldText == 1}">checked</c:if> />合格，可选为供应商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E28.fieldText == 2}">checked</c:if> />基本合格，可作为暂用/候备供应商 <br>&nbsp;
			  	<input type="radio" disabled="disabled" <c:if test="${E28.fieldText == 3}">checked</c:if> />不合格，不选为供应商&nbsp;
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	<tr>
		<td align="right">
		签名:
		<c:choose>
			<c:when test="${E29.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E29.count}"/>].fieldText" readonly="readonly" 
					 name="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
					 <c:if test="${!empty E29.fieldText}">value="<c:out value="${E29.fieldText}"/>"</c:if>
					 <c:if test="${empty E29.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E29.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose> &nbsp;日期:
      <c:choose>
			<c:when test="${E30.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
					 <c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
					 <c:if test="${empty E30.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if> 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E30.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	  &nbsp;
		</td>
	</tr>
	</table>

    </td>
  </tr>
  
  
  
  <tr>
    <td width="14%"><p align="center">综合意见</p></td>
    <td colspan="4" valign="top">
	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td valign="top">综合评审组意见及判定等级:（评审最终得分:
		<c:choose>
			<c:when test="${E31.isEdit == '1'}">
				<input type="Text" size="6" id="fieldContents[<c:out value="${E31.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E31.count}"/>].fieldText" value="<c:out value="${E31.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="6" value="<c:out value="${E31.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
			
      分，判定等级:
	  <c:choose>
			<c:when test="${E32.isEdit == '1'}">
				<input type="Text" size="3" id="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E32.count}"/>].fieldText" value="<c:out value="${E32.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="3" value="<c:out value="${E32.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	  级）<br>&nbsp;
		<c:choose>
			<c:when test="${E33.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${E33.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E33.count}"/>].fieldText" value="<c:out value="${E33.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${E33.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
		<c:choose>
			<c:when test="${XE33.isEdit == '1'}">
				<input type="Text" size="78" maxlength="40" id="fieldContents[<c:out value="${XE33.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${XE33.count}"/>].fieldText" value="<c:out value="${XE33.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<input type="Text" size="78" value="<c:out value="${XE33.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
	</tr>
	<tr>
		<td align="right">
		审核组长:<c:choose>
			<c:when test="${E34.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E34.count}"/>].fieldText" readonly="readonly" 
					 name="fieldContents[<c:out value="${E34.count}"/>].fieldText" 
 					 <c:if test="${!empty E34.fieldText}">value="<c:out value="${E34.fieldText}"/>"</c:if>
					 <c:if test="${empty E34.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E34.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;日期:
		<c:choose>
			<c:when test="${E35.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
					 <c:if test="${!empty E35.fieldText}">value="<c:out value="${E35.fieldText}"/>"</c:if>
					 <c:if test="${empty E35.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E35.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
		</td>
	</tr>
	</table>

</td>
  </tr>
  
  
  <tr>
    <td width="14%"><p align="center">公司意见</p></td>
    <td colspan="4" valign="top">
	
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="left">
	  <c:choose>
			<c:when test="${E36.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E36.count}"/>].fieldText" value="1" 
		  			<c:if test="${E36.fieldText == 1 || empty E36.fieldText}">checked</c:if> />可作为合格供应商&nbsp;<br>
		  		<input type="radio" name="fieldContents[<c:out value="${E36.count}"/>].fieldText" value="2" 
		  			<c:if test="${E36.fieldText == 2}">checked</c:if> />可作为基本合格供应商（暂用/候备）<br>
				<input type="radio" name="fieldContents[<c:out value="${E36.count}"/>].fieldText" value="3" 
		  			<c:if test="${E36.fieldText == 3}">checked</c:if> />不选为供应商&nbsp;<br>
				<input type="radio" name="fieldContents[<c:out value="${E36.count}"/>].fieldText" value="3" 
		  			<c:if test="${E36.fieldText == 3}">checked</c:if> />其它&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E36.fieldText == 1}">checked</c:if> />可作为合格供应商&nbsp;<br>
		  		<input type="radio" disabled="disabled" <c:if test="${E36.fieldText == 2}">checked</c:if> />可作为基本合格供应商（暂用/候备）&nbsp;<br>
			  	<input type="radio" disabled="disabled" <c:if test="${E36.fieldText == 3}">checked</c:if> />不选为供应商&nbsp;<br>
				<input type="radio" disabled="disabled" <c:if test="${E36.fieldText == 4}">checked</c:if> />其它
			</c:otherwise>
		</c:choose>
		<c:choose>
		<c:when test="${E37.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E37.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E37.count}"/>].fieldText" value="<c:out value="${E37.fieldText}"/>" 
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${E37.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	  
	  </td>
    <td align="right" valign="bottom">签名:
	<c:choose>
			<c:when test="${E38.isEdit == '1'}">
				<input type="Text" size="10" id="fieldContents[<c:out value="${E38.count}"/>].fieldText" readonly="readonly" 
					 name="fieldContents[<c:out value="${E38.count}"/>].fieldText" 
					 <c:if test="${!empty E38.fieldText}">value="<c:out value="${E38.fieldText}"/>"</c:if>
					 <c:if test="${empty E38.fieldText}">value="<c:out value="${user.userName}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="10" value="<c:out value="${E38.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	 日期:
	<c:choose>
			<c:when test="${E39.isEdit == '1'}">
				<input type="Text" size="12" id="fieldContents[<c:out value="${E39.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E39.count}"/>].fieldText" 
					 <c:if test="${!empty E39.fieldText}">value="<c:out value="${E39.fieldText}"/>"</c:if>
					 <c:if test="${empty E39.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>  
					 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:when>
			<c:otherwise>
				<input type="Text" size="12" value="<c:out value="${E39.fieldText}"/>" readonly="readonly"
					style="background-color: #EFF0F2;border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>&nbsp;
	</td>
  </tr>
</table>

	</td>
  </tr>
</table>
<table width="680" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	  <td width="80%" align="left">
	  	<font style="font-size:12px">备注:1、<u>90≤分数≤100&nbsp;A级&nbsp;&nbsp;优秀</u>；&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	2、<u>80≤分数＜90&nbsp;&nbsp;B级&nbsp;&nbsp;合格</u>；<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	3、<u>70≤分数＜80&nbsp;C级&nbsp;&nbsp;基本合格</u>；&nbsp;
	  	4、<u>分数＜70&nbsp;&nbsp;C级&nbsp;&nbsp;不合格</u>；</font>
	  </td>
	  <td  width="20%" align="right">
	  		<font style="font-size:12px">GN-R-025 V1.4</font>
	  </td>
	</tr>
</table>
<br>