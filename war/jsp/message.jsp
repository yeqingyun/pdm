<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>GIONEE</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="./include/js/oa.js"></script>
	<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
	<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
	<script type="text/javascript">
		function uriReload(param) {
			if(window.top!=window.self && param !='login.shtml') {
				location.href=param;
			}
			else {
				window.parent.location.href=param;
			}
		}

		function touri(param) {
				window.parent.location.href=param;
		}
		
		//ˢ�´����б����
		//提交任务成功后刷新我的任务、流程管理、我的问题、问题管理、风险管理
		$(function () {
			var isReloadGrid = document.getElementById("isReloadGrid")
			
			if(isReloadGrid.value!=null && isReloadGrid.value=="true"){
				
				if(window.parent.taskWinow){
					window.parent.taskWinow.frame.gridManager.loadData();
				}
				if(window.parent.wflistWinow){
					window.parent.wflistWinow.frame.gridManager.loadData();
				}
				if(window.parent.quesWinow){
					window.parent.quesWinow.frame.gridManager.loadData();
				}
				if(window.parent.quesManagerWinow){
					window.parent.quesManagerWinow.frame.gridManager.loadData();
				}
				if(window.parent.riskWinow){
					window.parent.riskWinow.frame.gridManager.loadData();
				}
				if(window.parent.parent.riskWinow){
					window.parent.parent.riskWinow.frame.gridManager.loadData();
				}
			}
		});

		//刷新我的任务数量
	 	$(function () {
			jQuery.ajax({
			       type:"post",  
			       url:"./WfRd!countTask.shtml",  
			       dataType:"json",
			       success: function fun1(data) {  
			    	   //alert(data.Total);
			       }
			});  
		}); 
	</script>
</head>

<body>
<input type="hidden" id="isReloadGrid" value="<c:out value="${isReloadGrid}"/>">

<div class="frmInsetCon">
<br><br>
<div class="prompt">
	<div class="tips">
	<p class="btnbg t"><span class="btnbg">系统提示信息</span></p>
		<div class="tipsCon">
			<table cellpadding=0 cellspacing=0>

	<tr>
		<td style="text-align:left;">
		<p>
		<font color="#dc143c"><c:out value="${msg}" escapeXml="false"/></font>
			<c:if test="${redirect!=''}">
				<input type="button" id="determineBtn" value="确定" class="btnbg"  onclick="uriReload('<c:out value="${redirect}"/>');"/>
			</c:if>
			<c:if test="${goback!=null&&goback!=''}">
				<input type="button" id="returnBtn" value="返回" class="btnbg"   onclick="touri('<c:out value="${goback}"/>');"/>
			</c:if>
		</p>
			
		</td>
	</tr>

</table>
</div>
</div>
</div>
</div>
</body>

</html>

