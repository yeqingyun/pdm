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

<script src="./include/js/zrsy/addrBook.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'AddrBook'},
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
<input type="hidden" id="bookId" name="addrBook.bookId" size="30" value="<c:out value="${addrBook.bookId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">公司：</td>
			<td>
				<select id="comId" name="addrBook.comId" style="width:135px">
					<c:forEach items="${coms}" var="com">
						<option value="<c:out value="${com.comId}"/>"<c:if test="${com.comId==addrBook.comId}">selected</c:if>><c:out value="${com.comNm}"/></option>
					</c:forEach>
				</select>
			</td><td align="left"></td>
			<td height="24" width="90" align="center">部门：</td>
			<td>
				<select id="deptId" name="addrBook.deptId" style="width:135px">
					<c:forEach items="${depts}" var="dept">
						<option value="<c:out value="${dept.deptId}"/>"<c:if test="${dept.deptId==addrBook.deptId}">selected</c:if>><c:out value="${dept.deptNm}"/></option>
					</c:forEach>
				</select>
			</td><td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户：</td><td>
			<select id="userId" name="addrBook.userId" style="width:135px">
					<c:forEach items="${usrs}" var="usr">
						<option value="<c:out value="${usr.id}"/>"<c:if test="${usr.id==addrBook.userId}">selected</c:if>><c:out value="${usr.usrName}"/></option>
					</c:forEach>
				</select>
			</td><td align="left"></td>
			<td height="24" width="90" align="center">电话：</td><td><input type="text" id="phone" name="addrBook.phone" size="30" value="<c:out value="${addrBook.phone}"/>"/></td><td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">分机：</td><td><input type="text" id="extPhone" name="addrBook.extPhone" size="30" value="<c:out value="${addrBook.extPhone}"/>"/></td><td align="left"></td>
			<td height="24" width="90" align="center">手机：</td><td><input type="text" id="mobile" name="addrBook.mobile" size="30" validate="{required:true}" value="<c:out value="${addrBook.mobile}"/>"/></td><td align="left"></td>
		</tr><tr>
			<td height="24" width="90" align="center">邮件地址：</td><td><input type="text" id="mailAddr" name="addrBook.mailAddr" size="30" validate="{required:true,email:true}" value="<c:out value="${addrBook.mailAddr}"/>"/></td><td align="left"></td>
			<td height="24" width="90" align="center">邮件名称：</td><td><input type="text" id="addrName" name="addrBook.addrName" size="30" validate="{required:true}" value="<c:out value="${addrBook.addrName}"/>"/></td><td align="left"></td>
		</tr>
	</table>
</form>

</div>

</body>
</html>