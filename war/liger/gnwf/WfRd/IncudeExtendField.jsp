<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>

<%@ page import="java.util.List"%>
<%@ page import="gnwf.vo.WfRdField"%>
 <!--  <script language="JavaScript" src="./include/js/gnwf/WfRd_special.js"></script>  -->
<div id="fielddiv">
<!-- //asdasdasd adsfasf  -->
	<% 
		//扩展字段包含页面，把List<WfRdField>组成扩展属性字段内容
		List<WfRdField> list = (List<WfRdField>)request.getAttribute("fieldContents");
		
		if(list!=null && list.size()>0){
			StringBuffer notNullStr = new StringBuffer();
			for(int i=0;i<list.size();i++){
				WfRdField field = list.get(i);
				if(field!=null && "notNull".equalsIgnoreCase(field.getIsNull()) && field.getIsEdit()==1){
					notNullStr.append(field.getCount()+",");	//记录不能为空字段
				}
				if(field!=null && field.getRowId()<=0){
					request.setAttribute(field.getFieldCode(),field);	//单值的扩展字段
					if(field.getIsEdit()==1){
	%>
					<input type="hidden" id="<%=field.getCount()%>" name="fieldContents[<%=field.getCount()%>].fieldId" value="<%=field.getFieldId()%>">
	<% 
					}
				}
			}
			request.setAttribute("notNullStr",notNullStr.toString());
			request.setAttribute("size",list.size());
		}
	%>
</div>
<input type="hidden" id="fcount" value="<c:out value="${size}"/>"/>
<input type="hidden" id="notNullStr" value="<c:out value="${notNullStr}"/>"/>
