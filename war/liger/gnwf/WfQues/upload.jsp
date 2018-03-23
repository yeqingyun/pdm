<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>
<style type="text/css">
.header{
	position:absolute;
    top:50%;
    left:50%;
    width:348px;
    height:60px;
    margin-top:-30px;
    margin-left:-150px;
    line-height:10px;
    font-size:13px;
    text-align:center;
}
.innerTable {
	padding:0;
	margin:0;
	width:500px;
	background:#9FC2E5;
}
.innerTable th,.innerTable td {
	border-right: 1px solid #9FC2E5;
	border-bottom: 1px solid #9FC2E5;
	background: #fff;
	text-align: left;
	padding: 0 7px;
	height: 50px;
}
.innerTable th{
font-size: 14px;
font-weight: bold;
text-align:center;
}
</style>
<script type="text/javascript">
$(function() {
	if(document.getElementById("action").value.indexOf("impSovleQues") > -1) {
		document.getElementById("quesFile").style.display = 'none';
	}
	if(document.getElementById("msg").value.indexOf("完成") > -1) {
		document.getElementById("fileInp").style.display = 'none';
		document.getElementById("btn").style.display = 'none';
		/* document.getElementById("uploadFile").style.display='none'; */
		window.parent.frames["quesIframe"].location = "./WfQues!taskList.shtml?wfNo=" + $("#wfNo").val();
	}
});
function formSubmit() {
	document.getElementById("form").action=$("#action").val();
	document.getElementById("form").submit();
}
function upLoadQuesFile() {
	var gngile_uploadURL = $('#gngile_upload_URL').val();
	var server_URL = $('#server_URL').val();
	var url = gngile_uploadURL
		//'http://gnfile.gionee.com:28080/gnfs/GnFileService!upload.shtml?' //文件服务器的上传的URl
	      /**RUL后面跟的参数 **/
	         +'?syId='+$("#syId").val()    //系统ID
	         +'&syNm='+$("#syNm").val()   //系统名字
	         +'&usrId='+$("#usrId" ).val() //用户ID
	         +'&usrNm='+$("#usrNm" ).val() //用户名字
	         +'&diyFolder='+$("#syNm").val() //文件上传的目录中自定义部分
	         +'&callBackUrl='+server_URL+"/WfQues!afterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
          +'&tempParams='+"impQuesIds:"+$('#impQuesIds').val();  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
       /**RUL后面跟的参数 **/
      // alert(url);
	url=encodeURI(url);  //encodeURI
	//var dialog = $.ligerDialog.open({title:'上传附件', height: 200, width: 400,url:url,isResize: true, name :'dlgUploader'});
	var dialog = $.ligerDialog.open({ url: url, height: 200,width: 490, buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ] });
	//监听Dialog关闭事件
	$(".l-dialog-winbtns").click(function(){
		
	});
}
</script>
</head>
<body>
<form method="post" enctype="multipart/form-data" id="form">
    <input type="hidden" id="msg"  name = "msg" value="<c:out value="${msg}"/>"/> 
    <input type="hidden" id="action" value="<c:out value="${action}"/>"/>
    <input type="hidden" id="wfNo" name="wfQues.wfNo" size="30" value="<c:out value="${wfQues.wfNo}"/>"/>
    <input type="hidden" id="impQuesIds" name="impQuesIds" size="30" value="<c:out value="${impQuesIds}"/>" />
    <input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>" /> 
    <input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>" /> 
    <input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>" /> 
    <input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />
    <input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    <input type="hidden" id="wfQues.prjtNo" name="wfQues.prjtNo" size="30" value="<c:out value="${wfQues.prjtNo}"/>" />
    <input type="hidden" id="wfQues.prjtNm" name="wfQues.prjtNm" size="30" value="<c:out value="${wfQues.prjtNm}"/>" />
<div class="listTable clearfix" style="margin:50px 60px;text-align:center;padding-top:1	0px;  height:150px;" >


<table class="innerTable" >
	<tr>
		<th> 批导：</th>
		<td >
			<input type="file" id="fileInp" name="fileInp"/>
			<input type="button" id="btn" value="导入" onclick="formSubmit();"/>
			<font size="-1" color="#ff4500"><c:out value="${msg}"/></font>
		</td>
	</tr>
	<!-- 
	<tr id="quesFile">
		<th> 问题附件：</th>
		<td>
			<input name="uploadFile" type="button" id="uploadFile" value="上传导入问题的附件" 
			onclick="upLoadQuesFile()" size="30" disabled="disabled" />
		</td>
	</tr>
	 -->
</table>
</div>
</form>
	<div style=" margin-bottom:10;color:red" > 
	<!-- &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注：如果您没有问题模板请先下载模板：
	<a href="./Login!downLoad.shtml?filePath=/include/template/question_template.xlsx&fileNm=question_template.xlsx"> 问题导入EXCEL模板</a>
	 -->
	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注导入模板必须包含以下字段：阶段、问题描述、等级、分类、来源
	</div>
</body>
</html>
