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

<script src="./include/js/zrprjt/prjtDef.js?v=1.0" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '新创建'},{ Status: 2, text: '已启动'},{ Status: 3, text: '暂停'},{ Status: 4, text: '已结束'}];
var devDeptNameIDData = [{ devDeptNameID: 1, text: '研发一部' }, { devDeptNameID: 2, text: '研发二部'},{ devDeptNameID: 3, text: '研发三部'},{ devDeptNameID: 4, text: '海外研发部'}];

$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'PrjtDef'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	
	$("#maingrid").ligerGrid({ 
		columns: [
			{ display: '项目编号', name: 'prjtNo', align: 'left', width: 120 },
			{ display: '项目名称', name: 'prjtNm', align: 'left', width: 120 },	
			{ display: '研发部门', name: 'devDeptNameID', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: devDeptNameIDData, valueColumnName: 'devDeptNameID' },
                render: function (item)
                {
                    if (parseInt(item.devDeptNameID) == 1) {
                    	return '研发一部';
                    }else if(parseInt(item.devDeptNameID) == 2){
                    	return '研发二部';
                    }else if(parseInt(item.devDeptNameID) == 3){
                    	return '研发三部';
                    }else if(parseInt(item.devDeptNameID) == 4){
                    	return '海外研发部';
                    }
                }},
            { display: '创建日期', name: 'createDate', align: 'left', width: 120 },
			//{ display: '分类名称', name: 'projtTypeNm', align: 'left', width: 120 },
			//{ display: '级别', name: 'leve', align: 'left', width: 120 },
			//{ display: '实施范围', name: 'scope', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,type:'int',
				editor: { type: 'select', data: statusData, valueColumnName: 'status' },
                render: function (item)
                {
                    if (parseInt(item.status) == 0) {
                    	return '无效';
                    }else if(parseInt(item.status) == 1){
                    	return '新创建';
                    }else if(parseInt(item.status) == 2){
                    	return '已启动';
                    }else if(parseInt(item.status) == 3){
                    	return '暂停';
                    }else if(parseInt(item.status) == 4){
                    	return '已结束';
                    }
                }}
			//{ display: '创建人', name: 'createBy', align: 'left', width: 120 },
			//{ display: '最后更新', name: 'lastUpd', align: 'left', width: 120 },
			//{ display: '计划开始', name: 'planStaDate', align: 'left', width: 120 },
			//{ display: '计划结束', name: 'planOveDate', align: 'left', width: 120 },
			//{ display: '实际开始', name: 'staDate', align: 'left', width: 120 },
			//{ display: '实际结束', name: 'oveDate', align: 'left', width: 120 },
			
			//{ display: '更新日期', name: 'lastDate', align: 'left', width: 120 },
			//{ display: '备注', name: 'remark', align: 'left', width: 120 },
			//{ display: '百分比', name: 'perce', align: 'left', width: 120 }

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./PrjtDef!list.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		//onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

$(function(){
	if ($("#typId").length > 0)
		$("#typId").ligerComboBox();
	if ($("#leve").length > 0)
		$("#leve").ligerTextBox();
	if ($("#scope").length > 0)
		$("#scope").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
 	/* if ($("#devDeptNameID").length > 0)
		$("#devDeptNameID").ligerTextBox();  */
	/**if ($("#createBy").length > 0)
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
**/
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
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="prjtNo" name="prjtDef.prjtNo"/></td>
			<td height="24" width="90" align="center">项目名称：</td><td><input type="text" id="prjtNm" name="prjtDef.prjtNm"/></td>
			<td height="24" width="90" align="center">项目分类：</td>
			<td>
				<select id="typId" name="prjtDef.typId" style="width:135px">
					<option value="">请选择</option>
					<c:forEach items="${prjtTyps}" var="prjtTyp">
						<option value="<c:out value="${prjtTyp.typId}"/>"><c:out value="${prjtTyp.typNm}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">级别：</td><td><input type="text" id="leve" name="prjtDef.leve"/></td>
			<td height="24" width="90" align="center">实施范围：</td><td><input type="text" id="scope" name="prjtDef.scope"/></td>
			<td height="24" width="90" align="center">状态：</td>
			<td>
				<select id="status" name="prjtDef.typId" style="width:135px">
					<option value="">全部</option>
					<option value="0">无效</option>
	 				<option value="1" selected>新创建</option>
	 				<option value="2">已启动</option>
	 				<option value="3">暂停</option>
	 				<option value="4">已结束</option>
				</select>
			</td>
		</tr>
		
		<!-- tr>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="prjtDef.createBy"/></td>
			<td height="24" width="90" align="center">最后更新：</td><td><input type="text" id="lastUpd" name="prjtDef.lastUpd"/></td>
			<td height="24" width="90" align="center">计划开始始：</td><td><input type="text" id="startPlanStaDate" name="prjtDef.startPlanStaDate"/></td>
			<td height="24" width="90" align="center">计划开始止：</td><td><input type="text" id="endPlanStaDate" name="prjtDef.endPlanStaDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">计划结束始：</td><td><input type="text" id="startPlanOveDate" name="prjtDef.startPlanOveDate"/></td>
			<td height="24" width="90" align="center">计划结束止：</td><td><input type="text" id="endPlanOveDate" name="prjtDef.endPlanOveDate"/></td>
			<td height="24" width="90" align="center">实际开始始：</td><td><input type="text" id="startStaDate" name="prjtDef.startStaDate"/></td>
			<td height="24" width="90" align="center">实际开始止：</td><td><input type="text" id="endStaDate" name="prjtDef.endStaDate"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">实际结束始：</td><td><input type="text" id="startOveDate" name="prjtDef.startOveDate"/></td>
			<td height="24" width="90" align="center">实际结束止：</td><td><input type="text" id="endOveDate" name="prjtDef.endOveDate"/></td>
			<td height="24" width="90" align="center">创建日期始：</td><td><input type="text" id="startCreateDate" name="prjtDef.startCreateDate"/></td>
			<td height="24" width="90" align="center">创建日期止：</td><td><input type="text" id="endCreateDate" name="prjtDef.endCreateDate"/></td>
			<td height="24" width="90" align="center">更新日期始：</td><td><input type="text" id="startLastDate" name="prjtDef.startLastDate"/></td>
			<td height="24" width="90" align="center">更新日期止：</td><td><input type="text" id="endLastDate" name="prjtDef.endLastDate"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="prjtDef.remark"/></td>
			<td height="24" width="90" align="center">百分比：</td><td><input type="text" id="perce" name="prjtDef.perce"/></td>
		</tr-->

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>
