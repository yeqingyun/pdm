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

<script src="./include/js/zrsy/gp.js" type="text/javascript"></script>


<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Gp'},
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
<input type="hidden" id="gpId" name="gp.gpId" size="30" value="<c:out value="${gp.gpId}"/>"/>
<input type="hidden" id="syId" name="gp.syId" size="30" value="<c:out value="${syDef.syId}"/>"/>


	<table width="99%">
		<tr>
			<td height="24" width="90" align="center">组名称：</td><td><input type="hidden" id="gpName" name="gp.gpName" size="30" value="<c:out value="${gp.gpName}"/>"/><c:out value="${gp.gpName}"/></td>
			<td align="left"></td>
			<td height="24" width="90" align="center">说明：</td><td><input type="hidden" id="remark" name="gp.remark" size="30" value="<c:out value="${gp.remark}"/>"/><c:out value="${gp.remark}"/></td>
			<td align="left"></td>
		</tr>
		<tr>
			<td colspan="5">
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr>
						<td width="10%" height="24">系统</td>
						<td>

								<input type="checkbox" id="syDef0" name="_syDefs" <c:out value="${syDef.checked}"/> value="<c:out value="${syDef.syId}"/>"/><c:out value="${syDef.syName}"/>

						</td>
					</tr>
				</table>
				
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr bgcolor="#EFF0F2">
						<td width="10%" height="24">菜单</td>
						<td>子菜单</td>
					</tr>
					<c:set var="ii" value="0"/>
					<c:forEach items="${menus}" var="menu">
						<tr>
							<td width="10%" height="24"><input type="checkbox" id="menu<c:out value="${ii}"/>" name="_menus" <c:out value="${menu.checked}"/> value="<c:out value="${menu.id}"/>"/><c:out value="${menu.text}"/></td>
							<td>
								<c:forEach items="${menu.subMenus}" var="subMenu">
									<input type="checkbox" id="menu<c:out value="${ii}"/>" name="_menus" <c:out value="${subMenu.checked}"/> value="<c:out value="${subMenu.id}"/>"/><c:out value="${subMenu.text}"/>
									<c:set var="ii" value="${ii+1}"/>
									<c:forEach items="${subMenu.subMenus}" var="sub">
										<input type="checkbox" id="menu<c:out value="${ii}"/>" name="_menus" <c:out value="${sub.checked}"/> value="<c:out value="${sub.id}"/>"/><c:out value="${sub.text}"/>
										<c:set var="ii" value="${ii+1}"/>
									</c:forEach>
								</c:forEach>
							</td>
						</tr>
						<c:set var="ii" value="${ii+1}"/>
					</c:forEach>
				</table>

				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr bgcolor="#EFF0F2">
						<td width="10%" height="24">子菜单</td>
						<td>功能模块</td>
					</tr>
					
					<c:set var="yy" value="0"/>
					<c:forEach items="${subMenus}" var="subMenu">
						
						<tr>
							<td width="14%" height="24"><c:out value="${subMenu.text}"/></td>
							<td>
								<c:set var="y" value="0"/>
								<c:forEach items="${subMenu.nodes}" var="node">
									<input type="checkbox" id="node<c:out value="${yy}"/>" name="_nodes" <c:out value="${node.checked}"/> value="<c:out value="${node.id}"/>"/><c:out value="${node.text}"/>
									<c:set var="y" value="${y+1}"/>
									<c:if test="${y%10==0}"><br></c:if>
									<c:set var="yy" value="${yy+1}"/>
								</c:forEach>
							</td>
							<td></td>
						</tr>
						<c:forEach items="${subMenu.subMenus}" var="sub">
							<tr>
								<td width="14%" height="24">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${sub.text}"/></td>
								<td>
									<c:set var="y" value="0"/>
									<c:forEach items="${sub.nodes}" var="node">
										<input type="checkbox" id="node<c:out value="${yy}"/>" name="_nodes" <c:out value="${node.checked}"/> value="<c:out value="${node.id}"/>"/><c:out value="${node.text}"/>
										<c:set var="y" value="${y+1}"/>
										<c:if test="${y%10==0}"><br></c:if>
										<c:set var="yy" value="${yy+1}"/>
									</c:forEach>
								</td>
								<td></td>
							</tr>
						</c:forEach>
					</c:forEach>
				</table>
				
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr bgcolor="#EFF0F2">
						<td width="12%" height="24">功能模块</td>
						<td>功能授权</td>
					</tr>
					<c:set var="xx" value="0"/>
					<c:forEach items="${nodes}" var="node">
						<tr>
							<td width="10%" height="24" valign="top"><c:out value="${node.text}"/></td>
							<td>
								<c:set var="x" value="0"/>
								<c:forEach items="${node.btns}" var="btn">
									<input type="checkbox" id="btn<c:out value="${xx}"/>" name="_btns" <c:out value="${btn.checked}"/> value="<c:out value="${node.id}"/>,<c:out value="${btn.id}"/>"/><c:out value="${btn.text}"/>
									<c:set var="x" value="${x+1}"/>
									<c:if test="${x%15==0}"><br></c:if>
									
									<c:set var="xx" value="${xx+1}"/>
								</c:forEach><br>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</form>

</div>

</body>
</html>