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
<script language="JavaScript" src="./include/js/common.js"></script>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<script type="text/javascript">
$(function () {
	$.validator.addMethod("notnull",
        function (value, element, regexp){
            if (!value) return true;
            return !$(element).hasClass("l-text-field-null");
        },"不能为空"
    );
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        //调试状态，不会提交数据的
        debug: false,
        errorPlacement: function (lable, element){
            if (element.hasClass("l-textarea"))
            {
                element.addClass("l-textarea-invalid");
            }
            else if (element.hasClass("l-text-field"))
            {
                element.parent().addClass("l-text-invalid");
            }
            $(element).removeAttr("title").ligerHideTip();
            $(element).attr("title", lable.html()).ligerTip();
        },
        success: function (lable){
            var element = $("#" + lable.attr("for"));
            if (element.hasClass("l-textarea")){
                element.removeClass("l-textarea-invalid");
            }else if (element.hasClass("l-text-field")){
                element.parent().removeClass("l-text-invalid");
            }
            $(element).removeAttr("title").ligerHideTip();
        },
        submitHandler: function () {
        	updateRisk();
        }
    });
    $("form").ligerForm();
	if ($("#wfRisk_ResponsibleUserName").length > 0)
		$("#wfRisk_ResponsibleUserName").ligerTextBox({width:235});
    if($("#reload").val()==1){
		document.getElementById("closeBtn").style.display = "";
    }
});

var dialog33;
function selcResponsible(){
	dialog33 = $.ligerDialog.open({title:'选择风险责任人', height: 400, width: 450,url: './WfRisk!selcResponsible.shtml?prjtNo='+$("#wfRisk_prjtNo").val()});
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
		window.location = "./WfRisk!editorRiskUI.shtml?reload=1&wfRisk.riskId="+$("#riskId").val();
	} } ] });
//监听Dialog关闭事件
$(".l-dialog-winbtns").click(function(){
		window.location = "./WfRisk!editorRiskUI.shtml?reload=1&wfRisk.riskId="+$("#riskId").val();
});
}

