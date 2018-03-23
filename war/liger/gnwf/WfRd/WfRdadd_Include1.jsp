<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

	<!--组扩展字段内容页-->
	<%@ include file="IncudeExtendField.jsp"%>

 	<tr bgcolor="#ffffff">
		<th width="18%"><c:out value="${cate.fieldName}"/></th>
		<td>
			<select name="fieldContents[<c:out value="${cate.count}"/>].fieldText" style="width: 200px;">
				<option value="0" <c:if test="${cate.fieldText==0}">selected</c:if>>请选择</option>
				<option value="1" <c:if test="${cate.fieldText==1}">selected</c:if>>A类变更</option>
				<option value="2" <c:if test="${cate.fieldText==2}">selected</c:if>>B类变更</option>
				<option value="3" <c:if test="${cate.fieldText==3}">selected</c:if>>C类变更</option>
			</select>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<th width="18%"><c:out value="${Test.fieldName}"/></th>
		<td>
			<input type="Text" name="fieldContents[<c:out value="${Test.count}"/>].fieldText" size="48" value="<c:out value="${Test.fieldText}"/>">
			&nbsp;&nbsp;<font color="red">*</font>
		</td>
	</tr>
	<tr bgcolor="#ffffff">
		<th width="18%"><c:out value="${Test1.fieldName}"/></th>
		<td>
			<input type="Text" name="fieldContents[<c:out value="${Test1.count}"/>].fieldText" size="48" value="<c:out value="${Test1.fieldText}"/>">
		</td>
	</tr>