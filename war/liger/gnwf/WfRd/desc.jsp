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

<script type="text/javascript">
$(function () {
	$("#layout1").ligerLayout({
		topHeight:25,
		minLeftWidth:80,
		minRightWidth:80,
		leftWidth: 178
	});
	
	$("#tree1").ligerTree({
		url:'./ligerTree!loadWorkFlowTree1.shtml', 
        idFieldName :'id',
        textFieldName :'text',
        checkbox :false,
        onAfterAppend:function(){
        	$("#tree1").ligerGetTreeManager().collapseAll();
        },//树默认折叠
        onSelect: function (node, e){
        	if(node.data.id=='help'){
        		window.open("./liger/gnwf/WfRd/金立研发管理系统操作手册V0.2.doc");
        		return;
        	}else if(node.data.id=='help1'){
         		window.open("./liger/gnwf/WfRd/PDM操作手册/操作手册_金立PDM系统_CMO.doc");
        		return;
        	}else if(node.data.id=='help2'){
         		window.open("./liger/gnwf/WfRd/PDM操作手册/操作手册_金立PDM系统_DQA.doc");
        		return;
        	}else if(node.data.id=='help3'){
         		window.open("./liger/gnwf/WfRd/PDM操作手册/操作手册_金立PDM系统_产品经理.doc");
        		return;
        	}else if(node.data.id=='help4'){
         		window.open("./liger/gnwf/WfRd/PDM操作手册/操作手册_金立PDM系统_项目经理.doc");
        		return;
        	}else if(node.data.id=='help5'){
         		window.open("./liger/gnwf/WfRd/PDM操作手册/操作手册_金立PDM系统_项目组成员.doc");
        		return;
        	}else if(node.data.id=='help6'){
         		window.open("./liger/gnwf/WfRd/PDM操作手册/流程管理.doc");
        		return;
        	}
	        var id = node.data.id;
	        if($("#tree1").ligerGetTreeManager().hasChildren(node.data)){
	        	//treeManager.expand(node.data);
        	}else{
        		$("#frame").attr("src",'./WfRdView!previewDesc.shtml?wfRd.flowId='+id);
        		$("#flowId").val(id);
        	}
        },
        onBeforeExpand: function (node, e){
        	$("#tree1").ligerGetTreeManager().collapseAll();
        },
        onSuccess:function(){
        	
        	$("#tree1").ligerGetTreeManager().append(null, [{id:"help111",text:"PDM操作手册" ,children: [
        	                                                                                      {id:"help1", text: 'CMO手册' },
        	                                                                                      {id:"help2",text: 'DQA手册' },
        	                                                                                      {id:"help3",text: '产品经理手册' },
        	                                                                                      {id:"help4",text: '项目经理手册' },
        	                                                                                      {id:"help5",text: '项目组成员手册' },
        	                                                                                      {id:"help6",text: '流程管理' }]}]);
        
        	$("#tree1").ligerGetTreeManager().append(null, [{id:"help",text:"统操作手册下载" }]);
        }

	});
	
	
	
	
	
	
    function resizeWin() {
		var h_tol = $('#layout1').height();
		var h_top = $('#top').height();
		$('#left').height(h_tol-h_top-30);
	}
	
	$(function(){
		resizeWin();
		window.onresize = function(){  
			var h_tol = $('#layout1').height();
			var h_top = $('#top').height();
			$('#left').height(h_tol-h_top-30);
        }  
	});
	$("#pageloading").hide();
});
</script>
</head>

<body style="padding:10px;">
<input type="hidden" id="flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>" />
<div id="layout1">
	<div position="left" style="overflow:auto;overflow-x: hidden;" id="left" title="工作流类别" class="l-scroll">
			<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">

<div class="l-loading" style="display: block" id="pageloading"></div>

<iframe id="frame" width="100%" height="100%"></iframe>

	</div>
</div> 
</body>

</html>