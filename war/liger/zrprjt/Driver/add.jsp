<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<style type="text/css">
	body .l-button{width:120px;float:left;}
</style>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/zrprjt/driver.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'Driver'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	if ($("#driveNo").length > 0)
		$("#driveNo").ligerTextBox({width:145});
	if ($("#driveNm").length > 0)
		$("#driveNm").ligerTextBox({width:145});
	if ($("#remark").length > 0)
		$("#remark").ligerTextBox({width:145});
	$("#flowId").ligerComboBox({width:145,isMultiSelect: false,selectBoxWidth:180,
			onSelected: function(newvalue) {
				$.post("WfStep!loadSteps.shtml",
						{'wfStep.flowId':newvalue},
						function(data) {
				            $("#stepId").ligerGetComboBoxManager().setData(data);
						},
						"json"
				);
			},
			onSuccess: function() {
				$("#flowId").ligerComboBox().selectValue("<c:out value="${driver.flowId}"/>");
			}
		}
	);
	$("#stepId").ligerComboBox({url:"wfStep!loadSteps.shtml?wfStep.flowId=<c:out value="${driver.flowId}"/>",textField:'text', valueField:'id', isMultiSelect: false,selectBoxWidth:180,
			onSuccess: function() {
				$("#stepId").ligerComboBox().selectValue("<c:out value="${driver.stepId}"/>");
			}
		}
	);
      check();
});

var gridManager;
var dirData = [{ direct: 1, text: '入' }, { direct: -1, text: '出'}];
$(function () {
	gridManager = $("#maingrid").ligerGrid({
		columns: [
		  		{ display: 'flowId', name: 'flowId',hide: true},
		  		{ display: 'stepId', name: 'stepId',hide: true},
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
		  	],
		  	checkbox: true,
		  	rownumbers:true,
		  	pageSize:20,
		  	url:'./DriverDtl!list.shtml?driveId=<c:out value="${driver.driveId}"/>',
		  	usePager:false,
		  	width: '99.5%',
		  	height:'99%',
		  	isChecked: f_isChecked,
		  	onCheckRow: f_onCheckRow,
		  	onCheckRow: f_onCheckRow,
		  	onDblClickRow: f_onDblClickRow,
		  	onCheckAllRow: f_onCheckAllRow
	});
});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>
	<input type="hidden" id="driveId" name="driver.driveId" value="<c:out value="${driver.driveId}"/>"/>
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">驱动编号：</td><td><input type="text" id="driveNo" name="driver.driveNo" value="<c:out value="${driver.driveNo}"/>"/></td><td align=left></td>
			<td height="24" width="90" align="center">驱动名称：</td><td><input type="text" id="driveNm" name="driver.driveNm" value="<c:out value="${driver.driveNm}"/>"/></td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流：</td><td>
				<select id="flowId" name="driver.flowId">
					<option value="-1" selected>请选择</option>
					<c:forEach items="${wfCfgs}" var="cfg">
						<option value="<c:out value="${cfg.flowId}"/>" <c:if test="${driver.flowId==cfg.flowId}">selected</c:if>><c:out value="${cfg.flowName}"/></option>
					</c:forEach>
				</select>
			</td><td align=left></td>
			<td height="24" width="90" align="center">步骤：</td><td>
				<input type="text" id="stepId" name="driver.stepId" ltype="text" />
			</td><td align=left></td>
		</tr><tr>
			<td height="24" width="90" align="center">备注：</td><td><input type="text" id="remark" name="driver.remark" value="<c:out value="${driver.remark}"/>"/></td><td align=left></td>
		</tr>
	</table>
</form>
<br/>
<div>
		<a class="l-button" style="width:60px;" onclick="addNewRow()">添加</a>
		<a class="l-button" style="width:60px;" onclick="deleteRow()">删除</a>
	</div>
	 <div class="l-clear"></div>
	    <div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

</div>

</body>
</html>