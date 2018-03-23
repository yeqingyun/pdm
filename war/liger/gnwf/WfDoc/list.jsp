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
			{ display: '工作流标题', name: 'wfName', align: 'left', width: 160},
			{ display: '文档名称', name: 'docName', align: 'left', width: 290,
				render: function (row){
					var innerHtml = "<img align='middle' src='./include/img/workflow/"+row.icon+"'/>"
						+"<a href='WfDoc!dow.shtml?wfDoc.docId="+row.docId+"'>"+row.docName+"</a>";
					return innerHtml;
				}	
			},
			{ display: '版本号', name: 'docVer', align: 'center', width: 50 },
			{ display: '创建人', name: 'createName', align: 'center', width: 80 },
			{ display: '创建日期', name: 'createDate', align: 'center', width: 100 },
			{ display: '工作流编号', name: 'wfNo', align: 'center', width: 100 }
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./WfDoc!list.shtml',
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
	<div position="left" style="overflow-x: hidden;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
	</div>
	<div position="center" id="framecenter">
	
	
<div class="l-loading" style="display: block" id="pageloading"></div>
<div id="toolbar"></div>

<div id="sform" style="margin:2px;height:30px;">
<input type="hidden" id="projectNo" name="wfDoc.projectNo" value="<c:out value="${wfDoc.projectNo}"/>">
	<table>
		<tr>
			<td height="24" width="90" align="center">文档名称：</td>
			<td><input type="text" id="docName" name="wfDoc.docName"/></td>
			<td height="24" width="90" align="center">工作流编号：</td>
			<td><input type="text" id="wfNo" name="wfDoc.wfNo"/></td>
		</tr><tr>
		</tr>

	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

	</div>
</div> 
</body>

</html>