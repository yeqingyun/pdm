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

<script src="./include/js/gnwf/wfJob.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar.shtml",
			{parm:'WfJob'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '工作岗位ID', name: 'jobId', align: 'left', width: 120 },
			{ display: '公司', name: 'comNm', align: 'left', width: 120 },
			{ display: '部门', name: 'deptNm', align: 'left', width: 120 },
			{ display: '岗位名称', name: 'jobName', align: 'left', width: 120 },
			{ display: '编制数', name: 'defQty', align: 'left', width: 120 },
			{ display: '任职数', name: 'factQty', align: 'left', width: 120 },
			{ display: '状态', name: 'status', align: 'left', width: 120 ,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.status == 1) {
						innerHtml += "green";
						v = "有效";
					} else if(row.status == 0){
						innerHtml += "";
						v = "无效";
					} 
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			},

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfJob!list.shtml',
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
	if ($("#isUpdOrLoad").length > 0)
		$("#isUpdOrLoad").ligerTextBox();
	if ($("#annexUpdOrLoad").length > 0)
		$("#annexUpdOrLoad").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	$("#comIdVal").ligerComboBox({ url:"Com!loadComs.shtml", textField:'text', valueField:'id',isMultiSelect: false ,
		onSelected: function (newvalue)
		{
			$.post("Com!loadTree.shtml",
			{'com.comId':newvalue},
			function(data) {
				$("#deptIdVal").ligerGetComboBoxManager().setData(data);
			},
			"json"
			);
			$("#comId").val(newvalue);
		}
	});
	$("#deptIdVal").ligerComboBox({isMultiSelect: false,textField:'text', valueField:'id',
		onSelected: function(newvalue) {
			$.post("WfJob!loadJobs.shtml",
			{'jobs.jobId':newvalue},
			function(data) {
				$("#jobIdVal").ligerGetComboBoxManager().setData(data);
			},
			"json"
			);
			$("#deptId").val(newvalue);
		}		
	});
	$("#jobIdVal").ligerComboBox({textField:'text', valueField:'id',isMultiSelect: false,
		onSelected: function (newtext)
        {
			$("#jobId").val(newtext);
        }	
	});
	
});
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:30px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">公司：</td>
			<td>
			 <input type="hidden" id="comId" size="32"/>
			 <input type="text" id="comIdVal" size="32"/>
			</td>
			<td height="24" width="90" align="center">部门：</td>
			<td>
				<input type="hidden" id="deptId" size="32"/>
				<input type="text" id="deptIdVal" size="30" />
			</td>
			<td height="24" width="90" align="center">岗位名称：</td>
			<td>
				<input type="hidden" id="jobId" size="32"/>
				<input type="text" id="jobIdVal"/>
			</td>
			<td height="24" width="90" align="center">状态：</td><td>
			<select id="status" name="hrJobCfg.status" style="width:135px">
					<option value="">请选择</option>
					<option value="1">有效</option>
					<option value="0">无效</option>
				</select>
			</td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>