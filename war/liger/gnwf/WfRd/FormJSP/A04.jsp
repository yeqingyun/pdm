<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<%@ include file="../IncudeExtendField.jsp"%>  
<p  align="center" style='font-size:14.0pt;font-family:  宋体;'><strong>深圳市金立通信设备有限公司</strong><br/>
<strong>SAP系统帐号申请表</strong></p>

<table width="720" style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#0066EE cellpadding=3 align=center>
  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">用户信息(Employee Information)</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>中文姓名</td>
	<td colspan="24" width="24%" >
		<c:choose>
			<c:when test="${E1.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E1.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E1.count}"/>].fieldText" value="<c:out value="${E1.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E1.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">姓名全拼</td>
    <td colspan="15" width="15%">
		<c:choose>
			<c:when test="${E2.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E2.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E2.count}"/>].fieldText" value="<c:out value="${E2.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E2.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td colspan="15" width="15%" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>联系方式</td>
    <td colspan="15" width="15%">
		<c:choose>
			<c:when test="${E3.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E3.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E3.count}"/>].fieldText" value="<c:out value="${E3.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E3.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>公司</td>
	<td colspan="24" width="24%">
		<c:choose>
			<c:when test="${E4.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E4.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E4.count}"/>].fieldText" value="<c:out value="${E4.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E4.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>岗位</td>
    <td colspan="45" width="45%">
		<c:choose>
			<c:when test="${E5.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E5.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E5.count}"/>].fieldText" value="<c:out value="${E5.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E5.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>邮箱地址</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E6.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E6.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E6.count}"/>].fieldText" value="<c:out value="${E6.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E6.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td> 
  </tr>
    <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0"><font color="#FF0000">*</font>所属部门</td>
	<td colspan="24" width="24%">
		<c:choose>
			<c:when test="${E7.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E7.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E7.count}"/>].fieldText" value="<c:out value="${E7.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E7.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
	<td colspan="15" width="15%" align="center" bgcolor="#F0F0F0">成本中心编号</td>
    <td colspan="45" width="45%">
		<c:choose>
			<c:when test="${E8.isEdit == '1'}">
				<input type="Text" size="20" id="fieldContents[<c:out value="${E8.count}"/>].fieldText" 
					 name="fieldContents[<c:out value="${E8.count}"/>].fieldText" value="<c:out value="${E8.fieldText}"/>" >
			</c:when>
			<c:otherwise>
				<c:out value="${E8.fieldText}"/>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">帐号信息(Technical Information)</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">申请类型</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E9.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="1" 
		  			<c:if test="${E9.fieldText == 1 || empty E9.fieldText}">checked</c:if> />新增账号及权限&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E9.count}"/>].fieldText" value="2" 
		  			<c:if test="${E9.fieldText == 2}">checked</c:if> />仅新增账号&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E9.fieldText == 1}">checked</c:if> />新增账号及权限&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E9.fieldText == 2}">checked</c:if> />仅新增账号&nbsp;
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="60" align="center" bgcolor="#F0F0F0">帐号类型</td>
	<td colspan="84" width="84%">
    	<c:choose>
			<c:when test="${E10.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="1" 
		  			<c:if test="${E10.fieldText == 1 || empty E10.fieldText}">checked</c:if> />会话&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E10.count}"/>].fieldText" value="2" 
		  			<c:if test="${E10.fieldText == 2}">checked</c:if> />通讯(后台账号:即只用于系统数据连接,不能前台登录的账号，IT专用)&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E10.fieldText == 1}">checked</c:if> />会话&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E10.fieldText == 2}">checked</c:if> />通讯(后台账号:即只用于系统数据连接,不能前台登录的账号，IT专用)&nbsp;
			</c:otherwise>
		</c:choose>
    </td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">客户端</td>
	<td colspan="84" width="84%">
	    <c:choose>
			<c:when test="${E12.isEdit == '1'}">
				<input type="radio" name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="1" 
		  			<c:if test="${E11.fieldText == 1 || empty E11.fieldText}">checked</c:if> />PRD800&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="2" 
		  			<c:if test="${E11.fieldText == 2}">checked</c:if> />QAS800&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="3" 
		  			<c:if test="${E11.fieldText == 3}">checked</c:if> />GDQ500&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="4" 
		  			<c:if test="${E11.fieldText == 4}">checked</c:if> />GDQ200&nbsp;
		  		<input type="radio" name="fieldContents[<c:out value="${E11.count}"/>].fieldText" value="5" 
		  			<c:if test="${E11.fieldText == 5}">checked</c:if> />GDQ100&nbsp;
			</c:when>
			<c:otherwise>
				<input type="radio" disabled="disabled" <c:if test="${E11.fieldText == 1}">checked</c:if> />PRD800&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E11.fieldText == 2}">checked</c:if> />QAS800&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E11.fieldText == 3}">checked</c:if> />GDQ500&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E11.fieldText == 4}">checked</c:if> />GDQ200&nbsp;
		  		<input type="radio" disabled="disabled" <c:if test="${E11.fieldText == 5}">checked</c:if> />GDQ100&nbsp;
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
  
  
  
  
  

  
  
  
   <tr>
    <td colspan="16" width="16%" rowspan="4" align="center" bgcolor="#F0F0F0">访问数据范围</td>
    <td colspan="84" width="84%" bgcolor="#F0F0F0">法人范围：</td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="50">
	<table width="100%">
	 <tr>
		<td>
			<c:choose>
				<c:when test="${E12.isEdit == '1'}">
					<input name="fieldContents[<c:out value="${E12.count}"/>].fieldText"  type="checkbox" value="1" 
						<c:if test="${E12.fieldText == 1}">checked</c:if> />深圳市金立通信设备有限公司(1000)
				</c:when>
				<c:otherwise>
					<input type="checkbox" disabled="disabled" <c:if test="${E12.fieldText == 1}">checked</c:if> />深圳市金立通信设备有限公司(1000)
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${E13.isEdit == '1'}">
					<input name="fieldContents[<c:out value="${E13.count}"/>].fieldText"  type="checkbox" value="1" 
						<c:if test="${E13.fieldText == 1}">checked</c:if> />东莞市金铭电子有限公司(2000)
				</c:when>
				<c:otherwise>
					<input type="checkbox" disabled="disabled" <c:if test="${E13.fieldText == 1}">checked</c:if> />东莞市金铭电子有限公司(2000)
				</c:otherwise>
			</c:choose>
		</td>
	 </tr>
	 <tr>
	 	<td>
		<c:choose>
			<c:when test="${E14.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E14.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E14.fieldText == 1}">checked</c:if> />东莞市金卓电子有限公司(3000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E14.fieldText == 1}">checked</c:if> />东莞市金卓电子有限公司(3000)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E15.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E15.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E15.fieldText == 1}">checked</c:if> />香港金立(8000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E15.fieldText == 1}">checked</c:if> />香港金立(8000)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E16.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E16.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E16.fieldText == 1}">checked</c:if> />香港金泰(8800)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E16.fieldText == 1}">checked</c:if> />香港金泰(8800)
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	</table>
	</td>
  </tr>
  <tr>
    <td colspan="84" width="84%" bgcolor="#F0F0F0">工厂范围</td>
  </tr>
  <tr>
    <td colspan="84" width="84%"  height="70">
	<table width="100%">
	 <tr>
		<td>
		<c:choose>
			<c:when test="${E17.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E17.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E17.fieldText == 1}">checked</c:if> />金立工厂(1000/1000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E17.fieldText == 1}">checked</c:if> />金立工厂(1000/1000)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E18.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E18.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E18.fieldText == 1}">checked</c:if> />金铭工厂(2000/2000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E18.fieldText == 1}">checked</c:if> />金铭工厂(2000/2000)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E19.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E19.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E19.fieldText == 1}">checked</c:if> />金卓工厂(3000/3000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E19.fieldText == 1}">checked</c:if> />金卓工厂(3000/3000)
			</c:otherwise>
		</c:choose>
		</td>
	 </tr>
	 <tr>
	 	<td>
		<c:choose>
			<c:when test="${E20.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E20.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E20.fieldText == 1}">checked</c:if> />金立无价值工厂(1999/1000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E20.fieldText == 1}">checked</c:if> />金立无价值工厂(1999/1000)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E21.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E21.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E21.fieldText == 1}">checked</c:if> />金铭无价值工厂(2999/2000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E21.fieldText == 1}">checked</c:if> />金铭无价值工厂(2999/2000)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E22.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E22.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E22.fieldText == 1}">checked</c:if> />金卓无价值工厂(3999/3000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E22.fieldText == 1}">checked</c:if> />金卓无价值工厂(3999/3000)
			</c:otherwise>
		</c:choose>
		</td>
	 </tr>
	 <tr>
	 	<td>
		<c:choose>
			<c:when test="${E23.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E23.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E23.fieldText == 1}">checked</c:if> />金卓保税工厂(3100)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E23.fieldText == 1}">checked</c:if> />金卓保税工厂(3100)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E24.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E24.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E24.fieldText == 1}">checked</c:if> />印刷事业部(2100)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E24.fieldText == 1}">checked</c:if> />印刷事业部(2100)
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${E25.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E25.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E25.fieldText == 1}">checked</c:if> />香港金立工厂(8000)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E25.fieldText == 1}">checked</c:if> />香港金立工厂(8000)
			</c:otherwise>
		</c:choose>
	</td>
	 </tr>
	 <tr>
	 	<td>
		<c:choose>
			<c:when test="${E26.isEdit == '1'}">
				<input name="fieldContents[<c:out value="${E26.count}"/>].fieldText"  type="checkbox" value="1" 
					<c:if test="${E26.fieldText == 1}">checked</c:if> />香港金泰工厂(8100)
			</c:when>
			<c:otherwise>
				<input type="checkbox" disabled="disabled" <c:if test="${E26.fieldText == 1}">checked</c:if> />香港金泰工厂(8100)
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	</table>
</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="70" align="center" bgcolor="#F0F0F0">账户申请描述</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E27.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E27.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E27.count}"/>].fieldText"><c:out value="${E27.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E27.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>





  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">帐号审核</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">部门经理审核</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
		  <td colspan="84" width="84%">
		  是否同意：
			<c:choose>
				<c:when test="${E28.isEdit == '1'}">
					<input type="radio" name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);"
			  			<c:if test="${E28.fieldText == 1 || empty E28.fieldText}">checked</c:if> />同意&nbsp;
			  		<input type="radio" name="fieldContents[<c:out value="${E28.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);"
			  			<c:if test="${E28.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" <c:if test="${E28.fieldText == 1}">checked</c:if> />同意&nbsp;
			  		<input type="radio" disabled="disabled" <c:if test="${E28.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:otherwise>
			</c:choose>
		  </td>
		</tr>
		<tr>
			<td height="48">
			<c:choose>
				<c:when test="${E29.isEdit == '1'}">
					<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E29.count}"/>].fieldText" 
						name="fieldContents[<c:out value="${E29.count}"/>].fieldText"><c:out value="${E29.fieldText}"/></textarea>
				</c:when>
				<c:otherwise>
					<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E29.fieldText}"/></textarea>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E30.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E30.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${E30.count}"/>].fieldText" 
						 <c:if test="${!empty E30.fieldText}">value="<c:out value="${E30.fieldText}"/>"</c:if>
						 <c:if test="${empty E30.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E30.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	</td>
  </tr>
  
  
  
  <!-- 
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">内部顾问</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">
			是否同意：
			<c:choose>
				<c:when test="${E31.isEdit == '1'}">
					<input type="radio" name="fieldContents[<c:out value="${E31.count}"/>].fieldText" value="1" 
			  			<c:if test="${E31.fieldText == 1 || empty E31.fieldText}">checked</c:if> />同意&nbsp;
			  		<input type="radio" name="fieldContents[<c:out value="${E31.count}"/>].fieldText" value="2" 
			  			<c:if test="${E31.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" <c:if test="${E31.fieldText == 1}">checked</c:if> />同意&nbsp;
			  		<input type="radio" disabled="disabled" <c:if test="${E31.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="48">
				<c:choose>
					<c:when test="${E32.isEdit == '1'}">
						<textarea rows="4" cols="70" id="fieldContents[<c:out value="${E32.count}"/>].fieldText" 
							name="fieldContents[<c:out value="${E32.count}"/>].fieldText"><c:out value="${E32.fieldText}"/></textarea>
					</c:when>
					<c:otherwise>
						<c:out value="${E32.fieldText}"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E33.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E33.count}"/>].fieldText" 
						 name="fieldContents[<c:out value="${E33.count}"/>].fieldText" value="<c:out value="${E33.fieldText}"/>" 
						 style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E33.fieldText}"/>" disabled="disabled"
						style="background-color:#F0F0F0; border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	</td>
  </tr>
  -->
  
  
  
 <!-- 
	多财务主管审核,并发任务
	根据选中字段，显示表单
	默认-只显示金立
 -->

