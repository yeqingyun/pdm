<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/js/gnwf/wfRisk.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<style type="text/css">
.showinfo{
  display:none;
  left:1px;
  top:1px;
  width:103px; 
  min-height:10px; 
  padding:5px;
  color:#000088;
  background:#aaffee; 
  position:fixed; 
  z-index:999999; 
  font-family: Arial; 
  border: 2px solid #379082; 
  border-radius: 20px; 
  
 }
</style>
<script type="text/javascript">
var MouseEvent = function(e){
	 this.x = e.pageX
	 this.y = e.pageY
}
var Mouse = function(e){
	var kdheight =  jQuery(document).scrollTop();
	mouse = new MouseEvent(e);
	leftpos = mouse.x-50;
	toppos = mouse.y-kdheight+8; 
}
$(function () {
	$("#layout1").ligerLayout({
   		topHeight:25,
   		minLeftWidth:80,
   		minRightWidth:80,
   		leftWidth: 178
   	});
        	
	/* $("#tree1").ligerTree({ 
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
	}); */
   	$("#tree1").ligerTree({ 
   	   url:'./PrjtDef!loadPrjtTree.shtml?loadPrjtTreeType=risk', 
         idFieldName :'prjtNo',
         textFieldName :'prjtNm',
         iconFieldName :'iconUri',
         checkbox :false,
         onSelect: function (node, e){
		     var prjtNo = node.data.prjtNo;
		     $("#prjtNo").val(prjtNo);
		     $.post("WfQues!findProjectRoleID.shtml",{'prjtNo':prjtNo},
	   	       			function(data) {
	        	   //判断是否项目组成员，是项目组成员就可以使用以下按钮	   
	        	   //判断是否“不随角色改变”角色，是就全部显示
					if (document.getElementById("gpName").value.indexOf('不随项目变更角色') > -1) {
						document.getElementById("btnspan").style.display = '';
					}
					else if(data == 0){
		            		document.getElementById("btnspan").style.display = 'none';
		            	}else{
		            		document.getElementById("btnspan").style.display = '';
		            	
		            	}
	   	       			},
	   	       			"json"
	   	       	      ); 
		      if(prjtNo != 'ALL'){
		    	/*  document.getElementById("btnspan").style.display = '';
		    	 document.getElementById("goRiskBtn").style.display = ''; */
		    	 if(document.getElementById("gpName").value.indexOf('项目经理') > -1) {
		    	   	document.getElementById("mailBtn").style.display = '';
		    	 }
		     }else {
		    	/*  document.getElementById("btnspan").style.display = 'none';
		    	 document.getElementById("goRiskBtn").style.display = 'none'; */
		    	 document.getElementById("mailBtn").style.display = 'none';
		     } 
	   	     sea();
         }
   	});
   	
   	$("#mailBtn").hover(function(e) {
		Mouse(e);
		$(".showinfo").css({top:toppos,left:leftpos}).fadeIn(100);
	},function() {
		$(".showinfo").hide();
	});
});
function sendMail() {
	var prjtNo = $("#prjtNo").val();
	if(prjtNo == null || prjtNo == '') {
		$.ligerDialog.warn('请先选择项目！');
		return;
	}
	dialog33 = $.ligerDialog.open({title:'选择项目组成员', height: 400, width: 450,
			   url: './WfRisk!selectPrjtUser.shtml?prjtNo='+prjtNo});
}
function setUsrData(mailUsrId,mailUsrName){
	var str = '{';
	str += '"mailUsrId":"' + mailUsrId + '",';
	str += '"prjtNo":"' + $("#prjtNo").val() + '"';
	str += '}';
	$.post("WfRisk!sendMail.shtml",eval('(' + str + ')'),function(data) {
		$.ligerDialog.success(data);
	},"text"); 
}
function uploadRiskTemplate() {
	window.location.href = './include/template/risk_template.xls';
}

function wfTaskView(wfNo,taskId,taskStepId) {
	//alert("./WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfNo+"&taskStepId="+stepId);
	if(window.parent) {
		if(window.parent.f_open) {
			window.parent.f_open(
				//"./WfRdView.shtml?wfRd.wfNo="+wfNo,
			    "./WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfNo+"&currentTaskId="+taskId+"&taskStepId="+taskStepId,
				"工作流"+wfNo,
				"./include/images/Alien Folder.png"
			);
		}
	}
}
// 启动风险流程
function goRisk1() {
	var selectRow = gridManager.getSelectedRows();
	if(selectRow.length <= 0) {
		$.ligerDialog.warn('请选择风险记录。');
		return false;
	}
	if(document.getElementById("gpName").value.indexOf('项目经理') <= -1) {
		for(var i=0;i<selectRow.length;i++) {
			if(selectRow[i].responsibleUserId.indexOf($("#sys_usrid").val()) <= -1) {
				$.ligerDialog.warn('当前用户必须是责任人才能执行此操作！');
				return;
			}
		}
   	}
	
	for(var i=0;i<selectRow.length;i++) {
		if(selectRow[i].status == 2) {
			$.ligerDialog.warn('不能重复启动！');
			
			//alert(request.getAttribte("wfRd111"));
			//window.open ('/zrprjt/WfRdView!wfTaskView.shtml?wfRd.wfNo=B4314500010&currentTaskId=4501&taskStepId=239');
			
			return;
		}
	}
	//$("span.ic0o02").html()
	//window  = "<a href='#' onClick=\"wfTaskView('"+row.wfNo+"',"+row.taskId+","+row.taskStepId+")\">进入</a>";
	//window  = "<a href='#' onClick=\"wfTaskView('B4314500010',"+row.taskId+","3")\">进入</a>";

	$.ligerDialog.confirm('确定要启动风险流程？', function(type) {
		if (type) {
			$.post("WfRisk!goRisk.shtml", {
				'riskIds' : checkedCustomer.join(',')
			}, function(data) {
				var arr = data.split(",");
				//获取wfRd.wfNo
				 var wfNoarr = arr[0].split(":")[1];
				var currentTaskIdarr = arr[1].split(":")[1];
				var taskStepIdarr = arr[2].split(":")[1];
				//alert(wfNoarr);
				//alert(currentTaskIdarr);
				//alert(taskStepIdarr); 
				$.ligerDialog.success(arr[3].split(":")[1]);
				
				sea();
					window.parent.open(
							encodeURI("./WfRdView!wfTaskView.shtml?wfRd.wfNo="+wfNoarr+"&taskStepId="+taskStepIdarr+"&currentTaskId="+currentTaskIdarr,"UTF-8")
						//"./WfRdView.shtml?wfRd.wfNo="+wfNo,
					);
			}, "text");
			
		}
	});
}


