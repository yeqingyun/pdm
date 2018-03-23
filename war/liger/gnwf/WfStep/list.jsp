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

<script src="./include/js/gnwf/wfStep.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'WfStep'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '步骤ID', name: 'stepId',align: 'center', width: 50},
			{ display: '所属工作流', name: 'flowName', align: 'left', width: 120 },
			{ display: '步骤名称', name: 'stepName', align: 'left', width: 120 },
			{ display: '步骤类型', name: 'stepType', align: 'left', width: 120,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.stepType == 1) {
						v = "普通";
					}else if(row.stepType == 2){
						v = "分支";
					}else if(row.stepType == 3){
						v = "并发";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			},
			{ display: '排序', name: 'sort', align: 'left', width: 120 },
			{ display: '是否自动流转', name: 'isAuto', align: 'left', width: 120,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.isAuto == 0) {
						v = "否";
					} else if(row.isAuto == 1){
						innerHtml += "blue";
						v = "是";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			},
			{ display: '最后步骤', name: 'isLastStep', align: 'left', width: 120,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.isLastStep == 0) {
						v = "否";
					} else if(row.isLastStep == 1){
						innerHtml += "blue";
						v = "是";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}	
			},
			{ display: '状态', name: 'status', align: 'left', width: 120,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.status == 0) {
						innerHtml += "red";
						v = "无效";
					} else if(row.status == 1){
						innerHtml += "blue";
						v = "有效";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			}
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfStep!list.shtml',
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
	if ($("#flowId").length > 0)
		$("#flowId").ligerComboBox();
});

function f_select()
{
    var rows = gridManager.getCheckedRows(); 
    return rows; 
}
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:50px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">所属工作流：</td>
			<td>
				<select id="flowId" name="wfStep.flowId" >
					<option value="" selected>请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${cfg.flowId==wfStep.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>