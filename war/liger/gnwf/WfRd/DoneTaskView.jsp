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

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/js/gnwf/wfRd.js"></script>
<script type="text/javascript" src="./include/js/gnwf/WfRd_special.js"></script>

<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>

<style type="text/css">
#box2,#box3,#box4 {
	padding: 10px;
	border: 1px solid #0066CC;
}

body {
	height: 100%;
}

html {
	height: 100%;
}

p {
	padding: 0;
	margin: 0;
}

input {
	padding: 1;
	margin: 0;
}

.lcjd {
	font-size: 12px;
}

.lcjd th {
	height: 24px;
	text-align: left;
	color: #fff;
}

.lcjd th,.lcjd td {
	vertical-align: middle;
	padding: 5px;
}

.lcjd td {
	border-right: 1px solid #5D99DA;
	border-bottom: 1px solid #5D99DA;
}

.lcjd td .detai {
	display: none;
	position: absolute;
	bottom: 25px;
	left: 0;
	background: #e8e3e3;
	padding: 3px;
}

.lcjd td .detail {
	border: 1px solid #bbb;
	background: #fff;
	padding: 10px;
	line-height: 23px;
	text-align: left;
	color: #5b5a59;
	position: relative;
}

.lcjd td .detail .arr {
	position: absolute;
	z-index: 99;
	bottom: -8px;
	*bottom: -4px;
	left: 20px;
}

.lcjd .stitle td {
	background: #d7d7d7;
	font-weight: bold;
	height: 20px;
}

.lcjd .hostname {
	line-height: 21px;
	padding-right: 10px;
}

.lcjd .lcbtn {
	height: 21px;
	line-height: 21px;
	background: url(./include/img/workflow/lcbtn.png) no-repeat;
	width: 68px;
	text-align: center;
	position: relative;
	white-space: nowrap;
}

.lcjd .hover {
	background-position: 0 -21px;
}

.lcjd .lcbtn input {
	border: none;
	height: 21px;
	line-height: 21px;
	background: none;
	color: #fff;
	cursor: pointer;
}

ul {
	padding: 0;
	margin: 0;
	list-style: none;
}

.title li {
	float: left;
	height: 35px;
	padding: 0 20px;
	cursor: pointer;
}

.title {
	float: left;
	height: 35px;
	line-height: 35px;
	background: url(./include/img/workflow/bg.png) repeat-x;
	font: bold 14px/35px '宋体';
	color: #fff;
	border-radius: 4px;
	border-left: 1px solid #a1a1a1;
	border-right: 1px solid #a1a1a1;
}

.title li.cur {
	background: url(./include/img/workflow/bghover.png) repeat-x;
	color: #454545;
}

.content {
	clear: both;
}

.content li {
	display: none;
}
</style>

