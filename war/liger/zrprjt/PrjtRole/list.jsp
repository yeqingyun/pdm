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

<script src="./include/js/zrprjt/prjtRole.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];

var roleTypData = [{ RoleTyp: 0, text: '人员不随项目变更' }, { RoleTyp: 1, text: '人员随项目变更'}];
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'PrjtRole'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	 $("#prjtTypId").ligerComboBox({url:"PrjtRole!getTyps.shtml",  width:186, textField:"typNm", valueField:"typId", isMultiSelect: false});
	 
	
	$("#maingrid").ligerGrid({
		columns: [
			//{ display: 'ID', name: 'roleId', align: 'left', width: 120 },
			{ display: '角色名称', name: 'roleNm', align: 'left', width: 120 },
			{ display: '项目分类', name: 'typNm', align: 'left', width: 120 },
			{ display: '权限', name: 'isRead', align: 'left', width: 120 },
			{ display: '角色类型', name: 'roleTyp', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: roleTypData, valueColumnName: 'roleTyp' },
                render: function (item)
                {
                    if (parseInt(item.roleTyp) == 0) return '人员不随项目变更';
                    return '人员随项目变更';
                }},
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) return '无效';
                    return '有效';
                }},
			//{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			//{ display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			//{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			//{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			{ display: '备注', name: 'remark', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./PrjtRole!list.shtml',
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
	
	
	if ($("#prjtNo").length > 0)
		$("#prjtNo").ligerComboBox();
	if ($("#roleNm").length > 0)
		$("#roleNm").ligerTextBox();
	if ($("#isRead").length > 0)
		$("#isRead").ligerComboBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();

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
			<td height="24" width="90" align="center">项目分类：</td>
			
			<td>
			 <input type="text" id="prjtTypId" name="prjtRole.prjtTypId" size="30" validate="{required:true}" />
			</td>
			<!--td>
				<select id="prjtNo" name="prjtRole.prjtNo" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtDefs}" var="prjtDef">
						<option value="<c:out value="${prjtDef.prjtNo}"/>"><c:out value="${prjtDef.prjtNo}"/></option>
					</c:forEach>
				</select>
			</td -->
			<td height="24" width="90" align="center">角色名称：</td><td><input type="text" id="roleNm" name="prjtRole.roleNm"/></td>
			<td height="24" width="90" align="center">权限：</td>
			  <td>
			    <select id="isRead" name="prjtRole.isRead" style="width: 196px">
			        <option value="">请选择</option>
					<option value="0">可查看</option>
					<option value="1">可上传</option>
					<option value="2">可下载</option>
					<option value="3">可查看，上传，下载</option>
				</select>
			  </td>
			<td height="24" width="90" align="center">状态：</td>
			  <td>
			  <select id="status" name="prjtRole.status" style="width: 196px">
			        <option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			  </td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