<c:if test="${(E12.fieldText==1 || E15.fieldText==1) || (empty E13.fieldText && empty E14.fieldText && empty E16.fieldText)}">
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">金立财务<br>主管审核</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">
			是否同意：
			<c:choose>
				<c:when test="${E34.isEdit == '1'}">
					<input type="radio" name="fieldContents[<c:out value="${E34.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
			  			<c:if test="${E34.fieldText == 1  || empty E34.fieldText}">checked</c:if> />同意&nbsp;
			  		<input type="radio" name="fieldContents[<c:out value="${E34.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
			  			<c:if test="${E34.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" <c:if test="${E34.fieldText == 1}">checked</c:if> />同意&nbsp;
			  		<input type="radio" disabled="disabled" <c:if test="${E34.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="48">
				<c:choose>
					<c:when test="${E35.isEdit == '1'}">
						<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E35.count}"/>].fieldText" 
							name="fieldContents[<c:out value="${E35.count}"/>].fieldText"><c:out value="${E35.fieldText}"/></textarea>
					</c:when>
					<c:otherwise>
						<textarea "readonly" class="textarea-hidden"><c:out value="${E35.fieldText}"/></textarea>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E36.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E36.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${E36.count}"/>].fieldText" 
						 <c:if test="${!empty E36.fieldText}">value="<c:out value="${E36.fieldText}"/>"</c:if>
						 <c:if test="${empty E36.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E36.fieldText}"/>" disabled="disabled" 
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	</td>
  </tr>
</c:if>



<c:if test="${E13.fieldText == 1}">
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">金铭财务<br>主管审核</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">
			是否同意：
			<c:choose>
				<c:when test="${EE1.isEdit == '1'}">
					<input type="radio" name="fieldContents[<c:out value="${EE1.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
			  			<c:if test="${EE1.fieldText == 1  || empty EE1.fieldText}">checked</c:if> />同意&nbsp;
			  		<input type="radio" name="fieldContents[<c:out value="${EE1.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
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
			<td height="48">
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
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${EE3.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${EE3.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${EE3.count}"/>].fieldText" 
						 <c:if test="${!empty EE3.fieldText}">value="<c:out value="${EE3.fieldText}"/>"</c:if>
						 <c:if test="${empty EE3.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${EE3.fieldText}"/>" disabled="disabled" 
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	</td>
  </tr>
</c:if>



<c:if test="${E14.fieldText == 1}">
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">金卓财务<br>主管审核</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">
			是否同意：
			<c:choose>
				<c:when test="${EE4.isEdit == '1'}">
					<input type="radio" name="fieldContents[<c:out value="${EE4.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
			  			<c:if test="${EE4.fieldText == 1  || empty EE4.fieldText}">checked</c:if> />同意&nbsp;
			  		<input type="radio" name="fieldContents[<c:out value="${EE4.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
			  			<c:if test="${EE4.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" <c:if test="${EE4.fieldText == 1}">checked</c:if> />同意&nbsp;
			  		<input type="radio" disabled="disabled" <c:if test="${EE4.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="48">
				<c:choose>
					<c:when test="${EE5.isEdit == '1'}">
						<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${EE5.count}"/>].fieldText" 
							name="fieldContents[<c:out value="${EE5.count}"/>].fieldText"><c:out value="${EE5.fieldText}"/></textarea>
					</c:when>
					<c:otherwise>
						<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${EE5.fieldText}"/></textarea>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${EE6.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${EE6.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${EE6.count}"/>].fieldText" 
						 <c:if test="${!empty EE6.fieldText}">value="<c:out value="${EE6.fieldText}"/>"</c:if>
						 <c:if test="${empty EE6.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${EE6.fieldText}"/>" disabled="disabled" 
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	</td>
  </tr>
</c:if>




<c:if test="${E16.fieldText == 1}">
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">金泰财务<br>主管审核</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">
			是否同意：
			<c:choose>
				<c:when test="${EE7.isEdit == '1'}">
					<input type="radio" name="fieldContents[<c:out value="${EE7.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
			  			<c:if test="${EE7.fieldText == 1  || empty EE7.fieldText}">checked</c:if> />同意&nbsp;
			  		<input type="radio" name="fieldContents[<c:out value="${EE7.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
			  			<c:if test="${EE7.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" <c:if test="${EE7.fieldText == 1}">checked</c:if> />同意&nbsp;
			  		<input type="radio" disabled="disabled" <c:if test="${EE7.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="48">
				<c:choose>
					<c:when test="${EE8.isEdit == '1'}">
						<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${EE8.count}"/>].fieldText" 
							name="fieldContents[<c:out value="${EE8.count}"/>].fieldText"><c:out value="${EE8.fieldText}"/></textarea>
					</c:when>
					<c:otherwise>
						<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${EE8.fieldText}"/></textarea>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${EE9.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${EE9.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${EE9.count}"/>].fieldText" 
						 <c:if test="${!empty EE9.fieldText}">value="<c:out value="${EE9.fieldText}"/>"</c:if>
						 <c:if test="${empty EE9.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${EE9.fieldText}"/>" disabled="disabled" 
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	</td>
  </tr>
</c:if>
  
<!-- ------- -->

  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">SAP负责人审核</td>
	<td colspan="84" width="84%">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="84" width="84%">
			是否同意：
			<c:choose>
				<c:when test="${E40.isEdit == '1'}">
					<input type="radio" name="fieldContents[<c:out value="${E40.count}"/>].fieldText" value="1" onclick="chgNextBtn(true);" 
			  			<c:if test="${E40.fieldText == 1 || empty E40.fieldText}">checked</c:if> />同意&nbsp;
			  		<input type="radio" name="fieldContents[<c:out value="${E40.count}"/>].fieldText" value="2" onclick="chgNextBtn(false);" 
			  			<c:if test="${E40.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" <c:if test="${E40.fieldText == 1}">checked</c:if> />同意&nbsp;
			  		<input type="radio" disabled="disabled" <c:if test="${E40.fieldText == 2}">checked</c:if> />不同意&nbsp;
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<td height="48">
				<c:choose>
					<c:when test="${E41.isEdit == '1'}">
						<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E41.count}"/>].fieldText" 
							name="fieldContents[<c:out value="${E41.count}"/>].fieldText"><c:out value="${E41.fieldText}"/></textarea>
					</c:when>
					<c:otherwise>
						<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E41.fieldText}"/></textarea>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td align="right">
			签&nbsp;名：
			<c:choose>
				<c:when test="${E42.isEdit == '1'}">
					<input type="Text" size="20" id="fieldContents[<c:out value="${E42.count}"/>].fieldText" readonly="readonly" 
						 name="fieldContents[<c:out value="${E42.count}"/>].fieldText" 
						 <c:if test="${!empty E42.fieldText}">value="<c:out value="${E42.fieldText}"/>"</c:if>
						 <c:if test="${empty E42.fieldText}">value="<c:out value="${user.userName}"/>"</c:if> 
						 style="border:0px; background:#EFF0F2">
				</c:when>
				<c:otherwise>
					<input type="Text" size="20" value="<c:out value="${E42.fieldText}"/>" disabled="disabled"
						style=" border:1px; border-bottom-style: solid;border-top-style: none;border-left-style:none;border-right-style:none;">
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
		</table>
	</td>
  </tr>





  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">授权角色信息(由SAP顾问填写)</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="70" align="center" bgcolor="#F0F0F0">授权角色</td>
	<td colspan="84" width="84%">
		<c:choose>
			<c:when test="${E37.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E37.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E37.count}"/>].fieldText"><c:out value="${E37.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E37.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>



  <tr>
    <td colspan="100" width="100%" height="30" align="center" bgcolor="#F0F0F0">账号维护信息(由SAP维护工程师填写)</td>
  </tr>
  <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">账号编码</td>
	<td colspan="84" width="84%" >
		<c:choose>
			<c:when test="${E38.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E38.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E38.count}"/>].fieldText"><c:out value="${E38.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E38.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
    <tr>
    <td colspan="16" width="16%" height="30" align="center" bgcolor="#F0F0F0">维护工程师</td>
	<td colspan="84" width="84%" >
		<c:choose>
			<c:when test="${E39.isEdit == '1'}">
				<textarea  rows="4" cols="70" id="fieldContents[<c:out value="${E39.count}"/>].fieldText" 
					name="fieldContents[<c:out value="${E39.count}"/>].fieldText"><c:out value="${E39.fieldText}"/></textarea>
			</c:when>
			<c:otherwise>
				<textarea rows="4" cols="70" readonly="readonly" class="textarea-hidden"><c:out value="${E39.fieldText}"/></textarea>
			</c:otherwise>
		</c:choose>
	</td>
  </tr>
</table>