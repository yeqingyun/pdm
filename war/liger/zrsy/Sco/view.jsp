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

<script src="./include/js/zrsy/sco.js" type="text/javascript"></script> 
<script type="text/javascript">

$(function () {
	$.post("ligerToolBar2.shtml",
			{parm:'url'},
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
<input type="hidden" id="scoId" name="sco.scoId" size="30"  value="<c:out value="${sco.scoId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">类型：</td><td><input type="text" id="scope" name="sco.scope" size="30" validate="{required:true}" value="<c:out value="${sco.scope}"/>"/></td><td align="left"></td>
		</tr><tr>
		<td height="24" width="90" align="center">范围名称：</td><td><input type="text" id="scoNm" name="sco.scoNm" size="30" validate="{required:true}" value="<c:out value="${sco.scoNm}"/>"/></td><td align="left"></td>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="sco.status" style="width:135px">
					<option value="1"<c:if test="${sco.status==1}">selected</c:if>>有效</option>
					<option value="0"<c:if test="${sco.status==0}">selected</c:if>>无效</option>
				</select></td><td align="left"></td>
		</tr>
		<tr>
			<td colspan="5">
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<c:set var="i" value="0"/>
					<c:forEach items="${coms}" var="com">
						<tr>
							<td width="10%" height="24"><input type="checkbox" id="com<c:out value="${i}"/>" name="_coms" <c:out value="${com.checked}"/> value="<c:out value="${com.comId}"/>"/><c:out value="${com.comNm}"/></br>
								<table align="center" width="100%" cellpadding="4" cellspacing="4">
									<c:set var="ii" value="0"/>
									<c:forEach items="${com.depts}" var="dept">
									<tr>
										<td>&nbsp;&nbsp;&nbsp;<input type="checkbox" id="dept<c:out value="${ii}"/>" name="_depts<c:out value="${i}"/>" <c:out value="${dept.checked}"/> value="<c:out value="${dept.deptId}"/>"/><c:out value="${dept.deptNm}"/>
											</br>
												<c:set var="iii" value="0"/>
												<c:forEach items="${dept.usrs}" var="usr">
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="usr<c:out value="${iii}"/>" name="_usrs<c:out value="${ii}"/>" <c:out value="${usr.checked}"/> value="<c:out value="${usr.id}"/>"/><c:out value="${usr.usrName}"/>
												<c:set var="iii" value="${iii+1}"/>
												</c:forEach>
										</td>
									</tr>
									<c:set var="ii" value="${ii+1}"/>
									</c:forEach>
								</table>
							</td>
						</tr>
					<c:set var="i" value="${i+1}"/>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
	
</form>

</div>
</body>
</html>