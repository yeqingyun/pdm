<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<head>
<title>Untitled</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	
<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>
<script src="./include/js/gnwf/wfReply.js" type="text/javascript"></script>
	
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="./include/js/oa.js"></script>


<script type="text/javascript"> 


    function upQuesResp(id,status){
    	 $.post("QuesResp!sav.shtml?quesResp.id ="+id+"&quesResp.status="+status,
    				function(data) {
    					 $.ligerDialog.success(data);
    				},
    				"text"
    	);
    }
    
    //挂起
    function hangup(){
    	$.post("WfQues!hangup.shtml?wfQues.quesId ="+id+"&quesResp.status="+status,
				function(data) {
					 $.ligerDialog.success(data);
				},
				"text"
	    );
    }
    
    //挂起
    function goRisk(){
    	$.post("WfQues!hangup.shtml?wfQues.quesId ="+id+"&quesResp.status="+status,
				function(data) {
					 $.ligerDialog.success(data);
				},
				"text"
	    );
    }
    
    //关闭问题
    function closQues(){
    	$.post("WfQues!hangup.shtml?wfQues.quesId ="+id+"&quesResp.status="+status,
				function(data) {
					 $.ligerDialog.success(data);
				},
				"text"
	    );
    }
    
    //修改问题责任人
    function updateQuesUsrs(){
    	$.post("WfQues!hangup.shtml?wfQues.quesId ="+id+"&quesResp.status="+status,
				function(data) {
					 $.ligerDialog.success(data);
				},
				"text"
	    );
    }
    
    function showRemarkDialog(var i){
    	
    	if(i==1){//不通过
    		
    	}else if(i==2){//挂起
    		
    	}else if(i==3){//走风险
    		
    	}else if(i==4){//退回重新验证
    		
    	}
    	
    	
    	
    }
    
    
    var dis = false; 	
    function showFlexPage(){
   	 if(dis){
   		    document.getElementById("flexpage").style.display = "none";
   			document.getElementById("showFlexBtn").value= "浏览更新文档";
   			dis = false;
   	 }else{
   		    document.getElementById("flexpage").style.display = "";
   			document.getElementById("showFlexBtn").value= "隐藏更新文档";
   			dis = true;
   	 }
   	
   }
</script>
</head>

<body>
<div class="frmInsetCon">

