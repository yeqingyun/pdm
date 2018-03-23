<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="./include/css/global.css" />
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css" />
<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>

<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script type="text/javascript" src="./include/js/gnwf/wfRd.js"></script>
<script type="text/javascript" src="./include/js/gnwf/WfRd_special.js"></script>
<script type="text/javascript" src="./include/js/gnwf/nextStep.js"></script>

<script src="./include/js/Xmlhttp.js" type="text/javascript"></script>

<%--  <%@ include file="IncudeExtendField.jsp"%>  --%>
<link type="text/css" rel="stylesheet" href="./include/css/filed.css"/>
<!-- 冲突 -->
<!-- <script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script> -->
<link rel="stylesheet" href="./include/kindeditor-4.1.10/themes/default/default.css" />
<script type="text/javascript" charset="utf-8" src="./include/kindeditor-4.1.10/kindeditor-min.js"></script>
<script type="text/javascript" charset="utf-8" src="./include/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" src="./include/cal/WdatePicker.js"></script>

<script type="text/javascript">
//同时初始化多个kindeditor
//KindEditor网页编辑器
//如果有属性editor="true",则说明它是KindEditor
//如果有disabled="disabled"属性,则是不可编辑状态
$(function () {
	KindEditor.ready(function(K) {
		$('textarea[editor="true"]').each(function(){
			var k = K.create(this, {
				resizeType :1,
				shadowMode : false,
				allowPreviewEmoticons : false,
				urlType : 'absolute',
				uploadJson : './WfQues!uploadImg.shtml',
				fileManagerJson : './WfQues!fileManager.shtml',
				height :"500",
				width:"680",
				allowFileManager : true,
				afterCreate : function() {
					this.sync();
				},
		        afterBlur:function(){
		            this.sync();
		        }
			});
			if(typeof( $(this).attr("readonly")) != "undefined"){
				k.readonly($(this).attr("readonly"));//设置只读属性
			}else if(typeof( $(this).attr("disabled")) != "undefined"){
				k.readonly($(this).attr("disabled"));//设置只读属性
			}
		});
	});
	
	var readonlyColor="#F0F0F0";//不可编辑颜色
	var editColor="#FFFFFF";//可以编辑的颜色
	$('select, input:text, input:checkbox, input:password, textarea[editor!="true"]').each(function(){
		var readonly=true;
		if(typeof( $(this).attr("readonly")) != "undefined"){
			//$(this).attr('disabled',$(this).attr("readonly"));//如果是只读,则设置为不可编辑
			if($(this).attr("readonly")){//如果是只读,则设置为灰色
				$(this).css("background-color",readonlyColor);//
				readonly=false;
			}else{
				$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
			}
		}else{
			$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
		}
		if(readonly){
			if(typeof( $(this).attr("disabled")) != "undefined"){
				//$(this).attr('disabled',$(this).attr("readonly"));//如果是只读,则设置为不可编辑
				if($(this).attr("disabled")){//如果是只读,则设置为灰色
					$(this).css("background-color",readonlyColor);//
				}else{
					$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
				}
			}else{
				$(this).css("background-color",editColor);//如果可以编辑,则设置为白色
			}
		}
	});
});
</script>