</script>
</head>
<body style="padding:0px;">
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">
	<div position="left" style="height: 95%;width: 98%;overflow: auto;" id="left" title="项目列表" class="l-scroll">
			<ul id="tree1"></ul>
	</div>

	<div position="center" id="framecenter">

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
			<div id="toolbar"></div>
			<div id="sform" style="margin:10px;height:60px;">
				<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
				<input type="hidden" id="prjtNo"  name = "prjtNo" value="<c:out value="${prjtNo}"/>"/>
				<input type="hidden" id="wfNo"  name = "wfNo" value="<c:out value="${wfNo}"/>"/>
				<input type="hidden" id="gpName"  name ="gpName" value="<c:out value="${sessionScope.SYUSR.gpNames}"/>"/>
				<input type="hidden" id="taskCount"/>
				<table>
					<tr>
						<td height="24" width="40" align="center">部门：</td>
						<td> 
						 	<input id="deptName" name="wfRisk.deptName" style="width:100px"/>
						 </td>
						 <td height="24" width="90" align="center">责任人：</td>
						<td> 
						 	<input id="responsibleUserName" name="wfRisk.responsibleUserName" style="width:100px"/>
						 </td>
						<td height="24" width="90" align="center">风险类别：</td>
						<td> 
						 	<input id="riskCategory" name="wfRisk.riskCategory" style="width:100px"/>
						 </td>
						 <td height="24" width="90" align="center">风险状态：</td>
						<td> 
						 	<select id="status" name="wfRisk.status" style="width: 106px;">
								<option value="">请选择</option>
								 <option value="1">OPEN</option> 
								<option value="4">作废</option>
								<option value="5">CLOSE</option>
							</select>
						 </td>
						 
					</tr>
					<tr>
						<td height="24" width="110" align="center">风险说明及后果：</td>
						<td> 
						 	<input id="riskConsequence" name="wfRisk.riskConsequence" style="width:100px"/>
						 </td>
					</tr>
				</table>
				&nbsp;
				<table width="100%">
					<tr>
						<td>
							<div class="wfbigbtn1">
							    <input id="upBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" />
							    <span id="btnspan" style="display: none"> 
								    &nbsp;&nbsp;<input id="addBtn" type="button" value="新增" class="wfbigbtn2"  onclick="add();" />
								 	&nbsp;&nbsp;<input id="editorBtn" type="button" value="编辑"  class="wfbigbtn2" onclick="editorRisk();" />
								 	&nbsp;&nbsp;<input id="impNewBtn" type="button" value=" 导入"  class="wfbigbtn2" onclick="impRisk();" />
								 	&nbsp;&nbsp;<input id="expBtn" type="button" value=" 导出"  class="wfbigbtn2" onclick="exportRisk();" />
								 	&nbsp;&nbsp;&nbsp;&nbsp;
								 	&nbsp;&nbsp;<input id="invalidBtn" type="button" value="作废"  class="wfbigbtn2" onclick="invalidRisk();" />
								 	&nbsp;&nbsp;<input id="invalidBtn" type="button" value="关闭"  class="wfbigbtn2" onclick="closeRisk();" />
							    </span>
							</div>
	   					</td>
	   					<td align="right">
							<input id="mailBtn" style="display: none" type="button" value=" 收集风险"  class="wfbigbtn2" onclick="sendMail()" />
							<div id="prompt" class="showinfo">邮件通知项目组所有组员进行风险的提出</div>
							
							<!-- &nbsp;&nbsp;<input id="goRiskBtn" style="display: none" type="button" value=" 启动风险评估流程"  class="wfbigbtn2" onclick="goRisk1()" />
						 	 -->
						 	&nbsp;&nbsp;<input id="uploadBtn" type="button" value=" 下载风险模板"  class="wfbigbtn2" onclick="uploadRiskTemplate()" />
	   					</td>
					</tr>
				</table>
			</div>
			<br/>
	<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
	</form>
</div>
</body>
</html>