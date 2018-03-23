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

<script src="./include/js/zrprjt/prjtDef.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout();
	$.post("ligerToolBar1.shtml",
			{parm:'PrjtDef'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#typId").length > 0)
		$("#typId").ligerComboBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#scope").length > 0)
		$("#scope").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#planStaDate").length > 0)
		$("#planStaDate").ligerDateEditor({showTime: false});
	if ($("#startPlanStaDate").length > 0)
		$("#startPlanStaDate").ligerDateEditor({showTime: false});
	if ($("#endPlanStaDate").length > 0)
		$("#endPlanStaDate").ligerDateEditor({showTime: false});
	if ($("#planOveDate").length > 0)
		$("#planOveDate").ligerDateEditor({showTime: false});
	if ($("#startPlanOveDate").length > 0)
		$("#startPlanOveDate").ligerDateEditor({showTime: false});
	if ($("#endPlanOveDate").length > 0)
		$("#endPlanOveDate").ligerDateEditor({showTime: false});
	if ($("#staDate").length > 0)
		$("#staDate").ligerDateEditor({showTime: false});
	if ($("#startStaDate").length > 0)
		$("#startStaDate").ligerDateEditor({showTime: false});
	if ($("#endStaDate").length > 0)
		$("#endStaDate").ligerDateEditor({showTime: false});
	if ($("#oveDate").length > 0)
		$("#oveDate").ligerDateEditor({showTime: false});
	if ($("#startOveDate").length > 0)
		$("#startOveDate").ligerDateEditor({showTime: false});
	if ($("#endOveDate").length > 0)
		$("#endOveDate").ligerDateEditor({showTime: false});
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
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerTextBox();
	if ($("#prjtNm").length > 0)
		$("#prjtNm").ligerTextBox();
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox();
	if ($("#perce").length > 0)
		$("#perce").ligerTextBox();

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
			<td height="24" width="90" align="center">分类ID：</td>
			<td>
				<select id="typId" name="prjtDef.typId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtTyps}" var="prjtTyp">
						<option value="<c:out value="${prjtTyp.typId}"/>"><c:out value="${prjtTyp.typNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="prjtDef.leve" size="30" value="<c:out value="${prjtDef.leve}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">实施范围：</td><td><input type="text" id="scope" name="prjtDef.scope" size="30" value="<c:out value="${prjtDef.scope}"/>"/></td>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="prjtDef.status" size="30" value="<c:out value="${prjtDef.status}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="prjtDef.createBy" size="30" value="<c:out value="${prjtDef.createBy}"/>"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="prjtDef.lastUpd" size="30" value="<c:out value="${prjtDef.lastUpd}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">计划开始：</td><td><input type="text" id="planStaDate" name="prjtDef.planStaDate" size="30" value="<c:out value="${prjtDef.planStaDate}"/>"/></td>
			<td height="24" width="90" align="center">计划结束：</td><td><input type="text" id="planOveDate" name="prjtDef.planOveDate" size="30" value="<c:out value="${prjtDef.planOveDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">实际开始：</td><td><input type="text" id="staDate" name="prjtDef.staDate" size="30" value="<c:out value="${prjtDef.staDate}"/>"/></td>
			<td height="24" width="90" align="center">实际结束：</td><td><input type="text" id="oveDate" name="prjtDef.oveDate" size="30" value="<c:out value="${prjtDef.oveDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="prjtDef.createDate" size="30" value="<c:out value="${prjtDef.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastDate" name="prjtDef.lastDate" size="30" value="<c:out value="${prjtDef.lastDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="prjtNo" name="prjtDef.prjtNo" size="30" value="<c:out value="${prjtDef.prjtNo}"/>"/></td>
			<td height="24" width="90" align="center">项目名称：</td><td><input type="text" id="prjtNm" name="prjtDef.prjtNm" size="30" value="<c:out value="${prjtDef.prjtNm}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="prjtDef.remark" size="30" value="<c:out value="${prjtDef.remark}"/>"/></td>
			<td height="24" width="90" align="center">百分比：</td><td><input type="text" id="perce" name="prjtDef.perce" size="30" value="<c:out value="${prjtDef.perce}"/>"/></td>
		</tr>

	</table>
</form>

</div>

	</div>
</div>

</body>
</html>