<style type="text/css" rel="stylesheet" >
.allBox{width:988px;margin:10px auto;overflow:hidden;}
.allTit{ border:#ffd4c1 solid 1px;background:#fff0e9;}
.allTit p{padding:0 10px;line-height:32px;color:#78503d;font-size:14px;}
/* .allTit p b{padding:0 10px;} */
.allTit p span{padding-right:15px;}
.borBRed{border-bottom:#ffd4c1 1px solid;}
.area01{margin:10px 0;border:#ccc solid 1px;width:986px;}
.titBox{background:url(./include/img/workflow/rpx.png) repeat-x 0 0;padding:0 10px;height:32px;line-height:32px;border-bottom:#b7cee2 solid 1px;}
.titBox h3{font-size:16px;float:left;color:#515c68;font-weight:700;}
.titBox .butonList{margin-top:4px;float:right;}
.titBox .butonList li{float:left;}
.titBox .butonList li a{line-height:19px;padding:0 10px;text-decoration:none;margin:0 5px;font-size:12px;color:#fff; display:block;height:height:19px;border:#23537d solid 1px; border-radius:3px;background:#3670F2;}
.titBox .butonList li a:hover{background:#669bca;}
.processPicture{width:944px;text-align:center;margin:10px auto;margin-top:20px;}
.processSection{width:118px;display:inline-block;margin:0 -4px;margin-bottom:10px;}
.PSTit{font-size:12px;height:66px;overflow:hidden;color:#666;line-height:22px;padding:0 17px;width:90px;}
*+ html .processSection { display:inline;zoom:1;}
.innerTable {margin:0 auto;}
.table01 {width:947px; margin:10px auto;color:#666;border-collapse:collapse;border-spacing:0;border-left:1px solid #ccc;border-top:1px solid #ccc;background:#fff;}
.table01 a{text-decoration:none;}
.table01 a:hover{color:#ab0000;text-decoration:underline;}
.table01 th , .table01 td{padding:0px 10px;border-right:1px solid #ccc;text-align:center;font-size:12px;text-align:center;height:32px;line-height:32px;border-bottom:1px solid #ccc;}
.table01 th{font-weight:bold;background:#eee;}
.colorRed{color:#b35a48;}
.colorBlue{color:#3f94cf;}
.butList{float:right;}
.butList li{float:left;}
.butList btn{margin:0 5px;color:#fff;background:#F58541;border:#b76e27 solid 1px;height:50px;padding:0 10px; border-radius:3px;cursor:pointer;}
.butList btn:hover{background:#db9a72;}
.padLR5{padding:0 5px;}
</style>

<script type="text/javascript">
var pop;
function selectPrjtUsers(){
	//alert("233333333333333333"+window.parent.document.getElementById("wfRd.wfNo").value);
	if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
		//alert("fff");
		selectPrjtUsers2();
	}else{
		//alert("ddd");
		pop =  $.ligerDialog.open({title:'添加邮件接收人', height: 410, width: 500,url: './PrjtUsr!addMailUser.shtml',
			showMax: true, showToggle: true, showMin: true, isResize: true
		});
	}
}

	function scall() {/*
		var top = 30;
		var scTop = $('body,html').scrollTop();
		if(scTop==null || scTop==''){
			scTop = document.body.scrollTop;	//google chrome取值
		}
		
		if(scTop>=top){
			document.getElementById('toolbar').style.position='fixed';
			document.getElementById('toolbar').style.width='100%';
			document.getElementById('toolbar').style.top='0px';
		}else{
			document.getElementById('toolbar').style.position='';
			document.getElementById('toolbar').style.top='';
		}*/
	}
	window.onscroll = scall;

	$(function() {
		$.post("ligerToolBar!custom.shtml", {
			parm : 'WfRd',
			pageType : '11'
		}, function(data) {
			$("#toolbar").ligerToolBar(data);
		}, "json");
	});

	/**
	var childListGridManager;
	$(function() {
		$("#childListGrid")
				.ligerGrid(
						{
							columns : [
									{
										display : '流程编号',
										name : 'wfNo',
										align : 'center',
										width : 110,
										render : function(row) {
											var innerHtml = "<a href='#' onclick=\"openRelateFlow('"
													+ row.wfNo
													+ "')\">"
													+ row.wfNo + "</a>";
											return innerHtml;
										}
									}, {
										display : '流程名称',
										name : 'wfName',
										align : 'left',
										width : 120
									}, {
										display : '实际开始时间',
										name : 'factSDate',
										align : 'center',
										width : 100
									}, {
										display : '实际完成时间',
										name : 'factEDate',
										align : 'center',
										width : 100
									}, {
										display : '状态',
										name : 'status',
										align : 'center',
										width : 60,
										render : function(row) {
											var innerHtml = "<font color='";
											var v;
											if (row.status == 0) {
												innerHtml += "red";
												v = "待处理";
											} else if (row.status == 1) {
												innerHtml += "blue";
												v = "办理中";
											} else if (row.status == 2) {
												innerHtml += "green";
												v = "已完成";
											} else if (row.status == 3) {
												innerHtml += "red";
												v = "已关闭";
											} else if (row.status == 4) {
												innerHtml += "red";
												v = "已作废";
											}
											innerHtml += "'>" + v + "</font>";
											return innerHtml;
										}
									}, {
										display : '发起人',
										name : 'createName',
										align : 'center',
										width : 90
									}, {
										display : '发起时间',
										name : 'createDate',
										align : 'center',
										width : 100
									} ],
							url : './WfRd!selChildList.shtml?wfRd.wfNo='
									+ $("#wfNo").val(),
							usePager : false,
							width : '700',
							isChecked : f_isChecked,
							onCheckRow : f_onCheckRow,
							onCheckRow : f_onCheckRow,
							onCheckAllRow : f_onCheckAllRow
						});
		childListGridManager = $("#childListGrid").ligerGetGridManager();
	});
	
	

	$(function() {
		$("#navProjectTab").ligerTab({
			onBeforeSelectTabItem : function(tabid) {
				if (tabid == '4') {
					window.frames["quesIframe"].reloadGrid();
				}
				if (tabid == '2') {
					wfdocGridManager.loadData();
				}
			}
		});
	});
	**/
	function f_click(tabid) {
		$("#navProjectTab").ligerTab();
		var navtab = $("#navProjectTab").ligerGetTabManager();
		navtab.selectTabItem(tabid);
	}
	
	function addQuestion(isFromWf) {
		if(isFromWf){
			if ($('#wfNo') && $('#wfNo').val() && $('#wfNo').val() != '') {
				window.parent.parent.f_open('./WfQues!add.shtml?wfQues.taskId='
						+ $('#taskStepId').val() + '&wfQues.wfNo=' + document.getElementById("wfRd.wfNo").value+'&wfQues.prjtNo='+document.getElementById("wfRd.projectNo").value+'&wfQues.prjtNm='+document.getElementById("prjtDef.prjtNm").value+'&isFromWf=' + isFromWf,'新增对话框：WfQues');
				/* DialogMgr.create('新增对话框：WfQues', './WfQues!add.shtml?wfQues.taskId='
						+ $('#taskStepId').val() + '&wfQues.wfNo=' + document.getElementById("wfRd.wfNo").value+'&wfQues.prjtNo='+document.getElementById("wfRd.projectNo").value+'&isFromWf=' + isFromWf); */
			} else {
				$.ligerDialog.warn('没有对应流程,不能添加问题。');
			}
		}else{
			DialogMgr.create('新增对话框：WfQues', './WfQues!add.shtml?isFromWf='+isFromWf);
		}
	}
	
	var DialogMgr = (function() {
		var dialog;
		return {
			create : function(title, url) {
				dialog = $.ligerDialog.open({
					title : title,
					height : 560,
					width : 810,
					url : url,
					showMax : true,
					showToggle : true,
					showMin : true,
					isResize : true
				})

				// }
			},
			close : function() {
				if (dialog) {
					dialog.close();
				}
			}
		};
	})();
	function rejectJob(){		//退回任务
		if(document.getElementById("currentTask.createBy").value==-1){
			alert("系统所发任务不可退回!");
		}else{
			var str=prompt("您确定要退回任务吗？","请写退回原因(字数不得超过50个字)，谢谢");
			var taskId = document.getElementById("currentTask.taskId").value;
			if(str){
				document.getElementById("currentTask.remark").value=str;
				//alert(document.getElementById("currentTask.remark").value);
				document.getElementById("wfRd-form").action="WfRdView!reject.shtml?taskId="+taskId+"&remark="+document.getElementById("currentTask.remark").value;
				document.getElementById("wfRd-form").target="_self";
				document.getElementById("wfRd-form").submit();
			}
		}
		
	}
	function backJob2(wfNo,taskId){			//主板面板收回任务
		if(confirm("您真的要收回任务吗？")){
			//document.getElementById("currentTask.remark").value=str;
			//alert(document.getElementById("currentTask.remark").value);
			document.getElementById("wfRd-form").action="WfRdView!newBackJob.shtml?taskId="+taskId
			document.getElementById("wfRd-form").target="_self";
			document.getElementById("wfRd-form").submit();
		}
	}
	
	
	function importNewCQQues() {
		$.ligerDialog.confirm('确定要导入新记录？', function(type) {
			if (type) {
				if (document.getElementById("wfRd.wfNo") && document.getElementById("wfRd.wfNo").value && document.getElementById("wfRd.wfNo").value != '') {
					var url ='./CQDefect!importNewCQQues.shtml' + '?wfQues.wfNo='+ document.getElementById("wfRd.wfNo").value;
					var dialog = $.ligerDialog.open({ url: url, height: 300,width: 600, 
						buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ] });
				} else {
					$.ligerDialog.warn('没有对应流程,不能添加问题。');
				}
			}
		});
	}
	
	  
	function uplTemp() {
		window.location.href = './include/template/question_template.xls';
	}
	
	
	
	function stopWfinfo(){
		$.ligerDialog.confirm('确定要终止流程？', function(type) {
			if (type) {
				document.getElementById("wfRd-form").action="WfRdView!stopWfinfo.shtml";
				document.getElementById("wfRd-form").target="_self";
				document.getElementById("wfRd-form").submit();
			}
		});
		
	}
	
	
</script>
<script type="text/javascript">
	$(function() {
		//clickShow($("#applicationBtn1"), $("#applicationForm1"),true);
		//clickShow($("#applicationBtn2"), $("#applicationForm2"),true);
		//clickShow($("#applicationBtn3"), $("#applicationForm3"));
		//clickShow($("#applicationBtn4"), $("#applicationForm4"),true);
		//clickShow($("#applicationBtn5"), $("#applicationForm5"));
		//clickShow($("#applicationBtn6"), $("#applicationForm6"));
		//clickShow($("#applicationBtn7"), $("#applicationForm7"));
		//clickShow($("#applicationBtn9"), $("#applicationForm9"));
	})
	
	function clickShow(btn, con, show) {
		if (show) {
			con.show();
			btn.html("点击隐藏");
		} else {
			con.hide(); //默认隐藏
			btn.html("点击展开");
		}
		btn.bind('click', function() {
			if (con.is(":hidden")) {
				con.slideDown();
				$(this).html("点击隐藏");
			} else {
				con.slideUp();
				$(this).html("点击展开");
			}
		})
	}
	var showQuesManager = false;
	function showQues(){
		 if(showQuesManager){
			    document.getElementById("ques").style.display = "none";
				document.getElementById("showQuesBtn").innerHTML= "展开风险";
	    		//window.frames["managerQuesIframe"].reloadGrid();
	    		showQuesManager = false;
		 }else{
			    document.getElementById("ques").style.display = "";
				document.getElementById("showQuesBtn").innerHTML= "收起风险";
				showQuesManager = true;
		 }
	}
</script>
<style type="text/css">
.u32_line {
	background-image: url('./include/images/line2.png');
	width: 912px;
	height: 3px;
}

.l-form {
	MARGIN-LEFT: auto;
	MARGIN-RIGHT: auto;
	width: 700px;
	background-color: #FFFEF8;
	width: 700px;
}

.lbcs {
	font-weight: bold
}

.innerTable th,.innerTable td {
	border-right: 1px solid #9FC2E5;
	border-bottom: 1px solid #9FC2E5;
	background: #fff;
	text-align: left;
	padding: 0 7px;
	
}
.innerTable th{
font-size: 14px;
font-weight: bold;
}
</style>
<script type="text/javascript">
//var showSendEmailWin=null;
//打开发送窗口
function openEmail(){
	var prjtNo=$(document.getElementById("wfRd.projectNo")).val();
		var url = './WfRd!showSendMail.shtml';
		var showSendEmailWin = $.ligerDialog.open({
			title : '发送邮件窗口',
			url : url,
			height : 600,
			width : 900,
			
		});
}
//打开阅知发送窗口
function openEmailForReader(){
	var wfNo=$(document.getElementById("wfRd.wfNo")).val();
		var url = './WfRd!showSendMailForReader.shtml?wfRd.wfNo='+wfNo;
		var showSendEmailWin = $.ligerDialog.open({
			title : '发送邮件窗口',
			url : url,
			height : 550,
			width : 800,
			
		});
}

/* function openEmail(){
	var prjtNo=$(document.getElementById("wfRd.projectNo")).val();
	alert(prjtNo);
	if(prjtNo && (""+prjtNo).length>0){
		  var url = './WfRd!showSendMail.shtml?prjtDef.prjtNo='+prjtNo
		+"&prjtDef.typId=1";//1 研发管理  
		var url = './WfRd!showSendMail.shtml';
		var showSendEmailWin = $.ligerDialog.open({
			title : '发送邮件窗口',
			url : url,
			height : 600,
			width : 900,
			
		});
	}else{
		$.ligerDialog.warn('没有对项目,不能发邮件。');
	}
} */


/**$(function(){
	//如果有 项目编号, 则可以发邮件,显示button
	
	var prjtNo=$(document.getElementById("wfRd.projectNo")).val();
	if(prjtNo && (""+prjtNo).length>0){
		$("#openEmailBtn").show();
	}else{
		$("#openEmailBtn").hide();
	}
	
})**/
</script>
</head>




<body>
<div>
    <input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
    <input type="hidden" id="stepId" name="wfStep.stepId" value="<c:out value="${currentTask.stepId}"/>">
 
    <br>
 <form id="wfRd-form" name="wfRd-form"   method="post" action="WfRdView.shtml" onsubmit="return saveJobCheck();" enctype="multipart/form-data">
<!--  写入可以编辑的fieldId -->
		<%--   <c:forEach items="${fieldContents}" var="step" varStatus="s">
			<c:choose>
				<c:when test="${step.isEdit == '1'}">
					<input type="hidden"  name="fieldContents[<c:out value="${step.count}"/>].fieldId" value="<c:out value="${step.fieldId}"/>">
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</c:forEach>   --%>
		
		<input type="hidden" id="currentTask.stepName" name="currentTask.stepName" value="<c:out value="${currentTask.stepName}"/>">
	<input type="hidden" id="actUri" value="WfRdView">
	<input type="hidden" id="wfRd.wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
	<input type="hidden" id="wfRd.flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>">
	<input type="hidden" id="currentTask.taskId" name="currentTask.taskId" value="<c:out value="${currentTask.taskId}"/>">
	<input type="hidden" id="currentTask.preTaskId" name="currentTask.preTaskId" value="<c:out value="${currentTask.preTaskId}"/>">
	<input type="hidden" id="currentTask.stepId" name="currentTask.stepId" value="<c:out value="${currentTask.stepId}"/>">
	<input type="hidden" id="currentTask.sort" name="currentTask.sort" value="<c:out value="${currentTask.sort}"/>">
	<input type="hidden" id="currentTask.createBy" name="currentTask.createBy" value="<c:out value="${currentTask.createBy}"/>">
	<input type="hidden" id="docNames" name="currentTask.docNames" value="<c:out value="${currentTask.docNames}"/>">
	<input type="hidden" id="checkReSub" name="checkReSub" value="<c:out value="${checkReSub}"/>">
	<input type="hidden" id="agentBy" name="agentBy" value="<c:out value="${agentBy}"/>">
	<input type="hidden" id="isFillQues" value="<c:out value="${currentTask.isFillQues}"/>">
	<input type="hidden" id="isDQAJob" value="<c:out value="${currentTask.isDQAJob}"/>">
	<input type="hidden" id="tips" value="<c:out value="${tips}"/>">
	<input type="hidden" id="curUserId" name="curUserId" value="<c:out value="${user.id}"/>">
	<input type="hidden" id="docFileId" name="wfDoc.docId" value="">
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
	<input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>"/>
	<input type="hidden" id="cateId" />
	
	<input type="hidden" id="currentTask.isLastStep" name="currentTask.isLastStep" value="<c:out value="${currentTask.isLastStep}"/>">
	
<%-- 	<input  id="backTaskssList" name="backTaskssList" value="${backTaskssList}">
	<input  id="signList" name="signList" value="${signList}"> --%>
	
	
	<input type="hidden" id="taskStepId" name="taskStepId" value="<c:out value="${taskStepId}"/>">
	<input type="hidden" id="currentTaskId" name="currentTaskId" value="<c:out value="${currentTaskId}"/>">
	<input type="hidden" id="wfRd.projectNo" name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>">
	<input type="hidden" id="prjtDef.prjtNm" name="prjtDef.prjtNm" value="<c:out value="${prjtDef.prjtNm}"/>">
	<input type="hidden" id="isFromMyTask" name="isFromMyTask" size="30" value="<c:out value="${isFromMyTask}"/>" />
	
	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>" />
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>" />
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>" /> 
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />
	
	<input type="hidden" id="uploadType" name="uploadType" size="30" value="<c:out value="${uploadType}"/>" />
	<input type="hidden" id="wfDocId" name="wfDocId" size="30" value="<c:out value="${wfDocId}"/>" />
	
	<input type="hidden" id="currentTask.remark" name="currentTask.remark" value="">
     <!--  
	进入下一步URL：http://localhost:8080/zrprjt/WfRdView!andrNextStepPage.shtml?taskStepId=<c:out value="${taskStepId}"/>&currentTaskId=<c:out value="${currentTaskId}"/>&wfRd.wfNo=<c:out value="${wfRd.wfNo}" />&logCode=6605fcb9-bcfc-4cac-ab4e-1a0be8afb2ef&checkReSub=<c:out value="${checkReSub}"/>
     -->
			<div class="allBox">

				<c:choose>
					<c:when test="${prjtDef.prjtNm!=null}">
						<div class="allTit" id="台头">
							<p class="borBRed">
								<span><b>项目名称：</b>
								<c:out value="${prjtDef.prjtNm}" /></span> <span><b>所属工作流：</b>
								<c:out value="${cfg.flowName}" /></span> <span><b>发起人：</b>
								<c:if test="${wfRd.createBy==-1}">
								系统
							</c:if> <c:if test="${wfRd.createBy!=-1}">
										<c:out value="${wfRd.createName}" />
									</c:if></span> <span><b>状态：</b>
								<c:choose>
										<c:when test="${currentTask.status==0}">
										待处理
										</c:when>
										<c:when test="${currentTask.status==1}">
										办理中
									</c:when>
										<c:when test="${currentTask.status==2}">
										已完成
									</c:when>
										<c:when test="${currentTask.status==3}">
										已关闭
									</c:when>
										<c:when test="${currentTask.status==4}">
										已作废
									</c:when>
									</c:choose></span> <span><b>流程编号：</b>
								<c:out value="${wfRd.wfNo}" /></span>
							</p>
						</div>
					</c:when>
					<c:otherwise>

						<div class="allTit" id="台头">
							<p class="borBRed">
								<span><b>流程编号：</b>
								<c:out value="${wfRd.wfNo}" /></span>
								<span><b>工作流名称：</b>
								<c:out value="${wfRd.wfName}" /></span> 
								 <span><b>发起人：</b>
								<c:if test="${wfRd.createBy==-1}">
								系统
							</c:if> <c:if test="${wfRd.createBy!=-1}">
										<c:out value="${wfRd.createName}" />
									</c:if></span> <span><b>状态：</b>
								<c:choose>
										<c:when test="${currentTask.status==0}">
										待处理
									</c:when>
										<c:when test="${currentTask.status==1}">
										办理中
									</c:when>
										<c:when test="${currentTask.status==2}">
										已完成
									</c:when>
										<c:when test="${currentTask.status==3}">
										已关闭
									</c:when>
										<c:when test="${currentTask.status==4}">
										已作废
									</c:when>
										<c:when test="${currentTask.status==5}">
										已收回
									</c:when>
										<c:when test="${currentTask.status==6}">
										已退回
									</c:when>
									</c:choose></span> </br>
									<span><b>工作流描述：</b>
								<c:out value="${wfRd.wfDesc}" /></span>
							</p>
						</div>

					</c:otherwise>
				</c:choose>








				<div class="area01" id="流程进度">
					<div class="titBox">
						<h3>流程进度</h3>
					</div>

					<div class="area01Con">
						<div class="processPicture">
							<c:forEach items="${imgStepList}" var="step" varStatus="s">
								<div class="processSection">
									<c:choose>
										<c:when test="${step.isCurrent==1}">
											<img src="./include/img/workflow/step02.png" />
										</c:when>
										<c:when test="${step.isCurrent!=1 && !empty step.taskTime}">
											<img src="./include/img/workflow/step01.png" />
										</c:when>
										<c:when test="${step.isCurrent!=1 && empty step.taskTime}">
											<img src="./include/img/workflow/step03.png" />
										</c:when>
									</c:choose>
									<div class="PSTit">
										<c:out value="${step.stepName}" />
										<br />
										<fmt:formatDate pattern="yyyy-MM-dd" value="${step.taskTime}"
											type="both" />
									</div>
								</div>
							</c:forEach>

							<c:if test="${fn:length(imgStepList)>8}">
								<c:set var="imgStepListSize" value="${fn:length(imgStepList)}" />
								<c:forEach begin="0" end="${24-imgStepListSize}">
									<div class="processSection"></div>
								</c:forEach>
							</c:if>

						</div>

						<table class="table01">
						<p align=center><font size="3" color="black" ><c:out value="${cfg.flowName}" /></font></p>
							<thead>
								<tr>
									<th>步骤次序</th>
									<th>步骤名称</th>
									<th>分发人</th>
									<th>分发时间</th>
									<th>主办人</th>
									<th>完成时间</th>
									<th>状态/操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${taskList}" var="wfRdTask">
									<tr>
										<td>第<c:out value="${wfRdTask.sort}" />步
										</td>
										<td style="text-align: left;"><c:out
												value="${wfRdTask.stepName}" />
											<c:if test="${wfRdTask.taskType==2}">(协办)</c:if></td>
										
										<td><c:out value="${wfRdTask.createrName}" /></td>
										<td><fmt:formatDate type="both"
												value="${wfRdTask.createDate}" /></td>
										<td><c:out value="${wfRdTask.acceptName}" /> <c:if
												test="${wfRdTask.acptRoleName!=null}">--<c:out
													value="${wfRdTask.acptRoleName}" />
											</c:if></td>
										<td><fmt:formatDate type="both"
												value="${wfRdTask.endDate}" /></td>
										<td><c:choose>
												<c:when test="${wfRdTask.status==0}">
													<font color="gray">待接收</font>
												</c:when>
												<c:when test="${wfRdTask.status==1}">
													<font color="blue">办理中</font>
												</c:when>
												<c:when test="${wfRdTask.status==2}">
													<font color="green">已完成</font>
												</c:when>
												<c:when test="${wfRdTask.status==3}">
													<!-- <font color="red">已关闭</font>    -->
													<font color="red">已完成</font>
												</c:when>
												<c:when test="${wfRdTask.status==4}">
													<font color="red">已作废</font>
												</c:when>
												<c:when test="${wfRdTask.status==5}">
													<font color="green">已收回</font>
												</c:when>
												<c:when test="${wfRdTask.status==6}">
													<font color="green">已退回</font>
												</c:when>
											</c:choose> <c:if
												test="${wfRdTask.status <= 1 && wfRdTask.acceptBy!= sessionScope.SYUSR.id}">
												<!--  
							     <input type="button" value=" 催办" class="wfbigbtn2"
							      onclick="pushMail2('<c:out value="${wfRd.wfNo}"/>',<c:out value="${wfRdTask.taskId}"/>);" />
							     -->
												<a href="javascript:pushMail2('<c:out value="${wfRd.wfNo}"/>',<c:out value="${wfRdTask.taskId}"/>);"
													class="colorBlue padLR5">催办</a>
											</c:if>
											  <c:if test="${wfRdTask.status ==0 && wfRdTask.taskType!=2 && wfRdTask.createBy == sessionScope.SYUSR.id}">
											<a href="javascript:backJob2('<c:out value="${wfRd.wfNo}"/>',<c:out value="${wfRdTask.taskId}"/>);"
													class="colorBlue padLR5">收回</a>
											</c:if>   
											 <!--  
									    <input type="button" value=" 接收" class="wfbigbtn2" 
									    onclick="pushMail(<c:out value="${wfRd.wfNo}"/>,<c:out value="${wfRdTask.taskId}"/>);" />
										<input type="hidden" id="currentTask.taskId" name="currentTask.taskId" value="<c:out value="${unAcceptTask.taskId}"/>">
									  --> <!-- 
									<c:if test="${currentTask.taskId==wfRdTask.taskId}">
							          <c:if test="${currentTask.status==0}">
									    <a href="javascript:acceptJob();" class="colorBlue padLR5">接收</a>
							          </c:if> 
								   </c:if> 
								   
								  --></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 退回原因 --> <c:if test="${!empty backTaskssList}">
										<p style="font-size: 16px; font-family: serif;"
											title="所转交的任务被退回来了">
											<font color="red">退回原因:
											<c:forEach items="${backTaskssList}" var="back"
												varStatus="s">
												<c:out value="${s.count}" />.<c:out value="${back.remark}" />[<c:out
													value="${back.createrName}" />]；&nbsp;
											</c:forEach></font>
										</p>
										<br>
									</c:if> <!-- 退回原因 --> 
					</div>
				</div>
				<!-- 当是问题转风险审批流程时候显示问题转风险信息 -->
				 <c:if test="${wfRd.flowId == 55||wfRd.flowId == 57}"> 
				 <c:if test="${currentTask.stepName == '部门经理审核'||currentTask.stepName == '责任工程师填写申请'||currentTask.stepName == 'DQA审核'}"> 
				<div class="area01" id="问题转风险申请信息">
					<div class="titBox">
					<h3>问题转风险申请信息</h3>
					</div>
					<div>
					 <jsp:include flush="true" page="FormJSP/managerQuesGoRiskTaskforWfRD.jsp"></jsp:include> 
					</div>
				</div>
				 </c:if> 
				 </c:if> 

<input type="hidden"  id="flagDcc" value="<c:out value="${flagDcc}"/>" />
				<div class="area01" id="填写表单">
					<c:if test="${!empty includeJspUri}">
						<div class="titBox">


							<h3>填写表单</h3>
							<ul class="butonList">
								<li><a name="showWfFormsBtn" id="showWfFormsBtn"
									href="javascript:showFroms()">收起表单</a></li>

								<li><a name="#applicationBtn5" id="applicationBtn5"
									href="javascript:printForm()">打印表单</a></li>
							</ul>
						</div>


						<!--  <div class="area01Con">  -->
						<div id="wfForms">
							<!--  <div class="listTable clearfix" style="margin: 10px 20px;">  
							<table cellpadding=0 cellspacing=0.5 width=95% style="background: #9FC2E5;" class="innerTable" align=center>-->
							<!-- <tr>判断是否为供应商评审流程，是就导入评论页面
									<td colspan="6"> -->
							<tr>
								<td>
									
									<c:choose>
								       <c:when test="${wfRd.flowId==53}"> <!-- SAP主数据维护流程（53测试环境55生产环境53） -->
								             	<c:if test="${currentTask.stepId==350}">
														<!-- 财务填单表 -->
														<jsp:include flush="true" page="FormJSP/A30Step6.jsp"></jsp:include>
													</c:if>
													<c:if test="${currentTask.stepId==351}">
														<!-- 计调填单表 -->
														<jsp:include flush="true" page="FormJSP/A30Step7.jsp"></jsp:include>
													</c:if>
													<c:if test="${currentTask.stepId==352}">
														<!-- 采购填单表 -->
														<jsp:include flush="true" page="FormJSP/A30Step8.jsp"></jsp:include>
													</c:if>
								             		<c:if test="${currentTask.stepId==362}">
														<!-- 采购转仓库填单表 -->
														<jsp:include flush="true" page="FormJSP/A30Step9.jsp"></jsp:include>
													</c:if>
													<c:if test="${currentTask.stepId==346||currentTask.stepId==347||currentTask.stepId==348||currentTask.stepId==349||currentTask.stepId==353||currentTask.stepId==354}">
								             		<jsp:include flush="true" page="${includeJspUri}"></jsp:include>
								             		</c:if>
								        </c:when>
								       <c:when test="${wfRd.flowId==54}"><!-- DCC其他变更流程 -->
								       <jsp:include flush="true" page="FormJSP/A34.jsp"></jsp:include>
								       
										</c:when>
								       <c:otherwise><!-- 其他流程嵌套页面 --> 
								              <jsp:include flush="true" page="${includeJspUri}"></jsp:include>  
								       </c:otherwise> 
								       
								</c:choose> 
								 
									</td>
									</tr>
									</div>
					</c:if>
					<!-- </td>
								</tr> 
							</table>-->
					<br>
				</div>
			

												<!-- 会签记录显示 -->
				<div class="area01" id = "会签记录">
            <div class="titBox">
                <h3>会签与意见</h3>
         	</div>
         	
         	<!-- 会签 -->
											<c:if test="${wfRd.status==1}">
											
												<table width="100%" border="0" cellspacing=0 cellpadding=0>
													<tr>
														<td width="19%">&nbsp;</td>
														<td><IMG align=top
															src="./include/img/workflow/green_arrow.gif"><SPAN
															class=big3> <B>会签意见区</B></SPAN> (如有意见请在此填写)</td>
													</tr>
												</table>
												<table id="OwnershipStructure" width="520"
													style="BORDER-COLLAPSE: collapse" class=small border=1
													cellspacing=0 borderColor=#0066CC cellpadding=1
													align=center>
													<tr>
														<td><textarea rows="6" cols="100"
																name="wfRdSign.signText"><c:out
																	value="${wfRdSign.signText}" /></textarea></td>
													</tr>
												</table>
												
												<!-- 总会签 -->
											</c:if>
											<c:if test="${wfRd.status==2}">
												<c:if test="${!empty signList}">
													<!-- head -->
													<div>
														<table id="quesTB" width="100%" border="0" cellspacing=0
															cellpadding=0>
															<tr>
																<td width="3%">&nbsp;</td>
																<td><IMG align=top
																	src="./include/img/workflow/green_arrow.gif"><SPAN
																	class=big3> <B>意见列表</B></SPAN>&nbsp;&nbsp;<a href="#"
																	id="applicationBtn9"></a></td>
															</tr>
														</table>
													</div>

													<div id="applicationForm9" class="listTable clearfix"
														style="margin: 10px 20px;">

														<table cellpadding=0 cellspacing=0 width=90%
															style="background: #9FC2E5;" class="innerTable"
															align=center>
															<tr>
																<td><c:forEach items="${signList}" var="sign"
																		varStatus="s">
																		<li>
																			<div class="ico"></div>
																			<p class="p2">
																				<c:out value="${sign.signText}" escapeXml="false" />
																			</p> <span> <c:out value="${sign.userName}" />| <fmt:formatDate
																					pattern="yyyy-MM-dd" value="${sign.createDate}"
																					type="both" />
																		</span>
																		</li>
																	</c:forEach></td>
															</tr>
														</table>
													</div>
													<br>
												</c:if>
											</c:if>
											<!-- 会签 --> 
         	
         	
         	
         	
         	<div class="area01Con">
			     <c:if test="${!empty signList}">
													<table style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="90%" align=center>
													 <!--  <tr class=TableHeader align="left" >
													    <td>会签与意见</td>
													 </tr> -->
													  <c:forEach items="${signList}" var="sign" varStatus="s">
													  	<tr <c:if test="${s.count%2!=1}">class=TableContent</c:if> <c:if test="${s.count%2==1}">class=TableData</c:if> height="50">
														    <td>第<c:out value="${s.count}"/>步【会签】 
														    	<c:out value="${sign.stepName}"/> 
														    	<U style="CURSOR: hand" title=部门：<c:out value="${sign.deptName}"/>><c:out value="${sign.userName}"/></U>：<br/>
														      	<c:out value="${sign.signText}"/>&nbsp;&nbsp;&nbsp;
														     	<I><fmt:formatDate value="${sign.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></I><br/>
														    </td>
														</tr>
													  </c:forEach>
													</table>
													<br/>
													</c:if>
            </div>
        </div>
													
													<!-- 会签记录显示 -->

			<c:if test="${wfRd.flowId == 41}">
	        <div class="area01" id = "查看风险">
	        	  <div class="titBox">
	                <h3>查看风险</h3>
	                <ul class="butonList">
	                    <li><a name="showQuesBtn" id="showQuesBtn" href="javascript:showQues()">展开风险</a></li>
	                </ul>
	            </div>
	        	 <div class="area01Con">
				     <div id="ques" style="display:none">
						<div  class="listTable clearfix" style="margin: 10px 20px;">
							<table cellpadding=0 cellspacing=0.5 width=98% style="background: #9FC2E5;" class="innerTable" align=center>
								<tr>
									<td colspan="6">
											<div tabid="4" title="风险查看" style="width: 100%; height: 620px;">
												<iframe id="managerQuesIframe" name="managerQuesIframe" frameborder="0"  
												style="width: 100%; height: 100%" 
												src="./WfRisk!wfRdRiskListUI.shtml?wfNo=<c:out value="${wfRd.wfNo}"/>"></iframe>
										</div>
									</td>
								</tr>
							</table>
						</div>
			         </div>
	            </div>
	        </div>
			</c:if>
			
			
		<%-- <c:if test="${showDoc}"> --%>
	     <div class="area01" id = "上传附件">
            <div class="titBox">
                <h3>上传附件</h3>
         	</div>
         	<div class="area01Con">
			     <div id="doc">
					<div  class="listTable clearfix" style="margin: 10px 20px;">
						<table cellpadding=0 cellspacing=0.5 width=100% style="background: #9FC2E5;" class="innerTable" align=center>
							<tr>
								<td colspan="6">
									<div tabid="4" title="上传附件" style="width: 100%; height: 250px;">
										<iframe id="uploadIframe" name="uploadIframe" frameborder="0"  
												style="width: 100%; height: 100%;overflow-x : hidden" 
												src="./WfRdView!viewDocCate.shtml?currentTask.status=${currentTask.status}&wfRd.wfNo=<c:out value="${wfRd.wfNo}"/>&currentTaskId=<c:out value="${currentTaskId}"/>&taskStepId=<c:out value="${taskStepId}"/>"></iframe>
									</div>
								</td>
							</tr>
					  </table>
					</div>
		         </div>
            </div>
        </div>
		<%-- </c:if>
	 --%>
		
	<c:if test="${wfRd.needQues==1}">
		 <div class="area01" id = "问题列表">
	            <div class="titBox">
	                <h3>提出问题</h3>&nbsp;&nbsp;
	                <input id="uplTempBtn" class="wfbigbtn2" type="button" onclick="javascript:uplTemp()" value="下载问题模板"></input>
	                <ul class="butonList">
	                    <li><a href="javascript:addQuestion(1)">我要提问题</a></li>
	                    <li><a href="javascript:importNewQues()">批量导入问题</a></li>
	                    <li><a href="javascript:importNewCQQues()">批量导入CQ问题</a></li>
	                    <li><a name="showQuesListBtn" id="showQuesListBtn" href="javascript:showQuesList()">展开问题列表</a></li>
	                </ul>
	            </div>
	            <div class="area01Con">
				     <div id="quesList" style="display:none">
						<div  class="listTable clearfix" style="margin: 10px 20px;">
							<table cellpadding=0 cellspacing=0.5 width=95% style="background: #9FC2E5;" class="innerTable" align=center>
								<tr>
									<td colspan="6"><c:if test="${cfg.hasQuesMoudle==0}">
											<div tabid="4" title="问题列表" style="width: 100%; height: 620px;">
												<iframe id="quesIframe" name="quesIframe" frameborder="0" name="showmessage" style="width: 100%; 
												height: 100%" src="./WfQues!taskList.shtml?prjtNo=<c:out value="${wfRd.projectNo}"/>&wfNo=<c:out value="${wfRd.wfNo}"/>"></iframe>
											</div>
										</c:if></td>
								</tr>
							</table>
						</div>
			         </div>
	            </div>
	        </div>
	    </c:if>
	    
	    
	    <c:if test="${needSpecifyNext}">
		 <div class="area01" id = "确定下一步">
		<%--   <input  id="Sort111" name="wfStepSort" value="<c:out value="${step.Sort}" />">   --%>
		        <div class="titBox"><h3>确定下一步</h3></div>
	            <div class="area01Con">
						<div  class="listTable clearfix" >
							
								<!-- 转交界面 -->
							      <input  type="hidden" id="taskCount"value="<c:out value="${taskCount}"/>">
										
								  <div id ="wfdocgrid"  class="listTable clearfix" >
									<table cellpadding=0 cellspacing=0.5 width=100% style="background:#9FC2E5;" class="innerTable" align=center>
										<tr bgcolor="#EFF0F2">
											<th width="25%">下一步骤（<font color="red">勾选需转交步骤</font>）
											</th>
											<th width="15%">主办人（<font
												color="red">不能为空</font>）</td>
											</th>
											<th width="15%">协办人（<font
												color="red">协办人可以不选</font>）</td>
											</th>
											<th width="15%">计划完成时间</th>
											<c:if   test="${wfRd.flowId==40||wfRd.flowId==42}">
											<th width="15%">选择已定人员</th>
											</c:if>
										</tr>
					
										<!-- c:set var="n" value="0" / -->
										<c:set var="n" value="0"/>
										<c:set var="i" value="0" />
										
										<c:forEach items="${nextSteps}" var="step" varStatus="vs">
											<tr bgcolor="#FFFFFF" height="35">
												<!-- 下一步骤 --> 
												<td>
													<input type="Checkbox" id="nextSteps[<c:out value="${n}"/>].stepId" name="nextSteps[<c:out value="${n}"/>].stepId" 
														value="<c:out value="${step.stepId}"/>" <c:if test="${vs.last==true && vs.count==1 && currentTask.isLastStep!=1}">checked</c:if>
														onclick="selectStep('<c:out value="${n}"/>','<c:out value="${step.stepType}"/>')">
													<c:if test="${currentTask.sort>step.sort}">
														<font style="color: red">退回至：</font>
													</c:if> <font style="color: blue;"> <c:out
															value="${step.stepName}" />&nbsp;&nbsp;
												</font> <c:if test="${step.stepType==1}">&nbsp;</c:if> <c:if
														test="${step.stepType==2}">(分支)</c:if> <c:if
														test="${step.stepType==3}">(并发)</c:if>
											    </td>
											    <!-- 下一步骤 --> 
					
					                            <!-- 主办人 -->  
												<td >
													 <input type="hidden"  flag ="people" id="userId<c:out value="${i}"/>"value="<c:out value="${step.idText}"/>"> 
													 <b id="task<c:out value="${i}"/>"><c:out value="${step.taskText}" escapeXml="false" /></b>
													 <input type="text"  autoComplete="Off"
														id="userName<c:out value="${i}"/>"
														size="30"
														value="<c:out value="${step.nameText}"/>"
														onkeydown="enterTips(<c:out value="${i}"/>)"
														onkeyup="showtips2(<c:out value="${i}"/>);if(event.keyCode==27)c();">
														<input type="button" value="选择成员" onclick="selectNextStepUsers(this,<c:out value="${i}"/>);"></input>
													<br><select id="sel<c:out value="${i}"/>" style="width: 140px; position: absolute; z-index: 1000; display: none"
														 onkeydown="if(event.keyCode==13)rv(<c:out value="${i}"/>)"></select> 
														 
													<c:set var="i" value="${i+1}" /> 
												</td> 
												<!-- 主办人 -->


												<!-- 协办人 -->  
												<td id="xtd<c:out value="${n}"/>">
													 <input type="hidden"   id="userId<c:out value="${i}"/>"value="<c:out value="${step.idText2}"/>"> 
													 <b id="task<c:out value="${i}"/>"><c:out value="${step.taskText2}" escapeXml="false" /></b>
													 <input type="text"  autoComplete="Off" readonly="true" 
														id="userName<c:out value="${i}"/>"
														size="38"
														value="<c:out value="${step.nameText2}"/>"
														onkeydown="enterTips(<c:out value="${i}"/>)"
														onkeyup="showtips2(<c:out value="${i}"/>);if(event.keyCode==27)c();">
														<input type="button" value="选择成员" onclick="selectNextStepAssistpUsers(this,<c:out value="${i}"/>);"></input>
													<br><select id="sel<c:out value="${i}"/>" style="width: 140px; position: absolute; z-index: 1000; display: none"
														 onkeydown="if(event.keyCode==13)rv(<c:out value="${i}"/>)"></select> 
														 
													<c:set var="i" value="${i+1}" /> 
												</td> 
												<!-- 协办人 -->  
												
					                            <!-- 计划完成时间 -->  
												<td>
												<fmt:formatDate value="${currentDate}" var="planEDate" type="date"/>
													<input type="text"
																name="nextSteps[<c:out value="${i}"/>].taskTime"
																id="date<c:out value="${i}"/>"
																style="width: 100px; height: 17px;" readOnly
																value="<fmt:formatDate value="${currentDate}"/>"
																onClick="WdatePicker({el:'date<c:out value="${i}"/>'});" />
												</td>
												 <!-- 计划完成时间 -->  
												<!-- 判断只有海外流程的流程ID才显示选择项目人员 -->  
												<c:if   test="${wfRd.flowId==40||wfRd.flowId==42}">
												<td>
													<input type="button" value="选择项目组成员" onclick="selectPrjtUsers(this);"></input>
												</td>
												</c:if>
												<!-- 判断只有海外流程的流程ID才显示选择项目人员 -->  
											</tr>
											<c:set var="n" value="${n+1}"/>
										</c:forEach>
										<input type="hidden" id="n" value="<c:out value="${n}"/>">
										<input type="hidden" id="n" value="<c:out value="${n}"/>">
									</table>
									
									</div>
									<br>
								
	            </div>
	        </div>
	        
	 </c:if>
 

 
 
 
 
 	<!-- 邮件阅知功能 -->
				<div class="area01" id = "邮件阅知功能">
            <div class="titBox">
                <h3>邮件阅知</h3>
         	</div>
         	<div class="area01Con">
			     <div id="eform" style="margin-left: 15px; margin-right: 15px; margin-top: 15px;">
			<input type="hidden" id="usrIds" name="usrIds" value="<c:out value="${usrIds}"/>" />
			<input type="hidden" id="prjtNo" name="prjtDef.prjtNo" size="30" value="<c:out value="${prjtDef.prjtNo}"/>" />
			<input type="hidden" id="typId" name="prjtDef.typId" size="30" value="<c:out value="${prjtDef.typId}"/>"/>
			
			<table width="90%">
					<tr bgcolor="#ffffff">
						<td width="20%" align="center">提示：</td>
						<td><font color="red">主办人及协办人不用阅知</font>(系统会自动发邮件)</td>
						</br>
					</tr>
					<tr>
						<td width="20%"align="center">收件人:</td>
						<td bgcolor="#ffffff" width="80%" height="24"><input
							type="text" id="usrString" name="usrString"
							value="<c:out value="${usrString}"/>"
							style="width: 650px; height: 18px" readonly="true" /> <input
							type="button" value="选择接收人" onclick="selectPrjtUsers();"></input></td>
					</tr>
					
					</tr>
				
			</table>
	</div>
            </div>
        </div>
													
													<!-- 会签记录显示 -->
 
 
 
 
 
 
	
 	
<table width="100%"><tr><td>
<div class="wfbigbtn1">
    <input id="openEmailBtn" class="wfbigbtn2" type="button" onclick="javascript:openEmail()" value="发送邮件"></input>
   <input id="openEmailBtn1" class="wfbigbtn2" type="button" onclick="javascript:openEmailForReader()" value="邮件阅知"></input>
</div>

	   </td><td align="right">
	   
	   <!-- 
	   <input type="button" class="wfbigbtn2"  value="缩小" onclick="nextStepPage();" />
	    -->
		<c:if test="${currentTask.status<=1}">
		
		<c:choose>
				<c:when test="${needSpecifyNext}">
	          <input type="button"  id = "nextStepPageBtn"  class="wfbigbtn"  value="转交到下一步" onclick="nextStepPage();" />
	   			</c:when>
	   			<c:otherwise>
	          <input type="button"  id = "nextStepPageBtn"  class="wfbigbtn"  value="完成任务" onclick="nextStepPageForSuss();" />
	          </c:otherwise>
	    </c:choose>
	           <input type="button"  id = "saveJobBtn"  class="wfbigbtn"  value="暂存" onclick="saveJob();" />
	           <c:if test="${currentTask.createBy!=-1}">
	           
	            <input type="button"  id = "rejectJobBtn"  class="wfbigbtn"  value="退回" onclick="rejectJob();" />
	            </c:if>
	            <input type="button"  id = "setAgentByBtn"  class="wfbigbtn"  value="设置代办人" onclick="setAgentBy();" />
	            <c:forEach items="${taskList}" var="wfRdTask">
	   				 <c:if test="${wfRdTask.sort==1 && wfRdTask.status <= 1 && wfRdTask.acceptBy == sessionScope.SYUSR.id}">
	            <input type="button"  id = "stopWfinfoBtn"  class="wfbigbtn"  value="终止流程" onclick="stopWfinfo();" />
	            	</c:if>
	            </c:forEach>
	    		</c:if>
	    </td></tr></table>
	</div>
	<!-- context -->


	<%@ include file="userpop.jsp"%>
  </form>
  <br>
  <br>
  <%-- <%@ include file="/include/inc/popupforFlow.jsp"%> --%>
<%@ include file="./impdlgForWorkFlow.jsp"%>
</div>
</body>
</html>