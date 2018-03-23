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

<script type="text/javascript" src="./include/js/oa.js"></script>
<script type="text/javascript" src="./include/js/gnwf/wfRd.js" ></script>
<script type="text/javascript" src="./include/js/gnwf/NextStepPage.js"></script>
<script type="text/javascript" src="./include/cal/WdatePicker.js"></script>

<script type="text/javascript" src="./include/js/gnwf/MailTo.js"></script>

<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'BaseLib'},
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
	     url:'./PrjtDef!loadPrjtTree.shtml?loadPrjtTreeType=doc', 
        idFieldName :'prjtNo',
        textFieldName :'prjtNm',
        iconFieldName :'iconUri',
        checkbox :false,
        onSelect: function (node, e){
        	//alert(node.data.prjtNo);
        	var  prjtNo = node.data.prjtNo;
        	//$("#flowId").val(21);
        	if(prjtNo){
        	 $("#projectNo").val(prjtNo);
        	}else{
        		$("#projectNo").val("");
        	}
        	sea();
        }
	});
	
	$("#maingrid").ligerGrid({
		columns: [
			{ display: '文档名称', name: 'docName', align: 'left', width: 290,
				render: function (row){
					var innerHtml = "<img align='middle' src='./include/img/workflow/"+row.icon+"'/>"
					+row.docName;
					//	+"<a href='WfDoc!dow.shtml?wfDoc.docId="+row.docId+"'>"+row.docName+"</a>";
					return innerHtml;
				}	
			},
			{ display: '版本号', name: 'docVer', align: 'center', width: 50 },
			{ display: '文档类别', name: 'cateName', align: 'left', width: 160},
			{ display: '工作流', name: 'wfName', align: 'left', width: 160},
			{ display: '状态',  align: 'center', width: 50,
				render: function (row){
					var v = "未发布";
					if(row.status==2){
						v ="已发布";
					}
					return v;
				}
			},
			{ display: '版本号', name: 'docVer', align: 'center', width: 50 },
			
			{ display: '发布',  align: 'center', width: 50,
				render: function (row){
					//var v = "<a href='BaseLibWfDoc!shareBaseLib.shtml?fileNo="+row.fileNo+"'>发布</a>";
					//+ "<a  href='PrjtDef!download.shtml?fileNo="+PrjtTaskFileNo+"&fileName="+PrjtTaskFileName+"'>"+PrjtTaskFileName+"</a>";
					//var v = "<a href='javascript:shareBaseLib("+row.fileNo+","+row.status+")'>发布</a>";
					
					 return "<a href='#' onclick='shareBaseLib(\""+row.fileNo+"\""+","+row.status+")'>发布</a>";
					//return v;
				}
			},
			{ display: '创建人', name: 'createName', align: 'center', width: 80 },
			{ display: '创建日期', name: 'createDate', align: 'center', width: 100 },
			{ display: '工作流编号', name: 'wfNo', align: 'center', width: 100 }
		],
		checkbox: true,
		rownumbers:true,
		pageSize:20,
		url:'./BaseLibWfDoc!selPrjtBaseLib.shtml',
		usePager:true,
		width: '99.5%',
		height:'99%',
		isChecked: f_isChecked,
		onCheckAllRow: f_onCheckAllRow
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

function shareBaseLib(fileNo,status){
	if(status==2){
		alert("已经发布了，不能再发布");
		return;
	}
		$.post("BaseLibWfDoc!shareBaseLib.shtml?fileNo="+fileNo,
				function(data) {
					alert(data);
				},
				"text"
		);
}

$(function(){
	if ($("#docName").length > 0)
		$("#docName").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	 $("#flowId").ligerComboBox({url:"SchCfg!getWfs.shtml",  width:186, textField:"flowName", valueField:"flowId", isMultiSelect: false});
	 flowIdmanager = $("#flowId").ligerGetComboBoxManager();
	 
	 $("#docCateId").ligerComboBox({url:"WfDoc!loadDocCates.shtml",  width:186, textField:"docName", valueField:"docId", isMultiSelect: false});
	 docCateIdmanager = $("#docCateId").ligerGetComboBoxManager();
});

function sea() {
	//alert($("#projectNo").val());
	gridManager.setOptions({
		parms: [
			{name: 'wfDoc.docName', value: $("#docName").val()},
				{name: 'wfDoc.cateId', value:docCateIdmanager.getValue()},
				{name: 'wfDoc.flowId', value:flowIdmanager.getValue()},
				{name: 'wfDoc.projectNo', value: $("#projectNo").val()}
		]
	});
	gridManager.loadData();
}

function share(fileNo) {
	alert(fileNo);
	
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
				"./BaseLibWfDoc!openShareDocDig.shtml?fileNo="+fileNo,
				"发布",
				"./include/images/Alien Folder.png"
			);
		}
	}
	
}
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
		
		
		 <td height="24" width="50" >工作流：</td>
			  <td>
			    <input type="text" id="flowId"  size="30"   />
			  </td>
			  
			  <td height="24" width="60"  >文档类别：</td>
			  <td>
			    <input type="text" id="docCateId"  size="30"  />
			  </td>
			  
			  
			<td height="24" width="60"  align="center">文档名称：</td>
			
			<td><input type="text" id="docName" name="wfDoc.docName"/></td>
			
			
			<!--td height="24" width="90" align="center">文档名称：</td>
			<td><input type="text" id="docName" name="wfDoc.docName"/></td>
			<td height="24" width="90" align="center">工作流编号：</td>
			<td><input type="text" id="wfNo" name="wfDoc.wfNo"/></td  -->
			<!--td height="24" width="90" align="center">
		     <input type="button" class="btn" value=" 启动发布流程 " 
					   		onclick="openPop('WFSelectUser.shtml');">
					   		<c:set var="i" value="${i+1}"/>
		  </td  -->
		</tr>
	</table>
</div>

<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>

	</div>
</div> 
<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>