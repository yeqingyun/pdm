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

<script src="./include/js/dowWfDoc.js" type="text/javascript"></script>
<script type="text/javascript">
var gridManager;
$(function () {
	$.post("ligerToolBar0.shtml",
			{parm:'ProcFile'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	
	$("#layout1").ligerLayout({
		topHeight:25,
		minLeftWidth:80,
		minRightWidth:80,
		leftWidth: 170
	});
	
	
	$("#maingrid").ligerGrid({
		columns: [
			//'最终附件类别'4
			/* { display: '文档类别', name: 'cateName', align: 'left', width: 160}, */
			//判断看到的文件是否能下载’2‘可以下载’3‘看到但是不能下载
			//{ display: 'downloadStatus', name: 'downloadStatus', align: 'left', width: 160},
			//附件(点击可下载)1
			{ display: '文档名称', name: 'docName', align: 'left', width: 465,
				render: function (row){
					//alert(row.docId);
				var innerHtml="";
					 if(row.downloadStatus==3){
						 //	alert(row.downloadStatus);
						  innerHtml = row.docName;
					
					}else{ 
						 innerHtml = "<img align='middle' src='./include/img/workflow/"+row.icon+"'/>"
						//+"<a href='WfDoc!dow.shtml?wfDoc.docId="+row.docId+"'>"+row.docName+"</a>";
						+"<a href='javascript:dowDoc("+row.docId+")'>"+row.docName+"</a>";
				 	}  
					return innerHtml;
				}	
			},
			//'步骤'2
			/* { display: '工作流', name: 'wfName', align: 'left', width: 160}, */
			//'版本号'3
			{ display: '版本号', name: 'docVer', align: 'center', width: 60 },
			//'上传人'5
			{ display: '创建人', name: 'createName', align: 'center', width: 70 }, 
			//'上传时间'6
			{ display: '创建日期', name: 'createDate', align: 'center', width: 120 }
		],
		
		rownumbers:true,
		/* pageSize:20, */
		//url:'./WfDoc!prjtProceFlieList.shtml?wfDoc.projectNo='+$("#projectNo").val(),
		//url: './PrjtDef!selectWfDoc.shtml?prjtDef.prjtNo='+$('#prjtNo', window.parent.document).val(),
		url:'./WfDoc!prjtSurveyFlieList.shtml?wfDoc.projectNo='+$('#prjtNo', window.parent.document).val(),
		usePager:false,
		checkbox: false,
		showTableToggleBtn:true,

		width: '50%',
		height:'95%',
		
		enabledEdit: true,
	    frozen:false,
        showTitle: false,width:'90%',columnWidth:120,
        /* detail: { onShowDetail: f_showOrder,height:'auto' },
        onError: function (a, b)
        { 
        } */
	});
	$("#pageloading").hide();
	gridManager = $("#maingrid").ligerGetGridManager();
});

var flowIdmanager;
var docCateIdmanager;
$(function(){
	if ($("#docName").length > 0)
		$("#docName").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	
	 $("#flowId").ligerComboBox({url:"SchCfg!getWfs.shtml",  width:186, textField:"flowName", valueField:"flowId", isMultiSelect: false});
	 flowIdmanager = $("#flowId").ligerGetComboBoxManager();
	 
	 $("#docCateId").ligerComboBox({url:"WfDoc!loadDocCates.shtml",  width:186, textField:"docName", valueField:"docId", isMultiSelect: false});
	 docCateIdmanager = $("#docCateId").ligerGetComboBoxManager();
	 $("#flowId,#docCateId").change(function(){
		 sea();
	 });
	 $("#docName").keyup(function(){
		 sea();
	 });
});

/*  function transBase(){
	 $.ligerDialog.confirm('确定要直接归档？', 
				function (type) { 
					if(type) {
						
						var data = gridManager.getSelected();
						if(!data) {
							$.ligerDialog.warn('请选择编辑的记录。');
							return;
						}
						
						var str = '{';
						str += '"wfDoc.fileNo":"'+data.fileNo+'"';
						str += '}';

						$.post("ProcFileWfDoc!transToBasicLib.shtml",
							JSON.parse(str),
							function(data) {
								$.ligerDialog.success(data);
							},
							"text"
						);
					}
				}
			);
 }
  */
 function sea() {
		gridManager.setOptions({
			parms: [
				{name: 'wfDoc.docName', value: $("#docName").val()},
				{name: 'wfDoc.cateId', value:docCateIdmanager.getValue()},
				{name: 'wfDoc.flowId', value:flowIdmanager.getValue()}
			]
		});
		gridManager.loadData();
	}

</script>
</head>

<body style="padding:0px;">
<!-- <div id="layout1" >
	<div id="framecenter">
	
	
<div class="l-loading"  id="pageloading"></div>
<div id="toolbar"></div> -->




<div style="margin-left:20px; " id="maingrid"  ></div>


<!-- </div>  -->
<!--  <table class="table01">
    	<thead>
          <tr>
		    <th>最终附件类别</th>
		    <th>附件(点击可下载)</th>
		    <th>步骤</th>
		    <th>版本</th>
			<th>上传人</th>
			<th>上传时间</th>
			<th>操作</th>
         </tr>
       </thead>
      
 </table>
 -->
</body>

</html>