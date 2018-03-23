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

<script src="./include/js/gnwf/wfRd.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'WfRd'},
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
	
	
	$("#tree1").ligerTree({ 
	     url:'./PrjtDef!loadPrjtTree.shtml', 
        idFieldName :'prjtNo',
        textFieldName :'prjtNm',
        iconFieldName :'iconUri',
        checkbox :false,
        onSelect: function (node, e){
        	var  prjtNo = node.data.prjtNo;
        	//$("#flowId").val(21);
        	$("#projectNo").val(prjtNo);
        	sea();
        }
	});
	
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '工作流编号', name: 'wfNo', align: 'left', width: 110 },
			{ display: '工作流标题', name: 'wfName', align: 'left', width: 240 },
			{ display: '计划开始时间', name: 'planSDate', align: 'center', width: 110 },
			{ display: '计划完成时间', name: 'planEDate', align: 'center', width: 110 },
			{ display: '实际开始时间', name: 'factSDate', align: 'center', width: 110 },
			{ display: '实际完成时间', name: 'factEDate', align: 'center', width: 110 },
			{ display: '状态', name: 'status', align: 'center', width: 70,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.status == 0) {
						innerHtml += "red";
						v = "待处理";
					}else if(row.status == 1){
						innerHtml += "blue";
						v = "办理中";
					}else if(row.status == 2){
						innerHtml += "green";
						v = "已完成";
					}else if(row.status == 3){
						innerHtml += "red";
						v = "已关闭";
					}else if(row.status == 4){
						innerHtml += "red";
						v = "已作废";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			},
			{ display: '创建人', name: 'createName', align: 'center', width: 90 },
			{ display: '创建日期', name: 'createDate', align: 'center', width: 100 }
		],
		checkbox: true,
		rownumbers:true,
		delayLoad:true,		//初始化不加载数据
		pageSize:20,
		url: './WfRd!list.shtml',
		//url: './WfRd!list.shtml?wfRd.selectType='+$("#selectType").val()+'&wfRd.flowId='+$("#flowId").val(),
		usePager:true,
		width: '98.5%',
		height:'98%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow2,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	sea();
});


function f_onDblClickRow2(data){
	
	//wfTaskView(data.wfNo,data.taskId,data.taskStepId);
	wfRdView2(data.wfNo);
}

$(function(){
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#status").length > 0)
		$("#status").ligerComboBox();
	if ($("#createName").length > 0)
		$("#createName").ligerTextBox();
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
});
</script>
</head>

<body style="padding:0px;">
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	<div position="left" style="height: 95%;width: 98%;overflow: auto;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">

<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toolbar"></div>

<div id="sform" style="margin:2px;height:50px;">
<input type="hidden" id="flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>">
<input type="hidden" id="projectNo" name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>">
<input type="hidden" id="selectType" name="wfRd.selectType" value="0">

	<table>
		<tr>
			<td height="24" width="120" align="center">流程标题：</td><td><input type="text" id="wfName" name="wfRd.wfName"/></td>
			<td height="24" width="120" align="center">流程编号：</td><td><input type="text" id="wfNo" name="wfRd.wfNo"/></td>
			<td height="24" width="120" align="center">流程状态：</td>
			<td>
				<select id="status" name="wfRd.status">
					<option value="">请选择</option>
					<option value="0" <c:if test="${wfRd.status=='0'}">selected</c:if>>待处理</option>
					<option value="1" <c:if test="${wfRd.status=='1'}">selected</c:if>>办理中</option>
					<option value="2" <c:if test="${wfRd.status=='2'}">selected</c:if>>已完成</option>
					<option value="3" <c:if test="${wfRd.status=='3'}">selected</c:if>>已关闭</option>
					<option value="4" <c:if test="${wfRd.status=='4'}">selected</c:if>>已作废</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="24" width="120" align="center">创建日期开始：</td>
			<td><input type="text" id="startCreateDate" name="wfRd.startCreateDate"/></td>
			<td height="24" width="120" align="center">创建日期结束：</td>
			<td><input type="text" id="endCreateDate" name="wfRd.endCreateDate"/></td>
			<td height="24" width="120" align="center">创建人：</td>
			<td><input type="text" id="createName" name="wfRd.createName"/></td>
		</tr>

	</table>
</div>


<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>



	</div>
</div> 
</body>

</html>