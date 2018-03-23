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
		//提交任务成功后刷新我的任务
		$(function () {
			var isReloadGrid = document.getElementById("isReloadGrid")
			//alert(isReloadGrid.value);
			if(isReloadGrid.value!=null && isReloadGrid.value=="true"){
				
				if(window.parent.taskWinow){
					//console.dir(window.parent.taskWinow.frame.gridManager);
					window.parent.taskWinow.frame.gridManager.loadData();
				}
				if(window.parent.wflistWinow){
					window.parent.wflistWinow.frame.gridManager.loadData();
				}
				if(window.parent.parent.wflistWinow){
					window.parent.parent.wflistWinow.frame.gridManager.loadData();
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

		<font color="#dc143c"><c:out value="${msg}" escapeXml="false"/></font>
			
</body>

</html>

