<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- <html xmlns="http://www.w3.org/1999/xhtml"> -->
<head>
<title></title>
<link href="./include/liger/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="./include/liger/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="./include/css/global.css" />
<link type="text/css" rel="stylesheet" href="./include/css/oa.css" />
<link rel="stylesheet" href="./include/css/workflow.css" type="text/css" / >

<script type="text/javascript" src="./include/js/jquery-1.8.1.min.js"></script>

<script src="./include/liger/jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script type="text/javascript" src="./include/js/gnwf/wfRd.js"></script>
<script type="text/javascript" src="./include/js/gnwf/WfRd_special.js"></script>

<script src="./include/liger/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="./include/liger/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>



<script src="./include/js/dowWfDoc.js" type="text/javascript"></script>
<script type="text/javascript">
	

	$.post("WfDoc!prjtSurveyNewFlie.shtml",{'wfDoc.projectNo':$('#prjtNo', window.parent.document).val()},
  			function(data) {
		/* alert(JSON.stringify(data));
			alert(data.Rows[0]); */
				 if(data.Rows[0]){
					 document.getElementById("wfDoc.icon").value = data.Rows[0].icon; 
		     	    document.getElementById("wfDoc.docId").value = data.Rows[0].docId; 
		     	    document.getElementById("wfDoc.docName").value = data.Rows[0].docName; 
		     	    
		     	   document.getElementById("imgDoc").src ="./include/img/workflow/"+data.Rows[0].icon ;
		     	   
		     	  document.getElementById("aDoc").href = "javascript:dowDoc("+data.Rows[0].docId+")"; 
		     	 $("#aDoc")[0].innerHTML=data.Rows[0].docName;

		     	// document.getElementById("aDoc").name = data.Rows[0].docName; 
		     	  
			   } 
  			},
  			"json"
  	      );


	
	var showDos = true;
	function showPrjtDos(){
		/* var showDos = false;
		alert(showDos); */
		if(!showDos){
			// alert("none");
		    document.getElementById("definDos").style.display = "none";
			document.getElementById("showPrjtDosBtn").innerHTML= "列表";
			showDos = true;
		 }else{
			// alert("show");
			    document.getElementById("definDos").style.display = "";
				document.getElementById("showPrjtDosBtn").innerHTML= "列表";
				showDos = false;
		 }
		
	}
	
	function hidden(){
		document.getElementById("definDos").style.display="none";
		//alert(document.getElementById("div").style.display)
		}
	function showDefinDos(){
		document.getElementById("definDos").style.display="";
		//alert(document.getElementById("div").style.display)
		}
		
		function preUploadFile(wfNo,taskId,cateId) {
			//alert("进入上传");
			var prjtNo=$('#prjtNo', window.parent.document).val();
			//alert(prjtNo);
			var gngile_uploadURL = $('#gngile_upload_URL', window.parent.document).val();
			var server_URL = $('#server_URL', window.parent.document).val();
			
			//var tempPar = '&tempParams='+"wfNo:"+wfNo+",taskId:"+taskId+",cateId:"+cateId+",uploadType:DefinDos"; 
			var tempPar = '&tempParams='+"wfNo:'1',taskId:1,cateId:1,uploadType:DefinDos"; 
	   		if(prjtNo){
	   			tempPar =tempPar+",prjtNo:"+prjtNo;
	   		}
	   		
	   		var url = gngile_uploadURL
	   		      /**RUL后面跟的参数 **/
	   	  	         +'?syId='+$('#syId', window.parent.document).val()    //系统ID
	   	  	         +'&syNm='+$('#syNm', window.parent.document).val()   //系统名字
	   	  	         +'&usrId='+$("#usrId", window.parent.document ).val() //用户ID
	   	  	         +'&usrNm='+$("#usrNm", window.parent.document ).val() //用户名字
	   	  	         +'&diyFolder='+"PDM//WFDOC" //文件上传的目录中自定义部分
	   	  	       //  +'&uploadType='+$("#uploadType" ).val() //文件上传类型 
	   	  	    
	   	  	         +'&callBackUrl='+server_URL+"/afterUploadAction!wfSurveyAfterUpload.dhtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
	   		         +tempPar;
	   	  	         //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
	   	          /**RUL后面跟的参数 **/
		    	//alert(url);
	   		url=encodeURI(url);  //encodeURI
	   		//var dialog = $.ligerDialog.open({title:'上传附件', height: 200, width: 470,url:url,isResize: true, name :'dlgUploader'});
	      	var dialog = $.ligerDialog.open({ url: url, height: 200,width: 490, buttons: [ { text: '完成', 
	      	onclick: function (item, dialog) { dialog.close(); 
	      	  window.location = "./PrjtDef!projtSurvey.shtml?prjtNo="+prjtNo; 
	      	 } } ] });
			
			 $(".l-dialog-close").click(function() {
				 window.location = "./PrjtDef!projtSurvey.shtml?prjtNo="+prjtNo;
			}); 
			
		}
</script>
</head>

<body >
  <form id="wfRd-form" name="wfRddoc-form"> 
	   <%-- <input type="hidden" id="wfNo" name="wfRd.wfNo" value="<c:out value="${wfRd.wfNo}"/>"/>
    <input type="hidden" id="stepId" name="wfStep.stepId" value="<c:out value="${currentTask.stepId}"/>"/> --%>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
	<input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>"/>
	<input type="hidden" id="cateId" />
	
	<input type="hidden" id="taskStepId" name="taskStepId" value="<c:out value="${taskStepId}"/>"/>
	<input type="hidden" id="currentTaskId" name="currentTaskId" value="<c:out value="${currentTaskId}"/>"/>
	<input type="hidden" id="wfRd.projectNo" name="wfRd.projectNo" value="<c:out value="${wfRd.projectNo}"/>">
	
	<input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>" />
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>" />
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>" /> 
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>" />
	<input type="hidden" id="wfDocId" name="wfDocId" size="30" value="<c:out value="${wfDocId}"/>" />
	<input type="hidden" id="uploadType" name="uploadType" size="30" value="<c:out value="${uploadType}"/>" />
	
	
	<input type="hidden" id="wfDoc.icon" name="wfDoc.icon" size="30" value="<c:out value="${icon}"/>" />
	<input type="hidden" id="wfDoc.docId" name="wfDoc.docId" size="30" value="<c:out value="${docId}"/>" />
	<input type="hidden" id="wfDoc.docName" name="wfDoc.docName" size="30" value="<c:out value="${docName}"/>" />
       <div  id = "产品定义书 ">
                   <br>
	              &nbsp&nbsp&nbsp&nbsp
	              &nbsp&nbsp&nbsp&nbsp
	               <input  type="button" onClick="javascript:preUploadFile('1',1,1);" value="上传产品定义书"> 
	               <br>
	             <!--    <div id="definDos" onMouseout="hidden();"> -->
						    <jsp:include flush="true" page="projtSurvey_definDos.jsp"></jsp:include>
				  <!--  </div> -->
	          
	
        </div>


</form>
</body>

</html>