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

<script src="./include/js/gnwf/wfCfg.js" type="text/javascript"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'WfCfg'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	$("#maingrid").ligerGrid({
		columns: [
					{ display: '工作流编码', name: 'flowCode', align: 'left', width: 120 },
					{ display: '工作程名称', name: 'flowName', align: 'left', width: 120 },
					{ display: '分类', name: 'cateName', align: 'left', width: 120 },
					{ display: '公司ID', name: 'comId', align: 'left', width: 120 },
					{ display: '部门ID', name: 'deptId', align: 'left', width: 120 },
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
					{ display: '是否需要提问题', name: 'needQues', align: 'left', width: 120,
						render: function (row){
							var innerHtml = "<font color='";
							var v;
							if(row.needQues == 0) {
								innerHtml += "blue";
								v = "不需要";
							} else if(row.needQues == 1){
								innerHtml += "blue";
								v = "需要";
							}
							innerHtml += "'>"+v+"</font>";
							return innerHtml;
						}		
					}
					
					
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfCfg!list.shtml',
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
	if ($("#cateId").length > 0)
		$("#cateId").ligerComboBox();
	if ($("#flowCode").length > 0)
		$("#flowCode").ligerTextBox();
	if ($("#flowName").length > 0)
		$("#flowName").ligerTextBox();
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
			<td height="24" width="90" align="center">流程分类：</td>
			<td>
			<select id="cateId" name="wfCfg.cateId">
				<option value="">请选择</option>
				<c:forEach items="${cates}" var="cate">
					<option value="<c:out value="${cate.cateId}"/>" <c:if test="${wfCfg.cateId==cate.cateId}">selected</c:if>><c:out value="${cate.cateName}"/></option>
				</c:forEach>
			</select>
			
			</td>
			<td height="24" width="90" align="center">工作程名称：</td><td><input type="text" id="flowName" name="wfCfg.flowName"/></td>
		</tr>
	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</form>

</body>

</html>