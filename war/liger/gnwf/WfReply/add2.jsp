<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>

<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css"/>
<link href="./include/css/rep.css" rel="stylesheet" type="text/css"/>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script src="./include/liger/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="./include/liger/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="./include/liger/jquery-validation/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>

<script src="./include/js/gnwf/wfReply.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
	/* $.post("ligerToolBar1.shtml",
			{parm:'WfReply'},
			function(data) {
				$("#toolbar").ligerToolBar(data);
			},
			"json"
	); */
	
	if(( document.getElementById("gpName").value.indexOf("DQA") > -1)){
    	document.getElementById("DQA_opration").style.display = "";
    	
	}else{
		document.getElementById("DQA_opration").style.display = "none";
	}
	
	
	if($("#reload").val()==1){
		if ($("#ResultFile").length > 0){
		      document.getElementById("ResultFile").style.display = "";
		}
		if ($("#ResultUpBtn").length > 0){
			  document.getElementById("ResultUpBtn").value= "修改附件 ";
		}
		if ($("#IdtfResFile").length > 0){
			  document.getElementById("IdtfResFile").style.display = "";
		}
		if ($("#IdtfResUpBtn").length > 0){
		      document.getElementById("IdtfResUpBtn").value= "修改附件 ";
		}
	}
	
	$.fn.ligerRadio = function(){}
	$("#toptoolbar").ligerToolBar({ items: [
      		    { text: '回复', click: sav},
      	    	{ line:true },
      		    { text: '重置', click: res },
      		]
      	});
       check();
	if ($("#createDate").length > 0)
		$("#createDate").ligerDateEditor({showTime: false});
	if ($("#startCreateDate").length > 0)
		$("#startCreateDate").ligerDateEditor({showTime: false});
	if ($("#endCreateDate").length > 0)
		$("#endCreateDate").ligerDateEditor({showTime: false});
	if ($("#lastUpdDate").length > 0)
		$("#lastUpdDate").ligerDateEditor({showTime: false});
	if ($("#startLastUpdDate").length > 0)
		$("#startLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#endLastUpdDate").length > 0)
		$("#endLastUpdDate").ligerDateEditor({showTime: false});
	if ($("#userId").length > 0)
		$("#userId").ligerTextBox();
	if ($("#status").length > 0)
		$("#status").ligerTextBox();
	if ($("#createBy").length > 0)
		$("#createBy").ligerTextBox();
	if ($("#lastUpd").length > 0)
		$("#lastUpd").ligerTextBox();
	
	
	if($("#wfQues_idtfBy").val() == $("#sessionScope_SYUSR_id").val() && $("#wfQues_status").val() == 10){
		$("#wfRepLyType").val(1);         //给出验证效果
	}else{
		$("#wfRepLyType").val(0);        //给出解决措施
	}
	
	
});

//KindEditor网页编辑器
var editor;
KindEditor.ready(function(K) {
	var cfg = {
		resizeMode : 1,
		shadowMode : false,
		allowPreviewEmoticons : false,
		urlType : 'absolute',
		uploadJson : './WfQues!uploadImg.shtml',
		afterCreate : function() {
			this.sync();
		},
        afterBlur:function(){
            this.sync();
        },
        items : [ 'bold', 'italic', 'underline', 'strikethrough',
                'removeformat', '|', 'insertorderedlist',
                'insertunorderedlist', '|', 'forecolor',
                'fontname', 'fontsize', '|', 'link', 'unlink',
                'emoticons', 'code', 'image', 'flash', 'quote',
                'attach', '|', 'fullscreen','selectall', 'source', 'about' ]
	};
	editor = K.create('textarea',cfg);
});

function uploadFile(wfReplyType) {
	
	    var gngile_uploadURL = $('#gngile_upload_URL').val();
		var server_URL = $('#server_URL').val();
	
	var url = gngile_uploadURL
		//'http://gnfile.gionee.com:28080/gnfs/GnFileService!upload.shtml?' //文件服务器的上传的URl
	      /**RUL后面跟的参数 **/
  	         +'?syId='+$("#syId").val()    //系统ID
  	         +'&syNm='+"PDM"  //系统名字
  	         +'&usrId='+$("#usrId").val() //用户ID
  	         +'&usrNm='+$("#usrNm").val() //用户名字
  	         +'&diyFolder='+"GN800\\堆叠设计" //文件上传的目录中自定义部分
  	         +'&uploadType='+"BaseLib" 
	         //上传的是ProcFile，取值只能是ProcFile或者BaseLib
	         //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
	         +'&fileVersion='+"1.01" //文件版本号，自己在业务逻辑中确定
  	         +'&callBackUrl='+server_URL+"/WfReply!afterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
             +'&tempParams='+"wfReply.quesId:"+$("#quesId").val()+",prjtNm:GN800,wfId:1001,wfNm:堆叠设计,wfReplyType:"+wfReplyType;  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
          /**RUL后面跟的参数 **/
	url=encodeURI(url);  //encodeURI
	var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
	//监听Dialog关闭事件
	$(".l-dialog-winbtns").click(function(){
		     //alert("close");
			window.location = "./WfReply!add.shtml?WfReply.quesId="+$("#quesId").val()+"&reload=1";
	});
}



