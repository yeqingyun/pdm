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

<script src="./include/js/gnmail/mailBook.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#bookId").length > 0)
		$("#bookId").ligerTextBox({width:145});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox({width:145});
	if ($("#groupId").length > 0)
		$("#groupId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#extPhone").length > 0)
		$("#extPhone").ligerTextBox({width:145});
	if ($("#phone").length > 0)
		$("#phone").ligerTextBox({width:145});
	if ($("#mobile").length > 0)
		$("#mobile").ligerTextBox({width:145});
	if ($("#mailAddr").length > 0)
		$("#mailAddr").ligerTextBox({width:145});
	if ($("#addrName").length > 0)
		$("#addrName").ligerTextBox({width:145});
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox({width:145});
	if ($("#status").length > 0)
		$("#status").ligerTextBox({width:145});
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox({width:145});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox({width:145});
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">地址薄ID：</td><td><input type="text" id="bookId" name="mailBook.bookId" validate="{required:true}" value="<c:out value="${mailBook.bookId}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">用户ID：</td><td><input type="text" id="userId" name="mailBook.userId" validate="{required:true}" value="<c:out value="${mailBook.userId}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">分组ID：</td>
			<td>
				<select id="groupId" name="mailBook.groupId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${mailGroups}" var="mailGroup">
						<option value="<c:out value="${mailGroup.groupId}"/>"><c:out value="${mailGroup.groupId}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">电话1：</td><td><input type="text" id="extPhone" name="mailBook.extPhone" validate="{required:true}" value="<c:out value="${mailBook.extPhone}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">电话1：</td><td><input type="text" id="phone" name="mailBook.phone" validate="{required:true}" value="<c:out value="${mailBook.phone}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">手机：</td><td><input type="text" id="mobile" name="mailBook.mobile" validate="{required:true}" value="<c:out value="${mailBook.mobile}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">邮件件地址：</td><td><input type="text" id="mailAddr" name="mailBook.mailAddr" validate="{required:true}" value="<c:out value="${mailBook.mailAddr}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">名称：</td><td><input type="text" id="addrName" name="mailBook.addrName" validate="{required:true}" value="<c:out value="${mailBook.addrName}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="mailBook.remark" validate="{required:true}" value="<c:out value="${mailBook.remark}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="mailBook.status" validate="{required:true}" value="<c:out value="${mailBook.status}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="mailBook.createBy" validate="{required:true}" value="<c:out value="${mailBook.createBy}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="mailBook.createDate" validate="{required:true}" value="<c:out value="${mailBook.createDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="mailBook.lastUpd" validate="{required:true}" value="<c:out value="${mailBook.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="mailBook.lastUpdDate" validate="{required:true}" value="<c:out value="${mailBook.lastUpdDate}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>