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

<script src="./include/js/zrsy/dept.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'Dept'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '部门Id', name: 'deptId', align: 'left', width: 120 ,hide: 1},
			{ display: '公司', name: 'comNm', align: 'left', width: 120 },
			{ display: '上级部门', name: 'parent', align: 'left', width: 120 },
			{ display: '部门编号', name: 'deptNo', align: 'left', width: 120 },
			{ display: '部门名称', name: 'deptNm', align: 'left', width: 120 },
			{ display: '级别', name: 'leve', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }
             },
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 ,hide: 1},
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 ,hide: 1},
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			
			{ display: '备注', name: 'remark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./Dept!list.shtml',
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
	if ($("#comId").length > 0)
		$("#comId").ligerComboBox({selectBoxWidth: 180});
	if ($("#parent").length > 0)
		$("#parent").ligerComboBox({selectBoxWidth: 180});
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#deptNo").length > 0)
		$("#deptNo").ligerTextBox();
	if ($("#deptNm").length > 0)
		$("#deptNm").ligerTextBox();

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
			<td height="24" width="90" align="center">公司：</td>
			<td>
				<select id="comId" name="dept.comId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${coms}" var="com">
						<option value="<c:out value="${com.comId}"/>"><c:out value="${com.comNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">上级部门：</td><td>
				<select id="parent" name="dept.parent" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${depts}" var="dept">
						<option value="<c:out value="${dept.deptId}"/>"><c:out value="${dept.deptNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="dept.leve"/></td>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="dept.status" style="width:135px">
					<option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			</td>
		</tr><tr>
			<td height="24" width="90" align="center">公司名称：</td><td><input type="text" id="deptNm" name="dept.deptNm"/></td>
			<td height="24" width="90" align="center">公司编号：</td><td><input type="text" id="deptNo" name="dept.deptNo"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="dept.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="dept.endCreateDate"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
