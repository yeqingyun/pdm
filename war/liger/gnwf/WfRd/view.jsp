<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<style type="text/css">
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
.lcjd .title td{background:#d7d7d7;font-weight:bold;height:20px;}
.lcjd .hostname{line-height:21px;padding-right:10px;}
.lcjd .lcbtn{height:21px;line-height:21px;background:url(./include/img/workflow/lcbtn.png) no-repeat;width:68px;text-align:center;position:relative;white-space:nowrap; }
.lcjd .hover{background-position:0 -21px;}
.lcjd .lcbtn input{border:none;height:21px;line-height:21px;background:none;color:#fff;cursor:pointer;}
</style>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link href="./include/css/workflow.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfRd.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'url'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
	check();
	if ($("#planSDate").length > 0)
		$("#planSDate").ligerDateEditor({showTime: false});
	if ($("#startPlanSDate").length > 0)
		$("#startPlanSDate").ligerDateEditor({showTime: false});
	if ($("#endPlanSDate").length > 0)
		$("#endPlanSDate").ligerDateEditor({showTime: false});
	if ($("#planEDate").length > 0)
		$("#planEDate").ligerDateEditor({showTime: false});
	if ($("#startPlanEDate").length > 0)
		$("#startPlanEDate").ligerDateEditor({showTime: false});
	if ($("#endPlanEDate").length > 0)
		$("#endPlanEDate").ligerDateEditor({showTime: false});
	if ($("#factSDate").length > 0)
		$("#factSDate").ligerDateEditor({showTime: false});
	if ($("#startFactSDate").length > 0)
		$("#startFactSDate").ligerDateEditor({showTime: false});
	if ($("#endFactSDate").length > 0)
		$("#endFactSDate").ligerDateEditor({showTime: false});
	if ($("#factEDate").length > 0)
		$("#factEDate").ligerDateEditor({showTime: false});
	if ($("#startFactEDate").length > 0)
		$("#startFactEDate").ligerDateEditor({showTime: false});
	if ($("#endFactEDate").length > 0)
		$("#endFactEDate").ligerDateEditor({showTime: false});
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#scheId").length > 0)
		$("#scheId").ligerTextBox();
	if ($("#flowId").length > 0)
		$("#flowId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	if ($("#wfName").length > 0)
		$("#wfName").ligerTextBox();
	if ($("#wfDesc").length > 0)
		$("#wfDesc").ligerTextBox();
	if ($("#wfNo").length > 0)
		$("#wfNo").ligerTextBox();
	if ($("#projectNo").length > 0)
		$("#projectNo").ligerTextBox();
	if ($("#preWfNo").length > 0)
		$("#preWfNo").ligerTextBox();

});
</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form>

<input type="hidden" id="actUri" value="WfRdView">
<input type="hidden" id="ediUri" value="WfRdModify">
<input type="hidden" id="addUri" value="WfRdAdd">
<input type="hidden" id="actParam" value="?wfRd.wfNo=<c:out value="${wfRd.wfNo}"/>">
<input type="hidden" id="wfRd.wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>">
<input type="hidden" id="wfRd.flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>">

<p style="font-size: large;font-weight: bold;">流程预览页面</p>

<!-- 任务信息 -->
<font style="font-size:large;">流程<c:out value="${wfRd.wfNo}"/></font>
<c:if test="${!empty unAcceptTask && empty currentTask}">
	&nbsp;<p style="font-size:x-large;">有您的任务，接收任务请点击：
	<!-- 
	<a href="#" >
		<font color="red" style="font-size:x-large;">接收任务</font>
	</a>
	 -->
	<input type="button" class="wfbigbtn" align="bottom" value="  接收任务  "
		onclick="setViewFrmSrc('WfRdView!accept.shtml?wfRd.wfNo=<c:out value="${wfRd.wfNo}"/>&currentTask.taskId=<c:out value="${unAcceptTask.taskId}"/>');">
	</p>
</c:if>
<br>
<br>

<!-- 表单 -->
<% 
	String path = (String)request.getAttribute("includeJspUri");
	if(path!=null){
%>
<table width="100%" border="0" cellspacing=0 cellpadding=0>
<tr>
	<td width="5%">&nbsp;</td>
	<td>
		<IMG align=top src="./include/img/workflow/green_arrow.gif">
		<SPAN class=big3> <B>表单:</B></SPAN>
		<a href="#" class="applicationBtn">点击隐藏</a>&nbsp;&nbsp;
		<a href="#" onclick="printForm();">打印表单</a>
	</td>
</tr>
</table>
<div class="applicationForm">
<table id="box3" border="0" width="100%" style="BORDER-COLLAPSE:collapse;">
<tr>
<td>
	<jsp:include flush="true" page="<%=path%>"></jsp:include>
</td>
</tr>
</table>
</div>
<% 
	}
%>
<!-- 表单 -->


<br>
<br>
<!-- 流程概要 -->
<table style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="90%" align=center>
	<tr class=TableHeader align="left">
		<td colspan="4">流程概要</td>
	</tr>
	<tr height="22">
		<td bgcolor="#EFF0F2">所属工作流</td>
		<td bgcolor="#ffffff" colspan=3><c:out value="${cfg.flowName}"/></td>
	</tr>
	<tr height="22">
		<td bgcolor="#EFF0F2">流程编号</td>
		<td bgcolor="#ffffff">
			<c:out value="${wfRd.wfNo}"/>
			<c:choose>
				<c:when test="${wfRd.status==0}"><font color="blue">(待处理)</font></c:when>
				<c:when test="${wfRd.status==1}"><font color="blue">(办理中)</font></c:when>
				<c:when test="${wfRd.status==2}"><font color="blue">(已完成)</font></c:when>
				<c:when test="${wfRd.status==3}"><font color="blue">(已关闭)</font></c:when>
				<c:when test="${wfRd.status==4}"><font color="blue">(已作废)</font></c:when>
			</c:choose>
			&nbsp;&nbsp;
			<c:if test="${wfRd.preWfNo!=null}">
				(父流程：
				<a href="#" onclick="setEditFrmSrc('WfRdView!previewExecute.shtml?wfRd.wfNo=<c:out value="${wfRd.preWfNo}"/>');">
					<c:out value="${wfRd.preWfNo}"/>
				</a>
				)
			</c:if>
		</td>
		<td bgcolor="#EFF0F2">流程名称</td>
		<td bgcolor="#ffffff"><c:out value="${wfRd.wfName}"/></td>
	</tr>
	<tr height="22">
		<td bgcolor="#EFF0F2">发起人</td>
		<td bgcolor="#ffffff"><c:out value="${wfRd.createName}"/></td>
		<td bgcolor="#EFF0F2">发起时间</td>
		<td bgcolor="#ffffff">
		<fmt:formatDate value="${wfRd.createDate}" var="createDate"/>
			<c:out value="${createDate}"/>
		</td>
	</tr>
	<tr height="22">
		<td bgcolor="#EFF0F2" width="15%">计划开始时间</td>
		<td bgcolor="#ffffff" width="35%">
		<fmt:formatDate value="${wfRd.planSDate}"/></td>
		<td bgcolor="#EFF0F2" width="15%">计划完成时间</td>
		<td bgcolor="#ffffff" width="35%">
		<fmt:formatDate value="${wfRd.planEDate}"/></td>
	</tr>
	<tr height="22">
		<td bgcolor="#EFF0F2">实际开始时间</td>
		<td bgcolor="#ffffff">
		<fmt:formatDate value="${wfRd.factSDate}"/>
		</td>
		<td bgcolor="#EFF0F2">实际完成时间</td>
		<td bgcolor="#ffffff">
		<fmt:formatDate value="${wfRd.factEDate}"/></td>
	</tr>
	<tr height="22">
		<td bgcolor="#EFF0F2">描述</td>
		<td bgcolor="#ffffff" colspan=3><c:out value="${wfRd.wfDesc}" escapeXml="false"/></td>
	</tr>
</table>
<!-- 流程概要 -->

<br>
<br>


<!-- 流程进度-->
<table  style="background:#fff;border-left:1px solid #5D99DA;" class="lcjd" border=0 cellspacing=0  cellpadding=0 width="90%" align=center>
  <tr>
    <th colSpan=8 noWrap style="background:#5D99DA;">流程进度</th>
  </tr>
  
  <tr class="title">
  	<td>步骤</td>
  	<td width="18%">任务名称</td>
  	<td width="8%" align="center">创建时间</td>
  	<td width="8%" align="center">分发人</td>
  	<td width="8%" align="center">接收时间</td>
  	<td style="border-right:none;" align="center">办理情况</td>
  	<td>&nbsp;</td>
  	<td width="16%" align="center">状态/操作</td>
  </tr>
  
  <c:forEach items="${taskList}" var="task" varStatus="s">
 	<tr <c:if test="${s.count%2!=1}">class=TableContent</c:if> <c:if test="${s.count%2==1}">class=TableData</c:if>>
	    <td noWrap align="center">
	    	第<SPAN class=Big4><c:out value="${task.sort}"/></SPAN>步 
	    </td>
	    <td>
	    	<SPAN title=上一步骤序号：1>
	    		<img border=0 src="./include/img/workflow/arrow_down.gif">
	    		<c:out value="${task.stepName}"/><c:if test="${task.stepType==1}">&nbsp;</c:if><c:if test="${task.taskType==1 && task.stepType==3}">(并发)</c:if><c:if test="${task.taskType==2}">(协办)</c:if>
	    	</SPAN>
		</td>
		<td align="center">
			<fmt:formatDate value="${task.createDate}"/>
		</td>
		<td align="center">
			<font color="blue"><c:out value="${task.createrName}"/></font><br>
		</td>
		<td align="center">
			<fmt:formatDate value="${task.acceptDate}"/>
		</td>
	    <td id=2_2 style="border-right:none;">
	    	<p class="hostname">
	    		<c:if test="${task.status==0}"><img align="middle" src="./include/img/workflow/email_close.gif"/></c:if>
	    		<c:if test="${task.status==1}"><img align="middle" src="./include/img/workflow/email_open.gif"/></c:if>
	    		<c:if test="${task.status==2}"><img align="middle" src="./include/img/workflow/flow_next.gif"/></c:if>
	    		<c:if test="${task.status==3}"><img align="middle" src="./include/img/workflow/flow_next.gif"/></c:if>
	    		<c:if test="${task.status==4}"><img align="middle" src="./include/img/workflow/flow_next.gif"/></c:if>
	    		<c:if test="${task.status==5}"><img align="middle" src="./include/img/workflow/flow_back.gif"/></c:if>
	    		<c:if test="${task.status==6}"><img align="middle" src="./include/img/workflow/flow_back.gif"/></c:if>
		    <SPAN class=big4>
		    	<U style="CURSOR: pointer" title="部门：系统处/OA项目组"><c:out value="${task.acceptName}"/>&nbsp;&nbsp;
		    	<c:if test="${task.taskType==1}">主办</c:if>
	    		<c:if test="${task.taskType==2}">协办</c:if>
	    		</U>
		    </SPAN>
	      	[
	      	<FONT color=green>
	      		<c:if test="${task.status==0}">未接收办理；</c:if>
	      		<c:if test="${task.isSystemFinsh==1}">系统自动完成；</c:if>
	      		<c:if test="${task.status!=0 && task.isSystemFinsh!=1}">用时：<c:out value="${task.useTime}"/>；</c:if>
	      	</FONT>
	      	<c:if test="${!empty task.overTime}">
	      		<FONT color=red>超时天数：<c:out value="${task.overTime}"/>天</FONT>
	      	</c:if>
		  	 ]
		  	</p>	
		</td>
		<td>
			<div class="lcbtn" align="right">
					<input type="button" value="查看详情" />
					<div class="detai">
						<div class="detail">
						开始时间：<fmt:formatDate value="${task.acceptDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
						结束时间：<fmt:formatDate value="${task.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/><br />
						计划完成时间：<fmt:formatDate value="${task.reqDate}"/><br />
						说明：此步骤每日子24小时可办理，公休日记入时长
						<img src="./include/img/workflow/arrowbg.png" class="arr" />
						</div>
					</div>
			</div>
		</td>
		<td>&nbsp;
	      	<c:if test="${task.status==0}"><FONT color=red>待接收</FONT></c:if>
	    	<c:if test="${task.status==1}"><FONT color=blue>办理中</FONT></c:if>
	    	<c:if test="${task.status==2}"><FONT color=green>已完成</FONT></c:if>
	    	<c:if test="${task.status==3}"><FONT color=red>已关闭</FONT></c:if>
	    	<c:if test="${task.status==4}"><FONT color=red>已作废</FONT></c:if>
	    	<c:if test="${task.status==5}"><FONT color=green>已收回</FONT></c:if>
	    	<c:if test="${task.status==6}"><FONT color=green>已退回</FONT></c:if>
	    	<c:if test="${task.status<=1}">&nbsp;
			  	<U style="CURSOR: pointer" title="进入催办页面，填写催办信息，系统会发邮件催办任务人"
			  		onclick="pushMail('<c:out value="${wfRd.wfNo}"/>','<c:out value="${task.taskId}"/>');">
			  		催办
			  	</U>
			</c:if>
				<!-- &nbsp;&nbsp;<U title="任务办理完毕，不可催办">催办</U>-->
	    	<c:if test="${task.status==0 && task.createBy==user.id && task.stepType!=3}">&nbsp;
			  	<U style="CURSOR: pointer" title="在此任务没接收之前，点击可收回工作，返回上一步分发人" 
			  		onclick="backJob('<c:out value="${wfRd.wfNo}"/>','<c:out value="${task.taskId}"/>');">
			  		收回
			  	</U>
			</c:if>
		</td>
	</tr>
 </c:forEach>
</table>
<!-- 流程进度-->


<br/>
<br/>

<!-- 附件 -->
<table style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="90%" align=center>
 	<tr class=TableHeader>
    	<td colspan="6">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td align="left">公共附件</td>
					<td align="right">
						<input type="button" value="添加 " onclick="window.openAddFiledlg();">&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</td>
    </tr>
    <tr bgcolor="#EFF0F2" >
		<td>附件(点击可下载或查看)</td>
		<td>版本</td>
		<td>最终附件类别</td>
		<td>上传人</td>
		<td>上传时间</td>
		<td>操作</td>
	</tr>
	<c:if test="${empty docList}">
		<tr class=TableData>
			<td colspan="6" align="center" height="25">暂无附件</td>
		</tr>	
	</c:if>
	<c:forEach items="${docList}" var="doc">
		<tr class=TableData onmouseover="this.style.backgroundColor='#DFE8F6';" onmouseout="this.style.backgroundColor='#FFFFFF';">
			<td>
		    	<img align="middle" src="./include/img/workflow/<c:out value="${doc.icon}"/>"/>
		    	<a class=attach_name href="WfDocDownload.shtml?wfDoc.docId=<c:out value="${doc.docId}"/>">
		    		<c:out value="${doc.docName}"/>
		    	</a><br>
		    </td>
		    <td><c:out value="${doc.docVer}"/></td>
			<td><c:out value="${doc.cateName}"/></td>
			<td><c:out value="${doc.createName}"/></td>
			<td><c:out value="${doc.createDate}"/></td>
			<td>
				<input type="button" class="btn" value="删除" onclick="deleteFile('<c:out value="${doc.docId}"/>');">&nbsp;&nbsp;
				
				<c:if test="${doc.deptDocId==0}">
					<input type="button" class="btn" value="归档" onclick="copyToDeptDoc('<c:out value="${doc.docId}"/>');">
				</c:if>
				<c:if test="${doc.deptDocId!=0}">
					<input type="button" class="btn" value="查看归档记录" onclick="setListFrmSrc('DocList.shtml?doc.docId=<c:out value="${doc.deptDocId}"/>');">
				</c:if>
			</td>
		</tr>
	</c:forEach>
</table>
<!-- 附件 -->
<br/>
<br/>


<!-- 会签 -->
<c:if test="${!empty signList}">
<table style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 borderColor=#b8d1e2 cellpadding=3 width="90%" align=center>
  <tr class=TableHeader align="left" >
    <td>会签与意见</td>
 </tr>
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
<!-- 会签 -->


<br>
<br>

<!-- 子工作流-->
<c:if test="${!empty relateFlows}">
<table style="BORDER-COLLAPSE: collapse" class=small border=1 cellspacing=0 cellpadding=3 width="90%" align=center>
	<tr class=TableHeader>
		<td colspan="9"><b>相关工作流</b></td>
	</tr>
	
	<c:if test="${!empty childList}">
	<tr bgcolor="#EFF0F2" >
		<td>流程编号</td>
		<td>流程名称</td>
		<td>计划开始时间</td>
		<td>计划完成时间</td>
		<td>实际开始时间</td>
		<td>实际完成时间</td>
		<td>状态</td>
		<td>发起人</td>
		<td>发起时间</td>
	</tr>
	<c:forEach items="${childList}" var="wfinfo">
			<tr bgcolor="#FFFFFF" onmouseover="this.style.backgroundColor='#DFE8F6';" onmouseout="this.style.backgroundColor='#FFFFFF';">
				<td height="20" align="center"><a href="#" onclick="openRelateFlow('<c:out value='${wfinfo.wfNo}'/>');"><c:out value="${wfinfo.wfNo}"/></a></td>
				<td><c:out value="${wfinfo.wfName}"/></td>
				<td align="center"><fmt:formatDate value="${wfinfo.planSDate}"/></td>
				<td align="center"><fmt:formatDate value="${wfinfo.planEDate}"/></td>
				<td align="center"><fmt:formatDate value="${wfinfo.factSDate}"/></td>
				<td align="center"><fmt:formatDate value="${wfinfo.factEDate}"/></td>
				<td align="center">
					<c:choose>
						<c:when test="${wfinfo.status==0}"><font color="red">待处理</font></c:when>
						<c:when test="${wfinfo.status==1}"><font color="blue">办理中</font></c:when>
						<c:when test="${wfinfo.status==2}"><font color="green">已完成</font></c:when>
						<c:when test="${wfinfo.status==3}"><font color="gray">已关闭</font></c:when>
						<c:when test="${wfinfo.status==4}"><font color="gray">已作废</font></c:when>
					</c:choose>
				</td>
				<td><c:out value="${wfinfo.createName}"/></td>
				<td align="center"><fmt:formatDate value="${wfinfo.createDate}"/></td>
			</tr>
	</c:forEach>
	</c:if>
	
	<tr>
    	<td colspan="9">
    		<table border="0" width="100%">
    		<tr>
	    		<td align="left">选择要启动的子流程：
		    		<c:forEach items="${relateFlows}" var="flow" varStatus="s">
				    	<input type="radio" name="relate" value="<c:out value="${flow.relateId}"/>" 
				    		<c:if test="${s.count==1}">checked</c:if>>
				    	<c:out value="${flow.flowName}"/>
					</c:forEach>
	    		</td>
	    		<td align="right">
	    			<input type="button" value="启动" onclick="startRelateFlow('<c:out value="${wfRd.wfNo}"/>');">
	    		</td>
    		</tr>
    		</table>
		</td>
	</tr>
</table>
</c:if>
<!-- 子工作流-->

<br>
<br>

<!-- 
	<table width="90%">
		<tr>
			<td height="24" width="90" align="center">计划开始时间：</td><td><input type="text" id="planSDate" name="wfRd.planSDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.planSDate}"/>"/></td>
			<td height="24" width="90" align="center">计划完成时间：</td><td><input type="text" id="planEDate" name="wfRd.planEDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.planEDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">实际开始时间：</td><td><input type="text" id="factSDate" name="wfRd.factSDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.factSDate}"/>"/></td>
			<td height="24" width="90" align="center">实际完成时间：</td><td><input type="text" id="factEDate" name="wfRd.factEDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.factEDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">创建日期：</td><td><input type="text" id="createDate" name="wfRd.createDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.createDate}"/>"/></td>
			<td height="24" width="90" align="center">更新日期：</td><td><input type="text" id="lastUpdDate" name="wfRd.lastUpdDate" size="30" validate="{required:true}" value="<c:out value="${wfRd.lastUpdDate}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">进度任务：</td><td><input type="text" id="scheId" name="wfRd.scheId" size="30" validate="{required:true}" value="<c:out value="${wfRd.scheId}"/>"/></td>
			<td height="24" width="90" align="center">工作流定义ID：</td><td><input type="text" id="flowId" name="wfRd.flowId" size="30" validate="{required:true}" value="<c:out value="${wfRd.flowId}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">状态：</td><td><input type="text" id="status" name="wfRd.status" size="30" validate="{required:true}" value="<c:out value="${wfRd.status}"/>"/></td>
			<td height="24" width="90" align="center">创建人：</td><td><input type="text" id="createBy" name="wfRd.createBy" size="30" validate="{required:true}" value="<c:out value="${wfRd.createBy}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">更新人：</td><td><input type="text" id="lastUpd" name="wfRd.lastUpd" size="30" validate="{required:true}" value="<c:out value="${wfRd.lastUpd}"/>"/></td>
			<td height="24" width="90" align="center">工作流标题：</td><td><input type="text" id="wfName" name="wfRd.wfName" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfName}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">工作流内容：</td><td><input type="text" id="wfDesc" name="wfRd.wfDesc" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfDesc}"/>"/></td>
			<td height="24" width="90" align="center">工作流编号：</td><td><input type="text" id="wfNo" name="wfRd.wfNo" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfNo}"/>"/></td>
		</tr><tr>
			<td height="24" width="90" align="center">项目编号：</td><td><input type="text" id="projectNo" name="wfRd.projectNo" size="30" validate="{required:true}" value="<c:out value="${wfRd.projectNo}"/>"/></td>
			<td height="24" width="90" align="center">上级工作流：</td><td><input type="text" id="preWfNo" name="wfRd.preWfNo" size="30" validate="{required:true}" value="<c:out value="${wfRd.preWfNo}"/>"/></td>
		</tr>

	</table>
 -->
</form>
</div>


<script type="text/javascript">
$(function(){
	tableColor($(".lcjd"),"#fff","#f3faff","#cde3f3")
})
</script>
</body>
</html>