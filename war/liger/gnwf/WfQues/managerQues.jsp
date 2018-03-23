<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<head>
<title>Untitled</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	
<script type="text/javascript" src="./include/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="./include/kindeditor/lang/zh_CN.js"></script>
<script src="./include/js/gnwf/wfReply.js" type="text/javascript"></script>

<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>


<style type="text/css">
.allBox{width:988px;margin:10px auto;overflow:hidden;}

.allTit{ border:#ffd4c1 solid 1px;background:#fff0e9;}
.allTit p{padding:0 10px;line-height:32px;color:#78503d;font-size:14px;}
/* .allTit p b{padding:0 10px;} */
.allTit p span{padding-right:15px;}

.borBRed{border-bottom:#ffd4c1 1px solid;}
.area01{margin:10px 0;border:#ccc solid 1px;width:986px;}
.titBox{background:url(/zrprjt/include/img/workflow/rpx.png) repeat-x 0 0;padding:0 10px;height:32px;line-height:32px;border-bottom:#b7cee2 solid 1px;}
.titBox h3{font-size:16px;float:left;color:#515c68;font-weight:700;}
.titBox .butonList{margin-top:4px;float:right;}
.titBox .butonList li{float:left;}
.titBox .butonList li a{line-height:22px;padding:0 10px;text-decoration:none;margin:0 5px;font-size:12px;color:#fff; display:block;height:height:22px;border:#23537d solid 1px; border-radius:3px;background:#4280b7;}
.titBox .butonList li a:hover{background:#669bca;}
.processPicture{width:944px;text-align:center;margin:10px auto;margin-top:20px;}
.processSection{width:118px;display:inline-block;margin:0 -4px;margin-bottom:10px;}
.PSTit{font-size:12px;height:66px;overflow:hidden;color:#666;line-height:22px;padding:0 17px;width:90px;}
*+ html .processSection { display:inline;zoom:1;}
* html .processSection { display:inline;zoom:1;}
.table01 {width:947px; margin:10px auto;color:#666;border-collapse:collapse;border-spacing:0;border-left:1px solid #ccc;border-top:1px solid #ccc;background:#fff;}
.table01 table{width:100%;}
.table01 a{text-decoration:none;}
.table01 a:hover{color:#ab0000;text-decoration:underline;}
.table01 th , .table01 td{ vertical-align:middle;padding:0px 10px;border-right:1px solid #ccc;text-align:center;font-size:12px;text-align:center;height:32px;line-height:32px;border-bottom:1px solid #ccc;}
.table01 th{font-weight:bold;background:#eee;}
.colorRed{color:#b35a48;}
.colorBlue{color:#3f94cf;}
 .butList{float:left;}
.butList li{float:left;} 
.butList button{margin:0 5px;color:#fff;background:#d07b48;border:#b76e27 solid 1px;height:24px;padding:0 10px; border-radius:3px;cursor:pointer;}
.tableBut{margin:0 5px;color:#fff;background:#4280b7;border:#23537d solid 1px;height:24px;padding:0 10px; border-radius:3px;cursor:pointer;}
.tableBut:hover{background:#669bca;}
.butList button:hover{background:#db9a72;}
.padLR5{padding:0 5px;}
.table01 .padd0{padding:inherit;}
.table01 .noBorR{border-right:none;}
.tableTextarea01{width:100%;padding:10px;height:100px;margin:10px auto;border:#ccc solid 1px;font-size:12px;color:#666;}
.table02 td{text-align:left;}
.table02 th{width:10%;}
.table02 .texAliC{text-align:center;}
.showinfo{
  display:none;
  left:1px;
  top:1px;
  width:103px; 
  min-height:10px; 
  padding:5px;
  color:#000088;
  background:#aaffee; 
  position:fixed; 
  z-index:999999; 
  font-family: Arial; 
  border: 2px solid #379082; 
  border-radius: 20px; 
  
 }
</style>
<script type="text/javascript"> 

var MouseEvent = function(e){
	 this.x = e.pageX
	 this.y = e.pageY
}
var Mouse = function(e){
	var kdheight =  jQuery(document).scrollTop();
	mouse = new MouseEvent(e);
	leftpos = mouse.x+8;
	toppos = mouse.y-kdheight-50; 
}
$(function () {
	
	//alert("${wfQues.fileName}");
	//把附件字段拆分后分别显示*******
	 var filelist = "${wfQues.fileName}";
	 var quesID = "${wfQues.quesId}";
	 var arrayfilelist = filelist.split("||");
	 var htm = "";
	 if(filelist == ''){
			htm ="无";
		}else{
			for(var i=0;i<arrayfilelist.length;i++){
				if(i==0){
					htm += "<a class='colorBlue padLR5' href=javascript:downloadOrViewFile('"+quesID+"','"+arrayfilelist[i]+"'); >";
				     htm +=arrayfilelist[i]+"</a>&nbsp&nbsp&nbsp;";
					//alert(htm);
				}else{
					htm += "、&nbsp;<a class='colorBlue padLR5' href=javascript:downloadOrViewFile('"+quesID+"','"+arrayfilelist[i]+"'); >";
				     htm +=arrayfilelist[i]+"</a>&nbsp;";
					//alert(htm);
				}
			}
		}
	$("#tdDownLoad").html(htm);
	
	//把措施附件字段拆分后分别显示***********
	
	if(( document.getElementById("gpName").value.indexOf("DQA") > -1)){
		if(document.getElementById("closeBtn")!=null){
    		document.getElementById("closeBtn").style.display = "";
		}
		if(document.getElementById("showReturnBackBtn")!=null){
    		document.getElementById("showReturnBackBtn").style.display = "";
		}
	}else{
		if(document.getElementById("closeBtn")!=null){
	    	document.getElementById("closeBtn").style.display = "none";
		}
		if(document.getElementById("showReturnBackBtn")!=null){
	    	document.getElementById("showReturnBackBtn").style.display = "none";
		}
	}
	
	/* if(( document.getElementById( "wfQues_status").value==19)||( document.getElementById("loadReturnBackQR").value==1) ){
		document.getElementById("returnBackRemark").style.display = "";
	} */
	
	if(document.getElementById("textId")!=null&&document.getElementById("text")!=null){
		if(document.getElementById(document.getElementById("textId").value)!=null){
			document.getElementById(document.getElementById("textId").value).value = document.getElementById("text").value ;
		}
	}
	
	$("#upBtn").hover(function(e) {
		Mouse(e);
		$(".showinfo").css({top:toppos,left:leftpos}).fadeIn(100);
	},function() {
		$(".showinfo").hide();
	});
});
     
    function upQuesResp(id,status){
    	 $.post("QuesResp!sav.shtml?quesResp.id ="+id+"&quesResp.status="+status,
    				function(data) {
    					 $.ligerDialog.success(data);
    				},
    				"text"
    	);
    }
    //去回车符
    function removeReturnSign(text) {
    	return text.replace(/[\r\n]/g,"");
    }
    
	//催办
    function rush(userid,quesRespId){
		
    	var rushRemark=prompt("您确定要催办任务吗？","请写催办原因(字数不得超过50个字)，谢谢");
		
		if(rushRemark){
			
		//alert(rushRemark);
		var quesId =  $("#wfQues_quesId").val();
		//alert(quesId);
		var str = '{"quesResp.usrId":'+ userid + ',"quesResp.remark":"'+ rushRemark + '","wfQues.quesId":"' + quesId + '","quesResp.id":' + quesRespId + '}';
		//alert(str);
		$.post("WfQues!rush.shtml",eval('(' + str + ')'),function(data){
			$.ligerDialog.success(data.msg);
				//alert(data.rushDate);
			if(undefined != data.rushDate) {
				$("#rushDate" + quesRespId).text('上次催办原因和时间：' + data.rushDate );
			}
		},"json");
		}
    }
    
    //挂起
    function hangup(id){
    	var str = '{';
    	if ($("#idtfRes"+id).length > 0) {
				str += '"quesResp.idtfRes":"' + removeReturnSign($("#idtfRes"+id).val()) + '",';
		}
		str += '"quesResp.remark":"' + "挂起"+ '",';
		str += '"quesResp.status":"' + 3 + '",';
		str += '"quesResp.id":"' + id + '",';
		str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '"';
		str += '}';
		
		$.post("WfQues!hangUp.shtml",
    			eval('(' + str + ')'),
				function(data) {
					 $.ligerDialog.success(data,"提示",function(){
						 window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
					 });
				},
				"text"
	    );
    }
    
    //转风险评估
    function labelRiskStatus(quesRespId) {
    	$.ligerDialog.confirm("你确认转风险评估吗?", function(data){
    		if(data){
    			var str = '{';
    			str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '",';
    			str += '"quesResp.id":"' + quesRespId + '"';
    			str += '}';
    			$.post("WfQues!labelRiskStatus.shtml",
    	    			eval('(' + str + ')'),
    					function(data) {
    						 $.ligerDialog.success(data,"提示",function(){
    							 window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
    						 });
    						 relodefaster()
    					},
    					"text"
    		    );
    		}
    	});
    }
    
    
    //修改问题责任人
    function updateQuesUsrs1(){
    	var dialog33 = $.ligerDialog.open({title:'修改问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?prjtNo='+$("#wfQues_prjtNo").val()+'&isFromWf=0'});
    }
   
    function setUsrData(usrId,usrName){
    	document.getElementById("responsible").innerHTML = usrName;
    	$("#responsibleUID").val(usrId);
    	var str = '{';
		str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '",';
		str += '"wfQues.usrName":"' + usrName + '",';
		str += '"responsibleUID":"' + usrId + '"';
		str += '}';
    	$.post("WfQues!updateResponers.shtml",eval('(' + str + ')'),function(data) {
    		$.ligerDialog.success(data,"提示",function(){
    			window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
			  });
    		relodefaster()
		 },"text");
    }
    
   // 提交解决措施
    function savResult(id){
    	var str = '{';
		if ($("#result"+id).length > 0) {
				str += '"quesResp.result":"' + removeReturnSign($("#result"+id).val()) + '",';
		}
		if ($("#quesAnalysis"+id).length > 0) {
			str += '"quesResp.quesAnalysis":"' + removeReturnSign($("#quesAnalysis"+id).val()) + '",';
		}
		str += '"quesResp.status":"' + 1 + '",';
		str += '"quesResp.quesId":"' + $("#wfQues_quesId").val() + '",';
		str += '"quesResp.id":"' + id + '"';
		str += '}';
    	$.post("QuesResp!upd.shtml",
    			eval('(' + str + ')'),
				function(data) {
    				 $.ligerDialog.success(data,"提示",function(){
    					 
    					 window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
    				 });
    				 relodefaster() 
				},
				"text"
	    );
    }
    //提交任务成功后刷新我的任务或者问题管理
	function relodefaster() {
			if(window.parent.quesWinow){
				window.parent.quesWinow.frame.gridManager.loadData();
			}
			if(window.parent.quesManagerWinow){
				window.parent.quesManagerWinow.frame.gridManager.loadData();
			}
	}
	
    //验证有效
      function approve(id){
    	//alert("!!!!!!!");
    	var str = '{';
   		if ($("#idtfRes"+id).length > 0) {
   			str += '"quesResp.idtfRes":"' + removeReturnSign($("#idtfRes"+id).val()) + '",';
   		}
   		str += '"quesResp.status":"' + 2 + '",';
   		str += '"quesResp.quesId":"' + $("#wfQues_quesId").val() + '",';
   		str += '"quesResp.id":"' + id + '"';
   		str += '}';
       	
       	$.post("QuesResp!upd.shtml",
       			eval('(' + str + ')'),
   				function(data) {
	   			    $.ligerDialog.success(data,"提示",function(){
	   			    	window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
	   			    });
	   			 relodefaster()
   				},
   				"text"
   	    );
       }
    //验证无效 
    function reject(id,usrId){
    	//alert("!!!!!!!");
    	var str = '{';
   		if ($("#idtfRes"+id).length > 0) {
   				str += '"quesResp.idtfRes":"' + removeReturnSign($("#idtfRes"+id).val()) + '",';
   		}
   		str += '"quesResp.status":"' + -1 + '",';
   		str += '"quesResp.quesId":"' + $("#wfQues_quesId").val() + '",';
   		str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '",';
   		str += '"wfQues.title":"' + $("#wfQues_title").val() + '",';
   		str += '"quesResp.usrId":"' + usrId + '",';
   		str += '"quesResp.id":"' + id + '"';
   		str += '}';
       	$.post("QuesResp!upd.shtml",
       			eval('(' + str + ')'),
   				function(data) {
	   				$.ligerDialog.success(data,"提示",function(){
	   					window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
	   			    });
	   				relodefaster()
   				},
   				"text"
   	    );
    }
   //验证退回
    function returnQues(id,usrId){
    	var str = '{';
   		if ($("#idtfRes"+id).length > 0) {
   				str += '"quesResp.idtfRes":"' + removeReturnSign($("#idtfRes"+id).val()) + '",';
   		}
   		str += '"quesResp.status":"' + -2 + '",';
   		str += '"quesResp.quesId":"' + $("#wfQues_quesId").val() + '",';
   		str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '",';
   		str += '"wfQues.title":"' + $("#wfQues_title").val() + '",';
   		str += '"quesResp.usrId":"' + usrId + '",';
   		str += '"quesResp.id":"' + id + '"';
   		str += '}';
       	$.post("QuesResp!upd.shtml",
       			eval('(' + str + ')'),
   				function(data) {
	   				$.ligerDialog.success(data,"提示",function(){
	   					window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
	   			    });
	   				relodefaster()
   				},
   				"text"
   	    );
    } 
   
   
   
  //关闭问题弹出填写关闭原因页面
	
	 function closQues() {
		 window.location ="./WfQues!managerCloseQuesReason.shtml?wfQues.quesId=" + $("#wfQues_quesId").val();
			//window.parent.f_open('./WfQues!managerCloseQuesReason.shtml?wfQues.quesId=' + $("#wfQues_quesId").val());
		}
   
   
  //关闭问题
   /*  function closQues(){
    	var str = '{';
    	str += '"wfQues.status":"' + $("#wfQues_status").val() + '",';
    	str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '"';
    	str += '}';
    	
    	$.post("WfQues!closeQues.shtml",
    			eval('(' + str + ')'),
    			function(data) {
    				  $.ligerDialog.success(data,"提示",function(){
    					  window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
    				  });
    			},
    			"text"
        );
    } */
   
    function uploadFile(qr_id,upFielType,textId) {
    	//alert("22");
        var gngile_uploadURL = $('#gngile_upload_URL').val();
    	var server_URL = $('#server_URL').val();
    	var url = gngile_uploadURL
          /**RUL后面跟的参数 **/
    	         +'?syId='+$("#syId").val()    //系统ID
    	         +'&syNm='+$("#syNm").val()  //系统名字
    	         +'&usrId='+$("#usrId").val() //用户ID
    	         +'&usrNm='+$("#usrNm").val() //用户名字
    	         +'&diyFolder='+"QuesFile" //文件上传的目录中自定义部分
    	         +'&uploadType='+"BaseLib" 
             //上传的是ProcFile，取值只能是ProcFile或者BaseLib
             //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
             +'&fileVersion='+"1.01" //文件版本号，自己在业务逻辑中确定
    	         +'&callBackUrl='+server_URL+"/afterUploadAction!quesRespAfterUploadFile.dhtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
             +'&tempParams='+"quesResp.id:"+qr_id+",upFielType:"+upFielType;  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
          /**RUL后面跟的参数 **/
    url=encodeURI(url);  //encodeURI
    //var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
    var dialog = $.ligerDialog.open({ url: url, height: 150,width: 490, buttons: [ { text: '完成', onclick: function (item, dialog) { dialog.close(); } } ] });
    //监听Dialog关闭事件
    $(".l-dialog-winbtns").click(function(){
    	//alert("./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val());
    	//	window.location = "./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val();
    	textId= textId+qr_id;
    	var text = encodeURI(document.getElementById(textId).value);
    	 //alert(document.getElementById(textId).value);
    	 window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val()+"&text="+text+"&textId="+textId;
    });
    }
    
 //下载问题描述的附件
function downloadOrViewFile(wfQuesId,fileName) {
	var picfixArr = ["jpg","bmp","jpeg","png","gif"];
	var fix = fileName.substring(fileName.indexOf(".") + 1,fileName.length);
	if(picfixArr.indexOf(fix) > -1) {
		window.parent.parent.f_open('./WfQues!viewPic.shtml?wfQues.quesId=' + wfQuesId,"预览");
	}else {
		window.location.href = './WfQues!downloadFile.shtml?wfQues.quesId=' + wfQuesId +'&fileNameStr = '+fileName;
	}
}
 
 
 


</script>
</head>

<body>
	<input type="hidden" id="fileNameList" name="fileNameList" size="30" value="<c:out value="${fileNameList}"/>"/>
	
    <input type="hidden" id="syId" name="syId" size="30" value="<c:out value="${syId}"/>"/>
	<input type="hidden" id="syNm" name="syNm" size="30" value="<c:out value="${syNm}"/>"/>
	<input type="hidden" id="usrId" name="usrId" size="30" value="<c:out value="${usrId}"/>"/>
	<input type="hidden" id="usrNm" name="usrNm" size="30" value="<c:out value="${usrNm}"/>"/>
	<input type="hidden" id="gngile_upload_URL" name="gngile_upload_URL" size="30" value="<c:out value="${initParam.gngile_upload_URL}"/>" />
    <input type="hidden" id="server_URL" name="server_URL" size="30" value="<c:out value="${initParam.server_URL}"/>" />
    
    <input type="hidden" id="text" name="text" size="30" value="<c:out value="${text}"/>" />
    <input type="hidden" id="textId" name="textId" size="30" value="<c:out value="${textId}"/>" />

	<input type="hidden" id="responsibleUID" name="responsibleUID" size="30" value="<c:out value="${responsibleUID}"/>"/>
	
	<input type="hidden" id="wfQues_sourceID" name="wfQues_sourceID" value="<c:out value="${wfQues.sourceID}"/>">
		
	<input type="hidden" id="wfQues_quesId" name="wfQues_quesId" value="<c:out value="${wfQues.quesId}"/>">
	<input type="hidden" id="wfQues_prjtNo" name="wfQues.prjtNo" value="<c:out value="${wfQues.prjtNo}"/>">
	<input type="hidden" id="gpName" name="gpName" size="30" value="<c:out value="${gp.gpName}"/>" />
	<input type="hidden" id="wfQues_status" name="wfQues_status" size="30" value="<c:out value="${wfQues.status}"/>" />
	
	<input type="hidden" id="wfQues_title" name="wfQues_title" size="30" value="<c:out value="${wfQues.title}"/>" />
	
	<input type="hidden" id="sessionScope_SYUSR_id" name="sessionScope_SYUSR_id" size="30" value="<c:out value="${sessionScope.SYUSR.id}"/>" />

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
<%-- <% 
pageContext.setAttribute("fileNameList", <c:out value="${fileNameList}"/>); 
%> --%>
<div class="allBox">
        <div class="allTit"  id = "台头">
            <p class="borBRed">
                <span><b>项目名称：</b><c:out value="${wfQues.prjtNm}"/></span>
                <!-- 
                <span><b>所属流程：</b><c:out value="${wfQues.wfName}"/></span>
                <span><b>提出人：</b><c:out value="${wfQues.createUsr}"/></span>
                 -->
                 <span><b>阶段：</b>
                     <c:choose>
			            <c:when test="${wfQues.scheId ==23}">
			                           小批试产（T1）
			            </c:when>
			            <c:when test="${wfQues.scheId ==28}">
			                             小批试产（T2）
			           </c:when>
			           <c:when test="${wfQues.scheId ==52}">
			                             小批试产（T3）
			           </c:when>
			           <c:when test="${wfQues.scheId ==25}">
			                             中批试产1
			            </c:when>
			          <c:when test="${wfQues.scheId ==31}">
			        	   中批试产2
			            </c:when>
		            </c:choose>
                 </span>
                 
                 
                  <span><b>来源：</b>
                     <c:choose>
			            <c:when test="${wfQues.sourceID ==1}">
			                          硬件测试
			            </c:when>
			            <c:when test="${wfQues.sourceID ==2}">
			                            试产组装
			           </c:when>
			           <c:when test="${wfQues.sourceID ==3}">
			                             试产贴片
			            </c:when>
			          <c:when test="${wfQues.sourceID ==4}">
			        	  白盒测试
			            </c:when>
			            <c:when test="${wfQues.sourceID ==5}">
			        	整机测试
			            </c:when>
			            <c:when test="${wfQues.sourceID ==6}">
			        	工厂测试
			            </c:when>
		            </c:choose>
                 </span>
                 
                 
                  <span><b>分类：</b>
                     <c:choose>
			            <%-- <c:when test="${wfQues.quesTypeID ==1}">
			                         基带
			            </c:when> --%>
			            <c:when test="${wfQues.quesTypeID ==2}">
			                            射频
			           </c:when>
			           <%-- <c:when test="${wfQues.quesTypeID ==3}">
			                            音频
			            </c:when> --%>
			          <c:when test="${wfQues.quesTypeID ==4}">
			        	  结构
			            </c:when>
			            <c:when test="${wfQues.quesTypeID ==5}">
			        	外观工艺
			            </c:when>
			             <c:when test="${wfQues.quesTypeID ==6}">
			        	软件
			            </c:when>
			             <c:when test="${wfQues.quesTypeID ==7}">
			        	贴片工艺
			            </c:when>
			             <c:when test="${wfQues.quesTypeID ==8}">
			        	组装工艺
			            </c:when>
			            <c:when test="${wfQues.quesTypeID ==9}">
			        	物料
			            </c:when>
			            <c:when test="${wfQues.quesTypeID ==10}">
			        	附配件测试
			            </c:when>
			            <c:when test="${wfQues.quesTypeID ==11}">
			        	硬件
			            </c:when>
		            </c:choose>
                 </span>
                 
                 <!--
                <span><b>提出时间：</b><fmt:formatDate type="both"   value="${wfQues.createDate}"/></span>
                  -->
                <span><b>状态：</b> 
                    <c:choose>
			            <c:when test="${wfQues.status ==21}">
			                             已挂起
			            </c:when>
			            <c:when test="${wfQues.status ==30}">
			                              已关闭
			           </c:when>
			           <c:when test="${wfQues.status ==40}">
			           	转风险
			            </c:when>
			          <c:when test="${wfQues.status ==1}">
			           	待解决
			            </c:when>
			            <c:when test="${wfQues.status ==9}">
			           	验证未通过
			            </c:when>
			            <c:when test="${wfQues.status ==10}">
			           	待验证
			            </c:when>
			            <c:when test="${wfQues.status ==11}">
			           	验证通过
			            </c:when>
			             <c:when test="${wfQues.status ==8}">
			           	退回
			            </c:when>
		            </c:choose>
		        </span>
		        <span><b>不良比例：</b><c:out value="${wfQues.fractionDefective}"/></span>
		        <span><b>测试项：</b><c:out value="${wfQues.testItemName}"/></span>
		        <!-- <span><button  class="butList" onclick="closQues();">关闭</button></span> -->
            </p>
        </div>
         
        <div class="area01" id = "问题描述">
            <div class="titBox">
                <h3>问题描述</h3>
            </div>
            <div class="area01Con">
                <table class="table01 table02">
                    <tbody>
                        <%-- <tr>
                            <th nowrap="nowrap" >标题</th>
                            <td nowrap="nowrap"><c:out value="${wfQues.title}"/></td>
                        </tr> 
                        
                        <tr>
                            <th nowrap="nowrap" >问题来源</th>
                            <td nowrap="nowrap">
                            <c:choose>
					            <c:when test="${wfQues.sourceID ==1}">
					                             硬件测试
					            </c:when>
					            <c:when test="${wfQues.sourceID ==2}">
					                             试产组装
					           </c:when>
					           <c:when test="${wfQues.sourceID ==3}">
					        	   试产贴片
					            </c:when>
					          <c:when test="${wfQues.sourceID ==4}">
					           	白盒测试
					            </c:when>
					            <c:when test="${wfQues.sourceID ==5}">
					           	整机测试
					            </c:when>
				            </c:choose>
                           <c:out value="${wfQues.sourceID}"/> 
                           </td>
                        </tr>
                        
                        --%>
                        <tr>
                            <th nowrap="nowrap">问题描述</th>
                            <td nowrap="nowrap"><textarea readonly="readonly" class="tableTextarea01"><c:out value="${wfQues.quesText}"  escapeXml="false"/></textarea></td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap" >责任人</th>
                            <td nowrap="nowrap" id = "responsible">
                            	<c:out value="${wfQues.usrName}"/>
                            	<%-- <c:if test="${wfQues.status != 30 && wfQues.status != 40}" >
	                                <input id="upBtn" type="button" value=" 转交" class="wfbigbtn2"  onclick="updateQuesUsrs1();" />
	                                <div id="prompt" class="showinfo">问题责任人可以更改</div>
                                </c:if> --%>
                            </td>
                        </tr>
                        <tr >
                            <th  >附件</th>
                            <td  id ='tdDownLoad' >
                         
                            </td>
                        </tr>
                        
                        <tr>
                            <td colspan="2" class="padd0">
                                <table>
                                    <tbody>
                                        <tr>
                                            <th nowrap="nowrap">问题提出者</th><td nowrap="nowrap"><span class="padLR5"><c:out value="${wfQues.createUsr}"/></span></td>
                                           <%--  <th nowrap="nowrap">要求完成时间</th><td nowrap="nowrap"><fmt:formatDate type="both"   value="${wfQues.completedDate}"/></td> --%>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
        
        
        
         <div class="area01" id = "解决措施及效果验证">
            <div class="titBox">
                <h3>解决措施及效果验证</h3>
            </div>
            
            
            <div class="area01Con">
            
              <c:forEach items="${wfQues.quesRespList}" var="re">
                <table class="table01 table02">
                    <tbody>
                    	<tr>
                            <th nowrap="nowrap">原因分析</td>
                            <td nowrap="nowrap">
                                   <c:choose>
							        <c:when test="${re.usrId == sessionScope.SYUSR.id }">
							            <textarea class="tableTextarea01" id= "quesAnalysis<c:out value="${re.id}"/>" autofocus="autofocus" ><c:out value="${re.quesAnalysis}"/> </textarea>
						            </c:when>
						            <c:otherwise>
								  		<textarea class="tableTextarea01" id= "quesAnalysis<c:out value="${re.id}"/>"  readonly="readonly"   ><c:out value="${re.quesAnalysis}"/></textarea> 
								    </c:otherwise>
								 </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap">措施</td>
                            <td nowrap="nowrap">
                                   <c:choose>
							        <c:when test="${re.usrId == sessionScope.SYUSR.id }">
							            <textarea class="tableTextarea01" id= "result<c:out value="${re.id}"/>" autofocus="autofocus" ><c:out value="${re.result}"/> </textarea>
						            </c:when>
						            <c:otherwise>
								  		<textarea class="tableTextarea01" id= "result<c:out value="${re.id}"/>"  readonly="readonly"   ><c:out value="${re.result}"/></textarea> 
								    </c:otherwise>
								 </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap" >措施附件</td>
                            <td nowrap="nowrap">
                            <img align="middle" src="./include/img/workflow/<c:out value="${re.resFileIcon}"/>"/>
					    	<a class="colorBlue padLR5" href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${re.resultFileNo}"/>'>
					    	 <c:out value="${re.resultFileName}"/>
					    	</a>
                             <c:choose>
						        <c:when test="${re.usrId == sessionScope.SYUSR.id }">
	                                  <a href="javascript:uploadFile(<c:out value="${re.id}"/>,'resultFile','result');" class="colorBlue padLR5">上传附件</a>
					            </c:when>
							 </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="padd0">
                                <table>
                                    <tbody>
                                        <tr>
                                            <th nowrap="nowrap">责任人</th><td nowrap="nowrap"><c:out value="${re.usrName}"/> </td>
                                            <th nowrap="nowrap">完成时间</th><td nowrap="nowrap"><fmt:formatDate type="both"   value="${re.resultDate}"/></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="noBorR"></td>
                                           <td nowrap="nowrap" class= "inTableBu texAliC">
												<c:if test="${re.usrId == sessionScope.SYUSR.id && wfQues.status != 30  && wfQues.status != 20 && wfQues.status != 40}">
													 <c:if test="${re.status != 2 && re.status != 3}">
		                                             	<button id="savResultBtn<c:out value="${re.id}"/>" class="tableBut" onclick="savResult(<c:out value="${re.id}"/>);" >提交解决措施</button>
												     </c:if>
												     <c:choose>
												     	<c:when test="${re.status == 0 || re.status == -1}">
												     		<%-- <button id="showGoRiskBtn" class="tableBut" onclick="labelRiskStatus(<c:out value="${re.id}"/>);"  >转风险评估</button> --%>
												     	</c:when>
												     </c:choose>
												     
												</c:if >
												<%-- <c:if test="${wfQues.createBy == sessionScope.SYUSR.id && wfQues.status != 30  && wfQues.status != 20 && wfQues.status != 40}">
												 <c:choose>
												 	<c:when test="${re.status == 0 || re.status == -1}"> --%>
														<button id="rushBtn" class="tableBut" onclick="rush(<c:out value="${re.usrId}"/>,<c:out value="${re.id}"/>);">催办</button>
											    	 <span id="rushDate<c:out value="${re.id}"/>"> 
														<c:if test="${!empty re.rushDate}">
															上次催办原因及时间：<c:out value="${re.remark}"/>---<fmt:formatDate type="both" value="${re.rushDate}"/>
														</c:if>
													 </span>
												   <%--  </c:when>
												 </c:choose>
													
												</c:if> --%>
                                           </td>
                         </tr>
                        
	                        <tr>
                            <th nowrap="nowrap">验证说明</td>
                            <td nowrap="nowrap">
                              <c:choose>
						        <c:when test="${wfQues.createBy == sessionScope.SYUSR.id && re.status != 0}">
								    <textarea class="tableTextarea01" id="idtfRes<c:out value="${re.id}"/>"  autofocus="autofocus" ><c:out value="${re.idtfRes}"/></textarea>
					            </c:when>
					            <c:when test="${wfQues.createBy != sessionScope.SYUSR.id && isDQA eq 'DQA' && wfQues.status == 11}">
								    <textarea class="tableTextarea01" id="idtfRes<c:out value="${re.id}"/>"  autofocus="autofocus" ><c:out value="${re.idtfRes}"/></textarea>
					            </c:when>
					            <c:otherwise>
							  		 <textarea class="tableTextarea01" id="idtfRes<c:out value="${re.id}"/>"  readonly="readonly" ><c:out value="${re.idtfRes}"/></textarea>
					            </c:otherwise>
							 </c:choose>
                            </td>
                        </tr>
                        
                        <tr>
                            <th nowrap="nowrap" >验证说明附件</td>
                            <td nowrap="nowrap">
						    	<%-- <img align="middle" src="./include/img/workflow/<c:out value="${re.idFileIcon}"/>"/> --%>
						    	<a class="colorBlue padLR5" href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${re.idtfResFileNo}"/>'>
						    	 <c:out value="${re.idtfResFileName}"/>
						    	</a>
						    	<c:choose>
							        <c:when test="${wfQues.createBy == sessionScope.SYUSR.id && re.status != 0}">
										   <a href="javascript:uploadFile(<c:out value="${re.id}"/>,'idtfFile','idtfRes');" class="colorBlue padLR5">上传附件</a>
						            </c:when>
								 </c:choose>
                            </td>
                        </tr>
                        
			                        
			            <tr>
                            <td colspan="2" class="padd0">
                                <table>
                                    <tbody>
                                        <tr>
                                            <th nowrap="nowrap">验证人</th><td nowrap="nowrap"><c:out value="${re.idtfUsrName}"/></td>
                                            <th nowrap="nowrap">验证时间</th><td nowrap="nowrap"><fmt:formatDate type="both"   value="${re.idtfDate}"/></td>
                                            <th nowrap="nowrap">验证效果</th>
                                            <td nowrap="nowrap">
                                                 <c:choose>
                                                 	 <c:when test="${re.status == -2}">
										                              退回
										            </c:when>
										            <c:when test="${re.status == -1}">
										                              无效
										            </c:when>
										            <c:when test="${re.status ==2}">
										                              有效
										            </c:when>
										            <c:when test="${re.status ==3}">
										                              挂起
										            </c:when>
										            <c:when test="${re.status ==5}">
										                              转风险
										            </c:when>
									            </c:choose>
                                            </td>
                                           
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        
                         <tr>
                               <td class="noBorR"></td>
                                            <td nowrap="nowrap" class= "inTableBu texAliC">
                                              <c:choose>
											     <c:when test="${wfQues.createBy == sessionScope.SYUSR.id && wfQues.status != 30 && wfQues.status != 40 && re.status != 0}">
											           <c:choose>
											           		<c:when test="${re.status == -2}">
												              <button id="rejectBtn<c:out value="${re.id}"/>" class="tableBut" onclick="approve(<c:out value="${re.id}"/>);" >有效</button>
												              <button id="approveBtn<c:out value="${re.id}"/>" class="tableBut" onclick="reject(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">无效</button>
												              <button id="returnBtn<c:out value="${re.id}"/>" class="tableBut" onclick="returnQues(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">退回</button>
												            </c:when>
												            <c:when test="${re.status == -1}">
												              <button id="rejectBtn<c:out value="${re.id}"/>" class="tableBut" onclick="approve(<c:out value="${re.id}"/>);" >有效</button>
												              <button id="returnBtn<c:out value="${re.id}"/>" class="tableBut" onclick="returnQues(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">退回</button>
												              <!-- <button id="showHangupBtn" class="tableBut" onclick="hangup(<c:out value="${re.id}"/>);" >挂起</button> -->
												            </c:when>
												            <c:when test="${re.status == 2}">
												               <button id="approveBtn<c:out value="${re.id}"/>" class="tableBut" onclick="reject(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">无效</button>
												               <!-- <button id="showHangupBtn" class="tableBut" onclick="hangup(<c:out value="${re.id}"/>);" >挂起</button> -->
												            </c:when>
												            <c:when test="${re.status == 1}">
												             <button id="rejectBtn<c:out value="${re.id}"/>" class="tableBut" onclick="approve(<c:out value="${re.id}"/>);" >有效</button>
												             <button id="approveBtn<c:out value="${re.id}"/>" class="tableBut" onclick="reject(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">无效</button>
												             <button id="returnBtn<c:out value="${re.id}"/>" class="tableBut" onclick="returnQues(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">退回</button>
												             <!-- <button id="showHangupBtn" class="tableBut" onclick="hangup(<c:out value="${re.id}"/>);" >挂起</button> -->
												            </c:when>
												           <%--  <c:when test="${re.status == 3}">//挂起
												             <button id="rejectBtn<c:out value="${re.id}"/>" class="tableBut" onclick="approve(<c:out value="${re.id}"/>);" >有效</button>
												             <button id="approveBtn<c:out value="${re.id}"/>" class="tableBut" onclick="reject(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">无效</button>
												            </c:when> --%>
											            </c:choose>
										         </c:when>
										         <c:when test="${wfQues.createBy != sessionScope.SYUSR.id && wfQues.status == 11  && isDQA eq 'DQA'}">
										         	<button id="approveBtn<c:out value="${re.id}"/>" class="tableBut" onclick="reject(<c:out value="${re.id}"/>,<c:out value="${re.usrId}"/>);">无效</button>
										         </c:when>
										      </c:choose>
                                            </td>
                                </tr>
                    </tbody>
                </table>
               </c:forEach>
            </div>
           
              
         </div>
         <c:if test="${wfQues.status != 30}">
           <div class="bottomTools">
	            <ul class="butList">
	                <li><button id="closeBtn"  onclick="closQues();">关闭</button></li>
	                <!-- 
					<c:if test="${loadGoRisk != 1 }">
			           <li><button id="showReturnBackBtn"  onclick="showReturnBack();">退回</button></li>
					</c:if>
					-->
	            </ul>
              </div>
        </c:if>
         
         <c:if test="${wfQues.status == 30}">
           <div class="area01" id = "关闭问题原因">
            <div class="titBox">
                <h3>关闭问题原因</h3>
            </div>
            <div class="area01Con">
                <table class="table01 table02">
                    <tbody>
                        <tr>
                            <th nowrap="nowrap">关闭问题原因</th>
                            <td nowrap="nowrap"><textarea readonly="readonly" class="tableTextarea01"><c:out value="${wfQues.closeQuesReason}"  escapeXml="false"/></textarea></td>
                        </tr>
                    </tbody>
                </table>
            </div>
       		</div>
        </c:if>
</div>
</body>