function updateRisk() {
	$.ligerDialog.confirm('确定要更新风险记录？',function(type) {
		if (type) {
			var str = '{';
			if($("#riskId").length > 0) {
				str += '"wfRisk.riskId":"' + $("#riskId").val()
				+ '",';
			}
			if ($("#wfRisk_prjtNo").length > 0) {
				str += '"wfRisk.prjtNo":"'
						+ $("#wfRisk_prjtNo").val() + '",';
			}
			if ($("#riskCategory").length > 0) {
				str += '"wfRisk.riskCategory":"' + $("#riskCategory").val()
						+ '",';
			}
			if ($("#executionDate").length > 0) {
				str += '"wfRisk.executionDate":"' + $("#executionDate").val()
						+ '",';
			}
			if ($("#deptName").length > 0) {
				str += '"wfRisk.deptName":"' + $("#deptName").val()
						+ '",';
			}
			if ($("#description").length > 0) {
				str += '"wfRisk.description":"'
						+ ($("#description").val()).replace(
								/\n/g, "").replace(/"/g, "'")
						+ '",';
			}
			
			
			if ($("#riskConsequence").length > 0) {
				str += '"wfRisk.riskConsequence":"'
						+ ($("#riskConsequence").val()).replace(
								/\n/g, "").replace(/"/g, "'")
						+ '",';
			}
			if ($("#preventiveMeasures").length > 0) {
				str += '"wfRisk.preventiveMeasures":"'
						+ ($("#preventiveMeasures").val()).replace(
								/\n/g, "").replace(/"/g, "'")
						+ '",';
			}
			
			
			if ($("#responsibleUID").length > 0) {
				str += '"wfRisk.responsibleUserId":"'
						+ $("#responsibleUID").val() + '",';
			}
			if ($("#responsibleUserName").length > 0) {
				str += '"wfRisk.responsibleUserName":"'
						+ $("#responsibleUserName").val() + '",';
			}
			if ($("#riskMonitor").length > 0) {
				str += '"wfRisk.riskMonitor":"'
					+ ($("#riskMonitor").val()).replace(
							/\n/g, "").replace(/"/g, "'")
							+ '",';
			}
			str += '}';
			$.post("WfRisk!editorRisk.shtml",
				eval('(' + str + ')'),
				function(data) {
					var s = data.split(":");
					/* if (s[0].indexOf("完成") > -1) {
						document.getElementById("closeBtn").style.display = "";
					} */
					$.ligerDialog.success(s[0]);
					if(window.parent.parent.riskWinow){
						window.parent.parent.riskWinow.frame.gridManager.loadData();
						document.getElementById("closeBtn").style.display = "";
					}
					if(window.parent.riskWinow){
						window.parent.riskWinow.frame.gridManager.loadData();
						document.getElementById("closeBtn").style.display = "";
					}
				}, "text");
		}
	});
}
</script>
</head>
<body>
<div id="toolbar"></div>
<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<form>
    <input type="hidden" id="reload" name="reload" size="30" value="<c:out value="${reload}"/>"/>
	<input type="hidden" id="riskId" name="wfRisk.riskId" size="30" value="<c:out value="${wfRisk.riskId}"/>"/>
	<input type="hidden" id="wfRisk_prjtNo" value="<c:out value="${wfRisk.prjtNo}"/>"/>
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
			<th >项目：<font color="red">&nbsp;*</font></th>
			<td colspan="3"><input type="text" id="prjtNo" name="prjtNo" size="30" value="<c:out value="${wfRisk.prjtNm}"/>" readonly="readonly"  validate="{required:true}" />   </td>
		</tr>
	
		<tr>
			<th>提出部门：</th>
			<td><input type="text" id="deptName" name="wfRisk.deptName"  value="<c:out value="${wfRisk.deptName}"/>" />  </td>
			<th>确认日期：</th>
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
					<input type="text" id="responsibleUserName" name="wfRisk.responsibleUserName" readonly="readonly" validate="{required:true}" value="<c:out value="${wfRisk.responsibleUserName}"/>"/>
				</td>
		</tr>
		
		 <tr>
                            <th nowrap="nowrap">问题说明</td>
                            <td colspan="3"><textarea class="tableTextarea01" disabled style="width:590px;height: 100px;"><c:out value="${wfRisk.description}"  escapeXml="false"/></textarea></td>
                        </tr>
		<tr>
			<th>风险说明及后果：<font color="red">&nbsp;*</font></th>
			<td colspan="3">
				<textarea id="riskConsequence" name="wfRisk.riskConsequence" style="width:590px;height: 150px;" validate="{required:true}"><c:out value="${wfRisk.riskConsequence}"/></textarea>  
			</td>
		</tr>
		<tr>
			<th>拟采取措施：<font color="red">&nbsp;*</font></th>
			<td colspan="3">
				<textarea id="preventiveMeasures" name="wfRisk.preventiveMeasures" style="width:590px;height: 150px;" validate="{required:true}"><c:out value="${wfRisk.preventiveMeasures}"/></textarea>  
			</td>
		</tr>
		<tr>
			<th>风险监控结果：<font color="red">&nbsp;*</font></th>
			<td colspan="3">
				<textarea id="riskMonitor" name="wfRisk.riskMonitor" style="width:590px;height: 150px;" validate="{required:true}"><c:out value="${wfRisk.riskMonitor}"/></textarea>  
			</td>
		</tr>
		
    <tr id="savBtn"  >
	   <td colspan="4">
		    <table style="BORDER-COLLAPSE: collapse" class=small border=0 cellspacing=0 cellpadding=0  align=right>
				<tr>
					<td align="right">
						<input  type="submit" value="  更新风险  " class="wfbigbtn2" />
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
					<input id="uploadBut" type="button" value="  修改附件  "  class="wfbigbtn2" onclick="uploadFile('file');" />
				</td>
			</tr>
		 </table>
	   </td>
	</tr>
	
</table>
</div> 
<div id = "closeBtn" style="display: none"> 
	<table style="BORDER-COLLAPSE: collapse" class="small" border=0 cellspacing=0 cellpadding=0 width="700" align="right">
		<tr>
			<td align="right">
				<input  type="button" value="  完成  " class="wfbigbtn"  onclick="window.parent.DialogMgr.close();" />
			</td>
		</tr>
	 </table>
 </div>
<br/>
<br/>
 

</form>
</div>
</body>
</html>