<%@page contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfRd.js" type="text/javascript"></script>


<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfRd'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
     if ($("#wfName").length > 0)
   		$("#wfName").ligerTextBox();
   	if ($("#wfDesc").length > 0)
   		$("#wfDesc").ligerTextBox();
	if ($("#planSDate").length > 0)
		$("#planSDate").ligerDateEditor({showTime: false});
	if ($("#planEDate").length > 0)
		$("#planEDate").ligerDateEditor({showTime: false});
	

});

//选择项目组成员
$(function() {
	function syncdata() {
		$("#docDesc").val();
	}
});

var autoid=1;
var editgridManager;
var statusData = [{ Status: -1, text: '未配置人员'},{ Status: 0, text: '无效' }, { Status: 1, text: '有效'}];
var roleTypData = [{ RoleTyp: 0, text: '人员不随项目变更' }, { RoleTyp: 1, text: '人员随项目变更'}];
var upTypData = [{ UpTyp: 1, text: '加入项目组' }, { UpTyp: 0, text: '离开项目组'}];


var selectWin =null;
//open select window


var pop;
function selectPrjtUsers(){
	if($("#prjtNo").val()!=null && $("#prjtNo").val()!=''){
		//alert("fff");
		selectPrjtUsers2();
	}else{
		//alert("ddd");
		pop =  $.ligerDialog.open({title:'选择项目组成员', height: 390, width: 500,url: './PrjtUsr!addMailUser.shtml',
			showMax: true, showToggle: true, showMin: true, isResize: true
		});
	}
}

function closepop(){
	pop.close();
}






</script>
</head>
<body>

<div id="toolbar"></div>

<div id="eform"  style="margin-left:15px;margin-right:15px;margin-top:15px;">

<form id="wfRd-form" name="wfRd-form" >
	<input type="hidden" id="flowId" name="wfRd.flowId" value="<c:out value="${wfRd.flowId}"/>"/>
	<input type="hidden" id="preWfNo" name="wfRd.preWfNo" value="<c:out value="${wfRd.preWfNo}"/>"/>
	<input type="hidden" id="projectNo" name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>"/>
	<!-- 如果是DCC其他变更流程就做提示信息 flowId测试为56 生产是54 -->
	<c:if   test="${wfRd.flowId==54}">
	<div align="center"><font color="#FF0000">DCC其他变更流程启动</font> </div>
	</c:if>
	<table width="90%">
	
		<tr>
			<td height="24" width="90" align="center">工作流名称：</td>
			<td><input type="text" id="wfName" name="wfRd.wfName" size="30" validate="{required:true}" value="<c:out value="${wfRd.wfName}"/>"/></td>
			<td height="24" width="90" align="center">工作流描述：</td>
			<td><input type="text" id="wfDesc" name="wfRd.wfDesc" size="30" value="<c:out value="${wfRd.wfDesc}"/>"/></td>
		</tr>
		<tr>
			<td height="24" width="90" align="center">计划开始时间：</td>
			<td>
				<fmt:formatDate value="${wfRd.planSDate}" var="planStaDate" type="date"/>
				<input type="text" id="planSDate" name="wfRd.planSDate" size="30" ltype="date" value="<c:out value="${planStaDate}"/>"/>
			</td>
			<td height="24" width="90" align="center">计划完成时间：</td>
			<td>
				<fmt:formatDate value="${wfRd.planEDate}" var="planEDate" type="date"/>
				<input type="text" id="planEDate" name="wfRd.planEDate" size="30" ltype="date" value="<c:out value="${planEDate}"/>"/>
			</td>
		</tr>				
			
	</table>


</br>

	


	<c:if   test="${wfRd.flowId==40||wfRd.flowId==42}">
	<input type="button" value="选择项目组成员" onclick="selectPrjtUsers();"></input>
	<input type="text" id="usrString" name="usrString" 
					value="<c:out value="${usrString}"/>" style="width: 650px; height: 18px" readonly="true" /> 
					
			<input type="hidden"  id="usrIds" name="usrIds" 
					value="<c:out value="${usrIds}"/>" style="width: 650px; height: 18px" readonly="true" /> 

	</c:if>			
	</form>

</div>

</body>
</html>