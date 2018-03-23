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

<script src="./include/js/dept.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#deptId").length > 0)
		$("#deptId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerTextBox();
	if ($("#parent").length > 0)
		$("#parent").ligerTextBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
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
	if ($("#deptNo").length > 0)
		$("#deptNo").ligerTextBox();
	if ($("#deptNm").length > 0)
		$("#deptNm").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="deptId" name="dept.deptId" size="30" value="<c:out value="${dept.deptId}"/>"/></td>
			<td height="24" width="90" align="center">null：</td><td><input type="text" id="comId" name="dept.comId" size="30" value="<c:out value="${dept.comId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">上级部门：</td><td><input type="text" id="parent" name="dept.parent" size="30" value="<c:out value="${dept.parent}"/>"/></td>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="dept.leve" size="30" value="<c:out value="${dept.leve}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="dept.status" size="30" value="<c:out value="${dept.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="dept.createBy" size="30" value="<c:out value="${dept.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="dept.lastUpd" size="30" value="<c:out value="${dept.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="dept.createDate" size="30" value="<c:out value="${dept.createDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="dept.lastDate" size="30" value="<c:out value="${dept.lastDate}"/>"/></td>
			<td height="24" width="90" align="center">公司编号：</td><td><input type="text" id="deptNo" name="dept.deptNo" size="30" value="<c:out value="${dept.deptNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">公司名称：</td><td><input type="text" id="deptNm" name="dept.deptNm" size="30" value="<c:out value="${dept.deptNm}"/>"/></td>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="dept.remark" size="30" value="<c:out value="${dept.remark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>