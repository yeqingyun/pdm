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

<script src="./include/js/gnwf/wfMatlPro.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'WfMatlPro'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#purRevDate").length > 0)
		$("#purRevDate").ligerDateEditor({showTime: false});
	if ($("#startPurRevDate").length > 0)
		$("#startPurRevDate").ligerDateEditor({showTime: false});
	if ($("#endPurRevDate").length > 0)
		$("#endPurRevDate").ligerDateEditor({showTime: false});
	if ($("#matRevDate").length > 0)
		$("#matRevDate").ligerDateEditor({showTime: false});
	if ($("#startMatRevDate").length > 0)
		$("#startMatRevDate").ligerDateEditor({showTime: false});
	if ($("#endMatRevDate").length > 0)
		$("#endMatRevDate").ligerDateEditor({showTime: false});
	if ($("#managerRevDate").length > 0)
		$("#managerRevDate").ligerDateEditor({showTime: false});
	if ($("#startManagerRevDate").length > 0)
		$("#startManagerRevDate").ligerDateEditor({showTime: false});
	if ($("#endManagerRevDate").length > 0)
		$("#endManagerRevDate").ligerDateEditor({showTime: false});
	if ($("#matlProId").length > 0)
		$("#matlProId").ligerTextBox();
	if ($("#isPurPass").length > 0)
		$("#isPurPass").ligerTextBox();
	if ($("#isMatPass").length > 0)
		$("#isMatPass").ligerTextBox();
	if ($("#isManagerPass").length > 0)
		$("#isManagerPass").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#proName").length > 0)
		$("#proName").ligerTextBox();
	if ($("#designName").length > 0)
		$("#designName").ligerTextBox();
	if ($("#manageName").length > 0)
		$("#manageName").ligerTextBox();
	if ($("#proDesc").length > 0)
		$("#proDesc").ligerTextBox();
	if ($("#curStep").length > 0)
		$("#curStep").ligerTextBox();
	if ($("#managerReview").length > 0)
		$("#managerReview").ligerTextBox();
	if ($("#managerRemark").length > 0)
		$("#managerRemark").ligerTextBox();
	if ($("#curVersion").length > 0)
		$("#curVersion").ligerTextBox();
	if ($("#proCost").length > 0)
		$("#proCost").ligerTextBox();
	if ($("#purReview").length > 0)
		$("#purReview").ligerTextBox();
	if ($("#purRemark").length > 0)
		$("#purRemark").ligerTextBox();
	if ($("#matReveiw").length > 0)
		$("#matReveiw").ligerTextBox();
	if ($("#matRemark").length > 0)
		$("#matRemark").ligerTextBox();

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
			<td height="24" width="90" align="center">采购审批日期：</td><td><input type="text" id="purRevDate" name="wfMatlPro.purRevDate" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.purRevDate}"/>"/></td>
			<td height="24" width="90" align="center">材料审批日期：</td><td><input type="text" id="matRevDate" name="wfMatlPro.matRevDate" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.matRevDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">产品经理审批日期：</td><td><input type="text" id="managerRevDate" name="wfMatlPro.managerRevDate" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.managerRevDate}"/>"/></td>
			<td height="24" width="90" align="center">MatlProId：</td><td><input type="text" id="matlProId" name="wfMatlPro.matlProId" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.matlProId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">采购是否通过：</td><td><input type="text" id="isPurPass" name="wfMatlPro.isPurPass" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.isPurPass}"/>"/></td>
			<td height="24" width="90" align="center">材料审批结果：</td><td><input type="text" id="isMatPass" name="wfMatlPro.isMatPass" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.isMatPass}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">产品经理审批结果：</td><td><input type="text" id="isManagerPass" name="wfMatlPro.isManagerPass" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.isManagerPass}"/>"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfMatlPro.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.wfNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">项目名称：</td><td><input type="text" id="proName" name="wfMatlPro.proName" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.proName}"/>"/></td>
			<td height="24" width="90" align="center">设计人员：</td><td><input type="text" id="designName" name="wfMatlPro.designName" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.designName}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">产品经理：</td><td><input type="text" id="manageName" name="wfMatlPro.manageName" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.manageName}"/>"/></td>
			<td height="24" width="90" align="center">功能描述：</td><td><input type="text" id="proDesc" name="wfMatlPro.proDesc" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.proDesc}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">产品目前处于研发流程的阶段：</td><td><input type="text" id="curStep" name="wfMatlPro.curStep" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.curStep}"/>"/></td>
			<td height="24" width="90" align="center">产品经理审批人：</td><td><input type="text" id="managerReview" name="wfMatlPro.managerReview" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.managerReview}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">产品经理审批意见：</td><td><input type="text" id="managerRemark" name="wfMatlPro.managerRemark" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.managerRemark}"/>"/></td>
			<td height="24" width="90" align="center">报告版本：</td><td><input type="text" id="curVersion" name="wfMatlPro.curVersion" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.curVersion}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">成本要求：</td><td><input type="text" id="proCost" name="wfMatlPro.proCost" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.proCost}"/>"/></td>
			<td height="24" width="90" align="center">采购工程师：</td><td><input type="text" id="purReview" name="wfMatlPro.purReview" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.purReview}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">采购意见：</td><td><input type="text" id="purRemark" name="wfMatlPro.purRemark" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.purRemark}"/>"/></td>
			<td height="24" width="90" align="center">材料工程师：</td><td><input type="text" id="matReveiw" name="wfMatlPro.matReveiw" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.matReveiw}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">材料审批意见：</td><td><input type="text" id="matRemark" name="wfMatlPro.matRemark" size="30" validate="{required:true}" value="<c:out value="${wfMatlPro.matRemark}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>