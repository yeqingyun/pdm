<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css"
	rel="stylesheet" type="text/css" />

<script src="./include/liger/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js"
	type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js"
	type="text/javascript"></script>
<script src="./include/liger/jquery-validation/jquery.metadata.js"
	type="text/javascript"></script>

<script src="./include/js/zrprjt/prjtDef.js?v=1.0"
	type="text/javascript"></script>

<script type="text/javascript">

	$(function() {
		if ($("#reload").val()==1) {
			if ($("#ResultFile").length > 0){
			      document.getElementById("ResultFile").style.display = "";
			}
			if ($("#uploadFile").length > 0) {
				document.getElementById("uploadFile").value = "修改产品定义书 ";
				
			}
		}
	});


     function upPrjtDefDoc() {
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
	  	         //+'&uploadType='+"BaseLib" 
		         //上传的是ProcFile，取值只能是ProcFile或者BaseLib
		         //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
		        // +'&fileVersion='+$("#taskVersion" , window.parent.document).val() //文件版本号，自己在业务逻辑中确定
	  	         +'&callBackUrl='+server_URL+"/PrjtDef!afterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
	             +'&tempParams='+"prjtNo:"+$('#prjtNo').val()+",prjtDefFileType:PrjtDefDocFile";  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
	          /**RUL后面跟的参数 **/
		url=encodeURI(url);  //encodeURI
		//var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
		 var dialog = $.ligerDialog.open({ url: url, height: 200,width: 490, buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ] });
		//监听Dialog关闭事件
		$(".l-dialog-winbtns").click(function(){
			
		 var taskMangerWin = window.top.document.getElementById("taskManger").contentWindow;
		 
		 window.location = "./PrjtDef!upPrjtDefFile.shtml?prjtDef.prjtNo="+$("#prjtNo").val()+"&reload=1";
		 
	            //通过获取到的window对象操作HTML元素，这和普通页面一样
		// taskManger.document.getElementById("exit").style.visibility = "visible";
			
				//window.location = "./WfReply!add.shtml?WfReply.quesId="+$("#quesId").val()+"&reload=1";
				
				//$("#tree1").ligerGetTreeManager().selectNode($('#prjtNo', window.parent.document).val());
		});
	}
	
</script>



</head>
<body>

    <input type="hidden" id="reload" name="reload" value="<c:out value="${reload}"/>"/>
	<div id="eform"
		style="margin-left: 15px; margin-right: 15px; margin-top: 15px;">

		<form id="prjtForm" name="prjtForm" action="PrjtDef!sav.shtml"
			method="post" enctype="multipart/form-data">
			
			<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
            <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
			
			
			<input type="hidden" id="syId" name="syId" size="30"
				value="<c:out value="${syId}"/>" /> <input type="hidden" id="syNm"
				name="syNm" size="30" value="<c:out value="${syNm}"/>" /> <input
				type="hidden" id="usrId" name="usrId" size="30"
				value="<c:out value="${usrId}"/>" /> <input type="hidden"
				id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />
			<input type="hidden" id="prjtNm" name="prjtNm" size="30"
				value="<c:out value="${prjtDef.prjtNm}"/>" /> <input type="hidden"
				id="prjtNo" name="prjtNo" size="30"
				value="<c:out value="${prjtDef.prjtNo}"/>" /> <input type="hidden"
				id="wfDoc_docId" name="wfDoc_docId" size="30"
				value="<c:out value="${wfDoc.docId}"/>" />
				
				
				<input type="hidden"
				id="updWfDoc" name="updWfDoc" size="30"
				value="<c:out value="${updWfDoc}"/>" />

			<table width="90%">

				<tr>
				      <div id="ResultFile" style="display:none"> 
						      	<img align="middle" src="./include/img/workflow/<c:out value="${prjtDefFile.icon}"/>"/>
						    		<a href= '<c:out value="${initParam.gngile_download_URL}"/>'+'<c:out value="${prjtDefFile.fileNo}"/>'>
						    	
						    	 <c:out value="${prjtDefFile.docName}"/>
						    	</a>
				  	 </div>
				  	    
					<td><input name="uploadFile" type="button" id="uploadFile"
						value="上传产品规划书" onclick="upPrjtDefDoc()" size="30" /></td>
				</tr>
			</table>
		</form>

	</div>

</body>
</html>