function closeQuestion(){
	$.ligerDialog.confirm('确定要关闭问题？', 
			function (type) { 
				if(type) {
					$.post("WfQues!cls.shtml",
						{'choices': $("#quesId").val()},
						function(data) {
							$.ligerDialog.success(data);
							reloadGrid();
						},
						"text"
					);
				}
			}
		);
}
function returnIdtf(){
	
	sav();
	
}


</script>
</head>
<body>

<!-- <div id="toolbar"></div> -->
<div id="eform" style="margin-left:15px;margin-right:15px;margin-top:15px;">
<form>
	<input type="hidden" id="replyId" name="wfReply.replyId" size="30" value="<c:out value="${wfReply.replyId}"/>"/>
	<input type="hidden" id="quesId" name="wfReply.quesId" size="30" value="<c:out value="${wfReply.quesId}"/>"/>
					
	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
	<input type="hidden" id="prjtNo" name="prjtNo" size="30" value="<c:out value="${prjtNo}"/>"/>
	<input type="hidden" id="prjtNm" name="prjtNm" size="30" value="<c:out value="${prjtNm}"/>"/>
	<input type="hidden" id="wfId" name="wfId" size="30" value="<c:out value="${wfId}"/>"/>
	<input type="hidden" id="wfNm" name="wfNm" size="30" value="<c:out value="${wfNm}"/>"/>
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
	
	<input type="hidden" id="wfQues_userId" name="wfQues_userId" size="30" value="<c:out value="${wfQues.userId}"/>"/>
	<input type="hidden" id="wfQues_idtfBy" name="wfQues_idtfBy" size="30" value="<c:out value="${wfQues.idtfBy}"/>"/>
	<input type="hidden" id="resultFileName" name="resultFileName" size="30" value="<c:out value="${wfQues.resultFileName}"/>"/>
	<input type="hidden" id="resultFileNo" name="resultFileNo" size="30" value="<c:out value="${wfQues.resultFileNo}"/>"/>
	<input type="hidden" id="idtfResFileName" name="idtfResFileName" size="30" value="<c:out value="${wfQues.idtfResFileName}"/>"/>
	<input type="hidden" id="idtfResFileNo" name="idtfResFileNo" size="30" value="<c:out value="${wfQues.idtfResFileNo}"/>"/>
	<input type="hidden" id="reload" name="reload" size="30" value="<c:out value="${reload}"/>"/>
	
	
	
	<input type="hidden" id="sessionScope_SYUSR_id" name="sessionScope_SYUSR_id" size="30" value="<c:out value="${sessionScope.SYUSR.id}"/>"/>
	<input type="hidden" id="wfRepLyType" name="wfRepLyType" size="30" value="<c:out value="${wfRepLyType}"/>"/>
	
	<input type="hidden" id="wfQues_status" name="wfQues_status" size="30" value="<c:out value="${wfQues.status}"/>"/>
	
	
	
	
	
	
	<input type="hidden" id="wfQues_status" name="wfQues_status" size="30" value="<c:out value="${wfQues.status}"/>"/>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
    <input type="hidden" id="gpName" name="gpName" size="30" value="<c:out value="${gp.gpName}"/>" />
	
	
	
	
