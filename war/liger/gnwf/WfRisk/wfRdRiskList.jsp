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
<script src="./include/js/gnwf/wfRisk.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<script type="text/javascript">
</script>
</head>
<body style="padding:0px;">
<div id="layout1" style="overflow-x: hidden;overflow-y:hidden;">

	<div position="center" id="framecenter">

<div class="l-loading" style="display: block" id="pageloading"></div>
<form id="form1">
			<div id="toolbar"></div>
			<div id="sform" style="margin:10px;height:30px;">
				<input type="hidden" id="sys_usrid" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
				<input type="hidden" id="wfNo"  name = "wfNo" value="<c:out value="${wfNo}"/>"/>
				<input type="hidden" id="taskCount"/>
				<table>
					<tr>
					<td height="24" width="90" align="center">部门：</td>
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
					
					 <td height="24" width="90" align="center">
					 <input id="upBtn" type="button" value=" 查询" class="wfbigbtn2"  onclick="sea();" />
					 </td>
					 
					 <td height="24" width="90" align="center" style="display: ">
					 	<input id="upBtn" type="button" value=" 导出"  class="wfbigbtn2" onclick="exportRisk();" />
					 </td>
					 
					</tr>
					
				</table>
			</div>
			
			<div id="maingrid" style="margin-top:1px;margin-left:1px;"></div>
			
			</form>
</div>

<%@ include file="/liger/gnwf/WfRd/mailpop.jsp"%>
</body>
</html>