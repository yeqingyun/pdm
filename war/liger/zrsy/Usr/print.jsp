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

<script src="./include/js/zrsy/usr.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'Usr'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#id").length > 0)
		$("#id").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerComboBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#login").length > 0)
		$("#login").ligerTextBox();
	if ($("#pwd").length > 0)
		$("#pwd").ligerTextBox();
	if ($("#usrNo").length > 0)
		$("#usrNo").ligerTextBox();
	if ($("#usrName").length > 0)
		$("#usrName").ligerTextBox();
	if ($("#email").length > 0)
		$("#email").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();

});
</script>
</head>
<body>

<div id="layout1">
	<div position="center">

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">Id：</td><td><input type="text" id="id" name="usr.id" size="30" value="<c:out value="${usr.id}"/>"/></td>
			<td height="24" width="90" align="center">公司：</td>
			<td>
				<select id="comId" name="usr.comId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${coms}" var="com">
						<option value="<c:out value="${com.comId}"/>"><c:out value="${com.comNm}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">部门：</td>
			<td>
				<select id="deptId" name="usr.deptId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${depts}" var="dept">
						<option value="<c:out value="${dept.deptId}"/>"><c:out value="${dept.deptNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="usr.status" size="30" value="<c:out value="${usr.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="usr.createBy" size="30" value="<c:out value="${usr.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="usr.lastUpd" size="30" value="<c:out value="${usr.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="usr.createDate" size="30" value="<c:out value="${usr.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="usr.lastDate" size="30" value="<c:out value="${usr.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">登录账号：</td><td><input type="text" id="login" name="usr.login" size="30" value="<c:out value="${usr.login}"/>"/></td>
			<td height="24" width="90" align="center">密码：</td><td><input type="text" id="pwd" name="usr.pwd" size="30" value="<c:out value="${usr.pwd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户号：</td><td><input type="text" id="usrNo" name="usr.usrNo" size="30" value="<c:out value="${usr.usrNo}"/>"/></td>
			<td height="24" width="90" align="center">用户名称：</td><td><input type="text" id="usrName" name="usr.usrName" size="30" value="<c:out value="${usr.usrName}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">电子邮箱：</td><td><input type="text" id="email" name="usr.email" size="30" value="<c:out value="${usr.email}"/>"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="usr.remark" size="30" value="<c:out value="${usr.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>