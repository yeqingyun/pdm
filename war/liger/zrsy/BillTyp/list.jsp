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

<script src="./include/js/zrsy/billTyp.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'BillTyp'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: 'Id', name: 'id', align: 'left', width: 120 },
			{ display: '主表外键', name: 'subsId', align: 'left', width: 120 },
			{ display: '单据类型', name: 'typNm', align: 'left', width: 120 },
			{ display: '编码(比如:A01)', name: 'code', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./BillTyp!list.shtml',
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
	if ($("#id").length > 0)
		$("#id").ligerTextBox({width:145});
	if ($("#subsId").length > 0)
		$("#subsId").ligerComboBox({selectBoxWidth:200,width:145});
	if ($("#typNm").length > 0)
		$("#typNm").ligerTextBox({width:145});
	if ($("#code").length > 0)
		$("#code").ligerTextBox({width:145});

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
			<td height="24" width="90" align="center">Id：</td><td><input type="text" id="id" name="billTyp.id"/></td>
			<td height="24" width="90" align="center">主表外键：</td>
			<td>
				<select id="subsId" name="billTyp.subsId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${billSubss}" var="billSubs">
						<option value="<c:out value="${billSubs.id}"/>"><c:out value="${billSubs.id}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">单据类型：</td><td><input type="text" id="typNm" name="billTyp.typNm"/></td>
			<td height="24" width="90" align="center">编码(比如:A01)：</td><td><input type="text" id="code" name="billTyp.code"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>