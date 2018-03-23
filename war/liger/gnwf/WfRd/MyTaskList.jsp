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

	$("#layout1").ligerLayout({
		topHeight:25,
		minLeftWidth:80,
		minRightWidth:80,
		leftWidth: 178
	});
	
	$("#tree1").ligerTree({ 
	     url:'./PrjtDef!loadPrjtTree.shtml?loadPrjtTreeType=task', 
        idFieldName :'prjtNo',
        textFieldName :'prjtNm',
        iconFieldName :'iconUri',
        checkbox :false,
        onSelect: function (node, e){
        	var prjtNo = node.data.prjtNo;
        	$("#projectNo").val(prjtNo);
	        sea();
        }
	});
	
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '工作流编号', name: 'wfNo', align: 'left', width: 90 },
			{ display: '工作流标题', name: 'wfName', align: 'left', width: 200 },
			 { display: '工作流任务', name: 'stepName', align: 'left', width: 160 ,hide : true}, 
			{ display: '计划开始时间', name: 'planSDate', align: 'center', width: 100 },
			{ display: '计划完成时间', name: 'planEDate', align: 'center', width: 100 },
			//{ display: '实际开始时间', name: 'factSDate', align: 'center', width: 100 },
			/* { display: '实际开始时间', name: 'acceptDate', align: 'center', width: 100 ,hide : true},
			{ display: '实际完成时间', name: 'factEDate', align: 'center', width: 100  ,hide : true}, */
			{ display: '分发时间', name: 'acceptDate', align: 'center', width: 90 },
			 { display: '完成时间', name: 'endDate', align: 'center', width: 90}, 
			
			{ display: '状态', name: 'status', align: 'center', width: 50,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.taskStatus == 0) {
						innerHtml += "red";
						v = "待处理";
					}else if(row.taskStatus == 1){
						innerHtml += "blue";
						v = "办理中";
					}else if(row.taskStatus == 2){
						innerHtml += "green";
						v = "已完成";
					}else if(row.taskStatus == 3){
						innerHtml += "red";
						v = "已关闭";
					}else if(row.taskStatus == 4){
						innerHtml += "red";
						v = "已作废";
					}else if(row.taskStatus == 5){
						innerHtml += "red";
						v = "已收回";
					}else if(row.taskStatus == 6){
						innerHtml += "red";
						v = "已退回";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			},
			{ display: '创建人', name: 'createName', align: 'center', width: 70 },
			{ display: '创建日期', name: 'createDate', align: 'center', width: 100 },
			{ display: '操作', name: 'wfDesc', align: 'center', width: 50,
				render: function (row){
					var v = "<a href='#' onClick=\"wfTaskView('"+row.wfNo+"',"+row.taskId+","+row.taskStepId+")\">进入</a>";
					return v;
				}
			},
			{ display: '编辑', name: 'wfDesc', align: 'center', width: 50,
				render: function (row){
					var v = "<a href='#' onClick=\"wfEditTaskView('"+row.wfNo+"',"+row.taskId+","+row.taskStepId+")\">编辑</a>";
					return v;
				}
			}
		],
		checkbox: true,
		rownumbers:true,
		delayLoad:true,		//初始化不加载数据
		pageSize:20,
		//url: './WfRd!selectTask.shtml',
		url: './WfRd!selectMyTask.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onDblClickRow: f_onDblClickRow,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
	selectWfRd();
});

function selTask(type){
	$("#selectType").val(type);
	selectWfRd();
}

$(function(){
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
});
</script>
</head>

<body style="padding:0px;">

<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	<div position="left" style="height: 95%;width:98%;overflow: auto;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">
<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">
<input type="hidden" id="selectType" name="selectType" value="<c:out value="${wfRd.selectType}"/>"/>
<input type="hidden" id="flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>"/>
<input type="hidden" id=projectNo name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>"/>

<!-- 
<div id="sform" style="margin:10px;height:30px;">
	<table>
		<tr>
			<td height="24" width="120" align="center">工作流标题：</td><td><input type="text" id="wfName" name="wfRd.wfName"/></td>
			<td height="24" width="120" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfRd.wfNo"/></td>
			<td height="24" width="120" align="center">流程种类：</td>
			<td>
				<select id="flowId">
					<option value="">请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${wfRd.flowId==cfg.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
</div>
-->

<div id="sform" style="margin:10px;height:12px;">
	<table>
		<tr>
			<td>
				&nbsp;&nbsp;<input type="radio" name="check" onclick="selTask('1');" checked="checked"/><font color="blue">未处理任务</font>
				&nbsp;&nbsp;<input type="radio" name="check" onclick="selTask('5');"/><font color="blue">已处理任务</font>
			</td>
		</tr>
	</table>
</div>
<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

	</div>
</div> 
</body>

</html>