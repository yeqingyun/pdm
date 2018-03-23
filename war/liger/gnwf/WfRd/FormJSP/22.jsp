<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面22jsp</span>
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>研发备料计划表</strong></p>
<table width="680" border=1 bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>

  <tr height="30">
    <td colspan="2">产品型号:

<c:choose>
    <c:when test="${E1.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E1.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[0].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td colspan="4">申请部门:

<c:choose>
    <c:when test="${E2.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[1].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td colspan="2">申请日期：

<c:choose>
    <c:when test="${E3.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[2].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td width="46"><div align="center">序号</div></td>
    <td width="91"><div align="center">物料名称</div></td>
    <td width="97"><div align="center">规格/材质</div></td>
    <td width="82"><div align="center">单机用量</div></td>
    <td width="73"><div align="center">数量</div></td>
    <td width="73"><div align="center">需求时间</div></td>
    <td width="99"><div align="center">用途</div></td>
    <td width="101"><div align="center">备注</div></td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E4.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[3].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E5.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E5.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[4].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E6.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[5].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E7.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[6].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E8.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[7].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E9.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="<c:out value="${E9.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[8].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E10.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[9].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E11.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E11.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="<c:out value="${E11.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[10].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E12.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E12.count}"/>].fieldText" value="<c:out value="${E12.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[11].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E13.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E13.count}"/>].fieldText" value="<c:out value="${E13.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[12].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E14.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E14.count}"/>].fieldText" value="<c:out value="${E14.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[13].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E15.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E15.count}"/>].fieldText" value="<c:out value="${E15.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[14].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E16.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E16.count}"/>].fieldText" value="<c:out value="${E16.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[15].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E17.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E17.count}"/>].fieldText" value="<c:out value="${E17.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[16].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E18.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="<c:out value="${E18.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[17].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E19.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E19.count}"/>].fieldText" value="<c:out value="${E19.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[18].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E20.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E20.count}"/>].fieldText" value="<c:out value="${E20.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[19].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E21.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E21.count}"/>].fieldText" value="<c:out value="${E21.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[20].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E22.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E22.count}"/>].fieldText" value="<c:out value="${E22.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[21].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E23.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E23.count}"/>].fieldText" value="<c:out value="${E23.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[22].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E24.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E24.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E24.count}"/>].fieldText" value="<c:out value="${E24.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[23].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E25.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E25.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E25.count}"/>].fieldText" value="<c:out value="${E25.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[24].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E26.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E26.count}"/>].fieldText" value="<c:out value="${E26.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[25].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E27.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E27.count}"/>].fieldText" value="<c:out value="${E27.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[26].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E28.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E28.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="<c:out value="${E28.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[27].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E29.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E29.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E29.count}"/>].fieldText" value="<c:out value="${E29.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[28].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E30.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E30.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E30.count}"/>].fieldText" value="<c:out value="${E30.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[29].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E31.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E31.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E31.count}"/>].fieldText" value="<c:out value="${E31.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[30].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E32.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E32.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E32.count}"/>].fieldText" value="<c:out value="${E32.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[31].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E33.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E33.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E33.count}"/>].fieldText" value="<c:out value="${E33.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[32].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E34.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E34.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E34.count}"/>].fieldText" value="<c:out value="${E34.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[33].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E35.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E35.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E35.count}"/>].fieldText" value="<c:out value="${E35.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[34].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E36.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E36.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E36.count}"/>].fieldText" value="<c:out value="${E36.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[35].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E37.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E37.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E37.count}"/>].fieldText" value="<c:out value="${E37.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[36].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E38.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E38.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E38.count}"/>].fieldText" value="<c:out value="${E38.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[37].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E39.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E39.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E39.count}"/>].fieldText" value="<c:out value="${E39.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[38].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E40.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E40.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E40.count}"/>].fieldText" value="<c:out value="${E40.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[39].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E41.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E41.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E41.count}"/>].fieldText" value="<c:out value="${E41.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[40].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E42.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E42.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E42.count}"/>].fieldText" value="<c:out value="${E42.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[41].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E43.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E43.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E43.count}"/>].fieldText" value="<c:out value="${E43.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[42].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E44.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E44.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E44.count}"/>].fieldText" value="<c:out value="${E44.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[43].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E45.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E45.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E45.count}"/>].fieldText" value="<c:out value="${E45.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[44].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E46.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E46.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E46.count}"/>].fieldText" value="<c:out value="${E46.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[45].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E47.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E47.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E47.count}"/>].fieldText" value="<c:out value="${E47.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[46].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E48.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E48.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E48.count}"/>].fieldText" value="<c:out value="${E48.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[47].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E49.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E49.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E49.count}"/>].fieldText" value="<c:out value="${E49.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[48].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E50.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E50.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E50.count}"/>].fieldText" value="<c:out value="${E50.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[49].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E51.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E51.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E51.count}"/>].fieldText" value="<c:out value="${E51.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[50].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E52.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E52.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E52.count}"/>].fieldText" value="<c:out value="${E52.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[51].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E53.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E53.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E53.count}"/>].fieldText" value="<c:out value="${E53.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[52].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E54.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E54.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E54.count}"/>].fieldText" value="<c:out value="${E54.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[53].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E55.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E55.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E55.count}"/>].fieldText" value="<c:out value="${E55.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[54].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E56.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E56.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E56.count}"/>].fieldText" value="<c:out value="${E56.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[55].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E57.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E57.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E57.count}"/>].fieldText" value="<c:out value="${E57.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[56].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E58.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E58.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E58.count}"/>].fieldText" value="<c:out value="${E58.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[57].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E59.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E59.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E59.count}"/>].fieldText" value="<c:out value="${E59.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[58].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E60.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E60.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E60.count}"/>].fieldText" value="<c:out value="${E60.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[59].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E61.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E61.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E61.count}"/>].fieldText" value="<c:out value="${E61.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[60].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E62.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E62.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E62.count}"/>].fieldText" value="<c:out value="${E62.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[61].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E63.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E63.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E63.count}"/>].fieldText" value="<c:out value="${E63.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[62].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E64.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E64.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E64.count}"/>].fieldText" value="<c:out value="${E64.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[63].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E65.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E65.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E65.count}"/>].fieldText" value="<c:out value="${E65.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[64].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E66.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E66.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E66.count}"/>].fieldText" value="<c:out value="${E66.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[65].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E67.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E67.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E67.count}"/>].fieldText" value="<c:out value="${E67.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[66].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E68.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E68.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E68.count}"/>].fieldText" value="<c:out value="${E68.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[67].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E69.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E69.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E69.count}"/>].fieldText" value="<c:out value="${E69.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[68].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E70.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E70.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E70.count}"/>].fieldText" value="<c:out value="${E70.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[69].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E71.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E71.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E71.count}"/>].fieldText" value="<c:out value="${E71.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[70].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E72.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E72.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E72.count}"/>].fieldText" value="<c:out value="${E72.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[71].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E73.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E73.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E73.count}"/>].fieldText" value="<c:out value="${E73.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[72].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E74.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E74.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E74.count}"/>].fieldText" value="<c:out value="${E74.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[73].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E75.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E75.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E75.count}"/>].fieldText" value="<c:out value="${E75.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[74].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E76.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E76.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E76.count}"/>].fieldText" value="<c:out value="${E76.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[75].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E77.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E77.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E77.count}"/>].fieldText" value="<c:out value="${E77.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[76].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E78.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E78.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E78.count}"/>].fieldText" value="<c:out value="${E78.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[77].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E79.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E79.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E79.count}"/>].fieldText" value="<c:out value="${E79.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[78].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E80.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E80.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E80.count}"/>].fieldText" value="<c:out value="${E80.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[79].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E81.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E81.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E81.count}"/>].fieldText" value="<c:out value="${E81.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[80].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E82.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E82.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E82.count}"/>].fieldText" value="<c:out value="${E82.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[81].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E83.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E83.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E83.count}"/>].fieldText" value="<c:out value="${E83.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[82].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E84.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E84.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E84.count}"/>].fieldText" value="<c:out value="${E84.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[83].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E85.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E85.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E85.count}"/>].fieldText" value="<c:out value="${E85.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[84].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E86.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E86.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E86.count}"/>].fieldText" value="<c:out value="${E86.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[85].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E87.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E87.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E87.count}"/>].fieldText" value="<c:out value="${E87.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[86].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E88.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E88.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E88.count}"/>].fieldText" value="<c:out value="${E88.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[87].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E89.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E89.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E89.count}"/>].fieldText" value="<c:out value="${E89.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[88].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E90.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E90.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E90.count}"/>].fieldText" value="<c:out value="${E90.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[89].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E91.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E91.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E91.count}"/>].fieldText" value="<c:out value="${E91.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[90].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E92.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E92.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E92.count}"/>].fieldText" value="<c:out value="${E92.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[91].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E93.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E93.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E93.count}"/>].fieldText" value="<c:out value="${E93.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[92].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E94.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E94.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E94.count}"/>].fieldText" value="<c:out value="${E94.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[93].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E95.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E95.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E95.count}"/>].fieldText" value="<c:out value="${E95.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[94].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E96.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E96.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E96.count}"/>].fieldText" value="<c:out value="${E96.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[95].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E97.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E97.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E97.count}"/>].fieldText" value="<c:out value="${E97.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[96].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E98.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E98.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E98.count}"/>].fieldText" value="<c:out value="${E98.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[97].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E99.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E99.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E99.count}"/>].fieldText" value="<c:out value="${E99.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[98].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E100.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E100.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E100.count}"/>].fieldText" value="<c:out value="${E100.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[99].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E101.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E101.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E101.count}"/>].fieldText" value="<c:out value="${E101.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[100].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E102.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E102.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E102.count}"/>].fieldText" value="<c:out value="${E102.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[101].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E103.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E103.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E103.count}"/>].fieldText" value="<c:out value="${E103.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[102].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E104.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E104.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E104.count}"/>].fieldText" value="<c:out value="${E104.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[103].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E105.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E105.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E105.count}"/>].fieldText" value="<c:out value="${E105.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[104].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E106.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E106.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E106.count}"/>].fieldText" value="<c:out value="${E106.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[105].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E107.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E107.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E107.count}"/>].fieldText" value="<c:out value="${E107.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[106].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E108.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E108.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E108.count}"/>].fieldText" value="<c:out value="${E108.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[107].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E109.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E109.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E109.count}"/>].fieldText" value="<c:out value="${E109.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[108].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E110.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E110.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E110.count}"/>].fieldText" value="<c:out value="${E110.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[109].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E111.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E111.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E111.count}"/>].fieldText" value="<c:out value="${E111.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[110].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E112.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E112.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E112.count}"/>].fieldText" value="<c:out value="${E112.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[111].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E113.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E113.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E113.count}"/>].fieldText" value="<c:out value="${E113.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[112].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E114.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E114.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E114.count}"/>].fieldText" value="<c:out value="${E114.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[113].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E115.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E115.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E115.count}"/>].fieldText" value="<c:out value="${E115.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[114].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E116.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E116.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E116.count}"/>].fieldText" value="<c:out value="${E116.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[115].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E117.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E117.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E117.count}"/>].fieldText" value="<c:out value="${E117.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[116].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E118.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E118.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E118.count}"/>].fieldText" value="<c:out value="${E118.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[117].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E119.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E119.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E119.count}"/>].fieldText" value="<c:out value="${E119.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[118].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E120.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E120.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E120.count}"/>].fieldText" value="<c:out value="${E120.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[119].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E121.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E121.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E121.count}"/>].fieldText" value="<c:out value="${E121.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[120].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E122.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E122.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E122.count}"/>].fieldText" value="<c:out value="${E122.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[121].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E123.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E123.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E123.count}"/>].fieldText" value="<c:out value="${E123.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[122].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E124.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E124.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E124.count}"/>].fieldText" value="<c:out value="${E124.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[123].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E125.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E125.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E125.count}"/>].fieldText" value="<c:out value="${E125.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[124].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E126.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E126.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E126.count}"/>].fieldText" value="<c:out value="${E126.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[125].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E127.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E127.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E127.count}"/>].fieldText" value="<c:out value="${E127.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[126].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E128.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E128.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E128.count}"/>].fieldText" value="<c:out value="${E128.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[127].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E129.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E129.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E129.count}"/>].fieldText" value="<c:out value="${E129.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[128].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E130.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E130.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E130.count}"/>].fieldText" value="<c:out value="${E130.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[129].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E131.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E131.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E131.count}"/>].fieldText" value="<c:out value="${E131.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[130].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td>

<c:choose>
    <c:when test="${E132.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E132.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E132.count}"/>].fieldText" value="<c:out value="${E132.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[131].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E133.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E133.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E133.count}"/>].fieldText" value="<c:out value="${E133.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[132].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E134.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E134.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E134.count}"/>].fieldText" value="<c:out value="${E134.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[133].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E135.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E135.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E135.count}"/>].fieldText" value="<c:out value="${E135.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[134].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E136.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E136.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E136.count}"/>].fieldText" value="<c:out value="${E136.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[135].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E137.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E137.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E137.count}"/>].fieldText" value="<c:out value="${E137.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[136].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E138.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E138.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E138.count}"/>].fieldText" value="<c:out value="${E138.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[137].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
    <td>

