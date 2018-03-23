<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<span style="visibility:hidden">页面20jsp</span>
	<p align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
	<strong>整机试 产 通 知 单</strong></p>
	
	<table   width="680" border=1  bordercolor=#000000 align=center cellpadding=0 cellspacing=0  style='border-collapse:collapse;font-size:11.0pt'>
   
	    <tr height="30">
	       	<td width="150"><div align="center">产品型号</div></td>
	      	<td width="150">&nbsp;
				<c:choose>
				    <c:when test="${E3.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
				    </c:when>
				    <c:otherwise>
				        <c:out value="${fieldContents[2].fieldText}"/>
				    </c:otherwise>
				</c:choose>
		   	</td>
		    <td width="150"><div align="center">要求试产日期</div></td>
		    <td width="230">&nbsp;
				<c:choose>
				    <c:when test="${E4.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E4.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
				    </c:when>
				    <c:otherwise>
				        <c:out value="${fieldContents[3].fieldText}"/>
				    </c:otherwise>
				</c:choose>
		   </td>
	    </tr>
    
	    <tr>
		    <td height="30"><div align="center">发出部门</div></td>
		    <td>&nbsp;
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
				    <td><div align="center">项目经理</div></td>
				    <td>&nbsp;
				
				<c:choose>
				    <c:when test="${E6.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
				    </c:when>
				    <c:otherwise>
				        <c:out value="${fieldContents[5].fieldText}"/>
				    </c:otherwise>
				</c:choose>
		    </td>
	     </tr>
     
  <tr>
    <td height="30"><div align="center">试产类型</div></td>
    <td height="30" colspan="3"> 
    <c:choose>
    <c:when test="${E11.isEdit == '1'}">
        <input name="fieldContents[<c:out value="${E11.count}"/>].fieldText"  type="checkbox" value="1"
            <c:if test="${E11.fieldText == '1'}">checked</c:if> />
		 第
        <input type="Text" size="2" id="fieldContents[<c:out value="${E7.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" >
		次小批量（
        <input type="Text" size="3" id="fieldContents[<c:out value="${E8.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" >
	    ）台
        <input name="fieldContents[<c:out value="${E29.count}"/>].fieldText"  type="checkbox" value="2"
            <c:if test="${E29.fieldText == '2'}">checked</c:if> />
		第
        <input type="Text" size="2" id="fieldContents[<c:out value="${E9.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="<c:out value="${E9.fieldText}"/>" >
      次中批量（
        <input type="Text" size="3" id="fieldContents[<c:out value="${E10.count}"/>].fieldText"
            name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="<c:out value="${E10.fieldText}"/>" >
	  ）台
    </c:when>
    <c:otherwise>
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[10].fieldText == '1'}">checked</c:if> />
		 第
        <c:out value="${fieldContents[6].fieldText}"/>
        次小批量（
        <c:out value="${fieldContents[7].fieldText}"/>
	    ）台
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[28].fieldText == '2'}">checked</c:if> />
		第
        <c:out value="${fieldContents[8].fieldText}"/>
      次中批量（
        <c:out value="${fieldContents[9].fieldText}"/>
	  ）台
    </c:otherwise>
    </c:choose>
	</td>
  </tr>
  
		 <tr>
		    <td height="400px" colspan="4" valign="top">
		    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="20">试产方案及注意事项：</td>
				</tr>
				<tr>
					<td height="95%" >
					      <input type="hidden" id = "aaa" value ="fieldContents[<c:out value="${E12.count}"/>].fieldText">
					      
					       <textarea editor="true"
	<c:choose>
	    <c:when test="${E12.isEdit == '1'}">
	    </c:when>
	    <c:otherwise>
	    readonly="readonly"
	    </c:otherwise>
	</c:choose>
	          rows="30" style="width: 660px"  cols="70" id="fieldContents[<c:out value="${E12.count}"/>].fieldText"
	            name="fieldContents[<c:out value="${E12.count}"/>].fieldText"><c:out value="${fieldContents[11].fieldText}"/></textarea>
					        
					    
					    
					<!--
					<c:choose>
					    <c:when test="${E12.isEdit == '1'}">
					    </c:when>
					    <c:otherwise>
					        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${E12.fieldText}"/></textarea>
					    </c:otherwise>
					</c:choose>
				  -->
					</td>
				</tr>
				<tr>
					<td height="30" align="right">
					部门经理：
					<c:choose>
					    <c:when test="${E13.isEdit == '1'}">
					        <input type="Text" size="20" id="fieldContents[<c:out value="${E13.count}"/>].fieldText"
					            name="fieldContents[<c:out value="${E13.count}"/>].fieldText" 
								<c:if test="${!empty E13.fieldText}">value="<c:out value="${E13.fieldText}"/>"</c:if>
								<c:if test="${empty E13.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
					            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
					    </c:when>
					    <c:otherwise>
					        <input type="Text" size="20" value="<c:out value="${fieldContents[12].fieldText}"/>" disabled="disabled"
					            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
					    </c:otherwise>
					</c:choose>
					 &nbsp;日&nbsp;期：
					<c:choose>
					    <c:when test="${E14.isEdit == '1'}">
					        <input type="Text" size="20" id="fieldContents[<c:out value="${E14.count}"/>].fieldText"
					            name="fieldContents[<c:out value="${E14.count}"/>].fieldText" 
								<c:if test="${!empty E14.fieldText}">value="<c:out value="${E14.fieldText}"/>"</c:if>
								<c:if test="${empty E14.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
					            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
					    </c:when>
					    <c:otherwise>
					        <input type="Text" size="20" value="<c:out value="${fieldContents[13].fieldText}"/>" disabled="disabled"
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
	    <td height="100" colspan="4" valign="top">
	    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="20">核对物料齐套情况：（结构物料）</td>
			</tr>
			<tr>
				<td height="40" >
				<input type="hidden" id="filedI2"  size="30" value="fieldContents[<c:out value="${E26.count}"/>].fieldText"/>
				<c:choose>
				    <c:when test="${E26.isEdit == '1'}">
				<textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E26.count}"/>].fieldText"
				name="fieldContents[<c:out value="${E26.count}"/>].fieldText"><c:out value="${E26.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[25].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">
				物 控：
				<c:choose>
				    <c:when test="${E27.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E27.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
							<c:if test="${!empty E27.fieldText}">value="<c:out value="${E27.fieldText}"/>"</c:if>
							<c:if test="${empty E27.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[26].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E28.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E28.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E28.count}"/>].fieldText" 
							<c:if test="${!empty E28.fieldText}">value="<c:out value="${E28.fieldText}"/>"</c:if>
							<c:if test="${empty E28.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[27].fieldText}"/>" disabled="disabled"
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
	    <td height="100" colspan="4" valign="top">
	    <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="20">核对物料齐套情况：（电子物料）</td>
			</tr>
			<tr>
				<td height="40" >
				       
				<c:choose>
				    <c:when test="${E15.isEdit == '1'}">
				        <textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E15.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E15.count}"/>].fieldText"><c:out value="${E15.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[14].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">
				物 控：
				<c:choose>
				    <c:when test="${E16.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E16.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E16.count}"/>].fieldText" 
							<c:if test="${!empty E16.fieldText}">value="<c:out value="${E16.fieldText}"/>"</c:if>
							<c:if test="${empty E16.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[15].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				 &nbsp;日&nbsp;期：
				<c:choose>
				    <c:when test="${E17.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E17.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E17.count}"/>].fieldText" 
							<c:if test="${!empty E17.fieldText}">value="<c:out value="${E17.fieldText}"/>"</c:if>
							<c:if test="${empty E17.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[16].fieldText}"/>" disabled="disabled"
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
	     <td height="80" colspan="4" valign="top">
	     
	      <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="18">计调部安排并回复：</td>
			</tr>
			<tr>
				<td height="24">
				<table width="100%">
				  <tr height="30">
				    <td width="120"><div align="center">生产车间：</div></td>
				    <td width="120">
						<c:choose>
						    <c:when test="${E18.isEdit == '1'}">
						        <input type="Text" size="14" id="fieldContents[<c:out value="${E18.count}"/>].fieldText"
						            name="fieldContents[<c:out value="${E18.count}"/>].fieldText" value="<c:out value="${E18.fieldText}"/>"
						            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
						    </c:when>
						    <c:otherwise>
						        <input type="Text" size="14" value="<c:out value="${fieldContents[17].fieldText}"/>" disabled="disabled"
						            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
						    </c:otherwise>
						</c:choose>
					</td>
				    <td width="80" align="center">产线：</td>
				    <td width="100">
						<c:choose>
						    <c:when test="${E19.isEdit == '1'}">
						        <input type="Text" size="14" id="fieldContents[<c:out value="${E19.count}"/>].fieldText"
						            name="fieldContents[<c:out value="${E19.count}"/>].fieldText" value="<c:out value="${E19.fieldText}"/>"
						            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
						    </c:when>
						    <c:otherwise>
						        <input type="Text" size="14" value="<c:out value="${fieldContents[18].fieldText}"/>" disabled="disabled"
						            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
						    </c:otherwise>
						</c:choose>
					</td>
					
					<td width="120" align="center">试产时间：</td>
				    <td width="120">
						<c:choose>
						    <c:when test="${E20.isEdit == '1'}">
						        <input type="Text" size="14" id="fieldContents[<c:out value="${E20.count}"/>].fieldText"
						            name="fieldContents[<c:out value="${E20.count}"/>].fieldText" value="<c:out value="${E20.fieldText}"/>"
						            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
						    </c:when>
						    <c:otherwise>
						        <input type="Text" size="14" value="<c:out value="${fieldContents[19].fieldText}"/>" disabled="disabled"
						            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
						    </c:otherwise>
						</c:choose>&nbsp;
					</td>
				   </tr>
				  </table>
				</td>
			  </tr>
	       </table>
	     </td>
	  </tr>
	     
	
	
			<tr>
				<td height="30"  colspan="4" align="right">
				签 名：
				<c:choose>
				    <c:when test="${E21.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E21.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E21.count}"/>].fieldText" 
							<c:if test="${!empty E21.fieldText}">value="<c:out value="${E21.fieldText}"/>"</c:if>
							<c:if test="${empty E21.fieldText}">value="<c:out value="${user.usrName}"/>"</c:if> 
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[20].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				  &nbsp;日&nbsp;期： 
				<c:choose>
				    <c:when test="${E22.isEdit == '1'}">
				        <input type="Text" size="20" id="fieldContents[<c:out value="${E22.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E22.count}"/>].fieldText" 
							<c:if test="${!empty E22.fieldText}">value="<c:out value="${E22.fieldText}"/>"</c:if>
							<c:if test="${empty E22.fieldText}">value="<fmt:formatDate value="${currentDate}"/>"</c:if>
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;"/>
				    </c:when>
				    <c:otherwise>
				        <input type="Text" size="20" value="<c:out value="${fieldContents[21].fieldText}"/>" disabled="disabled"
				            style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				    </c:otherwise>
				</c:choose>
				  &nbsp;
				</td>
			</tr>
			
		  <tr>
		    <td height="80" colspan="4" valign="top">备注：
				<br/>
				<c:choose>
				    <c:when test="${E23.isEdit == '1'}">
				        <textarea  rows="4" cols="70" style="width: 670px"  id="fieldContents[<c:out value="${E23.count}"/>].fieldText"
				            name="fieldContents[<c:out value="${E23.count}"/>].fieldText"><c:out value="${E23.fieldText}"/></textarea>
				    </c:when>
				    <c:otherwise>
				        <textarea rows="4" cols="70" style="width: 670px"  readonly="readonly" class="textarea-hidden"><c:out value="${fieldContents[22].fieldText}"/></textarea>
				    </c:otherwise>
				</c:choose>
			</td>
		  </tr>
		  
		  <tr>
			  <td colspan="3">
			  <label for="shouban"></label>&nbsp;CC:
