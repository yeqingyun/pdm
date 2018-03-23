<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		var gngile_uploadURL = $('#gngile_upload_URL').val();
		var server_URL = $('#server_URL').val();
		//var cateId = getRadioValue('docCate');
		var httpUrl = gngile_uploadURL+'?'
				+ 'syId=1&syNm=PDM&usrId='
				+ usrId
				+ '&usrNm='
				+ usrNm
				+ '&diyFolder='
				+ "aaa"
				+ '&callBackUrl='
				+ server_URL+"/WfDoc!addfile.shtml"
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
	
	
	
	var wfdocGridManager;
	$(function() {
		var flowId = document.getElementById("wfRd.flowId").value;
		$("#wfdocgrid")
				.ligerGrid(
						{
							columns : [
									{
										display : '最终附件类别',
										name : 'cateName',
										align : 'left',
										width : 120,
										render : function(row) {
											if (row.cateName == null) {
												return "无";
											} else {
												var innerHtml = "<input type='hidden' id='griddoccate"+row.cateId+"' value='1'/>"
														+ row.cateName;
												return innerHtml;
											}
										}
									},
									{
										display : '附件(点击可下载)',
										name : 'docName',
										align : 'left',
										width : 170,
										render : function(row) {
											if (row.docId == null) {
												return "";
											}
											var docNm = row.docName;
											if (row.docName.length > 15) {
												docNm = row.docName.substring(
														0, 14)
														+ "...";
											}
											var innerHtml = "<img align='top' src='./include/img/workflow/"+row.icon+"'/>"
													+ "<a href='WfDoc!dow.shtml?wfDoc.docId="
													+ row.docId
													+ "'>"
													+ docNm
													+ "</a>";
											return innerHtml;
										}
									},
									{
										display : '步骤',
										name : 'stepName',
										align : 'left',
										width : 130,
										render : function(row) {
											if (row.docId == null) {
												return "";
											}
											if (row.stepName == null) {
												return "无";
											} else {
												return row.stepName
											}
										}
									},
									{
										display : '版本',
										name : 'docVer',
										align : 'center',
										width : 40,
										render : function(row) {
											if (row.docId == null) {
												return "";
											}
											return row.docVer
										}
									},
									{
										display : '上传人',
										name : 'createName',
										align : 'center',
										width : 70,
										render : function(row) {
											if (row.docId == null) {
												return "";
											}
											return row.createName
										}
									},
									{
										display : '上传时间',
										name : 'createDate',
										align : 'center',
										width : 75,
										render : function(row) {
											if (row.docId == null) {
												return "";
											}
											return row.createDate
										}
									},
									{
										display : '操作',
										name : 'createDate',
										align : 'center',
										width : 85,
										render : function(row) {
											var innerHtml = "";
											var abhtml = "&nbsp;<a href=\"javascript:preUploadFile('<c:out value='${wfRd.projectNo}'/>','',"
													+ "'<c:out value='${wfRd.flowId}'/>','<c:out value='${cfg.flowName}'/>',"
													+ "'<c:out value='${user.id}'/>','<c:out value='${currentUserName}'/>',"
													+ "'<c:out value='${wfRd.wfNo}'/>','<c:out value='${currentTask.taskId}'/>','"
													+ row.cateId
													+ "');\" >"
													+ "添加</a>";
											innerHtml += abhtml;
											if (row.docId != null) {
												if (row.createBy == $(
														"#curUserId").val()) {
													innerHtml += "&nbsp;<a href=\"javascript:deleteFile('"
															+ row.docId
															+ "');\">删除</a>";
												} else {
													innerHtml += "&nbsp;删除";
												}
												if (flowId == 39) { //归档流程
													if (row.isBase == 1) {
														innerHtml += "&nbsp;<a href=\"javascript:baseFile('"
																+ row.docId
																+ "');\">归档</a>";
													} else {
														innerHtml += "&nbsp;已归档";
													}
												}
											}
											return innerHtml;
										}
									} ],
							//url: './WfDoc!list.shtml?wfDoc.status=1&wfDoc.wfNo='+$("#wfNo").val(),
							url : './WfDoc!list.shtml?wfDoc.status=1&wfDoc.wfNo='
									+ $("#wfNo").val()
									+ "&wfStep.stepId="
									+ $("#stepId").val(),//currentTask.stepId
							usePager : true,
							pageSize : 50,
							width : '700',
							isChecked : f_isChecked,
							onCheckRow : f_onCheckRow,
							onCheckRow : f_onCheckRow,
							onCheckAllRow : f_onCheckAllRow
						});
		wfdocGridManager = $("#wfdocgrid").ligerGetGridManager();
	});

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

	var wfRdTaskGridManager;
	$(function() {
		$("#wfRdTaskGrid")
				.ligerGrid(
						{
							columns : [
									{
										display : '步骤',
										name : 'docName',
										align : 'center',
										width : 70,
										render : function(row) {
											var innerHtml = "第" + row.sort
													+ "步";
											return innerHtml;
										}
									},
									{
										display : '任务名称',
										name : 'stepName',
										align : 'left',
										width : 170,
										render : function(row) {
											var innerHtml = row.stepName;
											if (row.taskType == 2) {
												innerHtml += "(协办)";
											}
											return innerHtml;
										}
									},
									{
										display : '主办人',
										name : 'acceptName',
										align : 'center',
										width : 200,
										render : function(row) {
											var innerHtml = "<font color='blue'>"
													+ row.acceptName;
											if (row.acptRoleName != null) {
												innerHtml = innerHtml + "---"
														+ row.acptRoleName;
											}
											var innerHtml = innerHtml
													+ "</font>";
											return innerHtml;
										}
									},
									{
										display : '接收时间',
										name : 'acceptDate',
										align : 'center',
										width : 90
									},
									{
										display : '分发人',
										name : 'createrName',
										align : 'center',
										width : 90
									},
									{
										display : '创建时间',
										name : 'createDate',
										align : 'center',
										width : 90
									},
									{
										display : '状态/操作',
										name : 'createDate',
										align : 'left',
										width : 140,
										render : function(row) {
											var innerHtml = "<font color='";
											var v;
											if (row.status == 0) {
												innerHtml += "red";
												v = "待接收";
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
											} else if (row.status == 5) {
												innerHtml += "green";
												v = "已收回";
											} else if (row.status == 6) {
												innerHtml += "green";
												v = "已退回";
											}
											innerHtml += "'>" + v + "</font>";

											if (row.status <= 1) {
												innerHtml += "&nbsp;&nbsp;<a href='#' onclick=\"pushMail('"
														+ $("#wfNo").val()
														+ "','"
														+ row.taskId
														+ "')\">催办</a>";
											}

											return innerHtml;
										}
									} ],
							url : './WfRdTask!list.shtml?wfRdTask.wfNo='
									+ $("#wfNo").val(),
							usePager : true,
							pageSize : 50,
							width : '890',
							isChecked : f_isChecked,
							onCheckRow : f_onCheckRow,
							onCheckRow : f_onCheckRow,
							onCheckAllRow : f_onCheckAllRow
						});
		wfRdTaskGridManager = $("#wfRdTaskGrid").ligerGetGridManager();
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
				<input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
				<input type="hidden" id="cateId" />
				<!-- head -->
				<div>
					<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
						<tr>
							<td width="3%">&nbsp;</td>
							<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>流程信息</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn1"></a></td>
						</tr>
					</table>
				</div>
				<!-- head -->
				<!-- table -->
				<div id="applicationForm1" class="listTable clearfix" style="margin: 10px 20px;">
					<table cellpadding=0 cellspacing=0.5 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
						<tr>
							<th width="10%">流程名称</th>
							<td colspan="5"><c:out value="${wfRd.wfName}" />&nbsp;&nbsp; <c:if test="${!empty wfRd.preWfNo}">
								(父流程：
								<a href="#" onclick="openRelateFlow('<c:out value="${wfRd.preWfNo}"/>');"> <c:out value="${wfRd.preWfNo}" />
									</a>
								)
							</c:if></td>
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
							<th>所属工作流</th>
							<td><c:out value="${cfg.flowName}" /></td>
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
							<th>创建时间</th>
							<td><fmt:formatDate value="${wfRd.createDate}" var="createDate" /> <c:out value="${createDate}" /></td>
							<th>实际开始-结束</th>
							<td><c:if test="${!empty wfRd.factSDate}">
									<fmt:formatDate value="${wfRd.factSDate}" />--<fmt:formatDate value="${wfRd.factEDate}" />
								</c:if></td>
						</tr>
					</table>
					<br>
				</div>
			</form>
		</div>
		<!-- table -->
		<!-- 流程信息 -->

		<!-- 流程进度 -->
		<div>
			<!-- head -->
			<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="3%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>流程进度</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn2"></a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->

			<div id="applicationForm2" class="listTable clearfix" style="margin: 10px 20px;">

				<table cellpadding=0 cellspacing=0 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
					<tr>
						<td width="3%" style="border-right: 0 solid black;"></td>
						<td colspan="5" id="process" align="center"><br> <c:forEach items="${imgStepList}" var="step" varStatus="s">
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
							</c:forEach></td>
					</tr>
					<tr>
						<td colspan="6">
							<div id="wfRdTaskGrid" style="margin-top: 1px; margin-left: 1px;"></div>
						</td>
					</tr>
				</table>
				<br>
			</div>
		</div>
		<!-- table -->
		<!-- 流程进度 -->
		
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

		<!-- 工作任务 -->
		<div>
			<!-- head -->
			<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="3%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B> <c:choose>
										<c:when test="${currentTask.agentBy == user.id}">
											<SPAN class=big5>代办任务</SPAN>
											<font style="font-size: 14px;">(本任务由</font>
											<font style="font-size: 14px; color: blue;"><c:out value="${currentTask.acceptName}" /></font>
											<font style="font-size: 14px;">交给您代办)</font>
										</c:when>
										<c:when test="${!empty currentTask.agentBy && currentTask.agentBy!=0 && currentTask.agentBy != user.id}">
											<SPAN class=big5>工作任务</SPAN>
											<font style="font-size: 14px;">(已将本任务交给</font>
											<font style="font-size: 14px; color: blue;"><c:out value="${currentTask.agentName}" /></font>
											<font style="font-size: 14px;">代办)</font>
										</c:when>
										<c:otherwise>
											<SPAN class=big5>工作任务</SPAN>
										</c:otherwise>
									</c:choose>
							</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn1"></a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->
			<div id="applicationForm1" class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
					<tr>
						<th width="10%">步骤名称</th>
						<td colspan="1"><c:out value="${currentTask.stepName}" /> <c:if test="${currentTask.taskType==1}">(主办)</c:if> <c:if test="${currentTask.taskType==2}">(协办)</c:if></td>
						<th>流程编号</th>
						<td colspan="3"><c:out value="${wfRd.wfNo}" /></td>
					</tr>
					<tr>
						<th>要求完成时间</th>
						<td><fmt:formatDate value="${currentTask.reqDate}" /></td>
						<th>内容描述</th>
						<td colspan="3"><c:out value="${currentTask.stepDesc}" escapeXml="false" /> <c:if test="${empty currentTask.stepDesc}">无</c:if></td>
					</tr>
					<tr>
						<th>操作</th>
						<td colspan="5"><div id="toolbar"></div></td>
					</tr>
				</table>
				<br>
			</div>
		</div>
		<!-- table -->
		<!-- 工作任务 -->

		<!-- 表单 -->
		<div>
			<%
				String pathstr = (String) request.getAttribute("includeJspUri");
				if (pathstr != null) {
			%>
			<!-- head -->
			<div>
				<a name="#applicationBtn5"></a>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="3%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>表单</B></SPAN>&nbsp;&nbsp;<a href="#applicationBtn5" id="applicationBtn5"></a>&nbsp;&nbsp;<a href="#" onclick="printForm();">打印表单</a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->
			<div id="applicationForm5" class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
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

		<!-- 附件 -->
		<div id="applicationDiv3">
			<!-- head -->
			<div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="3%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>附件</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn3"></a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->
			<div id="applicationForm3" class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
					<tr>
						<td colspan="6"><div id="wfdocgrid" style="margin-top: 1px; margin-left: 1px;"></div> <br> <%-- <a href="javascript:uploadFile('<c:out value="${wfRd.projectNo}"/>','',
						'<c:out value="${wfRd.flowId}"/>','<c:out value="${cfg.flowName}"/>',
						'<c:out value="${user.id}"/>','<c:out value="${currentUserName}"/>',
						'<c:out value="${wfRd.wfNo}"/>');"> 添加附件 </a> --%></td>
					</tr>
				</table>
				<br>
			</div>
		</div>
		<!-- table -->
		<!-- 附件 -->

		<!-- 相关子流程  -->
		<div>
			<!-- head -->
			<!-- <div>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="3%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>相关子流程</B></SPAN>&nbsp;&nbsp;<a href="#" id="applicationBtn6"></a></td>
					</tr>
				</table>
			</div> -->
			<!-- head -->
			<!-- table -->
			<!-- <div id="applicationForm6" class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
					<tr>
						<td colspan="6"><div id="childListGrid" style="margin-top: 1px; margin-left: 1px;"></div></td>
					</tr>
					<tr><td> -->
			<!--启动流程 -->
			<%-- <div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
				<a href="#" onclick="startRelateFlow('<c:out value="${wfRd.wfNo}"/>','<c:out value="${wfRd.projectNo}"/>');">点击启动流程</a>
			</div>
			<c:if test="${!empty relateFlows}">
			<table border="0" width="100%">
    		<tr>
	    		<td align="left">
		    		<c:forEach items="${relateFlows}" var="flow" varStatus="s">
				    	<input type="radio" name="relate" value="<c:out value="${flow.relateId}"/>" 
				    		<c:if test="${s.count==1}">checked</c:if>>
				    	<c:out value="${flow.flowName}"/>&nbsp;&nbsp;&nbsp;
					</c:forEach>
	    		</td>
    		</tr>
    		</table></c:if>
					</td></tr>
				</table>
				<br>
			</div> --%>
		</div>
		<!-- table -->
		<!-- 相关子流程 -->

		<!-- 问题 -->
		<div>
			<!-- head -->
			<div>
				<a name="#applicationBtn7"></a>
				<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
					<tr>
						<td width="3%">&nbsp;</td>
						<td><IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>问题</B></SPAN>&nbsp;&nbsp;<a href="#applicationBtn7" id="applicationBtn7"></a></td>
					</tr>
				</table>
			</div>
			<!-- head -->
			<!-- table -->
			<div id="applicationForm7" class="listTable clearfix" style="margin: 10px 20px;">
				<table cellpadding=0 cellspacing=0.5 width=90% style="background: #9FC2E5;" class="innerTable" align=center>
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
		<!-- table -->
		<!-- 问题 -->
	</div>
	<!-- context -->


	<%@ include file="userpop.jsp"%>
</body>
</html>