<div class="wrap">
	<div class="wrap_1">
	    <h1><c:out value="${wfQues.title}"/></h1>
	    <p class="p1"><c:out value="${wfQues.quesText}"  escapeXml="false"/></p>
	    <span>创建人：<c:out value="${wfQues.createUsr}"/>
	    </span>
	    <div class="clear"></div>
	  </div>
	  <div class="line"></div>
	  
	  
	   <div class="wrap_3">
			<ul>
			  <li class="li_rep">
	  	            <c:choose>
					<c:when test="${wfQues.userId == sessionScope.SYUSR.id}">
						 <div class='title'>
							验证人-验证效果
							<font style="font-size: 12px">
							(
							<c:choose>
								<c:when test="${wfQues.idtfBy == 0}">待定</c:when>
								<c:otherwise>
									<c:out value="${wfQues.idtfUsr}"/>
								</c:otherwise>
							</c:choose>
						   )
						   </font>
						</div>
						<p class="p1"><c:out value="${wfQues.idtfRes}" escapeXml="false"/></p>
	  	            </c:when>
	  	            
		  	        <c:when test="${wfQues.idtfBy == sessionScope.SYUSR.id}">
		  	           <div class='title'>
							责任人-解决措施
							<font class="link">
							(
								<c:choose>
									<c:when test="${wfQues.userId == 0}">待定</c:when>
									<c:otherwise>
										<c:out value="${wfQues.usrName}"/>
									</c:otherwise>
								</c:choose>
							)
							</font>
						</div>
						<p class="p1"><c:out value="${wfQues.result}" escapeXml="false"/></p>
	  	            </c:when>
	  	            </c:choose>
	  	    </li>
			</ul>
		</div>
	  
	  
	  <div class="wrap_3">
			<ul>
			  <li class="li_rep">
				<c:choose>
					<c:when test="${wfQues.userId == sessionScope.SYUSR.id}">
					    <div class='title'>
							责任人-解决措施
							<font class="link">
							(
								<c:choose>
									<c:when test="${wfQues.userId == 0}">待定</c:when>
									<c:otherwise>
										<c:out value="${wfQues.usrName}"/>
									</c:otherwise>
								</c:choose>
							)
							</font>
						</div>
						<div id="ResultFile" style="display:none"> 
						      	<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.resFileIcon}"/>"/>
						    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.resultFileNo}"/>'>
						    	 <c:out value="${wfQues.resultFileName}"/>
						    	</a>
				  	    </div>
				  	    <input id="ResultUpBtn" type="button" value="上传附件" onclick="uploadFile('ResultFile');" />
				   </c:when>
		  	       <c:when test="${wfQues.idtfBy == sessionScope.SYUSR.id}">
		  	            <div class='title'>
							验证人-验证效果
							<font style="font-size: 12px">
							(
							<c:choose>
								<c:when test="${wfQues.idtfBy == 0}">待定</c:when>
								<c:otherwise>
									<c:out value="${wfQues.idtfUsr}"/>
								</c:otherwise>
							</c:choose>
						   )
						   </font>
						</div>
						<div id="IdtfResFile" style="display:none"> 
					      	<img align="middle" src="./include/img/workflow/<c:out value="${wfQues.idFileIcon}"/>"/>
					    	
					    	<a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfQues.idtfResFileNo}"/>'>
					    	
					    	 <c:out value="${wfQues.idtfResFileName}"/>
					    	</a>
					  	</div>
					  	<input id="IdtfResUpBtn" type="button" value="上传附件" onclick="uploadFile('IdtfResFile');" />
			  	   </c:when>
			  	</c:choose>
			  	
				<c:if test="${wfQues.idtfBy == sessionScope.SYUSR.id && wfQues.status == 10}">
					<div>
						 <input type="radio" name="checkstate" id="s1" onchange="editor.text('通过')" />通过
						 <input type="radio" name="checkstate" id="s2" onchange="editor.text('不通过')"/>不通过
						 <input type="radio" name="checkstate" id="s3" onchange="editor.text('挂起')"/>挂起
				    </div>
			    </c:if>
			    
				<textarea name="replyText" id="replyText"  style="height: 50px;width: 100%;border-color: #D6E3EF"><c:out value="${wfReply.replyText}"/></textarea>
				<input type="button" class="repbtn" onclick="sav()" value="提交"/>
				
				<div id = "DQA_opration"  style="display:none">
					<input type="button" class="repbtn" onclick="closeQuestion()" value="关闭"/>
					<input type="button" class="repbtn" onclick="returnIdtf()" value="DQA打回重新验证"/>
				</div>
			  </li>
			</ul>
		</div>
	
	
	
	
	<div class="wrap_3">
	    <ul>
	      <li class="li_t"><span class="title">记录</span></li>
	      <c:forEach items="${wfReplys}" var="reply">
	      <li>
	      	<div class="ico"></div>
	        <p class="p2"><c:out value="${reply.replyText}" escapeXml="false"/></p>
	         <c:if test="${reply.showFileIcon}">
	      	<img align="middle" src="./include/img/workflow/<c:out value="${reply.fileIcon}"/>"/>
	         
	         <a href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${reply.fileNo}"/>'>
	    	 <c:out value="${reply.fileName}"/>
	    	</a>
		   </c:if>
	        <span><c:out value="${reply.usrName}"/>|<fmt:formatDate pattern="yyyy-MM-dd" value="${reply.createDate}" type="both"/></span></li>
		   <c:choose>
			 <c:when test="${reply.groupId==oldGpId}">
		        <li style="background-color: #00224C"></span></li>
			 </c:when>
		     <c:otherwise>
		       <c:set var="oldGpId" value="${reply.replyId}"/>
			 </c:otherwise>
		 </c:choose>
	       </c:forEach>
	    </ul>
	  </div>
</div>
</form>

</div>

</body>
</html>