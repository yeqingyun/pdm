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

<script src="./include/js/zrsy/chlnV.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'ChlnV'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#fileNm").length > 0)
		$("#fileNm").ligerTextBox({width:145});
	if ($("#chlnNo").length > 0)
		$("#chlnNo").ligerTextBox({width:145});
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
	if ($("#lastDate").length > 0)
		$("#lastDate").ligerDateEditor({showTime: false});
	if ($("#startLastDate").length > 0)
		$("#startLastDate").ligerDateEditor({showTime: false});
	if ($("#endLastDate").length > 0)
		$("#endLastDate").ligerDateEditor({showTime: false});
	if ($("#chlnTyp").length > 0)
		$("#chlnTyp").ligerTextBox({width:145});

      check();
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.fileNm}"/><!--文件名-->：</td><td><input type="text" id="fileNm" name="chlnV.fileNm" validate="{required:true}" value="<c:out value="${chlnV.fileNm}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.chlnNo}"/><!--版本号-->：</td><td><input type="text" id="chlnNo" name="chlnV.chlnNo" validate="{required:true}" value="<c:out value="${chlnV.chlnNo}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.createBy}"/><!--创建人-->：</td><td><input type="text" id="createBy" name="chlnV.createBy" validate="{required:true}" value="<c:out value="${chlnV.createBy}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.createDate}"/><!--创建日期-->：</td><td><input type="text" id="createDate" name="chlnV.createDate" validate="{required:true}" value="<c:out value="${chlnV.createDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.lastUpd}"/><!--最后更新-->：</td><td><input type="text" id="lastUpd" name="chlnV.lastUpd" validate="{required:true}" value="<c:out value="${chlnV.lastUpd}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.lastDate}"/><!--更新日期-->：</td><td><input type="text" id="lastDate" name="chlnV.lastDate" validate="{required:true}" value="<c:out value="${chlnV.lastDate}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center"><c:out value="${labchlnV.chlnTyp}"/><!--单据类型-->：</td><td><input type="text" id="chlnTyp" name="chlnV.chlnTyp" validate="{required:true}" value="<c:out value="${chlnV.chlnTyp}"/>"/></td><td align=left></td>
		</tr>

	</table>
</form>

</div>

</body>
</html>