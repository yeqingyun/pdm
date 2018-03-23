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

<script src="./include/js/zrsy/gpNode.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'GpNode'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'null', name: 'gpId', align: 'left', width: 120 },
			{ display: 'null', name: 'nodeId', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./GpNode!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

$(function(){
	if ($("#gpId").length > 0)
		$("#gpId").ligerComboBox();
	if ($("#nodeId").length > 0)
		$("#nodeId").ligerComboBox();

});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="gpId" name="gpNode.gpId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${gps}" var="gp">
						<option value="<c:out value="${gp.gpId}"/>"><c:out value="${gp.gpName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">null：</td>
			<td>
				<select id="nodeId" name="gpNode.nodeId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${nodes}" var="node">
						<option value="<c:out value="${node.id}"/>"><c:out value="${node.text}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>