<c:choose>
    <c:when test="${E24.isEdit == '1'}">
        <input name="fieldContents[<c:out value="${E24.count}"/>].fieldText"  type="checkbox" value="1"
            <c:if test="${E24.fieldText == '1'}">checked</c:if> />采购部&nbsp;
        <input name="fieldContents[<c:out value="${E26.count}"/>].fieldText"  type="checkbox" value="2"
            <c:if test="${E26.fieldText == '2'}">checked</c:if> />计调部&nbsp;
        <input name="fieldContents[<c:out value="${E27.count}"/>].fieldText"  type="checkbox" value="3"
            <c:if test="${E27.fieldText == '3'}">checked</c:if> />生产部&nbsp;
        <input name="fieldContents[<c:out value="${E28.count}"/>].fieldText"  type="checkbox" value="4"
            <c:if test="${E28.fieldText == '4'}">checked</c:if> />品质部&nbsp;
        <input name="fieldContents[<c:out value="${E30.count}"/>].fieldText"  type="checkbox" value="5"
            <c:if test="${E30.fieldText == '5'}">checked</c:if> />工程部 &nbsp;
    </c:when>
    <c:otherwise>
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[23].fieldText == '1'}">checked</c:if> />采购部&nbsp;
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[25].fieldText == '2'}">checked</c:if> />计调部&nbsp;
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[26].fieldText == '3'}">checked</c:if> />生产部&nbsp;
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[27].fieldText == '4'}">checked</c:if> />品质部&nbsp;
        <input type="checkbox" disabled="disabled" <c:if test="${fieldContents[29].fieldText == '5'}">checked</c:if> />工程部 &nbsp;
    </c:otherwise>
</c:choose>
    </td>
    <td width="190" align="left">表格编号：
		<c:choose>
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
</table>

