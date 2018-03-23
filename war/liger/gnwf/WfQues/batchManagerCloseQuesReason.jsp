<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%> --%>
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

<script type="text/javascript" src="./include/js/gnwf/nextStep.js"></script>


<style type="text/css">
.allBox{width:988px;margin:10px auto;overflow:hidden;}

.allTit{ border:#ffd4c1 solid 1px;background:#fff0e9;}
.allTit p{height:32px;line-height:32px;color:#78503d;font-size:14px;}

.allTit p span{padding-right:29px;}

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
.butList{float:right;}
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
	var wfQuesIDs1 = "${wfQuesIDs}";
	document.getElementById('input1').value = wfQuesIDs1;
	if(( document.getElementById("gpName").value.indexOf("DQA") > -1)&& (document.getElementById("wfQues_status").value==11)){
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
	
	if(( document.getElementById( "wfQues_status").value==19)||( document.getElementById("loadReturnBackQR").value==1) ){
		document.getElementById("returnBackRemark").style.display = "";
	}
	
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
    function rush(userid,quesId,quesRespId){
		var str = '{"quesResp.usrId":'+ userid + ',"wfQues.quesId":' + quesId
			+ ',"quesResp.id":' + quesRespId + '}';
		$.post("WfQues!rush.shtml",eval('(' + str + ')'),function(data){
			$.ligerDialog.success(data.msg);
			if(undefined != data.rushDate) {
				$("#rushDate" + quesRespId).text('上次催办时间：' + data.rushDate);
			}
		},"json");
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
    //设置部门经理
    var dialog33;
    function selcResponsible(){
    	if($("#isFromWf").val()==1){
    		//alert($("#wfNo").val());
    		var checkedWfNo = $("#wfNo").val();
    		dialog33 = $.ligerDialog.open({title:'手动设置问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?wfQues.wfNo='+checkedWfNo+'&prjtNo'+$("#wfQuesPrjtNo").val()+'&isFromWf='+$("#isFromWf").val()});
    	}else{
    			dialog33 = $.ligerDialog.open({title:'手动设置问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?prjtNo='+$("#wfQuesPrjtNo").val()+'&isFromWf='+$("#isFromWf").val()});
    	}
    }
    
    //关闭问题并弹出填写关闭原因页面
    function batchLabelQuesStatus() {
    	var wfQuesIDs = "${wfQuesIDs}";
    	var closeQuesReason = $("#wfQues_closeQuesReason").val();
    	if(closeQuesReason==null||closeQuesReason==" "){
    		alert("\"关闭原因\"不能为空"); 
    		return;
    	}
    	
    	 $.ligerDialog.confirm("你确认关闭该问题?", function(data){
    		if(data){
    			var str = '{';
    			str += '"wfQues.closeQuesReason":"' + $("#wfQues_closeQuesReason").val() + '",';
    			str += '"wfQuesIDs":"' + wfQuesIDs +'"';
    			str += '}';
    			$.post("WfQues!batchLabelQuesStatus.shtml",
    	    			eval('(' + str + ')'),
    					function(data) {
    						 $.ligerDialog.success(data,"提示",function(){
    							closeDialog();
    						 }
    						 );
    						 relodeQuesManager()
    					},
    					"text"
    		    );
    		}
    	}); 
    }
  //提交任务成功后刷新问题管理
	function relodeQuesManager() {
			if(window.parent.quesManagerWinow){
				window.parent.quesManagerWinow.frame.gridManager.loadData();
			}
	}
    var dialog33;
    //修改问题责任人
    function updateQuesUsrs(){
    	dialog33 = $.ligerDialog.open({title:'修改问题责任人', height: 400, width: 450,url: './WfQues!selcResponsible.shtml' + '?prjtNo='+$("#wfQues_prjtNo").val()+'&isFromWf=0'});
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
		 },"text");
    }
  
   
  //关闭问题
    function closQues(){
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
    }
   
    function uploadFile(qr_id,upFielType,textId) {
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
    	         +'&callBackUrl='+server_URL+"/afterUploadAction!quesRespAfterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
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
function downloadOrViewFile(wfQuesId,fileName) {
	var picfixArr = ["jpg","bmp","jpeg","png","gif"];
	var fix = fileName.substring(fileName.indexOf(".") + 1,fileName.length);
	if(picfixArr.indexOf(fix) > -1) {
		window.parent.parent.f_open('./WfQues!viewPic.shtml?wfQues.quesId=' + wfQuesId,"预览");
	}else {
		window.location.href = './WfQues!downloadFile.shtml?wfQues.quesId=' + wfQuesId;
	}
}
</script>
</head>

<body>

	
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

<div class="allBox">

        <table class="table01 table02">
                   
                    	<tr>
                            <th nowrap="nowrap">你批量选择关闭的问题编号有以下：<input size="100"  id="input1"/></th>
                           
                        </tr>
                       
                 
                </table>
      	
         <div class="area01" id = "关闭问题需要填写的内容">
            <div class="titBox">
                <h3>关闭问题需要填写的内容：</h3>
            </div>
            
            
            <div class="area01Con">
            
              
                <table class="table01 table02">
                    <tbody>
                    	<tr>
                            <th nowrap="nowrap">关闭问题原因：<font color="red">&nbsp;*</font></th>
                            <td nowrap="nowrap">
							            <textarea class="tableTextarea01" id= "wfQues_closeQuesReason"  name="wfQues_closeQuesReason" autofocus="autofocus" 
							            value="<c:out value="${wfQues.closeQuesReason}"/>"> </textarea>
                            </td>
                        </tr>
                       
                    </tbody>
                </table>
              
            </div>
         </div>
         <div id = ="showGoRiskBtn1">
         <tr>
                            <td class="noBorR"></td>
                                           <td nowrap="nowrap" class= "inTableBu texAliC">
                                          &nbsp;&nbsp;&nbsp;&nbsp;  <button id="showGoRiskBtn" class="tableBut"  onclick="batchLabelQuesStatus();"  >关闭问题</button>
                                           </td>
       	</tr>
       	</div>
       	<div id = "closeBtn" style="display: none"> 
	<table style="BORDER-COLLAPSE: collapse" class="small" border=0 cellspacing=0 cellpadding=0 width="700" align="center">
		<tr>
			<td align="right">
				<input  type="button" value="  完成  " class="wfbigbtn"  onclick="window.parent.DialogMgr.close();" />
			</td>
		</tr>
	 </table>
 </div>
       	 
</div>
</body>
