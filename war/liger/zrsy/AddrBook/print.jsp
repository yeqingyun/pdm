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

<script src="./include/js/zrsy/addrBook.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar2.shtml",
			{parm:'AddrBook'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#bookId").length > 0)
		$("#bookId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerTextBox();
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#empId").length > 0)
		$("#empId").ligerTextBox();
	if ($("#phone").length > 0)
		$("#phone").ligerTextBox();
	if ($("#extPhone").length > 0)
		$("#extPhone").ligerTextBox();
	if ($("#mobile").length > 0)
		$("#mobile").ligerTextBox();
	if ($("#mailAddr").length > 0)
		$("#mailAddr").ligerTextBox();
	if ($("#addrName").length > 0)
		$("#addrName").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});

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
			<td height="24" width="90" align="center">地址薄ID：</td><td><input type="text" id="bookId" name="addrBook.bookId" size="30" validate="{required:true}" value="<c:out value="${addrBook.bookId}"/>"/></td>
			<td height="24" width="90" align="center">公司ID：</td><td><input type="text" id="comId" name="addrBook.comId" size="30" validate="{required:true}" value="<c:out value="${addrBook.comId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">部门ID：</td><td><input type="text" id="deptId" name="addrBook.deptId" size="30" validate="{required:true}" value="<c:out value="${addrBook.deptId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="userId" name="addrBook.userId" size="30" validate="{required:true}" value="<c:out value="${addrBook.userId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="empId" name="addrBook.empId" size="30" validate="{required:true}" value="<c:out value="${addrBook.empId}"/>"/></td>
			<td height="24" width="90" align="center">电话：</td><td><input type="text" id="phone" name="addrBook.phone" size="30" validate="{required:true}" value="<c:out value="${addrBook.phone}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">分机：</td><td><input type="text" id="extPhone" name="addrBook.extPhone" size="30" validate="{required:true}" value="<c:out value="${addrBook.extPhone}"/>"/></td>
			<td height="24" width="90" align="center">手机：</td><td><input type="text" id="mobile" name="addrBook.mobile" size="30" validate="{required:true}" value="<c:out value="${addrBook.mobile}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">邮件地址：</td><td><input type="text" id="mailAddr" name="addrBook.mailAddr" size="30" validate="{required:true}" value="<c:out value="${addrBook.mailAddr}"/>"/></td>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="addrName" name="addrBook.addrName" size="30" validate="{required:true}" value="<c:out value="${addrBook.addrName}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="addrBook.remark" size="30" validate="{required:true}" value="<c:out value="${addrBook.remark}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="addrBook.status" size="30" validate="{required:true}" value="<c:out value="${addrBook.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="addrBook.createBy" size="30" validate="{required:true}" value="<c:out value="${addrBook.createBy}"/>"/></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="addrBook.createDate" size="30" validate="{required:true}" value="<c:out value="${addrBook.createDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="addrBook.lastUpd" size="30" validate="{required:true}" value="<c:out value="${addrBook.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="addrBook.lastDate" size="30" validate="{required:true}" value="<c:out value="${addrBook.lastDate}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>