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

<script src="./include/js/gnwf/wfField.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'WfField'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '工作流', name: 'flowName', align: 'left', width: 120 },
			{ display: '字段名', name: 'fieldCode', align: 'left', width: 120 },
			{ display: '中文名', name: 'fieldName', align: 'left', width: 120 },
			{ display: '是否允许空', name: 'isNull', align: 'left', width: 120,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.isNull == 0) {
						v = "否";
					} else if(row.isNull == 1){
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
			},
			
			{ display: '标题', name: 'fieldTitle', align: 'left', width: 120 },
			{ display: '是否需要在APP上填写', name: 'needFilledOnAPP', align: 'left', width: 120,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.needFilledOnAPP == 1) {
						innerHtml += "red";
						v = "需要";
					} else if(row.needFilledOnAPP == 0){
						innerHtml += "blue";
						v = "不需要";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}	
			}
			

		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfField!list.shtml',
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
</script>
</head>

<body style="padding:0px;">

<div id="toolbar"></div>

<div class="l-loading" style="display: block" id="pageloading"></div>

<form id="form1">

<div id="sform" style="margin:10px;height:30px;">
	<table>
		<tr>
			<td height="24" width="90" align="center">工作流ID：</td>
			<td>
				<select id="flowId" name="wfField.flowId" >
					<option value="" selected>请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${cfg.flowId==wfField.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
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