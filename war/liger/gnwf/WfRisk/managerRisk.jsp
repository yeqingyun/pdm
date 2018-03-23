<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

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
	
<link type="text/css" rel="stylesheet" href="./include/css/global.css"/>
<link type="text/css" rel="stylesheet" href="./include/css/oa.css"/>

<style type="text/css">
.allBox{width:988px;margin:10px auto;overflow:hidden;}
.allTit{ border:#ffd4c1 solid 1px;background:#fff0e9;}
.allTit p{height:32px;line-height:32px;color:#78503d;font-size:14px;}
.allTit p b{padding:0 10px;}
.allTit p span{padding-right:40px;}
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
	$("#upBtn").hover(function(e) {
		Mouse(e);
		$(".showinfo").css({top:toppos,left:leftpos}).fadeIn(100);
	},function() {
		$(".showinfo").hide();
	});
});
     
    //去回车符
    function removeReturnSign(text) {
    	return text.replace(/[\r\n]/g,"");
    }
    
  //问题走风险通过后，关闭问题
    function closRiskQuesOfWfLastStep() {
    	var str = '{';
    	str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '"';
    	str += '}';
    	$.post("WfQues!closeRiskQues.shtml",eval('(' + str + ')'),function(data){
    		$.ligerDialog.success(data,"提示",function(){
    			window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
    		});
    	},"text");
    }

    //问题走风险不通过时，问题被退回，重新解决
    function returnBackRiskQuesOfWflastStep(){
    	var str = '{';
    	str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '"';
    	str += '}';
    	$.post("WfQues!returnBackRiskQues.shtml",eval('(' + str + ')'),function(data){
    		$.ligerDialog.success(data,"提示",function(){
    			window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
    		});
    	},"text");
    }
    
    //启动风险流程
    function goRisk(quesRespId){
    	$.ligerDialog.confirm("你确认启动风险流程吗?", function(data){
    		if(data){
    			var str = '{';
    			str += '"quesResp.remark":"' + "走风险" + '",';
    			str += '"quesResp.status":"' + 4 + '",';
    			str += '"quesResp.id":"' + quesRespId + '",';
    			str += '"wfQues.prjtNo":"' + $("#wfQues_prjtNo").val() + '",';
    			str += '"wfQues.title":"' + $("#wfQues_title").val() + '",';
    			str += '"wfQues.quesId":"' + $("#wfQues_quesId").val() + '"';
    			str += '}';
    			
    			$.post("WfQues!grisk.shtml",
    	    			eval('(' + str + ')'),
    					function(data) {
    						 $.ligerDialog.success(data,"提示",function(){
    							 window.location = "./WfQues!managerQues.shtml?wfQues.quesId="+$("#wfQues_quesId").val();
    						 });
    					},
    					"text"
    		    );
    		}
    	});
    }
  
    
    //关闭问
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
    
    
    var dialog33;
    //修改问题责任人
    function updateResponsibleUser(){
    		dialog33 = $.ligerDialog.open({title:'修改问题责任人', height: 400, width: 450,url: './WfRisk!selcResponsible.shtml' + '?prjtNo='+$("#prjtNo").val()});
    }
   
    function setUsrData(usrId,usrName){
    	document.getElementById("responsible").innerHTML = usrName;
    	$("#responsibleUID").val(usrId);
    	var str = '{';
		str += '"wfRisk.riskId":"' + $("#wfRisk_riskId").val() + '",';
		str += '"responsibleUID":"' + usrId + '",';
		str += '"wfRisk.responsibleUserName":"' + usrName + '"';
		str += '}';
    	$.post("WfRisk!updateResponsibleUser.shtml",eval('(' + str + ')'),
			function(data) {
	    		$.ligerDialog.success(data,"提示",function(){
	    			window.location = "./WfRisk!managerRisk.shtml?wfRisk.riskId="+$("#wfRisk_riskId").val();
				  });
			},
			"text"
	    );
    }
    
   // 提交解决措施
    function saveMeasures(id){
    	var str = '{';
		if ($("#measures"+id).length > 0) {
				str += '"wfRiskReply.measures":"' + removeReturnSign($("#measures"+id).val()) + '",';
		}
		str += '"wfRiskReply.id":"' + id + '",';
		str += '"wfRisk.riskId":"' + $("#wfRisk_riskId").val() + '"';
		str += '}';
    	$.post("WfRiskReply!saveMeasures.shtml",
   			eval('(' + str + ')'),
			function(data) {
   				 $.ligerDialog.success(data,"提示",function(){
   					 window.location = "./WfRisk!managerRisk.shtml?wfRisk.riskId="+$("#wfRisk_riskId").val();
   				 });
			},
			"text"
	    );
    }
  //催办
    function rush(responsibleUserId,riskId,riskReplyId){
		var str = '{"wfRiskReply.responsibleUserId":'+ responsibleUserId + ',"wfRisk.riskId":' + riskId
			+ ',"wfRiskReply.id":' + riskReplyId + '}';
		$.post("WfRiskReply!rush.shtml",eval('(' + str + ')'),function(data){
			$.ligerDialog.success(data.msg);
			if(undefined != data.rushDate) {
				$("#rushDate" + riskReplyId).text('上次催办时间：' + data.rushDate);
			}
		},"json");
    }
  
  //验证无效 
    function reject(id,responsibleUserId){
    	var str = '{';
   		if ($("#verifyResult"+id).length > 0) {
   				str += '"wfRiskReply.verifyResult":"' + removeReturnSign($("#verifyResult"+id).val()) + '",';
   		}
   		str += '"wfRisk.riskId":"' + $("#wfRisk_riskId").val() + '",';
   		str += '"quesResp.responsibleUserId":"' + responsibleUserId + '",';
   		str += '"wfRiskReply.id":"' + id + '"';
   		str += '}';
       	$.post("WfRiskReply!reject.shtml",
       			eval('(' + str + ')'),
   				function(data) {
	   				$.ligerDialog.success(data,"提示",function(){
	   					window.location = "./WfRisk!managerRisk.shtml?wfRisk.riskId="+$("#wfRisk_riskId").val();
	   			    });
   				},
   				"text"
   	    );
    }
    //验证有效
      function approve(id){
    	var str = '{';
   		if ($("#verifyResult"+id).length > 0) {
   			str += '"wfRiskReply.verifyResult":"' + removeReturnSign($("#verifyResult"+id).val()) + '",';
   		}
   		str += '"wfRisk.riskId":"' + $("#wfRisk_riskId").val() + '",';
   		str += '"wfRiskReply.id":"' + id + '"';
   		str += '}';
       	
       	$.post("WfRiskReply!approve.shtml",
   			eval('(' + str + ')'),
			function(data) {
			    $.ligerDialog.success(data,"提示",function(){
			    	window.location = "./WfRisk!managerRisk.shtml?wfRisk.riskId="+$("#wfRisk_riskId").val();
			    });
			},
			"text"
   	    );
       }
    
    
   function  showReturnBack(){
	   $.post("QuesResp!sav.shtml?quesResp.respType=2&loadReturnBackQR=1&quesResp.status=-2&quesResp.quesId="+$("#wfQues_quesId").val()+"&quesResp.usrId="+$("#sessionScope_SYUSR_id").val(),
   				function(data) {
		             window.location = "./WfQues!managerQues.shtml?loadReturnBackQR=1&wfQues.quesId="+$("#wfQues_quesId").val();
   				},
   				"text"
   	    );
   }
	   
   function showGoRisk(){
	   $.post("QuesResp!sav.shtml?quesResp.respType=4&loadGoRisk=1&quesResp.status=-2&quesResp.quesId="+$("#wfQues_quesId").val(),
   				function(data) {
		             window.location = "./WfQues!managerQues.shtml?loadGoRisk=1&wfQues.quesId="+$("#wfQues_quesId").val();
   				},
   				"text"
   	    );
   }

    
    var dis = false; 	
    function showFlexPage(){
   	 if(dis){
   		    document.getElementById("flexpage").style.display = "none";
   			document.getElementById("showFlexBtn").value= "浏览更新文档";
   			dis = false;
   	 }else{
   		    document.getElementById("flexpage").style.display = "";
   			document.getElementById("showFlexBtn").value= "隐藏更新文档";
   			dis = true;
   	 }
   }
    
    function uploadFile(riskReplyId,upFielType,textId) {
        var gngile_uploadURL = $('#gngile_upload_URL').val();
    	var server_URL = $('#server_URL').val();
    	var url = gngile_uploadURL
          /**RUL后面跟的参数 **/
    	         +'?syId='+$("#syId").val()    //系统ID
    	         +'&syNm='+$("#syNm").val()  //系统名字
    	         +'&usrId='+$("#usrId").val() //用户ID
    	         +'&usrNm='+$("#usrNm").val() //用户名字
    	         +'&diyFolder='+"RiskFile" //文件上传的目录中自定义部分
    	         +'&uploadType='+"BaseLib" 
             //上传的是ProcFile，取值只能是ProcFile或者BaseLib
             //ProcFile说明上传的事过程文档，BaseLib说明上传的是归档的文档
             +'&fileVersion='+"1.01" //文件版本号，自己在业务逻辑中确定
    	         +'&callBackUrl='+server_URL+"/WfRiskReply!afterUploadFile.shtml"//回调的URL,对应你Action中的一个方法，在这个方法里面处理上传完后的业务逻辑
             +'&tempParams='+"wfRiskReply.id:"+riskReplyId+",upFielType:"+upFielType;  //临时存放在gnfile的参数,回调时会原样回传过来，自己再解析
          /**RUL后面跟的参数 **/
    url=encodeURI(url);  //encodeURI
    var dialog = $.ligerDialog.open({title:'上传附件', height: 500, width: 470,url:url,isResize: true, name :'dlgUploader'});
    //监听Dialog关闭事件
    $(".l-dialog-winbtns").click(function(){
    	//alert("./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val());
    	//	window.location = "./WfQues!add.shtml?reload=1&isFromWf=0&wfQues.quesId="+$("#quesId").val();
    	//textId= textId+qr_id;
    	//var text = encodeURI(document.getElementById(textId).value);
    	 //alert(document.getElementById(textId).value);
    	 window.location = "./WfRisk!managerRisk.shtml?wfRisk.riskId="+$("#wfRisk_riskId").val();
    });
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
    
	<input type="hidden" id="responsibleUID" name="responsibleUID" size="30" value="<c:out value="${responsibleUID}"/>"/>
		
	<input type="hidden" id="wfRisk_riskId" name="wfRisk_riskId" value="<c:out value="${wfRisk.riskId}"/>">
	<input type="hidden" id="prjtNo" name="prjtNo" value="<c:out value="${wfRisk.prjtNo}"/>">
	<input type="hidden" id="responsibleUID" name="responsibleUID" size="30" value="<c:out value="${responsibleUID}"/>"/>
	
	<input type="hidden" id="loadHangupQR" name="loadHangupQR" size="30" value="<c:out value="${loadHangupQR}"/>" />
	<input type="hidden" id="loadGoRisk" name="loadGoRisk" size="30" value="<c:out value="${loadGoRisk}"/>" />
	<input type="hidden" id="loadReturnBackQR" name="loadReturnBackQR" size="30" value="<c:out value="${loadReturnBackQR}"/>" />
	<input type="hidden" id="returnBackQR" name="returnBackQR" size="30" value="<c:out value="${returnBackQR}"/>" />
	
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
        <div class="allTit"  id = "台头">
            <p class="borBRed">
                <%-- <span><b>所属流程：</b><c:out value="${wfRisk.wfName}"/></span> --%>
                <span><b>项目：</b><c:out value="${wfRisk.prjtNm}"/></span>
                <span><b>创建人：</b><c:out value="${wfRisk.createUserName}"/></span>
                <span><b>提出时间：</b><fmt:formatDate type="both"   value="${wfRisk.createDate}"/></span>
                <span><b>状态：</b> 
                    <c:choose>
			            <c:when test="${wfRisk.status ==1}">
			                   OPEN         <!--  未评估 -->
			            </c:when>
			            <c:when test="${wfRisk.status ==2}">
			                 OPEN         <!--      评估中 -->
			           </c:when>
			           <c:when test="${wfRisk.status ==3}">
			                  OPEN             <!-- 已评估 -->
			           </c:when>
			           <c:when test="${wfRisk.status ==4}">
			                              作废
			           </c:when>
			           <c:when test="${wfRisk.status ==5}">
			                              CLOSE
			           </c:when>
		            </c:choose>
		        </span>
            </p>
        </div>
        
        <div class="area01" id = "风险描述">
            <div class="titBox">
                <h3>风险描述</h3>
            </div>
            <div class="area01Con">
                <table class="table01 table02">
                    <tbody>
                        <tr>
                            <th nowrap="nowrap" >类别</td>
                            <td nowrap="nowrap"><c:out value="${wfRisk.riskCategory}"/></td>
                        </tr>
                         <tr>
                            <th nowrap="nowrap">问题说明</td>
                            <td nowrap="nowrap"><textarea class="tableTextarea01" disabled><c:out value="${wfRisk.description}"  escapeXml="false"/></textarea></td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap">风险说明及后果</td>
                            <td nowrap="nowrap"><textarea class="tableTextarea01" disabled><c:out value="${wfRisk.riskConsequence}"  escapeXml="false"/></textarea></td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap">拟采取措施</td>
                            <td nowrap="nowrap"><textarea class="tableTextarea01" disabled><c:out value="${wfRisk.preventiveMeasures}"  escapeXml="false"/></textarea></td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap">风险监控结果</td>
                            <td nowrap="nowrap"><textarea class="tableTextarea01" disabled><c:out value="${wfRisk.riskMonitor}"  escapeXml="false"/></textarea></td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap" >提出部门</td>
                            <td nowrap="nowrap"><c:out value="${wfRisk.deptName}"/></td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap" >负责人</td>
                            <td nowrap="nowrap" id = "responsible">
                            <c:out value="${wfRisk.responsibleUserName}"/>
                            <c:if test="${wfRisk.status == 1}"> 
                                <input id="upBtn" type="button" value=" 转交" class="wfbigbtn2"  onclick="updateResponsibleUser();" />
                                <div id="prompt" class="showinfo">风险责任人可以更改</div>
                             </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap" >附件</td>
                            <td nowrap="nowrap">
                                  <img align="middle" src="./include/img/workflow/<c:out value="${wfRisk.fileIcon}"/>"/>
							    	<a class="colorBlue padLR5" href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${wfRisk.fileNo}"/>' >
							    	 <c:out value="${wfRisk.fileName}"/>
                                  </a>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="padd0">
                                <table>
                                    <tbody>
                                        <tr>
                                            <th nowrap="nowrap">风险提出者</th><td nowrap="nowrap"><span class="padLR5"><c:out value="${wfRisk.createUserName}"/></span></td>
                                            <th nowrap="nowrap">执行日期</th><td nowrap="nowrap"><c:out value="${wfRisk.executionDate}"/></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        
<!-- 
        
         <div class="area01" id = "解决措施及效果验证">
            <div class="titBox">
                <h3>解决措施及效果验证</h3>
            </div>
            <div class="area01Con">
              <c:forEach items="${wfRisk.wfRiskReplyList}" var="re">
                <table class="table01 table02">
                    <tbody>
                        <tr>
                            <th nowrap="nowrap">措施</td>
                            <td nowrap="nowrap">
                                   <c:choose>
							        <c:when test="${re.responsibleUserId == sessionScope.SYUSR.id }">
							            <textarea class="tableTextarea01" id= "measures<c:out value="${re.id}"/>" autofocus="autofocus" ><c:out value="${re.measures}"/> </textarea>
						            </c:when>
						            <c:otherwise>
								  		<textarea class="tableTextarea01" id= "measures<c:out value="${re.id}"/>"  readonly="readonly"   ><c:out value="${re.measures}"/></textarea> 
								    </c:otherwise>
								 </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th nowrap="nowrap" >措施附件</td>
                            <td nowrap="nowrap">
                            <img align="middle" src="./include/img/workflow/<c:out value="${re.measuresFileIcon}"/>"/>
					    	<a class="colorBlue padLR5" href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${re.measuresFileNo}"/>'>
					    	 <c:out value="${re.measuresFileName}"/>
					    	</a>
                             <c:choose>
						        <c:when test="${re.responsibleUserId == sessionScope.SYUSR.id && wfRisk.status != 2 && wfRisk.status != 3}">
	                                  <a href="javascript:uploadFile(<c:out value="${re.id}"/>,'measuresFile','measures');" class="colorBlue padLR5">上传附件</a>
					            </c:when>
							 </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="padd0">
                                <table>
                                    <tbody>
                                        <tr>
                                            <th nowrap="nowrap">负责人</th><td nowrap="nowrap"><c:out value="${re.responsibleUserName}"/></td>
                                            <th nowrap="nowrap">完成时间</th><td nowrap="nowrap"><fmt:formatDate type="both"   value="${re.measuresDate}"/></td>
                                            
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="noBorR"></td>
	                          <td nowrap="nowrap" class= "inTableBu texAliC">
								<c:if test="${re.status != 2 && re.status != 3}">
									<c:if test="${re.responsibleUserId == sessionScope.SYUSR.id && wfRisk.status != 2 && wfRisk.status != 3}">
	                               	 <button id="savmeasuresBtn<c:out value="${re.id}"/>" class="tableBut" onclick="saveMeasures(<c:out value="${re.id}"/>);" >提交解决措施</button>
									</c:if>
								</c:if >
								<c:if test="${wfRisk.createUserId == sessionScope.SYUSR.id && wfRisk.status != 2 && wfRisk.status != 3}">
								 <c:choose>
								 	<c:when test="${re.status == 1 || re.status == -1}">
										<button id="rushBtn" class="tableBut" onclick="rush(<c:out value="${re.responsibleUserId}"/>,<c:out value="${wfRisk.riskId}"/>,<c:out value="${re.id}"/>);">催办</button>
								    	<span id="rushDate<c:out value="${re.id}"/>">
										<c:if test="${!empty re.rushDate}">
											上次催办时间：<fmt:formatDate type="both" value="${re.rushDate}"/>
										</c:if>
									</span>
								    </c:when>
								 </c:choose>
									
								</c:if>
	                         </td>
                         </tr>
                        
                        <tr>
                            <th nowrap="nowrap">验证说明</td>
                            <td nowrap="nowrap">
                              <c:choose>
						        <c:when test="${wfRisk.createUserId == sessionScope.SYUSR.id }">
								    <textarea class="tableTextarea01" id="verifyResult<c:out value="${re.id}"/>"  autofocus="autofocus" ><c:out value="${re.verifyResult}"/></textarea>
					            </c:when>
					            <c:otherwise>
							  		 <textarea class="tableTextarea01" id="verifyResult<c:out value="${re.id}"/>"  readonly="readonly" ><c:out value="${re.verifyResult}"/></textarea>
					            </c:otherwise>
							 </c:choose>
                            </td>
                        </tr>
                        
                        <tr>
                            <th nowrap="nowrap" >验证说明附件</td>
                            <td nowrap="nowrap">
						    	<img align="middle" src="./include/img/workflow/<c:out value="${re.verifyFileIcon}"/>"/>
						    	<a class="colorBlue padLR5" href='<c:out value="${initParam.gngile_download_URL}"/>?fileNo=<c:out value="${re.verifyFileNo}"/>'>
						    	 <c:out value="${re.verifyFileName}"/>
						    	</a>
						    	<c:choose>
							        <c:when test="${wfRisk.createUserId == sessionScope.SYUSR.id && wfRisk.status != 2 && wfRisk.status != 3}">
										   <a href="javascript:uploadFile(<c:out value="${re.id}"/>,'verifyResultFile','verifyResult');" class="colorBlue padLR5">上传附件</a>
						            </c:when>
								 </c:choose>
                            </td>
                        </tr>
                        
			                        
			            <tr>
                            <td colspan="2" class="padd0">
                                <table>
                                    <tbody>
                                        <tr>
                                            <th nowrap="nowrap">验证人</th><td nowrap="nowrap"><c:out value="${wfRisk.createUserName}"/></td>
                                            <th nowrap="nowrap">验证时间</th><td nowrap="nowrap"><fmt:formatDate type="both"   value="${re.verifyDate}"/></td>
                                            <th nowrap="nowrap">验证效果</th>
                                            <td nowrap="nowrap">
                                                 <c:choose>
										            <c:when test="${re.status == -1}">
										                              无效
										            </c:when>
										            <c:when test="${re.status ==2}">
										                              有效
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
							     	<c:when test="${wfRisk.createUserId == sessionScope.SYUSR.id && wfRisk.status != 2 && wfRisk.status != 3 && !empty re.measures}">
							    	<c:choose>
							    	 <c:when test="${re.status == 3}">
								          <button id="rejectBtn<c:out value="${re.id}"/>" class="tableBut" onclick="approve(<c:out value="${re.id}"/>);" >有效</button>
								     	  <button id="approveBtn<c:out value="${re.id}"/>" class="tableBut" onclick="reject(<c:out value="${re.id}"/>,<c:out value="${re.responsibleUserId}"/>);">无效</button>
								     </c:when>
								     <c:when test="${re.status == -1}">
								          <button id="rejectBtn<c:out value="${re.id}"/>" class="tableBut" onclick="approve(<c:out value="${re.id}"/>);" >有效</button>
								     </c:when>
								      <c:when test="${re.status == 2}">
								        <button id="approveBtn<c:out value="${re.id}"/>" class="tableBut" onclick="reject(<c:out value="${re.id}"/>,<c:out value="${re.responsibleUserId}"/>);">无效</button>
								      </c:when>
							    	</c:choose>
						         </c:when>
						      </c:choose>
                           </td>
                        </tr>
                    </tbody>
                </table>
               </c:forEach>
            </div>
</div>
 -->
</div>

</body>
