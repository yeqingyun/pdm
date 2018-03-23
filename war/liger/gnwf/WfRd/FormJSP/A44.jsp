<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>软件需求变更申请表</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066CC cellpadding=3 align=center>
  <tr>
    <td colspan="16" width="16%" height="30" align="center">项目名称</td>
	<td colspan="24" width="24%">
		<!-- 
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E1.fieldText}"/>
			</c:otherwise>
		</c:choose>
		 -->
		 
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<select name="fieldContents[<c:out value="${E1.count}"/>].fieldText">
					<option value="1001" <c:if test="${E1.fieldText == 1001}">selected</c:if>>&nbsp;&nbsp;金立售后系统</option>
					<option value="1002" <c:if test="${E1.fieldText == 1002}">selected</c:if>>&nbsp;&nbsp;欧信售后系统</option>
					<option value="1003" <c:if test="${E1.fieldText == 1003}">selected</c:if>>&nbsp;&nbsp;握手网</option>
					<option value="1004" <c:if test="${E1.fieldText == 1004}">selected</c:if>>&nbsp;&nbsp;金立广告核销系统</option>
					<option value="1005" <c:if test="${E1.fieldText == 1005}">selected</c:if>>&nbsp;&nbsp;WMS仓库管理系统</option>
					<option value="1006" <c:if test="${E1.fieldText == 1006}">selected</c:if>>&nbsp;&nbsp;金立OA办公系统</option>
					<option value="1007" <c:if test="${E1.fieldText == 1007}">selected</c:if>>&nbsp;&nbsp;金立资源管理平台</option>
					<option value="1008" <c:if test="${E1.fieldText == 1008}">selected</c:if>>&nbsp;&nbsp;IMC广告物料管理系统</option>
					<option value="1009" <c:if test="${E1.fieldText == 1009}">selected</c:if>>&nbsp;&nbsp;金立售后培训系统</option>
					<option value="1010" <c:if test="${E1.fieldText == 1010}">selected</c:if>>&nbsp;&nbsp;工具管理平台</option>
					<option value="1011" <c:if test="${E1.fieldText == 1011}">selected</c:if>>&nbsp;&nbsp;联通信息交互平台</option>
					<option value="1012" <c:if test="${E1.fieldText == 1012}">selected</c:if>>&nbsp;&nbsp;MES系统</option>
					<option value="2001" <c:if test="${E1.fieldText == 2001}">selected</c:if>>&nbsp;&nbsp;金铭扫描系统</option>
					<option value="2002" <c:if test="${E1.fieldText == 2002}">selected</c:if>>&nbsp;&nbsp;工资系统</option>
					<option value="2003" <c:if test="${E1.fieldText == 2003}">selected</c:if>>&nbsp;&nbsp;金蝶二次开发</option>
					<option value="2004" <c:if test="${E1.fieldText == 2004}">selected</c:if>>&nbsp;&nbsp;欧新物控系统</option>
					<option value="2005" <c:if test="${E1.fieldText == 2005}">selected</c:if>>&nbsp;&nbsp;欧新扫描系统</option>
					<option value="2006" <c:if test="${E1.fieldText == 2006}">selected</c:if>>&nbsp;&nbsp;固定资产</option>
					<option value="2007" <c:if test="${E1.fieldText == 2007}">selected</c:if>>&nbsp;&nbsp;项目部其它</option>
				</select>
			</c:when>
			<c:otherwise>
				<select disabled="disabled">
					<option value="1001" <c:if test="${E1.fieldText == 1001}">selected</c:if>>金立售后系统</option>
					<option value="1002" <c:if test="${E1.fieldText == 1002}">selected</c:if>>欧信售后系统</option>
					<option value="1003" <c:if test="${E1.fieldText == 1003}">selected</c:if>>握手网</option>
					<option value="1004" <c:if test="${E1.fieldText == 1004}">selected</c:if>>金立广告核销系统</option>
					<option value="1005" <c:if test="${E1.fieldText == 1005}">selected</c:if>>WMS仓库管理系统</option>
					<option value="1006" <c:if test="${E1.fieldText == 1006}">selected</c:if>>金立OA办公系统</option>
					<option value="1007" <c:if test="${E1.fieldText == 1007}">selected</c:if>>金立资源管理平台</option>
					<option value="1008" <c:if test="${E1.fieldText == 1008}">selected</c:if>>IMC广告物料管理系统</option>
					<option value="1009" <c:if test="${E1.fieldText == 1009}">selected</c:if>>金立售后培训系统</option>
					<option value="1010" <c:if test="${E1.fieldText == 1010}">selected</c:if>>工具管理平台</option>
					<option value="1011" <c:if test="${E1.fieldText == 1011}">selected</c:if>>联通信息交互平台</option>
					<option value="1012" <c:if test="${E1.fieldText == 1012}">selected</c:if>>MES系统</option>
					<option value="2001" <c:if test="${E1.fieldText == 2001}">selected</c:if>>金铭扫描系统</option>
					<option value="2002" <c:if test="${E1.fieldText == 2002}">selected</c:if>>工资系统</option>
					<option value="2003" <c:if test="${E1.fieldText == 2003}">selected</c:if>>金蝶二次开发</option>
					<option value="2004" <c:if test="${E1.fieldText == 2004}">selected</c:if>>欧新物控系统</option>
					<option value="2005" <c:if test="${E1.fieldText == 2005}">selected</c:if>>欧新扫描系统</option>
					<option value="2006" <c:if test="${E1.fieldText == 2006}">selected</c:if>>固定资产</option>
					<option value="2007" <c:if test="${E1.fieldText == 2007}">selected</c:if>>项目部其它</option>
				</select>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center">申请人</td>
    <td colspan="15" width="15%">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
						<c:if test="${!empty E2.fieldText}">value="<c:out value="${E2.fieldText}"/>"</c:if>
						<c:if test="${empty E2.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						style=" border:0px; background:#EFF0F2">
			</c:when>
			<c:otherwise>
				<input type="Text" size="20" value="<c:out value="${E2.fieldText}"/>" disabled="disabled"
					style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center">申请部门</td>
    <td colspan="15" width="15%">
    	<!-- 
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E3.fieldText}"/>
			</c:otherwise>
		</c:choose>
		 -->
		
			<c:choose>
				<c:when test="${E3.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
						 <c:if test="${!empty E3.fieldText}">value="<c:out value="${E3.fieldText}"/>"</c:if>
						 <c:if test="${empty E3.fieldText}">value="<c:out value="${user.deptName}"/>"</c:if> 
						 style=" border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E3.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" rowspan="2" align="center">变更内容：</td>
    <td colspan="84" width="84%">变更类型：
    	<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="1" 
		  			<c:if test="${E4.fieldText == 1 || empty E4.fieldText}">checked</c:if> />增加&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="2" 
		  			<c:if test="${E4.fieldText == 2}">checked</c:if> />修改&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="3" 
		  			<c:if test="${E4.fieldText == 3}">checked</c:if> />删除&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E4.fieldText == 1}">checked</c:if> />增加&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E4.fieldText == 2}">checked</c:if> />修改&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E4.fieldText == 3}">checked</c:if> />删除&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>变更细节：</td>
			</tr>
			<tr>
				<td height="100" >
					<c:choose>
						<c:when test="${E5.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${E5.count}"/>].fieldText"><c:out value="${E5.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E5.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" rowspan="2" align="center">部门经理:</td>
    <td colspan="84" width="84%">是否同意：
    	<c:choose>
			<c:when test="${EE1.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${EE1.count}"/>].fieldText" value="1" 
		  			<c:if test="${EE1.fieldText == 1 || empty EE1.fieldText}">checked</c:if> />同意&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${EE1.count}"/>].fieldText" value="2" 
		  			<c:if test="${EE1.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${EE1.fieldText == 1}">checked</c:if> />同意&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${EE1.fieldText == 2}">checked</c:if> />不同意&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>回复内容：</td>
			</tr>
			<tr>
				<td height="70" >
					<c:choose>
						<c:when test="${EE2.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${EE2.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${EE2.count}"/>].fieldText"><c:out value="${EE2.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${EE2.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
    
    
    
  <tr>
    <td colspan="16" width="16%" align="center">开发部经理:</td>
    <td colspan="84" width="84%" height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>回复内容：</td>
			</tr>
			<tr>
				<td height="70" >
					<c:choose>
						<c:when test="${EE4.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${EE4.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${EE4.count}"/>].fieldText"><c:out value="${EE4.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${EE4.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
  
  
  <tr>
    <td colspan="16" width="16%" rowspan="2" align="center">责任人回复:</td>
    <td colspan="84" width="84%">解决方案：
    	<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="1" 
		  			<c:if test="${E6.fieldText == 1 || empty E6.fieldText}">checked</c:if> />已修改完毕&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="2" 
		  			<c:if test="${E6.fieldText == 2}">checked</c:if> />给出解决建议&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E6.fieldText == 1}">checked</c:if> />已修改完毕&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E6.fieldText == 2}">checked</c:if> />给出解决建议&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>回复内容：</td>
			</tr>
			<tr>
				<td height="70" >
					<c:choose>
						<c:when test="${E7.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${E7.count}"/>].fieldText"><c:out value="${E7.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E7.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
  
  
  
  
  
  
  <tr>
    <td colspan="16" width="16%" rowspan="2" align="center">申请人满意度:</td>
    <td colspan="84" width="84%">满意分数：
		<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<select name="fieldContents[<c:out value="${E8.count}"/>].fieldText">
					<option value="10" <c:if test="${E8.fieldText == 10}">selected</c:if>>10分</option>
					<option value="9" <c:if test="${E8.fieldText == 9}">selected</c:if>>9分</option>
					<option value="8" <c:if test="${E8.fieldText == 8}">selected</c:if>>8分</option>
					<option value="7" <c:if test="${E8.fieldText == 7}">selected</c:if>>7分</option>
					<option value="6" <c:if test="${E8.fieldText == 6}">selected</c:if>>6分</option>
					<option value="5" <c:if test="${E8.fieldText == 5}">selected</c:if>>5分</option>
				</select>
			</c:when>
			<c:otherwise>
				<select disabled="disabled">
					<option value="10" <c:if test="${E8.fieldText == 10}">selected</c:if>>10分</option>
					<option value="9" <c:if test="${E8.fieldText == 9}">selected</c:if>>9分</option>
					<option value="8" <c:if test="${E8.fieldText == 8}">selected</c:if>>8分</option>
					<option value="7" <c:if test="${E8.fieldText == 7}">selected</c:if>>7分</option>
					<option value="6" <c:if test="${E8.fieldText == 6}">selected</c:if>>6分</option>
					<option value="5" <c:if test="${E8.fieldText == 5}">selected</c:if>>5分</option>
				</select>
			</c:otherwise>
		</c:choose>(不满意请填写回访内容)
	</td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="60">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>回访内容：</td>
			</tr>
			<tr>
				<td height="70" >
					<c:choose>
						<c:when test="${E9.isEdit == '1'}">
							<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E9.count}"/>].fieldText" 
								name="fieldContents[<c:out value="${E9.count}"/>].fieldText"><c:out value="${E9.fieldText}"/></textarea>
						</c:when>
						<c:otherwise>
							<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E9.fieldText}"/></textarea>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</td>
  </tr>
</table>