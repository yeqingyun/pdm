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

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>

<script src="./include/js/gnwf/wfRisk.js" type="text/javascript"></script>
<script language="JavaScript" src="./include/js/common.js"></script>

<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>

<script type="text/javascript">

var prjtNomanager;
var wfQues_wfRdheIdmanager;
$(function () {
	$("#comId").ligerComboBox({ url:"Com!loadComs.shtml", textField:'text', valueField:'id', isMultiSelect: false,selectBoxWidth:180,
		onSelected: function (newvalue)
		{
			changeCompany("#deptId",newvalue);
		},
		onSuccess: function() {
			$("#comId").ligerComboBox().selectValue("<c:out value="${wfQues.comId}"/>");
		}
		});
		$("#deptId").ligerComboBox({url:"Dept!selectDept.shtml?dept.parent=<c:out value="${wfQues.comId}"/>",textField:'deptNm', valueField:'deptId', selectBoxWidth:180,isMultiSelect: false,
				onSelected: function(newvalue) {
					changeDept("#userId",newvalue);
				},
				onSuccess: function() {
					$("#deptId").ligerComboBox().selectValue("<c:out value="${wfQues.deptId}"/>");
				}
			}
		);
	 $("#userId").ligerComboBox({url:"Usr!selectUser.shtml?usr.deptId=<c:out value="${wfQues.deptId}"/>",textField:'usrName', valueField:'id',isMultiSelect: false, selectBoxWidth:180,
		 
		 	onSuccess: function() {
				$("#userId").ligerComboBox().selectValue("<c:out value="${wfQues.userId}"/>");
			}
	 }); 
	if ($("#idtfDate").length > 0)
		$("#idtfDate").ligerDateEditor({showTime: false});
       check();
	if ($("#title").length > 0)
		$("#title").ligerTextBox({width:598});
	if ($("#cateId").length > 0)
		$("#cateId").ligerComboBox({width:235});
	
	
	if ($("#wfRisk_ResponsibleUserName").length > 0)
		$("#wfRisk_ResponsibleUserName").ligerTextBox({width:235});
	$("#prjtNo").ligerComboBox({ url:"./PrjtDef!loadPrjtTree.shtml", textField:'prjtNm',  valueField:'prjtNo', width:235, isMultiSelect: false});
    prjtNomanager = $("#prjtNo").ligerGetComboBoxManager();
	//$("#wfQues_wfRd").ligerComboBox({data: null, textField:'wfName', valueField:'wfNo',width:235,  isMultiSelect: false });
	//wfQues_wfRdmanager = $("#wfQues_wfRd").ligerGetComboBoxManager(); 
	if($("#prjtNo").length > 0){
	  $.ligerui.get("prjtNo").selectValue(document.getElementById("wfRisk_prjtNo").value);
	}
    if($("#reload").val()==1){
	    document.getElementById("savBtn").style.display = "none";
		document.getElementById("closeBtn").style.display = "";
		document.getElementById("uploadBut").disabled=false;
		document.getElementById("uploadBut").className="wfbigbtn2";
		document.getElementById("uploadBut").value = "修改附件";
    }
});

var dialog33;
function selcResponsible(){
	dialog33 = $.ligerDialog.open({title:'选择风险责任人', height: 400, width: 450,url: './WfRisk!selcResponsible.shtml?prjtNo='+prjtNomanager.getValue()});
}

function setUsrData(usrId,usrName){
	$("#responsibleUserName").val(usrName);
	$("#responsibleUID").val(usrId);
}