<form name="docFlowCheckForm" method="post" action="DocFlowCheck.shtml" onsubmit="return check();">
<input type="hidden" id="flowDocVersion.id" name="flowDocVersion.id" value="<c:out value="${flowDocVersion.id}"/>">
<input type="hidden" id="docFlowStep.id" name="docFlowStep.id" value="<c:out value="${docFlowStep.id}"/>">
<input type="hidden" id="flowDoc.id" name="flowDoc.id" value="<c:out value="${flowDoc.id}"/>">
<input type="hidden" id="docFlow.id" name="docFlow.id" value="<c:out value="${docFlow.id}"/>">
<input type="hidden" id="docFlowStep.isLastStep" name="docFlowStep.isLastStep" value="<c:out value="${docFlowStep.isLastStep}"/>">
<input type="hidden" id="flowDocVersion.createBy" name="flowDocVersion.createBy" value="<c:out value="${flowDocVersion.createBy}"/>">
<input type="hidden" id="checkSort" name = "checkSort" value="<c:out value="${checkSort}"/>">
<input type="hidden" id="accepterId" name = "accepterId" value="<c:out value="${accepterId}"/>">
<input type="hidden" id="docFlow.nextStepSort" name="docFlow.nextStepSort" value="<c:out value="${docFlow.nextStepSort}"/>">
<style type="text/css">
.innerTable th,.innerTable td{border-right:1px solid #9FC2E5;border-bottom:1px solid #9FC2E5;background:#fff;text-align:left;padding:0 7px;}
</style>






<!-- 问题内容  -->
<br>
<table id="quesTB" width="100%" border="0" cellspacing=0 cellpadding=0>
<tr>
	<td width="3%">&nbsp;</td>
	<td>
		<IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>问题</B></SPAN>
	</td>
</tr>
</table>

<div class="listTable clearfix" style="margin:10px 20px;" >
<table cellpadding=0 cellspacing=0.5 width=90% style="background:#9FC2E5;" class="innerTable"  align=center>
	<tr>
		<th width="10%">标题</th>
		<td colspan="5"><c:out value="${wfQues.title}"/></td>
	</tr>
	
	<tr>
	    <th>描述</th>
	    <td  colspan="5"><textarea name="replyText" id="replyText" disabled="disabled" style="height: 100px;width: 100%;border-color: #D6E3EF"><c:out value="${wfQues.quesText}"  escapeXml="false"/></textarea></td>
	</tr>
	<tr>
	    <th><a href="" style="background:green">附件</a></th>
		<td colspan="5">
		<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
	    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
	    	 <c:out value="${wfQues.resultFileName}"/>
	    	</a>
		</td>
	</tr>
	<tr>
		<th><input id="nextBtn" type="button" value="责任人" class="wfbigbtn" width="100%" onclick="approve();" /></th>
		<td colspan="5">堆叠工程师--马致远;结构工程师--张三;软件工程师--李四;</td>
	</tr>
	
	<tr>
	  <th >提出人</th>
	  <td  ><c:out value="${wfQues.createUsr}"/></td>
	  <th>提出时间</th>
	  <td><fmt:formatDate type="both"   value="${wfQues.createDate}"/></td>
	  <th >状态</th>
	  <td ><select id="wfQues_status" name="wfQues_status"  style="width: 100px">
				    <option value="">请选择</option>
					<option value="1">新问题</option>
					<option value="9">未通过</option>
					<option value="10">已回复</option>
					<option value="11">通过</option>
					<option value="20">走风险</option>
					<option value="21">已挂起</option>
					<option value="30">已关闭</option>
					
			</select>&nbsp;&nbsp;<font color="red">*&nbsp;</font></td>
	</tr>
	
</table>
</div>








<!--  解决措施  -->
<br>
<br>
<table width="100%" border="0" cellspacing=0 cellpadding=0>
<tr>
	<td width="3%">&nbsp;</td>
	<td>
		<IMG align=top src="./include/img/workflow/green_arrow.gif"><SPAN class=big3> <B>解决措施</B></SPAN>
	</td>
</tr>
</table>

<div class="listTable clearfix" style="margin:10px 20px;" >
	<table cellpadding=0 cellspacing=0.5 width=90%  style="background:#9FC2E5;" class="innerTable"  align=center>
	<tr>
		<th width="10%" style="background:green">责任人</th>
		<td >堆叠工程师--马致远</td>
		
	    <th>时间</th>
	 	<td colspan="3"><fmt:formatDate type="both"   value="${wfQues.createDate}"/></td>
		
	</tr>
	
	<tr>
	    <th>措施</th>
		<td colspan="5"><textarea style="height: 100px;width: 100%;border-color: #D6E3EF">黄日华丰盛町后三个地方各位瓦尔特我亲热污染和认购后 </textarea></td>
	</tr>
	<tr>
	 <th>措施附件</th>
		<td colspan="5">
		<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
	    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
	    	 <c:out value="${wfQues.resultFileName}"/>
	    	</a>
		</td>
		</tr>
		<tr>
		</tr>
	<tr>
	
	<tr>
		<th width="10%">验证人</th>
		<td >结构工程师--QA</td>
		
	    <th>时间</th>
	 	<td><fmt:formatDate type="both"   value="${wfQues.createDate}"/></td>
		 <th >验证效果</th>
		<td ><input id="nextBtn" type="button" value=" 无效" class="wfbigbtn"  onclick="approve();" />
				<input id="nextBtn" type="button" value=" 有效" class="wfbigbtn" onclick="reject();" />
	</tr>
	
	<th>验证说明</th>
	<td  colspan="5"><textarea name="replyText" id="replyText"  style="height: 100px;width: 100%;border-color: #D6E3EF"><c:out value="${wfReply.replyText}"/></textarea></td>
	</tr>
	
	<tr>
	<th>验证附件</th>
		<td colspan="5">
		<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
	    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
	    	 <c:out value="${wfQues.resultFileName}"/>
	    	</a>
		</td>
		</tr>
</table>
<br>
<table cellpadding=0 cellspacing=0.5 width=90%  style="background:#9FC2E5;" class="innerTable"  align=center>
	<tr>
		<th width="10%" style="background:green">责任人</th>
		<td >结构工程师--张三</td>
		
	    <th>时间</th>
	 	<td colspan="3"><fmt:formatDate type="both"   value="${wfQues.createDate}"/></td>
		
	</tr>
	
	<tr>
	    <th>措施</th>
		<td colspan="5"><textarea style="height: 100px;width: 100%;border-color: #D6E3EF">黄日华丰盛町后三个地方各位瓦尔特我亲热污染和认购后 </textarea></td>
	</tr>
	<tr>
	 <th>措施附件</th>
		<td colspan="5">
		<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
	    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
	    	 <c:out value="${wfQues.resultFileName}"/>
	    	</a>
		</td>
		</tr>
		<tr>
		</tr>
	<tr>
	
	<tr>
		<th width="10%">验证人</th>
		<td >结构工程师--QA</td>
		
	    <th>时间</th>
	 	<td><fmt:formatDate type="both"   value="${wfQues.createDate}"/></td>
		 <th >验证效果</th>
		<td ><input id="nextBtn" type="button" value=" 无效" class="wfbigbtn"  onclick="approve();" />
				<input id="nextBtn" type="button" value=" 有效" class="wfbigbtn" onclick="reject();" />
	</tr>
	
	<th>验证说明</th>
	<td  colspan="5"><textarea name="replyText" id="replyText"  style="height: 100px;width: 100%;border-color: #D6E3EF"><c:out value="${wfReply.replyText}"/></textarea></td>
	</tr>
	
	<tr>
	<th>验证附件</th>
		<td colspan="5">
		<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
	    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
	    	 <c:out value="${wfQues.resultFileName}"/>
	    	</a>
		</td>
		</tr>
</table>

<br>
<table cellpadding=0 cellspacing=0.5 width=90%  style="background:#9FC2E5;" class="innerTable"  align=center>
	<tr>
		<th width="10%" style="background:green">责任人</th>
		<td >软件工程师--李四</td>
		
	    <th>时间</th>
	 	<td colspan="3"><fmt:formatDate type="both"   value="${wfQues.createDate}"/></td>
		
	</tr>
	
	<tr>
	    <th>措施</th>
		<td colspan="5"><textarea style="height: 100px;width: 100%;border-color: #D6E3EF">黄日华丰盛町后三个地方各位瓦尔特我亲热污染和认购后 </textarea></td>
	</tr>
	<tr>
	 <th>措施附件</th>
		<td colspan="5">
		<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
	    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
	    	 <c:out value="${wfQues.resultFileName}"/>
	    	</a>
		</td>
		</tr>
		<tr>
		</tr>
	<tr>
	
	<tr>
		<th width="10%">验证人</th>
		<td >结构工程师--QA</td>
		
	    <th>时间</th>
	 	<td><fmt:formatDate type="both"   value="${wfQues.createDate}"/></td>
		 <th >验证效果</th>
		<td ><input id="nextBtn" type="button" value=" 无效" class="wfbigbtn"  onclick="approve();" />
				<input id="nextBtn" type="button" value=" 有效" class="wfbigbtn" onclick="reject();" />
	</tr>
	
	<th>验证说明</th>
	<td  colspan="5"><textarea name="replyText" id="replyText"  style="height: 100px;width: 100%;border-color: #D6E3EF"><c:out value="${wfReply.replyText}"/></textarea></td>
	</tr>
	
	<tr>
	<th>验证附件</th>
		<td colspan="5">
		<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
	    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
	    	 <c:out value="${wfQues.resultFileName}"/>
	    	</a>
		</td>
		</tr>
</table>


</div>









<!-- 操作区 -->

<br>
	<table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0 width="730" align=center>
		<tr>
		<td>
			<input type="button" id="showFlexBtn" value="显示措施记录" class="wfbigbtn" onclick="showFlexPage();" />
			</td>
			<td align="right">
				<input id="nextBtn" type="button" value=" 通过" class="wfbigbtn"  onclick="approve();" />
				<input id="nextBtn" type="button" value=" 不通过" class="wfbigbtn" onclick="reject();" />
				<input id="nextBtn" type="button" value=" 挂起" class="wfbigbtn" onclick="guaqi();" />
				<input id="nextBtn" type="button" value=" 提交" class="wfbigbtn" onclick="guaqi();" />
				<input id="nextBtn" type="button" value=" 走风险" class="wfbigbtn" onclick="guaqi();" />
				<input id="nextBtn" type="button" value=" 关闭" class="wfbigbtn" onclick="close();" />
				<input id="nextBtn" type="button" value=" 转发问题" class="wfbigbtn" onclick="guaqi();" />
			</td>
	 </table>
<br>









</form>
</div>
</body>