<script type="text/javascript">
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

	//附件上传--新
	function preUploadFile(prjtNo, prjtNm, wfId, wfNm, usrId, usrNm, wfNo,
			taskId, cateId) {
		//var cateId = getRadioValue('docCate');
		var httpUrl = 'http://gnfile.gionee.com:28080/gnfs/GnFileService!upload.shtml?'
				+ 'syId=1&syNm=PDM&usrId='
				+ usrId
				+ '&usrNm='
				+ usrNm
				+ '&diyFolder='
				+ "aaa"
				+ '&callBackUrl='
				+ "http://192.168.119.136:8080/zrprjt/WfDoc!addfile.shtml"
				+ '&tempParams='
				+ "wfId:"
				+ wfId
				+ ",wfNm:"
				+ wfNm
				+ ",wfNo:"
				+ wfNo;

		httpUrl = makeUrl(httpUrl, ",prjtNo:", prjtNo);
		httpUrl = makeUrl(httpUrl, ",taskId:", taskId);
		httpUrl = makeUrl(httpUrl, ",cateId:", cateId);
		//httpUrl = makeUrl(httpUrl,"&prjtNm=",prjtNm);

		httpUrl = encodeURI(httpUrl);
		var uploadDialog = $.ligerDialog.open({
			title : '上传附件',
			height : 450,
			width : 470,
			url : httpUrl,
			showToggle : true,
			showMin : true,
			isResize : true
		});

		$(".l-dialog-close").click(function() {
			var grid = $("#wfdocgrid").ligerGetGridManager().loadData();
			if (grid) {
				grid.loadData();
			}
		});
	}
	
	
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

	function f_click(tabid) {
		$("#navProjectTab").ligerTab();
		var navtab = $("#navProjectTab").ligerGetTabManager();
		navtab.selectTabItem(tabid);
	}
	
	function addQuestion( isFromWf ) {
		if(isFromWf){
			if ($('#wfNo') && $('#wfNo').val() && $('#wfNo').val() != '') {
				DialogMgr.create('新增对话框：WfQues', './WfQues!add.shtml?wfQues.taskId='
						+ $('#taskStepId').val() + '&wfQues.wfNo=' + document.getElementById("wfRd.wfNo").value+ '&isFromWf=' + isFromWf);
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
				// if(window.parent.createDialog) {
				// dialog = window.parent.createDialog(title,url);
				// } else {
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
	
	
	
	function impQues() {
		$.ligerDialog.confirm('确定要导入记录？', function(type) {
			if (type) {
				if (document.getElementById("wfRd.wfNo") && document.getElementById("wfRd.wfNo").value && document.getElementById("wfRd.wfNo").value != '') {
					$.ligerDialog.open({
						title : '对话框：文件导入',
						height : 160,
						width : 500,
						url : './WfQues!imp.shtml' + '?wfQues.wfNo='
								+ document.getElementById("wfRd.wfNo").value
					});
				} else {
					$.ligerDialog.warn('没有对应流程,不能添加问题。');
				}
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
		clickShow($("#applicationBtn5"), $("#applicationForm5"));
		//clickShow($("#applicationBtn6"), $("#applicationForm6"));
		clickShow($("#applicationBtn7"), $("#applicationForm7"));
		clickShow($("#applicationBtn9"), $("#applicationForm9"));
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
</head>
<body>
	<!-- context -->
	<div>
		<input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
		<input type="hidden" id="stepId" name="wfStep.stepId" value="<c:out value="${currentTask.stepId}"/>">

		<!-- 流程信息 -->
		<div>
			<form id="wfRd-form" name="wfRd-form" method="post" action="WfRdView.shtml" onsubmit="return saveJobCheck();" enctype="multipart/form-data">
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
				
				<input type="hidden" id="taskStepId" name="taskStepId" value="<c:out value="${taskStepId}"/>">
				
				
				
				
				

		<!-- 流程进度 -->
		<div>
			<!-- head -->
			<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="13%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>流程进度</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn2"></a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->

			<div id="applicationForm2" style="margin: 10px 20px;">

				<table cellpadding=0  border=0 cellspacing=0 width=100% align=center>
					<tr style="width: 30">
						<td width="15%" style="border-right: 0 solid black;"></td>
						<td nowrap="nowrap" colspan="5" id="process" align="center">
						<c:forEach items="${imgStepList}" var="step" varStatus="s">
								<c:if test="${s.count==1}">
									<DIV <c:choose>
											<c:when test="${step.isCurrent==1}">class="node current"</c:when>
											<c:when test="${step.isCurrent!=1 && !empty step.taskTime}">class="node ready"</c:when>
											<c:when test="${step.isCurrent!=1 && empty step.taskTime}">class="node gray"</c:when>
										 </c:choose>>
										<UL>
											<SLI class="tx1">&nbsp;</SLI>
											<SLI class="tx2"> <c:out value="${step.stepName}" /></SLI>
											<SLI class="tx3" id="track_time_0"> <br>
											<fmt:formatDate pattern="yyyy-MM-dd" value="${step.taskTime}" type="both" /> </SLI>
										</UL>
									</DIV>
								</c:if>
								<c:if test="${s.count!=1}">
									<DIV <c:choose>
											<c:when test="${step.isCurrent==1 || !empty step.taskTime}">class="proce ready"</c:when>
											<c:when test="${step.isCurrent!=1 || empty step.taskTime}">class="proce gray"</c:when>
										 </c:choose>>
										<UL>
											<SLI class="tx1">&nbsp;</SLI>
										</UL>
									</DIV>
									<DIV <c:choose>
											<c:when test="${step.isCurrent==1}">class="node current"</c:when>
											<c:when test="${step.isCurrent!=1 && !empty step.taskTime}">class="node ready"</c:when>
											<c:when test="${step.isCurrent!=1 && empty step.taskTime}">class="node gray"</c:when>
										 </c:choose>>
										<UL>
											<SLI class="tx1">&nbsp;</SLI>
											<SLI class="tx2"> <c:out value="${step.stepName}" /></SLI>
											<SLI class="tx3" id="track_time_1"> <br>
											<fmt:formatDate pattern="yyyy-MM-dd" value="${step.taskTime}" type="both" /> </SLI>
										</UL>
									</DIV>
								</c:if>
							</c:forEach>
						</td>
					</tr>
				</table>
			</div>
			
			
			<div id ="wfRdTaskGrid"  class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=70% style="background:#9FC2E5;" class="innerTable" align=center>
					<tr>
						<th style="text-align:center;" width="15%" >步骤次序</th>
					    <th   style="text-align:center;" width="15%">步骤名称</th>
					 	<th style="text-align:center;" width="15%">主办人</th>
					 	<th style="text-align:center;" width="15%">接收时间</th>
						<th  style="text-align:center;" width="15%">分发人</th>
						<th  style="text-align:center;" width="15%">创建时间</th>
						<th style="text-align:center;" width="40%">状态/操作</th>
					</tr>
					
					<c:forEach items="${wfRdTasks}" var="wfRdTask">
					<tr bgcolor="#FFFFFF" ondblclick="showRecord('<c:out value="${wfRdTask.taskId}"/>');" onmouseover="this.style.backgroundColor='#DFE8F6';" onmouseout="this.style.backgroundColor='#FFFFFF';">
						<td style="text-align:center;" >第<c:out value="${wfRdTask.sort}"/>步</td>
						<td style="text-align:center;" ><c:out value="${wfRdTask.stepName}"/><c:if test="${wfRdTask.taskType==2}">(协办)</c:if></td>
						<td style="text-align:center;">
						  <font color="blue">
						  <c:out value="${wfRdTask.acceptName}"/>
						  <c:if test="${wfRdTask.acptRoleName!=null}">--<c:out value="${wfRdTask.acptRoleName}"/></c:if>
						  </font>
						</td>
						<td style="text-align:center;"><fmt:formatDate type="both"   value="${wfRdTask.acceptDate}"/></td>
						<td style="text-align:center;"><c:out value="${wfRdTask.createrName}"/></td>
						<td style="text-align:center;"><fmt:formatDate  type="both"  value="${wfRdTask.createDate}"/></td>
						<td style="text-align:center;" >
						     <c:choose>
									<c:when test="${wfRdTask.status==0}">
										<font color="red">待接收</font>
									</c:when>
									<c:when test="${wfRdTask.status==1}">
										<font color="blue">办理中</font>
									</c:when>
									<c:when test="${wfRdTask.status==2}">
										<font color="green">已完成</font>
									</c:when>
									<c:when test="${wfRdTask.status==3}">
										<font color="red">已关闭</font>
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
							</c:choose>
						    <c:if test="${wfRdTask.status <= 1}">
						     <input type="button" value=" 催办" class="wfbigbtn2"
						      onclick="pushMail(<c:out value="${wfRd.wfNo}"/>,<c:out value="${wfRdTask.taskId}"/>);" />
						     </c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
				</div>
		</div>
		<!-- table -->
		<!-- 流程进度 -->
		
				<!-- head -->
				<div>
					<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
						<tr>
							<td width="13%">&nbsp;</td>
							<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>任务信息</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn1"></a></td>
						</tr>
					</table>
				</div>
				<!-- head -->
				
				<!-- table -->
				<div id="applicationForm1" class="listTable clearfix" style="margin: 10px 20px;">
					<table cellpadding=0 cellspacing=0.5 width=70% style="background: #9FC2E5;" class="innerTable" align=center>
						 <tr style="background-color: green">
							<th width="10%" >
							任务名称
							</th>
							<td colspan="3" style="font-size:18;font-weight: bolder"> <font color="red"><c:out value="${currentTask.stepName}" /> <c:if test="${currentTask.taskType==1}">(主办)</c:if> <c:if test="${currentTask.taskType==2}">(协办)</font></c:if></td>
							<th>要求完成时间</th>
							<td style="font-size:18;font-weight: bolder"><font color="red"><fmt:formatDate value="${currentTask.reqDate}" /></font></td>
						</tr>
						
						<tr>
						    <th>流程编号</th>
							<td><c:out value="${wfRd.wfNo}" /> <c:choose>
									<c:when test="${wfRd.status==0}">
										<font color="blue">(待处理)</font>
									</c:when>
									<c:when test="${wfRd.status==1}">
										<font color="blue">(办理中)</font>
									</c:when>
									<c:when test="${wfRd.status==2}">
										<font color="blue">(已完成)</font>
									</c:when>
									<c:when test="${wfRd.status==3}">
										<font color="blue">(已关闭)</font>
									</c:when>
									<c:when test="${wfRd.status==4}">
										<font color="blue">(已作废)</font>
									</c:when>
							</c:choose></td>
						
							<th width="10%">流程名称</th>
							<td ><c:out value="${wfRd.wfName}" />&nbsp;&nbsp; <c:if test="${!empty wfRd.preWfNo}">
								(父流程：
								<a href="#" onclick="openRelateFlow('<c:out value="${wfRd.preWfNo}"/>');"> <c:out value="${wfRd.preWfNo}" />
									</a>
								)
							</c:if></td>
							
							<th>计划开始-结束</th>
							<td><fmt:formatDate value="${wfRd.planSDate}" />--<fmt:formatDate value="${wfRd.planEDate}" /></td>
							
						</tr>
						
						
						
						
						<tr>
							<th>发起人</th>
							<td><c:if test="${wfRd.createBy==-1}">
								系统
							</c:if> <c:if test="${wfRd.createBy!=-1}">
									<c:out value="${wfRd.createName}" />
								</c:if></td>
							<th>所属工作流</th>
						    <td><c:out value="${cfg.flowName}" /></td>
							
							<th>实际开始-结束</th>
							<td><c:if test="${!empty wfRd.factSDate}">
									<fmt:formatDate value="${wfRd.factSDate}" />--<fmt:formatDate value="${wfRd.factEDate}" />
								</c:if></td>
						</tr>
						
						
					</table>
				</div>
			</form>
		</div>
		<!-- table -->
		<!-- 流程信息 -->
		
		
		<!-- 会签 -->
		<c:if test="${!empty signList}">
			<!-- head -->
			<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="3%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>意见列表</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn9"></a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->

			<div id="applicationForm9" class="listTable clearfix" style="margin: 10px 20px;">

				<table cellpadding=0 cellspacing=0 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
					<tr>
					<td></td>
					<c:forEach items="${signList}" var="sign" varStatus="s">
						<li>
							<div class="ico"></div>
							<p class="p2">
								<c:out value="${sign.signText}" escapeXml="false" />
							</p> <span> <c:out value="${sign.userName}" />| <fmt:formatDate pattern="yyyy-MM-dd" value="${sign.createDate}" type="both" />
						</span>
						</li>
					</c:forEach></tr></table>
			</div>
			<!-- table -->
			<br>
		</c:if>
		<!-- 会签 -->

		

		<!-- 表单 -->
		<div>
			<%
				String pathstr = (String) request.getAttribute("includeJspUri");
				if (pathstr != null) {
			%>
			<!-- head -->
			<div>
				<!-- a name="#applicationBtn5"></a -->
				
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="13%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>表单</B></SPAN>
						
						<input name="#applicationBtn5" id="applicationBtn5" type="button" value="点击展开表单" class="wfbigbtn2"" />
						<input name="#applicationBtn5" id="applicationBtn5" type="button" value="打印表单" onclick="printForm();" class="wfbigbtn2"" />
						<!-- &nbsp;&nbsp;<a href="#applicationBtn5" id="applicationBtn5"></a>&nbsp;&nbsp;<a href="#" onclick="printForm();">打印表单</a></td>
					      -->
					</tr>
				</table>
			</div>
			<!-- head -->
			
			<!-- table -->
			<div id="applicationForm5" class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=70% style="background: #9FC2E5;" class="innerTable" align=center>
					<tr>
						<td colspan="6"><jsp:include flush="true" page="<%=pathstr%>"></jsp:include></td>
					</tr>
				</table>
				<br>
			</div>
			<%
				}
			%>
		</div>
		<!-- table -->
		<!-- 表单 -->



       
			
			
			
			
		<c:if test="${!empty wfDocs}">
		<br>
		<!-- 附件 -->
		<div id="applicationDiv3">
			<!-- head -->
			<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="13%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>附件</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn3"></a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->
		
			<div id ="wfdocgrid"  class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=70% style="background:#9FC2E5;" class="innerTable" align=center>
					<tr>
						<th style="text-align:center;" width="15%" >最终附件类别</th>
					    <th   style="text-align:center;" width="25%">附件(点击可下载)</th>
					 	<th style="text-align:center;" width="15%">步骤</th>
					 	<th style="text-align:center;" width="15%">版本</th>
						<th  style="text-align:center;" width="15%">上传人</th>
						<th  style="text-align:center;" width="15%">上传时间</th>
						<th style="text-align:center;" width="40%">操作</th>
					</tr>
					
					<c:forEach items="${wfDocs}" var="wfDoc">
					<tr bgcolor="#FFFFFF" ondblclick="showRecord('<c:out value="${wfDoc.docId}"/>');" onmouseover="this.style.backgroundColor='#DFE8F6';" onmouseout="this.style.backgroundColor='#FFFFFF';">
						   <td style="text-align:center;" >
						        <c:if test="${wfDoc.cateName!=null}">
						           <c:out value="${wfDoc.cateName}"/>
						           <input type="hidden" id="griddoccate"<c:out value="${wfDoc.cateId}"/> value='1'/>
						        </c:if>
						    </td>
						<td style="text-align:center;" >
						            <img align="top" src="./include/img/workflow/<c:out value="${wfDoc.icon}"/>"/>"
									<a href="WfDoc!dow.shtml?wfDoc.docId=<c:out value="${wfDoc.docId}"/>">
									    <c:out value="${wfDoc.docName}"/>
									</a>
						          <c:out value="${wfDoc.docName}"/>
						</td>
						
						<td style="text-align:center;">
						         <c:choose>
					 				<c:when test="${wfDoc.stepName==null}">
										无
									</c:when>
									<c:otherwise>
										<c:out value="${wfDoc.stepName}"/>
									</c:otherwise>
							    </c:choose>
						</td>
						<td style="text-align:center;"><c:out value="${wfDoc.docVer}"/></td>
						<td style="text-align:center;"><c:out value="${wfDoc.createName}"/></td>
						<td style="text-align:center;">
					 				<c:if test="${wfDoc.docId != null}">
										<fmt:formatDate  type="both"  value="${wfDoc.createDate}"/>
									</c:if>
						</td>
						<td style="text-align:center;" >
							<input  type="button" value ="添加"  class="wfbigbtn2" onclick="preUploadFile(<c:out value="${wfRd.projectNo}"/>,
						                              '',
													   <c:out value="${wfRd.flowId}"/>,<c:out value="${cfg.flowName}"/>,
													   <c:out value="${user.id}"/>,<c:out value="${currentUserName}"/>,
													   <c:out value="${wfRd.wfNo}"/>,<c:out value="${currentTask.taskId}"/>,
													   <c:out value="${wfDoc.cateId}"/>
													    );"/>
							<c:if test="${wfDoc.docId != null}">
							      <c:if test="${wfDoc.createBy == sessionScope.SYUSR.id }">	
							          &nbsp; <input  type="button" value ="删除"  class="wfbigbtn2" onclick="deleteFile(<c:out value="${wfDoc.docId}"/>);"  />
							      </c:if>		
							    
								<c:if test="${wfRd.flowId==39}">	
								    <c:choose>
										<c:when test="${wfDoc.isBase==1}">
								          &nbsp; <input  type="button" value ="归档"  class="wfbigbtn2" onclick="baseFile(<c:out value="${wfDoc.docId}"/>);"  />
								        </c:when>
								        <c:otherwise>
								        &nbsp;已归档
								        </c:otherwise>
								     </c:choose>
							    </c:if>
							</c:if>			
											
						</td>
					</tr>
				</c:forEach>
				</table>
				</div>
		</div>
		
		 </c:if>
		<!-- table -->
		<!-- 附件 -->
		
		
         


		<!-- 问题 -->
		 <br>
		<div>
			<!-- head -->
					<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
						<tr>
							<td width="13%">&nbsp;</td>
							<td>
							  <IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>问题</B></SPAN>
							    <input name="#applicationBtn7" id="applicationBtn7" type="button" value ="点击展开问题列表"  class="wfbigbtn2"" />
							    <input type="button" value=" 提问题" class="wfbigbtn2"   onclick="addQuestion(1);" />
							    <input type="button" value=" 批量导入问题" class="wfbigbtn2"   onclick="impQues();" />
							   <!-- &nbsp;&nbsp;<a href="#applicationBtn7" id="applicationBtn7"></a>  -->
							    
							</td>
						</tr>
					</table>
			<!-- head -->
			<!-- table -->
			<div id="applicationForm7">
					<div  class="listTable clearfix" style="margin: 10px 20px;">
						<table cellpadding=0 cellspacing=0.5 width=70% style="background: #9FC2E5;" class="innerTable" align=center>
							<tr>
								<td colspan="6"><c:if test="${cfg.hasQuesMoudle==0}">
										<div tabid="4" title="问题列表" style="width: 100%; height: 620px;">
											<iframe id="quesIframe" name="quesIframe" frameborder="0" name="showmessage" style="width: 100%; height: 100%" src="./WfQues!taskList.shtml?wfNo=<c:out value="${wfRd.wfNo}"/>"></iframe>
										</div>
									</c:if></td>
							</tr>
						</table>
					</div>
		</div>
		</div>
		<!-- table -->
		<!-- 问题 -->
		
		
		
		
		<br>
		<br>
		<table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0 width="70%" align=center>
			<tr>
				<td align="right">
					<input id="closeBtn" type="button" value=" 完成任务" class="wfbigbtn"   onclick="closQues();" />
					<input id="closeBtn" type="button" value=" 设置代办人" class="wfbigbtn"   onclick="closQues();" />
					<input id="closeBtn" type="button" value=" 退回" class="wfbigbtn"   onclick="closQues();" />
					<input id="closeBtn" type="button" value=" 暂存" class="wfbigbtn"   onclick="closQues();" />
					<input id="closeBtn" type="button" value=" 终止流程" class="wfbigbtn"   onclick="closQues();" />
				</td>
		 </table>
		
	</div>
	<!-- context -->


	<%@ include file="userpop.jsp"%>
</body>
</html>