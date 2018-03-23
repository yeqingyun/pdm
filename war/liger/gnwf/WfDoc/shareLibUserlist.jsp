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

<script src="./include/js/gnwf/wfDoc.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'WfDoc'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	$("#layout1").ligerLayout({
		topHeight:25,
		minLeftWidth:80,
		minRightWidth:80,
		leftWidth: 178
	});
	
	var statusData = [{ Status: 0, text: '无效' }, { Status: 1, text: '查看'}, { Status: 2, text: '下载'}];

	$("#maingrid").ligerGrid({
		columns: [
			{ display: '部门', name: 'deptNm', align: 'left', width: 160},
			{ display: '姓名', name: 'usrNm', align: 'left', width: 160},
			{ display: '权限', name: 'Status', align: 'center', width: 50,
				render: function (item)
                {
                    if (parseInt(item.status) == 0) {
                    	return '无效';
                    }else if(parseInt(item.status) == 1){
                    	return '查看';
                    }else if(parseInt(item.status) == 2){
                    	return '下载';
                    }
                }},
			{ display: '发布人', name: 'createName', align: 'center', width: 80 },
			{ display: '发布日期', name: 'createDate', align: 'center', width: 100 }
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./ShareLibWfDoc!findShareRela.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});
$(function(){

	if ($("#docName").length > 0)
		$("#docName").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
});
</script>
</head>

<body style="padding:0px;">
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	
	
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toolbar"></div>

<div id="sform" style="margin:2px;height:30px;">
<input type="hidden" id="projectNo" name="wfDoc.projectNo" value="<c:out value="${wfDoc.projectNo}"/>">
	<table>
		<tr>
			<td height="24" width="90" align="center">姓名：</td>
			<td><input type="text" id="usrNm" name="shareRela.usrNm"/></td>
			<td height="24" width="90" align="center">权限：</td>
			<td>
			   <select id="status" name="shareRela.status" style="width: 196px">
			        <option value="">请选择</option>
					<option value="0">无效</option>
					<option value="1">查看</option>
					<option value="2">下载</option>
				</select>
			 </td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

	</div>
</div> 
</body>

</html>