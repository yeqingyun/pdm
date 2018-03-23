<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<style>
<!--
.wics {
	border: 1px;
	border-bottom-style: solid;
	border-top-style: none;
	border-left-style: none;
	border-right-style: none;
	size: 20;
}

.twics {
	border: 1px;
	border-bottom-style: solid;
	border-top-style: none;
	border-left-style: none;
	border-right-style: none;
	size: 10;
	width: 100%;
}
-->
</style>

<span style="visibility:hidden">页面52jsp</span>
<p align="center" style='font-size: 14.0pt; font-family: 宋体;'>
	<strong>深圳市金立通信设备有限公司</strong><br /> <strong>研发备料计划表</strong>
</p>

<table width="720" border=0 align=center cellpadding=0 cellspacing=0>
	<tr>
		<td colspan="33" align="left">产品型号：<c:choose>
				<c:when test="${E1.isEdit == '1'}">
					<input type="Text" class="wics"
						id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E1.count}"/>].fieldText"
						<c:if test="${!empty E1.fieldText}">value='<c:out value="${E1.fieldText}"/>'</c:if>
						<c:if test="${empty E1.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="wics"
						value="<c:out value="${fieldContents[0].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="34" align="left">申请部门：<c:choose>
				<c:when test="${E2.isEdit == '1'}">
					<input type="Text" class="wics"
						id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E2.count}"/>].fieldText"
						<c:if test="${!empty E2.fieldText}">value="<c:out value="${E2.fieldText}"/>"</c:if>
						<c:if test="${empty E2.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="wics"
						value="<c:out value="${fieldContents[1].fieldText}"/>" disabled="disabled" />
				</c:otherwise>
			</c:choose></td>
		<td colspan="33" align="left">申请日期：<c:choose>
				<c:when test="${E3.isEdit == '1'}">
					<input type="Text" class="wics"
						id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E3.count}"/>].fieldText"
						<c:if test="${!empty E3.fieldText}">value='<c:out value="${E3.fieldText}"/>'</c:if>
						<c:if test="${empty E3.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="wics"
						value="<c:out value="${fieldContents[2].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
</table>
<table id="OwnershipStructure" width="720" border=1 bordercolor=#000000
	align=center cellpadding=0 cellspacing=0
	style='border-collapse: collapse; font-size: 11.0pt'>
	<tr>
		<td colspan="10">序号</td>
		<td colspan="20">物料名称</td>
		<td colspan="10">型号/规格</td>
		<td colspan="10">单机用量</td>
		<td colspan="10">数量</td>
		<td colspan="10">需求时间</td>
		<td colspan="15">用途</td>
		<td colspan="15">备注</td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E4.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E4.count}"/>].fieldText"
						<c:if test="${!empty E4.fieldText}">value='<c:out value="${E4.fieldText}"/>'</c:if>
						<c:if test="${empty E4.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[3].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E5.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E5.count}"/>].fieldText"
						<c:if test="${!empty E5.fieldText}">value='<c:out value="${E5.fieldText}"/>'</c:if>
						<c:if test="${empty E5.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[4].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E6.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E6.count}"/>].fieldText"
						<c:if test="${!empty E6.fieldText}">value='<c:out value="${E6.fieldText}"/>'</c:if>
						<c:if test="${empty E6.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[5].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E7.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E7.count}"/>].fieldText"
						<c:if test="${!empty E7.fieldText}">value='<c:out value="${E7.fieldText}"/>'</c:if>
						<c:if test="${empty E7.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[6].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E8.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E8.count}"/>].fieldText"
						<c:if test="${!empty E8.fieldText}">value='<c:out value="${E8.fieldText}"/>'</c:if>
						<c:if test="${empty E8.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[7].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E9.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E9.count}"/>].fieldText"
						<c:if test="${!empty E9.fieldText}">value='<c:out value="${E9.fieldText}"/>'</c:if>
						<c:if test="${empty E9.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[8].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E10.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E10.count}"/>].fieldText"
						<c:if test="${!empty E10.fieldText}">value='<c:out value="${E10.fieldText}"/>'</c:if>
						<c:if test="${empty E10.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[9].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E11.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E11.count}"/>].fieldText"
						<c:if test="${!empty E11.fieldText}">value='<c:out value="${E11.fieldText}"/>'</c:if>
						<c:if test="${empty E11.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[10].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E12.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E12.count}"/>].fieldText"
						<c:if test="${!empty E12.fieldText}">value='<c:out value="${E12.fieldText}"/>'</c:if>
						<c:if test="${empty E12.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[11].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E13.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E13.count}"/>].fieldText"
						<c:if test="${!empty E13.fieldText}">value='<c:out value="${E13.fieldText}"/>'</c:if>
						<c:if test="${empty E13.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[12].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E14.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E14.count}"/>].fieldText"
						<c:if test="${!empty E14.fieldText}">value='<c:out value="${E14.fieldText}"/>'</c:if>
						<c:if test="${empty E14.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[13].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E15.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E15.count}"/>].fieldText"
						<c:if test="${!empty E15.fieldText}">value='<c:out value="${E15.fieldText}"/>'</c:if>
						<c:if test="${empty E15.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[14].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E16.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E16.count}"/>].fieldText"
						<c:if test="${!empty E16.fieldText}">value='<c:out value="${E16.fieldText}"/>'</c:if>
						<c:if test="${empty E16.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[15].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E17.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E17.count}"/>].fieldText"
						<c:if test="${!empty E17.fieldText}">value='<c:out value="${E17.fieldText}"/>'</c:if>
						<c:if test="${empty E17.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[16].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E18.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E18.count}"/>].fieldText"
						<c:if test="${!empty E18.fieldText}">value='<c:out value="${E18.fieldText}"/>'</c:if>
						<c:if test="${empty E18.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[17].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E19.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E19.count}"/>].fieldText"
						<c:if test="${!empty E19.fieldText}">value='<c:out value="${E19.fieldText}"/>'</c:if>
						<c:if test="${empty E19.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[18].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E20.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E20.count}"/>].fieldText"
						<c:if test="${!empty E20.fieldText}">value='<c:out value="${E20.fieldText}"/>'</c:if>
						<c:if test="${empty E20.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[19].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E21.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E21.count}"/>].fieldText"
						<c:if test="${!empty E21.fieldText}">value='<c:out value="${E21.fieldText}"/>'</c:if>
						<c:if test="${empty E21.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[20].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E22.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E22.count}"/>].fieldText"
						<c:if test="${!empty E22.fieldText}">value='<c:out value="${E22.fieldText}"/>'</c:if>
						<c:if test="${empty E22.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[21].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E23.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E23.count}"/>].fieldText"
						<c:if test="${!empty E23.fieldText}">value='<c:out value="${E23.fieldText}"/>'</c:if>
						<c:if test="${empty E23.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[22].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E24.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E24.count}"/>].fieldText"
						<c:if test="${!empty E24.fieldText}">value='<c:out value="${E24.fieldText}"/>'</c:if>
						<c:if test="${empty E24.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[23].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E25.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E25.count}"/>].fieldText"
						<c:if test="${!empty E25.fieldText}">value='<c:out value="${E25.fieldText}"/>'</c:if>
						<c:if test="${empty E25.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[24].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E26.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E26.count}"/>].fieldText"
						<c:if test="${!empty E26.fieldText}">value='<c:out value="${E26.fieldText}"/>'</c:if>
						<c:if test="${empty E26.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[25].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E27.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E27.count}"/>].fieldText"
						<c:if test="${!empty E27.fieldText}">value='<c:out value="${E27.fieldText}"/>'</c:if>
						<c:if test="${empty E27.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[26].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E28.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E28.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E28.count}"/>].fieldText"
						<c:if test="${!empty E28.fieldText}">value='<c:out value="${E28.fieldText}"/>'</c:if>
						<c:if test="${empty E28.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[27].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E29.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E29.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E29.count}"/>].fieldText"
						<c:if test="${!empty E29.fieldText}">value='<c:out value="${E29.fieldText}"/>'</c:if>
						<c:if test="${empty E29.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[28].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E30.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E30.count}"/>].fieldText"
						<c:if test="${!empty E30.fieldText}">value='<c:out value="${E30.fieldText}"/>'</c:if>
						<c:if test="${empty E30.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[29].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E31.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E31.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E31.count}"/>].fieldText"
						<c:if test="${!empty E31.fieldText}">value='<c:out value="${E31.fieldText}"/>'</c:if>
						<c:if test="${empty E31.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[30].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E32.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E32.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E32.count}"/>].fieldText"
						<c:if test="${!empty E32.fieldText}">value='<c:out value="${E32.fieldText}"/>'</c:if>
						<c:if test="${empty E32.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[31].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E33.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E33.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E33.count}"/>].fieldText"
						<c:if test="${!empty E33.fieldText}">value='<c:out value="${E33.fieldText}"/>'</c:if>
						<c:if test="${empty E33.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[32].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E34.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E34.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E34.count}"/>].fieldText"
						<c:if test="${!empty E34.fieldText}">value='<c:out value="${E34.fieldText}"/>'</c:if>
						<c:if test="${empty E34.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[33].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E35.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E35.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E35.count}"/>].fieldText"
						<c:if test="${!empty E35.fieldText}">value='<c:out value="${E35.fieldText}"/>'</c:if>
						<c:if test="${empty E35.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[34].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E36.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E36.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E36.count}"/>].fieldText"
						<c:if test="${!empty E36.fieldText}">value='<c:out value="${E36.fieldText}"/>'</c:if>
						<c:if test="${empty E36.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[34].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E37.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E37.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E37.count}"/>].fieldText"
						<c:if test="${!empty E37.fieldText}">value='<c:out value="${E37.fieldText}"/>'</c:if>
						<c:if test="${empty E37.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[36].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E38.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E38.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E38.count}"/>].fieldText"
						<c:if test="${!empty E38.fieldText}">value='<c:out value="${E38.fieldText}"/>'</c:if>
						<c:if test="${empty E38.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[37].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E39.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E39.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E39.count}"/>].fieldText"
						<c:if test="${!empty E39.fieldText}">value='<c:out value="${E39.fieldText}"/>'</c:if>
						<c:if test="${empty E39.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[38].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E40.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E40.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E40.count}"/>].fieldText"
						<c:if test="${!empty E40.fieldText}">value='<c:out value="${E40.fieldText}"/>'</c:if>
						<c:if test="${empty E40.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[39].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E41.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E41.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E41.count}"/>].fieldText"
						<c:if test="${!empty E41.fieldText}">value='<c:out value="${E41.fieldText}"/>'</c:if>
						<c:if test="${empty E41.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[40].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E42.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E42.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E42.count}"/>].fieldText"
						<c:if test="${!empty E42.fieldText}">value='<c:out value="${E42.fieldText}"/>'</c:if>
						<c:if test="${empty E42.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[41].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E43.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E43.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E43.count}"/>].fieldText"
						<c:if test="${!empty E43.fieldText}">value='<c:out value="${E43.fieldText}"/>'</c:if>
						<c:if test="${empty E43.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[42].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E44.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E44.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E44.count}"/>].fieldText"
						<c:if test="${!empty E44.fieldText}">value='<c:out value="${E44.fieldText}"/>'</c:if>
						<c:if test="${empty E44.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[43].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E45.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E45.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E45.count}"/>].fieldText"
						<c:if test="${!empty E45.fieldText}">value='<c:out value="${E45.fieldText}"/>'</c:if>
						<c:if test="${empty E45.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[44].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E46.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E46.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E46.count}"/>].fieldText"
						<c:if test="${!empty E46.fieldText}">value='<c:out value="${E46.fieldText}"/>'</c:if>
						<c:if test="${empty E46.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[45].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E47.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E47.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E47.count}"/>].fieldText"
						<c:if test="${!empty E47.fieldText}">value='<c:out value="${E47.fieldText}"/>'</c:if>
						<c:if test="${empty E47.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[46].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E48.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E48.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E48.count}"/>].fieldText"
						<c:if test="${!empty E48.fieldText}">value='<c:out value="${E48.fieldText}"/>'</c:if>
						<c:if test="${empty E48.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[47].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E49.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E49.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E49.count}"/>].fieldText"
						<c:if test="${!empty E49.fieldText}">value='<c:out value="${E49.fieldText}"/>'</c:if>
						<c:if test="${empty E49.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[48].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E50.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E50.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E50.count}"/>].fieldText"
						<c:if test="${!empty E50.fieldText}">value='<c:out value="${E50.fieldText}"/>'</c:if>
						<c:if test="${empty E50.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[49].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E51.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E51.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E51.count}"/>].fieldText"
						<c:if test="${!empty E51.fieldText}">value='<c:out value="${E51.fieldText}"/>'</c:if>
						<c:if test="${empty E51.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[50].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E52.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E52.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E52.count}"/>].fieldText"
						<c:if test="${!empty E52.fieldText}">value='<c:out value="${E52.fieldText}"/>'</c:if>
						<c:if test="${empty E52.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[51].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E53.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E53.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E53.count}"/>].fieldText"
						<c:if test="${!empty E53.fieldText}">value='<c:out value="${E53.fieldText}"/>'</c:if>
						<c:if test="${empty E53.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[52].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E54.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E54.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E54.count}"/>].fieldText"
						<c:if test="${!empty E54.fieldText}">value='<c:out value="${E54.fieldText}"/>'</c:if>
						<c:if test="${empty E54.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[53].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E55.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E55.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E55.count}"/>].fieldText"
						<c:if test="${!empty E55.fieldText}">value='<c:out value="${E55.fieldText}"/>'</c:if>
						<c:if test="${empty E55.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[54].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E56.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E56.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E56.count}"/>].fieldText"
						<c:if test="${!empty E56.fieldText}">value='<c:out value="${E56.fieldText}"/>'</c:if>
						<c:if test="${empty E56.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[55].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E57.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E57.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E57.count}"/>].fieldText"
						<c:if test="${!empty E57.fieldText}">value='<c:out value="${E57.fieldText}"/>'</c:if>
						<c:if test="${empty E57.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[56].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E58.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E58.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E58.count}"/>].fieldText"
						<c:if test="${!empty E58.fieldText}">value='<c:out value="${E58.fieldText}"/>'</c:if>
						<c:if test="${empty E58.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[57].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E59.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E59.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E59.count}"/>].fieldText"
						<c:if test="${!empty E59.fieldText}">value='<c:out value="${E59.fieldText}"/>'</c:if>
						<c:if test="${empty E59.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[58].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E60.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E60.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E60.count}"/>].fieldText"
						<c:if test="${!empty E60.fieldText}">value='<c:out value="${E60.fieldText}"/>'</c:if>
						<c:if test="${empty E60.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[59].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E61.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E61.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E61.count}"/>].fieldText"
						<c:if test="${!empty E61.fieldText}">value='<c:out value="${E61.fieldText}"/>'</c:if>
						<c:if test="${empty E61.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[60].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E62.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E62.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E62.count}"/>].fieldText"
						<c:if test="${!empty E62.fieldText}">value='<c:out value="${E62.fieldText}"/>'</c:if>
						<c:if test="${empty E62.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[61].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E63.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E63.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E63.count}"/>].fieldText"
						<c:if test="${!empty E63.fieldText}">value='<c:out value="${E63.fieldText}"/>'</c:if>
						<c:if test="${empty E63.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[62].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E64.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E64.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E64.count}"/>].fieldText"
						<c:if test="${!empty E64.fieldText}">value='<c:out value="${E64.fieldText}"/>'</c:if>
						<c:if test="${empty E64.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[63].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E65.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E65.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E65.count}"/>].fieldText"
						<c:if test="${!empty E65.fieldText}">value='<c:out value="${E65.fieldText}"/>'</c:if>
						<c:if test="${empty E65.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[64].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E66.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E66.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E66.count}"/>].fieldText"
						<c:if test="${!empty E66.fieldText}">value='<c:out value="${E66.fieldText}"/>'</c:if>
						<c:if test="${empty E66.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[65].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E67.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E67.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E67.count}"/>].fieldText"
						<c:if test="${!empty E67.fieldText}">value='<c:out value="${E67.fieldText}"/>'</c:if>
						<c:if test="${empty E67.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[66].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E68.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E68.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E68.count}"/>].fieldText"
						<c:if test="${!empty E68.fieldText}">value='<c:out value="${E68.fieldText}"/>'</c:if>
						<c:if test="${empty E68.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[67].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E69.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E69.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E69.count}"/>].fieldText"
						<c:if test="${!empty E69.fieldText}">value='<c:out value="${E69.fieldText}"/>'</c:if>
						<c:if test="${empty E69.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[68].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E70.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E70.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E70.count}"/>].fieldText"
						<c:if test="${!empty E70.fieldText}">value='<c:out value="${E70.fieldText}"/>'</c:if>
						<c:if test="${empty E70.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[69].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E71.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E71.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E71.count}"/>].fieldText"
						<c:if test="${!empty E71.fieldText}">value='<c:out value="${E71.fieldText}"/>'</c:if>
						<c:if test="${empty E71.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[70].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E72.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E72.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E72.count}"/>].fieldText"
						<c:if test="${!empty E72.fieldText}">value='<c:out value="${E72.fieldText}"/>'</c:if>
						<c:if test="${empty E72.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[71].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E73.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E73.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E73.count}"/>].fieldText"
						<c:if test="${!empty E73.fieldText}">value='<c:out value="${E73.fieldText}"/>'</c:if>
						<c:if test="${empty E73.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[72].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E74.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E74.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E74.count}"/>].fieldText"
						<c:if test="${!empty E74.fieldText}">value='<c:out value="${E74.fieldText}"/>'</c:if>
						<c:if test="${empty E74.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[73].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E75.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E75.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E75.count}"/>].fieldText"
						<c:if test="${!empty E75.fieldText}">value='<c:out value="${E75.fieldText}"/>'</c:if>
						<c:if test="${empty E75.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[74].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E76.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E76.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E76.count}"/>].fieldText"
						<c:if test="${!empty E76.fieldText}">value='<c:out value="${E76.fieldText}"/>'</c:if>
						<c:if test="${empty E76.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[75].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E77.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E77.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E77.count}"/>].fieldText"
						<c:if test="${!empty E77.fieldText}">value='<c:out value="${E77.fieldText}"/>'</c:if>
						<c:if test="${empty E77.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[76].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E78.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E78.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E78.count}"/>].fieldText"
						<c:if test="${!empty E78.fieldText}">value='<c:out value="${E78.fieldText}"/>'</c:if>
						<c:if test="${empty E78.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[77].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E79.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E79.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E79.count}"/>].fieldText"
						<c:if test="${!empty E79.fieldText}">value='<c:out value="${E79.fieldText}"/>'</c:if>
						<c:if test="${empty E79.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[78].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E80.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E80.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E80.count}"/>].fieldText"
						<c:if test="${!empty E80.fieldText}">value='<c:out value="${E80.fieldText}"/>'</c:if>
						<c:if test="${empty E80.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[79].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E81.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E81.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E81.count}"/>].fieldText"
						<c:if test="${!empty E81.fieldText}">value='<c:out value="${E81.fieldText}"/>'</c:if>
						<c:if test="${empty E81.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[80].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E82.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E82.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E82.count}"/>].fieldText"
						<c:if test="${!empty E82.fieldText}">value='<c:out value="${E82.fieldText}"/>'</c:if>
						<c:if test="${empty E82.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[81].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E83.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E83.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E83.count}"/>].fieldText"
						<c:if test="${!empty E83.fieldText}">value='<c:out value="${E83.fieldText}"/>'</c:if>
						<c:if test="${empty E83.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[82].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E84.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E84.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E84.count}"/>].fieldText"
						<c:if test="${!empty E84.fieldText}">value='<c:out value="${E84.fieldText}"/>'</c:if>
						<c:if test="${empty E84.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[83].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E85.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E85.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E85.count}"/>].fieldText"
						<c:if test="${!empty E85.fieldText}">value='<c:out value="${E85.fieldText}"/>'</c:if>
						<c:if test="${empty E85.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[84].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E86.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E86.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E86.count}"/>].fieldText"
						<c:if test="${!empty E86.fieldText}">value='<c:out value="${E86.fieldText}"/>'</c:if>
						<c:if test="${empty E86.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[85].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E87.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E87.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E87.count}"/>].fieldText"
						<c:if test="${!empty E87.fieldText}">value='<c:out value="${E87.fieldText}"/>'</c:if>
						<c:if test="${empty E87.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[86].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E88.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E88.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E88.count}"/>].fieldText"
						<c:if test="${!empty E88.fieldText}">value='<c:out value="${E88.fieldText}"/>'</c:if>
						<c:if test="${empty E88.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[87].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E89.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E89.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E89.count}"/>].fieldText"
						<c:if test="${!empty E89.fieldText}">value='<c:out value="${E89.fieldText}"/>'</c:if>
						<c:if test="${empty E89.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[88].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E90.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E90.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E90.count}"/>].fieldText"
						<c:if test="${!empty E90.fieldText}">value='<c:out value="${E90.fieldText}"/>'</c:if>
						<c:if test="${empty E90.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[89].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E91.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E91.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E91.count}"/>].fieldText"
						<c:if test="${!empty E91.fieldText}">value='<c:out value="${E91.fieldText}"/>'</c:if>
						<c:if test="${empty E91.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[90].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E92.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E92.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E92.count}"/>].fieldText"
						<c:if test="${!empty E92.fieldText}">value='<c:out value="${E92.fieldText}"/>'</c:if>
						<c:if test="${empty E92.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[91].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E93.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E93.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E93.count}"/>].fieldText"
						<c:if test="${!empty E93.fieldText}">value='<c:out value="${E93.fieldText}"/>'</c:if>
						<c:if test="${empty E93.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[92].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E94.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E94.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E94.count}"/>].fieldText"
						<c:if test="${!empty E94.fieldText}">value='<c:out value="${E94.fieldText}"/>'</c:if>
						<c:if test="${empty E94.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[93].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E95.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E95.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E95.count}"/>].fieldText"
						<c:if test="${!empty E95.fieldText}">value='<c:out value="${E95.fieldText}"/>'</c:if>
						<c:if test="${empty E95.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[94].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E96.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E96.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E96.count}"/>].fieldText"
						<c:if test="${!empty E96.fieldText}">value='<c:out value="${E96.fieldText}"/>'</c:if>
						<c:if test="${empty E96.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[95].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E97.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E97.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E97.count}"/>].fieldText"
						<c:if test="${!empty E97.fieldText}">value='<c:out value="${E97.fieldText}"/>'</c:if>
						<c:if test="${empty E97.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[96].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E98.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E98.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E98.count}"/>].fieldText"
						<c:if test="${!empty E98.fieldText}">value='<c:out value="${E98.fieldText}"/>'</c:if>
						<c:if test="${empty E98.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[07].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E99.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E99.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E99.count}"/>].fieldText"
						<c:if test="${!empty E99.fieldText}">value='<c:out value="${E99.fieldText}"/>'</c:if>
						<c:if test="${empty E99.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[98].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E100.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E100.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E100.count}"/>].fieldText"
						<c:if test="${!empty E100.fieldText}">value='<c:out value="${E100.fieldText}"/>'</c:if>
						<c:if test="${empty E100.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[99].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E101.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E101.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E101.count}"/>].fieldText"
						<c:if test="${!empty E101.fieldText}">value='<c:out value="${E101.fieldText}"/>'</c:if>
						<c:if test="${empty E101.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[100].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E102.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E102.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E102.count}"/>].fieldText"
						<c:if test="${!empty E102.fieldText}">value='<c:out value="${E102.fieldText}"/>'</c:if>
						<c:if test="${empty E102.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[101].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E103.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E103.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E103.count}"/>].fieldText"
						<c:if test="${!empty E103.fieldText}">value='<c:out value="${E103.fieldText}"/>'</c:if>
						<c:if test="${empty E103.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[102].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E104.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E104.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E104.count}"/>].fieldText"
						<c:if test="${!empty E104.fieldText}">value='<c:out value="${E104.fieldText}"/>'</c:if>
						<c:if test="${empty E104.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[103].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E105.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E105.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E105.count}"/>].fieldText"
						<c:if test="${!empty E105.fieldText}">value='<c:out value="${E105.fieldText}"/>'</c:if>
						<c:if test="${empty E105.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[104].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E106.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E106.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E106.count}"/>].fieldText"
						<c:if test="${!empty E106.fieldText}">value='<c:out value="${E106.fieldText}"/>'</c:if>
						<c:if test="${empty E106.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[105].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E107.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E107.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E107.count}"/>].fieldText"
						<c:if test="${!empty E107.fieldText}">value='<c:out value="${E107.fieldText}"/>'</c:if>
						<c:if test="${empty E107.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[106].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E108.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E108.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E108.count}"/>].fieldText"
						<c:if test="${!empty E108.fieldText}">value='<c:out value="${E108.fieldText}"/>'</c:if>
						<c:if test="${empty E108.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[107].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E109.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E109.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E109.count}"/>].fieldText"
						<c:if test="${!empty E109.fieldText}">value='<c:out value="${E109.fieldText}"/>'</c:if>
						<c:if test="${empty E109.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[108].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E110.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E110.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E110.count}"/>].fieldText"
						<c:if test="${!empty E110.fieldText}">value='<c:out value="${E110.fieldText}"/>'</c:if>
						<c:if test="${empty E110.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[109].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E111.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E111.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E111.count}"/>].fieldText"
						<c:if test="${!empty E111.fieldText}">value='<c:out value="${E111.fieldText}"/>'</c:if>
						<c:if test="${empty E111.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[110].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E112.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E112.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E112.count}"/>].fieldText"
						<c:if test="${!empty E112.fieldText}">value='<c:out value="${E112.fieldText}"/>'</c:if>
						<c:if test="${empty E112.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[111].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E113.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E113.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E113.count}"/>].fieldText"
						<c:if test="${!empty E113.fieldText}">value='<c:out value="${E113.fieldText}"/>'</c:if>
						<c:if test="${empty E113.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[112].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E114.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E114.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E114.count}"/>].fieldText"
						<c:if test="${!empty E114.fieldText}">value='<c:out value="${E114.fieldText}"/>'</c:if>
						<c:if test="${empty E114.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[113].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E115.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E115.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E115.count}"/>].fieldText"
						<c:if test="${!empty E115.fieldText}">value='<c:out value="${E115.fieldText}"/>'</c:if>
						<c:if test="${empty E115.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[114].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E116.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E116.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E116.count}"/>].fieldText"
						<c:if test="${!empty E116.fieldText}">value='<c:out value="${E116.fieldText}"/>'</c:if>
						<c:if test="${empty E116.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[115].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E117.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E117.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E117.count}"/>].fieldText"
						<c:if test="${!empty E117.fieldText}">value='<c:out value="${E117.fieldText}"/>'</c:if>
						<c:if test="${empty E117.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[116].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E118.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E118.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E118.count}"/>].fieldText"
						<c:if test="${!empty E118.fieldText}">value='<c:out value="${E118.fieldText}"/>'</c:if>
						<c:if test="${empty E118.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[117].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E119.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E119.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E119.count}"/>].fieldText"
						<c:if test="${!empty E119.fieldText}">value='<c:out value="${E119.fieldText}"/>'</c:if>
						<c:if test="${empty E119.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[118].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E120.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E120.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E120.count}"/>].fieldText"
						<c:if test="${!empty E120.fieldText}">value='<c:out value="${E120.fieldText}"/>'</c:if>
						<c:if test="${empty E120.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[119].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E121.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E121.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E121.count}"/>].fieldText"
						<c:if test="${!empty E121.fieldText}">value='<c:out value="${E121.fieldText}"/>'</c:if>
						<c:if test="${empty E121.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[120].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E122.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E122.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E122.count}"/>].fieldText"
						<c:if test="${!empty E122.fieldText}">value='<c:out value="${E122.fieldText}"/>'</c:if>
						<c:if test="${empty E122.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[121].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E123.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E123.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E123.count}"/>].fieldText"
						<c:if test="${!empty E123.fieldText}">value='<c:out value="${E123.fieldText}"/>'</c:if>
						<c:if test="${empty E123.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[122].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="10"><c:choose>
				<c:when test="${E124.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E124.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E124.count}"/>].fieldText"
						<c:if test="${!empty E124.fieldText}">value='<c:out value="${E124.fieldText}"/>'</c:if>
						<c:if test="${empty E124.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[123].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="20"><c:choose>
				<c:when test="${E125.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E125.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E125.count}"/>].fieldText"
						<c:if test="${!empty E125.fieldText}">value='<c:out value="${E125.fieldText}"/>'</c:if>
						<c:if test="${empty E125.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[124].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E126.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E126.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E126.count}"/>].fieldText"
						<c:if test="${!empty E126.fieldText}">value='<c:out value="${E126.fieldText}"/>'</c:if>
						<c:if test="${empty E126.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[125].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E127.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E127.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E127.count}"/>].fieldText"
						<c:if test="${!empty E127.fieldText}">value='<c:out value="${E127.fieldText}"/>'</c:if>
						<c:if test="${empty E127.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[126].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E128.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E128.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E128.count}"/>].fieldText"
						<c:if test="${!empty E128.fieldText}">value='<c:out value="${E128.fieldText}"/>'</c:if>
						<c:if test="${empty E128.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[127].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="10"><c:choose>
				<c:when test="${E129.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E129.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E129.count}"/>].fieldText"
						<c:if test="${!empty E129.fieldText}">value='<c:out value="${E129.fieldText}"/>'</c:if>
						<c:if test="${empty E129.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[128].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E130.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E130.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E130.count}"/>].fieldText"
						<c:if test="${!empty E130.fieldText}">value='<c:out value="${E130.fieldText}"/>'</c:if>
						<c:if test="${empty E130.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[129].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
		<td colspan="15"><c:choose>
				<c:when test="${E131.isEdit == '1'}">
					<input type="Text" class="twics"
						id="fieldContents[<c:out value="${E131.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E131.count}"/>].fieldText"
						<c:if test="${!empty E131.fieldText}">value='<c:out value="${E131.fieldText}"/>'</c:if>
						<c:if test="${empty E131.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="twics"
						value="<c:out value="${fieldContents[130].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose></td>
	</tr>
	<tr>
		<td colspan="100">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="84" width="84%">备注：</td>
				</tr>
				<tr>
					<td height="48"><c:choose>
							<c:when test="${E132.isEdit == '1'}">
								<textarea  rows="4" cols="70" style="width: 100%"
									id="fieldContents[<c:out value="${E132.count}"/>].fieldText"
									name="fieldContents[<c:out value="${E132.count}"/>].fieldText">
									<c:out value="${E132.fieldText}" />
								</textarea>
							</c:when>
							<c:otherwise>
								<textarea rows="4" cols="70" readonly="readonly"
									style="width: 100%" class="textarea-hidden">
									<c:out value="${fieldContents[131].fieldText}" />
								</textarea>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</table>
		</td>
	</tr>


</table>

<table width="720" border=0 align=center cellpadding=0 cellspacing=0>
	<tr>
	<td colspan="50"><a style="font-size: 11;">备注：研发备料计划经研发总监批准后发给工厂计调部安排物料计划；</a></td>
		<td colspan="50" align="right">表单编号：<c:choose>
				<c:when test="${E133.isEdit == '1'}">
					<input type="Text" class="wics"
						id="fieldContents[<c:out value="${E133.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E133.count}"/>].fieldText"
						<c:if test="${!empty E133.fieldText}">value='<c:out value="${E133.fieldText}"/>'</c:if>
						<c:if test="${empty E133.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="wics"
						value="<c:out value="${fieldContents[132].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr><td colspan="100">&nbsp;</td></tr>
	<tr>
		<td colspan="33">项目经理：<c:choose>
				<c:when test="${E134.isEdit == '1'}">
					<input type="Text" class="wics"
						id="fieldContents[<c:out value="${E134.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E134.count}"/>].fieldText"
						<c:if test="${!empty E134.fieldText}">value='<c:out value="${E134.fieldText}"/>'</c:if>
						<c:if test="${empty E134.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="wics"
						value="<c:out value="${fieldContents[133].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</td>
		<td colspan="33">审核：<c:choose>
				<c:when test="${E135.isEdit == '1'}">
					<input type="Text" class="wics"
						id="fieldContents[<c:out value="${E135.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E135.count}"/>].fieldText"
						<c:if test="${!empty E135.fieldText}">value='<c:out value="${E135.fieldText}"/>'</c:if>
						<c:if test="${empty E135.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="wics"
						value="<c:out value="${fieldContents[134].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</td>
		<td colspan="34">研发总监：<c:choose>
				<c:when test="${E136.isEdit == '1'}">
					<input type="Text" class="wics"
						id="fieldContents[<c:out value="${E136.count}"/>].fieldText"
						name="fieldContents[<c:out value="${E136.count}"/>].fieldText"
						<c:if test="${!empty E136.fieldText}">value='<c:out value="${E136.fieldText}"/>'</c:if>
						<c:if test="${empty E136.fieldText}">value=""</c:if> />
				</c:when>
				<c:otherwise>
					<input type="Text" class="wics"
						value="<c:out value="${fieldContents[135].fieldText}"/>" disabled="disabled">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>