<c:choose>
    <c:when test="${E139.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E139.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E139.count}"/>].fieldText" value="<c:out value="${E139.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[138].fieldText}"/>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
  <tr height="30">
    <td height="97" colspan="8" valign="top">备注:
	<br/>

<c:choose>
    <c:when test="${E140.isEdit == '1'}">
        <textarea  rows="4" cols="70" style="width: 670px" id="fieldContents[<c:out value="${E140.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E140.count}"/>].fieldText"><c:out value="${E140.fieldText}"/></textarea>
    </c:when>
    <c:otherwise>
        <textarea rows="4" cols="70" style="width: 670px" readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[139].fieldText}"/></textarea>
    </c:otherwise>
</c:choose>
	</td>
  </tr>
</table>
<table width="680" align="center" style="font-size:13px">
	<tr >
<td colspan="5">备注：研发备料计划经研发总监批准后发给工厂计调部安排物料计划</td>
  <td width="160">表格编号：

<c:choose>
    <c:when test="${E141.isEdit == '1'}">
        <input type="Text" size="20" style="width:85px" id="fieldContents[<c:out value="${E141.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E141.count}"/>].fieldText" value="<c:out value="${E141.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[140].fieldText}"/>
    </c:otherwise>
</c:choose>
  </td>
  </tr>
  <tr>
	 <td height="30" align="right" colspan="3"> <div align="left">项目经理：

<c:choose>
    <c:when test="${E142.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E142.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E142.count}"/>].fieldText" value="<c:out value="${E142.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[141].fieldText}"/>
    </c:otherwise>
</c:choose>
	 </div></td>
    <td height="30" align="right" colspan="2"> <div align="left">审核：

<c:choose>
    <c:when test="${E143.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E143.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E143.count}"/>].fieldText" value="<c:out value="${E143.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[142].fieldText}"/>
    </c:otherwise>
</c:choose>
	</div></td>
    <td height="30" align="right" colspan="1"><div align="left">研发总监：

<c:choose>
    <c:when test="${E144.isEdit == '1'}">
        <input type="Text" size="20" id="fieldContents[<c:out value="${E144.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E144.count}"/>].fieldText" value="<c:out value="${E144.fieldText}"/>" >
    </c:when>
    <c:otherwise>
        <c:out value="${fieldContents[143].fieldText}"/>
    </c:otherwise>
</c:choose>
	</div></td>
  </tr>

</table>