function uploadFile() {
    var gngile_uploadURL = $('#gngile_upload_URL').val();
	var server_URL = $('#server_URL').val();

    var url = gngile_uploadURL
	//'http://gnfile.gionee.com:28080/gnfs/GnFileService!upload.shtml?' //文件服务器的上传的URl
      /**RUL后面跟的参数 **/
	         +'?syId='+$("#syId").val()    //系统ID
	         +'&syNm='+"PDM"  //系统名字
	         +'&usrId='+$("#usrId").val() //用户ID
	         +'&usrNm='+$("#usrNm").val() //用户名字
	         +'&diyFolder='+"RiskFile" //文件上传的目录中自定义部分
	         +'&uploadType='+"BaseLib" 
         //上传的是ProcFile，取值只能是ProcFile或者BaseLib
         //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
         +'&fileVersion='+"1.01" //文件版本号，自己在业务逻辑中确定
	         +'&callBackUrl='+server_URL+"/afterUploadAction!riskAfterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
         +'&tempParams='+"wfRisk.riskId:"+$("#riskId").val().trim();  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
      /**RUL后面跟的参数 **/
url=encodeURI(url);  //encodeURI
//var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
var dialog = $.ligerDialog.open({ url: url, height: 200,width: 490, 
	buttons: [ { text: '完成', onclick: function (item, dialog) { 
		dialog.close(); 
		window.location = "./WfRisk!addRiskUI.shtml?reload=1&wfRisk.riskId="+$("#riskId").val();
	} } ] });
//监听Dialog关闭事件
$(".l-dialog-winbtns").click(function(){
		window.location = "./WfRisk!addRiskUI.shtml?reload=1&wfRisk.riskId="+$("#riskId").val();
});
}
</script>
</head>
<body>
<div id="toolbar"></div>
<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<form>
    <input type="hidden" id="reload" name="reload" size="30" value="<c:out value="${reload}"/>"/>
    <input type="hidden" id="wfRisk_prjtNo" name="wfRisk_prjtNo" size="30" value="<c:out value="${wfRisk.prjtNo}"/>"/>
	<input type="hidden" id="quesId" name="wfRisk.quesId" size="30" value="<c:out value="${wfRisk.quesId}"/>"/>
	<input type="hidden" id="scheId" name="wfRisk.scheId" size="30" value="<c:out value="${wfRisk.scheId}"/>"/>
	<input type="hidden" id="riskId" name="wfRisk.riskId" size="30" value="<c:out value="${wfRisk.riskId}"/>"/>

	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    <input type="hidden" id="responsibleUID" name="responsibleUID" size="30" value="<c:out value="${responsibleUID}"/>"/>
    <input type="hidden" id="wfQues_usrName" name="wfQues_usrName" size="30" value="<c:out value="${wfRisk.responsibleUserName}"/>"/>
    
 <style type="text/css">
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

	<div class="listTable clearfix" style="margin:10px 20px;" >
     <table cellpadding=0 cellspacing=0.5 width=700px style="background:#9FC2E5;" class="innerTable"  align=center>
	    <tr>
			<th>项目：<font color="red">&nbsp;*</font></th>
			<td colspan="3">
				<input  type="text" id="prjtNo" name="prjtNo" size="30" validate="{required:true}" 
				value="<c:out value="${prjtNo}"/>" />   
			</td>
		</tr>
	
		<tr>
			<th>提出部门：</th>
			<td><input type="text" id="deptName" name="wfRisk.deptName"  value="<c:out value="${wfRisk.deptName}"/>" />  </td>
			<th>执行日期：</th>
			<td>
			<input type="text" id="executionDate" name="wfRisk.executionDate"  value="<c:out value="${wfRisk.executionDate}"/>"/>
			</td>
		</tr>
		
		
		<tr>
			<th>风险类别：</th>
			<td>
				 <input type="text" id="riskCategory" name="wfRisk.riskCategory"  value="<c:out value="${wfRisk.riskCategory}"/>"/>
			</td>
			
				<td ><input type="button" id="responsibleBtn" name="responsibleBtn" size="30"  class="wfbigbtn2"  onclick="selcResponsible()" 
				 value="责任人"/><font color="red">&nbsp;*</font>
				</td>
				<td colspan="3">
					<input type="text" id="responsibleUserName" name="responsibleUserName" readonly="readonly" validate="{required:true}" value="<c:out value="${wfRisk.responsibleUserName}"/>"/>
				</td>
		</tr>
		
		<tr>
			<th>风险描述：<font color="red">&nbsp;*</font></th>
			<td colspan="3">
				<textarea id="description" name="description" style="width:600px;height: 200px;" validate="{required:true}"><c:out value="${wfRisk.description}"/></textarea>  
			</td>
		</tr>
		
    <tr id="savBtn"  >
	   <td colspan="4">
		    <table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0  align=right>
				<tr>
					<td align="right">
						<input  type="button" value="  提交风险  " class="wfbigbtn2" onclick="sav();"/>
					</td>
				</tr>
			 </table>
	   </td>
	</tr>
	
	<tr id = "upload" style="display:">
	   <th>附件：</th>
	   <td colspan="3">
      	 <img align="middle" src="./include/img/workflow/<c:out value="${wfRisk.fileIcon}"/>"/>
    	 <a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfRisk.fileNo}"/>'>
    	 <c:out value="${wfRisk.fileName}"/>
    	 </a>
	     <table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0  align=right>
			<tr>
				<td align="right">
					<input id="uploadBut" type="button" value="  上传附件  " disabled="disabled" onclick="uploadFile('file');" />
				</td>
			</tr>
		 </table>
	   </td>
	</tr>
</table>
</div> 
<br/>
<br/>
 
<div id = "closeBtn" style="display: none"> 
	<table style="BORDER-COLLAPSE: collapse" class="small" border=0 cellspacing=0 cellpadding=0 width="700" align="center">
		<tr>
			<td align="right">
				<input  type="button" value="  完成  " class="wfbigbtn"  onclick="window.parent.DialogMgr.close();" />
			</td>
		</tr>
	 </table>
 </div>
</form>
</div>
</body>
</html>