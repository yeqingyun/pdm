<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
	.flowGrid td {
		border-bottom:1px solid white;
		border-left: 1px solid white;
	}
	.fchild td {
		border-bottom:0px;
		border-left: 0px;
	}
</style>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script src="./include/js/gnwf/wfJob.js" type="text/javascript"></script>

<script type="text/javascript">
$(function () {
	$.post("ligerToolBar1.shtml",
			{parm:'WfJob'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	);
       check();
       if ($("#comNm").length > 0)
   		$("#comNm").ligerTextBox({width:130});
       if ($("#deptNm").length > 0)
   		$("#deptNm").ligerTextBox({width:120});
       if ($("#jobName").length > 0)
   		$("#jobName").ligerTextBox({width:120});
});

function canleDeal(chkBox,index) {
	if(!chkBox.checked) {
		var arrFile = $("input[name='file"+index+"']");
		var arrAnnex = $("input[name='annex"+index+"']");
		takeNotCheck(arrFile);
		takeNotCheck(arrAnnex);
	}
}
function takeNotCheck(chkBoxs) {
	if(chkBoxs != null && chkBoxs.length > 0) {
		for (var i=0;i<chkBoxs.length;i++) {
			if(chkBoxs[i].checked) {
				chkBoxs[i].checked = false;
			}
	    }
	}
}
</script>
</head>
<body>

<div id="toolbar"></div>


<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<form>
</form>
	<input type="hidden" name="wfJob.jobId" id="jobId" value="<c:out value="${hrJobCfg.jobId}"/>"/>
	<table width="95%">
		<tr>
			<td height="24" width="90" align="center">所在公司：</td><td><input type="text" readonly="readonly" id="comNm" size="30"  value="<c:out value="${hrJobCfg.comNm}"/>"/></td>
			<td height="24" width="90" align="center">所在部门:</td><td><input type="text" readonly="readonly" id="deptNm" size="30" value="<c:out value="${hrJobCfg.deptNm}"/>"/></td>
			<td height="24" width="90" align="center">工作岗位：</td><td><input type="text" readonly="readonly" id="jobName" size="30" value="<c:out value="${hrJobCfg.jobName}"/>"/></td>
		</tr>
		<tr>
			<td colspan="6">
				<table align="center" width="100%" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr bgcolor="#EFF0F2">
						<td colspan="10" width="10%" height="24">工作岗位人员</td>
					</tr>
					
					<c:set var="y" value="0"/>
					<c:forEach items="${usrs}" var="usrs">
						<c:if test="${y%10==0}"><tr></c:if>
						<td width="10%" height="24">
							<input type="checkbox" id="usr<c:out value="${y}"/>" name="_usrs" <c:out value="${usrs.checked}"/> value="<c:out value="${usrs.id}"/>"/><c:out value="${usrs.usrName}"/>
						</td>
						<c:if test="${y%10==9}"></tr></c:if>
						<c:set var="y" value="${y+1}"/>
					</c:forEach>
					<c:if test="${!(y%10==9)}"></tr></c:if>
				</table>
				
				<table align="center" width="100%" class="flowGrid" cellpadding="4" cellspacing="4" bgcolor="#D3E1F1">
					<tr><td width="100%" height="24" colspan="7" bgcolor="#EFF0F2">工作岗位授权</td></tr>
					<tr bgcolor="#EFF5FE">
						<td rowspan="2" width="33%">工作流名称</td>
						<td width="33%" colspan="3" align="center">文档</td>
						<td colspan="3" align="center">附件</td>
					</tr>
					<tr bgcolor="#EFF5FE">
						<td align="center">浏览</td>
						<td align="center">下载</td>
						<td align="center">更新</td>
						<td align="center">浏览</td>
						<td align="center">下载</td>
						<td align="center">更新</td>
					</tr>
					<c:set var="xx" value="0"/>
					<c:forEach items="${wfJobs}" var="wfJob">
						<tr>
							<td width="33%" height="24">
								<input type="checkbox" onchange="canleDeal(this,<c:out value="${xx}"/>)" id="flow<c:out value="${xx}"/>" name="_flows" <c:out value="${wfJob.checked}"/> value="<c:out value="${wfJob.flowId}"/>"/><c:out value="${wfJob.flowName}"/>
							</td>
							<td align="center" colspan="3">
								<table width="100%" class="fchild">
									<tr>
										<td align="center" width="33%">
											<input type="radio" name="file<c:out value="${xx}"/>" <c:if test="${wfJob.isUpdOrLoad==1}">checked</c:if> 
											/>
										</td>
										<td align="center" width="33%">
											<input type="radio" name="file<c:out value="${xx}"/>"<c:if test="${wfJob.isUpdOrLoad==2}">checked</c:if> 
											/>
										</td>
										<td align="center" width="33%">
											<input type="radio" name="file<c:out value="${xx}"/>" <c:if test="${wfJob.isUpdOrLoad==3}">checked</c:if> 
											/>
										</td>
									</tr>
								</table>
							</td>
							<td align="center" colspan="3">
								<table width="100%" class="fchild">
									<tr>
										<td align="center" width="33%">
											<input type="radio" name="annex<c:out value="${xx}"/>" <c:if test="${wfJob.annexUpdOrLoad==1}">checked</c:if> 
											/>
										</td>
										<td align="center" width="33%">
											<input type="radio" name="annex<c:out value="${xx}"/>" <c:if test="${wfJob.annexUpdOrLoad==2}">checked</c:if> 
											/>
										</td>
										<td align="center" width="33%">
											<input type="radio" name="annex<c:out value="${xx}"/>" <c:if test="${wfJob.annexUpdOrLoad==3}">checked</c:if> 
											/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>

	</table>

</div>

</body>
</html>