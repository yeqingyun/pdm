<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrsy/usr.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Usr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	check();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
<input type="hidden" id="id" name="usr.id" size="30" value="<c:out value="${usr.id}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">公司：</td>
			<td>
				<select id="comId" name="usr.comId" style="width:135px">
					<c:forEach items="${coms}" var="com">
						<option value="<c:out value="${com.comId}"/>"<c:if test="${com.comId==usr.comId}">selected</c:if>><c:out value="${com.comNm}"/></option>
					</c:forEach>
				</select>
			</td><td align="left"></td>
			<td height="24" width="90" align="center">部门：</td>
			<td>
				<select id="deptId" name="usr.deptId" style="width:135px">
					<c:forEach items="${depts}" var="dept">
						<option value="<c:out value="${dept.deptId}"/>"<c:if test="${dept.deptId==usr.deptId}">selected</c:if>><c:out value="${dept.deptNm}"/></option>
					</c:forEach>
				</select>
			</td><td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户号：</td><td><input type="text" id="usrNo" name="usr.usrNo" size="30" value="<c:out value="${usr.usrNo}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">状态：</td><td>
				<select id="status" name="usr.status" style="width:135px">
					<option value="1"<c:if test="${usr.status==1}">selected</c:if>>有效</option>
					<option value="0"<c:if test="${usr.status==0}">selected</c:if>>无效</option>
				</select>
			</td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户名称：</td><td><input type="text" id="usrName" name="usr.usrName" size="30" value="<c:out value="${usr.usrName}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">电子邮箱：</td><td><input type="text" id="email" name="usr.email" size="30" value="<c:out value="${usr.email}"/>" ltype="text" validate="{required:true,email:true}"/></td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">登录账号：</td><td><input type="text" id="login" name="usr.login" size="30" value="<c:out value="${usr.login}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">密码：</td><td><input type="password" id="pwd" name="usr.pwd" size="30" value="<c:out value="${usr.pwd}"/>" ltype="text" validate="{required:true}"/></td>
			<td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="usr.remark" size="30" value="<c:out value="${usr.remark}"/>"/></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td colspan="5">
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr bgcolor="#EFF0F2">
						<td width="10%" height="24" colspan=8>用户组</td>
					</tr>
					<tr>
						<c:set var="ii" value="0"/>
						<c:forEach items="${gps}" var="gp">
							<td width="10%" height="24"><input type="checkbox" id="gp<c:out value="${ii}"/>" name="_gps" <c:out value="${gp.checked}"/> value="<c:out value="${gp.gpId}"/>"/><c:out value="${gp.gpName}"/></td>
							<c:set var="ii" value="${ii+1}"/>
							<c:if test="${ii%8==0}"></tr><tr></c:if>
						</c:forEach>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr bgcolor="#EFF0F2">
						<td width="10%" height="24">授权范围</td>
					</tr>
					<tr>
						<c:set var="ii" value="0"/>
						<c:forEach items="${scos}" var="sco">
								<td width="10%" height="24"><input type="checkbox" id="sco<c:out value="${ii}"/>" name="_scos" <c:out value="${sco.checked}"/> value="<c:out value="${sco.scoId}"/>"/><c:out value="${sco.scoNm}"/></td>
							<c:set var="ii" value="${ii+1}"/>
						</c:forEach>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	
</form>

</div>

</body>
</html>