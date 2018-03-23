<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css"/>
<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/js/gnwf/wfRd.js" ></script>
<script type="text/javascript" src="./include/js/gnwf/WfRd_special.js"></script>

<style type="text/css">
	#box2,#box3,#box4{padding:10px;border:1px solid #0066CC;}
	body{height:100%;}
	html{height:100%;}
	p{padding:0;margin:0;}
	input{padding:1;margin:0;}
	.lcjd{font-size:12px;}
	.lcjd th{height:24px;text-align:left;color:#fff;}
	.lcjd th,.lcjd td{vertical-align:middle;padding:5px;}
	.lcjd td{border-right:1px solid #5D99DA;border-bottom:1px solid #5D99DA;}
	.lcjd td .detai{display:none;position:absolute;bottom:25px;left:0;background:#e8e3e3;padding:3px;}
	.lcjd td .detail{border:1px solid #bbb;background:#fff;padding:10px;line-height:23px;text-align:left;color:#5b5a59;position:relative;}
	.lcjd td .detail .arr{position:absolute;z-index:99;bottom:-8px;*bottom:-4px;left:20px;}
	.lcjd .stitle td{background:#d7d7d7;font-weight:bold;height:20px;}
	.lcjd .hostname{line-height:21px;padding-right:10px;}
	.lcjd .lcbtn{height:21px;line-height:21px;background:url(./include/img/workflow/lcbtn.png) no-repeat;width:68px;text-align:center;position:relative;white-space:nowrap; }
	.lcjd .hover{background-position:0 -21px;}
	.lcjd .lcbtn input{border:none;height:21px;line-height:21px;background:none;color:#fff;cursor:pointer;}
	
		
	ul{padding:0;margin:0;list-style:none;}

	.title li{float:left;height:35px;padding:0 20px;cursor:pointer;}
	.title{float:left;height:35px;line-height:35px;background:url(./include/img/workflow/bg.png) repeat-x;font:bold 14px/35px '宋体';color:#fff;border-radius:4px;border-left:1px solid #a1a1a1;border-right:1px solid #a1a1a1;}
	.title li.cur{background:url(./include/img/workflow/bghover.png) repeat-x;color:#454545;}
	
	.content{clear:both;}
	.content li{display:none;}
	
</style>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar!custom.shtml",
			{parm:'WfRd',pageType:'11'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
});

var wfdocGridManager;
$(function () {
	$("#wfdocgrid").ligerGrid({
		columns: [
			{ display: '附件(点击可下载)', name: 'docName', align: 'left', width: 220,
				render: function (row){
					var innerHtml = "<img align='top' src='./include/img/workflow/"+row.icon+"'/>"
						+"<a href='WfDoc!dow.shtml?wfDoc.docId="+row.docId+"'>"+row.docName+"</a>";
					return innerHtml;
				}
			},
			{ display: '步骤', name: 'stepName', align: 'left', width: 190, 
				render: function (row){
					if(row.stepName==null){
						return "无";
					}else{
						return row.stepName
					}
				}	
			},
			{ display: '版本号', name: 'docVer', align: 'center', width: 60 },
			{ display: '最终附件类别', name: 'cateName', align: 'left', width: 160, 
				render: function (row){
					if(row.cateName==null){
						return "无";
					}else{
						var innerHtml = "<input type='hidden' id='griddoccate"+row.cateId+"' value='1'/>"+row.cateName;
						return innerHtml;
					}
				}	
			},
			{ display: '上传人', name: 'createName', align: 'center', width: 90 },
			{ display: '上传时间', name: 'createDate', align: 'center', width: 100 },
			{ display: '操作', name: 'createDate', align: 'center', width: 60,
				render: function (row){
					var innerHtml;
					if(row.createBy==$("#curUserId").val()){
						innerHtml = "&nbsp;<a href=\"javascript:deleteFile('"+row.docId+"');\">删除</a>";
					}else{
						innerHtml = "&nbsp;删除";
					}
					return innerHtml;
				}	
			}
			
		],
		url: './WfDoc!list.shtml?wfDoc.status=1&wfDoc.wfNo='+$("#wfNo").val(),
		usePager:true,
		pageSize:50,
		width: '890',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onCheckAllRow: f_onCheckAllRow
	});
	wfdocGridManager = $("#wfdocgrid").ligerGetGridManager();
});


var childListGridManager;
$(function () {
	$("#childListGrid").ligerGrid({
		columns: [
			{ display: '流程编号', name: 'wfNo', align: 'center', width: 110,
				render: function (row){
					var innerHtml = "<a href='#' onclick=\"openRelateFlow('"+row.wfNo+"')\">"+row.wfNo+"</a>";
					return innerHtml;
				}
			},
			{ display: '流程名称', name: 'wfName', align: 'left', width: 120 },
			{ display: '实际开始时间', name: 'factSDate', align: 'center', width: 100 },
			{ display: '实际完成时间', name: 'factEDate', align: 'center', width: 100 },
			{ display: '状态', name: 'status', align: 'center', width: 60,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.status == 0) {
						innerHtml += "red";
						v = "待处理";
					}else if(row.status == 1){
						innerHtml += "blue";
						v = "办理中";
					}else if(row.status == 2){
						innerHtml += "green";
						v = "已完成";
					}else if(row.status == 3){
						innerHtml += "red";
						v = "已关闭";
					}else if(row.status == 4){
						innerHtml += "red";
						v = "已作废";
					}
					innerHtml += "'>"+v+"</font>";
					return innerHtml;
				}
			},
			{ display: '发起人', name: 'createName', align: 'center', width: 90 },
			{ display: '发起时间', name: 'createDate', align: 'center', width: 100 }
		],
		url: './WfRd!selChildList.shtml?wfRd.wfNo='+$("#wfNo").val(),
		usePager:false,
		width: '830',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onCheckAllRow: f_onCheckAllRow
	});
	childListGridManager = $("#childListGrid").ligerGetGridManager();
});

var wfRdTaskGridManager;
$(function () {
	$("#wfRdTaskGrid").ligerGrid({
		columns: [
			{ display: '步骤', name: 'docName', align: 'center', width: 80,
				render: function (row){
					var innerHtml = "第"+row.sort+"步";
					return innerHtml;
				}
			},
			{ display: '任务名称', name: 'stepName', align: 'left', width: 170,
				render: function (row){
					var innerHtml = row.stepName;
					if(row.taskType==2){
						innerHtml+="(协办)";
					}
					return innerHtml;
				}
			},
			{ display: '主办人', name: 'acceptName', align: 'center', width: 200,
				render: function (row){
					var innerHtml = "<font color='blue'>"+row.acceptName;
					if(row.acptRoleName!=null){
						innerHtml = innerHtml +"---"+row.acptRoleName;
					}
					var innerHtml = innerHtml+"</font>";
					return innerHtml;
				}
			
			},
			{ display: '接收时间', name: 'acceptDate', align: 'center', width: 90 },
			{ display: '分发人', name: 'createrName', align: 'center', width: 90 },
			{ display: '创建时间', name: 'createDate', align: 'center', width: 90 },
			{ display: '状态/操作', name: 'createDate', align: 'left', width: 140,
				render: function (row){
					var innerHtml = "<font color='";
					var v;
					if(row.status == 0) {
						innerHtml += "red";
						v = "待接收";
					}else if(row.status == 1){
						innerHtml += "blue";
						v = "办理中";
					}else if(row.status == 2){
						innerHtml += "green";
						v = "已完成";
					}else if(row.status == 3){
						innerHtml += "red";
						v = "已关闭";
					}else if(row.status == 4){
						innerHtml += "red";
						v = "已作废";
					}else if(row.status == 5){
						innerHtml += "green";
						v = "已收回";
					}else if(row.status == 6){
						innerHtml += "green";
						v = "已退回";
					}
					innerHtml += "'>"+v+"</font>";
					
					
					if(row.status<=1){
						innerHtml += "&nbsp;&nbsp;<a href='#' onclick=\"pushMail('"+row.taskId+"')\">催办</a>";
					}
					
					if(row.status==0 && row.createBy==$("#curUserId").val() && row.stepType!=3){
						innerHtml += "&nbsp;&nbsp;<a href='#' onclick=\"backJob('"+row.taskId+"')\">收回</a>";
					}
					
					return innerHtml;
				}
			}
		],
		url: './WfRdTask!list.shtml?wfRdTask.wfNo='+$("#wfNo").val(),
		usePager:true,
		pageSize:50,
		width: '890',
		isChecked: f_isChecked,
		onCheckRow: f_onCheckRow,
		onCheckRow: f_onCheckRow,
		onCheckAllRow: f_onCheckAllRow
	});
	wfRdTaskGridManager = $("#wfRdTaskGrid").ligerGetGridManager();
});
</script>

<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function (){
    	$("#navProjectTab").ligerTab({
    		onBeforeSelectTabItem: function (tabid){
            	if(tabid=='4'){
            		window.frames["quesIframe"].reloadGrid();
            	}
       		}	 
        });
    });
        
    function f_click(){
    	$("#navProjectTab").ligerTab();
        var navtab = $("#navProjectTab").ligerGetTabManager();
        navtab.selectTabItem("startFlowTab");
    }
</script>
</head>
<body>



<div id="navProjectTab" name ="navProjectTab"   style="width:99.8%;overflow:hidden; border:1px solid #A3C0E8; background-color:#FFFEF8;">

<div tabid="1" title="任务办理" <c:if test="${!empty unAcceptTask && empty currentTask}">lselected="true"</c:if> style="width:100%;min-height: 580px;">	<!--background-color:#F0F0F0-->
<form id="wfRd-form" name="wfRd-form" method="post" action="WfRdView!view.shtml" onsubmit="return check();">
<input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
<input type="hidden" id="flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>">
<input type="hidden" id="docFileId" name="wfDoc.docId" value="">

<!-- 任务信息 -->
		<div class="l-form" style="margin:0 auto;width: 700px;background-color: #FFFEF8;">
	    	<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif"><span>任务接收</span></div>
	  		<ul>
				<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
					<ul>
						<li style="width:95px;text-align:left;font-size:large;">流程编号 :</li>
						<li style="width:260px;text-align:left;font-size:large;"><c:out value="${wfRd.wfNo}"/></li>
					</ul>
				</li>
			</ul>
	  		<ul>
	  			<li fieldindex="2" class="l-fieldcontainer">
					<ul>
						<li style="text-align:left;">
							<div class="form-actions">
									<c:choose>
										<c:when test="${!empty unAcceptTask && empty currentTask}">
											<span style="font-size:x-large;">有您的任务，接收任务请点击</span>
												<input type="button" class="wfbigbtn" align="bottom" value="  接收任务  " onclick="acceptJob();">
												<input type="hidden" id="currentTask.taskId" name="currentTask.taskId" value="<c:out value="${unAcceptTask.taskId}"/>">
										</c:when>
										<c:otherwise>
											&nbsp;（当前没有在办任务）
										</c:otherwise>
									</c:choose>
							</div>
						</li>
					</ul>
				</li>
	  		</ul>
	  	</div>
	  		<br><br>


<!-- 表单 -->
<% 
	String path = (String)request.getAttribute("includeJspUri");
	if(path!=null){
%>
<br>
<br>
<table width="720" border="0" cellspacing=0 cellpadding=0 align="center">
<tr>
	<td>
		<IMG align=top src="./include/img/workflow/green_arrow.gif">
		<SPAN class=big3> <B>表单:</B></SPAN>&nbsp;
		<a href="#" onclick="printForm();">打印表单</a>
	</td>
</tr>
</table>
<div>
	<jsp:include flush="true" page="<%=path%>"></jsp:include>
</div>
<% 
	}
%>
<br>
<br>
<!-- 表单 -->

</form>
</div>




<div tabid="2" title="流程信息" <c:if test="${!(!empty unAcceptTask && empty currentTask)}">lselected="true"</c:if> style="width:100%;min-height: 560px;">
<form id="view-form" name="view-form" method="post" action="WfRdView!view.shtml" onsubmit="return check();" enctype="multipart/form-data">
<input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
<input type="hidden" id="curUserId" name="curUserId" value="<c:out value="${user.id}"/>">
<input type="hidden" id="rowTaskId" name="currentTask.taskId" value="">

<!-- 流程概要 -->
    	<div class="l-form" style="margin:0 auto;width:950px;background-color: #FFFEF8;">
	    	<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
	    		<span>流程信息</span>
	    	</div>
	  		<ul>
				<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
					<ul>
						<li style="width:90px;text-align:left;">流程名称 ：</li>
						<li style="width:250px;text-align:left;"><c:out value="${wfRd.wfName}"/>&nbsp;&nbsp;
							<c:if test="${!empty wfRd.preWfNo}">
								(父流程：
								<a href="#" onclick="openRelateFlow('<c:out value="${wfRd.preWfNo}"/>');">
									<c:out value="${wfRd.preWfNo}"/>
								</a>
								)
							</c:if>
						</li>
					</ul>
				</li>
			</ul>
	  		<ul>
				<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
					<ul>
						<li style="width:90px;text-align:left;">流程编号 ：</li>
						<li style="width:180px;text-align:left;">
							<c:out value="${wfRd.wfNo}"/>
							<c:choose>
								<c:when test="${wfRd.status==0}"><font color="blue">(待处理)</font></c:when>
								<c:when test="${wfRd.status==1}"><font color="blue">(办理中)</font></c:when>
								<c:when test="${wfRd.status==2}"><font color="blue">(已完成)</font></c:when>
								<c:when test="${wfRd.status==3}"><font color="blue">(已关闭)</font></c:when>
								<c:when test="${wfRd.status==4}"><font color="blue">(已作废)</font></c:when>
							</c:choose>
						</li>
					</ul>
				</li>
				<li fieldindex="2" class="l-fieldcontainer">
					<ul>
						<li style="width:90px;text-align:left;">所属工作流 ：</li>
						<li style="width:170px;text-align:left;"><c:out value="${cfg.flowName}"/></li>
					</ul>
				</li>
				<li fieldindex="3" class="l-fieldcontainer l-fieldcontainer-first">
					<ul>
						<li style="width:100px;text-align:left;">计划开始-结束 ：</li>
						<li style="width:220px;text-align:left;">
							<fmt:formatDate value="${wfRd.planSDate}"/>--<fmt:formatDate value="${wfRd.planEDate}"/>
						</li>
					</ul>
				</li>
			</ul>
			
			
	  		<ul>
				<li fieldindex="1" class="l-fieldcontainer l-fieldcontainer-first">
					<ul>
						<li style="width:90px;text-align:left;">发起人 ：</li>
						<li style="width:180px;text-align:left;">
							<c:if test="${wfRd.createBy==-1}">
								系统
							</c:if>
							<c:if test="${wfRd.createBy!=-1}">
								<c:out value="${wfRd.createName}"/>
							</c:if>
						</li>
					</ul>
				</li>
				<li fieldindex="2" class="l-fieldcontainer">
					<ul>
						<li style="width:90px;text-align:left;">创建时间 ：</li>
						<li style="width:170px;text-align:left;">
							<fmt:formatDate value="${wfRd.createDate}" var="createDate"/>
							<c:out value="${createDate}"/>
						</li>
					</ul>
				</li>
				<li fieldindex="3" class="l-fieldcontainer">
					<ul>
						<li style="width:100px;text-align:left;">实际开始-结束 ：</li>
						<li style="width:220px;text-align:left;">
							<c:if test="${!empty wfRd.factSDate}">
								<fmt:formatDate value="${wfRd.factSDate}"/>--<fmt:formatDate value="${wfRd.factEDate}"/>
							</c:if>
						</li>
					</ul>
				</li>
			</ul>
<br>
<br>
<!-- 流程概要 -->


<!-- 流程进度-->
		<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
			<span class=big5>流程进度</span>
		</div>
		
<DIV class="w">
<DIV class="section3" id="process" style="margin:0 auto;">

<c:forEach items="${imgStepList}" var="step" varStatus="s">
	<c:if test="${s.count==1}">
		<DIV <c:choose>
				<c:when test="${step.isCurrent==1}">class="node current"</c:when>
				<c:when test="${step.isCurrent!=1 && !empty step.taskTime}">class="node ready"</c:when>
				<c:when test="${step.isCurrent!=1 && empty step.taskTime}">class="node gray"</c:when>
			 </c:choose>>
			<UL>
			  <SLI class="tx1">&nbsp;</SLI>
			  <SLI class="tx2"><c:out value="${step.stepName}"/></SLI>
			  <SLI class="tx3" id="track_time_0">
			  	<br><fmt:formatDate pattern="yyyy-MM-dd" value="${step.taskTime}" type="both"/>
			  </SLI>
			</UL>
		</DIV>
	</c:if>
	<c:if test="${s.count!=1}">
		<DIV <c:choose>
				<c:when test="${step.isCurrent==1 || !empty step.taskTime}">class="proce ready"</c:when>
				<c:when test="${step.isCurrent!=1 || empty step.taskTime}">class="proce gray"</c:when>
			 </c:choose>>
			<UL><SLI class="tx1">&nbsp;</SLI></UL>
		</DIV>
		<DIV <c:choose>
				<c:when test="${step.isCurrent==1}">class="node current"</c:when>
				<c:when test="${step.isCurrent!=1 && !empty step.taskTime}">class="node ready"</c:when>
				<c:when test="${step.isCurrent!=1 && empty step.taskTime}">class="node gray"</c:when>
			 </c:choose>>
			<UL>
			  <SLI class="tx1">&nbsp;</SLI>
			  <SLI class="tx2"><c:out value="${step.stepName}"/></SLI>
			  <SLI class="tx3" id="track_time_1">
			  	<br><fmt:formatDate pattern="yyyy-MM-dd" value="${step.taskTime}" type="both"/>
			  </SLI>
			</UL>
		</DIV>
	</c:if>
</c:forEach>

</DIV>
</DIV>	
		
		
		<div id="wfRdTaskGrid" style="margin-top:1px;margin-left:1px;" ></div><br><br>
<!-- 流程进度-->

		
		
<!-- 附件 -->
			<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
				<span class=big5>公共附件</span>
			</div>
			<div id="wfdocgrid" style="margin-top:1px;margin-left:1px;" >
			</div>
			<br>
			<!-- 
			<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
				<a href="javascript:addFile();" >添加附件</a> 
			</div>
			
			<table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="720px" align=center>
				<tr>
					<td>
						<table id="attachtab" border="0" cellpadding="0" cellspacing="0">
						</table>
						<a id="submitFileBtn" style="display:none;" href="javascript:submitFile();">确认上传</a>
					</td>
				</tr>
			</table>
			-->
			
			<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
				<a href="javascript:uploadFile('<c:out value="${wfRd.projectNo}"/>','',
					'<c:out value="${wfRd.flowId}"/>','<c:out value="${cfg.flowName}"/>',
					'<c:out value="${user.id}"/>','<c:out value="${currentUserName}"/>',
					'<c:out value="${wfRd.wfNo}"/>');" >
					添加附件
				</a>
			</div>
			
			<br><br>
<!-- 附件 -->


<!-- 会签 -->
<c:if test="${!empty signList}">
		<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
			<span class=big5>意见列表</span>
		</div>

	<div class="wrap_3">
	    <ul>
	      <c:forEach items="${signList}" var="sign" varStatus="s">
	      	<li>
	      		<div class="ico"></div>
	        	<p class="p2"><c:out value="${sign.signText}" escapeXml="false"/></p>
	       		<span>
	       			<c:out value="${sign.userName}"/>|
	       			<fmt:formatDate pattern="yyyy-MM-dd" value="${sign.createDate}" type="both"/>
	       		</span>
	        </li>
	       </c:forEach>
	    </ul>
	  </div>
</c:if>
<!-- 会签 -->
</div>
</form>
</div>
	




<div tabid="3" title="相关子流程" style="width:100%;min-height: 580px;">

<!--子流程  -->
<div class="l-form" style="margin:0 auto;width: 830px;background-color: #FFFEF8;">
			<!-- 相关流程列表-->
			<div class="l-group l-group-hasicon"><img src="./include/liger/ligerUI/skins/icons/communication.gif">
				<span>相关子流程</span>
			</div>
			
			<div id="childListGrid" style="margin-top:1px;margin-left:1px;" ></div><br>
</div>
<!--子流程  -->

</div>
	
	
	
<!-- 问题  -->
<c:if test="${cfg.hasQuesMoudle==0}">
<div tabid="4" title="问题列表" style="width:100%;height: 620px;">
	<iframe id="quesIframe" name="quesIframe" frameborder="0" name="showmessage" style="width: 100%;height:100%" src="./WfQues!taskList.shtml?wfNo=<c:out value="${wfRd.wfNo}"/>"></iframe>
</div>
</c:if>
<!-- 问题 -->


</div>



</body>
</html>