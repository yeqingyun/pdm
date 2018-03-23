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
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'AddrBook'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);

	$("#maingrid").ligerGrid({
		columns: [
			{ display: '地址薄ID', name: 'bookId', align: 'left', width: 120 ,hide: 1},
			{ display: '公司', name: 'comId', align: 'left', width: 120 },
			{ display: '部门', name: 'deptId', align: 'left', width: 120 },
			{ display: '名称', name: 'addrName', align: 'left', width: 120 },
			{ display: '用户', name: 'userId', align: 'left', width: 120 ,hide: 1},
			{ display: '用户ID', name: 'empId', align: 'left', width: 120 ,hide: 1},
			{ display: '电话', name: 'phone', align: 'left', width: 120 },
			{ display: '分机', name: 'extPhone', align: 'left', width: 120 },
			{ display: '手机', name: 'mobile', align: 'left', width: 120 },
			{ display: '邮件地址', name: 'mailAddr', align: 'left', width: 120 },
			
			{ display: '备注', name: 'remark', align: 'left', width: 120 ,hide: 1},
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }
		},
			{ display: '创建人', name: 'createBy', align: 'left', width: 120 ,hide: 1},
			{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 ,hide: 1},
			{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./AddrBook!list.shtml',
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
	if ($("#bookId").length > 0)
		$("#bookId").ligerTextBox();
	if ($("#comId").length > 0)
		$("#comId").ligerComboBox();
	if ($("#deptId").length > 0)
		$("#deptId").ligerComboBox();
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
		$("#status").ligerComboBox();
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

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:70px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">公司：</td><td>
				<select id="comId" name="addrBook.comId" style="width:135px">
				<option value="">请选择</option>
					<c:forEach items="${coms}" var="com">
						<option value="<c:out value="${com.comId}"/>"><c:out value="${com.comNm}"/></option>
					</c:forEach>
				</select></td>
			<td height="24" width="90" align="center">部门：</td><td>
				<select id="deptId" name="addrBook.deptId" style="width:135px">
				<option value="">请选择</option>
					<c:forEach items="${depts}" var="dept">
						<option value="<c:out value="${dept.deptId}"/>"<c:if test="${dept.deptId==addrBook.deptId}">selected</c:if>><c:out value="${dept.deptNm}"/></option>
					</c:forEach>
				</select>
			</td>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="addrBook.status" style="width:135px">
					<option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			</td>
			<td height="24" width="90" align="center">电话：</td><td><input type="text" id="phone" name="addrBook.phone"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">员工姓名：</td><td><input type="text" id="addrName" name="addrBook.addrName"/></td>
			<td height="24" width="90" align="center">手机：</td><td><input type="text" id="mobile" name="addrBook.mobile"/></td>
			<td height="24" width="90" align="center">邮件地址：</td><td><input type="text" id="mailAddr" name="addrBook.mailAddr"/></td>
			<td height="24" width="90" align="center">分机：</td><td><input type="text" id="extPhone" name="addrBook.extPhone"/></td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>